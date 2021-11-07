<jsp:include page="facultyHeader.jsp"></jsp:include>
<%
	String msg= null;
 	if(request.getParameter("msg")!=null) {
 		msg=request.getParameter("msg");
 	}
%>
<div class="viewbox">
	<h3 style="text-align: center; color: white;">Change Your Password here</h3>
	<%if(msg!=null) {%>
	<h5 style="text-align: center; color: green; font-style: bold;"><%= msg %></h5>
	<%} %>
	<form action="facultypasswordchange" method="post" style="width: 500px; margin: 5% auto; color: white;">
	 <div class="form-group row">
    <label for="inputPassword" class="col-sm-2 col-form-label">Old Password</label>
    <div class="col-sm-10">
      <input type="text" name="old" class="form-control" placeholder="Old Password">
    </div>
  	</div>
 	<div class="form-group row">
    <label for="inputPassword" class="col-sm-2 col-form-label">New Password</label>
    <div class="col-sm-10">
      <input type="password" name="password" class="form-control" placeholder="New Password">
    </div>
  	</div>
 	<div class="form-group row">
    <label for="inputPassword" class="col-sm-2 col-form-label">Confirm New Password</label>
    <div class="col-sm-10">
      <input type="password" name="cpassword" class="form-control"  placeholder="Confirm New Password">
    </div>
  	</div>
  	<button class="btn btn-primary btn-block" type="submit">Update Password</button>
	</form>
</div>
<jsp:include page="facultyFooter.jsp"></jsp:include>