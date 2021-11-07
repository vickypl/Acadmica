package com.faculty;
/**
		author: Vicky pl
		email: vicky542011@gmail.com
		mobile: 7828789845
 **/

import java.io.IOException;

import javax.servlet.RequestDispatcher;
import javax.servlet.ServletException;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

public class GenrateCourseRegCode extends HttpServlet {
	private static final long serialVersionUID = 1L;
       
	protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		
		HttpSession sess = request.getSession(false);
		if(sess==null || (sess!=null && sess.getAttribute("faculty")==null)) {
			response.sendRedirect("home.jsp?msg=Login Required");
			return;
		}
		
		FacultyAction action = new FacultyAction();
		
		boolean isCodeAlreadyAvailable=true;
		String generatedCode=null;
		generatedCode = courseRequsetCodeGeneretor();
		isCodeAlreadyAvailable=action.isCourseCodeValid(generatedCode);
		while(!isCodeAlreadyAvailable) {
			generatedCode = courseRequsetCodeGeneretor();
			isCodeAlreadyAvailable=action.isCourseCodeValid(generatedCode);
		}
		
		try {
			Thread.sleep(5000);
		} catch (InterruptedException e) {
			e.printStackTrace();
		}
		
		
		boolean isPosted = action.postGenratedCourseRequestCode(generatedCode);
		
		if(isPosted) {
			request.setAttribute("generatedCode", generatedCode);
			RequestDispatcher rd = request.getRequestDispatcher("facultyloggedin.jsp");
			rd.forward(request, response);
		} else {
			response.sendRedirect("facultyloggedin.jsp?msg=Failed to generate the registration Code..");
		}
		
	}
	
	public String courseRequsetCodeGeneretor() {
		String base="ABCDEFGHIJKLMNOPQRSTUVWXYZ0123456789";
		java.util.Random rand = new java.util.Random();
		String code=null;
		String temp="";
		for(int i=1; i<=5; i++) {
			int index=rand.nextInt(base.length());
			temp=temp+base.charAt(index);
		}
		code=temp;
		return code;
	}
}
