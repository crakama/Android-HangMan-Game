package com.crakama.hangmandroidclient;

import android.util.Log;

/**
 * Created by kate on 04/01/2018.
 */

public class ConnectionPresenterImpl implements ConnectionPresenterInt {
    ConnectionInteractor connectionInteractor;

    @Override
    public void connectToServer(final String ipAddress) {
        Log.i("CLIENT IP PICKED", ipAddress);
        connectionInteractor = new ConnectionInteractorImpl();
        connectionInteractor.connectToServer(ipAddress);
    }

}
