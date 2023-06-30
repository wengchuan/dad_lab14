package server.app;

import java.io.DataInputStream;
import java.io.InputStream;
import java.io.ObjectOutputStream;
import java.io.OutputStream;
import java.net.ServerSocket;
import java.net.Socket;

import model.Customer;
import server.controller.CustomerDataManager;

public class TCPCustomerServerApp {

	public static void main(String[] args) {
		int portNo = 8077;

		CustomerDataManager customerDataManager = new CustomerDataManager();
		System.out.println("\n\tExecuting TCPCustomerServerApp");

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

				// Read product id from client
				String customerName = dis.readUTF();
				System.out.println("\tRequest for customer name: " + customerName);

				// Get product
				Customer customer = customerDataManager.searchCustomerByName(customerName);

				// 5. Respond to client
				OutputStream os = clientSocket.getOutputStream();
				ObjectOutputStream oos = new ObjectOutputStream(os);
				System.out.print("\tSending pruduct: " + customer.getCustomerId() + " " + customer.getName());
				oos.writeObject(customer);
			

			}

		} catch (Exception ex) {

			ex.printStackTrace();
		}

	}

}
