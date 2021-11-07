package com.faculty;
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

public class DeleteFacultyFile extends HttpServlet {
	private static final long serialVersionUID = 1L;

	protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {

		//session check
		HttpSession sess = request.getSession(false);
		if(sess==null || (sess!=null && sess.getAttribute("faculty")==null)) {
			response.sendRedirect("home.jsp?msg=Login Required");
			return;
		}
		Faculty faculty = (Faculty)sess.getAttribute("faculty");
		
		//Db Connector
		DatabaseConnector db = new DatabaseConnector();
		
		//fetching parameter value
		String fileName = request.getParameter("url");
		
		//delete record of file from database
		FacultyAction action = new FacultyAction();
		boolean deleteRecordStatus=action.deleteFacultyFile(fileName);
		
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
			String sql="select * from facultyFiles where regid='"+faculty.getRegistrationId()+"'";
			ArrayList<FacultyFiles> facultyFiles=action.getFacultyFiles(sql);
			sess.setAttribute("facultyFiles", facultyFiles);
			response.sendRedirect("facultystudymaterial.jsp?msg=File Deleted.");
		} else {
			response.sendRedirect("facultystudymaterial.jsp?msg=Failed to Deleted.");	
		}
	}

}
