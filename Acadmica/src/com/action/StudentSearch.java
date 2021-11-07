package com.action;
/**
author: Vicky pl
email: vicky542011@gmail.com
mobile: 7828789845
**/
import java.io.IOException;
import java.io.PrintWriter;
import java.util.ArrayList;
import javax.servlet.ServletException;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

import com.student.Student;
import com.student.StudentAction;

public class StudentSearch extends HttpServlet {
	private static final long serialVersionUID = 1L;
       
	protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {

		HttpSession sess = request.getSession(false);
		if(sess==null /*|| (sess!=null && sess.getAttribute("admin")==null)*/) {
			response.sendRedirect("deanlogin.jsp?msg=Authentication Failed.");
			return;
		}
		
		String param = request.getParameter("q");
		param=param.toLowerCase();
		StudentAction action = new StudentAction();
		
		String sql="select * from student where LOWER(firstname) like '%"+param+"%' or  id like '%"+param+"%' or LOWER(city) like '%"+param+"%' or LOWER(state) like '%"+param+"%'";
		ArrayList<Student> studentResultList = action.getStudent(sql);
		
		PrintWriter out = response.getWriter();
		
//		presentation view
			out.print("<table style='color: white;' class='table sortable'>");
			out.print("<thead>");
				out.print("<tr class='ho'>");
					out.print("<th scope='col'>S.no </th>");
					out.print("<th scope='col'>Registration code</th>");
					out.print("<th scope='col'>Name</th>");
					out.print("<th scope='col'>E-mail</th>");
					out.print("<th scope='col'>Action</th>");
				out.print("</tr>");
			out.print("</thead>");
			out.print("<tbody>");
			long count=1; for(Student stu : studentResultList) {
				out.print("<tr class='ho'>");
					out.print("<th scope='row'>"+count+"</th>");
					out.print("<th>"+stu.getRegistrationId()+"</th>");
					out.print("<td>"+stu.getFirstName()+" "+stu.getLastName()+"</td>");
					out.print("<td>"+stu.getEmail()+"</td>");
					out.print("<td><a href='adminstudentfulldetail?studentid="+stu.getRegistrationId()+"'>ViewFullDetails</a></td>");
				out.print("</tr>");
				count++; 
				}
				out.print("<tr>");
				out.print("<td>No Data Found</td>");
				out.print("</tr>");
			out.print("</tbody>");
		out.print("</table>");	
	}
}
