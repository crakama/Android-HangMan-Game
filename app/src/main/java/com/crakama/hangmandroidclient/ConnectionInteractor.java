package com.crakama.hangmandroidclient;

/**
 * Created by kate on 04/01/2018.
 */

public interface ConnectionInteractor {
    void connectToServer(String ipAddress);
    String clientServerRequests(String msg);
}
