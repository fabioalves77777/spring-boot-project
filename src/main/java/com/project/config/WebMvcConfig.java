package com.project.config;

import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.web.servlet.config.annotation.WebMvcConfigurer;

import com.project.security.jwt.JwtAuthEntryPoint;
import com.project.security.jwt.JwtAuthTokenFilter;
import com.project.security.jwt.JwtProvider;

@Configuration
public class WebMvcConfig implements WebMvcConfigurer 
{
    @Bean
    public BCryptPasswordEncoder passwordEncoder() 
    {
        return new BCryptPasswordEncoder();
    }
    
    @Bean
    public JwtAuthTokenFilter authenticationJwtTokenFilter() 
    {
        return new JwtAuthTokenFilter();
    }
    
    @Bean
    public JwtProvider tokenProvider() 
    {
        return new JwtProvider();
    }
    
    @Bean
    public JwtAuthEntryPoint unauthorizedHandler() 
    {
        return new JwtAuthEntryPoint();
    }
}