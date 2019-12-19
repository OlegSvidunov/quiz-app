package com.blueteam.bluequiz.filter;

import org.springframework.stereotype.Component;

import javax.servlet.*;
import javax.servlet.http.HttpServletRequest;
import java.io.IOException;

@Component
public class ForwardToReactMainPageFilter implements Filter {

    @Override
    public void doFilter(ServletRequest request,
                         ServletResponse response,
                         FilterChain chain) throws IOException, ServletException {

        HttpServletRequest req = (HttpServletRequest) request;
        String requestURI = req.getRequestURI();

        //handle api requests by spring
        if (requestURI.startsWith("/api")) {
            chain.doFilter(request, response);
            return;
        }

        //handle static requests by spring
        if (requestURI.startsWith("/static")) {
            chain.doFilter(request, response);
            return;
        }

        //handle login requests by spring
        if (requestURI.startsWith("/login")) {
            chain.doFilter(request, response);
            return;
        }

        //handle logout requests by spring
        if (requestURI.startsWith("/logout")) {
            chain.doFilter(request, response);
            return;
        }

        //handle registration requests by spring
        if (requestURI.startsWith("/registration")) {
            chain.doFilter(request, response);
            return;
        }

        //let react handle any other requests, by forwarding them to index.html
        request.getRequestDispatcher("/").forward(request, response);
    }

}