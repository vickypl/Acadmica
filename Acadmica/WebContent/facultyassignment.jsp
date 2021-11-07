<%@page import="com.course.Course"%>
<%@page import="com.faculty.Assignment"%>
<%@page import="java.util.ArrayList"%>
<%! @SuppressWarnings("unchecked") %>
<jsp:include page="facultyHeader.jsp"></jsp:include>
<%
	HttpSession sess = request.getSession(false);
	if(sess==null || (sess!=null && sess.getAttribute("faculty")==null)) {
		response.sendRedirect("home.jsp?msg=Login Required");
		return;
	}
	
	String msg= null;
 	if(request.getParameter("msg")!=null) {
 		msg=request.getParameter("msg");
 	}
	
	ArrayList<Course> courseList = (ArrayList<Course>)sess.getAttribute("courseList");
	ArrayList<Assignment> assignmentList = (ArrayList<Assignment>)sess.getAttribute("assignmentList");
%>
<style>
.ho:hover {
	background-color: #ffffff;
	color: black;
}
</style>
<div class="viewbox">
	<form action="assignmentpost" method="post">
		<table>
			<tr>
				<td style="color: white;"><i class="fa fa-book"></i></td>
				<td><label for="course">Subject</label>
				<select name="course" class="form-control">
				<% for (Course c : courseList) { 
					if(c.getCourseName()!=null) {
				%>
						<option value="<%=c.getCourseName()%>"><%=c.getCourseName()%></option>
				<% }
				} %>
				</select> <small style="color: white; font-size: 10px;">Select Course In which you want to assign.</small></td>
				<td style="width: 50%;"><label for="course">Assignment</label>
					<input class="form-control" style="width: 100%;" type="text"
					name="assignment" placeholder="Assignment" maxlength="250" required></td>
				<td><label for="course">Submission Date</label> <input
					class="form-control" type="date" name="subDate"
					placeholder="Submission Date" required></td>
				<td>
					<button class="btn btn-success" type="submit">Assign</button>
				</td>
			</tr>
		</table>
	</form>
	<hr style="border-width: 3px;">
	<div style="height: 350px; overflow: auto">
	<table style="color: white;" class="table table-dark">
		<thead>
			<tr class="ho">
				<th scope="col">S/No.</th>
				<th scope="col">Subject</th>
				<th scope="col">Assignment</th>
				<th scope="col">Assignment Date</th>
				<th scope="col">Submission Date</th>
				<th scope="col">Status</th>
			</tr>
		</thead>
		<tbody>
		<%if(msg!=null) {%>
			<h5 style="text-align: center; color: green; font-style: bold;"><%= msg %></h5>
		<%} 
 			int count=1; 
 			for(Assignment ass : assignmentList) {
				if (ass.getAssingment() != null) {
		%>
			<tr class="ho">
				<th scope="row"><%= count %></th>
				<td><%=ass.getCourse() %></td>
				<td><%=ass.getAssingment() %></td>
				<td><%= ass.getAssDate() %></td>
				<td><%= ass.getSubDate() %></td>
				<td><input type="checkbox" value="<%= ass.getStatus() %>"> recived</td>
			</tr>
			<%
					}
				count++;
				} %>
		</tbody>
	</table>
	</div>
</div>
<jsp:include page="facultyFooter.jsp"></jsp:include>