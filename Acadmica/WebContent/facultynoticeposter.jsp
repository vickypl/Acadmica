<jsp:include page="facultyHeader.jsp"></jsp:include>
<%
	HttpSession sess = request.getSession(false);
	if (sess == null || (sess != null && sess.getAttribute("faculty") == null)) {
		response.sendRedirect("home.jsp?msg=Login Required");
		return;
	}

	String msg=null;
	if(request.getParameter("msg")!=null) {
		msg =request.getParameter("msg");
	}
%>
<a href="facultynoticehistory.jsp"><button class="btn-info btn-block">Notice
		History</button></a>
<hr style="border-width: 2px;">
<div class="viewbox">
&nbsp;&nbsp;<% if(msg!=null) { %><span style="color: red;"><%= msg %></span><%} %>
	<div
		style="text-align: center; font-family: Georgia, 'Times New Roman', Times, serif; color: green; font-size: x-large;">
		Write Your Notice Here<br> <small
			style="font-size: 15px; color: red;">Note:whatever you post
			here will be visible to all your students.</small>
	</div>
	<form action="facultynoticeposter" method="post" class="form-group" style="padding: 1px;">
		<input style="margin: 1% auto;" type="text" name="topic"
			class="form-control" placeholder="Topic" value="" maxlength="15"
			required>
		<textarea name="notice" cols="100%" rows="5" class="form-control"
			style="font-size: 20px;" placeholder="Write Your Thoughts Here"></textarea>
		<button type="submit" style="padding: 10px; margin: 1% auto;"
			class="btn-success btn-block">Post Notice</button>
	</form>
</div>
<jsp:include page="facultyFooter.jsp"></jsp:include>