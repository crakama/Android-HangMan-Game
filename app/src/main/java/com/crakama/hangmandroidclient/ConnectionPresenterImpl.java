package com.crakama.hangmandroidclient;

import android.os.AsyncTask;
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
    }
    @Override
    public void msgToClient(String serverReply){
        //TODO :Always display this response somewhere on the user interface
        //mainInterface.
        mainInterface.gameState(serverReply);
    }

    class PresenterTask extends AsyncTask<String, Void,String>{
        @Override
        protected void onPreExecute(){
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
        protected void onPostExecute(String result){
            mainInterface.gameState(result);
        }
    }
}
