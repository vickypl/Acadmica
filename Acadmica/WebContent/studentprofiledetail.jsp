<jsp:include page="studentHeader.jsp"></jsp:include>
<%@ page import="com.student.Student" %>
<%
	HttpSession sess = request.getSession(false);
	if(sess==null || (sess!=null && sess.getAttribute("student")==null)) {
		response.sendRedirect("home.jsp?msg=Login Required");
		return;
	}
	Student student = (Student)sess.getAttribute("student");
%>
<span
	style="margin-left: 30px; font-family: 'Times New Roman', Times, serif; font-size: 30px; color: white;">Profile
	Details</span>
<hr style="border-width: 3px;">
<style>
td:hover {
	background-color: #ffffff;
	color: black;
}
</style>
<div class="viewbox">
	<div style="height: 350px; overflow: auto">
	<table style="color: white;" class="table table-dark">
		<thead>
			<tr>
				<th scope="col">Personal Details</th>
				<th scope="col">Contact Details</th>
				<th scope="col">Course Details</th>
				<th scope="col">Login Details</th>
			</tr>
		</thead>
		<tbody>
			<tr>
				<td scope="row">Full Name: <%= student.getFirstName() %> <%= student.getLastName() %></td>
				<td>Email: <%= student.getEmail() %></td>
				<td>Main Course: <%= student.getCourse() %></td>
				<td>Username: <%= student.getUsername() %></td>
			</tr>
			<tr>
				<td>Date Of Birth: <%= student.getDateOfBirth() %></td>
				<td>Mobile: <%= student.getMobile() %></td>
				<td>Course Faculty: <%= student.getFaculty() %></td>
				<td>Updated on: <%= student.getLastLogin() %></td>
			</tr>
			<tr>
				<td>Category: <%= student.getCategory() %></td>
				<td>Guardian Mobile: <%= student.getFatherMobile() %></td>
				<td></td>
				<td></td>
			</tr>
			<tr>
				<td>Mother's Name: <%= student.getMotherName() %></td>
				<td>Address: <%= student.getAddress1() %></td>
				<td></td>
				<td></td>
			</tr>
			<tr>
				<td>Father's Name: <%= student.getFatherName() %></td>
			</tr>
		</tbody>
	</table>
	</div>
</div>
<jsp:include page="studentFooter.jsp"></jsp:include>