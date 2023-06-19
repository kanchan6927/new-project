
<%@page import="com.techblog.dao.LikeDao"%>
<%@page import="com.techblog.entities.likeStatus"%>
<%@page import="java.util.List"%>
<%@page import="com.techblog.entities.Post"%>
<%@page import="com.techblog.dao.UserDao"%>
<%@page import="java.util.ArrayList"%>
<%@page import="com.techblog.entities.Categories"%>
<%@page import="com.techblog.dao.PostDao"%>
<%@page import="com.techblog.helpers.ConnectionProvider"%>
<%@page import="com.techblog.entities.Message"%>
<%@page import="com.techblog.entities.RegUser" %>
<%@page errorPage="error.jsp" %>
<%

    RegUser reguser = (RegUser) session.getAttribute("CurrentUser");
    if (reguser == null) {

        response.sendRedirect("login.jsp");
    }

%>





<%@page contentType="text/html" pageEncoding="UTF-8"%>
<!DOCTYPE html>
<html>
    <head>
        <meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
        <title>ToDo</title>
        <link rel="icon" type="image/x-icon" href="pics/favicon.ico">
        <link rel="stylesheet" href="https://cdn.jsdelivr.net/npm/bootstrap@4.6.2/dist/css/bootstrap.min.css" integrity="sha384-xOolHFLEh07PJGoPkLv1IbcEPTNtaed2xpHsD9ESMhqIYd0nLMwNLD69Npy4HI+N" crossorigin="anonymous">
        <link rel="stylesheet" href="https://cdnjs.cloudflare.com/ajax/libs/font-awesome/4.7.0/css/font-awesome.min.css">
        <link href="css/style.css" rel="stylesheet" type="text/css">

    </head>
    <body>



        <nav class="navbar navbar-expand-lg navbar-dark bg-black">
            <a class="navbar-brand" href="index.jsp">ToDo</a>
            <button class="navbar-toggler" type="button" data-toggle="collapse" data-target="#navbarSupportedContent" aria-controls="navbarSupportedContent" aria-expanded="false" aria-label="Toggle navigation">
                <span class="navbar-toggler-icon"></span>
            </button>

            <div class="collapse navbar-collapse" id="navbarSupportedContent">
                <ul class="navbar-nav mr-auto">
                    <li class="nav-item active">
                        <a class="nav-link" href="#">Home <span class="sr-only">(current)</span></a>
                    </li>



                    <li class="nav-item">
                        <a class="nav-link" href="#" data-toggle="modal" data-target="#addPostModal">Do post</a>
                    </li>
                    <li class="nav-item">
                        <a class="nav-link" href="#" data-toggle="modal" data-target="#notificationModal" ><i class="fa fa-bell"></i></a>

                    </li>


                </ul>

                <ul class="navbar-nav mr-right">
                    <li class="nav-item">
                        <a class="nav-link " href="#" data-toggle="modal" data-target="#profileModal"> <span><i class="fa fa-user" ></i></span>  <%= reguser.getName()%></a>

                    </li>

                    <li class="nav-item">
                        <a class="nav-link " href="#" data-toggle="modal" data-target="#MyPostedProfilesModal">My-Posts</a>
                    </li>


                    <li class="nav-item">
                        <a class="nav-link " href="LogoutServlet">LogOut</a>
                    </li>
                </ul>

            </div>
        </nav>  

        <div class="container">

            <%
                Message m = (Message) session.getAttribute("msg");
                if (m != null) {
            %>

            <p class="alert alert-dismissible <%=m.getCssclass()%>" role="alert"> <%= m.getContent()%> 
                <button type="button" class="close" data-dismiss="alert" aria-label="Close">
                    <span aria-hidden="true">&times;</span>
                </button>
            </p>

            <%
                    session.removeAttribute("msg");
                }
            %>

            <!--displaying details of current user on profile page--> 

            <!--                     <h1>   <%=reguser.getName()%>
                                    <br>
            <%=reguser.getEmail()%>
            <br>
            <%=reguser.getDateTime()%>
            <br>
            <%=reguser.getGender()%>
            <br>

            <%= reguser.getProfile()%> </h1>-->

            <!-- -------------------------------------------- -->

        </div>

        <!-------------Main body of the page---------------->

        <div class="container">
            <div class="row mt-5">
                <!--first part (left part)  -->
                <div class="col-md-4">
                    <!--showing categories this side-->
                    <div class="list-group">
                        <a href="#" onclick="getAllPostonLoad(0, this)" class="list-group-item list-group-item-action c-link active">
                            All posts
                        </a>


                        <%
                            PostDao postdaoo = new PostDao(ConnectionProvider.getConnection());
                            ArrayList<Categories> list1 = postdaoo.getAllCategories();
                            for (Categories cc : list1) {

                        %>
                        <a href="#" onclick="getAllPostonLoad(<%=cc.getCid()%>, this)" class="list-group-item list-group-item-action c-link"><%=cc.getName()%></a>
                        <%
                            }

                        %>

                    </div>



                </div>

                <!--second part (right part)-->
                <div class="col-md-8">
                    <!--showing all post this side-->

                    <div class="container text-center" id="loader"> 
                        <i class="fa fa-refresh  fa-spin"></i>
                        <h5 class="mt-3">loading...</h5>

                    </div>

                    <div class="container-fluid" id="postContainer" >

                    </div>

                </div>
            </div>
        </div>


        <!---------------------main body close---------------------->

        <!--logged in user details------------------------->

        <!-- Modal -->
        <div class="modal fade" id="profileModal" tabindex="-1" role="dialog" aria-labelledby="exampleModalLabel" aria-hidden="true">
            <div class="modal-dialog" role="document">
                <div class="modal-content">
                    <div class="modal-header bg-black text-white">
                        <h5 class="modal-title" id="exampleModalLabel">Techblog </h5>
                        <button type="button" class="close" data-dismiss="modal" aria-label="Close">
                            <span aria-hidden="true">&times;</span>
                        </button>
                    </div>
                    <div class="modal-body">
                        <div class="container text-center">
                            <img src="pics/<%= reguser.getProfile()%> " class="img-fluid" style="max-width: 20% !important; height: auto;">
                            <h5 class="modal-title" id="exampleModalLabel"><%= reguser.getName()%></h5>
                            <div id="profile-details">
                                <table class="table mt-3">
                                    <tr>
                                        <th>ID: </th>
                                        <td><%= reguser.getId()%></td>
                                    </tr>
                                    <tr>
                                        <th>About: </th>
                                        <td><%= reguser.getAbout()%></td>
                                    </tr>
                                    <tr>
                                        <th>Email :</th>
                                        <td><%= reguser.getEmail()%></td>
                                    </tr>

                                    <tr>
                                        <th>Password </th>
                                        <td><%= reguser.getPassword()%></td>
                                    </tr>
                                </table>

                            </div>

                            <!--profile edit-->

                            <div id="profile-edit">

                                <form action="EditServlet" method="post" enctype="multipart/form-data">
                                    <p>You cannot change the user ID !</p>

                                    <table class="table">
                                        <tr>
                                            <td>Id : </td>
                                            <td><%= reguser.getId()%></td>
                                        </tr>
                                        <tr>
                                            <td>Name : </td>
                                            <td><input type="text" class="form-control" name="user_name" value="<%= reguser.getName()%>"> </td>
                                        </tr>
                                        <tr>
                                            <td>Email: </td>
                                            <td><input type="email" class="form-control" name="user_email" value="<%= reguser.getEmail()%>"> </td>
                                        </tr>
                                        <tr>
                                            <td>Password : </td>
                                            <td><input type="password" name="user_password" class="form-control" value="<%=reguser.getPassword()%>"> </td>
                                        </tr>

                                        <tr>
                                            <td>About : </td>
                                            <td><input type="text" class="form-control" name="user_about" value="<%= reguser.getAbout()%>"> </td>
                                        </tr>

                                        <tr>
                                            <td>Change profile  : </td>
                                            <td><input type="file"  name="profile_image" value=""> </td>
                                        </tr>


                                    </table>

                                    <button type="submit" class="btn btn-primary">save</button>

                                </form>


                            </div>


                        </div>
                    </div>
                    <div class="modal-footer">
                        <button type="button" class="btn btn-secondary" data-dismiss="modal">Close</button>
                        <button type="button" id="edit-profile-btn" class="btn btn-primary">Edit Profile</button>
                    </div>
                </div>
            </div>
        </div>


        <!--add post modal---------------->


        <!-- Modal -->
        <div class="modal fade" id="addPostModal" tabindex="-1" role="dialog" aria-labelledby="exampleModalLabel" aria-hidden="true">
            <div class="modal-dialog" role="document">
                <div class="modal-content">
                    <div class="modal-header">
                        <h5 class="modal-title" id="exampleModalLabel">Provide Post details !</h5>
                        <button type="button" class="close" data-dismiss="modal" aria-label="Close">
                            <span aria-hidden="true">&times;</span>
                        </button>
                    </div>
                    <div class="modal-body">

                        <form action="AddPostServlet" id="addPostForm" method="post">

                            <div class="form-group">
                                <select class="form-control" name="selectCid" >
                                    <%
                                        PostDao postdao = new PostDao(ConnectionProvider.getConnection());
                                        ArrayList<Categories> list = postdao.getAllCategories();
                                        for (Categories c : list) {
                                    %>

                                    <option value="<%=c.getCid()%>"><%= c.getName()%> </option>

                                    <%
                                        }
                                    %>

                                    <option selected="" disabled="">--select categories--</option>

                                </select>
                            </div>

                            <div class="form-group">
                                <input type="text" name="ptitle" class="form-control" placeholder="Enter post Title..">

                            </div>
                            <div class="form-group">
                                <textarea type="text" name="pcontent" class="form-control" placeholder="Enter post Content.."></textarea>

                            </div>
                            <div class="form-group">
                                <input type="text" name="pcode" class="form-control" placeholder="Enter post code (if any )..">

                            </div>
                            <div class="form-group">
                                <lable>Select image : </lable>
                                <input type="file" name="ppic">

                            </div>
                            <br>
                            <div class="container text-center">
                                <button class="btn btn-primary">post</button>
                            </div>

                        </form>



                    </div>
                    <div class="modal-footer">
                        <button type="button" class="btn btn-secondary" data-dismiss="modal">Close</button>
                        <button type="button" class="btn btn-primary">Save changes</button>
                    </div>
                </div>
            </div>
        </div>



        <!---------------------------------My-posted-Modal----------------------->

        <!-- Modal -->

        <div class="modal fade" id="MyPostedProfilesModal" tabindex="-1" role="dialog" aria-labelledby="exampleModalLabel" aria-hidden="true">
            <div class="modal-dialog" role="document">
                <div class="modal-content">
                    <div class="modal-header">
                        <h5 class="modal-title" id="exampleModalLabel">My posts</h5>
                        <button type="button" class="close" data-dismiss="modal" aria-label="Close">
                            <span aria-hidden="true">&times;</span>
                        </button>
                    </div>
                    <div class="modal-body">

                        <%
                            List<Post> postlist = null;
                            List<Post> postlist3 = postdao.getPostByUserId(reguser.getId());

                            if (postlist3.size() == 0) {
                                out.println("<h4 class='text-center'> No Posts yet !</h4>");

                            }

                            for (Post p : postlist3) {
                        %>

                        <div class="card mt-3">
                            <div class="card-header">
                                <b> <%=p.getPtitle()%></b>
                            </div>
                            <div class="card-body">
                                <p><%=p.getPcontent()%></p>
                                <hr>
                                <p><code> <%=p.getPcode()%></code></p>
                                <hr>
                                <img src="blog_pics/<%=p.GetPpic()%>" class="img-fluid">
                            </div>
                            <div class="card-footer">
                                <button onclick="deletePost(<%=reguser.getId()%>,<%=p.getPid()%>)" class="btn btn-primary btn-sm" id="deletebtn">delete post</button>
                                <a><%=p.getPid()%></a>   

                            </div>
                        </div>

                        <%
                            }
                        %>


                    </div>
                    <div class="modal-footer">
                        <button type="button" class="btn btn-secondary" data-dismiss="modal">Close</button>
                        <button type="button" class="btn btn-primary">Save changes</button>
                    </div>
                </div>
            </div>
        </div>



        <!-- --------------------------------------------------------------- -->

        <!----------------- Notification Modal--------------->



        <!-- Modal -->
        <div class="modal fade" id="notificationModal" tabindex="-1" role="dialog" aria-labelledby="exampleModalLabel" aria-hidden="true">
            <div class="modal-dialog" role="document">
                <div class="modal-content">
                    <div class="modal-header">
                        <h5 class="modal-title" id="exampleModalLabel">Modal title</h5>
                        <button type="button" class="close" data-dismiss="modal" aria-label="Close">
                            <span aria-hidden="true">&times;</span>
                        </button>
                    </div>
                    <div class="modal-body">
                        <div class="notification-container" ><div id="notification"> </div>
                            <img src=" " id="postPic" class="img-fluid">
                        </div>

                        <div id="notification-container"></div>
                        
                    </div>
                    <div class="modal-footer">
                        <button type="button" class="btn btn-secondary" data-dismiss="modal">Close</button>
                        <button type="button" class="btn btn-primary">Save changes</button>
                    </div>
                </div>
            </div>
        </div>



    </body>

    <script src="https://code.jquery.com/jquery-3.6.4.min.js" integrity="sha256-oP6HI9z1XaZNBrJURtCoUT5SUnxFr8s3BzRl+cbzUq8=" crossorigin="anonymous"></script>

    <script src="https://cdn.jsdelivr.net/npm/popper.js@1.12.9/dist/umd/popper.min.js" integrity="sha384-ApNbgh9B+Y1QKtv3Rn7W3mgPxhU9K/ScQsAP7hUibX39j7fakFPskvXusvfa0b4Q" crossorigin="anonymous"></script>
    <script src="https://cdn.jsdelivr.net/npm/bootstrap@4.0.0/dist/js/bootstrap.min.js" integrity="sha384-JZR6Spejh4U02d8jOt6vLEHfe/JQGiRRSQQxSfFWpi1MquVdAyjUar5+76PVCmYl" crossorigin="anonymous"></script>
    <script src="js/index.js" type="text/javascript"></script>



</html>
