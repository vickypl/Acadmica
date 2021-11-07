<%@page import="java.util.ArrayList"%>
<%@page import="com.course.Course"%>
<%!@SuppressWarnings("unchecked")%>
<jsp:include page="deanHeader.jsp"></jsp:include>
<%
	HttpSession sess = request.getSession(false);
	if(sess==null || (sess!=null && sess.getAttribute("admin")==null)) {
		response.sendRedirect("home.jsp?msg=Login Required");
		return;
	}
	String msg= null;
	if(request.getParameter("msg")!=null) {
		msg=request.getParameter("msg");
	}
	ArrayList<Course> courseList = (ArrayList<Course>)sess.getAttribute("courseList");
%>
<style>
.ho:hover {
	background-color: white;
	color: black;
}
</style>
<div class="viewbox">
	<div
		style="text-align: center; font-family: Georgia, 'Times New Roman', Times, serif; color: rgb(255, 0, 0); font-size: x-large;">
		Available Courses</div>
		<small style="color: red; font-size: 15px; margin: 1% auto;">WARNING: if you delete any course , content related to that course will be automatically deleted.</small>
		<%if(msg!=null) {%>
		<br><small style="color: blue; font-size: 15px; margin: 1% auto;">INFO: <%=msg %></small>
		<%} %>
	<hr style="border-width: 3px;">
	<div style="height: 350px; overflow: auto">
	<table class="table sortable"  id="mytable" style="color: white; padding: 100px;" >
		<thead class="thead-dark">
			<tr class="ho">
				<th scope="col">S.no</th>
				<th scope="col">Course code</th>
				<th scope="col">Course Name</th>
				<th scope="col" colspan="3">Options</th>
			</tr>
		</thead>
		<tbody>
		<%int count=1; for(Course c : courseList) {%>
			<tr class="ho">
				<th scope="row"><%=count %></th>
				<th><%=c.getCourseId() %></th>
				<td><%=c.getCourseName() %></td>
				<td style="padding: 10px;" ><a href="admincoursefilesfetcher?cid=<%=c.getCourseId()%>"><button class="btn btn-info btn-block">Files</button></a></td>
				<td style="padding: 10px;" ><a href="admincoursevideofetcher?cid=<%=c.getCourseId()%>"><button class="btn btn-info btn-block">Videos</button></a></td>
				<td style="padding: 10px;" ><a href="deletecourse?cid=<%=c.getCourseId() %>"><button class="btn btn-danger">Delete Course</button></a></td>
			</tr>
			<%count++; } %>
		</tbody>
	</table>
	</div>
	</div>
<jsp:include page="deanFooter.jsp"></jsp:include>