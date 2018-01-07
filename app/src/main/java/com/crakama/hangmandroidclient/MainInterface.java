package com.crakama.hangmandroidclient;

import android.os.Bundle;
import android.os.Message;
import android.support.v4.app.Fragment;

/**
 * Created by kate on 04/01/2018.
 */

public interface MainInterface {
    void setConnectionButton(final boolean enabled);
    void connectionInfo(final String text);
    void gameState(String reply,Bundle args);
    void gameInfo(Message info);
    void changeFragment(Fragment newFragment);
    void startGameDig(String s);
}
