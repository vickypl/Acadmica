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

public class DeleteAdmissionRequest extends HttpServlet {
	private static final long serialVersionUID = 1L;
       

	protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		
		//session
		HttpSession sess = request.getSession(false);
		if (sess == null
				|| (sess != null && sess.getAttribute("admin") == null)) {
			response.sendRedirect("home.jsp?msg=Login Required");
			return;
		}
		
		String idToDel = request.getParameter("id");
		
		AdminAction action = new AdminAction();
		boolean isDeleted = action.deleteAdmissionRequest(idToDel);
		
		if(isDeleted) {
			String sql="select * from ADMISSION_REQUESTS";
			ArrayList<AdmissionRequestObject> admissionRequestList = action.getAddmissionRequestList(sql);
			sess.setAttribute("admissionRequestList", admissionRequestList);
			response.sendRedirect("deanadmissionrequest.jsp?msg=Request Successfully Deleted..");
		} else {
			response.sendRedirect("deanadmissionrequest.jsp?msg=Failed to Deleted..");			
		}
	}

}
