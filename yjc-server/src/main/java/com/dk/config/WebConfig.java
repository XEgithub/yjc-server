package com.dk.config;

import com.dk.interceptor.WxAuthInterceptor;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Configuration;
import org.springframework.web.servlet.config.annotation.CorsRegistry;
import org.springframework.web.servlet.config.annotation.InterceptorRegistry;
import org.springframework.web.servlet.config.annotation.WebMvcConfigurer;

/**
 * trip
 *
 * @author 张浩伟
 * @version 1.01 2017/12/15
 */
@Configuration
public class WebConfig implements WebMvcConfigurer {

    @Autowired
    private WechatMpProperties wechatMpProperties;

    @Override
    public void addCorsMappings(CorsRegistry registry) {
        registry.addMapping("/rest/**")
                .allowedOrigins("*")
                .allowedMethods("POST")
                .maxAge(3600);
        registry.addMapping("/**");
    }

    @Override
    public void addInterceptors(InterceptorRegistry registry) {
        WxAuthInterceptor wxAuthInterceptor = new WxAuthInterceptor();
        wxAuthInterceptor.setAppId(wechatMpProperties.getAppId());
        wxAuthInterceptor.setHost(wechatMpProperties.getHost());
        registry.addInterceptor(wxAuthInterceptor).addPathPatterns("/wx/**");
    }

}
