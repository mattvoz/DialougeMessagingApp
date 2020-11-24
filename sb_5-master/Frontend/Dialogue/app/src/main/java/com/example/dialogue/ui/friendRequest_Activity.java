package com.example.dialogue.ui;

import android.content.Context;
import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.ImageButton;
import android.widget.ListView;

import androidx.appcompat.app.AppCompatActivity;

import com.example.dialogue.R;
import com.example.dialogue.Utils.AppController;
import com.example.dialogue.Utils.friendRequestAdapter;
import com.example.dialogue.logic.friendRequestLogic;

import java.lang.reflect.Array;
import java.util.ArrayList;

public class friendRequest_Activity extends AppCompatActivity implements IView{

    private ListView lv;
    private ImageButton backButton;
    private ArrayList<String> requestList;
    private friendRequestLogic friendRequestLogic;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_friendrequest);

        backButton = findViewById(R.id.backButton);
        backButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                changeScreen(userFirends_Activity.class);
            }
        });

        lv = findViewById(R.id.friendRequestList);
            requestList = AppController.friendRequestList;
        friendRequestLogic = new friendRequestLogic(lv, this, this);
        friendRequestLogic.createWebSocketClient();
        lv.setAdapter(new friendRequestAdapter(this, R.layout.adapter_friendrequest, requestList, this, lv));
        requestList = friendRequestLogic.getRequestList();


    }

    @Override
    public void toastText(String s) {
    }

    @Override
    public void changeScreen(Class c) {
        startActivity(new Intent(this.getApplicationContext(), c));
    }

    /**
     * not used
     * @param u
     */
    @Override
    public void setUser(String u) {

    }

    @Override
    public Context getContext() {
        return friendRequest_Activity.this;
    }
    public void setRequestList( ArrayList<String> k){
        requestList = k;
    }


    public ArrayList<String> getRequestList(){
        return requestList;
    }

    public friendRequestLogic getfriendRequestLogic(){
        return friendRequestLogic;
    }


}
