/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/JSP_Servlet/Servlet.java to edit this template
 */
package com.techblog.servlets;

import com.techblog.dao.UserDao;
import com.techblog.entities.Message;
import com.techblog.entities.RegUser;
import com.techblog.helpers.ConnectionProvider;
import com.techblog.helpers.ProfileHelper;
import java.io.File;
import java.io.IOException;
import java.io.PrintWriter;
import javax.servlet.ServletException;
import javax.servlet.annotation.MultipartConfig;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;
import javax.servlet.http.Part;

/**
 *
 * @author KIRAN
 */
@MultipartConfig
public class EditServlet extends HttpServlet {

    protected void processRequest(HttpServletRequest request, HttpServletResponse response)
            throws ServletException, IOException {
        response.setContentType("text/html;charset=UTF-8");
        try (PrintWriter out = response.getWriter()) {

            String name = request.getParameter("user_name");
            String email = request.getParameter("user_email");
            String password = request.getParameter("user_password");
            String about = request.getParameter("user_about");

            Part part = request.getPart("profile_image");
            String imageName = part.getSubmittedFileName();

            // get user from the session
            HttpSession session = request.getSession();
            RegUser reguser = (RegUser) session.getAttribute("CurrentUser");

            reguser.setName(name);
            reguser.setEmail(email);
            reguser.setPassword(password);
            reguser.setAbout(about);
            String oldFile = reguser.getProfile();
            reguser.setProfile(imageName);

            // update to database
            UserDao dao = new UserDao(ConnectionProvider.getConnection());
            boolean ans = dao.updateUser(reguser);
            if (ans) {

//                out.println("updated");
                String path = request.getRealPath("/") + "pics" + File.separator + reguser.getProfile();

                String oldFilepath = request.getRealPath("/") + "pics" + File.separator + oldFile;

                if(!oldFile.equals("default.png")){
                 ProfileHelper.deleteFile(oldFilepath);
                }
               
                if (ProfileHelper.saveFile(part.getInputStream(), path)) {

                    out.println("Profile picture updated...");
                    Message msg = new Message("Profile updated Successfully !", "success", "alert-success");
                    session.setAttribute("msg", msg);

                } else {

//                    out.println("Profile not saved .. !");
                    Message msg = new Message("Profile not updated !", "error", "alert-danger");
                    session.setAttribute("msg", msg);
                }

            } else {

//                out.print("error occured");
                Message msg = new Message("Profile not updated !", "error", "alert-danger");
                session.setAttribute("msg", msg);

            }
            response.sendRedirect("profile.jsp");
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
