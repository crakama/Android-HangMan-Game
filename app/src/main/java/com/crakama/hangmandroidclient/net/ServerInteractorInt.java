package com.crakama.hangmandroidclient.net;

/**
 * Created by kate on 04/01/2018.
 */

public interface ServerInteractorInt {
    void connectToServer(String ipAddress);
    String clientServerRequests(String msg);
}
