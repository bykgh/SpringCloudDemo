package com.byk.core.config;

import com.byk.core.interceptor.RequestLoggingInterceptor;
import com.google.common.collect.ImmutableList;
import org.apache.http.Header;
import org.apache.http.HttpHost;
import org.apache.http.client.HttpClient;
import org.apache.http.client.config.RequestConfig;
import org.apache.http.conn.ssl.NoopHostnameVerifier;
import org.apache.http.conn.ssl.SSLConnectionSocketFactory;
import org.apache.http.conn.ssl.TrustSelfSignedStrategy;
import org.apache.http.conn.ssl.TrustStrategy;
import org.apache.http.impl.client.CloseableHttpClient;
import org.apache.http.impl.client.DefaultConnectionKeepAliveStrategy;
import org.apache.http.impl.client.HttpClientBuilder;
import org.apache.http.impl.client.HttpClients;
import org.apache.http.impl.conn.PoolingHttpClientConnectionManager;
import org.apache.http.message.BasicHeader;
import org.apache.http.ssl.SSLContextBuilder;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.boot.web.client.RestTemplateBuilder;
import org.springframework.cloud.client.loadbalancer.LoadBalanced;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.context.annotation.Primary;
import org.springframework.http.MediaType;
import org.springframework.http.client.ClientHttpRequestFactory;
import org.springframework.http.client.ClientHttpRequestInterceptor;
import org.springframework.http.client.HttpComponentsClientHttpRequestFactory;
import org.springframework.http.client.InterceptingClientHttpRequestFactory;
import org.springframework.http.converter.HttpMessageConverter;
import org.springframework.http.converter.StringHttpMessageConverter;
import org.springframework.web.client.RestTemplate;

import javax.net.ssl.SSLContext;
import java.nio.charset.Charset;
import java.security.KeyManagementException;
import java.security.KeyStoreException;
import java.security.NoSuchAlgorithmException;
import java.security.cert.X509Certificate;
import java.util.ArrayList;
import java.util.List;
import java.util.concurrent.TimeUnit;
import java.util.function.Supplier;

@Configuration
public class RestClientConfig {
    private static final Logger LOGGER = LoggerFactory.getLogger(RestClientConfig.class);

    @Value("${http.proxy.enable}")
    private boolean proxyEnable;
    @Value("${http.proxy.host}")
    private String proxyHost;
    @Value("${http.proxy.port}")
    private Integer proxyPort;

    private HttpClient httpClient(boolean proxyEnable) {
        // 长连接保持30秒
        PoolingHttpClientConnectionManager pollingConnectionManager = new PoolingHttpClientConnectionManager(30,
                TimeUnit.SECONDS);
        // 总连接数
        pollingConnectionManager.setMaxTotal(500);
        // 同路由的并发数
        pollingConnectionManager.setDefaultMaxPerRoute(100);

        HttpClientBuilder httpClientBuilder = HttpClients.custom();
        httpClientBuilder.setConnectionManager(pollingConnectionManager);

        // 重试次数，默认是3次，没有开启
        // httpClientBuilder.setRetryHandler(new
        // DefaultHttpRequestRetryHandler(2, true));

        // 保持长连接配置，需要在头添加Keep-Alive
        httpClientBuilder.setKeepAliveStrategy(DefaultConnectionKeepAliveStrategy.INSTANCE);

        List<Header> headers = new ArrayList<>();
        headers.add(new BasicHeader("Connection", "keep-alive"));
        httpClientBuilder.setDefaultHeaders(headers);

        RequestConfig.Builder requestConfigBuilder = RequestConfig.custom()
                // 数据读取超时时间，即ReadTimeout
                .setSocketTimeout(25000)
                // 连接超时
                .setConnectTimeout(5000)
                // 连接不够用的等待时间，不宜过长，必须设置，比如连接不够用时，时间过长将是灾难性的
                .setConnectionRequestTimeout(200);

        if (proxyEnable) {
            requestConfigBuilder.setProxy(new HttpHost(proxyHost, proxyPort));
        }
        return httpClientBuilder.setDefaultRequestConfig(requestConfigBuilder.build()).build();
    }

    @Bean
    public Supplier<ClientHttpRequestFactory> proxiedHttpRequestFactory() {

        return  new Supplier<ClientHttpRequestFactory>() {
            @Override
            public ClientHttpRequestFactory get() {
                List<ClientHttpRequestInterceptor> interceptors = new ArrayList<>(1);
                interceptors.add(requestLoggingInterceptor());
                InterceptingClientHttpRequestFactory interceptingClientHttpRequestFactory =
                        new InterceptingClientHttpRequestFactory(new HttpComponentsClientHttpRequestFactory(httpClient(true)),
                                interceptors);
                return interceptingClientHttpRequestFactory;
            }
        };
    }

    @Bean
    public Supplier<ClientHttpRequestFactory> httpRequestFactory() {
        return  new Supplier<ClientHttpRequestFactory>() {
            @Override
            public ClientHttpRequestFactory get() {
                List<ClientHttpRequestInterceptor> interceptors = new ArrayList<>(1);
                interceptors.add(requestLoggingInterceptor());
                InterceptingClientHttpRequestFactory interceptingClientHttpRequestFactory =
                        new InterceptingClientHttpRequestFactory(new HttpComponentsClientHttpRequestFactory(httpClient(false)),
                                interceptors);
                return interceptingClientHttpRequestFactory;
            }
        };
    }

    @Bean(name = "defaultRestTemplate")
    @LoadBalanced
    @Primary
    public RestTemplate defaultRestTemplate(RestTemplateBuilder builder) {
        if (proxyEnable) {
            return proxiedRestTemplate(builder);
        }
        return restTemplate(builder);
    }

    @Bean(name = "proxiedRestTemplate")
    public RestTemplate proxiedRestTemplate(RestTemplateBuilder builder) {

        RestTemplate restTemplate = builder.requestFactory(proxiedHttpRequestFactory()).build();
        reconfigStringConverter(restTemplate);
        return restTemplate;
    }

    @Bean(name = "restTemplate")
    public RestTemplate restTemplate(RestTemplateBuilder builder) {
        RestTemplate restTemplate =  builder.requestFactory(httpRequestFactory()).build();
        reconfigStringConverter(restTemplate);
        return restTemplate;
    }

    @Bean
    public RestTemplateBuilder restTemplateBuilder() {
        RestTemplateBuilder builder = new RestTemplateBuilder();
        return builder;
    }

    @Bean
    RequestLoggingInterceptor requestLoggingInterceptor() {
        return new RequestLoggingInterceptor();
    }

    @Bean(name = "restTemplateHttpsProxy")
    public RestTemplate restTemplateHttpsProxy() throws KeyStoreException, NoSuchAlgorithmException, KeyManagementException {
        TrustStrategy acceptingTrustStrategy = (X509Certificate[] chain, String authType) -> true;

        SSLContext sslContext = org.apache.http.ssl.SSLContexts.custom()
                .loadTrustMaterial(null, acceptingTrustStrategy)
                .build();

        SSLContextBuilder builder = new SSLContextBuilder();
        builder.loadTrustMaterial(null, new TrustSelfSignedStrategy());
        SSLConnectionSocketFactory csf  = new SSLConnectionSocketFactory(builder.build(), NoopHostnameVerifier.INSTANCE);

        CloseableHttpClient httpClient = HttpClients.custom()
                .setSSLSocketFactory(csf).setProxy(new HttpHost(proxyHost, proxyPort))
                .build();

        HttpComponentsClientHttpRequestFactory requestFactory =
                new HttpComponentsClientHttpRequestFactory();

        requestFactory.setHttpClient(httpClient);
        RestTemplate restTemplate = new RestTemplate(requestFactory);
        return restTemplate;
    }

    private void reconfigStringConverter(RestTemplate restTemplate) {
        List<HttpMessageConverter<?>> converters = restTemplate.getMessageConverters();
        for (HttpMessageConverter<?> converter : converters) {
            if (converter instanceof StringHttpMessageConverter) {
                StringHttpMessageConverter stringConverter = (StringHttpMessageConverter) converter;
                // 防止请求外部接口时写AcceptCharset头太大
                stringConverter.setWriteAcceptCharset(false);
                // 默认 Content-Type=[text/plain;charset=ISO-8859-1] 改为UTF-8
                stringConverter.setSupportedMediaTypes(ImmutableList.of(
                        new MediaType("text", "plain", Charset.forName("UTF-8")),
                        MediaType.ALL));
                break;
            }
        }
    }
}
