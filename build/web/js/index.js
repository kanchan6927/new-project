/* 
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/JSP_Servlet/JavaScript.js to edit this template
 */


/* 
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/JSP_Servlet/JavaScript.js to edit this template
 */

var socket = new WebSocket('ws://localhost:8080/TechBlog/websocket');

socket.onopen = function (event) {

    console.log("connection is created");


};



socket.onmessage = function (event) {



    var notificationElement = document.getElementById('notification');
    var notificationElement2 = document.getElementById('notification-container');
    var notificationMessage = event.data;

//    const childElm = document.createElement('p');
//     notificationElement2.appendChild(childElm);
//    childElm.innerText = notificationMessage;




    notificationElement.innerHTML = notificationMessage;
    console.log(event.data);




};

function visitors() {

    $.ajax({
        url: ' WebsocketServlet',
        type: 'post',
        dataType: 'json',
        success: function (data) {
//            console.log(data);
//            var users = data.users;
//            var NotHtml = '';
//            if (users.length == null) {
//
//                console.log("no list");
//            } else {
//
//                for (var i = 0; i < users.length; i++) {
//                    var user = users[i];
//                    var Name = user.whoLiked;
//                    var PPic = user.PostPics;
//                    console.log(user);
//                    console.log("console" + Name);
//                    console.log("console" + PPic);
//                    NotHtml += '<div>';
//                    NotHtml += '<img src="blog_pics/' + PPic + '" class="img-fluid" style="border-radius: 50%; width: 8%;">';
//                    NotHtml += '<a class="NList">' + Name + '</a>';
//                    NotHtml += '<br>';
//                    NotHtml += '</div>';
//
//                }
//                 $('#notification-container').html(NotHtml);
//            }



            if (data.hasOwnProperty('postPic')) {
                console.log("yes i am here");
                var postPicc = document.getElementById('postPic');
                postPicc.src = "blog_pics/" + data.postPic;

            } else {
                console.log("i am not there");

            }
        },
        error: function (jqXHR, textStatus, errorThrown) {
            console.log("error " + errorThrown);
        }

    });

}

visitors();


// registration form --------------------

$(document).ready(function () {

    $("#regForm").on('submit', function (event) {
        event.preventDefault();
//               var f = $(this).serialize();
        var f = new FormData(this); //if u have imges etc in your form in that case u have to use Formdata() object 
        console.log(f);
        $.ajax({
            url: "RegisterServlet",
            data: f,
            type: 'post',
            success: function (data, textStatus, jqXHR) {
                console.log(data);
                if (data.trim() === "done") {
//                    $("#msg").html("Registration Done Successfully !..");
                    window.alert("Registration done Successfully !");
                    var allInputs = document.querySelectorAll('input');
                    allInputs.forEach(singleInput => singleInput.value = '');
                    const myTimeout = setTimeout(hidemsg, 2000);
                    function hidemsg() {
                        $("#msg").hide();
                    }

                } else {
//                    $("#msg").html("something went wrong...");
//                    $("#msg").css("color", "red");
                    window.alert("error occured ! do check terms and conditions.");
                }

            },
            error: function (jqXHR, textStatus, errorThrown) {
//                console.log(data);
                console.log("error occured");
                alert.window.alert("do accept term and conditions");
            },
            processData: false,
            contentType: false

        });
    });
});
//--------------------------------------------------


//profile page edit form-------------------------

$(document).ready(function () {
    let editStatus = false;
    $("#edit-profile-btn").click(function () {
        if (editStatus == false) {
            $('#profile-details').hide();
            $('#profile-edit').show();
            editStatus = true;
            $(this).text("Back");
        } else {
            $('#profile-edit').hide();
            $('#profile-details').show();
            editStatus = false;
            $(this).text("Edit");
        }




    });
});
//--------------------------------------------



//profile page add post form-------------------------

$(document).ready(function () {

    $("#addPostForm").on("submit", function (event) {
        event.preventDefault();
        let form = new FormData(this);
        $.ajax({

            url: "AddPostServlet",
            data: form,
            type: "post",
            success: function (data, textStatus, jqXHR) {
                console.log(data);
                window.alert("Post Saved successfully !");
            },
            error: function (jqXHR, textStatus, errorThrown) {
                console.log(data);
                window.alert("something went wrong try again !");
            },
            processData: false,
            contentType: false
        });
    });
});
// ----------------------------------------

// ------------------Loading all posts using ajax---------------------


function getAllPostonLoad(catId, activelink) {
    $("#loader").show();
    $("#postContainer").hide();
    $(".c-link").removeClass('active');
    $.ajax({

        url: "LoadPosts.jsp",
        data: {cid: catId},
        method: "get",
        success: function (data, textStatus, jqXHR) {
            $("#loader").hide();
            $("#postContainer").show();
            $("#postContainer").html(data);
            $(activelink).addClass('active');
//            console.log(data);


        }
    });
}


$(document).ready(function () {

    let allpostRef = $('.c-link')[0];
    getAllPostonLoad(0, allpostRef);
});
//----------------------------------------------------------------

function doLike(pid, userId, is_liked) {

    console.log(pid + " " + userId + " ");
    const d = {
        pid: pid,
        userId: userId,
        is_liked: is_liked

    };
    $.ajax({
        url: "LikeServlet",
        data: d,
        type: 'post',
        dataType: 'json',
        success: function (data, textStatus, jqXHR) {
            console.log(data);
            var likeCountElement = document.getElementById('like-button-' + pid + "-" + userId);
            var likeStatusdiv = document.getElementById('likeStatusdiv-' + pid + "-" + userId);
            if (data.hasOwnProperty('countLike') && data.hasOwnProperty('isLikedStatus')) {


                let updatedIsLiked = parseInt(data.isLikedStatus);
                let updatedLikeCount = parseInt(data.countLike);
                let thumbClass = data.thumClass;
                var thumb = document.getElementById('thumb-' + pid + "-" + userId);
//                thumb.classList.add(thumbClass);

                $(thumb).toggleClass(thumbClass);
//                likeStatusdiv.innerHTML = updatedIsLiked;

                likeCountElement.innerHTML = updatedLikeCount; // Update the HTML content with the updated like count



            } else {
                console.log('Received invalid response format');
            }

        },
        error: function (jqXHR, textStatus, errorThrown) {
            console.log("Error: " + textStatus + " - " + errorThrown);
        }

    });
}



function deletePost(userId, pid) {
    console.log(userId + " " + pid);
    const dlt = {

        userId: userId,
        pid: pid,
        operation: "delete"
    };
    $.ajax({
        url: "DeletePostServlet",
        data: dlt,
        success: function (data, textStatus, jqXHR) {
            console.log(data);
            if (data.trim() == "deleted") {
                window.alert("Post Deleted successfully ! ");
            } else {
                window.alert("Something went wrong !");
            }


        },
        error: function (jqXHR, textStatus, errorThrown) {
            console.log(data);
        }

    });
}

function showlikes(pid) {

    $.ajax({
        url: "ShowLikesServlet",
        data: {pid: pid},
        dataType: 'json',
        success: function (response) {
            console.log(response);
            if (response.message) {

                $('#listContainer-' + pid).text(response.message);
            } else {
                var users = response.users;
                var likesHtml = '';
                for (var i = 0; i < users.length; i++) {
                    var user = users[i];
                    var profile = user.profile;
                    var name = user.name;
                    likesHtml += '<div>';
                    likesHtml += '<img src="pics/' + profile + '" class="img-fluid" style="border-radius: 50%; width: 8%;">';
                    likesHtml += '<a class="likeList">' + name + '</a>';
                    likesHtml += '<br>';
                    likesHtml += '</div>';
                }

                $('#listContainer-' + pid).html(likesHtml);
            }

            let container = document.getElementById("listContainer-" + pid);

          
            if (container.style.display === "none") {

                container.style.display = "block";
            } else {

                container.style.display = "none";
            }

        },
        error: function (jqXHR, textStatus, errorThrown) {
            console.log("error " + errorThrown);
        }

    });
}

