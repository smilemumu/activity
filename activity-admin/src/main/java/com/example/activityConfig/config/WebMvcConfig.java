package com.example.activityConfig.config;

import org.springframework.beans.factory.annotation.Value;
import org.springframework.context.annotation.Configuration;
import org.springframework.web.servlet.config.annotation.ResourceHandlerRegistry;
import org.springframework.web.servlet.config.annotation.WebMvcConfigurer;

import java.io.File;

@Configuration
public class WebMvcConfig implements WebMvcConfigurer {

    @Value("${file.upload.path:./uploads/}")
    private String uploadPath;

    @Override
    public void addResourceHandlers(ResourceHandlerRegistry registry) {
        File dir = new File(uploadPath);
        if (!dir.isAbsolute()) {
            dir = new File(System.getProperty("user.dir"), uploadPath);
        }
        String location = "file:" + dir.getAbsolutePath();
        if (!location.endsWith("/")) {
            location += "/";
        }
        registry.addResourceHandler("/uploads/**")
                .addResourceLocations(location);
    }
}
