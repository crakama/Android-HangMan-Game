package com.crakama.hangmandroidclient;

/**
 * Created by kate on 04/01/2018.
 */

public interface ConnectionPresenterInt {
    void connectToServer(String ipAddress);
    void replyToClient(String reply);
    void msgToClient(String serverReply);
    void msgToServer(String text);
}
