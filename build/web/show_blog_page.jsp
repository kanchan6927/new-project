

<%@page import="com.techblog.entities.likeStatus"%>
<%@page import="com.techblog.dao.LikeDao"%>
<%@page import="java.text.DateFormat"%>
<%@page import="com.techblog.dao.UserDao"%>
<%@page import="com.techblog.entities.Post"%>
<%@page import="com.techblog.dao.PostDao"%>
<%@page import="com.techblog.helpers.ConnectionProvider"%>
<%@page import="com.techblog.entities.RegUser"%>

<%@page contentType="text/html" pageEncoding="UTF-8"%>
<%@page errorPage="error.jsp" %> 

<%

    RegUser reguser = (RegUser) session.getAttribute("CurrentUser");
    if (reguser == null) {

        response.sendRedirect("login.jsp");
    }

%>

<%    int postId = Integer.parseInt(request.getParameter("post_id"));

    PostDao postdao = new PostDao(ConnectionProvider.getConnection());
    Post post = postdao.getPostByPostId(postId);

%>

<%--<%= postdao.getPostByUserId(reguser.getId()).getPtitle() %>--%>



<!DOCTYPE html>
<html>
    <head>
        <meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
        <title><%=post.getPtitle()%></title>
        <link rel="stylesheet" href="https://cdn.jsdelivr.net/npm/bootstrap@4.6.2/dist/css/bootstrap.min.css" integrity="sha384-xOolHFLEh07PJGoPkLv1IbcEPTNtaed2xpHsD9ESMhqIYd0nLMwNLD69Npy4HI+N" crossorigin="anonymous">
        <link rel="stylesheet" href="https://cdnjs.cloudflare.com/ajax/libs/font-awesome/4.7.0/css/font-awesome.min.css">
        <link href="css/style.css" rel="stylesheet" type="text/css">
    </head>
    <body>
<!--        <h1>Hello <%=postId%>!</h1>-->


        <main>

            <div class="container">
                <div class="row">
                    <div class="col-md-6 offset-md-3">
                        <div class="card">
                            <div class="card-header">
                                <b><%=post.getPtitle()%></b>
                            </div>
                            <div class="card-body">
                                <p><%=post.getPcontent()%></p>
                                <hr>
                                <p><code><%=post.getPcode()%></code></p>
                                <hr>
                                <img src="blog_pics/<%=post.GetPpic()%>" class="img-fluid">
                                <div class="row">
                                    <div class="col-md-6">

                                        <%
                                            UserDao userdao = new UserDao(ConnectionProvider.getConnection());
                                            userdao.getUserByUserId(post.getUserId()).getName();
                                        %>

                                        <p class="mt-3"> posted by: <%=userdao.getUserByUserId(post.getUserId()).getName()%> </p>
                                    </div>
                                    <div class="col-md-6"> 
                                        <p class="mt-3"><%= post.getPdate().toLocaleString()%></p>
                                        <!--<p class="mt-3"><%=DateFormat.getDateTimeInstance().format(post.getPdate())%></p>-->
                                    </div>
                                </div>

                            </div>
                            <div class="card-footer">

                                <%
                                    LikeDao likedao = new LikeDao(ConnectionProvider.getConnection());
                                    likeStatus ls = likedao.fetchthumClass(post.getPid(), reguser.getId());

                                %>

                                <a href="#" onclick="doLike(<%= post.getPid()%>,<%= reguser.getId()%>,<%=likedao.getIsLikedStatus(post.getPid(), reguser.getId())%>)"  class="btn btn-outline-primary btn-sm" ><i id="thumb-<%= post.getPid()%>-<%= reguser.getId()%>" class="fa <%= (ls != null) ? ls.getThumClass() : "fa-thumbs-o-up"%>"></i><span class="like-counter" id="like-button-<%=post.getPid()%>-<%=reguser.getId()%>" ><%=likedao.countLikeOnPost(post.getPid())%></span></a>

                                <!--<h1 id="likeStatusdiv-<%=post.getPid()%>-<%=reguser.getId()%>"><%=likedao.getIsLikedStatus(post.getPid(), reguser.getId())%></h1>-->


                                <a href="#" class="btn btn-outline-primary btn-sm"><i class="fa fa-commenting-o"><span>10</span></i></a>

                            </div>


                        </div>
                    </div>
                </div>
        </main>



    </body>
    <script src="https://code.jquery.com/jquery-3.6.4.min.js" integrity="sha256-oP6HI9z1XaZNBrJURtCoUT5SUnxFr8s3BzRl+cbzUq8=" crossorigin="anonymous"></script>

    <script src="https://cdn.jsdelivr.net/npm/popper.js@1.12.9/dist/umd/popper.min.js" integrity="sha384-ApNbgh9B+Y1QKtv3Rn7W3mgPxhU9K/ScQsAP7hUibX39j7fakFPskvXusvfa0b4Q" crossorigin="anonymous"></script>
    <script src="https://cdn.jsdelivr.net/npm/bootstrap@4.0.0/dist/js/bootstrap.min.js" integrity="sha384-JZR6Spejh4U02d8jOt6vLEHfe/JQGiRRSQQxSfFWpi1MquVdAyjUar5+76PVCmYl" crossorigin="anonymous"></script>
    <script src="js/index.js" type="text/javascript"></script>



</html>
