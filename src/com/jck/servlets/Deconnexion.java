package com.jck.servlets;

import java.io.IOException;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

/**
 * Servlet implementation class Deconnexion
 */
@WebServlet( "/Deconnexion" )
public class Deconnexion extends HttpServlet {
    private static final long  serialVersionUID = 1L;
    public static final String URL_REDIRECTION  = "/pro/connexion";

    public Deconnexion() {
        super();

    }

    protected void doGet( HttpServletRequest request, HttpServletResponse response ) throws ServletException,
            IOException {

        HttpSession session = request.getSession();
        session.invalidate();

        response.sendRedirect( URL_REDIRECTION );

    }

    protected void doPost( HttpServletRequest request, HttpServletResponse response ) throws ServletException,
            IOException {

    }

}
