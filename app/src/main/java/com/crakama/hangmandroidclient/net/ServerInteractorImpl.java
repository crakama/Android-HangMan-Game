package com.crakama.hangmandroidclient.net;

import android.util.Log;

import com.crakama.hangmandroidclient.commhandler.GamePresenterImpl;
import com.crakama.hangmandroidclient.commhandler.GamePresenterInt;
import com.crakama.hangmandroidclient.startup.MainActivity;

import java.io.IOException;
import java.net.Socket;
import java.util.LinkedList;
import java.util.Queue;

/**
 * Created by kate on 04/01/2018.
 */

public class ServerInteractorImpl implements ServerInteractorInt {
    static SocketStreamsHandler socketStreamsHandler;
    Queue<SocketStreamsHandler> q = new LinkedList<SocketStreamsHandler>();
    private Socket clientSocket;
    private  Thread clientThread;
    private GamePresenterInt gamePresenterInt;
    private int PORT = 1212;
    private MainActivity mainActivity;
    public ServerInteractorImpl(MainActivity mainActivity) {
        this.mainActivity = mainActivity;
    }

    @Override
    public void connectToServer(final String ipAddress) {
        clientThread = new Thread(new Runnable() {
            @Override
            public void run() {
                try {
                    clientSocket = new Socket(ipAddress,PORT);
                    ServerInteractorImpl.socketStreamsHandler = new SocketStreamsHandler(clientSocket);
                    q.add(socketStreamsHandler);
                    socketStreamsHandler.sendMessage("start");

                    String msg =   socketStreamsHandler.readMessage();
                    gamePresenterInt = new GamePresenterImpl(mainActivity);
                    gamePresenterInt.replyToClient(msg);
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
                if(socketStreamsHandler.isConnected()){
                    try {
                        socketStreamsHandler.sendMessage(msg);

                        receivedMesg[0] = socketStreamsHandler.readMessage();
                        gamePresenterInt = new GamePresenterImpl(mainActivity);


                    } catch (ClassNotFoundException|IOException e) {
                        e.printStackTrace();
                        gamePresenterInt.failedConnection("Connection to Server Failed");
                    }
                }else {
                    //receivedMesg[0] = "Connection to Server Failed";
                }


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
