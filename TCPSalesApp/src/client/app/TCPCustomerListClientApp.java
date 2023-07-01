package client.app;

import java.io.InputStream;
import java.io.ObjectInputStream;
import java.net.InetAddress;
import java.net.Socket;
import java.util.Collections;
import java.util.Comparator;
import java.util.List;


import model.Customer;

public class TCPCustomerListClientApp {
	public static void main(String[] args) {
		System.out.println("Running TCPCustomerListClientApp...");
		try {
			
			// Server information
			int serverPortNo = 8018;
			InetAddress serverAddress = InetAddress.getLocalHost();

			// 1. Connect to remote machine
			Socket socket = new Socket(serverAddress, serverPortNo);

			// Create stream to receive response from the server
			InputStream is = socket.getInputStream();
			ObjectInputStream ois = new ObjectInputStream(is);

			// 3. Read respond from the server - cast the object
			List<Customer> customers = (List<Customer>) ois.readObject();
			System.out.println("Customer List: ");
			// 4. Process response - display the object
			//Arrange the customer alphabetically 
			customers.sort(Comparator.comparing(Customer::getName));
			for(Customer customer:customers) {

				System.out.println("\tCustomer Id: " + customer.getCustomerId());
				System.out.println("\tName: " + customer.getName());
			}

		} catch (Exception ex) {

			ex.printStackTrace();

		}

	}
}
