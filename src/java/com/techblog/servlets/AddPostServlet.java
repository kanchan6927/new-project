/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/JSP_Servlet/Servlet.java to edit this template
 */
package com.techblog.servlets;

import com.techblog.dao.PostDao;
import com.techblog.entities.Post;
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

@MultipartConfig
public class AddPostServlet extends HttpServlet {

    protected void processRequest(HttpServletRequest request, HttpServletResponse response)
            throws ServletException, IOException {
        response.setContentType("text/html;charset=UTF-8");
        try (PrintWriter out = response.getWriter()) {

            int selectCid = Integer.parseInt(request.getParameter("selectCid"));
            String ptitle = request.getParameter("ptitle");
            String pcontent = request.getParameter("pcontent");
            String pcode = request.getParameter("pcode");

            Part part = request.getPart("ppic");
            String imageName = part.getSubmittedFileName();

            // getting current user Id
            HttpSession session = request.getSession();
            RegUser reguser = (RegUser) session.getAttribute("CurrentUser");

            reguser.getId();

            // create post class object
            Post post = new Post(ptitle, pcontent, pcode, imageName, null, selectCid, reguser.getId());

            PostDao postdao = new PostDao(ConnectionProvider.getConnection());
            if (postdao.uploadPost(post)) {

                String filePath = request.getRealPath("/") + "blog_pics" + File.separator + imageName;
                ProfileHelper.saveFile(part.getInputStream(), filePath);
                out.println("done");
            } else {

                out.println("something went wrong");
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
