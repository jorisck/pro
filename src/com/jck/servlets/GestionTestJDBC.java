package com.jck.servlets;

import java.io.IOException;
import java.util.List;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import com.jck.bdd.TestJDBC;

/**
 * Servlet implementation class GestionTestJDBC
 */
@WebServlet( "/GestionTestJDBC" )
public class GestionTestJDBC extends HttpServlet {
    private static final long  serialVersionUID = 1L;
    public static final String ATT_MESSAGES     = "messages";
    public static final String VUE              = "/WEB-INF/test__jdbc.jsp";

    /**
     * @see HttpServlet#HttpServlet()
     */
    public GestionTestJDBC() {
        super();
        // TODO Auto-generated constructor stub
    }

    /**
     * @see HttpServlet#doGet(HttpServletRequest request, HttpServletResponse
     *      response)
     */
    protected void doGet( HttpServletRequest request, HttpServletResponse response ) throws ServletException,
            IOException {
        TestJDBC test = new TestJDBC();
        List<String> messages = test.executerTests( request );

        request.setAttribute( ATT_MESSAGES, messages );

        this.getServletContext().getRequestDispatcher( VUE ).forward( request, response );
    }

    /**
     * @see HttpServlet#doPost(HttpServletRequest request, HttpServletResponse
     *      response)
     */
    protected void doPost( HttpServletRequest request, HttpServletResponse response ) throws ServletException,
            IOException {
        // TODO Auto-generated method stub
    }

}
