<%@page import="com.admin.RegistrationCode"%>
<%@page import="java.util.ArrayList"%>
<%! @SuppressWarnings("unchecked") %>
<jsp:include page="deanHeader.jsp"></jsp:include>
<%

	HttpSession sess = request.getSession(false);
	if(sess==null || (sess!=null && sess.getAttribute("admin")==null)) {
		response.sendRedirect("home.jsp?msg=Login Required");
		return;
	}
	
	ArrayList<RegistrationCode> pendingSubjectRegCodeList = null;
	if(sess.getAttribute("pendingSubjectRegCodeList")!=null) {
		pendingSubjectRegCodeList=(ArrayList<RegistrationCode>)sess.getAttribute("pendingSubjectRegCodeList");
	}
%>
<span
	style="margin-left: 30px; font-family: 'Times New Roman', Times, serif; font-size: 30px; color: white;">List of Pending Course Request Codes.</span>
<hr style="border-width: 3px;">
<style>
.ho:hover {
	background-color: #ffffff;
	color: black;
}
</style>
<div class="viewbox">
	<div style="height: 400px; overflow: auto">
	<table style="color: white;" class="table table-dark">
		<thead>
			<tr class="ho">
				<th scope="col">S.no.</th>
				<th scope="col">Code</th>
				<th scope="col">Status</th>
			</tr>
		</thead>
		<tbody>
		<%int count=1; for(RegistrationCode rc : pendingSubjectRegCodeList) {%>
			<tr class="ho">
				<td><%=count %></td>
				<td><%=rc.getCode() %></td>
				<td><%=rc.getStatus() %></td>
			</tr>
		<%count++; } %>
		</tbody>
	</table>
	</div>
</div>
<jsp:include page="deanFooter.jsp"></jsp:include>