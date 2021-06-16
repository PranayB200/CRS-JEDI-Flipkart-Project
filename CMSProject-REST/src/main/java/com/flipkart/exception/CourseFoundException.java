/**
 * 
 */
package com.flipkart.exception;

/**
 * @author JEDI-04-G3
 *
 */
public class CourseFoundException extends Exception{
	private String courseCode;
	
	public CourseFoundException(String courseCode) {
		this.courseCode = courseCode;
	}
	
	public String getCourseCode() {
		return courseCode;
	}
	
	@Override
	public String getMessage() {
		return "courseCode: " + courseCode + " already present in catalog!";
	}
}
