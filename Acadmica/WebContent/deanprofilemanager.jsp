<%@page import="com.admin.Admin"%>
<jsp:include page="deanHeader.jsp"></jsp:include>
<%
	HttpSession sess = request.getSession(false);
	if(session==null || (session!=null && session.getAttribute("admin")==null)) {
		response.sendRedirect("deanlogin.jsp?msg=Authentication Failed.");
		return;
	}
	
	Admin admin = (Admin)sess.getAttribute("admin");
	
	String msg=null;
	if(request.getParameter("msg")!=null) {
		msg=request.getParameter("msg");
	}
%>
<div class="viewbox">
	<form action="adminprofileupdate" method="post" style="color: white; padding: 10px; width: 50%; margin: 1% auto;">
		<table width="100%;">
		<h3 style="text-align: center;">Update Your Details here </h3>
			<% if(msg!=null){%>
			<tr><td colspan="2" style="color: red;"><%=msg %></td></tr>
			<%} %>
			<tr>
				<td><input type="text" class="form-control" placeholder="First Name" name="fname" value="<%=admin.getFirstName() %>" maxlength="20" required></td>
				<td><input type="text" class="form-control" placeholder="Last Name" name="lname" value="<%=admin.getLastName() %>" maxlength="20" required></td>
			</tr>
			<tr>
				<td colspan="2"><input type="text" class="form-control" placeholder="Username" name="username" value="<%=admin.getUsername() %>" maxlength="20" required></td>
			</tr>
			<tr>
				<td colspan="2"><input type="text" class="form-control" placeholder="E-mail" name="email" value="<%=admin.getEmail() %>" maxlength="50" required></td>
			</tr>
			<tr>
				<td colspan="2"><input type="password" class="form-control" placeholder="Password" name="password" maxlength="20" required></td>
			</tr>
			<tr>
				<td colspan="2"><input type="password" class="form-control" placeholder="Confirm Password" name="cpassword" maxlength="20" required></td>
			</tr>
			<tr>
				<td colspan="2" style="color: white;"><input type="checkbox" required> &nbsp;I Agree to the terms and Conditions..</td>
			</tr>
		</table>
		<button type="submit" style="background-color: green;"
			class="btn btn-block">Update</button>
	</form>
</div>
<jsp:include page="deanFooter.jsp"></jsp:include>