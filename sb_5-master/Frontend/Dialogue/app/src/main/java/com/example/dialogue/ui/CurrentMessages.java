package com.example.dialogue.ui;

import androidx.appcompat.app.AppCompatActivity;

import android.app.AlertDialog;
import android.content.Context;
import android.content.DialogInterface;
import android.os.Bundle;


import android.content.Intent;
import android.os.Handler;
import android.view.View;
import android.widget.AdapterView;
import android.widget.Button;
import android.widget.ListView;
import android.widget.Toast;

import com.example.dialogue.R;
import com.example.dialogue.Utils.AppController;
import com.example.dialogue.Utils.MessageListAdapter;
import com.example.dialogue.network.IServerRequest;
import com.example.dialogue.network.ServerRequest;
import com.example.dialogue.objects.MessageLog;

import java.util.ArrayList;
import java.util.Timer;

/**
 * @author Jacob Nett
 */
public class CurrentMessages extends AppCompatActivity implements IView{
    private Button friendList ;
    private ListView listView;
    private MessageListAdapter adapter;
    private ArrayList<MessageLog> messageLog;
    private Handler handler;
    private Runnable runnable;
    private int delay = 4*1000;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        listView = findViewById(R.id.messageLV);
        Button adminButton = (Button) findViewById(R.id.adminButton);
        adminButton.setVisibility(View.GONE);
        if(AppController.getInstance().getAdminlevel() > 0){
            adminButton.setVisibility(View.VISIBLE);
        }
        friendList = findViewById(R.id.toFriendsButton);
        AppController.getInstance().setIWeb();
        IServerRequest req = new ServerRequest();
        handler = new Handler();

        messageLog = AppController.getInstance().getArrayListData();
        adapter = new MessageListAdapter(this.getApplicationContext(), R.layout.adapter_view_layout, messageLog);
        adapter.notifyDataSetChanged();
        listView.setAdapter(adapter);

        listView.setOnItemClickListener(new AdapterView.OnItemClickListener() {
            @Override
            public void onItemClick(AdapterView<?> parent, View view, int position, long id) {
                AppController.getInstance().setViewLog((MessageLog) parent.getItemAtPosition(position));
                //recipientLog = messageLog;
                changeScreen(UserMessaging.class);
            }
        });

        friendList.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                changeScreen(userFirends_Activity.class);
            }
        });
        adminButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                changeScreen(AdminTools.class);
            }
        });

    }
    @Override
    protected void onResume() {
        //start handler as activity become visible

        handler.postDelayed( runnable = new Runnable() {
            public void run() {
                messageLog = AppController.getInstance().getArrayListData();
                adapter.notifyDataSetChanged();
                handler.postDelayed(runnable, delay);
            }
        }, delay);

        super.onResume();
    }

// If onPause() is not included the threads will double up when you
// reload the activity

    @Override
    protected void onPause() {
        handler.removeCallbacks(runnable); //stop handler when activity not visible
        super.onPause();
    }

    @Override
    public void onBackPressed() {
        new AlertDialog.Builder(this)
                .setMessage("Are you sure you want to exit?")
                .setCancelable(false)
                .setPositiveButton("Yes", new DialogInterface.OnClickListener() {
                    public void onClick(DialogInterface dialog, int id) {
                        AppController.getInstance().removeIWeb();
                        changeScreen(Login.class);
                    }
                })
                .setNegativeButton("No", null)
                .show();
    }

    @Override
    public void toastText(String s) {
        Toast.makeText(this, s, Toast.LENGTH_SHORT).show();
    }

    @Override
    public void changeScreen(Class c) {
        startActivity(new Intent(this.getApplicationContext(), c));
    }

    @Override
    public void setUser(String u) {

    }

    @Override
    public Context getContext() {
        return this.getApplicationContext();
    }

}
