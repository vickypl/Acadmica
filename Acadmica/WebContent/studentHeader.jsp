<%@page import="com.student.StudentProfilePic"%>
<%@page import="java.util.Date"%>
<%@ page import="com.student.Student" %>
<html>
<title>Acadmica</title>
</head>
 <link rel="icon" href="Images/navbar1.png" type="image/icon type">
<link rel="stylesheet" href="https://maxcdn.bootstrapcdn.com/bootstrap/3.4.1/css/bootstrap.min.css">
<script src="https://ajax.googleapis.com/ajax/libs/jquery/3.5.1/jquery.min.js"></script>
<script src="https://maxcdn.bootstrapcdn.com/bootstrap/3.4.1/js/bootstrap.min.js"></script>
<script async src="https://cse.google.com/cse.js?cx=25615e538d838a628"></script>
<link rel="stylesheet" href="https://cdnjs.cloudflare.com/ajax/libs/font-awesome/4.7.0/css/font-awesome.min.css">
<link rel="stylesheet" href="style.css">
<head>
<%
	HttpSession sess = request.getSession(false);
	if(sess==null || (sess!=null && sess.getAttribute("student")==null)) {
		response.sendRedirect("home.jsp?msg=Login Required");
		return;
	}
	
	StudentProfilePic spp = (StudentProfilePic)sess.getAttribute("studentProPic");
	
	String msg=null;
	if(request.getAttribute("msg")!=null) {
		msg=(String)request.getAttribute("msg");
	}
	Student student = (Student)sess.getAttribute("student");
	java.util.Date date = new java.util.Date();
%>
<body class="body_design" onload="myLoader()">
    <div id="loading">
        <center><span style="font-size: 24px;">loading...</span></center>
    </div>
    <nav class="navbar-inverse">
        <div class="container">
            <div class="navbar-header">
                <button type="button" class="navbar-toggle" data-toggle="collapse" data-target="#menu">
                    <span class="icon-bar"></span>
                    <span class="icon-bar"></span>
                    <span class="icon-bar"></span>
                </button>
                <a href="studentloggedin.jsp"><img src="Images/navbar1.png" alt="" class="img-responsive"
                        style="width: 50px;"></a>
            </div>
            <a class="navbar-brand" href="studentloggedin.jsp">Acadmica</a>
            <div class="collapse navbar-collapse" id="menu">
                <ul class="nav navbar-nav navbar-right">
                    <li><a href="studentloggedin.jsp">Home</a></li>
                    <li><a><button id="goFullScreen" style="background-color: transparent;">Full Screen Mode</button></a></li>
                    <li><a href="studentlogout">LogOut</a></li>
                    <li><a href="aboutus.jsp">AboutUs</a></li>
                </ul>
            </div>
        </div>
    </nav>
    <div class="col-md-9 studentMenubar">
        <ul class="list-unstyled list-inline mainmenu">
            <li><a href="deannoticeforstudents.jsp">Dean Notice
            <span style="position: relative; top: -2px; right: -2px; padding: 1px 6px; border-radius: 50%; background-color: red; color: white;" >New</span>
            </a>
            </li>
            <li><a href="facultynoticeforstudents.jsp">Faculty Notice
            <span style="position: relative; top: -2px; right: -2px; padding: 1px 6px; border-radius: 50%; background-color: red; color: white;" >New</span>
            </a>
            </li>
        </ul>
    </div>
    <div id="wrapper">
        <div id="sidebar-wrapper">
            <div class="profilePicbox">
                <div class="picoption">
				     <%if(spp.getUrl()!=null) {%>
                     	 <img src="profilepics\<%= spp.getUrl() %>" class="profileimage" id="photo">
                     <%} else { %>
                     	<img src="Images/dpdef.jpg" class="profileimage" id="photo"> 
                    <%} %>
                    <form action="profilepicupdate" method="POST" enctype="multipart/form-data">
                        <input type="file" id="file" name="pic" onchange="clickPhotoSubmit()" accept="image/*">
                        <label for="file" id="uploadBtn">Choose Photo</label>
                        <button type="submit" id="photoSubmit"></button>
                    </form>
                    <span style="margin-bottom: 60px; color: white; font-style: italic; font-size: 20px;"><%= student.getFirstName() %> <%=student.getLastName() %></span>
                </div>
            </div>
            <div class="sidebarManage">
                <ul class="list-unstyled">
                    <li>
                    <div class="searchlook"><div class="gcse-search"></div></div>
                    </li>
                    <li><a href="studentprofiledetail.jsp"><button class="sidebar-btn">Profile Details</button></a></li>
                    <li><a href="studentsteacherlist.jsp"><button class="sidebar-btn">Faculty</button></a></li>
                    <li><a href="studentassingmentlist.jsp"><button class="sidebar-btn">Assignment List</button></a></li>
                    <li><a href="studentsubjectlist.jsp"><button class="sidebar-btn">Courses</button></a></li>
                    <li><a href="studentfiles.jsp"><button class="sidebar-btn">My files</button></a></li>
                    <li><a href="doubthandle"><button class="sidebar-btn">Doubts</button></a></li>
                    <li><a href="changepassword"><button class="sidebar-btn">Change Password</button></a></li>
                    <li><a href="studentlogout"><button class="sidebar-btn">LogOut</button></a></li>
                </ul>
            </div>
        </div>
        <a href="#menu-toggle" style="color: rgb(255, 255, 255); margin: 0% auto; font-size: 30px;" class="fa fa-bars "
            id="menu-toggle"> Main Menu>></a>


        <!-- Page Content -->
        <div id="page-content-wrapper">
        <%if(msg!=null) {%>
        	<span style="color: green; font-size: 20px;"> <%= msg %></span>
        <% msg=null;   } %>
            <div class="container-fluid">
                <div class="row">
                    <div class="col-md-12">
                        <div class="contentbox">
                            <fieldset
                                style="margin-top: 10px; padding: 10px; margin: 2% auto; width: 400px; border-color: white; border-radius: 10px; border-style: solid; border-width: 3px; text-align: center;">
                                <span style="color: white; font-size: 20px;"><%= date %></span>
                            </fieldset>
                            <hr style="border-width: 3px;">