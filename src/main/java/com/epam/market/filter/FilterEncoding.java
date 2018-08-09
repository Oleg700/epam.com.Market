package com.epam.market.filter;

import com.epam.market.validation.Validation;
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
        String email = request1.getParameter("email");
        boolean checkEmail;
        if (email != null && !Validation.isValid(email)) {
            checkEmail = false;
            request1.setAttribute("checkEmail", checkEmail);
            chain.doFilter(request1, response1);
        } else {
            chain.doFilter(request1, response1);
        }
    }

    @Override
    public void init(FilterConfig filterConfig){
    }

    @Override
    public void destroy() {
    }
}
