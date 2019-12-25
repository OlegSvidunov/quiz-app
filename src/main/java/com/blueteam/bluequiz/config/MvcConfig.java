package com.blueteam.bluequiz.config;

import org.springframework.context.annotation.Configuration;
import org.springframework.web.servlet.config.annotation.ViewControllerRegistry;
import org.springframework.web.servlet.config.annotation.WebMvcConfigurer;


@Configuration
public class MvcConfig implements WebMvcConfigurer {

    @Override
    public void addViewControllers(ViewControllerRegistry registry) {
        registry.addViewController("/login").setViewName("login");
        registry.addViewController("/login/error").setViewName("loginError");
        registry.addViewController("/registration").setViewName("registration");
        registry.addViewController("/registration/error").setViewName("registrationError");
    }
}