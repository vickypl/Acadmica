<%@page import="com.admin.Admin"%>
<jsp:include page="deanHeader.jsp"></jsp:include>
<%
	HttpSession sess = request.getSession(false);
	if(session==null || (session!=null && session.getAttribute("admin")==null)) {
		response.sendRedirect("deanlogin.jsp?msg=Authentication Failed.");
		return;
	}
	String msg=null;
	if(request.getParameter("msg")!=null) {
		msg=request.getParameter("msg");
	}
	
	Admin admin = (Admin)sess.getAttribute("admin");
	String name = admin.getFirstName()+" "+admin.getLastName();
	
%>
<%if(msg!=null) {%>
	<div style="border-style: dotted;">
		<span style="font-size: 20px; color: red;"><%= msg %></span>
	</div>
<%} %>
<div class="viewbox">
	<table style="color: white; margin: 4% auto; font-family: cursive; ">
		<div
			style="text-align: center; font-family: Georgia, 'Times New Roman', Times, serif; color: rgb(255, 0, 0); font-size: x-large;">
			WEL-COME ADMIN</div>
		<tr>
			<td>Name:</td>
			<td><%= name %></td>
		</tr>
		<tr>
			<td>Username:</td>
			<td><%=admin.getUsername() %></td>
		</tr>
		<tr>
			<td>E-mail:</td>
			<td><%= admin.getEmail() %></td>
		</tr>
	</table>
</div>
<hr style="border-width: 3px;">
<div style="text-align: center;">
	<a href="deanregistrationcode.jsp"><button
			style="width: 200px; font-size: 20px; background-color: transparent; border-radius: 5px; color: red;">Registration
			Code Generator</button></a>
</div>
<style>
button:hover {
	border-color: red;
}
</style>
<jsp:include page="deanFooter.jsp"></jsp:include>