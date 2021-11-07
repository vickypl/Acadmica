package com.faculty;
/**
author: Vicky pl
email: vicky542011@gmail.com
mobile: 7828789845
**/
import java.io.IOException;
import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.SQLException;
import java.util.ArrayList;

import javax.servlet.ServletException;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

import com.action.DatabaseConnector;

public class FacultyNoticePoster extends HttpServlet {
	private static final long serialVersionUID = 1L;
       

	protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		
		//session
		HttpSession sess = request.getSession(false);
		if(sess==null || (sess!=null && sess.getAttribute("faculty")==null)) {
			response.sendRedirect("home.jsp?msg=Login Required");
			return;
		}
		Faculty faculty = (Faculty)sess.getAttribute("faculty");
		String facultyName = faculty.getFirstName()+" "+faculty.getLastName();
		
		//servlet name for error log file
		String servletname = this.getClass().getName();
		
		//fetching parameters
		String topic = request.getParameter("topic");
		String notice = request.getParameter("notice");
		
		DatabaseConnector db = new DatabaseConnector();
		
		Connection connection = null;
		PreparedStatement pstat = null;
		boolean noticeStatus = false;
		try {
			connection = db.connect();
			String sql="insert into faculty_notice (id, topic, notice, facultyid, postedby, postedon) values (fac_notice_id.nextval, ?, ?, ?, ?, SYSDATE)";

			pstat = connection.prepareStatement(sql);
			pstat.setString(1, topic);
			pstat.setString(2, notice);
			pstat.setString(3, faculty.getRegistrationId());
			pstat.setString(4, facultyName);
			
			int result = pstat.executeUpdate();
			if(result>0) {
				noticeStatus=true;
			} else {
				noticeStatus=false;
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
		if(noticeStatus) {
			FacultyAction facultyAction = new FacultyAction();			
			String sql="select * from faculty_notice where facultyid='"+faculty.getRegistrationId()+"'";
			ArrayList<FacultyNotice> noticeList = facultyAction.getFacultyNotice(sql);
			sess.setAttribute("noticeList", noticeList);
			response.sendRedirect("facultynoticeposter.jsp?msg=Notice Successfully Posted.");
		} else {
			response.sendRedirect("facultynoticeposter.jsp?msg=Failed to Post the notice.");			
		}
	}

}
