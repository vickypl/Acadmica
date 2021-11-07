<%@page import="com.student.Student"%>
<jsp:include page="deanHeader.jsp"></jsp:include>
<%@page import="com.student.StudentProfilePic"%>
<%

	HttpSession sess = request.getSession(false);
	if(sess==null || (sess!=null && sess.getAttribute("admin")==null)) {
		response.sendRedirect("home.jsp?msg=Login Required");
		return;
	}

	StudentProfilePic spp = (StudentProfilePic)request.getAttribute("studentPic");
	Student student = (Student)request.getAttribute("student");
%>
<style>
td:hover {
	background-color: #ffffff;
	color: black;
}
</style>
<div class="viewbox">
	<table style="color: white;" class="table table-dark">
		<!-- desing this as per student view requirements -->
		<div class="text-left">
			<img src="profilepics\<%=spp.getUrl() %>" style="width: 150px; margin: 10px;" /> <span
				style="padding: 10px; font-family: 'Times New Roman', Times, serif; font-size: 25px; color: white;">Reg.
				Code: <%= student.getRegistrationId() %></span> <span
				style="padding: 10px; font-family: 'Times New Roman', Times, serif; font-size: 25px; color: white;">Name:
				<%= student.getFirstName()+" "+student.getLastName() %></span> <span
				style="padding: 10px; font-family: 'Times New Roman', Times, serif; font-size: 25px; color: white;">Course:
				<%= student.getCourse() %></span>
		</div>
		<a href="admineditstudent?id=<%= student.getRegistrationId()%>"><button style="margin: 2px; font-size: larger;" class="btn btn-block">Edit Student Detail</button></a>
		<thead>
			<tr class="ho">
				<td><%= student.getGender() %></td>
				<td><%= student.getDateOfBirth() %></td>
				<td><%= student.getCategory() %></td>
				<td><%= student.getFatherName() %></td>
			</tr>
		</thead>
		<tbody>
			<tr class="ho">
				<td><%= student.getMotherName()%></td>
				<td><%= student.getStandard()%></td>
				<td><%= student.getCourse()%></td>
				<td><%= student.getSchool()%></td>
			</tr>
		</tbody>
				<tbody>
			<tr class="ho">
				<td><%= student.getCollege() %></td>
				<td><%= student.getDegree()%></td>
				<td><%= student.getMarks()%></td>
				<td><%= student.getEmail()%></td>
			</tr>
		</tbody>
				<tbody>
			<tr class="ho">
				<td><%= student.getMobile()%></td>
				<td><%= student.getFatherMobile() %></td>
				<td><%= student.getAddress1()%></td>
				<td><%= student.getAddress2()%></td>
			</tr>
		</tbody>
				<tbody>
			<tr class="ho">
				<td><%= student.getCity()%></td>
				<td><%= student.getState()%></td>
				<td><%= student.getPincode()%></td>
				<td><%= student.getSubject()%></td>
			</tr>
		</tbody>
				<tbody>
			<tr class="ho">
				<td><%=student.getUsername() %></td>
				<td colspan="2">Reg. Date: <%=student.getRegistrationDate() %></td>
				<td>Last Login: <%=student.getLastLogin() %></td>
				<td></td>
			</tr>
		</tbody>
	</table>
</div>
<jsp:include page="deanFooter.jsp"></jsp:include>