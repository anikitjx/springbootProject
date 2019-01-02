package com.orange;

import com.orange.utils.auth.AuthorizationInterceptor;
import org.springframework.context.annotation.Configuration;
import org.springframework.web.servlet.config.annotation.InterceptorRegistry;
import org.springframework.web.servlet.config.annotation.ResourceHandlerRegistry;
import org.springframework.web.servlet.config.annotation.WebMvcConfigurerAdapter;

import javax.annotation.Resource;

@Configuration
public class WebAppConfiguration extends WebMvcConfigurerAdapter {
    @Resource
    AuthorizationInterceptor authorizationInterceptor;
    @Override
    public void addInterceptors(InterceptorRegistry interceptorRegistry) {
        interceptorRegistry.addInterceptor(authorizationInterceptor).addPathPatterns("/**");
    }

    /*
     *配置静态文件
     */
    @Override
    public void addResourceHandlers(ResourceHandlerRegistry registry) {
        registry.addResourceHandler("/**")
                .addResourceLocations("file:C:/orange-videos/")
                .addResourceLocations("file:C:/orangebgm/")
                .addResourceLocations("classpath:/META-INF/resources/").addResourceLocations("classpath:/static/");
    }
}