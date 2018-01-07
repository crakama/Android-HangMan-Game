package com.crakama.hangmandroidclient;

import android.os.Bundle;
import android.os.Handler;
import android.os.Message;
import android.support.v4.app.Fragment;
import android.support.v4.app.FragmentTransaction;
import android.support.v7.app.AppCompatActivity;
import android.support.v7.widget.Toolbar;
import android.view.Menu;
import android.view.MenuInflater;
import android.view.MenuItem;

public class MainActivity extends AppCompatActivity implements MainInterface,
        ConnectionFragment.OnItemClickedListener,GameFragment.OnGameFragListener,
InstructionsFragment.OnDialogListener,WinFragment.OnWinDialogListener{
    private ConnectionPresenterInt connectionPresenterInt;

    //-------------------------------------------------------------------------
    // Default methods
    //-------------------------------------------------------------------------

    @Override
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        Toolbar toolbar = (Toolbar) findViewById(R.id.toolbar);
        setSupportActionBar(toolbar);
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

    @Override
    public boolean onCreateOptionsMenu(Menu menu) {
        MenuInflater menuInflater = getMenuInflater();
        menuInflater.inflate(R.menu.menu,menu);
        return true;
    }

    @Override
    public boolean onOptionsItemSelected(MenuItem item) {
        if(item.getItemId() == R.id.action_exit){
            finish();
            System.exit(0);
        }
        return super.onOptionsItemSelected(item);
    }

    @Override
    public void onDestroy(){
        super.onDestroy();
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
       handler.post(new Runnable() {
           @Override
           public void run() {
               ConnectionFragment.setConnectionInfo(setText);
           }
       });
    }


    @Override
    public void setConnectionButton(final boolean enabled) {
        handler.post(new Runnable() {
            @Override
            public void run() {
                ConnectionFragment.setButtonState(enabled);
            }
        });
    }

    //-------------------------------------------------------------------------
    // Game methods
    //-------------------------------------------------------------------------

    @Override
    public void startGameDig(final String reply){
        handler.post(new Runnable() {
            @Override
            public void run() {
                InstructionsFragment instructionsFragment = new InstructionsFragment();
                Bundle args = new Bundle();
                args.putString("reply", reply);
                instructionsFragment.setArguments(args);
                instructionsFragment.show(getSupportFragmentManager(),"Instructions Dialog");
            }
        });
    }

    @Override
    public void gameState(final String reply,Bundle args) {
            WinFragment winFragment = new WinFragment();
            winFragment.setArguments(args);
            winFragment.show(getSupportFragmentManager(),"Status Dialog");
    }

    @Override
    public void gameInfo(Message replyinfo) {
        handler.sendMessage(replyinfo);
    }

    Handler handler = new Handler(){
        @Override
        public void handleMessage(Message message){
            GameFragment.setGameInfo(message.obj.toString());
        }
    };
    @Override
    public void gameBtnClicked(String text){
        connectionPresenterInt.msgToServer(text);
    }
    @Override
    public void changeFragment(Fragment newFragment) {
        FragmentTransaction transaction = this.getSupportFragmentManager().beginTransaction();
        transaction.replace(R.id.fragment_container, newFragment);
        transaction.addToBackStack(null);
        transaction.commit();
    }

    @Override
    public void btnOKClicked(String text) {
        //TODO: Send instructions to server
        connectionPresenterInt = new ConnectionPresenterImpl(this);
        connectionPresenterInt.msgToServer(text);

    }
}
