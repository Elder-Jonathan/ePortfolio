public class ResponseDecoder {
    // Decodes a byte array back into a Response object
    public Response decode(byte[] data) {
        return Response.fromByteArray(data);
    }
}