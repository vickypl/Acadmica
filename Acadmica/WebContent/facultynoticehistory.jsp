<%@page import="com.faculty.FacultyNotice"%>
<%@page import="java.util.ArrayList"%>
<jsp:include page="facultyHeader.jsp"></jsp:include>
<%! @SuppressWarnings("unchecked") %>
<%
	HttpSession sess = request.getSession(false);
	if(sess==null || (sess!=null && sess.getAttribute("faculty")==null)) {
		response.sendRedirect("home.jsp?msg=Login Required");
		return;
	}
	
	String msg=null;
	if(request.getParameter("msg")!=null) {
		msg =request.getParameter("msg");
	}
	
	ArrayList<FacultyNotice> noticeList = (ArrayList<FacultyNotice>)sess.getAttribute("noticeList");
%>
<div class="viewbox">
	<div
		style="text-align: center; font-family: Georgia, 'Times New Roman', Times, serif; color: green; font-size: x-large;">
		<span><a href="facultynoticeposter.jsp"><button
					class="btn btn-default">Post New Notice</button></a></span>Notice History<br>
	</div>
	&nbsp;&nbsp;<% if(msg!=null) { %><span style="color: red;"><%= msg %></span><%} %>
	<hr style="border-width: 2px;">
	<table width="100%" style="color: white;">
		<tr>
			<th>S/no.</th>
			<th>Notice</th>
			<th>Topic</th>
			<th>Posted On</th>
			<th>option</th>
		</tr>
		<% int count=1; for(FacultyNotice fn : noticeList) {%>
		<tr>
			<td><%= count %></td>
			<td><%= fn.getNotice() %></td>
			<td><%= fn.getTopic() %></td>
			<td><%= fn.getPostedOn() %></td>
			<td><a href="deletefacultynotice?id=<%=fn.getId()%>"><button class="btn btn-danger">Delete</button></a></td>
		</tr>
		<%} %>
	</table>
</div>
<jsp:include page="facultyFooter.jsp"></jsp:include>