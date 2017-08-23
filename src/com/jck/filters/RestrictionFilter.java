package com.jck.filters;

import java.io.IOException;

import javax.servlet.Filter;
import javax.servlet.FilterChain;
import javax.servlet.FilterConfig;
import javax.servlet.ServletException;
import javax.servlet.ServletRequest;
import javax.servlet.ServletResponse;
import javax.servlet.annotation.WebFilter;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

/**
 * Servlet Filter implementation class RestrictionFilter
 */
@WebFilter( "/RestrictionFilter" )
public class RestrictionFilter implements Filter {

    public static final String ACCES_PUBLIC     = "/accesPublic.jsp";
    public static final String ATT_SESSION_USER = "sessionUtilisateur";

    public RestrictionFilter() {

    }

    public void init( FilterConfig fConfig ) throws ServletException {

    }

    public void doFilter( ServletRequest req, ServletResponse resp, FilterChain chain ) throws IOException,
            ServletException {
        HttpServletRequest request = (HttpServletRequest) req;
        HttpServletResponse response = (HttpServletResponse) resp;

        HttpSession session = request.getSession();

        if ( session.getAttribute( ATT_SESSION_USER ) == null ) {
            response.sendRedirect( request.getContextPath() + ACCES_PUBLIC );
        } else {
            chain.doFilter( request, response );
        }

    }

    public void destroy() {

    }

}
