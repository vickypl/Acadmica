<%@page import="com.course.CourseVideos"%>
<%@page import="java.util.ArrayList"%>
<%!@SuppressWarnings("unchecked")%>
<%
	HttpSession sess = request.getSession(false);
	if(sess==null || (sess!=null && sess.getAttribute("faculty")==null)) {
		response.sendRedirect("home.jsp?msg=Login Required");
		return;
	}
	
	String msg = null;
	if(request.getParameter("msg")!=null) {
		msg=request.getParameter("msg");
	}
	
	String courseName = null;
	if(sess.getAttribute("selectedCourseName")!=null) {
		courseName=(String) sess.getAttribute("selectedCourseName");
	}
	
	ArrayList<CourseVideos> courseVideosList = null;
	if(sess.getAttribute("courseVideosList")!=null) {
		courseVideosList = (ArrayList<CourseVideos>)sess.getAttribute("courseVideosList");
	}
%>
<html>
<head>
<meta http-equiv="Content-Type" content="text/html; charset=ISO-8859-1">
<title>Acadmica</title>
<link rel="icon" href="Images/navbar1.png" type="image/icon type">
<link rel="stylesheet" href="https://maxcdn.bootstrapcdn.com/bootstrap/3.4.1/css/bootstrap.min.css">
<script src="https://ajax.googleapis.com/ajax/libs/jquery/3.5.1/jquery.min.js"></script>
<script src="https://maxcdn.bootstrapcdn.com/bootstrap/3.4.1/js/bootstrap.min.js"></script>
<link rel="stylesheet" href="https://cdnjs.cloudflare.com/ajax/libs/font-awesome/4.7.0/css/font-awesome.min.css">
<link rel="stylesheet" href="style.css">
</head>
<body class="coursecontentview">
<div class="courseheading">
 <div class="text-left">
	<a href="facultycoursedetails.jsp"><button class="text-left btn btn-info">Back</button></a>
</div>
<%=courseName %>
</div>
<div>
<%if(msg!=null) {%>
	<strong style="color: lime;"><%=msg %></strong>
<%} %>
<table class="table" style="color: white;">
  <thead>
    <tr>
      <th>Videos present</th>
    </tr>
  </thead>
  <tbody>
  <% if(courseVideosList.size()>0) {
  int count=1; for(CourseVideos cv : courseVideosList) {%>
    <tr>
         <td>S.no. <%=count%>&nbsp;>>File Title : <%=cv.getVideoTitle() %></td>
    	<td align="left"><a href="facultydeletecourseitem?id=<%=cv.getId() %>&type=videotype&url=<%=cv.getUrl()%>&cid=<%=cv.getCourseId()%>"><button class="btn btn-danger">Delete</button></a></td>
    </tr>
    <tr>
      <td><div class="contentdom">
      <iframe width="99%" height="99%" src="courseVideos\<%=cv.getUrl() %>" allow="accelerometer; autoplay; clipboard-write; encrypted-media; gyroscope; picture-in-picture" allowfullscreen></iframe>
      </div></td>
    </tr>
    <%count++;}
  } %>
  </tbody>
</table>
</div>
</body>
</html>