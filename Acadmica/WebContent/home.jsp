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
%>
    <div class="container-fluid">
        <div class="raw">
            <div class="col-md-6">
                <div class="leftside">
                    <img src="Images/bodyicon.png" alt="" class="bodyimage">
                </div>
                <span class="tagline">Register to our courses</span>
                <div>
                    <a href="adminlogin"><button class="buttons">Dean Login</button></a>
                    <button onclick="openFacultyLoginForm(),closeStudentLoginForm()" class="buttons">Faculty Login</button>
                    <button onclick="openStudentLoginForm(),closeFacultyLoginForm()" class="buttons">Student Login</button>
                </div>
            </div>
            <div class="col-md-6">
                <%if(request.getParameter("msg")!=null) { %>
                	<div id="notibox" style="background-color: black; color: white; opacity: 0.8; width: 250px; border-radius: 3px; padding: 10px; border-style: double;">
						<button style="margin: 1% auto; color: white;" onclick="myFunction()">x</button>
						<%= request.getParameter("msg") %>
                	</div>
                <%} %>
                <div class="rightside" id="wiki">
                    <form action="request" method="post">
                            <table class="tablefront">
                                <tr>
                                    <td style="padding: 6px; opacity: 0.9; color: white; font-size: 20px;"><i class="fa fa-pencil"></i>  Register here to get the registration code:</td>
                                </tr>
                                <tr>
                                    <td>
                                    <input class="inputbox" type="text" name="fullname" value="" placeholder="Full Name" maxlength="30" required>
                                </td>
                            </tr>
                            <tr>
                                <td>
                                    <input class="inputbox" type="text" name="email" value="" placeholder="E-mail" maxlength="50" required>
                                </td>
                            </tr>
                            <tr>
                                <td>
                                    <input class="inputbox" type="text" name="mobile" value="" placeholder="Mobile" maxlength="10" required>
                                </td>
                            </tr>
                            <tr>
                                <td>
                                    <select class="inputbox" name="role">
                                        <option value="student" selected>Student</option>
                                        <option value="faculty">Faculty</option>
                                    </select>
                                </td>
                            </tr>
                            <tr>
                                <td>
                                    <input class="resfront" type="reset" value="refresh" >   
                                    <input class="resfront" type="submit" value="Submit" >
                                </td>
                            </tr>
                        </table>
                    </form>
                </div>
            </div>
        </div>
    </div>
    <script src="js/extra.js"></script>
<jsp:include page="homeFooter.jsp"></jsp:include>
    <script src="js/extra.js"></script>