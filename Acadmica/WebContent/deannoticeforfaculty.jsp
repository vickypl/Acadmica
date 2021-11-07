<%@page import="com.admin.AdminNotice"%>
<%@page import="java.util.ArrayList"%>
<%! @SuppressWarnings("unchecked") %>
<jsp:include page="facultyHeader.jsp"></jsp:include>
<%
	HttpSession sess = request.getSession(false);
	if (sess == null || (sess != null && sess.getAttribute("faculty") == null)) {
		response.sendRedirect("home.jsp?msg=Login Required");
		return;
	}
	
	ArrayList<AdminNotice> noticeList = null;
	if(sess.getAttribute("adminNoticeList")!=null) {
		noticeList=(ArrayList<AdminNotice>)sess.getAttribute("adminNoticeList");
	}
	
%>
<div class="viewbox">
	<style>
tr, td, th {
	border-color: white;
	border-bottom: thick;
}

tr:hover {
	background-color: white;
	color: black;
}
</style>
		<div style="text-align: center; font-family: Georgia, 'Times New Roman', Times, serif; color: rgb(255, 0, 0); font-size: x-large;">
			Notice</div>
	<table style="width: 100%; color: white; margin: 1% auto;">
		<tr>
			<th>S.no</th>
			<th>Topic</th>
			<th>Notice</th>
			<th>Posted On</th>
		</tr>
		<%int count =1; for(AdminNotice notice : noticeList) {%>
		<tr>
			<td><%=count %></td>
			<td><%=notice.getTopic() %></td>
			<td><%=notice.getNotice() %></td>
			<td><%=notice.getPostedon() %></td>
		</tr>
		<%count++; } %>
	</table>
</div>
<jsp:include page="facultyFooter.jsp"></jsp:include>