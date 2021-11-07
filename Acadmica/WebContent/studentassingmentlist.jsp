<%@page import="java.util.Set"%>
<%@page import="java.util.HashMap"%>
<%@page import="com.faculty.Assignment"%>
<%@page import="java.util.ArrayList"%>
<%! @SuppressWarnings("unchecked") %>
<jsp:include page="studentHeader.jsp"></jsp:include>
<%
	HttpSession sess = request.getSession(false);
	if (sess == null || (sess != null && sess.getAttribute("student") == null)) {
		response.sendRedirect("home.jsp?msg=Login Required");
		return;
	}

	HashMap<String, ArrayList<Assignment>> assignmentMap = new HashMap<String, ArrayList<Assignment>>();
	if (sess.getAttribute("facultyAssignmentMap") != null) {
		assignmentMap = (HashMap<String, ArrayList<Assignment>>) sess.getAttribute("facultyAssignmentMap");
	}
	
%>
<span
	style="margin-left: 30px; font-family: 'Times New Roman', Times, serif; font-size: 30px; color: white;">Assignment's</span>
<hr style="border-width: 3px;">
<style>
tr:hover {
	background-color: #ffffff;
	color: black;
}
</style>
<div class="viewbox">
	<div style="height: 350px; overflow: auto">
		<table style="color: white;" class="table table-dark">
			<thead>
				<tr>
					<th scope="col">S/No.</th>
					<th scope="col">Assignment</th>
					<th scope="col">AssingedBy</th>
					<th scope="col">Assignment Date</th>
					<th scope="col">Submission Date</th>
					<th scope="col">Status</th>
				</tr>
			</thead>
			<tbody>
		<%
			int count=1;
			Set<String> keys = assignmentMap.keySet();
			for(String key : keys) {
				ArrayList<Assignment> assignmentList = assignmentMap.get(key);
				for(Assignment as : assignmentList) {
			%>
				<tr>
					<th scope="row"><%=count%></th>
					<td><%=as.getAssingment()%></td>
					<td><%=as.getAssingedBy()%></td>
					<td><%=as.getAssDate()%></td>
					<td><%=as.getSubDate()%></td>
					<td><input type="checkbox" value="checked" checked />Submitted</td>
				</tr>
			<% count++; }
			} %>
			</tbody>
		</table>
	</div>
</div>
<jsp:include page="studentFooter.jsp"></jsp:include>