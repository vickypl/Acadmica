<%@page import="com.admin.NoticeOnlyFaculty"%>
<%@page import="com.admin.AdminNotice"%>
<%@page import="java.util.ArrayList"%>
<%!@SuppressWarnings("unchecked")%>
<jsp:include page="deanHeader.jsp"></jsp:include>
<%
	HttpSession sess = request.getSession(false);
	if(sess==null || (sess!=null && sess.getAttribute("admin")==null)) {
		response.sendRedirect("home.jsp?msg=Login Required");
		return;
	}
	ArrayList<AdminNotice> noticeList = (ArrayList<AdminNotice>)sess.getAttribute("noticeList");
	ArrayList<NoticeOnlyFaculty> noticeFacultyList = (ArrayList<NoticeOnlyFaculty>)sess.getAttribute("noticeOnlyForFaculty");

	String msg = null;
	if(request.getParameter("msg")!=null) {
		msg = request.getParameter("msg");
	}
%>
<%if(msg!=null) {%>
<strong style="color: lime;"><%=msg %></strong>
<%} %>
<div class="viewbox">
	<div
		style="text-align: center; font-family: Georgia, 'Times New Roman', Times, serif; color: green; font-size: x-large;">
		<span><a href="deannoticeposter.jsp"><button
					class="btn btn-default">Post New Notice</button></a></span>Notice History<br>
	</div>
	<hr style="border-width: 2px;">
	<div style="height: 150px; overflow: auto">
	<label style="color: green">For All</label>
	<table width="100%" style="color: white;">
		<tr>
			<th>S/no.</th>
			<th>Notice</th>
			<th>Topic</th>
			<th>Posted On</th>
			<th>option</th>
		</tr>
		<%int count=1; for(AdminNotice not : noticeList) {%>
		<tr>
			<td><%=count %></td>
			<td><%=not.getNotice() %></td>
			<td><%=not.getTopic() %></td>
			<td><%=not.getPostedon() %></td>
			<td><a href="admindeletenotice?ide=<%=not.getId()%>&of=all"><button class="btn btn-danger">Delete</button></a></td>
		</tr>
		<%count++; } %>
	</table>
	</div>
		<hr>
	<div style="height: 150px; overflow: auto">
	<label style="color: green">For Faculty Only</label>
	<table width="100%" style="color: white;">
			<tr>
			<th>S/no.</th>
			<th>Notice</th>
			<th>Topic</th>
			<th>Posted On</th>
			<th>option</th>
		</tr>
		<%count=1; for(NoticeOnlyFaculty notf : noticeFacultyList) {%>
		<tr>
			<td><%=count %></td>
			<td><%=notf.getNotice() %></td>
			<td><%=notf.getTopic() %></td>
			<td><%=notf.getPostedOn() %></td>
			<td><a href="admindeletenotice?ide=<%=notf.getId()%>&of=faculty"><button class="btn btn-danger">Delete</button></a></td>
		</tr>
		<%count++; } %>
	</table>
	</div>
</div>
<jsp:include page="deanFooter.jsp"></jsp:include>