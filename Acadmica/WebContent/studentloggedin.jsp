<jsp:include page="studentHeader.jsp"></jsp:include>
<%@ page import="com.student.Student" %>
<%
	HttpSession sess = request.getSession(false);
	if(sess==null || (sess!=null && sess.getAttribute("student")==null)) {
		response.sendRedirect("home.jsp?msg=Login Required");
		return;
	}
	Student student = (Student)sess.getAttribute("student");
	String msg=null;
	if(request.getParameter("msg")!=null) {
		msg=request.getParameter("msg");
	}
%>
<%if(msg!=null) {%>
	<div style="border-style: dotted;">
		<span style="font-size: 20px; color: red;"><%= msg %></span>
	</div>
<%} %>
<div class="viewbox">
	<table style="color: white; margin: 4% auto; font-family: cursive;">
		<tr style="border-style: solid; border-color: green; padding: 10px; color: red;">
			<td>Registration Code:</td>
			<td><%= student.getRegistrationId() %></td>
		</tr>
		<tr>
			<td>Mobile:</td>
			<td><%= student.getMobile() %></td>
		</tr>
		<tr>
			<td>E-mail:</td>
			<td><%= student.getEmail() %></td>
		</tr>
	</table>
</div>
<hr style="border-width: 3px;">
<!-- <a href="#"><button type="button" style="opacity: 1; font-size: 40px;" class="btn btn-primary btn-block">Disscussion Corner</button></a> -->
<jsp:include page="studentFooter.jsp"></jsp:include>