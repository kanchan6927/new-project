<%--<%@page import="com.techblog.entities.Categories"%>--%>
<%@page import="java.util.ArrayList"%>
<%@page import="com.techblog.entities.likeStatus"%>
<%@page import="com.techblog.dao.UserDao"%>
<%@page import="com.techblog.entities.RegUser"%>
<%@page import="com.techblog.dao.LikeDao"%>
<%@page import="java.util.List"%>
<%@page import="com.techblog.entities.Post"%>
<%@page import="com.techblog.dao.PostDao"%>
<%@page import="com.techblog.helpers.ConnectionProvider"%>



<div class="row">



    <%
        RegUser reguser = (RegUser) session.getAttribute("CurrentUser");
        Thread.sleep(1000);

        PostDao postdao = new PostDao(ConnectionProvider.getConnection());
        int cid = Integer.parseInt(request.getParameter("cid"));
        List<Post> postlist = null;

        if (cid == 0) {
            postlist = postdao.getAllPosts();

        } else {
            postlist = postdao.getPostByCatId(cid);
        }

        if (postlist.size() == 0) {
            out.println("<h4 class='text-center'>No posts regarding !</h4>");
            return;
        }

        for (Post p : postlist) {

    %>




    <div class="col-md-6 mt-3">
        <div class="card">
            <div class="card-header">
                <div class="imgcontainer">
                    <%   UserDao userdao = new UserDao(ConnectionProvider.getConnection());
                        userdao.getUserByUserId(p.getUserId()).getName();
                    %>

                    <img src="pics/<%= userdao.getUserByUserId(p.getUserId()).getProfile()%>" class="img-fluid" style="border-radius: 50%;width:8%;">
                    <span class="ml-3"><%=userdao.getUserByUserId(p.getUserId()).getName()%></span>
                </div>


            </div>
            <div class="card-body">
                <img src="blog_pics/<%=p.GetPpic()%>" class="img-fluid">

                <p class="mt-4"><%=p.getPtitle()%></p>

                <p><%=p.getPcontent()%></p>

                <p><code> <%=p.getPcode()%></code></p>


            </div>
            <div class="card-footer">
                <%
                    LikeDao ld = new LikeDao(ConnectionProvider.getConnection());
                %>

                <%
                    likeStatus ls = ld.fetchthumClass(p.getPid(), reguser.getId());
                    List<likeStatus> l = ld.whichUsersPost(reguser.getId());

                    if (l.size() == 0) {
                        out.println("List is empty");

                    }

                    for (likeStatus lss : l) {

                %>

                <!--<p><%=lss.getUserId()%></p>-->

                <%
                    }
                %>

               

                <a href="#" onclick="doLike(<%= p.getPid()%>,<%= reguser.getId()%>,<%=ld.getIsLikedStatus(p.getPid(), reguser.getId())%>)" class="btn btn-outline-primary btn-sm" ><i id="thumb-<%= p.getPid()%>-<%= reguser.getId()%>" class="fa  <%= (ls != null) ? ls.getThumClass() : "fa-thumbs-o-up"%>"></i><span class="like-counter" id="like-button-<%=p.getPid()%>-<%=reguser.getId()%>" ><%=ld.countLikeOnPost(p.getPid())%></span></a>
                <a href="#" class="btn btn-outline-primary btn-sm"><i class="fa fa-commenting-o"><span>10</span></i></a>
                <a href="show_blog_page.jsp?post_id=<%=p.getPid()%>" class="btn btn-outline-primary btn-sm">Read More</a>


                <!--<h1 id="likeStatusdiv-<%=p.getPid()%>-<%=reguser.getId()%>"><%=ld.getIsLikedStatus(p.getPid(), reguser.getId())%></h1>-->

                <div><a href="#" onclick="showlikes(<%=p.getPid()%>)">liked by : </a></div>
                <div id="listContainer-<%=p.getPid()%>"></div>


            </div>
        </div>
    </div>

    <%
        }

    %>


</div>
