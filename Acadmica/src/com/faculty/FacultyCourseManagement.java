package com.faculty;
/**
author: Vicky pl
email: vicky542011@gmail.com
mobile: 7828789845
**/
import java.io.IOException;
import java.util.Calendar;

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
import java.io.File;

import com.action.DatabaseConnector;
import com.course.Course;
import com.course.CourseActions;
public class FacultyCourseManagement extends HttpServlet {
	private static final long serialVersionUID = 1L;
       
	protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		
		HttpSession sess = request.getSession(false);
		if (sess == null || (sess != null && sess.getAttribute("faculty") == null)) {
			response.sendRedirect("home.jsp?msg=Login Required");
			return;
		}
		
		String courseCode = request.getParameter("cid");
		CourseActions action = new CourseActions();
		String sql="select * from course where course_id='"+courseCode+"'";
		Course course = null;
					course=action.getCourse(sql);
		sess.setAttribute("selectedCourse", course);
		response.sendRedirect("facultycoursemanagement.jsp");
	}
	
	@SuppressWarnings("unchecked")
	protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {

		HttpSession sess = request.getSession(false);
		if (sess == null || (sess != null && sess.getAttribute("faculty") == null)) {
			response.sendRedirect("home.jsp?msg=Login Required");
			return;
		}
		Faculty faculty = (Faculty)sess.getAttribute("faculty");
		
		
		boolean isMultiPartContent  = ServletFileUpload.isMultipartContent(request);
		
		
		final long MAX_FILE_SIZE=1024*50;
		final long MAX_VIDEO_SIZE=1024*50*10;
		String fileName=null;
		String courseId=null;
		String fileType=null;
		String fileTitle=null;
		String url=null;
		if(isMultiPartContent) {
			//class name for log file
			DatabaseConnector db = new DatabaseConnector();
			String servletname = this.getClass().getName();
			
			//respective paths file and videos type from the properties files;
			String fileUploadPath= db.getPathFromProperties("courseFiles");
			String videoUploadPath= db.getPathFromProperties("courseVideos");
			
			//file uploading logic
			java.util.List<FileItem> itemList = null;
			try {
				ServletFileUpload servletFileUpload = new ServletFileUpload(new DiskFileItemFactory());
				try {
					itemList  = servletFileUpload.parseRequest(request);
				} catch (FileUploadException e) {
					e.printStackTrace();
				}

				//fetching items one by one
				FileItem courseIdFileItem = itemList.get(0);
				FileItem typeFileItem = itemList.get(1);
				FileItem titleFileItem = itemList.get(2);
				FileItem docFileItem = itemList.get(3);
				
				long size=docFileItem.getSize()/1024;
				
				//converting the fileitems to respective formats
				courseId=courseIdFileItem.getString();
				fileType=typeFileItem.getString();
				fileTitle=titleFileItem.getString();
				fileName=docFileItem.getName();
						
				
				//checking weather the object is of video or file
				if(fileType.equals("filetype")) { //if its a file
					
					if(size>MAX_FILE_SIZE) {
						response.sendRedirect("facultycoursemanagement.jsp?msg=File Size Too Large.");
						return;						
					}
					
					String ext = FilenameUtils.getExtension(docFileItem.getName());
					boolean isValid = isFileValid(ext);
					if(!isValid) { //is file is not of the accepted format
						response.sendRedirect("facultycoursemanagement.jsp?msg=Invalid File Format.");
						return;
					}
					
					String finalUploadPath = fileUploadPath+Calendar.getInstance().getTimeInMillis()+"."+ext;
					
					//uploading the file
					File newFile = new File(finalUploadPath);
					docFileItem.write(newFile);
					
					
					//database registry of the file info
					FacultyAction action = new FacultyAction();
					String facultyId=faculty.getRegistrationId();
					url=newFile.getName();

					boolean isUploaded = action.fileInfoUpload(courseId, facultyId, fileTitle, url);
					
					if(isUploaded) {						
						String msg = fileName+" "+"Successfully Uploaded..";
						response.sendRedirect("facultycoursemanagement.jsp?msg="+msg);
					} else {
						String msg = fileName+" "+"Failed to upload..";
						response.sendRedirect("facultycoursemanagement.jsp?msg="+msg);
					}
					
					
				} else if(fileType.equals("videotype")) { //if its a video
					
					if(size>MAX_VIDEO_SIZE) {
						response.sendRedirect("facultycoursemanagement.jsp?msg=File Size Too Large.");
						return;						
					}
					
					
					String ext = FilenameUtils.getExtension(docFileItem.getName());
					boolean isValid = isVideoValid(ext);
					if(!isValid) { //is file is not of the accepted format
						response.sendRedirect("facultycoursemanagement.jsp?msg=Invalid File Format.");
						return;
					}
					
					String finalUploadPath = videoUploadPath+Calendar.getInstance().getTimeInMillis()+"."+ext;
					
					//uploading the file
					File newFile = new File(finalUploadPath);
					docFileItem.write(newFile);
					
					
					//database registry of the video info
					FacultyAction action = new FacultyAction();
					String facultyId=faculty.getRegistrationId();
					url=newFile.getName();

					boolean isUploaded = action.videoInfoUpload(courseId, facultyId, fileTitle, url);
					
					if(isUploaded) {						
						String msg = fileName+" "+"Successfully Uploaded..";
						response.sendRedirect("facultycoursemanagement.jsp?msg="+msg);
					} else {
						String msg = fileName+" "+"Failed to upload..";
						response.sendRedirect("facultycoursemanagement.jsp?msg="+msg);
					}
					
				}
				
			} catch(Exception e) {
				db.logInFile(e, servletname);
				response.sendRedirect("errorpage.jsp");
			}

		} else {
			response.sendRedirect("facultycoursemanagement.jsp?msg=Problem in uploading file.");
		}
	}
	
	public boolean isFileValid(String ext) {
		ext=ext.toLowerCase();
		if(ext.equals("pdf")) {
			return true;
		} else if(ext.equals(ext.equals("txt"))) {
			return true;
		} else if(ext.equals("docx")) {
			return true;
		}
		return false;
	}
	
	public boolean isVideoValid(String ext) {
		ext=ext.toLowerCase();
		if(ext.equals("mp4")) {
			return true;
		} else if(ext.equals(ext.equals("avi"))) {
			return true;
		} else if(ext.equals("webm")) {
			return true; 
		}
		return false;
	}
}
