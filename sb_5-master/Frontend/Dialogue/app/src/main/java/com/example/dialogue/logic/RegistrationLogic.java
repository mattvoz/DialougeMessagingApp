package com.example.dialogue.logic;

import android.text.TextUtils;
import android.widget.EditText;

import com.example.dialogue.network.IServerRequest;
import com.example.dialogue.ui.IView;
import com.example.dialogue.ui.CurrentMessages;
import com.example.dialogue.ui.Login;
import com.google.gson.JsonObject;

import org.json.JSONArray;
import org.json.JSONException;
import org.json.JSONObject;

import java.util.Random;
/**
 * @author Jacob Nett
 */
public class RegistrationLogic implements IVolleyListener {
    private IView SU;
    private IServerRequest serverRequest;
    public RegistrationLogic(IView SU, IServerRequest SR){
        this.SU = SU;
        serverRequest = SR;
        serverRequest.addVolleyListener(this);
    }

    /**
     * send user registration to the to the server request class
     * @param username
     * @param password
     * @param rePassword
     */
    public void Register(String username, String password, String rePassword) {

        //showProgressDialog();
        String url = "http://coms-309-sb-05.cs.iastate.edu:8080/addUser";
        //String url = "https://c69ea811-b5f6-408e-8689-138cc1756256.mock.pstmn.io/addUser";
        JSONObject object = new JSONObject();
        try {
            //input your API parameters
            object.put("username", username);
            object.put("password", password);
        } catch (JSONException e) {
            e.printStackTrace();
        }
        serverRequest.RegisterToServer(url, object);


    }

    /**
     * logic responce on server success
     * @param response
     */
    @Override
    public void onSuccess(String response) {
//        JSONObject j = response;
        if(response.equals("Saved")){
            String s = response;
            SU.toastText("You have successfully signed up!");
            SU.changeScreen(Login.class);
        }
        else {
            //username.setError("Username is Unavailable");
            SU.toastText("username unavailable");
        }
    }

    @Override
    /**
     * unused
     */
    public void onSuccess(JSONArray s) {

    }

    /**
     * logic response on server failure
     * @param s
     */
    @Override
    public void onError(String s) {
        SU.toastText("username unavailable");
    }
}
