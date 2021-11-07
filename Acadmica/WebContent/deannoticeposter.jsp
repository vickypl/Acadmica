<jsp:include page="deanHeader.jsp"></jsp:include>
<%
	HttpSession sess = request.getSession(false);
	if(sess==null || (sess!=null && sess.getAttribute("admin")==null)) {
		response.sendRedirect("home.jsp?msg=Login Required");
		return;
	}
	String msg = null;
	if(request.getParameter("msg")!=null) {
		msg= request.getParameter("msg");
	}
%>
<a href="deannoticehistory.jsp"><button class="btn-info btn-block">Notice
		History</button></a>
<hr style="border-width: 2px;">
<div class="viewbox">
	<div style="text-align: center; font-family: Georgia, 'Times New Roman', Times, serif; color: green; font-size: x-large;">
		Write Your Notice Here<br> <small
			style="font-size: 15px; color: red;">Note:whatever you post
			here will be visible to all available Members.</small>
			<%if(msg!=null) {%>
			 <small style="font-size: 20px; color: blue;"><%=msg %></small>
			<%} %>
	</div>
	<form action="adminnotice" method="POST" class="form-group" style="padding: 1px;">
		<label style="color: white;">Notice To</label>
		<select class="form-control" name="noticeto" value="">
		<option value="all">For Everyone</option>
		<option value="faculty">Faculty</option>
		</select>
		<input style="margin: 1% auto;" type="text" name="topic"
			class="form-control" placeholder="Topic" value="" maxlength="15"
			required>
		<textarea name="notice" cols="100%" rows="5" class="form-control"
			style="margin: 1% auto; font-size: 20px;" name="notice" placeholder="Write Your Notice Here" maxlength="499" ></textarea>
		<button type="submit" style="padding: 10px; margin: 1% auto;"
			class="btn-success btn-block">Post Notice</button>
	</form>
</div>
<jsp:include page="deanFooter.jsp"></jsp:include>