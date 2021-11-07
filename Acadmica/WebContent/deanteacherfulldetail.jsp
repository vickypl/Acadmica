<%@page import="com.faculty.FacutlyProfilePic"%>
<%@page import="com.faculty.Faculty"%>
<jsp:include page="deanHeader.jsp"></jsp:include>
<%
	HttpSession sess = request.getSession(false);
	if (sess == null
			|| (sess != null && sess.getAttribute("admin") == null)) {
		response.sendRedirect("home.jsp?msg=Login Required");
		return;
	}

	Faculty faculty = (Faculty) request.getAttribute("faculty");
	FacutlyProfilePic fpp = (FacutlyProfilePic) request.getAttribute("facultyPic");
	String name = faculty.getFirstName() + " " + faculty.getLastName();
%>
<style>
<!--
td:hover {
	color: black;
	background-color: white;
}
-->
</style>
<div class="viewbox">
	<table style="color: white;" class="table table-dark">
		<!-- desing this as per student view requirements -->
		<div class="text-left">
			<img src="profilepics\<%=fpp.getUrl()%>" style="width: 150px; margin: 10px;" /> <span style="padding: 10px; font-family: 'Times New Roman', Times, serif; font-size: 25px; color: white;">
				Reg. Code: <%=faculty.getRegistrationId()%></span> <span style="padding: 10px; font-family: 'Times New Roman', Times, serif; font-size: 25px; color: white;">
				Name: <%=name%></span> <span style="padding: 10px; font-family: 'Times New Roman', Times, serif; font-size: 25px; color: white;">
				Course:<%=faculty.getSubject()%></span><br>
		</div>
		<a href="admineditfaculty?id=<%=faculty.getRegistrationId()%>"><button
				style="margin: 2px; font-size: larger;" class="btn btn-block">Edit
				Faculty Detail</button></a>
		<thead>
			<tr class="ho">
				<td><%=faculty.getGender()%></td>
				<td><%=faculty.getDateOfBirth()%></td>
				<td><%=faculty.getMaritalStatus()%></td>
				<td><%=faculty.getGuardianName()%></td>
			</tr>
		</thead>
		<tbody>
			<tr class="ho">
				<td>====</td>
				<td>===</td>
				<td>====</td>
				<td><%=faculty.getEmail()%></td>
			</tr>
		</tbody>
		<tbody>
			<tr class="ho">
				<td><%=faculty.getMobile()%></td>
				<td>===</td>
				<td><%=faculty.getAddress1()%></td>
				<td><%=faculty.getAddress2()%></td>
			</tr>
		</tbody>
		<tbody>
			<tr class="ho">
				<td><%=faculty.getCity()%></td>
				<td><%=faculty.getState()%></td>
				<td><%=faculty.getPincode()%></td>
				<td><%=faculty.getSubject()%></td>
			</tr>
		</tbody>
		<tbody>
			<tr class="ho">
				<td><%=faculty.getUsername()%></td>
				<td colspan="2">Reg. Date: <%=faculty.getRegistrationDate()%></td>
				<td>Last Login: <%=faculty.getLastLogin()%></td>
				<td></td>
			</tr>
		</tbody>
	</table>
</div>
<jsp:include page="deanFooter.jsp"></jsp:include>