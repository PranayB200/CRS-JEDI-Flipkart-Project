/**
 * 
 */
package com.flipkart.service;

/**
 * @author pranaybansal
 *
 */
public interface UserInterface {
	
	/*
	 * Method to update the password of a user.
	 * 
	 * @param userID
	 * @param newPassword 
	 * @return a boolean denoting whether the password has been updated or not.
	 */
	boolean updatePassword(String userID, String newPassword);
	
	/*
	 * Method to verify User's Credentials.
	 * 
	 * @param userID
	 * @param password
	 * @returns a boolean denoting whether the user exists in the database.
	 */
	public boolean verifyCredentials(String userID, String password);
	
	/*
	 * Method to get role of the user.
	 * 
	 * @param userID
	 * @returns a String denoting the user's role.
	 */
	public String getRole(String userID);
}
