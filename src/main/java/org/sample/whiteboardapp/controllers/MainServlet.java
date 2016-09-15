/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package org.sample.whiteboardapp.controllers;

import java.io.IOException;
import java.io.PrintWriter;
import java.util.ArrayList;
import java.util.Collection;
import java.util.HashMap;
import javax.servlet.RequestDispatcher;
import javax.servlet.ServletContext;
import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

/**
 *
 * @author caev0
 */
@WebServlet(name = "MainServlet", urlPatterns = {"/", "/mainController"})
public class MainServlet extends HttpServlet {

    /**
     * Processes requests for both HTTP <code>GET</code> and <code>POST</code>
     * methods.
     *
     * @param request servlet request
     * @param response servlet response
     * @throws ServletException if a servlet-specific error occurs
     * @throws IOException if an I/O error occurs
     */
    protected void processRequest(HttpServletRequest request, HttpServletResponse response)
            throws ServletException, IOException {
        HttpSession session = request.getSession(true);
        Object user = session.getAttribute("user");
        if (user==null) {
            String userName = request.getParameter("userName");
            if (userName != null) {
                session = request.getSession();
                ServletContext scontx = getServletContext();
                ArrayList<String> usuarios = (ArrayList<String>)scontx.getAttribute("users");
                System.out.println(scontx.getAttribute("users"));
                if(noExisteUsuario(userName, usuarios))
                {
                    usuarios.add(userName);
                    scontx.setAttribute("users", usuarios);
                }
                System.out.println(scontx.getAttribute("users"));
                String[] users = usuarios.toArray(new String[0]);
                for (int i = 0; i < users.length; i++) {
                    System.out.println(users[i]);
                }
                session.setAttribute("user", userName);
                request.setAttribute("user", session.getAttribute("user"));
                RequestDispatcher view = request.getRequestDispatcher("mainPage.jsp");
                view.forward(request, response);
                user = session.getAttribute("user");
            } else {
                ServletContext scontx = getServletContext();
                ArrayList<String> users = ((ArrayList<String>)scontx.getAttribute("users"));
                if(users==null)
                {
                    users = new ArrayList<String>();
                    scontx.setAttribute("users", users);
                }
                response.sendRedirect("index.jsp");
            }
            
        } else {
            request.setAttribute("user", user);
            RequestDispatcher view = request.getRequestDispatcher("mainPage.jsp");
            view.forward(request, response);
        }
    }

    // <editor-fold defaultstate="collapsed" desc="HttpServlet methods. Click on the + sign on the left to edit the code.">
    /**
     * Handles the HTTP <code>GET</code> method.
     *
     * @param request servlet request
     * @param response servlet response
     * @throws ServletException if a servlet-specific error occurs
     * @throws IOException if an I/O error occurs
     */
    @Override
    protected void doGet(HttpServletRequest request, HttpServletResponse response)
            throws ServletException, IOException {
        processRequest(request, response);
    }

    /**
     * Handles the HTTP <code>POST</code> method.
     *
     * @param request servlet request
     * @param response servlet response
     * @throws ServletException if a servlet-specific error occurs
     * @throws IOException if an I/O error occurs
     */
    @Override
    protected void doPost(HttpServletRequest request, HttpServletResponse response)
            throws ServletException, IOException {
        HttpSession session = request.getSession(false);
        session.removeAttribute("user");
        response.sendRedirect("index.jsp");
    }

    /**
     * Returns a short description of the servlet.
     *
     * @return a String containing servlet description
     */
    @Override
    public String getServletInfo() {
        return "Short description";
    }// </editor-fold>

    private boolean noExisteUsuario(String userName, ArrayList<String> usuarios) 
    {
        boolean response = false;
        for (int i = 0; i < usuarios.size() && !false; i++) 
        {
            if(usuarios.get(i).equals(userName))
            {
                response=true;
            }
        }
        return response;
    }

}
