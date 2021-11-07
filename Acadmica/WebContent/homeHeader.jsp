<%@ page errorPage="errorpage.jsp" %>
<!DOCTYPE html>
<html lang="en">
<head>
    <link rel="icon" href="Images/navbar1.png" type="image/icon type">
    <meta name="author" content="vicky-pl">
    <meta name="mobile" content="7828789845">
    <link rel="stylesheet" href="https://maxcdn.bootstrapcdn.com/bootstrap/3.4.1/css/bootstrap.min.css">
    <script src="https://ajax.googleapis.com/ajax/libs/jquery/3.5.1/jquery.min.js"></script>
    <script src="https://maxcdn.bootstrapcdn.com/bootstrap/3.4.1/js/bootstrap.min.js"></script>
    <link rel="stylesheet" href="https://cdnjs.cloudflare.com/ajax/libs/font-awesome/4.7.0/css/font-awesome.min.css">
    <link rel="stylesheet" href="style.css">
    <title>Acadmica</title>
</head>
<body class="body_design" id="mainbody">
    <nav class="navbar-inverse">
        <div class="container">
            <div class="navbar-header">
                <button type="button" class="navbar-toggle" data-toggle="collapse" data-target="#menu">
                    <span class="icon-bar"></span>
                    <span class="icon-bar"></span>
                    <span class="icon-bar"></span>
                </button>
                <a href="home"><img src="Images/navbar1.png" alt="" class="img-responsive" style="width: 50px;"></a>
            </div>
            <a class="navbar-brand" href="home">Acadmica</a>
            <div class="collapse navbar-collapse" id="menu">
                <ul class="nav navbar-nav navbar-right">
                    <li><a href="home.jsp">Home</a></li>
                    <li><a href="javascript:openForm(),closeStudentLoginForm()">SignUp</a></li>
                    <li><a href="javascript:openStudentLoginForm(),closeForm()">SignIn(Student)</a></li>
                    <li><a href="aboutus.jsp">AboutUs</a></li>
                </ul>
            </div>
        </div>
    </nav>
    <div class="studentLoginform" id="popupFacultyForm">
        <form action="facultylogin" method="post" class="reg-code-form">
            <table class="studentLoginformtable">
                <button onMouseOut="this.style.color='white'" onMouseOver="this.style.color='red'" style="border-radius: 2px; border-color: red; background: transparent; margin-left: 90%;" type="button" onclick="closeFacultyLoginForm()">Cancel</button>
                <h4 style="color: red;">Faculty Login..</h4>
            <tr>
                <td style="color: white;">Username/Email:</td>
                <td><input style="width: 300px; padding: 8px; border-radius: 4px; background-color: transparent; color: white;" type="text" name="username" value="" placeholder="Username/Email" required></td>
            </tr>
            <tr>
                <td style="color: white;">Password:</td>
                <td><input style="width: 300px; padding: 8px; border-radius: 4px; background-color: transparent; color: white;" type="password" name="password" value="" placeholder="password" required></td>
            </tr>
            <tr>
                <td><button style="margin-left: 10px; width: 100%; background: transparent; border-color: green; color: white; height: 40px;" type="reset" onMouseOver="this.style.color='gray'"  onMouseOut="this.style.color='white'" >Refresh</button></td>
                <td><button style="margin-left: 10px; width: 100%; background: transparent; border-color: green; color: white; height: 40px;" type="submit" onMouseOver="this.style.color='green'"  onMouseOut="this.style.color='white'" >LogIn</button></td>
            </tr>
        </table>
      </form>
       <a href="facultypasswordrecovery">ForgotPassword?</a>
    </div>
    <div class="studentLoginform" id="popupStudentForm">
        <form action="studentlogin" method="post" class="reg-code-form">
            <table class="studentLoginformtable">
                <button onMouseOut="this.style.color='white'" onMouseOver="this.style.color='red'" style="border-radius: 2px; border-color: red; background: transparent; margin-left: 90%;" type="button" onclick="closeStudentLoginForm()">Cancel</button>
                <h4 style="color: red;">Student Login..</h4>
            <tr>
                <td style="color: white;">Username/Email:</td>
                <td><input style="width: 300px; padding: 8px; border-radius: 4px; background-color: transparent; color: white;" type="text" name="username" value="" placeholder="Username/Email" required></td>
            </tr>
            <tr>
                <td style="color: white;">Password:</td>
                <td><input style="width: 300px; padding: 8px; border-radius: 4px; background-color: transparent; color: white;" type="password" name="password" value="" placeholder="password" required></td>
            </tr>
            <tr>
                <td><button style="margin-left: 10px; width: 100%; background: transparent; border-color: green; color: white; height: 40px;" type="reset" onMouseOver="this.style.color='gray'"  onMouseOut="this.style.color='white'" >Refresh</button></td>
                <td><button style="margin-left: 10px; width: 100%; background: transparent; border-color: green; color: white; height: 40px;" type="submit" onMouseOver="this.style.color='green'"  onMouseOut="this.style.color='white'" >LogIn</button></td>
            </tr>
        </table>
      </form>
      <a href="studentpasswordrecovery">ForgotPassword?</a>
    </div>
    <%if(request.getParameter("codeResult")!=null) {%>
    	<span style="font-size: 20px; margin-left:200px; margin-right: 200px; color: red; background-color: black;"> <%=request.getParameter("codeResult") %></span>
    <%} %>
    <div class="formPopup" id="popupForm">
        <form action="registrationcodeverify" action="get" class="reg-code-form">
            <table>
                <button onMouseOut="this.style.color='white'" onMouseOver="this.style.color='red'" style="border-radius: 2px; border-color: red; background: transparent; margin-left: 90%;" type="button" onclick="closeForm()">Cancel</button>
                <h4 style="color: red;">Enter Registration code that is alloted to you..</h4>
            <tr>
                <td><h2>Registration Code</h2></td>
            </tr>
            <tr>
                <td><input class="regbox" type="text" placeholder="Registration Code" name="code" onfocus="" maxlength="10" required></td>
                <td><button style="margin-left: 10px; width: 100%; background: transparent; border-color: green; color: white; height: 60px;" type="submit" onMouseOver="this.style.color='green'"  onMouseOut="this.style.color='white'" >Get Registration form</button></td>
            </tr>
        </table>
      </form>
    </div>