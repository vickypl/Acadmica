<%@page import="com.course.Course"%>
<%@page import="java.util.ArrayList"%>
<%! @SuppressWarnings("unchecked") %>
<jsp:include page="studentHeader.jsp"></jsp:include>
<%
	HttpSession sess = request.getSession(false);
	if(sess==null || (sess!=null && sess.getAttribute("student")==null)) {
		response.sendRedirect("home.jsp?msg=Login Required");
		return;
	}
	
	ArrayList<Course> coursesList = new ArrayList<Course>();
	if(sess.getAttribute("coursesList")!=null) {
		coursesList = (ArrayList<Course>)sess.getAttribute("coursesList");
	}
	
	ArrayList<Course> courseOfStudent = new ArrayList<Course>();
	if(sess.getAttribute("courseOfStudent")!=null) {
		courseOfStudent =  (ArrayList<Course>)sess.getAttribute("courseOfStudent");
	}
	
	String msg = null;
	if(request.getParameter("msg")!=null) {
		msg=request.getParameter("msg");
	}
	
%>
<span
	style="margin-left: 30px; font-family: 'Times New Roman', Times, serif; font-size: 30px; color: white;">Subjects</span>
<hr style="border-width: 3px;">
<style>
.ho:hover {
	background-color: #ffffff;
	color: black;
}
</style>
<div class="viewbox">
	<span style="color: white;">List of Available Courses.</span>
	<table>
	<form action="addmorecourse" method="post">
		<tr>
		<td><select name="course" value="" class="form-control">
		<% for(Course c : coursesList) {%>
			<option value="<%=c.getCourseName()+" "+c.getCourseId() %>"><%=c.getCourseName() %></option>
		<%} %>
		</select></td>
		<td><input type="text" value="" name="code" class="form-control" placeholder="Subject Reg. Code"/></td>
		<td><button class="btn btn-info" type="submit">Add Course</button></td>
	</form>
		</tr>
		<%if(msg!=null) {%>		
		<tr><td><span style="color: Green; font-size: 15px;"><%=msg %></span></td></tr>
		<%} else { %>
		<tr><td><span style="color: white; font-size: 15px;">Select a course you want to request code for.</span></td></tr>
		<%} %>
		<tr>
	<form action="subjectcoderequest" method="post">
		<td><select name="course" value="" class="form-control">
		<% for(Course c : coursesList) {%>
			<option value="<%=c.getCourseName()+" "+c.getCourseId() %>"><%=c.getCourseName() %></option>
		<%} %>
		</select></td>
		<td><button class="btn btn-danger">Request Subject Reg. Code.</button></a></td>
	</form>
	</tr>
	</table>
	<div style="height: 350px; overflow: auto">
	<table style="color: white;" class="table table-dark">
		<thead>
			<tr class="ho">
				<th scope="col">S.no.</th>
				<th scope="col">Course Code</th>
				<th scope="col">Course </th>
				<th scope="col" colspan="2">Option</th>
			</tr>
		</thead>
		<tbody>
		<%int count=1; for(Course c : courseOfStudent) { %>
			<tr class="ho">
				<th scope="row"><%= count %></th>
				<td><%= c.getCourseId() %></td>
				<td><%= c.getCourseName() %></td>
				<td><a  href="studentcoursefilefetcher?id=<%=c.getCourseId()%>"><button class="btn btn-info btn-block">Files</button></a></td>
				<td><a  href="studentcoursevideorequest?id=<%=c.getCourseId()%>"><button class="btn btn-info btn-block">Videos</button></a></td>
			</tr>
			<%count++; } %>
		</tbody>
	</table>
	</div>
</div>
<jsp:include page="studentFooter.jsp"></jsp:include>