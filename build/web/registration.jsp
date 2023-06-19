<%-- 
    Document   : registration
    Created on : 20-Apr-2023, 10:58:14 pm
    Author     : KIRAN
--%>

<%@page contentType="text/html" pageEncoding="UTF-8"%>
<!DOCTYPE html>
<html>
    <head>
        <meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
        <title>Sign up</title>
        <link rel="icon" type="image/x-icon" href="pics/favicon.ico">

        <link rel="stylesheet" href="https://cdn.jsdelivr.net/npm/bootstrap@4.6.2/dist/css/bootstrap.min.css" integrity="sha384-xOolHFLEh07PJGoPkLv1IbcEPTNtaed2xpHsD9ESMhqIYd0nLMwNLD69Npy4HI+N" crossorigin="anonymous">
        <link href="css/style.css" rel="stylesheet" type="text/css">
    </head>
    <body>
        <!--Navigation--> 
        <%@include file="navbar.jsp" %>
        <!---------------------------->

        <main class=" background-clip2 p-5">
            <div class="container">
                <div class="row">
                    <div class="col-md-6 offset-md-3">
                        <div class="card">
                            <div class="card-header ">
                                <h4 class="text-center">Register here !</h4>
                            </div>
                            <div class="card-body">
                                <form action="RegisterServlet" id="regForm" method="post">

                                    <div class="form-group">
                                        <label for="user">User Name</label>
                                        <input type="text" name="user_name" class="form-control" id="user_name" aria-describedby="emailHelp" placeholder="Enter Name">
                                    </div>

                                    <div class="form-group">
                                        <label for="exampleInputEmail1">Email address</label>
                                        <input type="email" name="user_email" class="form-control" id="exampleInputEmail1" aria-describedby="emailHelp" placeholder="Enter email">
                                        <small id="emailHelp" class="form-text text-muted">We'll never share your email with anyone else.</small>
                                    </div>

                                    <div class="form-group">
                                        <label for="exampleInputPassword1">Password</label>
                                        <input type="password" name="user_password" class="form-control" id="exampleInputPassword1" placeholder="Password">
                                    </div>

                                    <div class="form-group">
                                        <label for="gender">Select Gender : &nbsp;</label>

                                        <input type="radio" name="gender" value="male" > Male
                                        <input type="radio" name="gender" value="female"> Female
                                    </div>


                                    <div class="form-group">
                                        <textarea class="form-control" name="about" rows="5" type="text" placeholder="enter something about yourself"></textarea>
                                    </div>


                                    <div class="form-group form-check">
                                        <input type="checkbox" name="check" class="form-check-input" id="exampleCheck1">
                                        <label class="form-check-label" for="exampleCheck1">agree terms and conditions</label>
                                    </div>


                                    <div class="text-center"><button type="submit" class="btn ibtn">Sign up</button></div>
                                    <h3 id="msg"></h3>

                                </form>
                            </div>




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
