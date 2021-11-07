/**
 * 
 */
package com.action;

import java.util.TimerTask;

/**
	author: Vicky pl
	email: vicky542011@gmail.com
	mobile: 7828789845
 **/
/**
 * @author Anonymox
 *
 */
public class TimingAtask extends TimerTask {

	@Override
	public void run() {
		//clearing logs of false attempts from the login_logs table
		DatabaseConnector db = new DatabaseConnector();
		String sql="truncate table login_logs";
		db.deleteFalseLoginAttempts(sql);
	}

}
