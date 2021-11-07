package com.faculty;

import java.io.IOException;
import java.util.ArrayList;

import javax.servlet.ServletException;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

public class DeleteSubjectCodeRequest extends HttpServlet {
	private static final long serialVersionUID = 1L;
       
	protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		
		//session
		HttpSession sess = request.getSession(false);
		if (sess == null || (sess != null && sess.getAttribute("faculty") == null)) {
			response.sendRedirect("home.jsp?msg=Login Required");
			return;
		}
		Faculty faculty = (Faculty)sess.getAttribute("faculty");
		
		
		String delId = request.getParameter("id");
		
		FacultyAction action = new FacultyAction();
		
		boolean isDeleted = action.deleteSubjectRegCodeRequest(delId);
		
		if(isDeleted) {
			
			String sql="select * from subject_reg_code_request where faculty_id='"+faculty.getRegistrationId()+"'";
			ArrayList<SubjectRegCodeRequest> subjectCourseCodeRequestList = action.getSubjectRegCodeRequest(sql);
			sess.setAttribute("subjectCourseCodeRequestList", subjectCourseCodeRequestList);
			
			response.sendRedirect("facultycoursecoderequest.jsp?msg=Request Deleted");
		} else {
			response.sendRedirect("facultycoursecoderequest.jsp?msg=Failed to Deleted requested..");	
		}
	
	}

}
