<jsp:include page="deanHeader.jsp"></jsp:include>
<%	
	HttpSession sess = request.getSession(false);
	if(sess==null || (sess!=null && sess.getAttribute("admin")==null)) {
		response.sendRedirect("deanlogin.jsp?msg=Authentication Failed.");
		return;
	}

	String msg=null;
	if(request.getParameter("msg")!=null) {
		msg=request.getParameter("msg");
	}
%>
<span style="color: red;">After Creating Latest Export Download here.</span>
<table class="table" style="color: white;">
<thead>
	<th>Exporter</th>
	<th>Downloader</th>
</thead>
<tbody>
<%if(msg!=null) {%>
	<tr style="color: lime;"><td><%=msg %></td></tr>
<%} %>
<tr>
<td><a href="createstudentexcel"><button class="btn btn-block">Export Students to Excel</button></a></td>
<td><a href="excelbackup\studentbackup.xlsx" download="studentbackup.xlsx"><button class="btn btn-success">Download Student Excel Backup</button></a></td>
</tr>
<tr>
<td><a href="createfacultyexcel"><button class="btn btn-block">Export Teachers to Excel</button></a></td>
<td><a href="excelbackup\facultybackup.xlsx" download="facultybackup.xlsx"><button class="btn btn-success">Download Faculty Excel Backup</button></a></td>
</tr>
<tr>
<td><a href="createcourseexcel"><button class="btn btn-block">Export Courses to Excel</button></a></td>
<td><a href="excelbackup\coursebackup.xlsx" download="coursebackup.xlsx"><button class="btn btn-success">Download Course Excel Backup</button></a></td>
</tr>
</tbody>
</table>
<jsp:include page="deanFooter.jsp"></jsp:include>