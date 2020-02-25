package com.example.config;

import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.ComponentScan;
import org.springframework.context.annotation.Configuration;
import org.springframework.web.servlet.config.annotation.EnableWebMvc;
import org.springframework.web.servlet.config.annotation.ResourceHandlerRegistry;
import org.springframework.web.servlet.view.InternalResourceViewResolver;

@Configuration
@EnableWebMvc
@ComponentScan(basePackages = "example.com")
public class WebConfig {

    // =======================================
    // =             Bean Config             =
    // =======================================

    @Bean
    public InternalResourceViewResolver getInternalResourceViewResolver(){
        InternalResourceViewResolver resolver = new InternalResourceViewResolver();
        resolver.setPrefix("/WEB-INF/jsp/");
        resolver.setSuffix(".jsp");

        return resolver;
    }


    // =======================================
    // =          Override Methods           =
    // =======================================

    public void addResourceHandlers(ResourceHandlerRegistry registry) {
        registry.addResourceHandler("/pdfs/**")
                .addResourceLocations("/WEB-INF/pdfs/");

        registry.addResourceHandler("/css/**")
                .addResourceLocations("/WEB-INF/css/");

        registry.addResourceHandler("/js/**")
                .addResourceLocations("/WEB-INF/js/");
    }

}
