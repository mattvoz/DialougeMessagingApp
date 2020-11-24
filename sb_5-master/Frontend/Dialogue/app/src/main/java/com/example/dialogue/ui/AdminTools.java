package com.example.dialogue.ui;

import android.app.AlertDialog;
import android.content.Context;
import android.content.DialogInterface;
import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.AdapterView;
import android.widget.Button;
import android.widget.ImageButton;
import android.widget.ListView;
import android.widget.Toast;

import androidx.appcompat.app.AppCompatActivity;

import com.example.dialogue.R;
import com.example.dialogue.Utils.AppController;
import com.example.dialogue.logic.AdminToolsLogic;

import com.example.dialogue.logic.CurrentMessageLogic;
import com.example.dialogue.network.ServerRequest;
import com.example.dialogue.objects.Report;

import org.json.JSONException;
import org.json.JSONObject;

public class AdminTools extends AppCompatActivity implements IView{
    ListView listView;
    ImageButton back;

    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_admin_tools);
        listView = (ListView) findViewById(R.id.adminListView);
        back = (ImageButton) findViewById(R.id.adminBackButton);
        Button refresh = (Button) findViewById(R.id.AdminRefresh);
        ServerRequest serverRequest = new ServerRequest();
        final AdminToolsLogic adminToolsLogic = new AdminToolsLogic(this , serverRequest, listView);
        refresh.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                adminToolsLogic.getList();
            }
        });
        back.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                changeScreen(CurrentMessages.class);
            }
        });


        adminToolsLogic.getList();
        listView.setOnItemClickListener(new AdapterView.OnItemClickListener() {
            @Override
            public void onItemClick(AdapterView<?> parent, View view, int position, long id) {
                final Report report = (Report) listView.getAdapter().getItem(position);
                new AlertDialog.Builder(listView.getContext())
                        .setMessage("Would you like to issue an infraction to this user?")
                        .setCancelable(false)
                        .setPositiveButton("Yes", new DialogInterface.OnClickListener() {
                            public void onClick(DialogInterface dialog, int id) {

                                String add = report.getUser();
                                adminToolsLogic.issueInfraction("http://coms-309-sb-05.cs.iastate.edu:8080/report/infraction/" + add +"/" + report.getId());
                            }
                        })
                        .setNegativeButton("No", null)
                        .show();
            }
        });
        listView.setOnItemLongClickListener(new AdapterView.OnItemLongClickListener(){
            @Override
            public boolean onItemLongClick(AdapterView<?> parent, View view, final int position, long id) {
                final Report report = (Report) listView.getAdapter().getItem(position);
                new AlertDialog.Builder(listView.getContext())
                        .setMessage("Would you like to delete this user report?")
                        .setCancelable(false)
                        .setPositiveButton("Yes", new DialogInterface.OnClickListener() {
                            public void onClick(DialogInterface dialog, int id) {
                                System.out.println(report.getId());
                                adminToolsLogic.deleteReport("http://coms-309-sb-05.cs.iastate.edu:8080/report/removeReport/"+ report.getId());
                            }
                        })
                        .setNegativeButton("No", null)
                        .show();
                // report message when a report message function is created
                return true;
            }

        });
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
        return null;
    }
}
