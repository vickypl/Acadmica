/**
 * 
 */
package com.faculty;

import java.sql.Connection;
import java.sql.PreparedStatement;
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
public class FacultyAction {
	
	//database Connector class
	DatabaseConnector db = new DatabaseConnector();
	Connection connection=null;
	PreparedStatement pstat = null;
	Statement statement = null;
	ResultSet resultSet = null;
	
	//servlet name for error log file
	String servletname = this.getClass().getName();
	
	
	public ArrayList<Faculty> getFaculty(String sql) {
		ArrayList<Faculty> list = new ArrayList<Faculty>();
		try {
			connection = db.connect();
			statement = connection.createStatement();
			resultSet=statement.executeQuery(sql);
			
			while(resultSet.next()) {
				Faculty faculty = new Faculty();
				faculty.setRegistrationId(resultSet.getString(1));
				faculty.setFirstName(resultSet.getString(2));
				faculty.setLastName(resultSet.getString(3));
				faculty.setGender(resultSet.getString(4));
				faculty.setDateOfBirth(resultSet.getString(5));
				faculty.setMaritalStatus(resultSet.getString(6));
				faculty.setGuardianName(resultSet.getString(7));
				faculty.setEmail(resultSet.getString(8));
				faculty.setMobile(resultSet.getString(9));
				faculty.setAddress1(resultSet.getString(10));
				faculty.setAddress2(resultSet.getString(11));
				faculty.setCity(resultSet.getString(12));
				faculty.setState(resultSet.getString(13));
				faculty.setPincode(resultSet.getString(14));
				faculty.setSubject(resultSet.getString(15));
				faculty.setUsername(resultSet.getString(16));
				faculty.setPassword(resultSet.getString(17));
				faculty.setRegistrationDate(resultSet.getString(18));
				faculty.setLastLogin(resultSet.getString(19));
				faculty.setRole(resultSet.getString(20));
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

	//this return single faculty object
	public Faculty getSingleFaculty(String sql) {

		Faculty faculty = null;
		try {
			connection = db.connect();
			statement = connection.createStatement();
			resultSet=statement.executeQuery(sql);
			
			while(resultSet.next()) {
				faculty = new Faculty();
				faculty.setRegistrationId(resultSet.getString(1));
				faculty.setFirstName(resultSet.getString(2));
				faculty.setLastName(resultSet.getString(3));
				faculty.setGender(resultSet.getString(4));
				faculty.setDateOfBirth(resultSet.getString(5));
				faculty.setMaritalStatus(resultSet.getString(6));
				faculty.setGuardianName(resultSet.getString(7));
				faculty.setEmail(resultSet.getString(8));
				faculty.setMobile(resultSet.getString(9));
				faculty.setAddress1(resultSet.getString(10));
				faculty.setAddress2(resultSet.getString(11));
				faculty.setCity(resultSet.getString(12));
				faculty.setState(resultSet.getString(13));
				faculty.setPincode(resultSet.getString(14));
				faculty.setSubject(resultSet.getString(15));
				faculty.setUsername(resultSet.getString(16));
				faculty.setPassword(resultSet.getString(17));
				faculty.setRegistrationDate(resultSet.getString(18));
				faculty.setLastLogin(resultSet.getString(19));
				faculty.setRole(resultSet.getString(20));
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
		return faculty;
	}
	//FacultyNotice
	
	public ArrayList<FacultyNotice> getFacultyNotice(String sql) {
		ArrayList<FacultyNotice> list = new ArrayList<FacultyNotice>();
		try {
			connection = db.connect();
			statement = connection.createStatement();
			resultSet=statement.executeQuery(sql);
			
			while(resultSet.next()) {
				FacultyNotice notice = new FacultyNotice();
				notice.setId(resultSet.getString(1));
				notice.setTopic(resultSet.getString(2));
				notice.setNotice(resultSet.getString(3));
				notice.setRegid(resultSet.getString(4));
				notice.setPostedBy(resultSet.getString(5));
				notice.setPostedOn(resultSet.getString(6));
				list.add(notice);
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
	
	public ArrayList<SubjectRegCodeRequest> getSubjectRegCodeRequest(String sql) {
		ArrayList<SubjectRegCodeRequest> list = new ArrayList<SubjectRegCodeRequest>();
		try {
			connection = db.connect();
			statement = connection.createStatement();
			resultSet=statement.executeQuery(sql);
			
			while(resultSet.next()) {
				SubjectRegCodeRequest req = new SubjectRegCodeRequest();
				req.setId(resultSet.getString(1));
				req.setFacultyId(resultSet.getString(2));
				req.setCid(resultSet.getString(3));
				req.setCourseName(resultSet.getString(4));
				req.setRequest(resultSet.getString(5));
				req.setRegid(resultSet.getString(6));
				req.setStudentName(resultSet.getString(7));
				list.add(req);
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
	
	public boolean deleteSubjectRegCodeRequest(String id) {
		boolean delStatus=false;
		try {
			connection = db.connect();
			statement = connection.createStatement();
			String sql="delete from subject_reg_code_request where id='"+id+"'";
			
			int res = statement.executeUpdate(sql);
			
			if(res>0) {
				delStatus=true;
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
		return delStatus;
	}
	
	
	public FacutlyProfilePic getFacultyProfilePic(String sql) {
		FacutlyProfilePic facultyPic = new FacutlyProfilePic();
		try {
			connection = db.connect();
			statement = connection.createStatement();
			resultSet=statement.executeQuery(sql);
			
			while(resultSet.next()) {
				facultyPic.setId(resultSet.getString(1));
				facultyPic.setRegid(resultSet.getString(2));
				facultyPic.setUrl(resultSet.getString(3));
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
		return facultyPic;
	}
	
	public ArrayList<FacultyFiles> getFacultyFiles(String sql) {
		ArrayList<FacultyFiles> list = new ArrayList<FacultyFiles>();
		try {
			connection = db.connect();
			statement = connection.createStatement();
			resultSet=statement.executeQuery(sql);
			
			while(resultSet.next()) {
				FacultyFiles facultyFile = new FacultyFiles();
				facultyFile.setId(resultSet.getString(1));
				facultyFile.setTitle(resultSet.getString(2));
				facultyFile.setFileType(resultSet.getString(3));
				facultyFile.setFileSize(resultSet.getString(4));
				facultyFile.setUploadDate(resultSet.getString(5));
				facultyFile.setRegid(resultSet.getString(6));
				facultyFile.setUrl(resultSet.getString(7));
				list.add(facultyFile);
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
	
	public boolean deleteFacultyFile(String url) {
		boolean status=false;		
		try {
			connection = db.connect();
			String sql="delete from facultyFiles where url=?";
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
	
	public boolean deleteFacultyNotice(String id) {
		boolean status=false;		
		try {
			connection = db.connect();
			String sql="delete from faculty_notice where id=?";
			pstat = connection.prepareStatement(sql);
			pstat.setString(1, id);
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

	public boolean courseRequestPoster(String id, String requestId, String req, String status) {
		boolean postStatus=false;
		try {
			connection = db.connect();
			String sql="insert into COURSE_CODE_REQUEST (id, requesterid, request, status) values (course_req_id.nextval, ?, 'Request For New Course.', 'false')";
			pstat = connection.prepareStatement(sql);
			pstat.setString(1, requestId);
			
			int r = pstat.executeUpdate();
			
			if(r>0) {
				postStatus=true;
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
		return postStatus;
	}
	
	public boolean fileInfoUpload(String courseId, String facultyId, String fileTitle, String url) {
		boolean status=false;
		try {
			connection = db.connect();
			String sql="insert into course_files (id, courseid, facultyid, file_title, url) values(course_file_num.nextval, ?, ?, ?, ?)";
			pstat = connection.prepareStatement(sql);
			pstat.setString(1, courseId);
			pstat.setString(2, facultyId);
			pstat.setString(3, fileTitle);
			pstat.setString(4, url);
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
	
	public boolean videoInfoUpload(String courseId, String facultyId, String videoTitle, String url) {
		boolean status=false;
		try {
			connection = db.connect();
			String sql="insert into course_videos (id, courseid, facultyid, video_title, url) values(course_vid_num.nextval, ?, ?, ?, ?)";
			pstat = connection.prepareStatement(sql);
			pstat.setString(1, courseId);
			pstat.setString(2, facultyId);
			pstat.setString(3, videoTitle);
			pstat.setString(4, url);
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
	
	public boolean isCourseCodeValid(String generatedCode) {
		boolean isValid=true;
		try {
			connection = db.connect();
			statement = connection.createStatement();
			String sql="select * from subject_registration_code where code='"+generatedCode+"'";
			resultSet = statement.executeQuery(sql);
			if(resultSet.next()) {
				return false;
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
		return isValid;
	}
	
	public boolean postGenratedCourseRequestCode(String generatedCode) {
		boolean status=false;
		try {
			connection = db.connect();
			String sql="insert into subject_registration_code (id, code, status) values(subject_regcode_id.nextval, ?, ?)";
			pstat = connection.prepareStatement(sql);
			pstat.setString(1, generatedCode);
			pstat.setString(2, "false");
			
			int r = pstat.executeUpdate();
			
			if(r>0) {
				status = true;
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
}
