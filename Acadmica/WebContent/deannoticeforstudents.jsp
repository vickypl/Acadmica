<%@page import="com.admin.AdminNotice"%>
<%@page import="java.util.ArrayList"%>
<jsp:include page="studentHeader.jsp"></jsp:include>
<%! @SuppressWarnings("unchecked") %>
<%
	HttpSession sess = request.getSession(false);
	if(sess==null || (sess!=null && sess.getAttribute("student")==null)) {
		response.sendRedirect("home.jsp?msg=Login Required");
		return;
	}
	
	ArrayList<AdminNotice> adminNoticeList = new ArrayList<AdminNotice>();
	if(sess.getAttribute("adminNoticeList")!=null) {
		adminNoticeList =  (ArrayList<AdminNotice>)sess.getAttribute("adminNoticeList");
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
	<table style="width: 100%; color: white; margin: 1% auto;">
		<div
			style="text-align: center; font-family: Georgia, 'Times New Roman', Times, serif; color: rgb(255, 0, 0); font-size: x-large;">
			Notice from dean</div>
		<tr>
			<th>S.no</th>
			<th>Notice</th>
			<th>Topic</th>
			<th>Posted on</th>
		</tr>
		<%
		if(adminNoticeList.size()>0) {
		int count=1; 
		for(AdminNotice an : adminNoticeList) {%>
		<tr>
			<td><%=count %></td>
			<td><%=an.getNotice() %></td>
			<td><%=an.getTopic() %></td>
			<td><%=an.getPostedon() %></td>
		</tr>
		<%count++;} 
		}%>
	</table>
</div>
<jsp:include page="studentFooter.jsp"></jsp:include>