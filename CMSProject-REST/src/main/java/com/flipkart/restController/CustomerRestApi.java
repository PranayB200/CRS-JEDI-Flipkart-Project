/**
 * 
 */
package com.flipkart.restController;

import javax.ws.rs.GET;
import javax.ws.rs.Path;
import javax.ws.rs.Produces;
import javax.ws.rs.core.MediaType;

import com.flipkart.bean.Customer;

/**
 * @author pranaybansal
 *
 */
@Path("/customerApi")
public class CustomerRestApi {
	
	// GET (Fetch) Implementation.
	@GET
	@Path("/customer")
	@Produces(MediaType.APPLICATION_JSON)
	public Customer getCustomerDetails() {
		
		//  We have to call all the services in the Rest layer with the respective methods.
		//  client --- service ---- dao ----> SQL
       
		Customer customer=new Customer();
		customer.setCustomerId(101);
		customer.setCustomerName("ABC");
		customer.setCustomerAddress("Delhi");
		
	   return customer;

	}

}
