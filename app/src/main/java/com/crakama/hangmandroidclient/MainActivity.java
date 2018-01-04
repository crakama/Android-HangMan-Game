package com.crakama.hangmandroidclient;

import android.net.Uri;
import android.os.Handler;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.widget.EditText;

public class MainActivity extends AppCompatActivity implements ConnectionFragment.OnItemClickedListener {
    private Handler handler;
    private ConnectionPresenterInt connectionPresenterInt;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        this.handler = new Handler();

        if(findViewById(R.id.fragment_container) != null){
            if(savedInstanceState != null){
                return;
            }
            ConnectionFragment connectionFragment = new ConnectionFragment();
            connectionFragment.setArguments(getIntent().getExtras());
            getSupportFragmentManager().beginTransaction().add(R.id.fragment_container,connectionFragment).commit();
        }
    }


    //-------------------------------------------------------------------------
    // Connection methods
    //-------------------------------------------------------------------------

    @Override
    protected  void onDestroy(){
        super.onDestroy();
    }

    @Override
    public void connectionBtnClicked(String ipAdress) {

        connectionPresenterInt = new ConnectionPresenterImpl();
        connectionPresenterInt.connectToServer(ipAdress);
    }
}
