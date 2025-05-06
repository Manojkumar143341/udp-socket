import java.net.*;

public class UDPServer {
    public static void main(String[] args) throws Exception {
        DatagramSocket serverSocket = new DatagramSocket(9876); // Port 9876
        byte[] receiveBuffer = new byte[1024];
        byte[] sendBuffer;

        System.out.println("Server is running and waiting for client...");

        // Receive data from client
        DatagramPacket receivePacket = new DatagramPacket(receiveBuffer, receiveBuffer.length);
        serverSocket.receive(receivePacket);

        String clientMsg = new String(receivePacket.getData(), 0, receivePacket.getLength());
        System.out.println("Received from client: " + clientMsg);

        // Send reply
        String reply = "Hello from server!";
        sendBuffer = reply.getBytes();

        InetAddress clientAddress = receivePacket.getAddress();
        int clientPort = receivePacket.getPort();

        DatagramPacket sendPacket = new DatagramPacket(sendBuffer, sendBuffer.length, clientAddress, clientPort);
        serverSocket.send(sendPacket);

        serverSocket.close();
        System.out.println("Server closed.");
    }
}
