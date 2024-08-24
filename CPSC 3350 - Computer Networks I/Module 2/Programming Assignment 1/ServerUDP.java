import java.net.DatagramPacket;
import java.net.DatagramSocket;

public class ServerUDP {
    public static void main(String[] args) throws Exception {
        if (args.length != 1) {
            System.out.println("Usage: java ServerUDP <Port>");
            return;
        }
        
        int port = Integer.parseInt(args[0]);
        
        try (DatagramSocket serverSocket = new DatagramSocket(port)) {
            byte[] receiveData = new byte[RequestBinConst.MAX_PACKET_LENGTH];
            
            while (true) {
                DatagramPacket receivePacket = new DatagramPacket(receiveData, receiveData.length);
                serverSocket.receive(receivePacket);
                
                ResponseDecoder decoder = new ResponseDecoder();
                Request request = Request.fromByteArray(receivePacket.getData());
                
                // Process request and prepare a response
                Response response = new Response(/* Result, ErrorCode, RequestID */);
                
                byte[] sendData = response.toByteArray();
                InetAddress IPAddress = receivePacket.getAddress();
                int sendPort = receivePacket.getPort();
                DatagramPacket sendPacket = new DatagramPacket(sendData, sendData.length, IPAddress, sendPort);
                serverSocket.send(sendPacket);
            }
        }
    }
}