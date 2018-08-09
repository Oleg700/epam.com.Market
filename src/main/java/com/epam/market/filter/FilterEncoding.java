package com.epam.market.filter;

import javax.servlet.*;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;

public class FilterEncoding implements Filter {
    @Override
    public void doFilter(ServletRequest request, ServletResponse response, FilterChain chain) throws IOException, ServletException {
        final HttpServletRequest request1 = (HttpServletRequest) request;
        final HttpServletResponse response1 = (HttpServletResponse) response;
        request1.setCharacterEncoding("UTF-8");
        response1.setCharacterEncoding("UTF-8");
        chain.doFilter(request1, response1);
    }

    @Override
    public void init(FilterConfig filterConfig){
    }

    @Override
    public void destroy() {
    }
}
