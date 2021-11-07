package com.student;

import java.io.IOException;
import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.SQLException;

import javax.servlet.ServletException;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

import com.action.DatabaseConnector;
import com.admin.AdminAction;
import com.admin.RegistrationCode;

public class AddMoreCourse extends HttpServlet {
	private static final long serialVersionUID = 1L;
       
	protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		
		//servlet name
		String servletname = this.getClass().getName();
		
		//session
		HttpSession sess = request.getSession(false);
		if(sess==null || (sess!=null && sess.getAttribute("student")==null)) {
			response.sendRedirect("home.jsp?msg=Login Required");
			return;
		}
		Student student = (Student)sess.getAttribute("student");
		
		
		//fetching params
		String courseNameCode = request.getParameter("course");
		String code = request.getParameter("code");
		
		String[] courseNameId = courseNameCode.split(" ");
//		String courseName=courseNameId[0];
		String courseId=courseNameId[1];
		
		
		AdminAction adminAction = new AdminAction();
		String sql="select * from subject_registration_code where code='"+code+"'";
		RegistrationCode subRejCode = null;
		subRejCode=adminAction.getRegCode(sql);
		
		if(subRejCode==null) {
			response.sendRedirect("studentsubjectlist.jsp?msg=Invalid Code..");
			return;
		}
		
		if(subRejCode.getStatus().equals("true")) {
			response.sendRedirect("studentsubjectlist.jsp?msg=Code Already Expired..");
			return;
		}
		

		DatabaseConnector db = new DatabaseConnector();
		
		Connection connection = null;
		PreparedStatement pstat = null;
	
		boolean addStatus=false;
		boolean codeExpired=false;
		try {
			connection = db.connect();
			sql="insert into course_student (id, regid, cid) values(course_stu_num.nextval, ?, ?)";
			pstat=connection.prepareStatement(sql);
			
			pstat.setString(1, student.getRegistrationId());
			pstat.setString(2, courseId);
			
			int result = pstat.executeUpdate();
			
			if(result>0) {
				addStatus=true;
			}
			
			sql="update subject_registration_code set status='true' where code=?";
			pstat= connection.prepareStatement(sql);
			pstat.setString(1, code);
			int r = pstat.executeUpdate();
			
			if(r>0) {
				codeExpired=true;
			}
			
			
			
		} catch (ClassNotFoundException e) {
			db.logInFile(e, servletname);
		} catch (SQLException e) {
			db.logInFile(e, servletname);
		} catch (Exception e) {
			db.logInFile(e, servletname);
		} finally {
			if(connection!=null) {
				try {
					connection.close();
				} catch(SQLException e) {
					db.logInFile(e, servletname);
				}
			}
		}
		
		if(addStatus && codeExpired) {
			response.sendRedirect("studentsubjectlist.jsp?msg=subject Successfully added....re-login & check.");
		} else {
			response.sendRedirect("studentsubjectlist.jsp?msg=Error in adding Subject , try later");		
		}
	}

}
