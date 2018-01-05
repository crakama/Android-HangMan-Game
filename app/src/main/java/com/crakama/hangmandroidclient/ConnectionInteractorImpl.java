package com.crakama.hangmandroidclient;

import android.util.Log;

import java.io.IOException;
import java.net.Socket;
import java.util.LinkedList;
import java.util.Queue;

/**
 * Created by kate on 04/01/2018.
 */

public class ConnectionInteractorImpl implements ConnectionInteractor {
    private Socket clientSocket;
    private  Thread clientThread;
    static SocketStreamsHandler socketStreamsHandler;
    private ConnectionPresenterInt connectionPresenterInt;
    private int PORT = 1212;
    private  MainActivity mainActivity;
    Queue<SocketStreamsHandler> q = new LinkedList<SocketStreamsHandler>();
    public ConnectionInteractorImpl(MainActivity mainActivity) {
        this.mainActivity = mainActivity;
    }

    @Override
    public void connectToServer(final String ipAddress) {
        clientThread = new Thread(new Runnable() {
            @Override
            public void run() {
                try {
                    clientSocket = new Socket(ipAddress,PORT);
                    ConnectionInteractorImpl.socketStreamsHandler = new SocketStreamsHandler(clientSocket);
                    q.add(socketStreamsHandler);
                    socketStreamsHandler.sendMessage("start");

                    String msg =   socketStreamsHandler.readMessage();
                    connectionPresenterInt = new ConnectionPresenterImpl(mainActivity);
                    connectionPresenterInt.replyToClient(msg);
                    Log.i("SERVER", msg);
                } catch (ClassNotFoundException|IOException e) {
                    e.printStackTrace();
                }
            }
        });
        clientThread.start();
    }

    @Override
    public String clientServerRequests(final String msg) {
        //Create an interface for client socket and use it as universal
        final String[] receivedMesg = new String[1];
        clientThread = new Thread(new Runnable() {
            @Override
            public void run() {
                //socketStreamsHandler = q.element();

                //while (socketStreamsHandler.isConnected()){
                    try {
                        socketStreamsHandler.sendMessage(msg);

                        receivedMesg[0] = socketStreamsHandler.readMessage();
                        connectionPresenterInt = new ConnectionPresenterImpl(mainActivity);
                        connectionPresenterInt.msgToClient(receivedMesg[0]);

                    } catch (ClassNotFoundException|IOException e) {
                        e.printStackTrace();
                    }
                //}
            }
        });
        clientThread.start();
        try {
            clientThread.join();
        } catch (InterruptedException e) {
            e.printStackTrace();
        }

        return receivedMesg[0];
    }
}
