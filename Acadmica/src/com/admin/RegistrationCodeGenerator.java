package com.admin;
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

public class RegistrationCodeGenerator extends HttpServlet {
	private static final long serialVersionUID = 1L;
     
	protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		
		//session
		HttpSession sess = request.getSession(false);
		if(sess==null || (sess!=null && sess.getAttribute("admin")==null)) {
			response.sendRedirect("home.jsp?msg=Login Required");
			return;
		}
		
		//fetching parameter
		String applicationFor =request.getParameter("codefor");
		
		AdminAction action = new AdminAction();
		
		//description of the below logic
		/*
		 * first this will generate a respective regCode then it will check weather the code is already is available
		 * in  table or not, if it is already available in table then it will return false and generate new code until a uniqe code is
		 * available and the moment it will get the unique code it will store it into table with the status false
		*/
		boolean codeStatus;
		String generatedCode = null;
		boolean postStatus=false;
		codeStatus = false;
		if(applicationFor.equals("faculty")) {
			while(codeStatus!=true) {
				generatedCode = facultyCodeGeneretor();
				codeStatus=action.isFacultyCodeValid(generatedCode);
			}
			postStatus=action.statusPoster("registration_code_faculty", "reg_fac_id.nextval", generatedCode, "false");
			if(postStatus) {
				request.setAttribute("regCode", generatedCode);
				RequestDispatcher rd = request.getRequestDispatcher("deanregistrationcode.jsp");
				rd.forward(request, response);
			} else {
				response.sendRedirect("deanregistrationcode.jsp?msg=Failed to Generate Code try again.");
			}
			
		} else if(applicationFor.equals("student")) {
			while(codeStatus!=true) {
				generatedCode = studentCodeGeneretor();
				codeStatus=action.isStudentCodeValid(generatedCode);
			}
			postStatus=action.statusPoster("registration_code_student", "reg_stu_id.nextval", generatedCode, "false");
			if(postStatus) {
				request.setAttribute("regCode", generatedCode);
				RequestDispatcher rd = request.getRequestDispatcher("deanregistrationcode.jsp");
				rd.forward(request, response);
			} else {
				response.sendRedirect("deanregistrationcode.jsp?msg=Failed to Generate Code try again.");
			}
		} else if(applicationFor.equals("course")) {
			while(codeStatus!=true) {
				generatedCode = courseCodeGeneretor();
				codeStatus=action.isCourseCodeValid(generatedCode);
			}
			postStatus=action.statusPoster("registration_code_course", "course_code_req.nextval", generatedCode, "false");
			if(postStatus) {
				request.setAttribute("regCode", generatedCode);
				RequestDispatcher rd = request.getRequestDispatcher("deanregistrationcode.jsp");
				rd.forward(request, response);
			}	else {
				response.sendRedirect("deanregistrationcode.jsp?msg=Failed to Generate Code try again.");
			}		
		}
	}
	
	public String facultyCodeGeneretor() {
		String base="ABCDEFGHIJKLMNOPQRSTUVWXYZ0123456789";
		java.util.Random rand = new java.util.Random();
		String code=null;
		String temp="";
		for(int i=1; i<=8; i++) {
			int index=rand.nextInt(base.length());
			temp=temp+base.charAt(index);
		}
		code=temp;
		return code;
	}
	
	public String studentCodeGeneretor() {
		String base="0123456789";
		java.util.Random rand = new java.util.Random();
		String code=null;
		String temp="";
		for(int i=1; i<=8; i++) {
			int index=rand.nextInt(base.length());
			temp=temp+base.charAt(index);
		}
		code=temp;
		return code;
	}
	
	public String courseCodeGeneretor() {
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
