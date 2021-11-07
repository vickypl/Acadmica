<%@page import="com.student.StudentFiles"%>
<%@page import="java.util.ArrayList"%>
<jsp:include page="studentHeader.jsp"></jsp:include>
<%! @SuppressWarnings("unchecked") %>
<%
	HttpSession sess = request.getSession(false);
	if(sess==null || (sess!=null && sess.getAttribute("student")==null)) {
		response.sendRedirect("home.jsp?msg=Login Required");
		return;
	}
	
	ArrayList<StudentFiles> studentFilesList = new ArrayList<StudentFiles>();
	if(sess.getAttribute("studentFilesList")!=null) {
		studentFilesList =  (ArrayList<StudentFiles>)sess.getAttribute("studentFilesList");
	}
	
	String msg=null;
	if(request.getParameter("msg")!=null) {
		msg=request.getParameter("msg");
	}
	
%>
<div class="viewbox">
		<table>
			<tr>
			<form action="studentfileupload" method="POST" enctype="multipart/form-data">
				<td style="color: white;"><i class="fa fa-file"></i></td>
				<td><input class="form-control" type="text" name="fileName" placeholder="File Name" required></td>
				<td><input class="form-control" type="file" name="file" required></td>
				<td><button class="btn btn-success">Upload New File</button></td>
			</form>
			<% if(msg!=null) {%>
				<td style="color: red;"><%= msg %></td>
			<%} %>
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
				<th>S no.</th>
				<th>File Name</th>
				<th>File Type</th>
				<th>File Size</th>
				<th>Uploaded Date</th>
				<th>Action</th>
			</tr>
		</thead>
		<tbody>
		<%int count=1; for(StudentFiles sf : studentFilesList) { %>
			<tr>
				<td><%= count %></td>
				<td><%= sf.getTitle()%></td>
				<td><%= sf.getFileType()%></td>
				<td><%= sf.getFileSize()%></td>
				<td><%= sf.getUploadDate()%></td>
				<td><a href="uploadedFiles\<%= sf.getUrl() %>"><button class="btn btn-success">Open</button></a>&nbsp;
					<a href="deletefile?url=<%= sf.getUrl() %>" ><button class="btn btn-success">Delete</button></a></td>
			</tr>
			<%count++; } %>
		</tbody>
	</table>
	</div>
</div>
<jsp:include page="studentFooter.jsp"></jsp:include>