package com.crakama.hangmandroidclient;

import android.os.AsyncTask;
import android.os.Bundle;
import android.os.Message;
import android.util.Log;

/**
 * Created by kate on 04/01/2018.
 */

public class ConnectionPresenterImpl implements ConnectionPresenterInt {
    ConnectionInteractor connectionInteractor;
    private MainInterface mainInterface ;


    public ConnectionPresenterImpl(MainActivity mainActivity) {
        this.mainInterface = mainActivity;
        this.connectionInteractor = new ConnectionInteractorImpl(mainActivity);
    }

    @Override
    public void connectToServer(final String ipAddress) {
        Log.i("CLIENT IP PICKED", ipAddress);
        mainInterface.setConnectionButton(false);
        mainInterface.connectionInfo("Connecting... Please wait");
        //connectionInteractor = new ConnectionInteractorImpl();
        connectionInteractor.connectToServer(ipAddress);
    }

    @Override
    public void replyToClient(String reply) {
        mainInterface.connectionInfo("Connected to server");
        //Tell Main View to Change Fragment and display reply
        mainInterface.startGameDig(reply);

    }

    @Override
    public void msgToServer(String msg) {
        //TODO do something while waiting for server response
        new PresenterTask().execute(msg);
        /*        if(msg.equalsIgnoreCase("yes")){
                    new PresenterTask().execute(msg);
                }else {
                    new NormalPresenterTask().execute(msg);
                }*/

    }
    @Override
    public void msgToClient(Message serverReply){
        //TODO :Always display this response somewhere on the user interface
        //mainInterface.


    }

    class PresenterTask extends AsyncTask<String, Void,String> {
        @Override
        protected void onPreExecute() {
            //TODO: Change fragment
            GameFragment gameFragment = new GameFragment();
            mainInterface.changeFragment(gameFragment);
        }

        @Override
        protected String doInBackground(String... strings) {
            String str = strings[0];
            return connectionInteractor.clientServerRequests(str);
        }

        @Override
        protected void onPostExecute(String result) {
            Message serverMessage = Message.obtain();
            if (result.substring(0, 12).equals("You win with")) {
                Bundle args = new Bundle();
                args.putString("win", result);
                mainInterface.gameState(result, args);
            } else if (result.substring(0, 10).equals("You loose,")) {
                Bundle args = new Bundle();
                args.putString("loose", result);
                mainInterface.gameState(result, args);
            } else {
            serverMessage.obj = result;
            mainInterface.gameInfo(serverMessage);
            }
         }
        }
    }


