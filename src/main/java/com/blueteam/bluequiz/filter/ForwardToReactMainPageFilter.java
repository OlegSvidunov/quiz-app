package com.blueteam.bluequiz.filter;

import org.springframework.stereotype.Component;

import javax.servlet.*;
import javax.servlet.http.HttpServletRequest;
import java.io.IOException;
import java.util.Arrays;
import java.util.List;

@Component
public class ForwardToReactMainPageFilter implements Filter {

    static final List<String> allowedURIsForSpringToHandleRequest = Arrays.asList("/api", "/static", "/login", "/logout", "/registration");

    @Override
    public void doFilter(ServletRequest request,
                         ServletResponse response,
                         FilterChain chain) throws IOException, ServletException {

        HttpServletRequest req = (HttpServletRequest) request;
        String requestURI = req.getRequestURI();

        //handle api, static and security requests by Spring
        for(String allowedURI: allowedURIsForSpringToHandleRequest) {
            if (requestURI.startsWith(allowedURI)) {
                chain.doFilter(request, response);
                return;
            }
        }

        //let react handle any other requests, by forwarding them to index.html
        request.getRequestDispatcher("/").forward(request, response);
    }

}