<%@page import="com.student.Student"%>
<%@page import="java.util.ArrayList"%>
<%!@SuppressWarnings("unchecked")%>
<jsp:include page="deanHeader.jsp"></jsp:include>
<%

	HttpSession sess = request.getSession(false);
	if(sess==null || (sess!=null && sess.getAttribute("admin")==null)) {
		response.sendRedirect("deanlogin.jsp?msg=Authentication Failed.");
		return;
	}
	
	ArrayList<Student> studentList = (ArrayList<Student>)sess.getAttribute("studentList");
%>
<!-- <table style="width: 100%;">
	<tr>
		<td style="width: 20%;"><span
			style="font-family: 'Times New Roman', Times, serif; font-size: 20px; color: white;">Student's
				List</span></td>
		<td width="100%"><input type="text" onkeyup="searchdata(this.value)" placeholder="Search Student" value="" class="form-control" maxlength="50" required></td>
		</tr>
</table>
<script type="text/javascript">
	function searchdata(a) {
		var xmlhttp = new XMLHttpRequest();
		xmlhttp.onreadystatechange =function () {
			document.getElementById("loaddata").innerHTML=xmlhttp.responseText;
		}
		
		xmlhttp.open("get", "searchstudent?q="+a, true);
		xmlhttp.send();
	}
</script> -->
<style>
.ho:hover {
	background-color: #ffffff;
	color: black;
}
</style>
<div id="loaddata" class="viewbox">
	<table style="color: white;" id="mytable" class="table sortable">
		<thead>
			<tr class="ho">
				<th scope="col">S.no </th>
				<th scope="col">Registration code</th>
				<th scope="col">Name</th>
				<th scope="col">E-mail</th>
				<th scope="col">Action</th>
			</tr>
		</thead>
		<tbody>
		<%long count=1; for(Student stu : studentList) { %>
			<tr class="ho">
				<th scope="row"><%=count %></th>
				<th><%=stu.getRegistrationId() %></th>
				<td><%= stu.getFirstName()+" "+stu.getLastName() %></td>
				<td><%= stu.getEmail() %></td>
				<td><a href="adminstudentfulldetail?studentid=<%=stu.getRegistrationId()%>">ViewFullDetails</a></td>
			</tr>
			<%count++; } %>
		</tbody>
	</table>
</div>
<jsp:include page="deanFooter.jsp"></jsp:include>