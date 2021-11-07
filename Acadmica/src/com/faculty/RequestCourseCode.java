package com.faculty;
/**
author: Vicky pl
email: vicky542011@gmail.com
mobile: 7828789845
**/
import java.io.IOException;

import javax.servlet.ServletException;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;


public class RequestCourseCode extends HttpServlet {
	private static final long serialVersionUID = 1L;
       
	protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		
		//session
		HttpSession sess = request.getSession(false);
		if(sess==null || (sess!=null && sess.getAttribute("faculty")==null)) {
			response.sendRedirect("home.jsp?msg=Login Required");
			return;
		}
	
		Faculty faculty = (Faculty)sess.getAttribute("faculty");
		
		String id="1";
		String requestId=faculty.getRegistrationId();
		String reqj="ff";
		String status="false";
		
		FacultyAction action = new FacultyAction();
		boolean postStatus = false;
						postStatus=action.courseRequestPoster(id, requestId, reqj, status);
		
		if(postStatus) {
			response.sendRedirect("facultycoursedetails.jsp?msg=Request Successfully Sent.");
		} else {
			response.sendRedirect("facultycoursedetails.jsp?msg=Failed to send the request.");			
		}

	}

}
