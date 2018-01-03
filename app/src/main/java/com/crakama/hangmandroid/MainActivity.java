package com.crakama.hangmandroid;

import android.os.Handler;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.util.Log;
import android.view.View;

import com.crakama.hangmandroid.server.Server;

import java.io.IOException;
import java.net.Socket;

public class MainActivity extends AppCompatActivity {
    ConnectionHandler connectionHandler;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

    }

    public  void Start(View view){

        Thread serverThread = new Thread(new Runnable() {

            @Override
            public void run() {

                try {
                    Socket clientHangSock = new Socket("192.168.137.50", 1213);
                    connectionHandler = new ConnectionHandler(clientHangSock);
                    connectionHandler.sendMessage("start");
                 String msg =   connectionHandler.readMessage();

                } catch (ClassNotFoundException|IOException e) {
                    e.printStackTrace();
                }
            }
        });
        serverThread.start();
        Log.i("CLIENT", "END OF THREAD 1 EXECUTION 2");
    }

    public void Play(View view)throws IOException {

        Thread serverThread2 = new Thread(new Runnable() {
            @Override
            public void run() {

            }
        });
        serverThread2.start();
        Log.i("CLIENT", "END OF THREAD 2 EXECUTION 2");
    }
}
