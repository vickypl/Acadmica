<%@page import="com.course.Course"%>
<jsp:include page="facultyHeader.jsp"></jsp:include>
<%
	HttpSession sess = request.getSession(false);
	if (sess == null || (sess != null && sess.getAttribute("faculty") == null)) {
		response.sendRedirect("home.jsp?msg=Login Required");
		return;
	}
	
	String msg = null;
	if(request.getParameter("msg")!=null) {
		msg = request.getParameter("msg");
	}
	
	Course course = null;
	if(sess.getAttribute("selectedCourse")!=null) {
		course=(Course) sess.getAttribute("selectedCourse");
	}
%>
<div class="viewbox">
	<div
		style="background-color: green; margin: 0% auto; width: 70%; height: 8%; border-color: white; border-bottom-style: solid; border-radius: 10px;">
		<p
			style="color: white; text-align: center; font-family: cursive; font-size: 30px;">
			Selected Course:
			<%if(course!=null) {%><%=course.getCourseName()%><%} %></p>
	</div>
	<%if(msg!=null) { %>
		<div class="alert alert-success" style="padding: 10px; margin-top: 10px;">
	  			<strong><%=msg %></strong>
		</div>
	<%} %>
	<div>
		<table style="margin: 7% auto; width: 100%; color: white;">
			<tr>
				<th>File Upload</th>
				<th>Video Upload</th>
			</tr>
			<tr>
				<td>
					<form action="facultycoursemanagement" method="post" enctype="multipart/form-data" style="padding: 20px;">
						<input type="hidden" name="coursecode" value="<%if(course!=null) {%><%=course.getCourseId() %><%} %>" />
						<input type="hidden" name="item" value="filetype" />
						<label>File Title</label> 
						<input type="text" name="title" placeholder="File Title" class="form-control" value="" required="required" maxlength="299"  required="required"/> 
						<label class="form-label" for="customFile">Select pdf | text | docx</label>
						<input type="file" name="fileitem" class="form-control" data-max-size="50000" />
						<br>
						<input type="submit" class="btn btn-success btn-block" value="Upload File" />
					</form>
				</td>
				<td>
					<form action="facultycoursemanagement" method="post" enctype="multipart/form-data" style="padding: 30px;">
						<input type="hidden" name="coursecode" value="<%if(course!=null) {%><%=course.getCourseId() %><%} %>" />
						<input type="hidden" name="item" value="videotype"/>
						<label>File Title</label> <input type="text" name="title" placeholder="File Title" class="form-control" value="" required="required"  maxlength="299" /> 
						<label class="form-label" for="customFile">Select Mp4 | WebM | AVI</label>
						<input type="file" name="fileitem" class="form-control" data-max-size="500000" required="required" />
						<br>
						<input type="submit" class="btn btn-success btn-block" value="Upload Video" />
					</form>
				</td>
			</tr>
		</table>
	</div>
</div>
<jsp:include page="facultyFooter.jsp"></jsp:include>