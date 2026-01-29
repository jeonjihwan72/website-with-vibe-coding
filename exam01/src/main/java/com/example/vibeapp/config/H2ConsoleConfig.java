package com.example.vibeapp.config;

import org.h2.server.web.JakartaWebServlet;
import org.springframework.boot.web.servlet.ServletRegistrationBean;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

@Configuration
public class H2ConsoleConfig {

    @Bean
    public ServletRegistrationBean<JakartaWebServlet> h2Console() {
        // H2 2.3.232 이상에서 제공하는 JakartaWebServlet을 사용하여
        // Spring Boot 4.0.1 (Jakarta EE 11)과의 호환성을 확보합니다.
        return new ServletRegistrationBean<>(new JakartaWebServlet(), "/h2-console/*");
    }
}
