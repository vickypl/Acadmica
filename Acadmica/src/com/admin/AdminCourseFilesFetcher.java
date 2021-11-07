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

import com.course.Course;
import com.course.CourseActions;
import com.course.CourseFiles;

public class AdminCourseFilesFetcher extends HttpServlet {
	private static final long serialVersionUID = 1L;
       
	protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		//session
		HttpSession sess = request.getSession(false);
		if(sess==null || (sess!=null && sess.getAttribute("admin")==null)) {
			response.sendRedirect("home.jsp?msg=Login Required");
			return;
		}
		//fetching id
		String courseId = request.getParameter("cid");
		
		CourseActions action = new CourseActions();
		String sql="select * from course_files where courseid='"+courseId+"'";
		ArrayList<CourseFiles> courseFiles = action.getCoursesFilesList(sql);

		//fetching courese detail for the name of the course
		sql="select * from course where course_id='"+courseId+"'";
		Course course = action.getCourse(sql);
		String selectedCourseName = course.getCourseName();
				
		sess.setAttribute("courseFiles", courseFiles);
		sess.setAttribute("selectedCourseName", selectedCourseName);
		response.sendRedirect("admincoursefileview.jsp");
	}

}
