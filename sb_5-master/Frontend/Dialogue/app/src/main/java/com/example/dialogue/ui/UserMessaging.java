package com.example.dialogue.ui;

import androidx.annotation.NonNull;
import androidx.appcompat.app.AppCompatActivity;

import android.content.Context;
import android.content.DialogInterface;
import android.content.Intent;
import android.os.Bundle;
import android.os.Handler;
import android.view.Menu;
import android.view.MenuItem;
import android.view.View;
import android.widget.Adapter;
import android.widget.AdapterView;
import android.widget.Button;
import android.widget.EditText;
import android.widget.ListView;
import android.widget.Toast;

import com.example.dialogue.R;
import com.example.dialogue.Utils.AppController;
import com.example.dialogue.Utils.LogListAdapter;
import com.example.dialogue.logic.CurrentMessageLogic;
import com.example.dialogue.logic.UserMessageLogic;
import com.example.dialogue.network.IServerRequest;
import com.example.dialogue.network.IWeb;
import com.example.dialogue.network.ServerRequest;
import com.example.dialogue.objects.MessageLog;
import android.app.AlertDialog;


import org.json.JSONException;
import org.json.JSONObject;

import java.util.ArrayList;
import java.util.Timer;
import java.util.TimerTask;

/**
 * @author Jacob Nett
 */
public class UserMessaging extends AppCompatActivity implements IView {
    private ListView lv;
    private EditText messageET;
    private Button sendButton;
    private LogListAdapter adapter;
    private MessageLog log;

    private Handler handler;
    private Runnable runnable;
    private int delay = 4*1000;
    @Override
    /**
     * Builds the screen for user to user messages
     */
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_user_messaging);
        lv = findViewById(R.id.logLV);
        messageET = findViewById(R.id.messageET);
        sendButton = findViewById(R.id.sendButton);

        handler = new Handler();
        ServerRequest rQue = new ServerRequest();
        final UserMessageLogic userMessageLogic = new UserMessageLogic(this, lv,AppController.getInstance().getViewLog().getRecipient(), rQue);
        log = AppController.getInstance().getViewLog();
        adapter = new LogListAdapter(this, R.layout.adapter_message_layout, log.getLog());
        lv.setAdapter(adapter);

        lv.setOnItemLongClickListener(new AdapterView.OnItemLongClickListener(){
            @Override
            public boolean onItemLongClick(AdapterView<?> parent, View view, final int position, long id) {

                final String  reportUrl = "http://coms-309-sb-05.cs.iastate.edu:8080/report/newReport";
                new AlertDialog.Builder(lv.getContext())
                        .setMessage("Are you sure you want to report this message?")
                        .setCancelable(false)
                        .setPositiveButton("Yes", new DialogInterface.OnClickListener() {
                            public void onClick(DialogInterface dialog, int id) {
                                // report message
                                JSONObject report = new JSONObject();
                                try{
                                    report.put("username", log.getLog().get(position).getSender());
                                    report.put("reportContent", log.getLog().get(position).getData());
//                                    System.out.println(log.getLog().get(position).getData());
                                } catch (JSONException e) {
                                    e.printStackTrace();
                                }

                                userMessageLogic.reportMessages(reportUrl, report);

                                changeScreen(UserMessaging.class);
                            }
                        })
                        .setNegativeButton("No", null)
                        .show();
                // report message when a report message function is created
                return false;
            }


        });

        sendButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                String message = messageET.getText().toString();
                userMessageLogic.sendMessage(message);
                messageET.setText(null);
                adapter.notifyDataSetChanged();
            }
        });

    }

    @Override
    public boolean onCreateOptionsMenu(Menu menu) {
        getMenuInflater().inflate(R.menu.menu, menu);
        return true;
    }

    @Override
    public boolean onOptionsItemSelected(@NonNull MenuItem item) {
        int id = item.getItemId();

        switch (id){
            case R.id.Delete:
                String reciepient = log.getRecipient();
                ArrayList<MessageLog> ml = AppController.getInstance().getArrayListData();
                for(int i = 0; i <ml.size(); i++){
                    if(ml.get(i).getRecipient().equals(reciepient)){
                        ml.remove(i);
                        break;
                    }
                }
                MessageLog clear = new MessageLog(reciepient);
                AppController.getInstance().setViewLog(clear);
                ml.add(clear);
                log.clearlog();
                AppController.getInstance().saveData(ml);
                adapter.notifyDataSetChanged();
                toastText("deleted");
                changeScreen(CurrentMessages.class);
        }
        return true;
    }

    @Override
    protected void onResume() {
        //start handler as activity become visible

        handler.postDelayed(runnable = new Runnable() {
            public void run() {
                log = AppController.getInstance().getViewLog();
                adapter.notifyDataSetChanged();
                handler.postDelayed(runnable, delay);
            }
        }, delay);

        super.onResume();
    }
    @Override
    protected void onPause() {
        handler.removeCallbacks(runnable); //stop handler when activity not visible
        super.onPause();
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


    @Override
    public void onBackPressed(){
        changeScreen(CurrentMessages.class);
    }
}