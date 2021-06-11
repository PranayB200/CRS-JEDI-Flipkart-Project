/**
 * 
 */
package com.flipkart.service;

import java.util.List;
import com.flipkart.bean.Course;
import com.flipkart.bean.Professor;
import com.flipkart.bean.Student;

/**
 * @author pranaybansal
 *
 */
public interface AdminInterface {
	
	/*
	 * Method to delete a course from the course catalogue.
	 * 
	 * @param courseCode
	 * @param courseList: course catalogue
	 */
	public void deleteCourse(String courseCode, List<Course> courseList);
	
	/*
	 * Method to add a course in the course catalogue.
	 * 
	 * @param course: new course to be added.
	 * @param courseList: course catalogue
	 */
	public void addCourse(Course course, List<Course> courseList);
	
	/*
	 * Method to get the list of courses in a particular catalogue.
	 * 
	 * @param catalogId
	 * @return List of courses in the given catalogue.
	 */
	public List<Course> viewCourses(int catalogId);
	
	
	/*
	 * Method to get a list of all the professors in the university.
	 * 
	 * @return List of professors.
	 */
	public List<Professor> viewProfessors();
	
	/*
	 * Method to add a new professor to the database.
	 * 
	 * @param professor: Object denoting the new professor.
	 */
	public void addProfessor(Professor professor);
	
	/*
	 * Method to assign a course to a professor.
	 * 
	 * @param courseCode: course to be assigned.
	 * @param professorId
	 */
	public void assignCourse(String courseCode, String professorId);
	
	
	/*
	 * Method to view students yet to be approved by the admin.
	 * 
	 * @return List of all such students.
	 */
	public List<Student> viewPendingAdmissions();
	
	/*
	 * Method to a approve a student.
	 * 
	 * @param studentId
	 * @param studentList: list where the approved student will be added.
	 */
	public void approveStudent(int studentId, List<Student> studentList);

}
