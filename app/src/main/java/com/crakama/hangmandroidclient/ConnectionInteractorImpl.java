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
<<<<<<< HEAD
    private ConnectionPresenterInt connectionPresenterInt;
    private int PORT = 1212;
    private  MainActivity mainActivity;

    public ConnectionInteractorImpl(MainActivity mainActivity) {
        this.mainActivity = mainActivity;
    }

    @Override
    public void connectToServer(final String ipAddress) {
=======
    private int PORT = 1213;

    @Override
    public void connectToServer(final String ipAddress) {
        Log.i("CLIENT IP PICKED Inter", ipAddress);
>>>>>>> de414d6... Update Model View Presenter
        clientThread = new Thread(new Runnable() {
            @Override
            public void run() {
                try {
                    clientSocket = new Socket(ipAddress,PORT);
                    connectionHandler = new ConnectionHandler(clientSocket);
                    connectionHandler.sendMessage("start");
                    String msg =   connectionHandler.readMessage();
<<<<<<< HEAD
                    connectionPresenterInt = new ConnectionPresenterImpl(mainActivity);
                    connectionPresenterInt.replyToClient(msg);
=======
>>>>>>> de414d6... Update Model View Presenter
                    Log.i("SERVER", msg);
                } catch (ClassNotFoundException|IOException e) {
                    e.printStackTrace();
                }
            }
        });
        clientThread.start();
    }
}
