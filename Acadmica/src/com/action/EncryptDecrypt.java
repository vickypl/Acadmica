/**
 * 
 */
package com.action;
/**
	author: Vicky pl
	email: vicky542011@gmail.com
	mobile: 7828789845
 **/
/**
 * @author Anonymox
 *
 */
public class EncryptDecrypt {

	public String encryptPassword(String string) {
		int key = 54;
		String encrypted="";
		char[] arr = string.toCharArray();
		for (char c : arr) {
			c=(char)(c+key);
			encrypted=encrypted+c;
		}
		return encrypted;
	}

	public String decryptPassword(String string) {
		int key = 54;
		String decrypted="";
		char[] arr = string.toCharArray();
		for (char c : arr) {
			c=(char)(c-key);
			decrypted=decrypted+c;
		}
		return decrypted;
	}
}