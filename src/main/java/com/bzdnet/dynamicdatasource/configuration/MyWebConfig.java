package com.bzdnet.dynamicdatasource.configuration;

import com.bzdnet.dynamicdatasource.interceptor.ChangeDatasource;
import org.springframework.context.annotation.Configuration;
import org.springframework.web.servlet.config.annotation.InterceptorRegistry;
import org.springframework.web.servlet.config.annotation.WebMvcConfigurationSupport;

/**
 * @author Administrator
 * 2018-11-11 18:21
 **/
@Configuration
public class MyWebConfig extends WebMvcConfigurationSupport {

    @Override
    protected void addInterceptors(InterceptorRegistry registry) {
        registry.addInterceptor(new ChangeDatasource()).addPathPatterns("/**");
    }
}
