<%@page import="com.course.Course"%>
<%@page import="java.util.ArrayList"%>
<%! @SuppressWarnings("unchecked") %>
<jsp:include page="homeHeader.jsp"></jsp:include>
<%
	Cookie[] cookies = request.getCookies();
	String userId = cookies[1].getValue();
	
	ArrayList<Course> courseList = new ArrayList<Course>();
	courseList=(ArrayList<Course>)request.getAttribute("courseList");
	
	String msg = null;
	if(request.getAttribute("msg")!=null) {
		msg= (String)request.getAttribute("msg");
	}
%>
<div class="container-fluid">
<br>
				<div class="progress">
					<div class="progress-bar progress-bar-striped progress-bar-animated"
						role="progressbar" aria-valuenow="25" aria-valuemin="0"
						aria-valuemax="50%" style="width: 25%;">33%</div>
				</div>
				<%if(msg!=null) {%>
				<span style="color: red; text-align: center; font-size: 15px;"><%=msg %></span>
				<%} %>
	<div class="signupformbox">
		<form action="#" method="POST">
			<div class="formOne" id="form1">
				<h2
					style="padding: 10px; color: white; opacity: 0.7; font-family: 'Times New Roman', Times, serif;"
					align="center">Personal Details</h2>
				<table class="signuptable">
					<tr>
						<td><input class="studentsignformfield" type="text" name="id" value="<%=userId %>"
							placeholder="Registration id" maxlength="10" readonly required></td>
					</tr>
					<tr>
						<td><input class="studentsignformfield" type="text" name="fname"
							placeholder="First Name" maxlength="30" required></td>
					</tr>
					<tr>
						<td><input class="studentsignformfield" type="text" name="lname"
							placeholder="Last Name" maxlength="30" required></td>
					</tr>
					<tr>
						<td><select class="studentsignformfield" value=""
							name="gender">&nbsp;
								<option value="male">Male</option>
								<option value="female">Female</option>
								<option value="other">Other</option>
						</select></td>
					</tr>
					<tr>
						<td><div style="color: white; font-size: 10px;">Date of birth:(MM/DD/YYYY) on before 1998-12-31</div><input class="studentsignformfield" type="date"
							placeholder="Date of birth..." name="dob" value="" max="1998-12-31" required></td>
					</tr>
					<tr>
						<td><select class="studentsignformfield" value="" name="mstatus"
							required>&nbsp;
								<option value="married">Married</option>
								<option value="unmarried">Unmarried</option>
								<option value="divorced">Divorced</option>
						</select></td>
					</tr>
					<tr>
						<td><input class="studentsignformfield" type="text"
							placeholder="Father's/Husband's/ Name..." name="faname" value=""
							maxlength="40" required></td>
					</tr>
					<tr>
						<td><button
								style="border-radius: 40px; background-color: transparent; color: green; width: 100%; height: 50px;"
								type="submit" onclick="openform3(),closeform1()" value="Next">Next
								Step</button></td>
					</tr>
				</table>
			</div>
			<!-- form 2 start -->
			<div class="formThree" id="form3">
				<h2
					style="padding: 10px; color: white; opacity: 0.7; font-family: 'Times New Roman', Times, serif;"
					align="center">Contect Details</h2>
				<table class="signuptable">
					<tr>
						<td><input class="studentsignformfield" type="email" name="email"
							placeholder="E-mail" maxlength="50" required></td>
					</tr>
					<tr>
						<td><input class="studentsignformfield" type="text" name="mobile"
							placeholder="Mobile" maxlength="10" required></td>
					</tr>
					<tr>
						<td><input class="studentsignformfield" type="text" name="address1"
							placeholder="Address Temporary" maxlength="40" required></td>
					</tr>
					<tr>
						<td><input class="studentsignformfield" type="text" name="address2"
							placeholder="Address Permanent" maxlength="40" required></td>
					</tr>
					<tr>
						<td><input class="studentsignformfield" type="text" value="" name="city"
							placeholder="City" required></td>
					</tr>
					<tr>
						<td><select class="studentsignformfield" value=""
							name="state" required>&nbsp;
								<option value="Andhra Pradesh">Andhra Pradesh</option>
								<option value="Andaman and Nicobar Islands">Andaman and
									Nicobar Islands</option>
								<option value="Arunachal Pradesh">Arunachal Pradesh</option>
								<option value="Assam">Assam</option>
								<option value="Bihar">Bihar</option>
								<option value="Chandigarh">Chandigarh</option>
								<option value="Chhattisgarh">Chhattisgarh</option>
								<option value="Dadar and Nagar Haveli">Dadar and Nagar
									Haveli</option>
								<option value="Daman and Diu">Daman and Diu</option>
								<option value="Delhi">Delhi</option>
								<option value="Lakshadweep">Lakshadweep</option>
								<option value="Puducherry">Puducherry</option>
								<option value="Goa">Goa</option>
								<option value="Gujarat">Gujarat</option>
								<option value="Haryana">Haryana</option>
								<option value="Himachal Pradesh">Himachal Pradesh</option>
								<option value="Jammu and Kashmir">Jammu and Kashmir</option>
								<option value="Jharkhand">Jharkhand</option>
								<option value="Karnataka">Karnataka</option>
								<option value="Kerala">Kerala</option>
								<option value="Madhya Pradesh">Madhya Pradesh</option>
								<option value="Maharashtra">Maharashtra</option>
								<option value="Manipur">Manipur</option>
								<option value="Meghalaya">Meghalaya</option>
								<option value="Mizoram">Mizoram</option>
								<option value="Nagaland">Nagaland</option>
								<option value="Odisha">Odisha</option>
								<option value="Punjab">Punjab</option>
								<option value="Rajasthan">Rajasthan</option>
								<option value="Sikkim">Sikkim</option>
								<option value="Tamil Nadu">Tamil Nadu</option>
								<option value="Telangana">Telangana</option>
								<option value="Tripura">Tripura</option>
								<option value="Uttar Pradesh">Uttar Pradesh</option>
								<option value="Uttarakhand">Uttarakhand</option>
								<option value="West Bengal">West Bengal</option>
						</select></td>
					</tr>
					<tr>
						<td><input class="studentsignformfield" type="text" value="" name="pincode"
							placeholder="Pin Code" maxlength="6" required></td>
					</tr>
					<tr>
					</tr>
					<tr>
						<td><div style="color: white; font-size: 10px;">&nbsp;&nbsp;&nbsp;Course You Want to Provide:</div><select class="studentsignformfield" value="" name="subject"
							required>&nbsp;
						<%-- 	<% for(Course course : courseList) {  String param= course.getCourseName()+" "+course.getCourseId();%>
								<option value="<%= param %>"><%=course.getCourseName() %></option>
							<%} %> --%>
								<option value="null">Create after registration</option>
						</select></td>
					</tr>
					<tr>
						<td><button
								style="border-radius: 40px; background-color: transparent; color: green; width: 45%; height: 50px;"
								type="submit" value="Next" onclick="openform1(), closeform3()">Previous
								Step</button>
							<button
								style="margin-left: 40px; border-radius: 40px; background-color: transparent; color: green; width: 45%; height: 50px;"
								type="submit" value="Next" onclick="openform4(), closeform3()">Next
								Step</button></td>
						<!-- <td><button style="border-radius: 40px; background-color: transparent; color: green; width: 100%; height: 50px;" type="submit" value="Next" onclick="openform4(), closeform3()">Next Step</button></td> -->
					</tr>
				</table>
			</div>
			<!-- form 2 end -->
			<!-- form 3 start -->
			<div class="formFour" id="form4">
				<h2
					style="padding: 10px; color: white; opacity: 0.7; font-family: 'Times New Roman', Times, serif;"
					align="center">Login Details</h2>
				<table class="signuptable">
					<tr>
						<td><input class="studentsignformfield" type="text" value="" name="username"
							placeholder="Username" maxlength="15" required></td>
					</tr>
					<tr>
						<td><input class="studentsignformfield" type="password" name="password"
							placeholder="Password" maxlength="30" required></td>
					</tr>
					<tr>
						<td><input class="studentsignformfield" type="password" name="cpassword"
							placeholder="Confirm Password"  maxlength="30" required></td>
					</tr>
					<br>
					<tr>
						<td style="color: white;"><input type="checkbox" required /> I accept the Terms and Condition of Service...</td>
					</tr>
					<br>
					<tr>
						<td><button
								style="border-radius: 40px; background-color: transparent; color: green; width: 100%; height: 50px;"
								type="submit" value="Next" onclick="closeform4(), openform3()">Previous</button></td>
					</tr>
					<tr>
						<td><button
								style="border-radius: 40px; background-color: transparent; color: green; width: 100%; height: 50px;"
								type="reset">Refresh</button></td>
					</tr>
					<tr>
						<td><button
								style="border-radius: 40px; background-color: transparent; color: green; width: 100%; height: 50px;"
								type="submit">Complete SignUp</button></td>
					</tr>
				</table>
			</div>
			<!-- form 3 end -->
		</form>
	</div>
</div>
<jsp:include page="homeFooter.jsp"></jsp:include>