<%@page import="com.faculty.FacutlyProfilePic"%>
<%@page import="com.faculty.Faculty"%>
<html>
<title>Acadmica</title>
</head>
<%
	HttpSession sess = request.getSession(false);
	if(sess==null || (sess!=null && sess.getAttribute("faculty")==null)) {
		response.sendRedirect("home.jsp?msg=Login Required");
		return;
	}
	
	FacutlyProfilePic fpp = (FacutlyProfilePic)sess.getAttribute("profilePicInfo");
	
	String msg=null;
	if(request.getAttribute("msg")!=null) {
		msg=(String)request.getAttribute("msg");
	}
	Faculty faculty = (Faculty)sess.getAttribute("faculty");
	java.util.Date date = new java.util.Date();
%>
<link rel="icon" href="Images/navbar1.png" type="image/icon type">
<link rel="stylesheet" href="https://maxcdn.bootstrapcdn.com/bootstrap/3.4.1/css/bootstrap.min.css">
<script src="https://ajax.googleapis.com/ajax/libs/jquery/3.5.1/jquery.min.js"></script>
<script src="https://maxcdn.bootstrapcdn.com/bootstrap/3.4.1/js/bootstrap.min.js"></script>
<script async src="https://cse.google.com/cse.js?cx=25615e538d838a628"></script>
<link rel="stylesheet" href="https://cdnjs.cloudflare.com/ajax/libs/font-awesome/4.7.0/css/font-awesome.min.css">
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
                <a href="facultyloggedin.jsp"><img src="Images/navbar1.png" alt="" class="img-responsive"
                        style="width: 50px;"></a>
            </div>
            <a class="navbar-brand" href="facultyloggedin.jsp">Acadmica</a>
            <div class="collapse navbar-collapse" id="menu">
                <ul class="nav navbar-nav navbar-right">
                    <li><a href="facultyloggedin.jsp">Home</a></li>
                    <li><a><button id="goFullScreen" style="background-color: transparent;">Full Screen Mode</button></a></li>
                    <li><a href="facultylogout">LogOut</a></li>
                    <li><a href="aboutus.jsp">AboutUs</a></li>
                </ul>
            </div>
        </div>
    </nav>
    <div class="col-md-9 studentMenubar">
        <ul class="list-unstyled list-inline mainmenu">
            <li><a href="deannoticeforfaculty.jsp">Dean Notice
            <span style="position: relative; top: -2px; right: -2px; padding: 1px 6px; border-radius: 50%; background-color: red; color: white;" >New</span>
            </a>
            </li>
            <li><a href="facultynoticeonlyfromdean.jsp">Notice(Facultyonly)
            <span style="position: relative; top: -2px; right: -2px; padding: 1px 6px; border-radius: 50%; background-color: red; color: white;" >New</span>
            </a></li>
			<li><a href="facultycoursecoderequest.jsp">Course Code Request(from Student)
            <span style="position: relative; top: -2px; right: -2px; padding: 1px 6px; border-radius: 50%; background-color: red; color: white;" >New</span>
            </a></li>
        </ul>
    </div>
    <div id="wrapper">
        <div id="sidebar-wrapper">
            <div class="profilePicbox">
                <div class="picoption">
				     <%if(fpp.getUrl()!=null) {%>
                     	 <img src="profilepics\<%= fpp.getUrl() %>" class="profileimage" id="photo">
                     <%} else { %>
                     	<img src="Images/dpdef.jpg" class="profileimage" id="photo"> 
                    <%} %>
                    <form action="facultypicupdate" method="POST" enctype="multipart/form-data">
                        <input type="file" id="file" name="pic" onchange="clickPhotoSubmit()" accept="image/*">
                        <label for="file" id="uploadBtn">Choose Photo</label>
                        <button type="submit" id="photoSubmit"></button>
                    </form>
                    <% String name= faculty.getFirstName()+" "+faculty.getLastName(); %>
                    <span style="color: white; font-family: times new roman; font-style: italic; font-size: 18px;"><%=name%></span>
                </div>
            </div>
            <div class="sidebarManage">
                <ul class="list-unstyled">
                    <li>
                    <div class="searchlook"><div class="gcse-search"></div></div>
                    </li>
                    <li><a href="facultyprofiledetail.jsp"><button class="sidebar-btn">Profile Details</button></a></li>
                    <li><a href="facultystudentlist.jsp"><button class="sidebar-btn">Student's List</button></a></li>
                    <li><a href="facultycoursedetails.jsp"><button class="sidebar-btn">Course Management</button></a></li>
                    <li><a href="facultyassignment.jsp"><button class="sidebar-btn">Assignments</button></a></li>
                    <li><a href="studentdoubtsection.jsp"><button class="sidebar-btn">Student Doubt Section</button></a></li>
                    <li><a href="facultystudymaterial.jsp"><button class="sidebar-btn">Study Material</button></a></li>
                    <li><a href="facultynoticeposter.jsp"><button class="sidebar-btn">Notice for Student's</button></a></li>
                    <li><a href="facultypasswordchange"><button class="sidebar-btn">Change Password</button></a></li>
                    <li><a href="facultylogout"><button class="sidebar-btn">LogOut</button></a></li>
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