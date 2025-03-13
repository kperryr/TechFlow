package org.taskntech.tech_flow.config;

import org.springframework.context.annotation.Configuration;
import org.springframework.web.servlet.config.annotation.ResourceHandlerRegistry;
import org.springframework.web.servlet.config.annotation.WebMvcConfigurer;

//tells spring where to store profile pics uploads
@Configuration
public class WebConfig implements WebMvcConfigurer {

    @Override
    public void addResourceHandlers(ResourceHandlerRegistry registry) {

        //resource handler maps URL paths to static resources
        registry.addResourceHandler("/uploads/**")
                .addResourceLocations("file:uploads/");
    }
}
