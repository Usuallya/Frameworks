package com.springbootproject.demo.filter;

import javax.servlet.*;
import javax.servlet.annotation.WebFilter;
import java.io.IOException;

//@WebFilter(filterName = "Filter1",urlPatterns = "/*")
public class Filter1 implements Filter {
    public void destroy() {
    }

    public void doFilter(ServletRequest req, ServletResponse resp, FilterChain chain) throws ServletException, IOException {
        System.out.println("进入Filter");
        chain.doFilter(req, resp);
        System.out.println("离开Filter");
    }

    public void init(FilterConfig config) throws ServletException {

    }

}
