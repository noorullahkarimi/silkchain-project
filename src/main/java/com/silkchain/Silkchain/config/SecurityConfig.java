package com.silkchain.Silkchain.config;

import com.silkchain.Silkchain.dao.WalletAuthenticationFilter;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.security.authentication.AuthenticationManager;
import org.springframework.security.config.annotation.authentication.builders.AuthenticationManagerBuilder;
import org.springframework.security.config.annotation.web.builders.HttpSecurity;
import org.springframework.security.config.annotation.web.configuration.EnableWebSecurity;
import org.springframework.security.config.annotation.web.configuration.WebSecurityConfigurerAdapter;
import org.springframework.security.web.authentication.UsernamePasswordAuthenticationFilter;
@Configuration
@EnableWebSecurity
public class SecurityConfig extends WebSecurityConfigurerAdapter {

    @Override
    protected void configure(HttpSecurity http) throws Exception {
        http
                .csrf().disable()
                .authorizeRequests()
                .antMatchers(
                        "/template/**", "/",
                        "/assets/**","/css/**","/js/**",
                        "/img/**", "/dashboard/**", "/enter"
                ).permitAll()
                .anyRequest().authenticated()
                .and()
                .addFilterBefore(walletAuthenticationFilter(), UsernamePasswordAuthenticationFilter.class);
    }

    @Bean
    public WalletAuthenticationFilter walletAuthenticationFilter() throws Exception {
        return new WalletAuthenticationFilter(authenticationManagerBean());
    }

    @Bean
    @Override
    public AuthenticationManager authenticationManagerBean() throws Exception {
        return super.authenticationManagerBean();
    }

    @Override
    protected void configure(AuthenticationManagerBuilder auth) throws Exception {
        // Configure your authentication manager here
    }
}