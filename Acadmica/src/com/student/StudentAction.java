/**
 * 
 */
package com.student;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.util.ArrayList;

import com.action.DatabaseConnector;
import com.faculty.Assignment;
import com.faculty.DoubtResponse;
import com.faculty.Doubts;

/**
	author: Vicky pl
	email: vicky542011@gmail.com
	mobile: 7828789845
 **/
/**
 * @author Anonymox
 *
 */
public class StudentAction {

	//database Connector class
	DatabaseConnector db = new DatabaseConnector();
	Connection connection=null;
	PreparedStatement pstat=null;
	Statement statement = null;
	ResultSet resultSet = null;
	
	//servlet name for error log file
	String servletname = this.getClass().getName();
	
	public ArrayList<Student> getStudent(String sql) {
		ArrayList<Student> list = new ArrayList<Student>();
		try {
			connection = db.connect();
			statement = connection.createStatement();
			resultSet=statement.executeQuery(sql);
			
			while(resultSet.next()) {
				Student student = new Student();
				student.setRegistrationId(resultSet.getString(1));
				student.setFirstName(resultSet.getString(2));
				student.setLastName(resultSet.getString(3));
				student.setGender(resultSet.getString(4));
				student.setDateOfBirth(resultSet.getString(5));
				student.setCategory(resultSet.getString(6));
				student.setFatherName(resultSet.getString(7));
				student.setMotherName(resultSet.getString(8));
				student.setStandard(resultSet.getString(9));
				student.setCourse(resultSet.getString(10));
				student.setSchool(resultSet.getString(11));
				student.setCollege(resultSet.getString(12));
				student.setDegree(resultSet.getString(13));
				student.setMarks(resultSet.getString(14));
				student.setEmail(resultSet.getString(15));
				student.setMobile(resultSet.getString(16));
				student.setFatherMobile(resultSet.getString(17));
				student.setAddress1(resultSet.getString(18));
				student.setAddress2(resultSet.getString(19));
				student.setCity(resultSet.getString(20));
				student.setState(resultSet.getString(21));
				student.setPincode(resultSet.getString(22));
				student.setSubject(resultSet.getString(23));
				student.setUsername(resultSet.getString(24));
				student.setPassword(resultSet.getString(25));
				student.setRegistrationDate(resultSet.getString(26));
				student.setLastLogin(resultSet.getString(27));
				student.setFaculty(resultSet.getString(28));
				student.setRole(resultSet.getString(29));
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

	public Student getSingleStudent(String sql) {
		Student student = null;
		try {
			connection = db.connect();
			statement = connection.createStatement();
			resultSet=statement.executeQuery(sql);
			
			while(resultSet.next()) {
				student=new Student();
				student.setRegistrationId(resultSet.getString(1));
				student.setFirstName(resultSet.getString(2));
				student.setLastName(resultSet.getString(3));
				student.setGender(resultSet.getString(4));
				student.setDateOfBirth(resultSet.getString(5));
				student.setCategory(resultSet.getString(6));
				student.setFatherName(resultSet.getString(7));
				student.setMotherName(resultSet.getString(8));
				student.setStandard(resultSet.getString(9));
				student.setCourse(resultSet.getString(10));
				student.setSchool(resultSet.getString(11));
				student.setCollege(resultSet.getString(12));
				student.setDegree(resultSet.getString(13));
				student.setMarks(resultSet.getString(14));
				student.setEmail(resultSet.getString(15));
				student.setMobile(resultSet.getString(16));
				student.setFatherMobile(resultSet.getString(17));
				student.setAddress1(resultSet.getString(18));
				student.setAddress2(resultSet.getString(19));
				student.setCity(resultSet.getString(20));
				student.setState(resultSet.getString(21));
				student.setPincode(resultSet.getString(22));
				student.setSubject(resultSet.getString(23));
				student.setUsername(resultSet.getString(24));
				student.setPassword(resultSet.getString(25));
				student.setRegistrationDate(resultSet.getString(26));
				student.setLastLogin(resultSet.getString(27));
				student.setFaculty(resultSet.getString(28));
				student.setRole(resultSet.getString(29));
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
		return student;
	}
	
	
	public StudentProfilePic getStudentProfilePic(String sql) {
		StudentProfilePic studentPic = null;
		try {
			connection = db.connect();
			statement = connection.createStatement();
			resultSet=statement.executeQuery(sql);
			
			if(resultSet.next()) {
				studentPic = new StudentProfilePic();
				studentPic.setId(resultSet.getString(1));
				studentPic.setRegid(resultSet.getString(2));
				studentPic.setUrl(resultSet.getString(3));
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
		return studentPic;
	}
	
	public ArrayList<StudentFiles> getStudentFiles(String sql) {
		ArrayList<StudentFiles> list = null;
		try {
			
			list = new ArrayList<StudentFiles>();
			connection = db.connect();
			statement = connection.createStatement();
			resultSet=statement.executeQuery(sql);
			
			while(resultSet.next()) {
				StudentFiles studentFile = new StudentFiles();
				studentFile.setId(resultSet.getString(1));
				studentFile.setTitle(resultSet.getString(2));
				studentFile.setFileType(resultSet.getString(3));
				studentFile.setFileSize(resultSet.getString(4));
				studentFile.setUploadDate(resultSet.getString(5));
				studentFile.setRegid(resultSet.getString(6));
				studentFile.setUrl(resultSet.getString(7));
				list.add(studentFile);
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
	
	public ArrayList<Doubts> getStudentDoubts(String sql) {
		ArrayList<Doubts> doubtList = null;
		try {
			
			doubtList = new ArrayList<Doubts>();
			connection = db.connect();
			statement = connection.createStatement();
			resultSet=statement.executeQuery(sql);
			
			while(resultSet.next()) {
				Doubts doubt = new Doubts();
				doubt.setId(resultSet.getString(1));
				doubt.setDoubt(resultSet.getString(2));
				doubt.setStudentId(resultSet.getString(3));
				doubt.setFacultyId(resultSet.getString(4));
				doubt.setPostedBy(resultSet.getString(5));
				doubt.setPostedOn(resultSet.getString(6));
				doubtList.add(doubt);
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
		return doubtList;
	}

	public ArrayList<DoubtResponse> getStudentDoubtsResponses(String sql) {
		ArrayList<DoubtResponse> doubtResponseList = null;
		try {
			 doubtResponseList = new ArrayList<DoubtResponse>();
			
			connection = db.connect();
			statement = connection.createStatement();
			resultSet=statement.executeQuery(sql);
			
			while(resultSet.next()) {
				DoubtResponse doubtRes = new DoubtResponse();
				doubtRes.setId(resultSet.getString(1));
				doubtRes.setName(resultSet.getString(2));
				doubtRes.setRegid(resultSet.getString(3));
				doubtRes.setResponse(resultSet.getString(4));
				doubtRes.setRespondedBy(resultSet.getString(5));
				doubtRes.setRespondedOn(resultSet.getString(6));
				doubtResponseList.add(doubtRes);
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
		return doubtResponseList;
	}

	public ArrayList<Assignment> getStudentAssingments(String sql) {
		ArrayList<Assignment> assignmentList = new ArrayList<Assignment>();
		try {
			connection = db.connect();
			statement = connection.createStatement();
			resultSet=statement.executeQuery(sql);
			
			while(resultSet.next()) {
				Assignment assignment = new Assignment();
				assignment.setId(resultSet.getString(1));
				assignment.setCourse(resultSet.getString(2));
				assignment.setRegid(resultSet.getString(3));
				assignment.setAssingment(resultSet.getString(4));
				assignment.setAssingedBy(resultSet.getString(5));
				assignment.setAssDate(resultSet.getString(6));
				assignment.setSubDate(resultSet.getString(7));
				assignment.setStatus(resultSet.getString(8));
				assignmentList.add(assignment);
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
		return assignmentList;
	}
	
	public Assignment getSingleAssingments(String sql) {
		Assignment assignment = new Assignment();		
		try {
			connection = db.connect();
			statement = connection.createStatement();
			resultSet=statement.executeQuery(sql);
			
			while(resultSet.next()) {
				assignment.setId(resultSet.getString(1));
				assignment.setCourse(resultSet.getString(2));
				assignment.setRegid(resultSet.getString(3));
				assignment.setAssingment(resultSet.getString(4));
				assignment.setAssingedBy(resultSet.getString(5));
				assignment.setAssDate(resultSet.getString(6));
				assignment.setSubDate(resultSet.getString(7));
				assignment.setStatus(resultSet.getString(8));
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
		return assignment;
	}
	
	public boolean deleteFile(String url) {
		boolean status=false;		
		try {
			connection = db.connect();
			String sql="delete from studentfiles where url=?";
			pstat = connection.prepareStatement(sql);
			pstat.setString(1, url);
			int result = pstat.executeUpdate();
			if (result>0) {
				status=true;
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
		return status;
	}

	//returns single doubt
	public Doubts getDoubt(String sql) {
		Doubts doubt = new Doubts();
		try {
			connection = db.connect();
			statement = connection.createStatement();
			resultSet=statement.executeQuery(sql);
			
			while(resultSet.next()) {
				doubt.setId(resultSet.getString(1));
				doubt.setDoubt(resultSet.getString(2));
				doubt.setStudentId(resultSet.getString(3));
				doubt.setFacultyId(resultSet.getString(4));
				doubt.setPostedBy(resultSet.getString(5));
				doubt.setPostedOn(resultSet.getString(6));
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
		return doubt;
	}
}
