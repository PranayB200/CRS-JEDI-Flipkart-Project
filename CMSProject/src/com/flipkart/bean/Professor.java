/**
 *
 */
package com.flipkart.bean;
import java.util.Date;
/**
 * @author sameer
 *
 */
public class Professor extends User{
	private String department;

	public String getDepartment() {
		return department;
	}
	public void setDepartment(String department) {
		this.department = department;
	}
}