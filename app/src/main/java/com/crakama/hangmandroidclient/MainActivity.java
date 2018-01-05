package com.crakama.hangmandroidclient;

import android.os.Bundle;
import android.support.v4.app.Fragment;
import android.support.v4.app.FragmentTransaction;
import android.support.v7.app.AppCompatActivity;

public class MainActivity extends AppCompatActivity implements MainInterface,
        ConnectionFragment.OnItemClickedListener,GameFragment.OnGameFragListener{
    private ConnectionPresenterInt connectionPresenterInt;

    @Override
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        connectionPresenterInt = new ConnectionPresenterImpl(this);
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
    public void connectionBtnClicked(String ipAdress) {

        connectionPresenterInt = new ConnectionPresenterImpl(this);
        connectionPresenterInt.connectToServer(ipAdress);
    }

    @Override
    public void connectionInfo(final String setText) {
        new Thread()
        {
            public void run()
            {
                runOnUiThread(new Runnable()
                {
                    public void run()
                    {
                        ConnectionFragment.setConnectionInfo(setText);
                    }
                });
            }
        }.start();
    }

    @Override
    public void setConnectionButton(final boolean enabled) {
        new Thread()
        {
            public void run()
            {
                runOnUiThread(new Runnable()
                {
                    public void run()
                    {
                        // UI Update operations
                        ConnectionFragment.setButtonState(enabled);
                    }
                });
            }
        }.start();
    }

    @Override
    public void gameState(String reply){
        //Call changeFragment
    }
    @Override
    public void gameBtnClicked(String text){
        //Tell presenter to pick user guess
    }
    @Override
    public void changeFragment(Fragment newFragment) {
        FragmentTransaction transaction = this.getSupportFragmentManager().beginTransaction();
        transaction.replace(R.id.fragment_container, newFragment);
        transaction.addToBackStack(null);
        transaction.commit();
    }

    @Override
    public void onDestroy(){
        super.onDestroy();
    }


}
