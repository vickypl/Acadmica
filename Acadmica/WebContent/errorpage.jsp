<%@ page isErrorPage="true" %> 
<html>
<title>Acadmica</title>
</head>
<link rel="icon" href="Images/navbar1.png" type="image/icon type">
<link rel="stylesheet" href="https://maxcdn.bootstrapcdn.com/bootstrap/3.4.1/css/bootstrap.min.css">
<script src="https://ajax.googleapis.com/ajax/libs/jquery/3.5.1/jquery.min.js"></script>
<script src="https://maxcdn.bootstrapcdn.com/bootstrap/3.4.1/js/bootstrap.min.js"></script>
<link rel="stylesheet" href="https://cdnjs.cloudflare.com/ajax/libs/font-awesome/4.7.0/css/font-awesome.min.css">
<link rel="stylesheet" href="style.css">
<head>
<% 
String msg=null;
if(request.getParameter("msg")!=null) {
	msg=request.getParameter("msg");
}
java.util.Date date = new java.util.Date();
Exception e =null;
if(request.getParameter("e")!=null) {
		e = new Exception(request.getParameter("e"));
} %>
<body class="body_design"> 
        <div id="page-content-wrapper">
            <div class="container-fluid">
                <div class="row">
                    <div class="col-md-12">
                        <div class="contentbox">
                            <fieldset
                                style="margin-top: 10px; padding: 10px; margin: 2% auto; width: 400px; border-color: white; border-radius: 10px; border-style: solid; border-width: 3px; text-align: center;">
                                <span style="color: white; font-size: 20px;"><%= date%></span>
                            </fieldset>
                            <hr style="border-width: 3px;">
                            <div class="viewbox">
                                <span style="font-size: 24px; font-family: 'Times New Roman', Times, serif; color: red;">
                                    Error...<br>
                                    Something went wrong...<br>
                                    Try again in few moments...
                                    <%if(msg!=null) {%>
                                    	<fieldset style="margin: 0% auto; border-style: solid; border-color: red;">
                                    		<legend>Issue</legend>
                                    		<%= msg %>
                                    	</fieldset>
                                    <%} %>
                                </span>
                                <div style="text-align: center;">
                                    <img src="Images/error.gif">
                                </div>
                            </div>
                        </div>
                    </div>
                </div>
            </div>
        </div>
        <footer>
            <div class="footer">
                <div class="row">
                    <div class="col-md-6">
                        <div class="footer_content">
                            Educate yourself with our wide range of courses...
                            </p>
                        </div>
                    </div>
                    <div class="col-md-3">
                        <div class="footer_social">
                            <h5 class="text-uppercase">Social Media</h5>
                            <ul class="list-unstyled mb-0">
                                <li>
                                    <a href="https://www.facebook.com/thevicky54"
                                        class="fa fa-facebook fa-lg text-dark"></a>
                                </li>
                                <br>
                                <li>
                                    <a href="https://twitter.com/vickypl54" class="fa fa-twitter fa-lg text-dark"></a>
                                </li>
                                <br>
                                <li>
                                    <a href="https://www.linkedin.com/in/vicky-pl/"
                                        class="fa fa-linkedin-square fa-lg text-dark"></a>
                                </li>
                                <br>
                                <li>
                                    <a href="https://www.instagram.com/the_vicky_pl/"
                                        class="fa fa-google-plus fa-lg text-dark"></a>
                                </li>
                            </ul>
                        </div>
                    </div>
                    <div class="col-md-3">
                        <div class="footer_link">
                            <h5 class="text-uppercase mb-0">Other</h5>

                            <ul class="list-unstyled">
                                <li>
                                    <a href="#!" class="text-dark">Link 1</a>
                                </li>
                                <br>
                                <li>
                                    <a href="#!" class="text-dark">Link 2</a>
                                </li>
                                <br>
                                <li>
                                    <a href="#!" class="text-dark">Link 3</a>
                                </li>
                                <br>
                                <li>
                                    <a href="#!" class="text-dark">Link 4</a>
                                </li>
                            </ul>
                        </div>
                    </div>
                </div>
                <div class="text-center p-3" style="bottom: 0; background-color: rgba(0, 0, 0, 0.2);">
                    © 2020 Copyright:
                    <a class="text-dark" href="index.html">Acadmica.com</a>
                </div>
            </div>
        </footer>
        <script>
            var preloader=document.getElementById('loading');

            function myLoader() {
                preloader.style.display='none';
            }
        </script>
</body>
</html>