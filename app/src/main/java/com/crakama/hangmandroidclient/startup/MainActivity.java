package com.crakama.hangmandroidclient.startup;

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

import com.crakama.hangmandroidclient.R;
import com.crakama.hangmandroidclient.commhandler.GamePresenterImpl;
import com.crakama.hangmandroidclient.commhandler.GamePresenterInt;
import com.crakama.hangmandroidclient.ui.DialogFragment;
import com.crakama.hangmandroidclient.ui.GameFragment;
import com.crakama.hangmandroidclient.ui.HomeFragment;
import com.crakama.hangmandroidclient.ui.InstructionsFragment;

public class MainActivity extends AppCompatActivity implements MainInterface,
        HomeFragment.OnItemClickedListener,GameFragment.OnGameFragListener,
InstructionsFragment.OnDialogListener,DialogFragment.OnWinDialogListener{
    private GamePresenterInt gamePresenterInt;

    //-------------------------------------------------------------------------
    // Default methods
    //-------------------------------------------------------------------------

    @Override
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        Toolbar toolbar = findViewById(R.id.toolbar);
        setSupportActionBar(toolbar);
        gamePresenterInt = new GamePresenterImpl(this);
        if(findViewById(R.id.fragment_container) != null){
            if(savedInstanceState != null){
                return;
            }
            HomeFragment homeFragment = new HomeFragment();
            homeFragment.setArguments(getIntent().getExtras());
            getSupportFragmentManager().beginTransaction().add(R.id.fragment_container, homeFragment).commit();
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

        gamePresenterInt = new GamePresenterImpl(this);
        gamePresenterInt.connectToServer(ipAdress);
    }

    @Override
    public void connectionInfo(final String setText) {
       handler.post(new Runnable() {
           @Override
           public void run() {
               HomeFragment.setConnectionInfo(setText);
           }
       });
    }


    @Override
    public void setConnectionButton(final boolean enabled) {
        handler.post(new Runnable() {
            @Override
            public void run() {
                HomeFragment.setButtonState(enabled);
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
        System.out.println("REPLY" +reply);
            DialogFragment dialogFragment = new DialogFragment();
            dialogFragment.setArguments(args);
            dialogFragment.show(getSupportFragmentManager(),"Status Dialog");
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
        gamePresenterInt.startAsyncTask(text);
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
        gamePresenterInt = new GamePresenterImpl(this);
        gamePresenterInt.startAsyncTask(text);

    }
}
