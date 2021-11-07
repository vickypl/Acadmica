<%@page import="com.faculty.DoubtResponse"%>
<%@page import="java.util.ArrayList"%>
<jsp:include page="studentHeader.jsp"></jsp:include>
<%! @SuppressWarnings("unchecked") %>
<%
	HttpSession sess = request.getSession(false);
	if(sess==null || (sess!=null && sess.getAttribute("student")==null)) {
		response.sendRedirect("home.jsp?msg=Login Required");
		return;
	}
	
	ArrayList<DoubtResponse> doubtResponsesList = new ArrayList<DoubtResponse>();
	if(sess.getAttribute("doubtResponsesList")!=null) {
		doubtResponsesList =  (ArrayList<DoubtResponse>)sess.getAttribute("doubtResponsesList");
	}
%>
<div class="viewbox">
	<span
		style="font-size: medium; font-family: 'Times New Roman', Times, serif; margin-left: 350px; color: green">Doubts
		Responded</span>
	<div style="height: 450px; overflow: auto">
	<table style="margin: 0% auto; color: blanchedalmond;" width="100%">
		<tr>
			<th>id</th>
			<th>Doubt</th>
			<th>Responded on</th>
			<th>Responded by</th>
		</tr>
		<% for(DoubtResponse dr : doubtResponsesList) { %>
		<tr>
			<td style="font-size: 15px;"><%= dr.getId() %></td>
			<td style="font-size: 15px;"><%= dr.getResponse() %></td>
			<td style="font-size: 15px;"><%= dr.getRespondedOn() %></td>
			<td style="background-color: black; color: greenyellow; font-size: 20px;"><%= dr.getRespondedBy() %>&nbsp;sir</td>
		</tr>
		<%} %>
	</table>
	</div>
</div>
<jsp:include page="studentFooter.jsp"></jsp:include>