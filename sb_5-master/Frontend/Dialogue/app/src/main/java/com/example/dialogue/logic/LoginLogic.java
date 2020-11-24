package com.example.dialogue.logic;

import android.text.TextUtils;

import android.widget.EditText;
import android.widget.Toast;


import com.example.dialogue.Utils.AppController;
import com.example.dialogue.network.IServerRequest;
import com.example.dialogue.objects.MessageLog;
import com.example.dialogue.ui.IView;
import com.example.dialogue.ui.CurrentMessages;
import com.google.gson.JsonObject;

import org.json.JSONArray;
import org.json.JSONException;
import org.json.JSONObject;

import java.util.ArrayList;

/**
 * @author Jacob Nett
 */
public class LoginLogic implements IVolleyListener{
    private IView L;
    private IServerRequest serverRequest;
    private int failedAttempts;
    private boolean resetMessages = false;


    public LoginLogic(IView L, IServerRequest SR){
        this.L = L;
        serverRequest = SR;
        serverRequest.addVolleyListener(this);
        failedAttempts = 0;
    }

    /**
     * Send users details to the server request class
     * @param name
     * @param pass
     */
    public void Login(String name, String pass){
        String url = "http://coms-309-sb-05.cs.iastate.edu:8080/user/";
        //String url = "https://7ad42a00-aebc-42d9-b8ed-205aa61c5018.mock.pstmn.io/user/";
        url = url + name + "/" + pass;
        serverRequest.LoginToServer(url);
    }

    public int mockingTestMethod(int i){
        i = i*4 + 3;
        if (i > 6){
            L.toastText("hello");
        }
        return i;
    }
    @Override
    /**
     * method for when backend sends as success
     */
    public void onSuccess(String response) {
        //starting the profile activity
        if(response != null) {
            int i = Integer.parseInt(response);
            AppController.getInstance().setAdminlevel(i);
        }
        if (response == null){
            failedAttempts ++;
            System.out.println(failedAttempts);
            int attemptsLeft = 5-failedAttempts;
            if (attemptsLeft <= 0){
                resetMessages = true;
            }
        }
        else {
            AppController.getInstance().setSharedPreferences();
            AppController.getInstance().initialArrayData();
            if(resetMessages){
                AppController.getInstance().saveData(new ArrayList<MessageLog>());
            }
            L.changeScreen(CurrentMessages.class);
            resetMessages = false;
            failedAttempts = 0;
        }
    }

    /**
     * unused
     * @param s
     */
    @Override
    public void onSuccess(JSONArray s) {

    }

    /**
     * unused
     * @param errorMessage
     */
    @Override
    public void onError(String errorMessage) {
    }
}
