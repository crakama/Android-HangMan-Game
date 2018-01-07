package com.crakama.hangmandroidclient;

import android.os.Message;

/**
 * Created by kate on 04/01/2018.
 */

public interface ConnectionPresenterInt {
    void connectToServer(String ipAddress);
    void replyToClient(String reply);
    void msgToClient(Message serverReply);
    void msgToServer(String text);
}
