<%@page import="com.faculty.Faculty"%>
<jsp:include page="deanHeader.jsp"></jsp:include>
<%
	HttpSession sess = request.getSession(false);
	if (sess == null || (sess != null && sess.getAttribute("admin") == null)) {
		response.sendRedirect("home.jsp?msg=Login Required");
		return;
	}

	Faculty faculty = (Faculty) request.getAttribute("faculty");
%>
<div class="text-center"
	style="color: white; font-size: 20px; font-family: 'Times New Roman', Times, serif;">
	Update Faculty Details</div>
<hr style="border-width: 3px;">
<div class="viewbox">
	<div class="updateformbox">
		<form action="admineditfaculty" method="post">
			<div class="formOne" id="form1">
				<h2
					style="padding: 10px; color: white; opacity: 0.7; font-family: 'Times New Roman', Times, serif;"
					align="center">Personal Details</h2>
				<table class="signuptable">
					<tr>
						<td><input class="studentsignformfield" type="text" name="id"
							value="<%=faculty.getRegistrationId() %>"
							placeholder="Registration id" maxlength="10" readonly
							style="color: gray;" required></td>
					</tr>
					<tr>
						<td><input class="studentsignformfield" type="text"
							name="fname" placeholder="First Name"
							value="<%=faculty.getFirstName() %>" maxlength="30" required></td>
					</tr>
					<tr>
						<td><input class="studentsignformfield" type="text"
							name="lname" placeholder="Last Name"
							value="<%=faculty.getLastName() %>" maxlength="30" required></td>
					</tr>
					<tr>
						<td><select class="studentsignformfield"
							value="<%=faculty.getGender() %>" name="gender">&nbsp;
								<option value="male">Male</option>
								<option value="female">Female</option>
								<option value="other">Other</option>
						</select></td>
					</tr>
					<tr>
						<td><div style="color: white; font-size: 10px;">Date of
								birth:(MM/DD/YYYY) on before 1998-12-31</div>
							<input class="studentsignformfield" type="date"
							placeholder="Date of birth..." name="dob"
							value="<%=faculty.getDateOfBirth() %>" max="1998-12-31" required></td>
					</tr>
					<tr>
						<td><select class="studentsignformfield"
							value="<%=faculty.getMaritalStatus() %>" name="mstatus" required>&nbsp;
								<option value="married">Married</option>
								<option value="unmarried">Unmarried</option>
								<option value="divorced">Divorced</option>
						</select></td>
					</tr>
					<tr>
						<td><input class="studentsignformfield" type="text"
							placeholder="Father's/Husband's/ Name..." name="faname"
							value="<%=faculty.getGuardianName() %>" maxlength="40" required></td>
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
						<td><input class="studentsignformfield" type="text"
							name="email" placeholder="E-mail"
							value="<%=faculty.getEmail() %>" maxlength="50" required></td>
					</tr>
					<tr>
						<td><input class="studentsignformfield" type="text"
							name="mobile" placeholder="Mobile" maxlength="10"
							value="<%=faculty.getMobile() %>" required></td>
					</tr>
					<tr>
						<td><input class="studentsignformfield" type="text"
							name="address1" placeholder="Address Temporary" maxlength="40"
							value="<%=faculty.getAddress1() %>" required></td>
					</tr>
					<tr>
						<td><input class="studentsignformfield" type="text"
							name="address2" placeholder="Address Permanent" maxlength="40"
							value="<%=faculty.getAddress2() %>" required></td>
					</tr>
					<tr>
						<td><input class="studentsignformfield" type="text"
							value="<%=faculty.getCity() %>" name="city" placeholder="City"
							required></td>
					</tr>
					<tr>
						<td><select class="studentsignformfield"
							value="<%=faculty.getState() %>" name="state" required>&nbsp;
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
							value="<%=faculty.getPincode() %>" name="pincode"
							placeholder="Pin Code" maxlength="6" required></td>
					</tr>
					<tr>
					</tr>
					<tr>
						<td><div style="color: white; font-size: 10px;">&nbsp;&nbsp;&nbsp;Course
								You Want to Provide:</div> <input class="studentsignformfield"
							type="text" value="<%=faculty.getSubject() %>" name="subject"
							readonly style="color: gray;" placeholder="Subject" required>
						</td>
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
						<td><div style="color: red; font-size: 10px;">Username and password is unchangeable.</div><input class="studentsignformfield" type="text"
							value="<%=faculty.getUsername() %>" name="username"
							placeholder="Username" maxlength="15" readonly
							style="color: gray;" required></td>
					</tr>
					<tr>
						<td><input class="studentsignformfield" type="password"
							name="password" value="protected" placeholder="Password"
							maxlength="30" readonly style="color: gray;" required></td>
					</tr>
					<tr>
						<td><input class="studentsignformfield" type="password"
							name="cpassword" value="protected" placeholder="Confirm Password"
							maxlength="30" readonly style="color: gray;" required></td>
					</tr>
					<br>
					<tr>
						<td style="color: white;"><input type="checkbox" required />
							I accept the Terms and Condition of Updating...</td>
					</tr>
					<br>
					<tr>
						<td><button
								style="border-radius: 40px; background-color: transparent; color: green; width: 100%; height: 50px;"
								value="Next" onclick="closeform4(), openform3()">Previous</button></td>
					</tr>
					<tr>
						<td><button
								style="border-radius: 40px; background-color: transparent; color: green; width: 100%; height: 50px;"
								type="submit">Update Details</button></td>
					</tr>
				</table>
			</div>
			<!-- form 3 end -->
		</form>
	</div>
</div>
<jsp:include page="deanFooter.jsp"></jsp:include>