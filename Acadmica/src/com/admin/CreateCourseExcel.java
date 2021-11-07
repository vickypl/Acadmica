package com.admin;

import java.io.File;
import java.io.FileOutputStream;
import java.io.IOException;
import java.util.ArrayList;

import javax.servlet.ServletException;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

import org.apache.poi.hssf.usermodel.HSSFRow;
import org.apache.poi.hssf.usermodel.HSSFSheet;
import org.apache.poi.hssf.usermodel.HSSFWorkbook;
import org.apache.poi.ss.usermodel.Cell;

import com.action.DatabaseConnector;
import com.course.Course;
import com.course.CourseActions;

public class CreateCourseExcel extends HttpServlet {
	private static final long serialVersionUID = 1L;

	protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {

		//session
		HttpSession sess = request.getSession(false);
		if(sess==null || (sess!=null && sess.getAttribute("admin")==null)) {
			response.sendRedirect("deanlogin.jsp?msg=Authentication Failed.");
			return;
		}
		
		DatabaseConnector db = new DatabaseConnector();
		
		CourseActions courseAction = new CourseActions();
		String sql="select * from course";
		ArrayList<Course> courseList = courseAction.getCoursesList(sql);
		
		if(courseList.size()>0) {
			try {
				
				HSSFWorkbook workbook = new HSSFWorkbook();
				HSSFSheet spreadsheet = workbook.createSheet();
				
				HSSFRow row;
				int rowId=0;
				
				row=spreadsheet.createRow(rowId++);
				row.createCell(0).setCellValue("Serial id");
				row.createCell(1).setCellValue("Course Id");
				row.createCell(2).setCellValue("Course Name");
				
				for(Course course : courseList) {
					row=spreadsheet.createRow(rowId++);
					int cellId=0;
					
					Cell cell0 = row.createCell(cellId++);
					cell0.setCellValue(course.getId());
					
					Cell cell1 = row.createCell(cellId++);
					cell1.setCellValue(course.getCourseId());
					
					Cell cell2 = row.createCell(cellId++);
					cell2.setCellValue(course.getCourseName());

				}
				
				// Writing the workbook in the file with extension .xlsx
				String backupFilePath = db.getPathFromProperties("backupPath");

				FileOutputStream fos = new FileOutputStream(new File(backupFilePath + "coursebackup.xlsx"));
				workbook.write(fos);
				fos.close();
				workbook.close();
				response.sendRedirect("deanbackups.jsp?msg=Courses Exported Successfully..");
				
				
			} catch (Exception e) {
				db.logInFile(e, "courseexcel");
			}
		} else {
			response.sendRedirect("deanbackups.jsp?msg=No Data to Export..");
		}
	}

}
