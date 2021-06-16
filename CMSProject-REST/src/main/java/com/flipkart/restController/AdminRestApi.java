package com.flipkart.restController;

import java.util.List;

import javax.validation.Valid;
import javax.validation.ValidationException;
import javax.validation.constraints.Max;
import javax.validation.constraints.Min;
import javax.validation.constraints.NotNull;
import javax.validation.constraints.Size;
import javax.ws.rs.Consumes;
import javax.ws.rs.FormParam;
import javax.ws.rs.GET;
import javax.ws.rs.POST;
import javax.ws.rs.PUT;
import javax.ws.rs.Path;
import javax.ws.rs.Produces;
import javax.ws.rs.QueryParam;
import javax.ws.rs.core.MediaType;
import javax.ws.rs.core.Response;

import com.flipkart.bean.Course;
import com.flipkart.bean.Professor;
import com.flipkart.bean.Student;
import com.flipkart.constant.Gender;
import com.flipkart.exception.CourseFoundException;
import com.flipkart.exception.CourseNotFoundException;
import com.flipkart.exception.ProfessorNotAddedException;
import com.flipkart.exception.StudentNotFoundException;
import com.flipkart.exception.StudentNotFoundForApprovalException;
import com.flipkart.exception.UserNotFoundException;
import com.flipkart.service.AdminInterface;
import com.flipkart.service.AdminOperation;

/**
 * @author pranaybansal
 *
 */

@Path("/adminRestApi")
public class AdminRestApi {
	AdminInterface adminOperation = AdminOperation.getInstance();
	
	@GET
	@Path("/viewCoursesInCatalog")
	@Produces(MediaType.APPLICATION_JSON)
	public List<Course> viewCoursesInCatalogue() {
		
		return adminOperation.viewCourses(1);
		
//		return Response.status(201).entity("[{'course_id':1,'course_name':'Java','instructor':1,'numSeats':10}]").build();
//		return [{'course_id':1,'course_name':"Java",'instructor':1,'numSeats':10}] 
		
	}

	@GET
	@Path("/viewPendingAdmissions")
	@Produces(MediaType.APPLICATION_JSON)
	public List<Student> viewPendingAdmissions() {
		
		return adminOperation.viewPendingAdmissions();
		
	}
	
	@POST
	@Path("/addCourse")
	public Response addCourse(@NotNull @FormParam("courseCode") String courseCode, @NotNull @FormParam("courseName") String courseName) {
//		List<Course> courseList = adminOperation.viewCourses(1);
		
		System.out.println(courseCode + " " + courseName);
		Course course = new Course(courseCode, courseName, null, 10);
		List<Course> courseList = viewCoursesInCatalogue();
		try {
			adminOperation.addCourse(course, courseList);
			return Response.status(201).entity("Course with courseCode: " + course.getCourseCode() + " added to catalog").build();
		
		} catch (CourseFoundException e) {
			
			return Response.status(409).entity(e.getMessage()).build();
		
		}	
	}
	
	@POST
	@Path("/assignCourseToProfessor")
	@Produces(MediaType.APPLICATION_JSON)
	public Response assignCourseToProfessor(
			@NotNull
			@FormParam("courseCode") String courseCode, 
			@NotNull
			@FormParam("professorId") String professorId) {
			
			try {
				adminOperation.assignCourse(courseCode, professorId);
				return Response.status(201).entity("courseCode: " + courseCode + " assigned to professor: " + professorId).build();
				
			} catch (CourseNotFoundException e) {
				
				return Response.status(409).entity(e.getMessage()).build();
				
			}
	}
	
	@POST
	@Path("/approveStudent")
	@Produces(MediaType.APPLICATION_JSON)
	public Response approveStudent(
			@NotNull
			@FormParam("studentId") int studentId) {
		List<Student> studentList = adminOperation.viewPendingAdmissions();
		
		try {
			
			adminOperation.approveStudent(studentId, studentList);
			String jsonResult = String.format("{'message':'Student with student id %d is approved'}", studentId);
			return Response.status(201).entity(jsonResult).build();
		
		} catch (StudentNotFoundException e) {
			
			return Response.status(409).entity(e.getMessage()).build();
		
		}
	}
	
	@POST
	@Path("/addProfessor")
	@Produces(MediaType.APPLICATION_JSON)
	public Response addProfessor(@NotNull @FormParam("Name") String name, @NotNull @FormParam("Department") String department,
								@NotNull @FormParam("designation") String designation, @NotNull @FormParam("userId") String userId,
								@NotNull @FormParam("password") String password, @NotNull @FormParam("Gender") String genderV,
								@NotNull @FormParam("address") String address, @NotNull @FormParam("country") String country) {
		
		Professor professor = new Professor();
		professor.setName(name);
		professor.setDepartment(department);
		professor.setDesignation(designation);
		professor.setDesignation(designation);
		professor.setUserId(userId);
		professor.setPassword(password);
		professor.setGender(Gender.getName(Integer.parseInt(genderV)));
		professor.setAddress(address);
		professor.setCountry(country);
		
		try {
			
			adminOperation.addProfessor(professor);
			return Response.status(201).entity("Professor with professorId: " + professor.getUserId() + " added").build();
			
		} catch (ProfessorNotAddedException e) {
			
			return Response.status(409).entity(e.getMessage()).build();
			
		}	
	}
	
/*
	
	@PUT
	@Path("/deleteCourse")
	@Produces(MediaType.APPLICATION_JSON)
	public Response deleteCourse(
			@Size(min = 1 , max = 10 , message = "Course Code length should be between 4 and 10 character")
			@NotNull
			@QueryParam("courseCode") String courseCode) throws ValidationException{
		List<Course> courseList = adminOperation.viewCourses(1);
		
		try {
			
			adminOperation.deleteCourse(courseCode, courseList);
			return Response.status(201).entity("Course with courseCode: " + courseCode + " deleted from catalog").build();
		
		} catch (CourseNotFoundException e) {
			
			return Response.status(409).entity(e.getMessage()).build();
		
		}	
	}
	*/
}
