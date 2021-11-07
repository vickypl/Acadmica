<%@page import="com.admin.RegistrationCode"%>
<%@page import="java.util.ArrayList"%>
<%!@SuppressWarnings("unchecked")%>
<jsp:include page="deanHeader.jsp"></jsp:include>
<%
	HttpSession sess = request.getSession(false);
	if(sess==null || (sess!=null && sess.getAttribute("admin")==null)) {
		response.sendRedirect("home.jsp?msg=Login Required");
		return;
	}
	ArrayList<RegistrationCode> codeList = (ArrayList<RegistrationCode>)sess.getAttribute("pendingRegCodeList");
%>
<style>
	tr:hover {
		background-color: white;
		color: black;
	}
</style>
<div class="viewbox">
	 <div style="height: 450px; overflow: auto">
		<table style="margin: 1% auto; padding: 10px; color: white; margin: 4% auto; width: 100%;">
			<tr>
			<th>S.no.</th>
			<th>id</th>
			<th>code</th>
			<th>status</th>
			</tr>
			<%int count=1; for(RegistrationCode rc : codeList) {%>
			<tr>
			<td><%=count %></td>
			<td><%=rc.getId() %></td>
			<td><%=rc.getCode() %></td>
			<td><%=rc.getStatus() %></td>			
			</tr>
			<%count++;} %>
		</table>
	</div>
</div>
<jsp:include page="deanFooter.jsp"></jsp:include>