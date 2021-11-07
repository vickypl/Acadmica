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
import com.faculty.Faculty;
import com.faculty.FacultyAction;

public class CreateFacultyExcel extends HttpServlet {
	private static final long serialVersionUID = 1L;
       
	protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {

		//session
		HttpSession sess = request.getSession(false);
		if(sess==null || (sess!=null && sess.getAttribute("admin")==null)) {
			response.sendRedirect("deanlogin.jsp?msg=Authentication Failed.");
			return;
		}
		
		DatabaseConnector db = new DatabaseConnector();
		
		//getting faculty list
		FacultyAction facultyAction = new FacultyAction();
		String sql="select * from faculty";
		ArrayList<Faculty> facultyList = facultyAction.getFaculty(sql);
		
		if(facultyList.size()>0) {
			try {
				
				HSSFWorkbook workbook = new HSSFWorkbook();
				HSSFSheet spreadsheet = workbook.createSheet();
				
				HSSFRow row;
				int rowid=0;
				
				row=spreadsheet.createRow(rowid++);
				row.createCell(0).setCellValue("FacultyId");
				row.createCell(1).setCellValue("FirstName");
				row.createCell(2).setCellValue("LastName");
				row.createCell(3).setCellValue("Gender");
				row.createCell(4).setCellValue("Dob");
				row.createCell(5).setCellValue("MStatus");
				row.createCell(6).setCellValue("Gardian");
				row.createCell(7).setCellValue("Email");
				row.createCell(8).setCellValue("Mobile");
				row.createCell(9).setCellValue("Address1");
				row.createCell(10).setCellValue("Address2");
				row.createCell(11).setCellValue("City");
				row.createCell(12).setCellValue("State");
				row.createCell(13).setCellValue("PinCode");
				row.createCell(14).setCellValue("Subject");
				row.createCell(15).setCellValue("Username");
				row.createCell(16).setCellValue("RegDate");
				row.createCell(17).setCellValue("LastLogin");
				
				
				for(Faculty faculty : facultyList) {
					
					row = spreadsheet.createRow(rowid++);
					
					int cellId=0;
					
					Cell cell0 = row.createCell(cellId++);
					cell0.setCellValue(faculty.getRegistrationId());

					Cell cell1 = row.createCell(cellId++);
					cell1.setCellValue(faculty.getFirstName());
					
					Cell cell2 = row.createCell(cellId++);
					cell2.setCellValue(faculty.getLastName());
					
					Cell cell3 = row.createCell(cellId++);
					cell3.setCellValue(faculty.getGender());
					
					Cell cell4 = row.createCell(cellId++);
					cell4.setCellValue(faculty.getDateOfBirth());
					
					Cell cell5 = row.createCell(cellId++);
					cell5.setCellValue(faculty.getMaritalStatus());
					
					Cell cell6 = row.createCell(cellId++);
					cell6.setCellValue(faculty.getGuardianName());
					
					Cell cell7 = row.createCell(cellId++);
					cell7.setCellValue(faculty.getEmail());
					
					Cell cell8 = row.createCell(cellId++);
					cell8.setCellValue(faculty.getMobile());
					
					Cell cell9 = row.createCell(cellId++);
					cell9.setCellValue(faculty.getAddress1());
					
					Cell cell10 = row.createCell(cellId++);
					cell10.setCellValue(faculty.getAddress2());
					
					Cell cell11 = row.createCell(cellId++);
					cell11.setCellValue(faculty.getCity());
					
					Cell cell12 = row.createCell(cellId++);
					cell12.setCellValue(faculty.getState());
					
					Cell cell13 = row.createCell(cellId++);
					cell13.setCellValue(faculty.getPincode());
					
					Cell cell14 = row.createCell(cellId++);
					cell14.setCellValue(faculty.getSubject());
					
					Cell cell15 = row.createCell(cellId++);
					cell15.setCellValue(faculty.getUsername());
					
					Cell cell16 = row.createCell(cellId++);
					cell16.setCellValue(faculty.getRegistrationDate());
					
					Cell cell17 = row.createCell(cellId++);
					cell17.setCellValue(faculty.getLastLogin());
					
				} //for loop end
				
				// Writing the workbook in the file with extension .xlsx
				String backupFilePath = db.getPathFromProperties("backupPath");

				FileOutputStream fos = new FileOutputStream(new File(backupFilePath + "facultybackup.xlsx"));
				workbook.write(fos);
				fos.close();
				workbook.close();
				response.sendRedirect("deanbackups.jsp?msg=Faculty Exported Successfully..");
				
				
			} catch (Exception e) {
				db.logInFile(e, "facultyexcel");
			}
		} else {
			response.sendRedirect("deanbackups.jsp?msg=No Data to Export..");
		}
		
	}

}
