/**
 * 
 */
package com.course;

import java.sql.Connection;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.util.ArrayList;

import com.action.DatabaseConnector;

/**
	author: Vicky pl
	email: vicky542011@gmail.com
	mobile: 7828789845
 **/
/**
 * @author Anonymox
 *
 */
public class CourseActions {
	
	//database Connector class
	DatabaseConnector db = new DatabaseConnector();
	Connection connection=null;
	Statement statement = null;
	ResultSet resultSet = null;
	
	//servlet name for error log file
	String servletname = this.getClass().getName();
	
	//returns available courseList
	
	public ArrayList<Course> getCoursesList(String sql) {
		ArrayList<Course> list = new ArrayList<Course>();
		try {
			connection = db.connect();
			statement = connection.createStatement();
			resultSet=statement.executeQuery(sql);
			
			while(resultSet.next()) {
				Course course = new Course();
				course.setId(resultSet.getString(1));
				course.setCourseId(resultSet.getString(2));
				course.setCourseName(resultSet.getString(3));
				list.add(course);
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
		return list;
	}

	//returns single course
	public Course getCourse(String sql) {
		Course course = null;
		try {
			connection = db.connect();
			statement = connection.createStatement();
			resultSet=statement.executeQuery(sql);
			
			if(resultSet.next()) {
				course = new Course();
				course.setId(resultSet.getString(1));
				course.setCourseId(resultSet.getString(2));
				course.setCourseName(resultSet.getString(3));
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
		return course;
	}
	
	//returns list of course files
	public ArrayList<CourseFiles> getCoursesFilesList(String sql) {
		ArrayList<CourseFiles> list = new ArrayList<CourseFiles>();
		try {
			connection = db.connect();
			statement = connection.createStatement();
			resultSet=statement.executeQuery(sql);
			
			while(resultSet.next()) {
				CourseFiles file = new CourseFiles();
				file.setId(resultSet.getString(1));
				file.setCourseId(resultSet.getString(2));
				file.setFacultyId(resultSet.getString(3));
				file.setFileTitle(resultSet.getString(4));
				file.setUrl(resultSet.getString(5));
				list.add(file);
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
		return list;
	}
	
	
	//returns list of course videos files
	public ArrayList<CourseVideos> getCourseVideoList(String sql) {
		ArrayList<CourseVideos> list = new ArrayList<CourseVideos>();
		try {
			connection = db.connect();
			statement = connection.createStatement();
			resultSet=statement.executeQuery(sql);
			
			while(resultSet.next()) {
				CourseVideos video = new CourseVideos();
				video.setId(resultSet.getString(1));
				video.setCourseId(resultSet.getString(2));
				video.setFacultyId(resultSet.getString(3));
				video.setVideoTitle(resultSet.getString(4));
				video.setUrl(resultSet.getString(5));
				list.add(video);
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
		return list;
	}
	
	
	//returns list of course student files
	public ArrayList<CourseStudent> getCourseStudentList(String sql) {
		ArrayList<CourseStudent> list = new ArrayList<CourseStudent>();
		try {
			connection = db.connect();
			statement = connection.createStatement();
			resultSet=statement.executeQuery(sql);
			
			while(resultSet.next()) {
				CourseStudent student = new CourseStudent();
				student.setId(resultSet.getString(1));
				student.setStudentId(resultSet.getString(2));
				student.setCourseId(resultSet.getString(3));
				list.add(student);
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
		return list;
	}
	
	//returns list of course faculty files
	public ArrayList<CourseFaculty> getCourseFacultyList(String sql) {
		ArrayList<CourseFaculty> list = new ArrayList<CourseFaculty>();
		try {
			connection = db.connect();
			statement = connection.createStatement();
			resultSet=statement.executeQuery(sql);
			
			while(resultSet.next()) {
				CourseFaculty faculty = new CourseFaculty();
				faculty.setId(resultSet.getString(1));
				faculty.setFacultyId(resultSet.getString(2));
				faculty.setCourseId(resultSet.getString(3));
				list.add(faculty);
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
		return list;
	}
}
