/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/JSP_Servlet/Servlet.java to edit this template
 */
package com.techblog.servlets;

import org.json.simple.JSONObject;

import com.techblog.dao.LikeDao;
import com.techblog.entities.likeStatus;
import com.techblog.helpers.ConnectionProvider;
import java.io.IOException;
import java.io.PrintWriter;
import javax.servlet.ServletException;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

public class LikeServlet2 extends HttpServlet {

    protected void processRequest(HttpServletRequest request, HttpServletResponse response)
            throws ServletException, IOException {
//        response.setContentType("text/html;charset=UTF-8");
        response.setContentType("application/json");
        try (PrintWriter out = response.getWriter()) {

            int pid = Integer.parseInt(request.getParameter("pid"));
            int userId = Integer.parseInt(request.getParameter("userId"));
            int is_liked = Integer.parseInt(request.getParameter("is_liked"));

            LikeDao likedao = new LikeDao(ConnectionProvider.getConnection());

            JSONObject responseData = new JSONObject();

            if (is_liked == 0) {
                is_liked = 1;
                boolean f = likedao.insertLike(pid, userId, is_liked);

                String thumClass;
                if (f) {
                    int updatedLikeCount = likedao.countLikeOnPost(pid);

                    int updatedIsLikedStatus = likedao.getIsLikedStatus(pid, userId);

                    likeStatus ls = likedao.fetchthumClass(pid, userId);

                    if (ls != null) {
                        thumClass = ls.getThumClass();
                    } else {
                        return;
                    }
                    responseData.put("success", true);
                    responseData.put("countLike", updatedLikeCount);
                    responseData.put("isLikedStatus", updatedIsLikedStatus);
                    responseData.put("thumClass", thumClass);

                    out.println(responseData.toString());

                } else {
                    responseData.put("success", false);
                }

            } else {
                if (is_liked == 1) {

                    boolean ff = likedao.deleteLike(pid, userId, is_liked);

                    if (ff) {
                        int updatedIsLikedStatus = likedao.getIsLikedStatus(pid, userId);
                        int updatedLikeCount = likedao.countLikeOnPost(pid);
                        responseData.put("success", true);
                        responseData.put("isLikedStatus", updatedIsLikedStatus);
                        responseData.put("countLike", updatedLikeCount);

//                        out.println(responseData.toJSONString());
                    } else {
                        responseData.put("success", false);
                    }
                }

                out.println(responseData.toJSONString());

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
