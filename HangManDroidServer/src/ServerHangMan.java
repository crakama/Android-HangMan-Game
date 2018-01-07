import java.io.IOException;
import java.net.ServerSocket;
import java.net.Socket;

/**
 * Created by kate on 02/01/2018.
 */

public class ServerHangMan {
    ServerSocket serverSocket;
    Socket clientSocket;
    public static void main(String[] args){
        int DEFAULT_PORT = 1212;
        try {
            new ServerHangMan().initConnection(DEFAULT_PORT);
        } catch (IOException | ClassNotFoundException e) {
            e.printStackTrace();
        }
    }

    public void initConnection(int port) throws IOException, ClassNotFoundException {
        serverSocket = new ServerSocket(port);
        System.out.println("\nServer Started:::...");

        while (true){
            clientSocket = serverSocket.accept();
            System.out.println("\nServer Accepted Connection:::");
            startClientHandler(clientSocket);
        }
    }
    public void startClientHandler(Socket clientSoConn) throws IOException, ClassNotFoundException {
        RequestsHandler requestsHandler = new RequestsHandler(clientSoConn);
        Thread serverThread = new Thread(requestsHandler);
        serverThread.start();
    }
}


