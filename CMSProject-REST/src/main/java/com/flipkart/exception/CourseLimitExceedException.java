/**
 * 
 */
package com.flipkart.exception;

/**
 * @author JEDI-04-G3 
 *
 */
public class CourseLimitExceedException extends Exception{
	
	private int num;

	
	public CourseLimitExceedException(int num )
	{	
		this.num = num;
	}


	@Override
	public String getMessage() 
	{
		return "You have already registered for " + num + " courses";
	}


}