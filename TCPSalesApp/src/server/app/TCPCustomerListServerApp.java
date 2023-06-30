package server.app;

import java.io.ObjectOutputStream;
import java.io.OutputStream;
import java.net.ServerSocket;
import java.net.Socket;
import java.util.List;

import model.Customer;
import server.controller.CustomerDataManager;

public class TCPCustomerListServerApp {

public static void main(String[] args) {
		
		int portNo = 8018;
		
		CustomerDataManager customerDataManager = new CustomerDataManager();
		
		System.out.println("\n\tExecuting TCPCustomerListServerApp");
		
		try {
			
			System.out.println("\tWaiting for next request");
			
			// 1. Bind to a port
			ServerSocket serverSocket = new ServerSocket(portNo); 
			
			// 2. Server need to continually run to listen to request
			while (true) {
				
				// 3. Accept request from client
				Socket clientSocket = serverSocket.accept();
								
				// Get product
				List<Customer> customers = customerDataManager.getListCustomer();
				
				// 4. Respond to client
				OutputStream os  = clientSocket.getOutputStream();
				ObjectOutputStream oos = new ObjectOutputStream(os);
				oos.writeObject(customers);
				System.out.println("\tSending : " + customers.size() 
					+ " customers");
				
			}
		
		} catch (Exception ex) {
			
			ex.printStackTrace();
		}

	}
	
	
}
