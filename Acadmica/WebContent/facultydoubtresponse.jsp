<%@page import="com.faculty.Doubts"%>
<jsp:include page="facultyHeader.jsp"></jsp:include>
<%
	HttpSession sess = request.getSession(false);
	if (sess == null || (sess != null && sess.getAttribute("faculty") == null)) {
		response.sendRedirect("home.jsp?msg=Login Required");
		return;
	}
	Doubts doubt = (Doubts)request.getAttribute("doubt");
	String msg=null;
	if(request.getParameter("msg")!=null) {
		msg =request.getParameter("msg");
	}
%>
<div class="viewbox">
	<span
		style="font-size: medium; font-family: 'Times New Roman', Times, serif; margin-left: 350px; color: green">Doubts
		history</span>&nbsp;&nbsp;<% if(msg!=null) { %><span style="color: red;"><%= msg %></span><%} %>
	<table style="color: blanchedalmond;">
		<tr>
			<td><span style="color: green;">Posted by: <%= doubt.getPostedBy() %></span></td>
		</tr>
		<tr>
			<td style="font-size: 23px;"><span class="dot"></span>>> <%= doubt.getDoubt() %></td>
		</tr>
	</table>
	<form action="doubtresponse" method="post">
		<input type="hidden" name="id" value="<%=doubt.getId() %>"/>
		<input type="hidden" name="studentid" value="<%=doubt.getStudentId() %>"/>
		<input type="hidden" name="postedby" value="<%=doubt.getPostedBy() %>"/>
		<textarea style="margin: 1% auto;" class="form-control" placeholder="Write Your Solution here.." rows="3" maxlength="250" name="solution" value=""></textarea>
		<button style="margin-left: 350px;" type="submit" class="btn btn-success me-2">Submit Response</button>
	</form>
</div>
<jsp:include page="facultyFooter.jsp"></jsp:include>