<%@page import="com.course.Course"%>
<%@page import="java.util.ArrayList"%>
<jsp:include page="facultyHeader.jsp"></jsp:include>
<%
	HttpSession sess = request.getSession(false);
	if(sess==null || (sess!=null && sess.getAttribute("faculty")==null)) {
		response.sendRedirect("home.jsp?msg=Login Required");
		return;
	}
%>

<jsp:include page="facultyFooter.jsp"></jsp:include>