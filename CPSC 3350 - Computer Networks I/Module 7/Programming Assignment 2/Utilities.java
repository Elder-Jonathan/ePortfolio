import java.nio.ByteBuffer;
import java.nio.charset.StandardCharsets;

/*
* Jonathan Elder
* Programming Assignment 2 CPSC 3350 Computer Networks
* 2/18/2024

*/

public class Utilities {

    // Converts an integer to a byte array.
    public static byte[] intToBytes(int value) {
        ByteBuffer buffer = ByteBuffer.allocate(Integer.BYTES);
        buffer.putInt(value);
        return buffer.array();
    }

    // Converts a short to a byte array.
    public static byte[] shortToBytes(short value) {
        ByteBuffer buffer = ByteBuffer.allocate(Short.BYTES);
        buffer.putShort(value);
        return buffer.array();
    }

    // Encodes a string to a byte array using UTF-16BE format.
    public static byte[] stringToUTF16BEBytes(String string) {
        return string.getBytes(StandardCharsets.UTF_16BE);
    }

    // Converts a byte array to a hex string for display.
    public static String bytesToHex(byte[] bytes) {
        StringBuilder sb = new StringBuilder();
        for (byte b : bytes) {
            sb.append(String.format("%02X ", b));
        }
        return sb.toString().trim();
    }

    // Performs the specified operation based on the opcode using cases to pick the correct operation.
    public static int performOperation(int opCode, int operand1, int operand2) {
        switch (opCode) {
            case 0:
                return operand1 * operand2;
            case 1: // Division, with check to prevent division by zero
                if (operand2 == 0) {
                    throw new ArithmeticException("Division by zero");
                }
                return operand1 / operand2;
            case 2:
                return operand1 | operand2;
            case 3:
                return operand1 & operand2;
            case 4:
                return operand1 - operand2;
            case 5:
                return operand1 + operand2;
            default:
                throw new IllegalArgumentException("Invalid operation code: " + opCode);
        }
    }

    // Returns the operation name based on the opcode case number.
    public static String getOperationName(int opCode) {
        switch (opCode) {
            case 0: return "multiplication";
            case 1: return "division";
            case 2: return "or";
            case 3: return "and";
            case 4: return "subtraction";
            case 5: return "addition";
            default: throw new IllegalArgumentException("Invalid operation code: " + opCode);
        }
    }

    // Creates a request byte array including the operation name encoded in UTF-16BE format.
    public static byte[] createRequest(int opCode, int operand1, int operand2, String opName) {
        byte[] opNameBytes = stringToUTF16BEBytes(opName);
        ByteBuffer buffer = ByteBuffer.allocate(1 + 1 + Integer.BYTES * 2 + Short.BYTES + 1 + opNameBytes.length);
        buffer.put((byte) (buffer.capacity()));
        buffer.put((byte) opCode);
        buffer.putInt(operand1);
        buffer.putInt(operand2);
        buffer.putShort((short) (Math.random() * Short.MAX_VALUE)); // Randomly generated request ID.
        buffer.put((byte) opNameBytes.length);
        buffer.put(opNameBytes);
        return buffer.array();
    }

    // Parses a response byte array and prints the result for viewing.
    public static void printResponse(byte[] response) {
        ByteBuffer buffer = ByteBuffer.wrap(response);
        int tml = buffer.get();
        int result = buffer.getInt();
        byte errorCode = buffer.get();
        short requestId = buffer.getShort();

        String message = String.format("Response: TML=%d, Result=%d, Error=%d, RequestID=%d", tml, result, errorCode, requestId);
        if (errorCode == 0) {
            System.out.println(message + " (OK)");
        } else {
            System.out.println(message + " (ERROR)");
        }
    }
}