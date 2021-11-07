package com.faculty;
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
import java.util.Calendar;
import java.util.List;

import javax.servlet.ServletException;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

import org.apache.commons.fileupload.FileItem;
import org.apache.commons.fileupload.FileUploadException;
import org.apache.commons.fileupload.disk.DiskFileItemFactory;
import org.apache.commons.fileupload.servlet.ServletFileUpload;
import org.apache.commons.io.FilenameUtils;

import com.action.DatabaseConnector;

public class FacultyFileUpload extends HttpServlet {
	private static final long serialVersionUID = 1L;

	@SuppressWarnings("unchecked")
	protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		
		//session vlidator
		HttpSession sess = request.getSession(false);
		if(sess==null || (sess!=null && sess.getAttribute("faculty")==null)) {
			response.sendRedirect("home.jsp?msg=Login Required");
			return;
		}
		Faculty faculty = (Faculty)sess.getAttribute("faculty");
		
		//servlet name for log file
		String servletname=this.getClass().getName();
		
		//Db Connector
		DatabaseConnector db = new DatabaseConnector();
		
		//Max file size
		final long MAX_FILE_SIZE = 20000;
		
		boolean isMultiPart  = ServletFileUpload.isMultipartContent(request);
		boolean uploadStatus=false; //for file upload
		boolean updateStatus=false; //for database
		if(isMultiPart) {
			
			List<FileItem> itemsList = null; 
			ServletFileUpload sfu = new ServletFileUpload(new DiskFileItemFactory());
				 try {
					 itemsList=sfu.parseRequest(request);
				 } catch(FileUploadException e) {
					 db.logInFile(e, servletname);
				 }
				 FileItem title = itemsList.get(0);
				 FileItem file = itemsList.get(1);
				
				 //file title for db info
				 String fileTitle = title.getString();
				 
				 //upload location from properties file
				 String uploadLocation = db.getPathFromProperties("fileUploadPath");
				 
				 //validating file properties
				 long fileSize = file.getSize()/1024;
				 String ext = FilenameUtils.getExtension(file.getName());
				 boolean allowed = fileValidator(ext);
				 
				 String url=null;
				 if(fileSize<=MAX_FILE_SIZE && allowed) {
					 for (FileItem fileItem : itemsList) {
							if (!fileItem.isFormField()) {
								File newFile = new File(uploadLocation+file.getName());
								try {
									File newFileName = new File(uploadLocation+Calendar.getInstance().getTimeInMillis()+faculty.getRegistrationId()+Calendar.getInstance().getTimeInMillis()+"."+ext);
									newFile.renameTo(newFileName);
									fileItem.write(newFileName);
									url=newFileName.getName();
									uploadStatus=true;
								} catch (Exception e) {
									db.logInFile(e, servletname);
									response.sendRedirect("errorpage.jsp");
								}
							}
						}

					 //uploading db info of file in database
					 if(uploadStatus) {
							Connection connection = null;
							PreparedStatement preStatement = null;
							try {
								connection = db.connect();
								String sql ="insert into facultyFiles (id, title, filetype, filesize, uploaddate, regid, url) values(stu_file_num.nextval, ?, ?, ?, sysdate, ?, ?)";
									preStatement = connection.prepareStatement(sql);
									preStatement.setString(1, fileTitle);
									preStatement.setString(2, ext);
									preStatement.setString(3, fileSize+"mb");
									preStatement.setString(4, faculty.getRegistrationId());
									preStatement.setString(5, url);
								
								int result = preStatement.executeUpdate();
								
								if(result>0) {
									updateStatus = true;
								} else {
									updateStatus= false;
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
							//for showing the uploaded files;
							FacultyAction action = new FacultyAction();
							String sql="select * from facultyFiles where regid='"+faculty.getRegistrationId()+"'";
							ArrayList<FacultyFiles> facultyFiles=action.getFacultyFiles(sql);
							sess.setAttribute("facultyFiles", facultyFiles);
					 }
						if(updateStatus) {
							response.sendRedirect("facultystudymaterial.jsp?msg=File Successfully Uploaded.");
						}
				 } else {					 
					 response.sendRedirect("facultystudymaterial.jsp?msg=File Size Should be less then 15mb(Supports pdf | docx | txt ).");
				 }
				 
				  
		} else {
			response.sendRedirect("facultystudymaterial.jsp?msg=Something Went Wrong!.");
		}
	}

	protected boolean fileValidator(String ext) {
		if(ext.equals("pdf")) {
			return true;
		} else if(ext.equals("txt")) {
			return true;
		} else if(ext.equals("docx")) {
			return true;
		} else if(ext.equals("doc")) {
			return true;
		}
		return false;
	}
	
}
