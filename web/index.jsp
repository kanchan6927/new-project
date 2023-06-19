

<%@page import="com.techblog.helpers.ConnectionProvider"%>
<%@page contentType="text/html" pageEncoding="UTF-8"%>
<%@page import="java.sql.*" %>
<!DOCTYPE html>
<html>
    <head>
        <meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
        <title>ToDo</title>
        <link rel="icon" type="image/x-icon" href="pics/favicon.ico">

        <link rel="stylesheet" href="https://cdn.jsdelivr.net/npm/bootstrap@4.6.2/dist/css/bootstrap.min.css" integrity="sha384-xOolHFLEh07PJGoPkLv1IbcEPTNtaed2xpHsD9ESMhqIYd0nLMwNLD69Npy4HI+N" crossorigin="anonymous">
        <link href="css/style.css" rel="stylesheet" type="text/css">
    </head>
    <body>

        <!--Navigation bar--> 
        <%@include file="navbar.jsp" %>
        <!--------------------------------->

        <div class="container-fluid m-0 p-0 index-flex">
            <div class="">
                <div class="container text-center">

                    <div><img src="pics/logo.png" width="50"></div> 
                    <div><a class="slowgun">Talk openly ,develop openly </a></div>
                    <p class="">Welcome to ToDo</p>


                    <a href="login.jsp"class="btn ibtn">Login</a>
                    <a href="registration.jsp"class="btn ibtn">Sign up</a>



                </div>
            </div>
        </div>


    </body>

    <script src="https://code.jquery.com/jquery-3.6.4.min.js" integrity="sha256-oP6HI9z1XaZNBrJURtCoUT5SUnxFr8s3BzRl+cbzUq8=" crossorigin="anonymous"></script>
    <script src="https://cdn.jsdelivr.net/npm/popper.js@1.12.9/dist/umd/popper.min.js" integrity="sha384-ApNbgh9B+Y1QKtv3Rn7W3mgPxhU9K/ScQsAP7hUibX39j7fakFPskvXusvfa0b4Q" crossorigin="anonymous"></script>
    <script src="https://cdn.jsdelivr.net/npm/bootstrap@4.0.0/dist/js/bootstrap.min.js" integrity="sha384-JZR6Spejh4U02d8jOt6vLEHfe/JQGiRRSQQxSfFWpi1MquVdAyjUar5+76PVCmYl" crossorigin="anonymous"></script>
    <script src="js/index.js" type="text/javascript"></script>



</html>
