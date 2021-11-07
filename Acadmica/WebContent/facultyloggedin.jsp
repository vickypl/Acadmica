<%@page import="com.faculty.Faculty"%>
<jsp:include page="facultyHeader.jsp"></jsp:include>
<%
	HttpSession sess = request.getSession(false);
	if(sess==null || (sess!=null && sess.getAttribute("faculty")==null)) {
		response.sendRedirect("home.jsp?msg=Login Required");
		return;
	}
	
	String msg=null;
	String generatedCode = null;
	if(request.getAttribute("generatedCode")!=null) {
		generatedCode=(String)request.getAttribute("generatedCode");
	}
	
	if(request.getParameter("msg")!=null) {
		msg=request.getParameter("msg");
	}
	
	Faculty faculty = (Faculty)sess.getAttribute("faculty");
%>
<%if(msg!=null) {%>
	<div style="border-style: dotted;">
		<span style="font-size: 20px; color: red;"><%= msg %></span>
	</div>
<%} %>
<div class="viewbox">
	<table style="color: white; margin: 4% auto;">
		<tr style="border-style: solid; border-color: green; padding: 10px; color: red;">
			<td>Registration Code:</td>
			<td><%= faculty.getRegistrationId() %></td>
		</tr>
		<tr>
			<td>Mobile:</td>
			<td><%= faculty.getMobile() %></td>
		</tr>
		<tr>
			<td>E-mail:</td>
			<td><%= faculty.getEmail() %></td>
		</tr>
	</table>
</div>
<hr style="border-width: 3px;">
<%if(generatedCode!=null) {%>
	<div style="border: solid; border-color: white; width: 300px; height: 70px; color: lime; margin: 1% auto; text-align: center; font-size: 15px;">Your Generated Code is:<strong> <%=generatedCode %></strong>
	<br><small>Send this to student who requested so that he/she can new course.</small>
	</div>	
<%} %>
<a href="gencourseregcode"><button type="button" style="margin: 1% auto; opacity: 1; font-size: 20px;" class="btn btn-danger">Generate Course Registration Code</button></a>
<jsp:include page="facultyFooter.jsp"></jsp:include>