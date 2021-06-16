/**
 * 
 */
package com.flipkart.application;

import org.glassfish.jersey.server.ResourceConfig;

import com.flipkart.restController.CustomerRestApi;
import com.flipkart.restController.UserRestApi;

public class ApplicationConfig extends ResourceConfig {
	
	public ApplicationConfig() {
		// Register all the services over here.
		
		register(CustomerRestApi.class);
		register(UserRestApi.class);
//		register(AdminRestApi.class);
//		register(ProfessorRestApi.class);
	}
}
