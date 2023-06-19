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

public class LikeServlet extends HttpServlet {

    protected void processRequest(HttpServletRequest request, HttpServletResponse response)
            throws ServletException, IOException {
        response.setContentType("application/json");
        try (PrintWriter out = response.getWriter()) {

            int pid = Integer.parseInt(request.getParameter("pid"));
            int userId = Integer.parseInt(request.getParameter("userId"));
            int is_liked = Integer.parseInt(request.getParameter("is_liked"));

            LikeDao likedao = new LikeDao(ConnectionProvider.getConnection());
            JSONObject responseData = new JSONObject();

            likeStatus userLiked = likedao.getIsLikedStatuss(pid, userId);

            if (userLiked == null) {

                boolean likeInserted = likedao.insertLike(pid, userId, is_liked);

                if (likeInserted) {

                    int updatedLikeCount = likedao.countLikeOnPost(pid);

                    int updatedIsLikedStatus = likedao.getIsLikedStatus(pid, userId);

                    String thumClass;

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

                    out.println("failed to insert like");

                }
            } else {
                if (userLiked != null) {

                    boolean likeRemoved = likedao.deleteLike(pid, userId, is_liked);
                    if (likeRemoved) {
                        
                        int updatedIsLikedStatuss = likedao.getIsLikedStatus(pid, userId);
                        int updatedLikeCountt = likedao.countLikeOnPost(pid);
                        likeStatus thumClass = likedao.fetchthumClass(pid, userId);
                        if (thumClass == null) {
                          
                            responseData.put("success", true);
                            responseData.put("isLikedStatus", updatedIsLikedStatuss);
                            responseData.put("countLike", updatedLikeCountt);
                            

                            out.println(responseData.toJSONString());
                        }

                    }
                    else {

                        out.println("faild to remove like");

                    }
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
