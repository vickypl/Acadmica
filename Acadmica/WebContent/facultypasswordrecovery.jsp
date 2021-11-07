<jsp:include page="homeHeader.jsp"></jsp:include>
<%
	String msg=null;
	if(request.getParameter("msg")!=null) {
		msg = request.getParameter("msg");
	}
%>
<div class="container-fluid">
	<div class="passwordrec">
		<h3 style="font-style: italic;" align="center">Faculty Password Recovery</h3>
		<form action="facultypasswordrecovery" method="post">
			<div class="form-group">
				<i class="fa fa-user icon"></i>
				<label for="exampleInputEmail1">E-mail</label> 
				<input type="email" class="form-control" id="exampleInputEmail1" name="email" maxlength="50" placeholder="E-mail" required="required"> 
				<small id="emailHelp" class="form-text text-muted">We'll send your password at this email.</small>
				<br><small style="color: red">Email must be registered.</small>
				<%if(msg!=null) {%>
				<br><small style="color: green; font-size: 20px;"><%=msg %></small>
				<%} %>
			</div>
			<button type="submit" class="btn-block btn-primary">Send Password</button>
		</form>
		<br>
	</div>
</div>
<jsp:include page="homeFooter.jsp"></jsp:include>