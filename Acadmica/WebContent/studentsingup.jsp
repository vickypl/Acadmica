<jsp:include page="homeHeader.jsp"></jsp:include>
<%!@SuppressWarnings("unchecked")%>
<%@ page import="java.util.ArrayList"%>
<%@ page import="com.course.Course"%>
<%
	Cookie[] cookies = request.getCookies();
	String userId = cookies[1].getValue();

	ArrayList<Course> courseList = new ArrayList<Course>();
	courseList = (ArrayList<Course>) request.getAttribute("courseList");
	
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
						aria-valuemax="50%" style="width: 25%;">25%</div>
				</div>
				<%if(msg!=null) {%>
				<span style="color: red; text-align: center; font-size: 15px;"><%=msg %></span>
				<%} %>
	<div class="signupformbox">
		<form action="studentsignup" method="POST">
			<div class="formOne" id="form1">
				<h2
					style="padding: 10px; color: white; opacity: 0.7; font-family: 'Times New Roman', Times, serif;"
					align="center">Personal Details</h2>
				<table class="signuptable">
					<tr>
						<td><input class="studentsignformfield" type="text"
							name="regid" value="<%=userId%>" placeholder="Registration id"
							maxlength="10" readonly="readonly" required
							style="background-color: gray;"></td>
					</tr>
					<tr>
						<td><input class="studentsignformfield" type="text"
							name="fname" value="" placeholder="First Name" maxlength="20"
							required></td>
					</tr>
					<tr>
						<td><input class="studentsignformfield" type="text"
							name="lname" value="" placeholder="Last Name" maxlength="20"
							required></td>
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
						<td><div style="color: white; font-size: 10px;">
								Date of birth:
								<!-- (MM/DD/YYYY) -->
							</div>
							<input type="date" name="dob" class="studentsignformfield"
							placeholder="dd-mm-yyyy" value="" /></td>
					</tr>
					<tr>
						<td><select class="studentsignformfield" value=""
							name="category" required>&nbsp;
								<option value="general">General</option>
								<option value="obc">Obc</option>
								<option value="scst">Sc/st</option>
						</select></td>
					</tr>
					<tr>
						<td><input class="studentsignformfield" type="text"
							placeholder="Father's Name..." name="faname" value="" required></td>
					</tr>
					<tr>
						<td><input class="studentsignformfield" type="text"
							placeholder="Mother's Name..." name="maname" value="" required></td>
					</tr>
					<tr>
						<td><button
								style="border-radius: 40px; background-color: transparent; color: green; width: 100%; height: 50px;"
								type="submit" onclick="openform2(),closeform1()" value="Next">Next
								Step</button></td>
					</tr>
				</table>
			</div>
			<!-- form 2 start -->
			<div class="formTwo" id="form2">
				<h2
					style="padding: 10px; color: white; opacity: 0.7; font-family: 'Times New Roman', Times, serif;"
					align="center">Academic Details</h2>
				<table class="signuptable">
					<tr>
						<td><div style="color: white; font-size: 10px;">&nbsp;&nbsp;&nbsp;Your
								Current Standard:</div>
							<select class="studentsignformfield" value="" name="standard"
							required disable>&nbsp;
								<option value="unknown">Unknown</option>
								<option value="1">Class I</option>
								<option value="2">Class II</option>
								<option value="3">Class III</option>
								<option value="4">Class IV</option>
								<option value="5">Class V</option>
								<option value="6">Class VI</option>
								<option value="7">Class VII</option>
								<option value="8">Class VIII</option>
								<option value="9">Class IX</option>
								<option value="10">Class X</option>
								<option value="11">Class XI</option>
								<option value="12">Class XII</option>
								<option value="bachelor">Bachelor's</option>
								<option value="master">Master's</option>
						</select></td>
					</tr>
					<tr>
						<td><div style="color: white; font-size: 10px;">&nbsp;&nbsp;&nbsp;Course
								You are enrolling for:</div>
							<select class="studentsignformfield" name="course" required>&nbsp;
								<%
									for (Course course : courseList) {
										String param = course.getCourseName()+" "+ course.getCourseId();
								%>
								<option value="<%=param%>"><%=course.getCourseName()%></option>
								<%
									}
								%>
						</select></td>
					</tr>
					<tr>
						<td><input class="studentsignformfield" type="text"
							placeholder="School Name" name="school" maxlength="50" value=""
							required></td>
					</tr>
					<tr>
						<td><input class="studentsignformfield" type="text"
							placeholder="College Name" name="college" maxlength="50" value=""
							required></td>
					</tr>
					<tr>
						<td><input class="studentsignformfield" type="text"
							placeholder="Degree" name="degree" maxlength="50" value=""
							required></td>
					</tr>
					<tr>
						<td><input class="studentsignformfield" type="text"
							placeholder="Subject(Degree in)" name="subject" maxlength="50"
							value="" required></td>
					</tr>
					<tr>
						<td><input class="studentsignformfield" type="text"
							placeholder="Letest Aggregate" name="marks" maxlength="3"
							value="" required></td>
					</tr>
					<tr>
						<td><button
								style="border-radius: 40px; background-color: transparent; color: green; width: 45%; height: 50px;"
								type="submit" value="Next" onclick="openform1(), closeform2()">Previous
								Step</button>
							<button
								style="margin-left: 40px; border-radius: 40px; background-color: transparent; color: green; width: 45%; height: 50px;"
								type="submit" value="Next" onclick="openform3(), closeform2()">Next
								Step</button></td>
					</tr>
				</table>
			</div>
			<!-- form 2 end -->
			<!-- form 3 start -->
			<div class="formThree" id="form3">
				<h2
					style="padding: 10px; color: white; opacity: 0.7; font-family: 'Times New Roman', Times, serif;"
					align="center">Contact Details</h2>
				<table class="signuptable">
					<tr>
						<td><input class="studentsignformfield" type="email"
							name="email" value="" placeholder="E-mail" maxlength="50"
							required></td>
					</tr>
					<tr>
						<td><input class="studentsignformfield" type="text"
							name="mobile" value="" placeholder="Mobile" maxlength="10"
							required></td>
					</tr>
					<tr>
						<td><input class="studentsignformfield" type="text"
							name="gaurdmobile" value="" placeholder="Guardian's Mobile"
							maxlength="10" required></td>
					</tr>
					<tr>
						<td><input class="studentsignformfield" type="text"
							name="address1" value="" placeholder="Address Temporary"
							maxlength="50" required></td>
					</tr>
					<tr>
						<td><input class="studentsignformfield" type="text"
							name="address2" value="" placeholder="Address Permanent"
							maxlength="50" required></td>
					</tr>
					<tr>
						<td><input class="studentsignformfield" type="text"
							name="city" placeholder="City" maxlength="30" required></td>
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
						<td><input class="studentsignformfield" type="text"
							name="pincode" value="" placeholder="Pin Code" maxlength="6"
							required></td>
					</tr>
					<tr>
						<td><button
								style="border-radius: 40px; background-color: transparent; color: green; width: 45%; height: 50px;"
								type="submit" value="Next" onclick="openform2(), closeform3()">Previous
								Step</button>
							<button
								style="margin-left: 40px; border-radius: 40px; background-color: transparent; color: green; width: 45%; height: 50px;"
								type="submit" value="Next" onclick="openform4(), closeform3()">Next
								Step</button></td>
						<!-- <td><button style="border-radius: 40px; background-color: transparent; color: green; width: 100%; height: 50px;" type="submit" value="Next" onclick="openform4(), closeform3()">Next Step</button></td> -->
					</tr>
				</table>
			</div>
			<!-- form 3 end -->
			<!-- form 4 start -->
			<div class="formFour" id="form4">
				<h2
					style="padding: 10px; color: white; opacity: 0.7; font-family: 'Times New Roman', Times, serif;"
					align="center">Login Details</h2>
				<table class="signuptable">
					<tr><span id="availablity"></span></tr>
					<tr>
						<td><input class="studentsignformfield" type="text" name="username" value="" placeholder="Username" minlength="6" maxlength="15" required></td>
					</tr>
					<tr>
						<td><input class="studentsignformfield" type="password"
							name="password" value="" placeholder="Password" maxlength="15"
							required></td>
					</tr>
					<tr>
						<td><input class="studentsignformfield" type="password"
							name="confirmp" value="" placeholder="Confirm Password" required></td>
					</tr>
					<br>
					<tr>
						<td style="color: white;"><input type="checkbox" name="check"
							value="checked" required /> I accept the Terms and Condition of
							Service.</td>
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
			<!-- form 4 end -->
		</form>
	</div>
</div>
</div>
</div>
<jsp:include page="homeFooter.jsp"></jsp:include>