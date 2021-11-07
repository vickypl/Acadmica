<%@page import="com.faculty.Doubts"%>
<%@page import="java.util.ArrayList"%>
<%! @SuppressWarnings("unchecked") %>
<jsp:include page="facultyHeader.jsp"></jsp:include>
<%
	HttpSession sess = request.getSession(false);
	if (sess == null || (sess != null && sess.getAttribute("faculty") == null)) {
		response.sendRedirect("home.jsp?msg=Login Required");
		return;
	}

	ArrayList<Doubts> doubtsList = new ArrayList<Doubts>();
	if (sess.getAttribute("doubtsList") != null) {
		doubtsList = (ArrayList<Doubts>) sess.getAttribute("doubtsList");
	}
	
	String msg=null;
	if(request.getParameter("msg")!=null) {
		msg =request.getParameter("msg");
	}
	
%>
<div class="viewbox">
	<span
		style="font-size: medium; font-family: 'Times New Roman', Times, serif; margin-left: 350px; color: green">Doubts
		Recived</span>&nbsp;&nbsp;<% if(msg!=null) { %><span style="color: red;"><%= msg %></span><%} %>
	<div style="height: 350px; overflow: auto">
	<table style="margin: 1% auto; width: 100%; color: #e2be2d;">
		<tr>
			<th>Doubts</th>
			<th>Posted by</th>
			<th>Posted On</th>
			<th>Option</th>
		</tr>
		<% for(Doubts doubt : doubtsList) { 
				if(doubt.getDoubt()!=null) {
		%>	
		<tr>
			<td style="font-size: 19px;"><span class="dot"></span> <%= doubt.getId() %>>> <%= doubt.getDoubt() %></td>
			<td><%=doubt.getPostedBy() %></td>
			<td><%=doubt.getPostedOn() %></td>
			<td><a href="doubtresponse?id1=<%=doubt.getId() %>"><button type="submit" style="width: fit-content; background-color: transparent; color: green; border-radius: 4px;">Response</button></a></td>
		</tr>
		<% } else {
				%>
					<h5>No more doubts</h5>
				<%
				break;
			}
		} 
		%>
	</table>
	</div>
</div>
<jsp:include page="facultyFooter.jsp"></jsp:include>