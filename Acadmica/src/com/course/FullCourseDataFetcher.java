package com.course;
/**
 * this class is not used yet
 * 
author: Vicky pl
email: vicky542011@gmail.com
mobile: 7828789845
**/
import java.sql.Statement;
import java.io.IOException;
import java.sql.Connection;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;

import com.course.Course;

import javax.servlet.ServletException;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import com.action.DatabaseConnector;

public class FullCourseDataFetcher extends HttpServlet {
	private static final long serialVersionUID = 1L;

	protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		//servlet name for error log file
		String servletname = this.getClass().getName();
		
		
		DatabaseConnector db = new DatabaseConnector();
		Connection connection = null;
		Statement statement = null;
		ResultSet resultSet = null;
		
		ArrayList<CourseFiles> filesList = new ArrayList<CourseFiles>();
		ArrayList<CourseVideos> videosList = new ArrayList<CourseVideos>();
		ArrayList<CourseStudent> studentList = new ArrayList<CourseStudent>();
		ArrayList<CourseFaculty> facultyList = new ArrayList<CourseFaculty>();
		ArrayList<Course> courseList = new ArrayList<Course>();
		
		try {
			connection = db.connect();
			statement = connection.createStatement();
			String sql1 = "select * from course_files";
			String sql2 = "select * from course_videos";
			String sql3 = "select * from course_student";
			String sql4 = "select * from course_faculty";
			String sql5 = "select * from course";
			
			resultSet=statement.executeQuery(sql1);
			while(resultSet.next()) {
				CourseFiles file = new CourseFiles();
				file.setId(resultSet.getString(1));
				file.setCourseId(resultSet.getString(2));
				file.setFacultyId(resultSet.getString(3));
				file.setFileTitle(resultSet.getString(4));
				file.setUrl(resultSet.getString(5));
				filesList.add(file);
			}
			
			resultSet=statement.executeQuery(sql2);
			while(resultSet.next()) {
				CourseVideos video = new CourseVideos();
				video.setId(resultSet.getString(1));
				video.setCourseId(resultSet.getString(2));
				video.setFacultyId(resultSet.getString(3));
				video.setVideoTitle(resultSet.getString(4));
				video.setUrl(resultSet.getString(5));
				videosList.add(video);
			}
			
			resultSet=statement.executeQuery(sql3);
			while(resultSet.next()) {
				CourseStudent student = new CourseStudent();
				student.setId(resultSet.getString(1));
				student.setStudentId(resultSet.getString(2));
				student.setCourseId(resultSet.getString(3));
				studentList.add(student);
			}
			
			resultSet=statement.executeQuery(sql4);
			while(resultSet.next()) {
				CourseFaculty faculty = new CourseFaculty();
				faculty.setId(resultSet.getString(1));
				faculty.setFacultyId(resultSet.getString(2));
				faculty.setCourseId(resultSet.getString(3));
				facultyList.add(faculty);
			}
			
			resultSet=statement.executeQuery(sql5);
			while(resultSet.next()) {
				Course course = new Course();
				course.setId(resultSet.getString(1));
				course.setCourseId(resultSet.getString(2));
				course.setCourseName(resultSet.getString(3));
				courseList.add(course);
			}
			
			response.getWriter().print(facultyList.get(0).getFacultyId());
			
			
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
	}
}
