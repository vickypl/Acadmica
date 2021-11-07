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

import com.action.DatabaseConnector;
import com.action.EncryptDecrypt;
import com.course.Course;
import com.course.CourseActions;
import com.course.CourseFiles;
import com.course.CourseVideos;
import com.faculty.Faculty;
import com.faculty.FacultyAction;
import com.student.Student;
import com.student.StudentAction;

public class AdminLogin extends HttpServlet {
	private static final long serialVersionUID = 1L;

	protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		response.sendRedirect("deanlogin.jsp");
	}

	protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {

		String username = request.getParameter("username");
		String password = request.getParameter("password");
		
		//encrypting password for match
		EncryptDecrypt encrypt = new EncryptDecrypt();
		String pass = encrypt.encryptPassword(password);
		
		AdminAction action = new AdminAction();
		String sql="select * from admin where username='"+username+"' or email='"+username+"' and password='"+pass+"'";
		Admin admin = null;
					admin = action.getAdmin(sql);
		
		if(admin!=null) {
			
			//1 all students list
			StudentAction studentAction = new StudentAction();
			sql="select * from student";
			ArrayList<Student> studentList = studentAction.getStudent(sql);
			
			//2 all course List
			CourseActions courseAction = new CourseActions();
			sql="select * from course";
			ArrayList<Course> courseList = courseAction.getCoursesList(sql);
			
			//2.1 all course Videos
			sql="select * from course_videos";
			ArrayList<CourseVideos> coursesVideosList = courseAction.getCourseVideoList(sql);
			
			//2.2 all course Files
			sql="select * from course_files";
			ArrayList<CourseFiles> courseFilesList = courseAction.getCoursesFilesList(sql);
			
			//3 all faculties
			FacultyAction facultyAction = new FacultyAction();
			sql="select * from faculty";
			ArrayList<Faculty> facultyList = facultyAction.getFaculty(sql);
			
			//4 notice posted by dean
			sql="select * from notification";
			ArrayList<AdminNotice> noticeList = action.getNoticeList(sql);
			
			//5 fetching all reg codes which are pending
			ArrayList<RegistrationCode> pendingRegCodeList = action.getPendingRegCodeList();
			
			//6 fetching course code request recived from faculty
			sql="select * from course_code_request";
			ArrayList<CourseCodeRequests> courseCodeRequestsList = action.getCourseRequestList(sql);
			
			//7 fetching  notice of faculty only
			sql="select * from NOTICE_ONLY_FACULTY";
			ArrayList<NoticeOnlyFaculty> noticeOnlyForFaculty = action.getNoticeOnlyforFacultyList(sql);
			
			//8 fetching admission request list
			sql="select * from ADMISSION_REQUESTS";
			ArrayList<AdmissionRequestObject> admissionRequestList = action.getAddmissionRequestList(sql);
			
			//9 fetching pending codes of the course request.
			sql="select * from SUBJECT_REGISTRATION_CODE where status='false'";
			ArrayList<RegistrationCode> pendingSubjectRegCodeList = action.getPendingSubjectRegCodeList(sql);
			
			//updating last login
			sql="update admin set lastlogin=SYSDATE where id='"+admin.getRegid()+"'";
			DatabaseConnector db = new DatabaseConnector();
			boolean lastLoginUpdate = db.lastLoginUpdate(sql);
			if(!lastLoginUpdate) {
				response.sendRedirect("errorpage.jsp?msg=Last Login Update Problem..");
				return;
			}
			
			
			if((username.equals(admin.getUsername()) || username.equals(admin.getEmail())) && pass.equals(admin.getPassword())) {
				//creating session
				HttpSession session = request.getSession();
				session.setAttribute("admin", admin);
				session.setAttribute("studentList", studentList);
				session.setAttribute("courseList", courseList);
				session.setAttribute("coursesVideosList", coursesVideosList);
				session.setAttribute("courseFilesList", courseFilesList);
				session.setAttribute("facultyList", facultyList);
				session.setAttribute("noticeList", noticeList);
				session.setAttribute("pendingRegCodeList", pendingRegCodeList);
				session.setAttribute("courseCodeRequestsList", courseCodeRequestsList);
				session.setAttribute("noticeOnlyForFaculty", noticeOnlyForFaculty);
				session.setAttribute("admissionRequestList", admissionRequestList);
				session.setAttribute("pendingSubjectRegCodeList", pendingSubjectRegCodeList);
				
				response.sendRedirect("facultydashboard");
			} else {				
				response.sendRedirect("home.jsp?msg=Invalid/Login Password");
			}
		}
	}

}
