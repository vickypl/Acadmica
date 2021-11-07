/**
 * 
 */
package com.admin;

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
public class AdminAction {
	//database Connector class
	DatabaseConnector db = new DatabaseConnector();
	Connection connection=null;
	PreparedStatement pstat = null;
	Statement statement = null;
	ResultSet resultSet = null;
	
	//servlet name for error log file
	String servletname = this.getClass().getName();
	
	//this return single faculty object
	public Admin getAdmin(String sql) {

		Admin admin = null;
		try {
			connection = db.connect();
			statement = connection.createStatement();
			resultSet=statement.executeQuery(sql);
			
			while(resultSet.next()) {
				admin= new Admin();
				admin.setRegid(resultSet.getString(1));
				admin.setFirstName(resultSet.getString(2));
				admin.setLastName(resultSet.getString(3));
				admin.setUsername(resultSet.getString(4));
				admin.setEmail(resultSet.getString(5));
				admin.setPassword(resultSet.getString(6));
				admin.setLastLogin(resultSet.getString(7));
				admin.setPicurl(resultSet.getString(8));
				admin.setRole(resultSet.getString(9));
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
		return admin;
	}
	
	//it updates the admin details
	public boolean updateAdminDetails(String fname, String lname, String username, String email, String password) {
		boolean updateStatus=false;
		try {
			connection = db.connect();
			String sql="update admin set firstname=?, lastname=?, username=?, email=?, password=? where id=10";
			pstat=connection.prepareStatement(sql);
			pstat.setString(1, fname);
			pstat.setString(2, lname);
			pstat.setString(3, username);
			pstat.setString(4, email);
			pstat.setString(5, password);
			
			int result = pstat.executeUpdate();
			
			if(result>0) {
				updateStatus=true;
			} else {
				updateStatus=false;
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
		return updateStatus;
	}
	
	//returns notice list
	public ArrayList<AdminNotice> getNoticeList(String sql) {
		ArrayList<AdminNotice> list = null;
		try {
			
			list = new ArrayList<AdminNotice>();
			connection = db.connect();
			statement = connection.createStatement();
			resultSet=statement.executeQuery(sql);
			
			while(resultSet.next()) {
				AdminNotice notice = new AdminNotice();
				notice.setId(resultSet.getString(1));
				notice.setTopic(resultSet.getString(2));
				notice.setNotice(resultSet.getString(3));
				notice.setPostedon(resultSet.getString(4));
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
	
	//returns any of the three kind of reg code(faculty student course) as per the query
	public RegistrationCode getRegCode(String sql) {
		RegistrationCode code = null;
		try {
			connection = db.connect();
			statement = connection.createStatement();
			resultSet=statement.executeQuery(sql);
			
			if(resultSet.next()) {
				code = new RegistrationCode();
				code.setId(resultSet.getString(1));
				code.setCode(resultSet.getString(2));
				code.setStatus(resultSet.getString(3));
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
		return code;
	}
	
	public ArrayList<RegistrationCode> getPendingRegCodeList() {
		ArrayList<RegistrationCode> list = new ArrayList<RegistrationCode>();
		String sql=null;
		try {
			connection = db.connect();
			statement = connection.createStatement();
			sql="select * from registration_code_faculty where status='false'";
			resultSet=statement.executeQuery(sql);
			while(resultSet.next()) {
				RegistrationCode code = new RegistrationCode();
				code.setId(resultSet.getString(1));
				code.setCode(resultSet.getString(2));
				code.setStatus(resultSet.getString(3));
				list.add(code);
			}
			
			sql="select * from registration_code_student where status='false'";
			resultSet=statement.executeQuery(sql);

			while(resultSet.next()) {
				RegistrationCode code = new RegistrationCode();
				code.setId(resultSet.getString(1));
				code.setCode(resultSet.getString(2));
				code.setStatus(resultSet.getString(3));
				list.add(code);
			}

			sql="select * from registration_code_course where status='false'";
			resultSet=statement.executeQuery(sql);
			
			while(resultSet.next()) {
				RegistrationCode code = new RegistrationCode();
				code.setId(resultSet.getString(1));
				code.setCode(resultSet.getString(2));
				code.setStatus(resultSet.getString(3));
				list.add(code);
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
	
	public boolean isFacultyCodeValid(String code) {
		boolean isValid=true;
		try {
			connection = db.connect();
			String sql="select code from registration_code_faculty where code=?";
			pstat = connection.prepareStatement(sql);
			pstat.setString(1, code);
			resultSet = pstat.executeQuery();
			if(resultSet.next()) {
				isValid=false;
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
	
	public boolean isStudentCodeValid(String code) {
		boolean isValid=true;
		try {
			connection = db.connect();
			String sql="select code from registration_code_student where code=?";
			pstat = connection.prepareStatement(sql);
			pstat.setString(1, code);
			resultSet = pstat.executeQuery();
			if(resultSet.next()) {
				isValid=false;
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

	public boolean isCourseCodeValid(String code) {
		boolean isValid=true;
		try {
			connection = db.connect();
			String sql="select code from registration_code_course where code=?";
			pstat = connection.prepareStatement(sql);
			pstat.setString(1, code);
			resultSet = pstat.executeQuery();
			if(resultSet.next()) {
				isValid=false;
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
	
	//this will post the generated valid registration code into respective table
	public boolean statusPoster(String tableName, String sequence, String code, String status) {
		boolean postStatus=false;
		try {
			connection = db.connect();
			String sql="insert into table (id, code, status) values (pkey, ?, ?)";
			sql=sql.replace("table", tableName);
			sql=sql.replace("pkey", sequence);
			pstat = connection.prepareStatement(sql);
			pstat.setString(1, code);
			pstat.setString(2, status);
			
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
		

	public ArrayList<CourseCodeRequests> getCourseRequestList(String sql) {
		ArrayList<CourseCodeRequests> list = new ArrayList<CourseCodeRequests>();
		
		try {
			connection = db.connect();
			statement = connection.createStatement();
			resultSet=statement.executeQuery(sql);
			while(resultSet.next()) {
				CourseCodeRequests codeRequset = new CourseCodeRequests();
				codeRequset.setId(resultSet.getString(1));
				codeRequset.setRequestId(resultSet.getString(2));
				codeRequset.setRequest(resultSet.getString(3));
				codeRequset.setStatus(resultSet.getString(4));
				list.add(codeRequset);
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
	
	public ArrayList<NoticeOnlyFaculty> getNoticeOnlyforFacultyList(String sql) {
		ArrayList<NoticeOnlyFaculty> list = new ArrayList<NoticeOnlyFaculty>();
		
		try {
			connection = db.connect();
			statement = connection.createStatement();
			resultSet=statement.executeQuery(sql);
			while(resultSet.next()) {
				NoticeOnlyFaculty notice = new NoticeOnlyFaculty();
				notice.setId(resultSet.getString(1));
				notice.setTopic(resultSet.getString(2));
				notice.setNotice(resultSet.getString(3));
				notice.setPostedOn(resultSet.getString(4));
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

	public boolean deleteNoticeForAll(String id) {
		boolean status=false;		
		try {
			connection = db.connect();
			String sql="delete from notification where id=?";
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
	
	public boolean deleteNoticeOfFaculty(String id) {
		boolean status=false;		
		try {
			connection = db.connect();
			String sql="delete from NOTICE_ONLY_FACULTY where id=?";
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
	
	public ArrayList<AdmissionRequestObject> getAddmissionRequestList(String sql) {
		ArrayList<AdmissionRequestObject> list = new ArrayList<AdmissionRequestObject>();
		
		try {
			connection = db.connect();
			statement = connection.createStatement();
			resultSet=statement.executeQuery(sql);
			while(resultSet.next()) {
				AdmissionRequestObject adrequest = new AdmissionRequestObject();
				adrequest.setId(resultSet.getString(1));
				adrequest.setFullname(resultSet.getString(2));
				adrequest.setEmail(resultSet.getString(3));
				adrequest.setMobile(resultSet.getString(4));
				adrequest.setRole(resultSet.getString(5));
				list.add(adrequest);
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
	
	public boolean deleteAdmissionRequest(String id) {
		boolean status=false;		
		try {
			connection = db.connect();
			String sql="delete from ADMISSION_REQUESTS where id=?";
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
	
	public boolean deleteCourseCodeRequest(String id) {
		boolean status=false;		
		try {
			connection = db.connect();
			String sql="delete from COURSE_CODE_REQUEST where id=?";
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
	
	public boolean deleteCourseFile(String id) {
		boolean status=false;		
		try {
			connection = db.connect();
			String sql="delete from course_files where id=?";
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
	
	public boolean deleteCourseVideo(String id) {
		boolean status=false;		
		try {
			connection = db.connect();
			String sql="delete from course_videos where id=?";
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
	
	public ArrayList<RegistrationCode> getPendingSubjectRegCodeList(String sql) {
		ArrayList<RegistrationCode> list = new ArrayList<RegistrationCode>();
		try {
			connection = db.connect();
			statement = connection.createStatement();
			resultSet=statement.executeQuery(sql);
			
			while(resultSet.next()) {
				RegistrationCode code = new RegistrationCode();
				code.setId(resultSet.getString(1));
				code.setCode(resultSet.getString(2));
				code.setStatus(resultSet.getString(3));
				list.add(code);
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
