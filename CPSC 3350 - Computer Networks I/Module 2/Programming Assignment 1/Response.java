import java.nio.ByteBuffer;

public class Response {
    private byte TML;           // Total Message Length
    private int Result;         // Result of the operation
    private byte ErrorCode;     // Error Code (0 for success)
    private short RequestID;    // Request ID to match the request

    // Constructor that initializes the fields
    public Response(int result, byte errorCode, short requestID) {
        this.TML = 8; // TML is fixed: 1 byte for TML, 4 bytes for Result, 1 byte for ErrorCode, and 2 bytes for RequestID
        this.Result = result;
        this.ErrorCode = errorCode;
        this.RequestID = requestID;
    }

    // Method to convert this object into a byte array
    public byte[] toByteArray() {
        ByteBuffer buffer = ByteBuffer.allocate(TML);
        buffer.put(TML);
        buffer.putInt(Result);
        buffer.put(ErrorCode);
        buffer.putShort(RequestID);
        return buffer.array();
    }

    // Static method to create a Response object from a byte array
    public static Response fromByteArray(byte[] array) {
        ByteBuffer buffer = ByteBuffer.wrap(array);

        byte tml = buffer.get();
        int result = buffer.getInt();
        byte errorCode = buffer.get();
        short requestID = buffer.getShort();

        return new Response(result, errorCode, requestID);
    }

    // Getters and setters for each field (if needed)
    // ...
}