package com.crakama.hangmandroid.server;

import android.util.Log;

import java.io.IOException;
import java.net.ServerSocket;
import java.net.Socket;

/**
 * Created by kate on 03/01/2018.
 */

public class Server {
    private ServerSocket serverSocket;
    private Socket socket;
    private int DEFALT_PORT = 1212;

    /**
     * Route server operations
     * @throwsIOException
     */
    public Server() throws IOException {
        //initConnection();
    }

    public void initConnection() throws IOException {
        Log.i("SERVER", "REQUEST HANDLER STARTED 1");
        serverSocket = new ServerSocket(DEFALT_PORT);
        Log.i("SERVER", "REQUEST HANDLER STARTED 2");

        while (true){
            Log.i("SERVER", "REQUEST HANDLER STARTED 3");
            socket = serverSocket.accept();
            Log.i("SERVER", "REQUEST HANDLER STARTED 4");
            startRequestHandler();
        }
    }

    private void startRequestHandler() {
        Log.i("SERVER", "REQUEST HANDLER STARTED");

        Thread clientHandlerThread = new Thread(new Runnable() {
            @Override
            public void run() {
                Log.i("SERVER", "REQUEST HANDLER RUM METHOD");
                System.out.println(" REQUEST HANDLER STARTED");
            }
        });
       clientHandlerThread.start();
    }

}
