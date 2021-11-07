<%@page import="java.util.ArrayList"%>
<%@page import="com.faculty.Faculty"%>
<%! @SuppressWarnings("unchecked") %>
<jsp:include page="studentHeader.jsp"></jsp:include>
<%
	HttpSession sess = request.getSession(false);
	if(sess==null || (sess!=null && sess.getAttribute("student")==null)) {
		response.sendRedirect("home.jsp?msg=Login Required");
		return;
	}
	
	ArrayList<Faculty> facultyList = new ArrayList<Faculty>();
	if(sess.getAttribute("facultyList")!=null) {
		facultyList =  (ArrayList<Faculty>)sess.getAttribute("facultyList");
	}
%>
<span
	style="margin-left: 30px; font-family: 'Times New Roman', Times, serif; font-size: 30px; color: white;">Your
	Teacher's</span>
<hr style="border-width: 3px;">
<style>
tr:hover {
	background-color: #ffffff;
	color: black;
}
</style>
<div class="viewbox">
	<div style="height: 350px; overflow: auto">
	<table style="color: white; margin-left: 20px;" width="95%">
		<tr>
			<th>S.no</th>
			<th>Name</th>
			<th>Email</th>
			<th>Subject</th>
		</tr>
		<%int count=1; for(Faculty faculty : facultyList) {%>
		<tr>
			<td><%= count %></td>
			<td><%= faculty.getFirstName()  %>&nbsp;<%= faculty.getLastName()  %></td>
			<td><%= faculty.getEmail() %></td>
			<td><%= faculty.getSubject() %></td>
		</tr>
		<%count++; } %>
	</table>
	</div>
</div>
<jsp:include page="studentFooter.jsp"></jsp:include>