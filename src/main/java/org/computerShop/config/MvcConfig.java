package org.computerShop.config;

import org.computerShop.email.SendEmail;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.web.servlet.config.annotation.ResourceHandlerRegistry;
import org.springframework.web.servlet.config.annotation.WebMvcConfigurer;

@Configuration
public class MvcConfig implements WebMvcConfigurer {

    @Value("${upload.path}")
    private String  uploadPath;
    @Value("${upload.CategoryPath}")
    private String  path;


    @Override
    public void addResourceHandlers(ResourceHandlerRegistry registry) {
        registry.addResourceHandler("/productImages/**").addResourceLocations("file://" + uploadPath + "/" );
        registry.addResourceHandler("/categoryImages/**").addResourceLocations("file://" + path + "/" );
        registry.addResourceHandler("/static/**").addResourceLocations("classpath:/static/");
    }


}
