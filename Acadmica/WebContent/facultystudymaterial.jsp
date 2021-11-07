<%@page import="com.faculty.FacultyFiles"%>
<%@page import="java.util.ArrayList"%>
<jsp:include page="facultyHeader.jsp"></jsp:include>
<%!@SuppressWarnings("unchecked")%>
<%
	HttpSession sess = request.getSession(false);
	if (sess == null || (sess != null && sess.getAttribute("faculty") == null)) {
		response.sendRedirect("home.jsp?msg=Login Required");
		return;
	}

	ArrayList<FacultyFiles> facultyFiles = new ArrayList<FacultyFiles>();
	if (sess.getAttribute("facultyFiles") != null) {
		facultyFiles = (ArrayList<FacultyFiles>) sess.getAttribute("facultyFiles");
	}

	String msg = null;
	if (request.getParameter("msg") != null) {
		msg = request.getParameter("msg");
	}
%>
<style>
th {
	color: rgb(12, 222, 241);
}

tr:hover {
	background-color: black;
	color: red;
}
</style>
<div class="viewbox">
	<table>
		<tr>
			<form action="facultyfileupload" method="post" enctype="multipart/form-data">
				<td style="color: white;"><i class="fa fa-file"></i></td>
				<td><input class="form-control" type="text" name="fileName"
					placeholder="File Name" required></td>
				<td><input class="form-control" type="file" name="file"
					required></td>
				<td><button class="btn btn-success">Upload New File</button></td>
			</form>
			<%
				if (msg != null) {
			%>
			<td style="color: red;"><%=msg%></td>
			<%
				}
			%>
		</tr>
	</table>
	<hr style="border-width: 3px;">
	<span
		style="font-size: x-large; font-family: 'Times New Roman', Times, serif; margin-left: 350px; color: white">Available
		Files</span>
	<div style="height: 350px; overflow: auto">
		<table style="border-style: solid;" class="table table-striped">
			<thead>
				<tr>
					<th>id</th>
					<th>File Name</th>
					<th>File Type</th>
					<th>File Size</th>
					<th>Uploaded Date</th>
					<th>Action</th>
				</tr>
			</thead>
			<tbody>
				<%
					int count = 1;
					for (FacultyFiles ff : facultyFiles) {
						if (ff.getTitle() != null) {
				%>
				<tr>
					<td><%=count%></td>
					<td><%=ff.getTitle()%></td>
					<td><%=ff.getFileType()%></td>
					<td><%=ff.getFileSize()%>mb</td>
					<td><%=ff.getUploadDate()%></td>
					<td><a href="uploadedFiles\<%=ff.getUrl()%>"><button
								class="btn btn-success">Open</button></a>&nbsp; 
								<a href="deletefacultyfile?url=<%= ff.getUrl() %>"><button
								class="btn btn-success">Delete</button></a></td>
				</tr>
				<%
					}
						count++;
					}
				%>
			</tbody>
		</table>
	</div>
</div>
<jsp:include page="facultyFooter.jsp"></jsp:include>