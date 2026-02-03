package com.maryjainprojects.firstjobapp.config;

import org.springframework.boot.autoconfigure.condition.ConditionalOnProperty;
import org.springframework.boot.web.servlet.ServletRegistrationBean;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

@Configuration
@ConditionalOnProperty(name = "spring.h2.console.enabled", havingValue = "true")
public class H2ConsoleConfiguration {

    @Bean
    public ServletRegistrationBean<?> h2ConsoleServletRegistration() {
        try {
            Class<?> webServletClass = Class.forName("org.h2.server.web.JakartaWebServlet");
            Object servlet = webServletClass.getDeclaredConstructor().newInstance();
            ServletRegistrationBean<jakarta.servlet.Servlet> registration =
                    new ServletRegistrationBean<>((jakarta.servlet.Servlet) servlet);
            registration.addUrlMappings("/h2-console/*");
            return registration;
        } catch (Exception e) {
            try {
                Class<?> webServletClass = Class.forName("org.h2.server.web.WebServlet");
                Object servlet = webServletClass.getDeclaredConstructor().newInstance();
                ServletRegistrationBean<jakarta.servlet.Servlet> registration =
                        new ServletRegistrationBean<>((jakarta.servlet.Servlet) servlet);
                registration.addUrlMappings("/h2-console/*");
                return registration;
            } catch (Exception ex) {
                throw new RuntimeException("Could not create H2 console servlet", ex);
            }
        }
    }
}