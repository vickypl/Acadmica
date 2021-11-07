package com.admin;

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
/**
author: Vicky pl
email: vicky542011@gmail.com
mobile: 7828789845
**/
/**
 * Servlet implementation class AdminNoticePoster
 */
public class AdminNoticePoster extends HttpServlet {
	private static final long serialVersionUID = 1L;

	protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		response.sendRedirect("deannoticeposter.jsp");
	}

	protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {

		HttpSession sess = request.getSession(false);
		if(sess==null || (sess!=null && sess.getAttribute("admin")==null)) {
			response.sendRedirect("home.jsp?msg=Login Required");
			return;
		}
		
		//servlet name for error log file
		String servletname = this.getClass().getName();
		
		String noticeTo = request.getParameter("noticeto");
		String topic = request.getParameter("topic");
		String notice = request.getParameter("notice");
		
		//database Connector class
		DatabaseConnector db = new DatabaseConnector();
		Connection connection=null;
		PreparedStatement pstat = null;
		
		boolean postStatus=false;
		try {
			connection = db.connect();
			String sql=null;
			if(noticeTo.equals("faculty")) {
				sql="insert into notice_only_faculty (id, topic, notice, postedon) values (noticeonlyfacutlyid.nextval, ?, ?, SYSDATE)";
				pstat=connection.prepareStatement(sql);				
			} else if(noticeTo.equals("all")){		
				sql="insert into notification (id, topic, notice, postedon) values (notific_id.nextval, ?, ?, SYSDATE)";
				pstat=connection.prepareStatement(sql);
			}
			
			pstat.setString(1, topic);
			pstat.setString(2, notice);
			int result=pstat.executeUpdate();
			
			if(result>0) {
				postStatus=true;
			} else {
				postStatus=false;				
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
		if(postStatus) {
			AdminAction action = new AdminAction();
			String sql="select * from notification";
			ArrayList<AdminNotice> noticeList = action.getNoticeList(sql);
			sess.setAttribute("noticeList", noticeList);
			response.sendRedirect("deannoticehistory.jsp");
		} else {
			response.sendRedirect("deannoticeposter.jsp?msg=Failed to Post the notice.");	
		}
	}

}
