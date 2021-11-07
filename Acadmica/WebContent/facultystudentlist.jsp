<%@page import="java.util.Set"%>
<%@page import="com.student.Student"%>
<%@page import="java.util.ArrayList"%>
<%@page import="java.util.HashMap"%>
<%! @SuppressWarnings("unchecked") %>
<jsp:include page="facultyHeader.jsp"></jsp:include>
<%
	HttpSession sess = request.getSession(false);
	if(sess==null || (sess!=null && sess.getAttribute("faculty")==null)) {
		response.sendRedirect("home.jsp?msg=Login Required");
		return;
	}
	
	Student studentFound = null;
	if(request.getAttribute("studentFound")!=null) {
		studentFound = (Student)request.getAttribute("studentFound");
	}
	
	HashMap<String, ArrayList<Student>> hashmap = (HashMap<String, ArrayList<Student>>)sess.getAttribute("courseStudentHashmap");
%>
<table style="width: 100%; color: white;">
	<tr>
		<form action="facultystudentsearch" method="get">
		<td style="width: 20%;"><span style="font-family: 'Times New Roman', Times, serif; font-size: 20px; color: white;">Student's List</span></td>
		<td width="100%"><input type="text" name="studentid" placeholder="Search Student via id" value="" class="form-control" maxlength="50" required></td>
		<td width="100%"><input type="submit"  value="Search" class="form-control" maxlength="50" required></td>
		</form>
	</tr>
	<%if(studentFound!=null) {%>
		<span style="color: green;">Student Found</span>
			<tr class="ho">
				<th scope="col">Registration code</th>
				<th scope="col">Name</th>
				<th scope="col">E-mail</th>
				<th scope="col">Action</th>
			</tr>
		<tbody>
			<tr class="ho">
				<td scope="row"><%= studentFound.getRegistrationId() %></td>
				<td><%= studentFound.getFirstName()+" "+studentFound.getLastName() %></td>
				<td><%= studentFound.getEmail() %></td>
				<td><a href="studentdetailview?studentid=<%=studentFound.getRegistrationId() %>">ViewFullDetails</a></td>
			</tr>
		</tbody>
		<%} %>
</table>
<hr style="border-width: 3px;">
<style>
.ho:hover {
	background-color: #ffffff;
	color: black;
}
</style>
<div class="viewbox">
<div style="height: 350px; overflow: auto">
	<table style="color: white;" class="table table-dark">
		<thead>
			<tr class="ho">
				<th scope="col">Registration code</th>
				<th scope="col">Name</th>
				<th scope="col">E-mail</th>
				<th scope="col">Action</th>
			</tr>
		</thead>
		<tbody>
		<%	Set<String> keys = hashmap.keySet();
			for(String key : keys) {
				ArrayList<Student> studentList = hashmap.get(key);
				for(Student student : studentList) {
			%>
			<tr class="ho">
				<th scope="row"><%= student.getRegistrationId() %></th>
				<td><%= student.getFirstName()+" "+student.getLastName() %></td>
				<td><%= student.getEmail() %></td>
				<td><a href="studentdetailview?studentid=<%=student.getRegistrationId() %>">ViewFullDetails</a></td>
			</tr>
			<% }
			} %>
		</tbody>
	</table>
</div>
</div>
<jsp:include page="facultyFooter.jsp"></jsp:include>