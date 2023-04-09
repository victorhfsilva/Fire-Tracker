package com.victor.firetrackerapi.security;

import org.springframework.context.annotation.Configuration;
import org.springframework.http.HttpMethod;
import org.springframework.security.config.annotation.authentication.builders.AuthenticationManagerBuilder;
import org.springframework.security.config.annotation.method.configuration.EnableGlobalMethodSecurity;
import org.springframework.security.config.annotation.web.builders.HttpSecurity;
import org.springframework.security.config.annotation.web.configuration.EnableWebSecurity;
import org.springframework.security.config.annotation.web.configuration.WebSecurityConfigurerAdapter;


public class WebSecurityConfig extends WebSecurityConfigurerAdapter {

    @Override
    protected void configure(AuthenticationManagerBuilder auth) throws Exception {
        auth.inMemoryAuthentication()
                .withUser("arduino-uno")
                .password("{noop}password")
                .roles("DEVICE")
                .and()
                .withUser("admin")
                .password("{noop}password")
                .roles("DEVICE", "ADMIN");
    }

    @Override
    protected void configure(HttpSecurity http) throws Exception {
        http.authorizeRequests()
                .antMatchers("/").permitAll()
                .antMatchers(HttpMethod.GET, "/firetracker/data").hasAnyRole("ADMIN")
                .antMatchers(HttpMethod.DELETE, "/firetracker/data").hasAnyRole("ADMIN")
                .antMatchers(HttpMethod.POST, "/firetracker/data/new").hasAnyRole("DEVICE")
                .antMatchers(HttpMethod.GET, "/firetracker/isOnFire").permitAll()
                .anyRequest().authenticated().and().formLogin();
    }
}