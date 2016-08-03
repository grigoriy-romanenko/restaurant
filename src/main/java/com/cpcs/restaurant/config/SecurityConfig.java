package com.cpcs.restaurant.config;

import com.cpcs.restaurant.service.UserDetailsServiceImpl;
import com.cpcs.restaurant.service.UserServiceImpl;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.security.config.annotation.authentication.builders.AuthenticationManagerBuilder;
import org.springframework.security.config.annotation.web.builders.HttpSecurity;
import org.springframework.security.config.annotation.web.configuration.EnableWebSecurity;
import org.springframework.security.config.annotation.web.configuration.WebSecurityConfigurerAdapter;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;

@Configuration
@EnableWebSecurity
public class SecurityConfig extends WebSecurityConfigurerAdapter {

    @Override
    protected void configure(AuthenticationManagerBuilder auth) throws Exception {
        auth.userDetailsService(userDetailsService()).passwordEncoder(new BCryptPasswordEncoder());
    }

    @Override
    protected void configure(HttpSecurity http) throws Exception {
        http
                .authorizeRequests()
                        .antMatchers("/menu/**", "/resources/**", "/register").permitAll()
                        .anyRequest().authenticated()
                        .and()
                .formLogin()
                        .loginProcessingUrl("/login").permitAll()
                        .loginPage("/login").permitAll()
                        .and()
                .logout()
                        .logoutUrl("/logout").permitAll()
                        .invalidateHttpSession(true)
                        .and()
                .csrf().disable()
                .headers().cacheControl();
    }

    @Bean
    public UserDetailsService userDetailsService() {
        return new UserDetailsServiceImpl();
    }

}