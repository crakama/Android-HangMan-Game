import java.io.IOException;
import java.net.Socket;

/**
 * Created by kate on 02/01/2018.
 */

public class RequestsHandler implements Runnable{
    Socket clientSocket;
    ServeInterface serveInterface;
    ConnectionHandler connectionHandler;
    public RequestsHandler(Socket clientSoConn) throws IOException, ClassNotFoundException {
        this.clientSocket = clientSoConn;
        this.connectionHandler = new ConnectionHandler(clientSocket);
        this.serveInterface = new ServerInterfaceImpl(connectionHandler);

    }

    /**
     * Open ObjectInputStream on client socket and read data,Read user data and respond using ObjectOutputStream
     */

    @Override
    public void run() {
        System.out.println("\nServer is Running, waiting for clients Requests:::...");
        try {

            while (true){
                String readDataStr = connectionHandler.readMessage();
                switch (readDataStr){
                    case "start":
                        serveInterface.initializeGame(clientSocket);
                        break;
                    case "yes":
                        serveInterface.playGame(connectionHandler);
                        break;
                    case "quit":
                        break;
                    default:
                        serveInterface.playGame(connectionHandler);
                        break;
                }
            }
        } catch (IOException | ClassNotFoundException e) {
            e.printStackTrace();
        }
    }
    public void processRequests(){

    }
}
