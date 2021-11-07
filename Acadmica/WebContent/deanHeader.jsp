<%@page import="java.util.Date"%>
<%@page import="com.admin.Admin"%>
<%
	HttpSession sess = request.getSession(false);
	if(sess==null || (sess!=null && sess.getAttribute("admin")==null)) {
		response.sendRedirect("deanlogin.jsp?msg=Authentication Failed.");
		return;
	}
	
	Admin admin = (Admin)sess.getAttribute("admin");
	String name = admin.getFirstName()+" "+admin.getLastName();
	
	Date date = new Date();
%>
<html>
<title>Acadmica</title>
</head>
<link rel="icon" href="Images/navbar1.png" type="image/icon type">
<link rel="stylesheet" href="https://maxcdn.bootstrapcdn.com/bootstrap/3.4.1/css/bootstrap.min.css">
<script src="https://ajax.googleapis.com/ajax/libs/jquery/3.5.1/jquery.min.js"></script>
<script src="https://maxcdn.bootstrapcdn.com/bootstrap/3.4.1/js/bootstrap.min.js"></script>
<script async src="https://cse.google.com/cse.js?cx=25615e538d838a628"></script>
<link rel="stylesheet" href="https://cdnjs.cloudflare.com/ajax/libs/font-awesome/4.7.0/css/font-awesome.min.css">
    <!--  data table js jq css-->
     <script type="text/javascript" src="js/datatable/js/dataTables.responsive.min.js"></script>
    <script type="text/javascript" src="js/datatable/js/jquery-3.5.1.js"></script>
    <script type="text/javascript" src="js/datatable/js/jquery.dataTables.min.js"></script>
    <script type="text/javascript" src="js/extra.js"></script>
    <link rel="stylesheet" href="datatable/css/jquery.dataTables.min.css">
    <link rel="stylesheet" href="datatable/css/responsive.dataTables.min.css">
    <!--  data table js jq css-->
<link rel="stylesheet" href="style.css">
<head>
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
                <a href="deanloggedin.jsp"><img src="Images/navbar1.png" alt="" class="img-responsive"
                        style="width: 50px;"></a>
            </div>
            <a class="navbar-brand" href="deanloggedin.jsp">Acadmica</a>
            <div class="collapse navbar-collapse" id="menu">
                <ul class="nav navbar-nav navbar-right">
                    <li><a href="deanloggedin.jsp">Home</a></li>
                    <li><a><button id="goFullScreen" style="background-color: transparent;">Full Screen Mode</button></a></li>
                    <li><a href="adminlogout">LogOut</a></li>
                    <li><a href="aboutus.jsp">AboutUs</a></li>
                </ul>
            </div>
        </div>
    </nav>
    <div class="col-md-9 studentMenubar">
        <ul class="list-unstyled list-inline mainmenu">
            <li>
            	<a href="deanadmissionrequest.jsp">Admission Requests
            	<span style="position: relative; top: -2px; right: -2px; padding: 1px 6px; border-radius: 50%; background-color: red; color: white;" >New</span>
            	</a>
            </li>
            <li><a href="deancoursecoderequest.jsp">Course Requests
            <span style="position: relative; top: -2px; right: -2px; padding: 1px 6px; border-radius: 50%; background-color: red; color: white;" >New</span>
            </a></li>
            <li><a href="deanpendingcodes.jsp">Pending Codes</a></li>
            <li><a href="deanfacultyspendingsubregcodes.jsp">Pending Subject Request Codes (from Students)</a></li>
        </ul>
    </div>
    <div id="wrapper">
        <div id="sidebar-wrapper">
            <div class="profilePicbox">
                <div class="picoption">
                    <%if(admin.getPicurl()!=null) {%>
                     	 <img src="profilepics\<%= admin.getPicurl() %>" class="profileimage" id="photo">
                     <%} else { %>
                     	<img src="Images/dpdef.jpg" class="profileimage" id="photo"> 
                    <%} %>
                    <form action="adminprofilepic" method="POST" enctype="multipart/form-data">
                        <input type="file" id="file" name="pic" onchange="clickPhotoSubmit()" accept="image/*">
                        <label for="file" id="uploadBtn">Choose Photo</label>
                        <button type="submit" id="photoSubmit"></button>
                    </form>
                    <span style="margin-bottom: 60px; color: white; font-style: italic; font-size: 20px;"><%=name %></span>
                </div>
            </div>
            <div class="sidebarManage">
                <ul class="list-unstyled">
                	<li>
					<div class="searchlook"><div class="gcse-search"></div></div>
					</li>
               	 	<!-- <li><form action="#"><input type="text" name="search" class="form-control" placeholder="Search"/><button style="margin-top: 3px; border-radius: 4px; font-family: Georgia, 'Times New Roman', Times, serif;" type="submit" class="btn-block btn-success">Search</button></form></li> -->
                    <li><a href="deanprofilemanager.jsp"><button class="sidebar-btn">Profile Manager</button></a></li>
                    <li><a href="deanstudentlist.jsp"><button class="sidebar-btn">Student's List</button></a></li>
                    <li><a href="deanteacherslist.jsp"><button class="sidebar-btn">Teacher's List</button></a></li>
                     <li><a href="deanbackups.jsp"><button class="sidebar-btn">Excel Backups</button></a></li>
<!--                    <li><a href="deancoursesdata.jsp"><button class="sidebar-btn">Course Data</button></a></li> -->
                    <li><a href="deancoursemanagement.jsp"><button class="sidebar-btn">Course management</button></a></li>
                    <li><a href="adminnotice"><button class="sidebar-btn">Notice Poster</button></a></li>
                    <li><a href="adminlogout"><button class="sidebar-btn">LogOut</button></a></li>
                </ul>
            </div>
        </div>
        <a href="#menu-toggle" style="color: rgb(255, 255, 255); margin: 0% auto; font-size: 30px;" class="fa fa-bars "
            id="menu-toggle"> Main Menu>></a>


        <!-- Page Content -->
        <div id="page-content-wrapper">
            <div class="container-fluid">
                <div class="row">
                    <div class="col-md-12">
                        <div class="admincontentbox">
                            <fieldset
                                style="margin-top: 10px; padding: 10px; margin: 2% auto; width: 400px; border-color: white; border-radius: 10px; border-style: solid; border-width: 3px; text-align: center;">
                                <span style="color: white; font-size: 20px;"><%=date %></span>
                            </fieldset>
                            <hr style="border-width: 3px;">