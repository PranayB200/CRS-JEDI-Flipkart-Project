package com.flipkart.restController;

import java.util.Scanner;
import org.apache.log4j.Logger;

import com.flipkart.service.UserOperation;
import com.flipkart.constant.Gender;
import com.flipkart.constant.Role;
import com.flipkart.exception.StudentNotRegisteredException;
import com.flipkart.exception.UserNotFoundException;
import com.flipkart.service.StudentInterface;
import com.flipkart.service.StudentOperation;
import com.flipkart.service.UserInterface;

import java.sql.SQLException;

import javax.validation.constraints.NotNull;
import javax.validation.constraints.Size;
import javax.ws.rs.Consumes;
import javax.ws.rs.FormParam;
import javax.ws.rs.GET;
import javax.ws.rs.POST;
import javax.ws.rs.Path;
import javax.ws.rs.Produces;
import javax.ws.rs.QueryParam;
import javax.ws.rs.core.MediaType;
import javax.ws.rs.core.Response;
import com.flipkart.bean.Student;
import com.flipkart.bean.User;
import com.flipkart.client.AdminCRSMenu;
import com.flipkart.client.CRSApplication;
import com.flipkart.client.ProfessorCRSMenu;
import com.flipkart.client.StudentCRSMenu;
/**
 * @author pranaybansal
 *
 */

@Path("/userRestApi")
public class UserRestApi{
	
	@POST
	@Path("/login")
	public Response verifyLogin(@NotNull @FormParam("userId") String userId, @NotNull @FormParam("password") String password) {
		UserInterface userInterface =UserOperation.getInstance();
		try 
		{
			Boolean loggedin = userInterface.verifyCredentials(userId, password);
			
			if(loggedin)
			{
				return Response.status(200).entity("Login successful").build();
			}
			else
			{
				return Response.status(401).entity("Login failed").build();
			}
		}
		catch (UserNotFoundException e) 
		{
			System.out.println("Hello");
			return Response.status(401).entity(e.getMessage()).build();
		} 
	}
	
	@POST
	@Path("/registerStudent")
	public Response registerStudent(@NotNull @FormParam("Name") String name, @NotNull @FormParam("ID") String userId,
									@NotNull @FormParam("Password") String password, @NotNull @FormParam("Branch") String branchName,
									@NotNull @FormParam("Batch") String batch, @NotNull @FormParam("Address") String address,
									@NotNull @FormParam("Country") String country, @NotNull @FormParam("Gender") String gender) {
		
		StudentInterface studentInterface=StudentOperation.getInstance();
		
		try {
			Gender genderV=Gender.getName(Integer.parseInt(gender));
		
			studentInterface.register(name, userId, password, genderV, Integer.parseInt(batch), branchName, address, country);
			return Response.status(201).entity("{'message':'Student Registered!'}").build();
		}
		catch(StudentNotRegisteredException ex)
		{
			return Response.status(401).entity("Something went wrong! "+ex.getStudentName() +" not registered. Please try again").build();
		}
		
		
	}
	
	
}
