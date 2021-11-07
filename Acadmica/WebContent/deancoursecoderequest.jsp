<%@page import="com.admin.CourseCodeRequests"%>
<%@page import="java.util.ArrayList"%>
<%!@SuppressWarnings("unchecked")%>
<jsp:include page="deanHeader.jsp"></jsp:include>
<%
	HttpSession sess = request.getSession(false);
	if(sess==null || (sess!=null && sess.getAttribute("admin")==null)) {
		response.sendRedirect("home.jsp?msg=Login Required");
		return;
	}
	
	ArrayList<CourseCodeRequests> courseCodeReqList = null;
	if(sess.getAttribute("courseCodeRequestsList")!=null) {
		courseCodeReqList=(ArrayList<CourseCodeRequests>)sess.getAttribute("courseCodeRequestsList");
	}
	
	String msg = null;
	if(request.getParameter("msg")!=null) {
		msg = request.getParameter("msg");
	}
%>
<%if(msg!=null) {%>
<strong style="color: lime;"><%=msg %></strong>
<%} %>
<div class="viewbox">
	<div style="height: 350px; overflow: auto">
	<table style="width: 100%; color: white;">
	<tr>
	<th>S.no.</th>
	<th>Request From(id)</th>
	<th>Request Msg</th>
	<th>Status</th>
	<th>Option</th>
	</tr>
	<%int count=1; for(CourseCodeRequests cc : courseCodeReqList) {%>
	<tr>
	<td><%=count %></td>
	<td><%=cc.getRequestId() %></td>
	<td><%=cc.getRequest() %></td>
	<td><%=cc.getStatus() %></td>
	<td><a href="deletecoderequest?id=<%=cc.getId() %>"><button class="btn btn-danger">Delete</button></a></td>
	</tr>
	<%count++;} %>
	</table>
	</div>
</div>
<jsp:include page="deanFooter.jsp"></jsp:include>