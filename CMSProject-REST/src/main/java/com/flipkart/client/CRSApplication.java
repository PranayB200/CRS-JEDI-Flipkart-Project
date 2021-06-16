package com.flipkart.client;

import java.util.Scanner;

import org.apache.log4j.BasicConfigurator;
import org.apache.log4j.Logger;

import com.flipkart.service.UserOperation;
import com.flipkart.constant.Gender;
import com.flipkart.constant.Role;
import com.flipkart.exception.StudentNotRegisteredException;
import com.flipkart.exception.UserNotFoundException;
import com.flipkart.service.StudentInterface;
import com.flipkart.service.StudentOperation;
import com.flipkart.service.UserInterface;

/**
 * 
 * @author JEDI-04-G3
 * This class is used as the main entry point of the application
 * In main menu to login, register are displayed
 * 
 */
public class CRSApplication {

	private static Logger logger = Logger.getLogger(CRSApplication.class);
	static boolean loggedin = false;
	StudentInterface studentInterface=StudentOperation.getInstance();
	UserInterface userInterface =UserOperation.getInstance();
	
	public static void main(String[] args) {
		BasicConfigurator.configure();
		Scanner sc = new Scanner(System.in);
		CRSApplication crsApplication=new CRSApplication();
		int userInput;	
		//create the main menu
		createMainMenu();
		userInput=sc.nextInt();
		try
		{
			
		//until user do not exit the application
		while(userInput!=4)
		{
			switch(userInput)
			{	
				case 1:
					//login
					crsApplication.loginUser();
					break;
				case 2:
					//student registration
					crsApplication.registerStudent();
					break;	
				case 3:
					crsApplication.updatePassword();
					break;
				default:
					System.out.println("Invalid Input");
			}
			createMainMenu();
			userInput=sc.nextInt();
		}
		}
		catch(Exception ex)
		{
			logger.error("Error occured "+ex);
		}
		finally
		{
			sc.close();
		}
	}
	
	/**
	 * Method to Create Main Menu
	 */
	public static void createMainMenu()
	{
		System.out.println("----------Welcome to Course Management System---------");
		System.out.println();
		System.out.println("1. Login");
		System.out.println("2. Student Registration");
		System.out.println("3. Update password");
		System.out.println("4. Exit");
		System.out.println();
		System.out.println("------------------------------------------------------");
		System.out.println("Enter user input");
	}
	
	/**
	 * Method for Login functionality
	 */
	public void loginUser()
	{
		//multiple exceptions are possible
		//invalid credential exception
		//user not found exception
		//user not approved exception
		Scanner sc=new Scanner(System.in);

		String userId,password;
		try
		{
			System.out.println("-----------------Login------------------");
			System.out.println("ID:");
			userId=sc.next();
			System.out.println("Password:");
			password=sc.next();
			loggedin = userInterface.verifyCredentials(userId, password);
			//2 cases
			//true->role->student->approved
			if(loggedin)
			{
				//System.out.println("Welcome "+userId);
				String role=userInterface.getRole(userId);
				Role userRole=Role.stringToName(role);
				switch(userRole)
				{
				case ADMIN:
					System.out.println("Logged In!");
					AdminCRSMenu adminMenu=new AdminCRSMenu();
					adminMenu.createMenu();
					break;
				case PROFESSOR:
					System.out.println("Logged In!");
					ProfessorCRSMenu professorMenu=new ProfessorCRSMenu();
					professorMenu.createMenu(userId);
					
					break;
				case STUDENT:
					
					int studentId=studentInterface.getStudentId(userId);
					boolean isApproved=studentInterface.isApproved(studentId);
					
					// To be debugged later.
					if(isApproved)
					{
						System.out.println("Logged In!");
						StudentCRSMenu studentMenu=new StudentCRSMenu();
						studentMenu.create_menu(studentId);
						
					}
					else
					{
						logger.warn("Failed to login, you have not been approved by the administration!");
						loggedin=false;
					}
					break;
				}
				
				
			}
			else
			{
				logger.error("Invalid Credentials!");
			}
			
		}
		catch(UserNotFoundException ex)
		{
			logger.error(ex.getUserId()+" not registered!");
		}	
	}
	
	/**
	 * Method to help Student register themselves, pending admin approval
	 */
	public void registerStudent()
	{
		Scanner sc=new Scanner(System.in);

		String userId,name,password,address,country,branchName;
		Gender gender;
		int genderV, batch;
		try
		{
			//input all the student details
			System.out.println("---------------Student Registration-------------");
			System.out.println("Name:");
			name=sc.nextLine();
			System.out.println("ID:");
			userId=sc.next();
			System.out.println("Password:");
			password=sc.next();
			System.out.println("Gender: \t 1: Male \t 2.Female\t 3.Other");
			genderV=sc.nextInt();
			sc.nextLine();
			System.out.println("Branch:");
			branchName=sc.nextLine();
			System.out.println("Batch:");
			batch=sc.nextInt();
			sc.nextLine();
			System.out.println("Address:");
			address=sc.nextLine();
			System.out.println("Country");
			country=sc.next();
			gender=Gender.getName(genderV);
			studentInterface.register(name, userId, password, gender, batch, branchName, address, country);
			
		}
		catch(StudentNotRegisteredException ex)
		{
			logger.error("Something went wrong! "+ex.getStudentName() +" not registered. Please try again");
		}
	}
	
	/**
	 * Method to update password of User
	 */
	public void updatePassword()
	{
		Scanner sc=new Scanner(System.in);
		String userId,newPassword;
		try
		{
			System.out.println("------------------Update Password--------------------");
			System.out.println("ID");
			userId=sc.next();
			System.out.println("New Password:");
			newPassword=sc.next();
			boolean isUpdated=userInterface.updatePassword(userId, newPassword);
			if(isUpdated)
				System.out.println("Password updated successfully!");

			else
				logger.error("Something went wrong, please try again!");
		}
		catch(Exception ex)
		{
			logger.error("Error Occured "+ex.getMessage());
		}
	}
}
