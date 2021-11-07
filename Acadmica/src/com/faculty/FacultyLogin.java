package com.faculty;
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
import com.admin.NoticeOnlyFaculty;
import com.course.Course;
import com.course.CourseActions;
import com.course.CourseFaculty;
import com.student.Student;
import com.student.StudentAction;

public class FacultyLogin extends HttpServlet {
	private static final long serialVersionUID = 1L;

	protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
			String username = request.getParameter("username");
			String password = request.getParameter("password");
			
			//db connection class
			DatabaseConnector db = new DatabaseConnector();

			//checking the number of unsuccessfull attempts
			String ipAddress=request.getLocalAddr();
			String atmptq="select * from login_logs where ipaddress='"+ipAddress+"'";
			ArrayList<LoginAttempt> attemptList = null;
			
			attemptList = db.getLoginAttempts(atmptq);

//			if(attemptList.size()>=3) {
//				response.sendRedirect("home.jsp?msg=Login Attempt Exceeded Try again in 24 hours..");
//				return;
//			}
			
			//encrypting password for match
			EncryptDecrypt encrypt = new EncryptDecrypt();
			String pass = encrypt.encryptPassword(password);
			
			FacultyAction action = new FacultyAction();
			String sql="select * from faculty where username='"+username+"' or email='"+username+"' and password='"+pass+"'";
			ArrayList<Faculty> facultyList = action.getFaculty(sql);
			
			if(facultyList.size()>0) {
				Faculty faculty = facultyList.get(0);
				
				//1 fetching course of this faculty
				CourseActions courseAction = new CourseActions();
				sql="select * from course_faculty where regid='"+faculty.getRegistrationId()+"'";
				ArrayList<CourseFaculty> courseOfFaculty = courseAction.getCourseFacultyList(sql);
				
				//2 fetching student of this faculty course wise(in case if any faculty owning multiple courses)
				StudentAction studentAction = new StudentAction();
				//this contains list of course and their name detail
				ArrayList<Course> courseList = new ArrayList<Course>();
				//HashMap here is defined as HashMap<courseid, courseStudentslist>
				HashMap<String, ArrayList<Student>> courseStudentHashmap = new HashMap<String, ArrayList<Student>>();
				ArrayList<Student> studentList = new ArrayList<Student>();
				if(courseOfFaculty.size()>0) {
					for(CourseFaculty cf : courseOfFaculty) {
						sql="select * from student s inner join course_student cs on s.id=cs.regid and cs.cid='"+cf.getCourseId()+"'";
						studentList = studentAction.getStudent(sql);
						courseStudentHashmap.put(cf.getCourseId(), studentList);
						//2.5 fetching course detail of the course list of this faculty
						sql="select * from course where course_id='"+cf.getCourseId()+"'";
						courseList.add(courseAction.getCourse(sql));
					}
				}
				
/*				//2.6 fetching content related to the course //files and videos
				HashMap<String, ArrayList<CourseFiles>> courseFilesContentHashmap = new HashMap<String, ArrayList<CourseFiles>>();
				HashMap<String, ArrayList<CourseVideos>> courseVideoContentHashmap = new HashMap<String, ArrayList<CourseVideos>>();
				if(courseList.size()>0) {
					ArrayList<CourseFiles> courseFiles = null;
					ArrayList<CourseVideos> courseVideos = null;
					for(Course course : courseList) {
						sql="select * from course_files where courseid='"+course.getCourseId()+"'";
						courseFiles = courseAction.getCoursesFilesList(sql);
						courseFilesContentHashmap.put(course.getCourseName(), courseFiles);
						sql="select * from course_videos where courseid='"+course.getCourseId()+"'";
						courseVideos = courseAction.getCourseVideoList(sql);
						courseVideoContentHashmap.put(course.getCourseName(), courseVideos);
					}
				}*/
				
				
				//3 fetching assignment history
				sql="select * from assingment where regid='"+faculty.getRegistrationId()+"'";
				ArrayList<Assignment> assignmentList = studentAction.getStudentAssingments(sql);
				
				//4 fetching doubts asked by student to this faculty
				sql="select * from doubts where facultyid='"+faculty.getRegistrationId()+"'";
				ArrayList<Doubts> doubtsList = studentAction.getStudentDoubts(sql);
				
/*				//5 fetching files uploaded by this faculty
				sql="select * from course_files where facultyid='"+faculty.getRegistrationId()+"'";
				ArrayList<CourseFiles> courseFiles = courseAction.getCoursesFilesList(sql);*/
				
				//6 fetching notice posted by this faculty
				sql="select * from faculty_notice where FACULTYID='"+faculty.getRegistrationId()+"'";
				ArrayList<FacultyNotice> noticeList = action.getFacultyNotice(sql);
				
				//7 fetching profilepic details of this faculty
				sql="select * from facultyprofilepic where regid='"+faculty.getRegistrationId()+"'";
				FacutlyProfilePic profilePicInfo = action.getFacultyProfilePic(sql);
				
				//8 fetching files of this faculty
				sql="select * from facultyFiles where regid='"+faculty.getRegistrationId()+"'";
				ArrayList<FacultyFiles> facultyFiles = action.getFacultyFiles(sql);
				
				//9 fetching dean notice for all
				AdminAction adminAction = new AdminAction();
				sql="select * from notification";
				ArrayList<AdminNotice> adminNoticeList = adminAction.getNoticeList(sql);
				//9.1 fetching notice which are only for faculty
				sql="select * from notice_only_faculty";
				ArrayList<NoticeOnlyFaculty> noticeOnlyForFacultyList  = adminAction.getNoticeOnlyforFacultyList(sql);
				
				//10 fetching subject code request by students
				sql="select * from subject_reg_code_request where faculty_id='"+faculty.getRegistrationId()+"'";
				ArrayList<SubjectRegCodeRequest> subjectCourseCodeRequestList = action.getSubjectRegCodeRequest(sql);
				
				
				//updating last login into database
				String updateSql = "update faculty set lastlogin=SYSDATE where id='"+faculty.getRegistrationId()+"'";
				boolean lastLoginUpdate=db.lastLoginUpdate(updateSql);
				if(!lastLoginUpdate) { 
					//response.sendRedirect("errorpage.jsp");
					//return;
				}
				
				if((username.equals(faculty.getUsername()) || username.equals(faculty.getEmail())) && pass.equals(faculty.getPassword())) {
					
					//deleting the false attempts from this address
					if(attemptList.size()>0) {
						sql="delete from login_logs where IPADDRESS='"+ipAddress+"'";
						boolean isFalseLoginAttemptsDeleted  = db.deleteFalseLoginAttempts(sql);
						if(!isFalseLoginAttemptsDeleted) {
							response.sendRedirect("home.jsp?msg=Something Went Wrong..");
							return;
						}
					}
					
					
					//creating session
					HttpSession session = request.getSession();
					session.setAttribute("faculty", faculty);
					session.setAttribute("courseOfFaculty", courseOfFaculty);
					session.setAttribute("courseList", courseList);
					session.setAttribute("courseStudentHashmap", courseStudentHashmap);
					session.setAttribute("subjectCourseCodeRequestList", subjectCourseCodeRequestList);
					//session.setAttribute("courseFilesContentHashmap", courseFilesContentHashmap);
					//session.setAttribute("courseVideoContentHashmap", courseVideoContentHashmap);
					session.setAttribute("assignmentList", assignmentList);
					session.setAttribute("doubtsList", doubtsList);
					//session.setAttribute("courseFiles", courseFiles);
					session.setAttribute("facultyFiles", facultyFiles);
					session.setAttribute("noticeList", noticeList); //posted by this faculty
					session.setAttribute("adminNoticeList", adminNoticeList); //posted by admin for all
					session.setAttribute("noticeOnlyForFacultyList", noticeOnlyForFacultyList); //posted by admin for only faculty
					session.setAttribute("profilePicInfo", profilePicInfo);
				 
					response.sendRedirect("facultydashboard");
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
