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

public class AdminDeleteNotice extends HttpServlet {
	private static final long serialVersionUID = 1L;
       

	protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		
		//session
		HttpSession sess = request.getSession(false);
		if(sess==null || (sess!=null && sess.getAttribute("admin")==null)) {
			response.sendRedirect("home.jsp?msg=Login Required");
			return;
		}
		
		//fetching perameters
		String id = request.getParameter("ide");
		String delof = request.getParameter("of");
		
		AdminAction action = new AdminAction();
		
		String sql=null;
		if(delof.equals("all")) {
			boolean isDeleted = false;
			isDeleted = action.deleteNoticeForAll(id);
			if(isDeleted) {
			
			sql="select * from notification";
			ArrayList<AdminNotice> noticeList = action.getNoticeList(sql);
			sess.setAttribute("noticeList", noticeList);
			
				response.sendRedirect("deannoticehistory.jsp?msg=Notice Deleted..");
			} else {
				response.sendRedirect("deannoticehistory.jsp?msg=Failed to Delete..");				
			}
		} else if(delof.equals("faculty")) {
			boolean isDeleted = false;
			isDeleted = action.deleteNoticeOfFaculty(id);
			
			sql="select * from NOTICE_ONLY_FACULTY";
			ArrayList<NoticeOnlyFaculty> noticeOnlyForFaculty = action.getNoticeOnlyforFacultyList(sql);
			sess.setAttribute("noticeOnlyForFaculty", noticeOnlyForFaculty);

			if(isDeleted) {
				response.sendRedirect("deannoticehistory.jsp?msg=Notice Deleted..");
			} else {
				response.sendRedirect("deannoticehistory.jsp?msg=Failed to Delete..");				
			}
		}
	}

}
