/**
 * 
 */
package com.action;
import java.io.BufferedWriter;
import java.io.FileReader;
import java.io.FileWriter;
import java.io.IOException;
import java.io.PrintWriter;
import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Date;
import java.util.Properties;
import java.util.Timer;
import java.util.TimerTask;



/**
	author: Vicky pl
	email: vicky542011@gmail.com
	mobile: 7828789845
 **/

public class DatabaseConnector {
	
	public void writer(Exception e, String servletname) throws Exception {
		
		//method in DatabaseConnector for getting path of properties file
		String path = getPathOfProperties();
		
		//accessing properties file to read the logfiles path
		FileReader reader = new FileReader(path);
		Properties p = new Properties();
		p.load(reader);
		String realPath = p.getProperty("errorLogPath");
		//String path = "G:\\dev\\workspace1\\Acadmica\\WebContent\\errorlog.txt";
		PrintWriter pw = new PrintWriter(new BufferedWriter(new FileWriter(realPath, true)));
		pw.write("Name of Servlet: -> "+servletname+"\n");
		pw.write("Date/Time of Error: -> "+new java.util.Date()+"\n");
		e.printStackTrace(pw);
		pw.write("++++++++++++++++++++++++++++++++++++++++++++++++++++++++++\n");
		pw.close();
	}
	
	public void logInFile(Exception e, String servletname) {
		try { writer(e, servletname); } catch (Exception exception) { e.printStackTrace(); }
	}

	//this method returns the path of the properties file
	public String getPathOfProperties() {
		String path = DatabaseConnector.class.getProtectionDomain().getCodeSource().getLocation().getPath();
		String myFile="extraInfo.properties";
	/*	String myFile="\\com\\action\\extraInfo.properties";
		path = path+myFile;
		path = path.replace("/", "\\");
		path = path.substring(1, path.length());*/
		path = path.replace("DatabaseConnector.class", myFile);
		return path;
	}
	
	public String getPathFromProperties(String key) throws IOException {
		String path = getPathOfProperties();
		FileReader reader  = new FileReader(path);
			Properties p = new Properties();
			p.load(reader);
		String realPath = p.getProperty(key);
		return realPath;
	}
	
	public Connection connect() throws Exception {
		
		String path=getPathOfProperties();
		
		FileReader reader = new FileReader(path);
		Properties p = new Properties();
		p.load(reader);
		String dbUser = p.getProperty("dbUser");
		String dbPass = p.getProperty("dbPass");
		EncryptDecrypt ed = new EncryptDecrypt();
		String dbPassword= ed.decryptPassword(dbPass);

		Connection connection = null;
		try {
			Class.forName("oracle.jdbc.driver.OracleDriver");
			String url = "jdbc:oracle:thin:@localhost:1521:xe";
			connection=DriverManager.getConnection(url, dbUser, dbPassword);
		} catch(ClassNotFoundException e) {
			writer(e, "dbConnector");
		} catch(SQLException e) {
			writer(e, "dbConnector");
		} catch(Exception e) {
			writer(e, "dbConnector");
		}
		return connection;
	}
	
	//common method for updating lastlogin of all users
	public boolean lastLoginUpdate(String sql) {
		boolean status=false;
		Connection connection = null;
		Statement statement = null;
		try {
			connection = connect();
			statement = connection.createStatement();
			int stat = statement.executeUpdate(sql);
			
			if(stat>0) {
				status=true;
			} else {
				status=false;
			}
			
		} catch (ClassNotFoundException e) {
			logInFile(e, "dbConnector");
		} catch (SQLException e) {
			logInFile(e, "dbConnector");
		} catch (Exception e) {
			logInFile(e, "dbConnector");
		}  finally {
			if(connection!=null) {
				try {
					connection.close();
				} catch(SQLException e) {
					logInFile(e, "dbConnector");
				}
			}
		}
		return status;
	}
	
	//login attempt updater
	public boolean updateLoginAttempt(String ipAddress, long timing) {
		boolean status=false;
		Connection connection = null;
		try {
			connection = connect();
			String sql="insert into login_logs (id, ipaddress, timing) values(login_log_id.nextval, ?, ?)";
			PreparedStatement pstat =connection.prepareStatement(sql);
			pstat.setString(1, ipAddress);
			pstat.setLong(2, timing);
			
			int stat=pstat.executeUpdate();
			
			if(stat>0) {
				status=true;
			} else {
				status=false;
			}
			
		} catch (ClassNotFoundException e) {
			logInFile(e, "dbConnector");
		} catch (SQLException e) {
			logInFile(e, "dbConnector");
		} catch (Exception e) {
			logInFile(e, "dbConnector");
		} finally {
			if(connection!=null) {
				try {
					connection.close();
				} catch(SQLException e) {
					logInFile(e, "dbConnector");
				}
			}
		}
		return status;
	}
	
	public ArrayList<LoginAttempt> getLoginAttempts(String sql) {
		ArrayList<LoginAttempt> list = new ArrayList<LoginAttempt>();
		
		Connection connection = null;
		Statement statement = null;
		ResultSet resultSet = null;
		try {
			connection = connect();
			statement= connection.createStatement();
			resultSet=statement.executeQuery(sql);
			
			while(resultSet.next()) {
				LoginAttempt attempt = new LoginAttempt();
				attempt.setId(resultSet.getString(1));
				attempt.setIpaddress(resultSet.getString(2));
				attempt.setTimming(resultSet.getLong(3));
				list.add(attempt);
			}
			
		} catch (ClassNotFoundException e) {
			logInFile(e, "dbConnector");
		} catch (SQLException e) {
			logInFile(e, "dbConnector");
		} catch (Exception e) {
			logInFile(e, "dbConnector");
		} finally {
			if(connection!=null) {
				try {
					connection.close();
				} catch(SQLException e) {
					logInFile(e, "dbConnector");
				}
			}
		}
		return list;
	}
	
	public boolean deleteFalseLoginAttempts(String sql) {
		boolean status=false;
		Connection connection = null;
		Statement statement = null;
		try {
			connection = connect();
			statement = connection.createStatement();
			
			int stat=statement.executeUpdate(sql);
			
			if(stat>0) {
				status=true;
			} else {
				status=false;
			}
			
		} catch (ClassNotFoundException e) {
			logInFile(e, "dbConnector");
		} catch (SQLException e) {
			logInFile(e, "dbConnector");
		} catch (Exception e) {
			logInFile(e, "dbConnector");
		} finally {
			if(connection!=null) {
				try {
					connection.close();
				} catch(SQLException e) {
					logInFile(e, "dbConnector");
				}
			}
		}
		return status;
	}
	
	//this block will clean the logs of login logs table at 2:54:54 AM everyday
	static {
		SimpleDateFormat fmt = new SimpleDateFormat("yyyy-MM-dd HH:mm:ss");
		Date date=null;
		try {
			date = fmt.parse("2021-05-13 02:54:54");
		} catch (ParseException e) {
			e.printStackTrace();
		}
		TimerTask task = new TimingAtask();
		Timer timer = new Timer();
		timer.scheduleAtFixedRate(task, date, 86400000);
	}
	
	public void closeConnection(Connection connection) throws Exception{
		if(connection!=null) {
			try {
				connection.close();
			} catch(SQLException e) {
				writer(e, "dbConnector");
			}
		}
	}
}
