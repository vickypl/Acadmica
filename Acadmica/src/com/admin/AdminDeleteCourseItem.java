package com.admin;
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
import com.course.CourseActions;
import com.course.CourseFiles;
import com.course.CourseVideos;

public class AdminDeleteCourseItem extends HttpServlet {
	private static final long serialVersionUID = 1L;

	protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		//session
		HttpSession sess = request.getSession(false);
		if(sess==null || (sess!=null && sess.getAttribute("admin")==null)) {
			response.sendRedirect("home.jsp?msg=Login Required");
			return;
		}
		
		//fetching params
		String courseId=request.getParameter("cid");
		String contentid=request.getParameter("id");
		String contentType=request.getParameter("type");
		String url=request.getParameter("url");
		
		//required variables
		String fileLocation=null;
		String fileFullPath=null;
		boolean isDeleted=false;
		boolean isRecordOfFileCleared=false;
		
		DatabaseConnector db = new DatabaseConnector();
		if(contentType.equals("filetype")) {
			
			fileLocation=db.getPathFromProperties("courseFiles");
			fileFullPath=fileLocation+url;
			File delFile = new File(fileFullPath);
			
			if(delFile.exists()) {
				isDeleted=delFile.delete();
			}
			
			if(isDeleted) {
				AdminAction action = new  AdminAction();
				isRecordOfFileCleared=action.deleteCourseFile(contentid);
			}
			
			if(isRecordOfFileCleared) {
				
				//fetching for updated view
				CourseActions action = new CourseActions();
				String sql="select * from course_files where courseid='"+courseId+"'";
				ArrayList<CourseFiles> courseFiles = action.getCoursesFilesList(sql);
				sess.setAttribute("courseFiles", courseFiles);
				
				response.sendRedirect("admincoursefileview.jsp?msg=File Deleted");
			} else {
				response.sendRedirect("admincoursefileview.jsp?msg=Failed to delete the file.");				
			}
			
		}
		
		if(contentType.equals("videotype")) {
			
			fileLocation=db.getPathFromProperties("courseVideos");
			
			fileFullPath=fileLocation+url;
			File delFile = new File(fileFullPath);
			isDeleted = false;
			if(delFile.exists()) {
				isDeleted=delFile.delete();
			}
			
			isRecordOfFileCleared=false;
			if(isDeleted) {
				AdminAction action = new  AdminAction();
				isRecordOfFileCleared=action.deleteCourseVideo(contentid);
			}
			
			if(isRecordOfFileCleared) {
				
				//fetching for updated view
				CourseActions action = new CourseActions();
				String sql="select * from course_videos where courseid='"+courseId+"'";
				ArrayList<CourseVideos> courseVideosList = action.getCourseVideoList(sql);
				sess.setAttribute("courseVideosList", courseVideosList);
				
				response.sendRedirect("admincoursevideoview.jsp?msg=Video Deleted");
			} else {
				response.sendRedirect("admincoursevideoview.jsp?msg=Failed to delete the video.");				
			}
		}
	}

}
