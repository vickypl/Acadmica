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

import javax.servlet.RequestDispatcher;
import javax.servlet.ServletException;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

import com.action.DatabaseConnector;
import com.student.StudentAction;

public class StudentDoubtResponse extends HttpServlet {
	private static final long serialVersionUID = 1L;
       
	protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		
		HttpSession sess = request.getSession(false);
		if (sess == null || (sess != null && sess.getAttribute("faculty") == null)) {
			response.sendRedirect("home.jsp?msg=Login Required");
			return;
		}
		
		String doubtId=request.getParameter("id1");

		//fetching the doubt which is responded
		StudentAction action = new StudentAction();
		String sql= "select * from doubts where id='"+doubtId+"'";
		Doubts doubt = action.getDoubt(sql);
		
		request.setAttribute("doubt", doubt);
		
		RequestDispatcher rd = request.getRequestDispatcher("facultydoubtresponse.jsp");
		rd.forward(request, response);
	}

	protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		
		HttpSession sess = request.getSession(false);
		if (sess == null || (sess != null && sess.getAttribute("faculty") == null)) {
			response.sendRedirect("home.jsp?msg=Login Required");
			return;
		}
		Faculty faculty = (Faculty)sess.getAttribute("faculty");
		
		String id=request.getParameter("id");
		String studentId=request.getParameter("studentid");
		String postedby=request.getParameter("postedby");
		String solution=request.getParameter("solution");
		
		//faculty name for doubt response
		String facultyname=faculty.getFirstName()+" "+faculty.getLastName();
		
		
		DatabaseConnector db = new DatabaseConnector();
		Connection connection = null;
		PreparedStatement  pstat = null;
		
		boolean responseStatus=false;
		try {
			connection = db.connect();
			String sql = "insert into doubt_response (id, name, regid, response, respondedby, respondedon) values (?, ?, ?, ?, ?, SYSDATE)";
			pstat = connection.prepareStatement(sql);
			pstat.setString(1, id);
			pstat.setString(2, postedby);
			pstat.setString(3, studentId);
			pstat.setString(4, solution);
			pstat.setString(5, facultyname);
			
			int result = pstat.executeUpdate();
			if(result>0) {
				responseStatus=true;
			} else {
				responseStatus=false;
			}
			
		} catch(ClassNotFoundException e) {
			
		} catch (SQLException e) {
			
		} catch (Exception e) {

		} finally {
			if(connection!=null) {
				try {
					connection.close();
				} catch(Exception e) {
					
				}
			}
		}
		if(responseStatus) {
			response.sendRedirect("studentdoubtsection.jsp?msg=Responded Successfully..");
		} else {
			response.sendRedirect("facultydoubtresponse.jsp?msg=Failed to Respond..");
		}
	}
}
