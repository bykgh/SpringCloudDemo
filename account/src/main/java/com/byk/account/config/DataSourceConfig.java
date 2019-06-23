package com.byk.account.config;

import com.byk.account.entity.User;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.boot.autoconfigure.jdbc.DataSourceProperties;
import org.springframework.boot.context.properties.ConfigurationProperties;
import org.springframework.boot.jdbc.DataSourceBuilder;
import org.springframework.boot.orm.jpa.EntityManagerFactoryBuilder;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.context.annotation.Primary;
import org.springframework.orm.jpa.JpaTransactionManager;
import org.springframework.orm.jpa.LocalContainerEntityManagerFactoryBean;
import org.springframework.transaction.PlatformTransactionManager;

import javax.sql.DataSource;
import java.util.Properties;


/**
 * 配置数据源
 * @author yikai.bi
 */
@Configuration
public class DataSourceConfig {

    private static final Logger LOGGER = LoggerFactory.getLogger(DataSourceConfig.class);

    @Bean(name = "omsDataSource")
    @Primary
    public DataSource omsDataSource() {
        DataSourceProperties manDataSourceProperties = omsDataSourceProperties();
        return DataSourceBuilder.create()
                .driverClassName(manDataSourceProperties.getDriverClassName())
                .url(manDataSourceProperties.getUrl())
                .username(manDataSourceProperties.getUsername())
                .password(manDataSourceProperties.getPassword())
                .build();
    }


    @Bean(name = "omsEntityManagerFactory")
    @Primary
    public LocalContainerEntityManagerFactoryBean omsEntityManagerFactory(EntityManagerFactoryBuilder builder) {
        LocalContainerEntityManagerFactoryBean em = builder
                .dataSource(omsDataSource())
                .packages(User.class)
                .persistenceUnit("oms")
                .build();
        Properties properties = new Properties();
        properties.setProperty("hibernate.physical_naming_strategy", "org.hibernate.boot.model.naming.PhysicalNamingStrategyStandardImpl");
        properties.put("hibernate.dialect", "org.hibernate.dialect.MySQL5InnoDBDialect");
        properties.put("hibernate.id.new_generator_mappings","false");
        em.setJpaProperties(properties);
        return em;
    }

    @Bean(name = "ormTransactionManager")
    public PlatformTransactionManager omsTransactionManager(EntityManagerFactoryBuilder builder) {
        return new JpaTransactionManager(omsEntityManagerFactory(builder).getObject());
    }

    @Bean
    @Primary
    @ConfigurationProperties(prefix="spring.datasource")
    public DataSourceProperties omsDataSourceProperties() { //2
        return new DataSourceProperties();
    }

}