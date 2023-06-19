

<%@page contentType="text/html" pageEncoding="UTF-8"%>
<%@page isErrorPage="true" %>
<!DOCTYPE html>
<html>
    <head>
        <meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
        <title>Something Went Wrong !</title>
        <link rel="stylesheet" href="https://cdn.jsdelivr.net/npm/bootstrap@4.6.2/dist/css/bootstrap.min.css" integrity="sha384-xOolHFLEh07PJGoPkLv1IbcEPTNtaed2xpHsD9ESMhqIYd0nLMwNLD69Npy4HI+N" crossorigin="anonymous">

    </head>
    <body>

        <div class="container ">

            <div class="col-md-4 offset-md-4">

                <img src="imgs/computer.png" class="img-fluid">


            </div>

            <div class=text-center">

                <h3 >Sorry ! something went wrong </h3>
                <%= exception%>
                <h4 "><a href="index.jsp" >back to Home </a></h4>
         
            </div>
        </div>


    </body>
</html>
