<jsp:include page="homeHeader.jsp"></jsp:include>
<%
	
	HttpSession sess = request.getSession(false);
	if(sess!=null && sess.getAttribute("student")!=null) {
		response.sendRedirect("studentloggedin.jsp");
	} else if(sess!=null && sess.getAttribute("faculty")!=null) {
		response.sendRedirect("facultyloggedin.jsp");		
	} else if(sess!=null && sess.getAttribute("admin")!=null) {
		response.sendRedirect("deanloggedin.jsp");
	}


	String msg = null;
	if(request.getParameter("msg")!=null) {
		msg=request.getParameter("msg");
	}
%>
<div class="container-fluid">
	<% if(msg!=null) {%>
		<div style="border: thick; border-color: white; font-style: italic;">
				<%= msg %>
		</div>
	<%} %>
	<div class="adminloginpenal">
		<h3 style="font-style: italic;" align="center">Admin Login</h3>
		<form action="adminlogin" method="post">
			<div class="form-group">
				<i class="fa fa-user icon"></i>
				<label for="exampleInputEmail1">Email/Username</label> 
				<input type="text" class="form-control" name="username" maxlength="50" placeholder="Email/Username"> 
				<small id="emailHelp" class="form-text text-muted">We'll never share your email with anyone else.</small>
			</div>
			<div class="form-group">
				 <i class="fa fa-key icon"></i>
				 <label for="exampleInputPassword1">Password</label> <input type="password" class="form-control" name="password" id="exampleInputPassword1" maxlength="50" placeholder="Password">
			</div>
			<button type="submit" class="btn-block btn-primary">Login</button>
		</form>
		<br>
		<a href="adminpasswordrecovery">ForgetPassword?</a>
	</div>
</div>
<jsp:include page="homeFooter.jsp"></jsp:include>