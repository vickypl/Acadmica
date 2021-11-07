<%@page import="com.faculty.Faculty"%>
<jsp:include page="facultyHeader.jsp"></jsp:include>
<%
	HttpSession sess = request.getSession(false);
	if(sess==null || (sess!=null && sess.getAttribute("faculty")==null)) {
		response.sendRedirect("home.jsp?msg=Login Required");
		return;
	}
	Faculty faculty = (Faculty)sess.getAttribute("faculty");
%>
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
				<td scope="row">Full Name: <%= faculty.getFirstName() %> <%= faculty.getLastName() %></td>
				<td>Email: <%= faculty.getEmail() %></td>
				<td>Main Course: <%= faculty.getSubject() %></td>
				<td>Username: <%= faculty.getUsername() %></td>
			</tr>
			<tr>
				<td>Date Of Birth: <%= faculty.getDateOfBirth() %></td>
				<td>Mobile: <%= faculty.getMobile() %></td>
				<%-- <td>Course Faculty: <%= faculty.getFaculty() %></td> --%>
				<td></td>
				<td>Updated on: <%= faculty.getLastLogin() %></td>
			</tr>
			<tr>
				<td>Guardian: <%= faculty.getGuardianName() %></td>
				<td>Address: <%= faculty.getAddress1() %> </td>
				<td></td>
				<td></td>
			</tr>
			<tr>
				<td>Merital Status: <%= faculty.getMaritalStatus() %></td>
				<td>2</td>
				<td>3</td>
				<td>4</td>
			</tr>
			<tr>
				<td></td>
			</tr>
		</tbody>
	</table>
	</div>
</div>
<jsp:include page="facultyFooter.jsp"></jsp:include>