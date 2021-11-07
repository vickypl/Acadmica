package com.admin;
/**
author: Vicky pl
email: vicky542011@gmail.com
mobile: 7828789845
**/
import java.io.IOException;
import java.util.ArrayList;

import javax.servlet.ServletException;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

public class DeleteCodeRequest extends HttpServlet {
	private static final long serialVersionUID = 1L;

	protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		
		//session
		HttpSession sess = request.getSession(false);
		if(sess==null || (sess!=null && sess.getAttribute("admin")==null)) {
			response.sendRedirect("home.jsp?msg=Login Required");
			return;
		}
		
		AdminAction action = new AdminAction();
		String delId=request.getParameter("id");
		
		boolean isDeleted = action.deleteCourseCodeRequest(delId);
		
		if(isDeleted) {
			String sql="select * from course_code_request";
			ArrayList<CourseCodeRequests> courseCodeRequestsList = action.getCourseRequestList(sql);
			sess.setAttribute("courseCodeRequestsList", courseCodeRequestsList);
			response.sendRedirect("deancoursecoderequest.jsp?msg=Request Deleted..");
		} else {			
			response.sendRedirect("deancoursecoderequest.jsp?msg=Failed to Delete..");
		}
	}

}
