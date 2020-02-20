package com.example.postbar.interceptor;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Configuration;
import org.springframework.web.servlet.config.annotation.InterceptorRegistry;
import org.springframework.web.servlet.config.annotation.WebMvcConfigurer;

/**
 * @author zhangxu
 * @title
 * @date 2019/8/21 17:37
 */
@Configuration
public class WebConfig implements WebMvcConfigurer {

    @Autowired
    private SessionInterceptor sessionInterceptor;

    @Override
    public void addInterceptors(InterceptorRegistry registry) {
        String[] exclude = {"/login", "/user/login", "/loginout"};
        registry.addInterceptor(sessionInterceptor).addPathPatterns("/**").excludePathPatterns(exclude);
    }
}
