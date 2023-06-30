package client.app;

import java.io.DataOutputStream;
import java.io.InputStream;
import java.io.ObjectInputStream;
import java.io.OutputStream;
import java.net.InetAddress;
import java.net.Socket;

import model.Customer;

public class TCPCustomerIdClientApp {
	public static void main(String[] args) {
		try {

			System.out.println("\tExecuting TCPCustomerClientApp");

			// Server information
			int serverPortNo = 8045;
			InetAddress serverAddress = InetAddress.getLocalHost();

			// 1. Connect to remote machine
			Socket socket = new Socket(serverAddress, serverPortNo);

			// Create stream to send request
			OutputStream os = socket.getOutputStream();
			DataOutputStream dos = new DataOutputStream(os);

			// Create stream to receive response from the server
			InputStream is = socket.getInputStream();
			
			
			// 2. Send request to the server
			int[] customerIds = {5,7,3,1,93};

			for (int count = 0; count < 5; count++) {

				dos.writeInt(customerIds[count]);
				System.out.println("\tRequesting customer name  " + customerIds[count] + "\n");

				
				
				ObjectInputStream ois = new ObjectInputStream(is);
				// 3. Read respond from the server - cast object
				Customer customer = (Customer) ois.readObject();

				// 4. Process response - display the object
				System.out.println("\tCustomer Information (From the server)");
				if(customer != null) {
				
				System.out.println("\tCustomer Id: " + customer.getCustomerId());
				System.out.println("\tName: " + customer.getName());
				}
				else {
					
					System.out.println("\tThe customer does not exist.");
				}
			}

		} catch (Exception ex) {
			 ex.printStackTrace();
		}
	}

}
