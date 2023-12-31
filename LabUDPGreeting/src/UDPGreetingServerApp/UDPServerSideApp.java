package UDPGreetingServerApp;


import java.io.IOException;
import java.math.BigInteger;
import java.net.DatagramPacket;
import java.net.DatagramSocket;
import java.net.InetAddress;

import serverside.SentenceProcessor;

/**
 * An example of server-Side application using UDP
 * 
 * How to run this application
 * 1. Open terminal
 * 2. Change to directory to /workspace-dad/udpdemo/bin
 * 3. java UDPServerSideApp
 * 4. Initial output: 
 * 	UDPServerSideApp: Demonstration of UDP Server-Side Application.
 * 5. Final output: 
 * 	Message received: Good morning.
 * 
 * 
 * @author emalianakasmuri
 *
 */

public class UDPServerSideApp {

	public static void main(String[] args) {
		
		System.out.println("UDPServerSideApp: Demonstration of UDP Server-Side "
				+ "Application.");
		
		
		// Permissible port for this application
		int portNo = 8083;
        
        try {
        	
    		// 1. Bind a DatagramSocket's object to a port number
            DatagramSocket datagramSocket = new DatagramSocket(portNo);
        	
            // Continually listen for request
        	while (true) {
                
                // 2. Variable to received data from the port
        		// 65535 is the maximum size for UDP packet
                byte[] receivedData = new byte[65535];
          
                // 3. Object represents packet from client
                DatagramPacket receivedPacket = 
                		new DatagramPacket(receivedData, receivedData.length);
                
                // 4. Receive packet
				datagramSocket.receive(receivedPacket);
				
				// 5. Extract data from packet
				receivedData = receivedPacket.getData();
				
				// 6. Further processing
				SentenceProcessor processor = 
						new SentenceProcessor(receivedData);
				String sentence = processor.getSentence();
				System.out.println("\nMessage received: " + sentence + ".\n");
				
				// This is not used because it give a misleading result
	            // int length = sentence.length();
	            
	            // More processing
	            int totalCharacters =  processor.countCharacters();
	            int totalVowel = processor.countVowel();
	            int totalConsonant = processor.countConsonant();
	            int totalPunctuation = processor.countPunctuation();
	            
	            byte[] outData = processor.convertToByteArray(totalCharacters);
	            byte[] outDataVowel = processor.convertToByteArray(totalVowel);
	            byte[] outDataConsonant = processor.convertToByteArray(totalConsonant);
	            byte[] outDataPunctuation = processor.convertToByteArray(totalPunctuation);
	            
	            // 7. Get the client information
	            InetAddress clientAddress =  receivedPacket.getAddress();
	            int clientPort = receivedPacket.getPort();
	            int sizeOutData = outData.length;
	            int sizeOutDataVowel = outDataVowel.length;
	            int sizeOutDataConsonant = outDataConsonant.length;
	            int sizeOutDataPunctuation = outDataPunctuation.length;
	            
	            
	            // 8. Wrap data into datagram packet
	            DatagramPacket outPacket = new DatagramPacket(outData, 
	            		sizeOutData, clientAddress, clientPort);
	            
	            DatagramPacket outPacketVowel = new DatagramPacket(outDataVowel, 
	            		sizeOutDataVowel, clientAddress, clientPort);
	            DatagramPacket outPacketConsonant = new DatagramPacket(outDataConsonant, 
	            		sizeOutDataConsonant, clientAddress, clientPort);
	            DatagramPacket outPacketPunctuation = new DatagramPacket(outDataPunctuation, 
	            		sizeOutDataPunctuation, clientAddress, clientPort);
	            
	            // 9. Reply to client
	            datagramSocket.send(outPacket);
	            datagramSocket.send(outPacketVowel);
	            datagramSocket.send(outPacketConsonant);
	            datagramSocket.send(outPacketPunctuation);
	            
	            
	            System.out.println("Message sent (totalCharacters) : " 
	            		+ totalCharacters  + ".\n");
	            System.out.println("Message sent (totalVowel) : " 
	            		+ totalVowel  + ".\n");
	            System.out.println("Message sent (totalConsonant) : " 
	            		+ totalConsonant  + ".\n");
	            System.out.println("Message sent (totalPunctuation) : " 
	            		+ totalPunctuation  + ".\n");
        	}
				
		} catch (IOException e) {
				
			e.printStackTrace();
        }
        
        System.out.println("UDPClientSideApp: End of program.");
    }
}
