<%@page import="com.faculty.Faculty"%>
<%@page import="com.faculty.Doubts"%>
<%@page import="java.util.ArrayList"%>
<%!@SuppressWarnings("unchecked")%>
<jsp:include page="studentHeader.jsp"></jsp:include>
<%
	HttpSession sess = request.getSession(false);
	if (sess == null
			|| (sess != null && sess.getAttribute("student") == null)) {
		response.sendRedirect("home.jsp?msg=Login Required");
		return;
	}

	ArrayList<Faculty> facultyList = new ArrayList<Faculty>();
	if (sess.getAttribute("facultyList") != null) {
		facultyList = (ArrayList<Faculty>) sess
				.getAttribute("facultyList");
	}

	ArrayList<Doubts> doubtList = new ArrayList<Doubts>();
	if (sess.getAttribute("doubtList") != null) {
		doubtList = (ArrayList<Doubts>) sess.getAttribute("doubtList");
	}

	String msg = null;
	if (request.getParameter("msg") != null) {
		msg = request.getParameter("msg");
	}
%>
<div class="viewbox">
	<a href="studentdoubtresponse.jsp"><button class="btn-block">Doubt Responses</button></a>
	<% if(msg!=null) { %>
		<span style="color: green; font-style: bold;">&nbsp;<%= msg %></span>
	<%} %>
	<span style="font-size: medium; font-family: 'Times New Roman', Times, serif; margin-left: 350px; color: green">Doubts history</span>
	<div style="height: 200px; overflow: auto">
		<table style="color: blanchedalmond;">
			<%
				for (Doubts d : doubtList) {
			%>
			<tr>
				<td style="font-size: 20px;"><span class="dot"></span> <%=d.getId()%>
					>> <%=d.getDoubt()%></td>
			</tr>
			<%
				}
			%>
		</table>
	</div>
	<%
		if (msg != null) {
	%>
	<div style="border-style: solid; background-color: lime;">
		<span style="color: green; font-size: 15px;"><%=msg%></span>
	</div>
	<%
		}
	%>
	<form action="doubthandle" method="POST">
		<select name="facultyId" class="form-control">
			<%
				for (Faculty f : facultyList) {
			%>
			<option value="<%=f.getRegistrationId()%>"><%=f.getFirstName() + " " + f.getLastName()%></option>
			<%
				}
			%>
		</select> <small class="form-text text-muted">Select Name of Faculty You want to ask Doubt From.</small>
		<textarea style="margin: 1% auto;" class="form-control" name="doubt" placeholder="Ask Your Doubt here.." maxlength="240" rows="3"></textarea>
		<button style="margin-left: 350px;" type="submit" class="btn btn-success me-2">Ask Doubt</button>
	</form>
</div>
<jsp:include page="studentFooter.jsp"></jsp:include>