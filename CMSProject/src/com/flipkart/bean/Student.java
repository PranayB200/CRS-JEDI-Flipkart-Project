/**
 *
 */
package com.flipkart.bean;

/**
 * @author sameer
 *
 */
public class Student extends User {
	private String branchName;
	private String studentId;
	private int batch;
	private boolean isAppproved;


	// Parameterized Constructor
	public Student(int userId, String name, String role, String password,String branchName, String studentId, int batch, boolean isAppproved) {
		super(userId, name,  role, password);
		this.branchName = branchName;
		this.studentId = studentId;
		this.batch = batch;
		this.isAppproved = isAppproved;
	}

	public String getBranchName() {
		return branchName;
	}
	public void setBranchName(String branchName) {
		this.branchName = branchName;
	}
	public String getStudentId() {
		return studentId;
	}
	public void setStudentId(String studentId) {
		this.studentId = studentId;
	}
	public int getBatch() {
		return batch;
	}
	public void setBatch(int batch) {
		this.batch = batch;
	}



}