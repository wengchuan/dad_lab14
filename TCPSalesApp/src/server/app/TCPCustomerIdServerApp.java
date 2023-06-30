package server.app;

import java.io.DataInputStream;
import java.io.InputStream;
import java.io.ObjectOutputStream;
import java.io.OutputStream;
import java.net.ServerSocket;
import java.net.Socket;

import model.Customer;
import server.controller.CustomerDataManager;

public class TCPCustomerIdServerApp {
	public static void main(String[] args) {
		int portNo = 8045;

		CustomerDataManager customerDataManager = new CustomerDataManager();
		System.out.println("\n\tExecuting TCPCustomerIdServerApp");

		try {

			System.out.println("\tWaiting for next request");

			// 1. Bind to a port
			ServerSocket serverSocket = new ServerSocket(portNo);

			// 2. Server need to continually run to listen to request
			while (true) {

				// 3. Accept request from client
				Socket clientSocket = serverSocket.accept();

				// 4. Process request - create input stream to read request
				// Request - customerName - string
				InputStream is = clientSocket.getInputStream();
				DataInputStream dis = new DataInputStream(is);
				while (true) {
					// Read product id from client
					int customerId = dis.readInt();
					System.out.println("\tRequest for customer id: " + customerId);

					// Get product
					Customer customer = customerDataManager.searchCustomerById(customerId);

					// 5. Respond to client
					OutputStream os = clientSocket.getOutputStream();
					ObjectOutputStream oos = new ObjectOutputStream(os);
					if (customer != null) {
						System.out.print("\tSending pruduct: " + customer.getCustomerId() + " " + customer.getName());
						oos.writeObject(customer);
					}else{
						System.out.print("\tSending pruduct: Does not exist" );
						oos.writeObject(customer);
					}

				}

			}

		} catch (Exception ex) {

			ex.printStackTrace();
		}

	}
}
