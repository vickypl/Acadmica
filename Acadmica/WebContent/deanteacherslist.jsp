<%@page import="com.faculty.Faculty"%>
<%@page import="java.util.ArrayList"%>
<%!@SuppressWarnings("unchecked")%>
<jsp:include page="deanHeader.jsp"></jsp:include>
<%

	HttpSession sess = request.getSession(false);
	if(session==null || (session!=null && session.getAttribute("admin")==null)) {
		response.sendRedirect("deanlogin.jsp?msg=Authentication Failed.");
		return;
	}
	
	ArrayList<Faculty> facultyList = (ArrayList<Faculty>)sess.getAttribute("facultyList");
%>
<!-- <table style="width: 100%;">
	<tr>
		<td style="width: 20%;"><span
			style="font-family: 'Times New Roman', Times, serif; font-size: 20px; color: white;">Teacher's
				List</span></td>
		<td>	
		<td width="100%"><input type="text" onkeyup="searchdata(this.value)" placeholder="Search Teacher" value="" class="form-control" maxlength="50" required></td>
	</tr>
</table>
<script type="text/javascript">
	function searchdata(a) {
		var xmlhttp = new XMLHttpRequest();
		xmlhttp.onreadystatechange =function () {
			document.getElementById("loaddata").innerHTML=xmlhttp.responseText;
		}
		
		xmlhttp.open("get", "teachersearch?q="+a, true);
		xmlhttp.send();
	}
</script>
<hr style="border-width: 3px;"> -->
<style>
.ho:hover {
	background-color: #ffffff;
	color: black;
}
</style>
<div id="loaddata" class="viewbox">
	<table style="color: white;" class="table sortable"  id="mytable">
		<thead>
			<tr class="ho">
				<th scope="col">S.no</th>
				<th scope="col">Registration code</th>
				<th scope="col">Name</th>
				<th scope="col">E-mail</th>
				<th scope="col">Action</th>
			</tr>
		</thead>
		<tbody>
		<%long count=1; for(Faculty fac : facultyList) { %>
			<tr class="ho">
				<th scope="row"><%=count %></th>
				<th><%=fac.getRegistrationId() %></th>
				<td><%=fac.getFirstName()+" "+fac.getLastName() %></td>
				<td><%=fac.getEmail() %></td>
				<td><a href="adminfacultydetailview?facultyid=<%=fac.getRegistrationId()%>">ViewFullDetails</a></td>
			</tr>
			<%count++; } %>
		</tbody>
	</table>
	</div>
<jsp:include page="deanFooter.jsp"></jsp:include>