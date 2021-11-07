<jsp:include page="deanHeader.jsp"></jsp:include>
<%
	HttpSession sess = request.getSession(false);
	if(sess==null || (sess!=null && sess.getAttribute("admin")==null)) {
		response.sendRedirect("home.jsp?msg=Login Required");
		return;
	}
	String code=null;
	if(request.getAttribute("regCode")!=null) {
		code=(String)request.getAttribute("regCode");
	}
	String msg=null;
	if(request.getParameter("msg")!=null) {
		msg=request.getParameter("msg");
	}
	
%>
<div class="viewbox">
	<table style="color: white; margin: 4% auto;">
		<div
			style="text-align: center; font-family: Georgia, 'Times New Roman', Times, serif; color: rgb(255, 0, 0); font-size: x-large;">
			Registration Code Generator</div>
		<%if(code!=null) { %>
		<div class="codeVisual">
			Your Code: <span><%=code %></span><br> <small style="color: red;">Note:
				Send this to Member so that he/she can Register Successfully..</small>
		</div>
		<%} else if (msg!=null) { %>
		<div class="codeVisual">
			<span><%=msg %></span><br> <small style="color: red;">Note:
				Send this to Member so that he/she can Register Successfully..</small>
		</div>	
		<%} else { %>
			<div class="codeVisual">
			<span>Your Code Will be Displayed here.</span>
			</div>
		<%} %>
		<hr>
		<tr>
			<td><a href="generatecode?codefor=faculty"><button type="button" style="font-size: 20px; font-family: cursive;" class="btn btn-danger">Generate Faculty Registration Code</button></a></td>
		</tr>
		<tr>
			<td><a href="generatecode?codefor=student"><button type="button" style="font-size: 20px; font-family: cursive;" class="btn btn-danger">Generate Student Registration Code</button></a></td>
		</tr>
		<tr>
			<td><a href="generatecode?codefor=course"><button type="button" style="font-size: 20px; font-family: cursive;" class="btn btn-danger">Generate Course Registration Code</button></a></td>
		</tr>
	</table>
</div>
<jsp:include page="deanFooter.jsp"></jsp:include>