import java.net.DatagramPacket;
import java.net.DatagramSocket;
import java.net.InetAddress;

public class SendUDP {
    public static void main(String[] args) throws Exception {
        if (args.length != 3) {
            System.out.println("Usage: java SendUDP <Destination> <Port> <Message>");
            return;
        }

        InetAddress destAddr = InetAddress.getByName(args[0]);  // Destination address
        int destPort = Integer.parseInt(args[1]);               // Destination port
        
        // Example usage, replace with dynamic user input for real application
        Request request = new Request(/* OpCode, Operand1, Operand2, RequestID, OpName */);
        
        RequestEncoder encoder = new RequestEncoder();
        byte[] codedRequest = encoder.encode(request);

        try (DatagramSocket sock = new DatagramSocket()) {
            DatagramPacket packet = new DatagramPacket(codedRequest, codedRequest.length, destAddr, destPort);
            sock.send(packet);
        }
    }
}