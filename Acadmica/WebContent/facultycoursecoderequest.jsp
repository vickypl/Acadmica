<%@page import="com.faculty.SubjectRegCodeRequest"%>
<%@page import="java.util.ArrayList"%>
<%!@SuppressWarnings("unchecked")%>
<jsp:include page="facultyHeader.jsp"></jsp:include>
<%
	HttpSession sess = request.getSession(false);
	if (sess == null || (sess != null && sess.getAttribute("faculty") == null)) {
		response.sendRedirect("home.jsp?msg=Login Required");
		return;
	}
	
	ArrayList<SubjectRegCodeRequest> subjectCourseCodeRequestList = null;
	if(sess.getAttribute("subjectCourseCodeRequestList")!=null) {
		subjectCourseCodeRequestList = (ArrayList<SubjectRegCodeRequest>)sess.getAttribute("subjectCourseCodeRequestList");
	}
	
	String msg = null;
	if(request.getParameter("msg")!=null) {
		msg = request.getParameter("msg");
	}
	
%>
<%if(msg!=null) {%>
	<span style="color: lime;"><%=msg %></span>
<%} %>
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
		<div style="text-align: center; font-family: Georgia, 'Times New Roman', Times, serif; color: rgb(255, 0, 0); font-size: x-large;">Pending Code Requests</div>
	 <tr>
	 <th>S.no.</th>
	 <th>For Course</th>
	 <th>Student id</th>
	 <th>Student Name</th>
	 <th>Option</th>
	 </tr>
	 <%int count=1; for(SubjectRegCodeRequest srcr : subjectCourseCodeRequestList) {%>
	 <tr>
	 <td><%=count %></td>
	 <td><%=srcr.getCourseName() %></td>
	 <td><%=srcr.getRegid() %></td>
	 <td><%=srcr.getStudentName() %></td>
	 <td><a href="delsubcoderequest?id=<%= srcr.getId()%>"><button class="btn btn-danger">Delete</button></a></td>
	 </tr>
	 <%count++; } %>
	</table>
</div>
<jsp:include page="facultyFooter.jsp"></jsp:include>