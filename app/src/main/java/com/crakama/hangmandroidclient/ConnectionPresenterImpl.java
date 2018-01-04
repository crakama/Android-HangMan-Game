package com.crakama.hangmandroidclient;

import android.util.Log;

/**
 * Created by kate on 04/01/2018.
 */

public class ConnectionPresenterImpl implements ConnectionPresenterInt {
    ConnectionInteractor connectionInteractor;
<<<<<<< HEAD
    private MainInterface mainInterface ;

    public ConnectionPresenterImpl(MainActivity mainActivity) {
        this.mainInterface = mainActivity;
        this.connectionInteractor = new ConnectionInteractorImpl(mainActivity);
    }
=======
>>>>>>> de414d6... Update Model View Presenter

    @Override
    public void connectToServer(final String ipAddress) {
        Log.i("CLIENT IP PICKED", ipAddress);
<<<<<<< HEAD
        mainInterface.setConnectionButton(false);
        mainInterface.connectionInfo("Connecting... Please wait");
        //connectionInteractor = new ConnectionInteractorImpl();
        connectionInteractor.connectToServer(ipAddress);
    }

    @Override
    public void replyToClient(String reply) {
        mainInterface.connectionInfo("Connected to server");
    }


=======
        connectionInteractor = new ConnectionInteractorImpl();
        connectionInteractor.connectToServer(ipAddress);
    }

>>>>>>> de414d6... Update Model View Presenter
}
