import java.io.*;
import java.net.Socket;
import java.util.Scanner;

/*
* Jonathan Elder
* Programming Assignment 2 CPSC 3350 Computer Networks
* 2/18/2024
*/

public class ClientTCP {
    private static final String SERVER_ADDRESS = "131.204.14.238"; // This is the local address of TUX238 where ServerTCP.java is located.
    private static final int SERVER_PORT = 10018; // The port number for Team 8.

    public static void main(String[] args) {
        try (Scanner scanner = new Scanner(System.in)) {
            System.out.println("Connecting to server..");
            try (Socket socket = new Socket(SERVER_ADDRESS, SERVER_PORT);
                 DataOutputStream dos = new DataOutputStream(socket.getOutputStream());
                 DataInputStream dis = new DataInputStream(socket.getInputStream())) {

                // RTT tracking.
                long minRTT = Long.MAX_VALUE;
                long maxRTT = 0;
                long totalRTT = 0;
                int requestCount = 0;

                while (true) {
                    System.out.println("Enter operation code (0: *, 1: /, 2: |, 3: &, 4: -, 5: +), operand1, operand2 separated by commas (or 'quit' to exit): ");
                    String input = scanner.nextLine();
                    if ("quit".equalsIgnoreCase(input)) break;

                    try {
                        String[] parts = input.split(",");
                        if (parts.length < 3) {
                            System.out.println("Invalid input format. Please use the format 'opCode,operand1,operand2'");
                            continue;
                        }

                        int opCode = Integer.parseInt(parts[0].trim());
                        int operand1 = Integer.parseInt(parts[1].trim());
                        int operand2 = Integer.parseInt(parts[2].trim());

                        String opName = Utilities.getOperationName(opCode);
                        byte[] request = Utilities.createRequest(opCode, operand1, operand2, opName);

                        long startTime = System.currentTimeMillis(); // Capture start time.
                        dos.write(request);
                        System.out.println("Request Sent in Hex: " + Utilities.bytesToHex(request));

                        byte[] response = new byte[9];
                        dis.readFully(response);
                        long endTime = System.currentTimeMillis(); // Capture end time.

                        long rtt = endTime - startTime; // Calculate RTT for this request.
                        minRTT = Math.min(minRTT, rtt);
                        maxRTT = Math.max(maxRTT, rtt);
                        totalRTT += rtt;
                        requestCount++;

                        System.out.println("Response Received in Hex: " + Utilities.bytesToHex(response));
                        Utilities.printResponse(response);
                    } catch (NumberFormatException e) {
                        System.out.println("Error: Please ensure opCode, operand1, and operand2 are integers.");
                    } catch (Exception e) {
                        System.out.println("An error occurred: " + e.getMessage());
                        e.printStackTrace();
                    }
                }

                // Calculate and display RTT statistics.
                long avgRTT = (requestCount > 0) ? totalRTT / requestCount : 0;
                System.out.println("\nRTT Report:");
                System.out.println("Min RTT: " + minRTT + " ms");
                System.out.println("Max RTT: " + maxRTT + " ms");
                System.out.println("Average RTT: " + avgRTT + " ms");
            }
        } catch (IOException e) {
            System.err.println("Client error: " + e.getMessage());
            e.printStackTrace();
        }
    }
}