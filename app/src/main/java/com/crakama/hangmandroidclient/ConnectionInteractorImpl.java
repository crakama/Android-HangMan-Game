package com.crakama.hangmandroidclient;

import android.util.Log;

import java.io.IOException;
import java.net.Socket;

/**
 * Created by kate on 04/01/2018.
 */

public class ConnectionInteractorImpl implements ConnectionInteractor {
    private Socket clientSocket;
    private  Thread clientThread;
    ConnectionHandler connectionHandler;
    private int PORT = 1213;

    @Override
    public void connectToServer(final String ipAddress) {
        Log.i("CLIENT IP PICKED Inter", ipAddress);
        clientThread = new Thread(new Runnable() {
            @Override
            public void run() {
                try {
                    clientSocket = new Socket(ipAddress,PORT);
                    connectionHandler = new ConnectionHandler(clientSocket);
                    connectionHandler.sendMessage("start");
                    String msg =   connectionHandler.readMessage();
                    Log.i("SERVER", msg);
                } catch (ClassNotFoundException|IOException e) {
                    e.printStackTrace();
                }
            }
        });
        clientThread.start();
    }
}
