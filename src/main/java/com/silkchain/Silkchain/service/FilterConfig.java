package com.silkchain.Silkchain.service;

import org.springframework.boot.web.servlet.FilterRegistrationBean;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

@Configuration
public class FilterConfig {

    @Bean
    public FilterRegistrationBean<RoleCheckFilter> roleCheckFilter() {
        FilterRegistrationBean<RoleCheckFilter> registrationBean = new FilterRegistrationBean<>();
        registrationBean.setFilter(new RoleCheckFilter());
        registrationBean.addUrlPatterns("/dashboard/table.html","/dashboard/profile.html", "/dashboard/dashboard.html");
        return registrationBean;
    }
}
