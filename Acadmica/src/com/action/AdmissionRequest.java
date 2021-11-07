package com.action;

import java.io.IOException;
import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.SQLException;

import javax.servlet.ServletException;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
/**
author: Vicky pl
email: vicky542011@gmail.com
mobile: 7828789845
**/
/**
 * Servlet implementation class AdmissionRequest
 */
public class AdmissionRequest extends HttpServlet {
	private static final long serialVersionUID = 1L;

	protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		//servlet name for error log file
		String servletname = this.getClass().getName();
		
		//fetching data
		String fullname = request.getParameter("fullname");
		String email = request.getParameter("email");
		String mobile = request.getParameter("mobile");
		String role = request.getParameter("role");
		
		DatabaseConnector db = new DatabaseConnector();
		
		Connection connection = null;
		PreparedStatement pstat =  null;
			try {
				connection = db.connect();
				String sql="insert into admission_requests (id, fullname, email, mobile, role) values(request_id.nextval, ?, ?, ?, ?)";
				pstat=connection.prepareStatement(sql);
				pstat.setString(1, fullname);
				pstat.setString(2, email);
				pstat.setString(3, mobile);
				pstat.setString(4, role);
				int result = pstat.executeUpdate();
				if(result>0) {
					response.sendRedirect("home.jsp?msg=Successfully Requested");
				} else {
					response.sendRedirect("home.jsp?msg=Request Failed.");
				}
			} catch (ClassNotFoundException e) {
				db.logInFile(e, servletname);
				response.sendRedirect("errorpage.jsp");
			} catch(SQLException e) {
				db.logInFile(e, servletname);
				response.sendRedirect("errorpage.jsp?e=Email Already Registered.");
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
	}
}
