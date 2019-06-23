package com.byk.account.security;

import org.springframework.security.crypto.password.PasswordEncoder;

/**
 * 无密码验证。测试使用的类
 * @author yikai.bi
 */
public class NoEncryptPasswordEncoder implements PasswordEncoder {

    @Override
    public String encode(CharSequence charSequence) {
        return (String) charSequence;
    }

    @Override
    public boolean matches(CharSequence charSequence, String s) {
        return s.equals((String) charSequence);
    }
}
