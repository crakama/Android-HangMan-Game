package com.crakama.hangmandroidclient.commhandler;

import android.os.AsyncTask;
import android.os.Bundle;
import android.os.Message;

import com.crakama.hangmandroidclient.net.ServerInteractorImpl;
import com.crakama.hangmandroidclient.net.ServerInteractorInt;
import com.crakama.hangmandroidclient.startup.MainActivity;
import com.crakama.hangmandroidclient.startup.MainInterface;
import com.crakama.hangmandroidclient.ui.GameFragment;

/**
 * Created by kate on 04/01/2018.
 */

public class GamePresenterImpl implements GamePresenterInt {
    ServerInteractorInt serverInteractorInt;
    private MainInterface mainInterface ;


    public GamePresenterImpl(MainActivity mainActivity) {
        this.mainInterface = mainActivity;
        this.serverInteractorInt = new ServerInteractorImpl(mainActivity);
    }

    @Override
    public void connectToServer(final String ipAddress) {
        mainInterface.setConnectionButton(false);
        mainInterface.connectionInfo("Connecting... Please wait");
        serverInteractorInt.connectToServer(ipAddress);
    }

    @Override
    public void replyToClient(String reply) {
        mainInterface.connectionInfo("Connected to server");
        mainInterface.startGameDig(reply);

    }

    @Override
    public void startAsyncTask(String msg) {
        //TODO do something while waiting for server response
        new PresenterTask().execute(msg);
         }
    @Override
    public void failedConnection(String failCon){
        Bundle args = new Bundle();
        args.putString("fail", failCon);
        mainInterface.gameState(failCon, args);

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
            return serverInteractorInt.clientServerRequests(str);
        }

        @Override
        protected void onPostExecute(String result) {
            Message serverMessage = Message.obtain();
            if(result == null){
                String fail = "Connection to Server Failed !!!!";
                Bundle args = new Bundle();
                args.putString("fail", fail);
                mainInterface.gameState(fail, args);
            }else if (result.substring(0, 12).equals("You win with")) {
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


