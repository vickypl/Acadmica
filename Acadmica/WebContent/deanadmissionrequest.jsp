<%@page import="com.admin.AdmissionRequestObject"%>
<%@page import="java.util.ArrayList"%>
<jsp:include page="deanHeader.jsp"></jsp:include>
<%! @SuppressWarnings("unchecked") %>
<%
	HttpSession sess = request.getSession(false);
	if(sess==null || (sess!=null && sess.getAttribute("admin")==null)) {
		response.sendRedirect("home.jsp?msg=Login Required");
		return;
	}

	ArrayList<AdmissionRequestObject> requestList = null;
	if(sess.getAttribute("admissionRequestList")!=null) {
		requestList=(ArrayList<AdmissionRequestObject>)sess.getAttribute("admissionRequestList");
	}
	
	String msg = null;
	if(request.getParameter("msg")!=null) {
		msg = request.getParameter("msg");
	}
	
%>
<%if(msg!=null){ %>
<strong style="color: red;"><%=msg %></strong>
<%} %>
<style>
tr, td, th {
	border-style: dotted;
	border-color: white;
}

tr:hover {
	background-color: white;
	color: black;
}
</style>
<div class="viewbox">
	<div style="height: 400px; overflow: auto">
		<div style="text-align: center; font-family: Georgia, 'Times New Roman', Times, serif; color: rgb(255, 0, 0); font-size: x-large;">
			Pending Requests</div>
	<table style="width: 100%; color: white; margin: 1% auto;">
		<tr>
			<th>S.no</th>
			<th>Full Name</th>
			<th>E-mail</th>
			<th>Mobile</th>
			<th>Role Type</th>
			<th>Action</th>
		</tr>
		<% if(requestList!=null) {
		int count=1;
		for(AdmissionRequestObject aro : requestList) {%>
		<tr>
			<td><%=count %></td>
			<td><%=aro.getFullname() %></td>
			<td><%=aro.getEmail() %></td>
			<td><%=aro.getMobile() %></td>
			<td><%=aro.getRole() %></td>
			<td><a href="deleteadrequest?id=<%=aro.getId() %>"><button class="btn btn-danger">Delete</button></a></td>
		</tr>
		<%count++;}
		} else {%>
		<tr>
		<td>No Request Available.</td>
		</tr>
		<%} %>
	</table>
	</div>
</div>
<jsp:include page="deanFooter.jsp"></jsp:include>