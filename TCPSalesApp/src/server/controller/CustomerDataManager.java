package server.controller;

import java.util.ArrayList;
import java.util.List;

import model.Customer;

public class CustomerDataManager {
	
	public CustomerDataManager() {
		generateSampleCustomer();
	}
	
	
	private List<Customer> customers = new ArrayList<>();
	
	//add the customer into a list 
	private void generateSampleCustomer() {
		String[] customerName = { "Jack", "Adam", "William", "Bob", "Jimmy", "Marry", "Philip", "Daniel", "Leon",
				"Chris" };
		int[] customerId = { 1,2,3,4,5,6,7,8,9,10 };
		for (int count = 0; count < 10; count++) {
			Customer customer = new Customer();
			customer.setName(customerName[count]);
			customer.setCustomerId(customerId[count]);
			customers.add(customer);
			
		}

	}
	//search customer using customer name
	public Customer searchCustomerByName(String customerName) {
		for(Customer customer : this.customers) {
			 if (customer.getName().contains(customerName)) {
	                return customer;
	            }
	        }
	        return null;
			
		}
		
	//search customer using customer id 
	public Customer searchCustomerById(int customerId) {
		for(Customer customer : this.customers) {
			 if (customer.getCustomerId() == customerId) {
	                return customer;
	            }
	        }
	        return null;
			
		}
	
	//return a list of customer 
	public List<Customer> getListCustomer() {
		
		return this.customers;
		
	}
	
	
	
}
	
	
	


