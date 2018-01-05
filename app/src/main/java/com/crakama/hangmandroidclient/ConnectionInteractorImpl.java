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
    private ConnectionPresenterInt connectionPresenterInt;
    private int PORT = 1212;
    private  MainActivity mainActivity;

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
                    connectionHandler = new ConnectionHandler(clientSocket);
                    connectionHandler.sendMessage("start");
                    String msg =   connectionHandler.readMessage();
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
}
