import java.net.DatagramPacket;
import java.net.DatagramSocket;

public class RecvUDP {
    public static void main(String[] args) throws Exception {
        if (args.length != 1) {
            System.out.println("Usage: java RecvUDP <Port>");
            return;
        }
        
        int port = Integer.parseInt(args[0]);
        
        try (DatagramSocket sock = new DatagramSocket(port)) {
            DatagramPacket packet = new DatagramPacket(new byte[RequestBinConst.MAX_PACKET_LENGTH], RequestBinConst.MAX_PACKET_LENGTH);
            sock.receive(packet);
            
            ResponseDecoder decoder = new ResponseDecoder();
            Response response = decoder.decode(packet.getData());
            
            // Process the response as needed
        }
    }
}