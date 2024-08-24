import java.io.*;
import java.net.ServerSocket;
import java.net.Socket;
import java.nio.ByteBuffer;


/*
* Jonathan Elder
* Programming Assignment 2 CPSC 3350 Computer Networks
* 2/18/2024

*/

public class ServerTCP {
	
	// This is the port number for Team 8 I am supposed to used. Made it a final CONSTANT.
    private static final int PORT = 10018;
	
	// Main for Server. Includes error handling.
    public static void main(String[] args) {
        try (ServerSocket serverSocket = new ServerSocket(PORT)) {
            System.out.println("Server is listening on port " + PORT);

            while (true) {
                try (Socket clientSocket = serverSocket.accept()) {
                    System.out.println("Client connected");
                    handleClient(clientSocket);
                } catch (Exception e) {
                    System.err.println("Error Message: " + e.getMessage());
                    e.printStackTrace();
                }
            }
        } catch (IOException e) {
            System.err.println("Server exception: " + e.getMessage());
            e.printStackTrace();
        }
    }

    private static void handleClient(Socket clientSocket) {
        try (DataInputStream dis = new DataInputStream(clientSocket.getInputStream());
             DataOutputStream dos = new DataOutputStream(clientSocket.getOutputStream())) {

            // Read the Total Message Length first
            int tml = dis.readUnsignedByte();
            byte[] requestBytes = new byte[tml - 1];
            dis.readFully(requestBytes);

            ByteBuffer requestBuffer = ByteBuffer.wrap(requestBytes);
            int opCode = requestBuffer.get();
            int operand1 = requestBuffer.getInt();
            int operand2 = requestBuffer.getInt();
            short requestId = requestBuffer.getShort();
            byte opNameLength = requestBuffer.get();
            byte[] opNameBytes = new byte[opNameLength];
            requestBuffer.get(opNameBytes);
            String opName = new String(opNameBytes, "UTF-16BE");

            // Display the request in hexadecimal.
            System.out.println("Received Request in Hex: " + Utilities.bytesToHex(requestBytes));
			
            // Display request in a format that is easily viewable.
            System.out.printf("Request ID: %d, Operation: %s (%d, %d)\n", requestId, opName, operand1, operand2);

            int result;
            byte errorCode = 0;
            try {
                result = Utilities.performOperation(opCode, operand1, operand2);
            } catch (ArithmeticException e) {
                result = 0;
                errorCode = 127; // Error code for invalid request as said in description.
            }

            ByteBuffer responseBuffer = ByteBuffer.allocate(9);
            responseBuffer.put((byte) responseBuffer.capacity());
            responseBuffer.putInt(result);
            responseBuffer.put(errorCode);
            responseBuffer.putShort(requestId);
            byte[] response = responseBuffer.array();

            dos.write(response);

        } catch (IOException e) {
            System.err.println("Error message: " + e.getMessage());
            e.printStackTrace();
        }
    }
}