/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/JSP_Servlet/Servlet.java to edit this template
 */
package com.techblog.servlets;

import com.techblog.dao.UserDao;
import com.techblog.entities.RegUser;
import com.techblog.helpers.ConnectionProvider;
import java.io.IOException;
import java.io.PrintWriter;
import javax.servlet.ServletException;
import javax.servlet.annotation.MultipartConfig;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

/**
 *
 * @author KIRAN
 */

@MultipartConfig

public class RegisterServlet extends HttpServlet {

    protected void processRequest(HttpServletRequest request, HttpServletResponse response)
            throws ServletException, IOException {
        response.setContentType("text/html;charset=UTF-8");
        try (PrintWriter out = response.getWriter()) {

            // fetch all form data 
            String check = request.getParameter("check");
            if (check == null) {

                out.println("please accept the check box");

            } else {

                String name = request.getParameter("user_name");
                String password = request.getParameter("user_password");
                String email = request.getParameter("user_email");
                String gender = request.getParameter("gender");
                String about = request.getParameter("about");

                // Create RegUser Object and set all data to that object
                RegUser reguser = new RegUser(name, password, email, gender, about);

                // create UserDao class's object
                UserDao dao = new UserDao(ConnectionProvider.getConnection());
//                dao.saveUser(reguser);
                if (dao.saveUser(reguser)) {
                    out.println(" done");
                    

                } else {
                    out.println("Something went wrong");

                }

            }

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
        processRequest(request, response);
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

}
