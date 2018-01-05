package com.crakama.hangmandroidclient;

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
    }


}
