package com.example.dialogue.logic;

import android.content.Context;
import android.widget.ArrayAdapter;
import android.widget.ListView;

import com.example.dialogue.R;
import com.example.dialogue.Utils.AppController;
import com.example.dialogue.Utils.reportAdapter;
import com.example.dialogue.network.IServerRequest;
import com.example.dialogue.network.ServerRequest;
import com.example.dialogue.objects.Report;
import com.example.dialogue.ui.IView;

import org.json.JSONArray;
import org.json.JSONException;
import org.json.JSONObject;

import java.util.ArrayList;

public class AdminToolsLogic implements IVolleyListener{
    private IView L;
    private IServerRequest serverRequest;
    private ListView lv;
    private Context c;
    private ArrayList<Report> arrayList;
    boolean pass;
    private String user;


    public AdminToolsLogic(IView L, IServerRequest SR, ListView lv){
        this.L = L;
        serverRequest = SR;
        serverRequest.addVolleyListener(this);
        this.c = AppController.getInstance();
        this.lv = lv;
    }
    public Boolean getList(){
        pass = false;
        String url =  "http://coms-309-sb-05.cs.iastate.edu:8080/report/" + AppController.getInstance().getUser() + "/all";
        serverRequest.getReport(url);
        return pass;
    }
    public void setUser(String user){
        this.user = user;
    }
    @Override
    public void onSuccess(String s) {
        pass = true;
    }

    @Override
    public void onSuccess(JSONArray s) {
        pass = true;
        arrayList = new ArrayList<>();
        int id = 0;
        Report toAdd = null;
        for(int i = 0; i < s.length(); i++){

            try {
               JSONObject jsonObject = (JSONObject) s.get(i);
               id = jsonObject.getInt("modId");
               String username = jsonObject.getString("username");
               String content = jsonObject.getString("reportContent");

               toAdd = new Report(id, username , content);
            } catch (JSONException e) {
                e.printStackTrace();
            }
            arrayList.add(toAdd);
        }
        reportAdapter adapter = new reportAdapter(c,  R.layout.adapter_report_layout, arrayList);
        lv.setAdapter(adapter);
    }

    @Override
    public void onError(String s) {

    }

    public boolean deleteReport(String url) {
        pass = false;
        serverRequest.deleteReport(url);

        return pass;
    }
    public void issueInfraction(String url) {
        serverRequest.issueInfraction(url);
    }
}
