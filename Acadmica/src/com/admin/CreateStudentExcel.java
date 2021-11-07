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
import com.student.Student;
import com.student.StudentAction;

public class CreateStudentExcel extends HttpServlet {
	private static final long serialVersionUID = 1L;

	protected void doGet(HttpServletRequest request,
			HttpServletResponse response) throws ServletException, IOException {

		HttpSession sess = request.getSession(false);
		if(sess==null || (sess!=null && sess.getAttribute("admin")==null)) {
			response.sendRedirect("deanlogin.jsp?msg=Authentication Failed.");
			return;
		}
		
		
		// getting all the student to fill into spreadsheet
		DatabaseConnector db = new DatabaseConnector();

		StudentAction studentAction = new StudentAction();
		String sql = "select * from student";
		ArrayList<Student> studentList = studentAction.getStudent(sql);

		if (studentList.size() > 0) {

			try {

				// create excel spreadsheet for the backup and uploading student
				// data into sheet
				HSSFWorkbook workbook = new HSSFWorkbook();
				HSSFSheet spreadsheet = workbook
						.createSheet("Student Data Sheet");

				HSSFRow row;
				int rowid = 0;

				// creating heading of the spreadsheet
				row = spreadsheet.createRow(rowid++);
				row.createCell(0).setCellValue("StudentId");
				row.createCell(1).setCellValue("First Name");
				row.createCell(2).setCellValue("Last Name");
				row.createCell(3).setCellValue("Gender");
				row.createCell(4).setCellValue("Dob");
				row.createCell(5).setCellValue("Category");
				row.createCell(6).setCellValue("Father's Name");
				row.createCell(7).setCellValue("Mother's Name");
				row.createCell(8).setCellValue("Standard");
				row.createCell(9).setCellValue("Course");
				row.createCell(10).setCellValue("School");
				row.createCell(11).setCellValue("College");
				row.createCell(12).setCellValue("Degree");
				row.createCell(13).setCellValue("Marks");
				row.createCell(14).setCellValue("Email");
				row.createCell(15).setCellValue("Mobile");
				row.createCell(16).setCellValue("FMobile");
				row.createCell(17).setCellValue("Address1");
				row.createCell(18).setCellValue("Address2");
				row.createCell(19).setCellValue("City");
				row.createCell(20).setCellValue("State");
				row.createCell(21).setCellValue("Pincode");
				row.createCell(22).setCellValue("Subject");
				row.createCell(23).setCellValue("Username");
				row.createCell(24).setCellValue("RegDate");
				row.createCell(25).setCellValue("LastLogin");

				// writing data into sheet
				for (Student student : studentList) {
					row = spreadsheet.createRow(rowid++);

					int cellId = 0;

					Cell cell0 = row.createCell(cellId++);
					cell0.setCellValue(student.getRegistrationId());

					Cell cell1 = row.createCell(cellId++);
					cell1.setCellValue(student.getFirstName());

					Cell cell2 = row.createCell(cellId++);
					cell2.setCellValue(student.getLastName());

					Cell cell3 = row.createCell(cellId++);
					cell3.setCellValue(student.getGender());

					Cell cell4 = row.createCell(cellId++);
					cell4.setCellValue(student.getDateOfBirth());

					Cell cell5 = row.createCell(cellId++);
					cell5.setCellValue(student.getCategory());

					Cell cell6 = row.createCell(cellId++);
					cell6.setCellValue(student.getFatherName());

					Cell cell7 = row.createCell(cellId++);
					cell7.setCellValue(student.getMotherName());

					Cell cell8 = row.createCell(cellId++);
					cell8.setCellValue(student.getStandard());

					Cell cell9 = row.createCell(cellId++);
					cell9.setCellValue(student.getCourse());

					Cell cell10 = row.createCell(cellId++);
					cell10.setCellValue(student.getSchool());

					Cell cell11 = row.createCell(cellId++);
					cell11.setCellValue(student.getCollege());

					Cell cell12 = row.createCell(cellId++);
					cell12.setCellValue(student.getDegree());

					Cell cell13 = row.createCell(cellId++);
					cell13.setCellValue(student.getMarks());

					Cell cell14 = row.createCell(cellId++);
					cell14.setCellValue(student.getEmail());

					Cell cell15 = row.createCell(cellId++);
					cell15.setCellValue(student.getMobile());

					Cell cell16 = row.createCell(cellId++);
					cell16.setCellValue(student.getFatherMobile());

					Cell cell17 = row.createCell(cellId++);
					cell17.setCellValue(student.getAddress1());

					Cell cell18 = row.createCell(cellId++);
					cell18.setCellValue(student.getAddress2());

					Cell cell19 = row.createCell(cellId++);
					cell19.setCellValue(student.getCity());

					Cell cell20 = row.createCell(cellId++);
					cell20.setCellValue(student.getState());

					Cell cell21 = row.createCell(cellId++);
					cell21.setCellValue(student.getPincode());

					Cell cell22 = row.createCell(cellId++);
					cell22.setCellValue(student.getSubject());

					Cell cell23 = row.createCell(cellId++);
					cell23.setCellValue(student.getUsername());

					Cell cell24 = row.createCell(cellId++);
					cell24.setCellValue(student.getRegistrationDate());

					Cell cell25 = row.createCell(cellId++);
					cell25.setCellValue(student.getLastLogin());
				}

				// Writing the workbook in the file with extension .xlsx
				String backupFilePath = db.getPathFromProperties("backupPath");

				FileOutputStream fos = new FileOutputStream(new File(backupFilePath + "studentbackup.xlsx"));
				workbook.write(fos);
				fos.close();
				workbook.close();
				response.sendRedirect("deanbackups.jsp?msg=Student Exported Successfully..");
				
			} catch (Exception e) {
				db.logInFile(e, "studentexcel");
			}
		} else {			
			response.sendRedirect("deanbackups.jsp?msg=No Data to Export..");
		}

	}

}
