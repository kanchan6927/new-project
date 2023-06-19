

<%@page import="com.techblog.entities.Message"%>
<%@page contentType="text/html" pageEncoding="UTF-8"%>
<!DOCTYPE html>
<html>
    <head>
        <meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
        <title>Login</title>
        <link rel="icon" type="image/x-icon" href="pics/favicon.ico">

        <link rel="stylesheet" href="https://cdn.jsdelivr.net/npm/bootstrap@4.6.2/dist/css/bootstrap.min.css" integrity="sha384-xOolHFLEh07PJGoPkLv1IbcEPTNtaed2xpHsD9ESMhqIYd0nLMwNLD69Npy4HI+N" crossorigin="anonymous">
        <link href="css/style.css" rel="stylesheet" type="text/css">
    </head>
    <body>

        <!--Navigation bar--> 
        <%@include file="navbar.jsp" %>
        <!--------------------------------->

        <main class="d-flex background-clip2 ">
            <div class="container">
                <div class="row">    
                    <div class="col-md-4 offset-md-4">
                        <div class="card">
                            <div class="card-header ">
                                <h4 class="text-center">Login here !</h4>
                                
                            </div>

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



                            <div class="card-body">
                                <form action="loginServlet">
                                    <div class="form-group">
                                        <label for="exampleInputEmail1">Email address</label>
                                        <input type="email" name="email_id" required class="form-control" id="exampleInputEmail1" aria-describedby="emailHelp" placeholder="Enter email">
                                        <small id="emailHelp" class="form-text text-muted">We'll never share your email with anyone else.</small>
                                    </div>
                                    <div class="form-group">
                                        <label for="exampleInputPassword1">Password</label>
                                        <input type="password" required name="pass" class="form-control" id="exampleInputPassword1" placeholder="Password">
                                    </div>

                                    <div class="text-center"><button type="submit" class="btn ibtn">Log in</button></div>
                                    <p class="mt-2 text-center">Don't have an account ? <a href="registration.jsp">Create one !</a> </p>
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
