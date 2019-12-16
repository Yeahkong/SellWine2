package com.hzxy.config;

import org.springframework.context.annotation.Configuration;
import org.springframework.web.servlet.config.annotation.ResourceHandlerRegistry;
import org.springframework.web.servlet.config.annotation.WebMvcConfigurerAdapter;

import javax.annotation.Resource;

/**
 * @Program SmartHome
 * @Package com.hzxy.config
 * @ClassName WebMvcConfigurer
 * @Author liuningying
 * @Date 2019-09-09 16:22
 */
@Configuration
public class WebMvcConfigurer extends WebMvcConfigurerAdapter {
    @Resource
    private UploadProperties uploadProperties;

    @Override
    public void addResourceHandlers(ResourceHandlerRegistry registry) {
        String path = uploadProperties.getBasePath();
        registry.addResourceHandler("/path/**").addResourceLocations("file://" + path);
        super.addResourceHandlers(registry);
    }
}
