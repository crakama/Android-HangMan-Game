package com.crakama.hangmandroid.services;

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
        System.out.println("Server is Running:::...");
        try {

            while (true){
                String readDataStr = connectionHandler.readMessage();
                System.out.println(readDataStr);
                switch (readDataStr){
                    case "start":
                        serveInterface.initializeGame(clientSocket);
                        System.out.println("Start thread finished");
                        break;
                    case "yes":
                        //Start
                        serveInterface.startGame(connectionHandler, clientSocket);
                        System.out.println("LOOP ENDED");
                        break;
                    case "quit":
                        break;
                    default:
                        //serveInterface = new ServerInterfaceImpl(connectionHandler,readDataStr);
                        serveInterface.playGame(connectionHandler);
                        break;
                }
            }
        } catch (IOException | ClassNotFoundException e) {
            e.printStackTrace();
        }
    }
    //TO DO Have a server interface and its Impl and migrate this method then invoke the method by reference.
    public void processRequests(){

    }
}
