public class RequestEncoder {
    // Encodes a Request object into a byte array for transmission
    public byte[] encode(Request request) {
        return request.toByteArray();
    }
}