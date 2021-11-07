package com.student;
/**
author: Vicky pl
email: vicky542011@gmail.com
mobile: 7828789845
**/
import java.io.File;
import java.io.IOException;
import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;

import javax.servlet.ServletException;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

import org.apache.commons.fileupload.FileItem;
import org.apache.commons.fileupload.disk.DiskFileItemFactory;
import org.apache.commons.fileupload.servlet.ServletFileUpload;
import org.apache.commons.io.FilenameUtils;

import com.action.DatabaseConnector;

public class ProfilePicUpdate extends HttpServlet {
	private static final long serialVersionUID = 1L;

	@SuppressWarnings("unchecked")
	protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {

		
		//class name for log file
		DatabaseConnector db = new DatabaseConnector();
		String servletname = this.getClass().getName();
		
		HttpSession sess = request.getSession(false);
		if(sess==null || (sess!=null && sess.getAttribute("student")==null)) {
			response.sendRedirect("home.jsp?msg=Login Required");
			return;
		}
		Student student = (Student)sess.getAttribute("student");
				
		String fullFilePath=db.getPathFromProperties("profilePicPath");
/*		String projectPath = getServletContext().getRealPath("/");
		String uploadDirName="profilepics";
		String uploadDirFullPath=null;
		uploadDirFullPath =projectPath+uploadDirName;
		String fullFilePath=uploadDirFullPath+ File.separatorChar;*/
		
		String fileName=null;
		try {
			DiskFileItemFactory factory = new DiskFileItemFactory();
			ServletFileUpload fileUpload = new ServletFileUpload(factory);
			
			List<FileItem> fileItems = new ArrayList<FileItem>();
			fileItems = fileUpload.parseRequest(request);
			
			//deleting the old uploaded pic
			if(fileItems.size()>0) {
				String oldfilepath=fullFilePath+student.getRegistrationId()+"."+"jpg";
				File delFile = new File(oldfilepath);
				delFile.delete();
			}
			
			
			for(FileItem item : fileItems) {
				if(!item.isFormField()) {
					File newfile = new File(fullFilePath+item.getName());
					String ext = FilenameUtils.getExtension(newfile.getName());
					if(ext.equals("jpg")) {
						File newFileName = new File(fullFilePath+student.getRegistrationId()+"."+ext);
						newfile.renameTo(newFileName);
						item.write(newFileName);
						fileName=newFileName.getName();
					} else {
						break;
					}
				}
			}
			
			response.getWriter().println("upload sucessfull.."+fileName);
		} catch(Exception e) {
			db.logInFile(e, servletname);
			response.sendRedirect("errorpage.jsp");
		}

		boolean status = false;
		if(fileName!=null) {
			
			Connection connection = null;
			PreparedStatement preStatement = null;
			try {
				connection = db.connect();
				String sql ="update studentprofilepic set url=? where regid=?";
					preStatement = connection.prepareStatement(sql);
					preStatement.setString(1, student.getRegistrationId()+".jpg");
					preStatement.setString(2, student.getRegistrationId());
				
				int result = preStatement.executeUpdate();
				
				if(result>0) {
					status = true;
				} else {
					status= false;
				}
				
			} catch(ClassNotFoundException e) {
				db.logInFile(e, servletname);
				response.sendRedirect("errorpage.jsp");
			} catch(SQLException e) {
				db.logInFile(e, servletname);
				response.sendRedirect("errorpage.jsp");
			} catch(Exception e) {
				db.logInFile(e, servletname);
				response.sendRedirect("errorpage.jsp");
			} finally {
				if(connection!=null) {
					try {
						connection.close();
					} catch(SQLException e) {
						db.logInFile(e, servletname);
						response.sendRedirect("errorpage.jsp");
					}
				}
			}
		}
		if(status) {
			response.sendRedirect("studentloggedin.jsp?msg=Profile Picture Updated Succefully..");
		} else {
			response.sendRedirect("studentloggedin.jsp?msg=Image format should be jpg for dp.");
		}
	}
}