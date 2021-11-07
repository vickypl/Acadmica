<%@page import="java.util.Set"%>
<%@page import="com.faculty.FacultyNotice"%>
<%@page import="java.util.ArrayList"%>
<%@page import="java.util.HashMap"%>
<%! @SuppressWarnings("unchecked") %>
<jsp:include page="studentHeader.jsp"></jsp:include>
<%
	HttpSession sess = request.getSession(false);
	if (sess == null || (sess != null && sess.getAttribute("student") == null)) {
		response.sendRedirect("home.jsp?msg=Login Required");
		return;
	}

	HashMap<String, ArrayList<FacultyNotice>> noticeMap = new HashMap<String, ArrayList<FacultyNotice>>();
	if (sess.getAttribute("facultyNoticesMap") != null) {
		noticeMap = (HashMap<String, ArrayList<FacultyNotice>>) sess.getAttribute("facultyNoticesMap");
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
			Notice from faculty</div>
		<tr>
			<th>S.no</th>
			<th>Notice</th>
			<th>PostedBy</th>
			<th>Date</th>
		</tr>
		<%int count=1;
			Set<String> keys = noticeMap.keySet();
			for(String key : keys) {
				ArrayList<FacultyNotice> noticeList = noticeMap.get(key);
				for(FacultyNotice notice : noticeList) {
		%>
		<tr>
			<td><%=count %></td>
			<td><%=notice.getNotice() %>.</td>
			<td><%=notice.getPostedBy() %></td>
			<td><%=notice.getPostedOn() %></td>
		</tr>
		<%count++;	}
			}
		%>
	</table>
</div>
<jsp:include page="studentFooter.jsp"></jsp:include>