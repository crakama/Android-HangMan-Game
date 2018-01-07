package com.crakama.hangmandroidclient.commhandler;

/**
 * Created by kate on 04/01/2018.
 */

public interface GamePresenterInt {
    void connectToServer(String ipAddress);
    void replyToClient(String reply);
    void failedConnection(String serverReply);
    void startAsyncTask(String text);
}
