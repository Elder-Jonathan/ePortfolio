import java.nio.ByteBuffer;
import java.nio.charset.StandardCharsets;

public class Request {
    private byte TML;          // Total Message Length
    private byte OpCode;       // Operation Code
    private int Operand1;      // First Operand
    private int Operand2;      // Second Operand
    private short RequestID;   // Request ID
    private byte OpNameLength; // Operation Name Length
    private String OpName;     // Operation Name

    // Constructor that initializes the fields
    public Request(byte opCode, int operand1, int operand2, short requestID, String opName) {
        this.OpCode = opCode;
        this.Operand1 = operand1;
        this.Operand2 = operand2;
        this.RequestID = requestID;
        this.OpName = opName;
        this.OpNameLength = (byte) opName.getBytes(StandardCharsets.UTF_16BE).length;
        this.TML = (byte) (1 + 1 + 4 + 4 + 2 + 1 + OpNameLength);
    }

    // Method to convert this object into a byte array
    public byte[] toByteArray() {
        ByteBuffer buffer = ByteBuffer.allocate(TML);
        buffer.put(TML);
        buffer.put(OpCode);
        buffer.putInt(Operand1);
        buffer.putInt(Operand2);
        buffer.putShort(RequestID);
        buffer.put(OpNameLength);
        buffer.put(OpName.getBytes(StandardCharsets.UTF_16BE));
        return buffer.array();
    }

    // Static method to create a Request object from a byte array
    public static Request fromByteArray(byte[] array) {
        ByteBuffer buffer = ByteBuffer.wrap(array);

        byte tml = buffer.get();
        byte opCode = buffer.get();
        int operand1 = buffer.getInt();
        int operand2 = buffer.getInt();
        short requestID = buffer.getShort();
        byte opNameLength = buffer.get();
        byte[] opNameBytes = new byte[opNameLength];
        buffer.get(opNameBytes);
        String opName = new String(opNameBytes, StandardCharsets.UTF_16BE);

        return new Request(opCode, operand1, operand2, requestID, opName);
    }

    // Getters and setters for each field (if needed)
    // ...
}