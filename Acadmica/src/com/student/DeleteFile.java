package com.student;
/**
author: Vicky pl
email: vicky542011@gmail.com
mobile: 7828789845
**/
import java.io.File;
import java.io.IOException;
import java.util.ArrayList;

import javax.servlet.ServletException;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

import com.action.DatabaseConnector;

public class DeleteFile extends HttpServlet {
	private static final long serialVersionUID = 1L;
       

	protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		
		//session vlidator
		HttpSession sess = request.getSession(false);
		if(sess==null || (sess!=null && sess.getAttribute("student")==null)) {
			response.sendRedirect("home.jsp?msg=Login Required");
			return;
		}
		Student student = (Student)sess.getAttribute("student");
		
		//Db Connector
		DatabaseConnector db = new DatabaseConnector();
		
		//fetching parameter value
		String fileName = request.getParameter("url");
		
		//delete record of file from database
		StudentAction action = new StudentAction();
		boolean deleteRecordStatus=action.deleteFile(fileName);
		
		String fileLocation = db.getPathFromProperties("fileUploadPath");
		fileLocation=fileLocation+fileName;
		File delFile = new File(fileLocation);
		boolean deleteFileStatus=false;
		if(delFile.exists()) {			
			deleteFileStatus = delFile.delete();
		} else {
			deleteFileStatus=true;
		}
				
		if(deleteRecordStatus && deleteFileStatus) {
			//for showing the uploaded files;
			String sql="select * from studentfiles where regid='"+student.getRegistrationId()+"'";
			ArrayList<StudentFiles> studentFilesList=action.getStudentFiles(sql);
			sess.setAttribute("studentFilesList", studentFilesList);
			response.sendRedirect("studentfiles.jsp?msg=File Deleted.");
		} else {
			response.sendRedirect("studentfiles.jsp?msg=Failed to Deleted.");			
		}
		
	}

}
