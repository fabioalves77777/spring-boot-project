package com.project.config;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.security.authentication.AuthenticationManager;
import org.springframework.security.config.annotation.authentication.builders.AuthenticationManagerBuilder;
import org.springframework.security.config.annotation.method.configuration.EnableGlobalMethodSecurity;
import org.springframework.security.config.annotation.web.builders.HttpSecurity;
import org.springframework.security.config.annotation.web.configuration.EnableWebSecurity;
import org.springframework.security.config.annotation.web.configuration.WebSecurityConfigurerAdapter;
import org.springframework.security.config.http.SessionCreationPolicy;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.security.web.authentication.UsernamePasswordAuthenticationFilter;

import com.project.security.jwt.JwtAuthEntryPoint;
import com.project.security.jwt.JwtAuthTokenFilter;
import com.project.services.UserDetailsServiceImpl;

@Configuration
@EnableWebSecurity
@EnableGlobalMethodSecurity( prePostEnabled = true )
public class WebSecurityConfig extends WebSecurityConfigurerAdapter 
{
	@Autowired
    private UserDetailsServiceImpl userDetailsService;
	@Autowired
    private JwtAuthEntryPoint unauthorizedHandler;
	@Autowired
    private BCryptPasswordEncoder passwordEncoder;
	@Autowired
    private JwtAuthTokenFilter jwtAuthTokenFilter;
	
	@Bean
    @Override
    public AuthenticationManager authenticationManagerBean() throws Exception {
        return super.authenticationManagerBean();
    }
	
    @Override
    protected void configure(AuthenticationManagerBuilder auth) throws Exception 
    {
    	auth.userDetailsService(userDetailsService).passwordEncoder(passwordEncoder);
    }   

    @Override
    protected void configure(HttpSecurity http) throws Exception 
    {
    	http.cors().and().csrf().disable().authorizeRequests()
        .antMatchers(AUTH_WHITELIST).permitAll()
        .anyRequest().authenticated().and()
        .exceptionHandling().authenticationEntryPoint(unauthorizedHandler).and()
        .sessionManagement().sessionCreationPolicy(SessionCreationPolicy.STATELESS);
    	http.headers().frameOptions().disable();
    	http.addFilterBefore(jwtAuthTokenFilter, UsernamePasswordAuthenticationFilter.class);
    }
    
    private static final String[] AUTH_WHITELIST = 
	{
        // -- swagger ui
        "/v2/api-docs",
        "/swagger-resources",
        "/swagger-resources/**",
        "/configuration/ui",
        "/configuration/security",
        "/swagger-ui.html",
        "/webjars/**",
        "/resources/**", 
        // other public endpoints of your API may be appended to this array
        "/static/**", 
        "/css/**", 
        "/js/**", 
        "/images/**",
        "/h2-console/**",
        "/api/auth/**"
    };

}