package com.student;
/**
author: Vicky pl
email: vicky542011@gmail.com
mobile: 7828789845
**/
import java.io.IOException;
import java.util.ArrayList;
import java.util.HashMap;

import javax.servlet.ServletException;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

import com.action.DatabaseConnector;
import com.action.EncryptDecrypt;
import com.action.LoginAttempt;
import com.admin.AdminAction;
import com.admin.AdminNotice;
import com.course.Course;
import com.course.CourseActions;
import com.faculty.Assignment;
import com.faculty.DoubtResponse;
import com.faculty.Doubts;
import com.faculty.Faculty;
import com.faculty.FacultyAction;
import com.faculty.FacultyNotice;


public class StudentLogin extends HttpServlet {
	private static final long serialVersionUID = 1L;

	protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		
		//database connector
		DatabaseConnector db = new DatabaseConnector();
		
		//checking the number of unsuccessfull attempts
		String ipAddress=request.getLocalAddr();
		String atmptq="select * from login_logs where ipaddress='"+ipAddress+"'";
		ArrayList<LoginAttempt> attemptList = null;
		
		attemptList = db.getLoginAttempts(atmptq);

		if(attemptList.size()>=3) {
			response.sendRedirect("home.jsp?msg=Login Attempt Exceeded Try again in 24 hours..");
			return;
		}
		
		//fetching parameters
		String username = request.getParameter("username");
		String password = request.getParameter("password");

		//encrypting password for match
		EncryptDecrypt encrypt = new EncryptDecrypt();
		String pass = encrypt.encryptPassword(password);
		
		
		StudentAction action = new StudentAction();
		
		//sql
		String sql="select * from student where username='"+username+"' or email='"+username+"' and password='"+pass+"'";
		
		ArrayList<Student> studentList = action.getStudent(sql);
		if (studentList.size()>0) {
			Student student = studentList.get(0);
		
			//collecting whatever information this student need
			
			//1 fetching courses of this student
			CourseActions courseAction = new CourseActions();
			sql="select * from course inner join course_student on course_student.regid='"+student.getRegistrationId()+"' and course_student.cid=course.course_id";
			ArrayList<Course> courseOfStudent = courseAction.getCoursesList(sql);
			
			//1.1 fetching list of all available courses
			sql="select * from course";
			ArrayList<Course> coursesList = courseAction.getCoursesList(sql);
			
			//2 fetching faculty of this student
			FacultyAction facultyAction = new FacultyAction();
			ArrayList<Faculty> facultyList = new ArrayList<Faculty>();
			if(courseOfStudent.size()>0) {
				for(Course c : courseOfStudent) {
					Faculty faculty = new Faculty();
					sql="select * from faculty inner join course_faculty cf on cf.cid='"+c.getCourseId()+"' and faculty.id=cf.regid";
					faculty=facultyAction.getSingleFaculty(sql);
					facultyList.add(faculty);
				}
			}
			//3 fetching this student's profilePic Info
			sql="select * from STUDENTPROFILEPIC where regid='"+student.getRegistrationId()+"'";
			StudentProfilePic studentProPic = action.getStudentProfilePic(sql);
			
			//4 fetching this student's filesInfo
			sql="select * from studentfiles where regid='"+student.getRegistrationId()+"'";
			ArrayList<StudentFiles> studentFilesList=action.getStudentFiles(sql);
			
			//5 fetching  this student's doubts's
			sql="select * from doubts where studentid='"+student.getRegistrationId()+"'";
			ArrayList<Doubts> doubtList = action.getStudentDoubts(sql);
			
			//6 fetching this student's doubt responses
			sql="select * from doubt_response where regid='"+student.getRegistrationId()+"'";
			ArrayList<DoubtResponse> doubtResponsesList = action.getStudentDoubtsResponses(sql);

			//7 fetching this student's assignments
			HashMap<String, ArrayList<Assignment>> facultyAssignmentMap  = new HashMap<String, ArrayList<Assignment>>();
			HashMap<String, ArrayList<FacultyNotice>> facultyNoticesMap  = new HashMap<String, ArrayList<FacultyNotice>>();
			ArrayList<Assignment> assignmentList = null;
			ArrayList<FacultyNotice> facultyNoticeForStudentList = null;  //for notices
			if(facultyList.size()>0) {
				for(Faculty f : facultyList) {
					assignmentList = new ArrayList<Assignment>();
					sql="select * from assingment where regid='"+f.getRegistrationId()+"'";
					assignmentList=action.getStudentAssingments(sql);
					facultyAssignmentMap.put(f.getRegistrationId(), assignmentList);
					sql="select * from faculty_notice where facultyid='"+f.getRegistrationId()+"'";
					facultyNoticeForStudentList  = facultyAction.getFacultyNotice(sql);
					facultyNoticesMap.put(f.getRegistrationId(), facultyNoticeForStudentList);
				}
			}
			
			//8 fetching noticeOfAdmin
			AdminAction adminAction = new AdminAction();
			sql="select * from notification";
			ArrayList<AdminNotice> adminNoticeList = adminAction.getNoticeList(sql);
			
			
			//updating last login into database
			String updateSql = "update student set LASTLOGIN=SYSDATE where id='"+student.getRegistrationId()+"'";
			boolean lastLoginUpdate=db.lastLoginUpdate(updateSql);
			if(!lastLoginUpdate) {
				//System.out.println("Last login update problem.");
				response.sendRedirect("errorpage.jsp");
				return;
			}
			
			if((username.equals(student.getUsername()) || username.equals(student.getEmail())) && pass.equals(student.getPassword())) {
				
				//deleting the false attempts from this address
				if(attemptList.size()>0) {
					sql="delete from login_logs where IPADDRESS='"+ipAddress+"'";
					boolean isFalseLoginAttemptsDeleted  = db.deleteFalseLoginAttempts(sql);
					if(!isFalseLoginAttemptsDeleted) {
						//System.out.println("false attempts not deleted");
						response.sendRedirect("home.jsp?msg=Something Went Wrong..");
						return;
					}
				}
				
				//creating session
				HttpSession session = request.getSession();
				session.setAttribute("student", student);
				session.setAttribute("courseOfStudent", courseOfStudent);
				session.setAttribute("facultyList", facultyList);
				session.setAttribute("studentProPic", studentProPic);
				session.setAttribute("studentFilesList", studentFilesList);
				session.setAttribute("doubtList", doubtList);
				session.setAttribute("doubtResponsesList", doubtResponsesList);
				session.setAttribute("facultyAssignmentMap", facultyAssignmentMap);
				session.setAttribute("coursesList", coursesList);
				session.setAttribute("adminNoticeList", adminNoticeList);
				session.setAttribute("facultyNoticesMap", facultyNoticesMap);
			 
				response.sendRedirect("studentdashboard");
			} else {				
				response.sendRedirect("home.jsp?msg=Invalid/Login Password");
			}
		} else {
			//updating attempt if password is wrong
			ipAddress=request.getLocalAddr();
			long timing= System.currentTimeMillis();
			boolean isUpdated = db.updateLoginAttempt(ipAddress, timing);
			if(isUpdated) {
				response.sendRedirect("home.jsp?msg=Invalid/Login Password");
			} else {
				response.sendRedirect("home.jsp?msg=Invalid/Login Password");				
			}
		}
	}
}
