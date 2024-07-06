package com.silkchain.Silkchain.service;

import javax.servlet.*;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;

public class RoleCheckFilter implements Filter {

    @Override
    public void doFilter(ServletRequest request, ServletResponse response, FilterChain chain) throws IOException, ServletException {
        HttpServletRequest httpRequest = (HttpServletRequest) request;
        HttpServletResponse httpResponse = (HttpServletResponse) response;

        // Check if user is authenticated and has admin role
        String role = (String) httpRequest.getSession().getAttribute("role");
        System.out.println("Role from session: " + role);
        if (role == null || !role.equals("ROLE_ADMIN")) {
            System.out.println("this user is not admin ");
            httpResponse.sendError(HttpServletResponse.SC_UNAUTHORIZED, "You are not authorized to access this resource.");
            return;
        }

        chain.doFilter(request, response);
    }
}

