<%@page import="java.util.ArrayList"%>
<%@page import="com.course.Course"%>
<%!@SuppressWarnings("unchecked")%>
<jsp:include page="facultyHeader.jsp"></jsp:include>
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
	
	ArrayList<Course> courseList = null;
	if(sess.getAttribute("courseList")!=null) {
		courseList = 	(ArrayList<Course>)sess.getAttribute("courseList");
	}
%>
<style>
	tr {		
		font-family: cursive;
	}
	.hov:hover {
		background-color: white;
		color: black;
	}
</style>
<%if(msg!=null){ %>
	<span style="color: red;"><%=msg %></span>
<%} else { %>
<span style="color: white;"> Course name must be unique.</span>
<%} %>
<div class="viewbox">
	<form action="courseaddview" method="post">
	<table>
		<tr>
		<td><input type="text" class="form-control" name="coursecode" placeholder="Course Code" maxlength="5" required="required"></td>
		<td><input type="text"  class="form-control" name="coursename" placeholder="Course Name" required="required"></td>
		<td><button class="btn btn-info" type="submit">Add Course</button></td>
		<td style="border: solid; border-color: red; padding-left: 10px;"><a href="requestcode" style="color: red;">Request New Course Code.</a></td>
		</tr>
	</table>
	</form>
	<div style="height: 350px; overflow: auto">
	<table style="color: white;" class="table table-dark">
	<tr class="hov">
		<th>Serial no.</th>
		<th>Course Title</th>
		<th colspan="3">Options</th>
	</tr>
	<%int count=1; 
	for(Course c : courseList) {%>
	<tr class="hov">
		<td>Course: <%= count %></td>
		<td><%= c.getCourseName() %></td>
		<td><a href="facultycoursemanagement?cid=<%=c.getCourseId()%>"><button class="btn btn-block btn-info">Management</button></a></td>
		<td><a href="facultycoursefilerequest?cid=<%=c.getCourseId()%>"><button class="btn btn-block btn-info">Files</button></a></td>
		<td><a href="facultycoursevideorequest?cid=<%=c.getCourseId()%>"><button class="btn btn-block btn-info">Videos</button></a></td>
	</tr>
	<%count++;} %>
	</table>
	</div>
</div>
<jsp:include page="facultyFooter.jsp"></jsp:include>