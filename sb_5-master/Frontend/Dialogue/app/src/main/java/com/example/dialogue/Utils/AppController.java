package com.example.dialogue.Utils;

import android.app.Application;
import android.content.Context;
import android.content.SharedPreferences;
import android.os.Message;
import android.util.Base64;
import android.view.MotionEvent;
import android.view.View;

import com.android.volley.Request;
import com.android.volley.RequestQueue;
import com.android.volley.toolbox.Volley;
import com.example.dialogue.network.IWeb;
import com.example.dialogue.network.createWeb;
import com.example.dialogue.objects.MessageLog;
import com.example.dialogue.objects.Messages;
import com.example.dialogue.ui.Login;
import com.google.gson.Gson;
import com.google.gson.internal.LinkedTreeMap;
import com.google.gson.reflect.TypeToken;

import java.lang.reflect.Type;
import java.security.MessageDigest;
import java.util.ArrayList;

import javax.crypto.Cipher;
import javax.crypto.spec.SecretKeySpec;

import tech.gusavila92.websocketclient.WebSocketClient;
/**
 * @author Jacob Nett
 */
public class AppController extends Application{
    public static final String TAG = AppController.class.getSimpleName();

    public static ArrayList<String> friendRequestList = new ArrayList<String>();
    private RequestQueue mRequestQueue;
    private static AppController mInstance;
    private SharedPreferences sharedPreferences;

    private String user;
    private IWeb iWeb;
    private MessageLog viewLog;
    private ArrayList<MessageLog> userArrayList;
    private int adminlevel;

    @Override
    public void onCreate(){
        super.onCreate();
        mInstance=this;
    }


    public static synchronized AppController getInstance(){
        return mInstance;
    }

    /**
     * Set the shared preference for the app
     */
    public void setAdminlevel(int i){
        adminlevel = i;
    }
    public int getAdminlevel(){
        return adminlevel;
    }
    public void setSharedPreferences(){
        sharedPreferences =  this.getSharedPreferences(user, Context.MODE_PRIVATE);
    }

    /**
     * Save the log that is used in list view
     * @param set messagelog
     */
    public void setViewLog(MessageLog set){
        viewLog = set;
    }

    /**
     * Returns the message log this is used in list views
     * @return Message Log
     */
    public MessageLog getViewLog(){
        return viewLog;
    }

    public void addToViewLog(Messages message) {
        viewLog.addMessage(message);
    }


    /**
     * @return the requestQueue used by the app
     */
    public RequestQueue getRequestQueue() {

        if(mRequestQueue == null){
            mRequestQueue = Volley.newRequestQueue(getApplicationContext());
        }
        return mRequestQueue;
    }

    /**
     * sets the current app user
     * @param user username to be set
     */
    public void setUser(String user){

        this.user = user;

    }

    /**
     * Gets the current app user
     * @return Username string
     */
    public String getUser(){
        return user;
    }

    /**
     * removes the current iweb connector to the websocket
     */
    public void removeIWeb(){
        iWeb = null;
    }

    /**
     * sets up the websocket
     */
    public void setIWeb(){
        if(iWeb == null) {
            iWeb = new createWeb();
            iWeb.createWebSocketClient();
        }
    }

    /**
     * method tor retrieve apps iweb
     * @return Iweb
     */
    public IWeb getIWeb(){
        return iWeb;
    }

    /**
     * add to the apps request queue
     * @param req
     * @param <T>
     */
    public <T> void addToRequestQueue(Request<T> req){
        getRequestQueue().add(req);
    }

    /**
     * cancels a request in the queue
     * @param tag
     */
    public void cancelPendingRequests(Object tag){
        if(mRequestQueue != null){
            mRequestQueue.cancelAll(tag);
        }
    }

    /**
     * save provided arraylist to the sharedPreferences
     * @param toSave
     */
    public void saveData(ArrayList toSave){
        setArrayData(toSave);
        SharedPreferences.Editor editor= sharedPreferences.edit();
        Gson gson = new Gson();
        String json = gson.toJson(toSave);
        editor.putString(user, json);
        setArrayData(toSave);
        editor.apply();
    }
    /**
     * pulls a stored data log and rebuilds it as an array list of object
     * @return
     */
    public ArrayList pullData(){
        Gson gson = new Gson();
        String json = sharedPreferences.getString(user, null);
        Type type = new TypeToken<ArrayList<MessageLog>>() {}.getType();
        ArrayList retList = gson.fromJson(json, type);
        if(retList == null){
            return new ArrayList<>();
        }

        for(int i = 0; i < retList.size(); i++){
            ArrayList<Messages> addBack = new ArrayList<>();
            MessageLog ml = (MessageLog) retList.get(i);
            int iterate = ml.logSize();
            for(int j = 0; j <  iterate; j++){
                Object object = ml.removeMessage(0);
                LinkedTreeMap ltm = (LinkedTreeMap) object;
                String sender = ltm.get("sender").toString();
                String date = ltm.get("date").toString();
                String data = ltm.get("data").toString();
                Messages newMessage = new Messages(data, date, sender);
                addBack.add(newMessage);
            }
            ml.AddAllMessages(addBack);
        }


        return retList;
    }

    /**
     * updates the users conversation logs
     * @param toSet
     */
    public void setArrayData(ArrayList toSet){
        userArrayList = toSet;
    }

    /**
     * pulls users conversation logs without pull from shared prefs
     * @return
     */
    public ArrayList<MessageLog> getArrayListData(){
        return  userArrayList;
    }

    /**
     * set the initial conversation of startup
     */
    public void initialArrayData() {
        userArrayList = pullData();
    }
    public String encrypt(String data) throws Exception{
        SecretKeySpec key = generateKey();
        Cipher c = Cipher.getInstance("AES");
        c.init(Cipher.ENCRYPT_MODE, key);
        byte[] encVal = c.doFinal(data.getBytes());
        String encryptedValue = Base64.encodeToString(encVal, Base64.DEFAULT);
        return  encryptedValue;
    }
    public String decrypt(String data) throws Exception {
        SecretKeySpec key = generateKey();
        Cipher c = Cipher.getInstance("AES");
        c.init(Cipher.DECRYPT_MODE, key);
        byte[] decodedValue = c.doFinal(Base64.decode(data, Base64.DEFAULT));
        return new String(decodedValue);
    }
    private SecretKeySpec generateKey() throws  Exception{
        final MessageDigest digest = MessageDigest.getInstance("SHA-256");
        byte[] bytes = "password".getBytes("UTF-8");
        digest.update(bytes, 0, bytes.length);
        SecretKeySpec key = new SecretKeySpec( digest.digest(), "AES");
        return key;
    }

    public String encrypt(String data, String password) throws Exception{
        SecretKeySpec key = generateKey(password);
        Cipher c = Cipher.getInstance("AES");
        c.init(Cipher.ENCRYPT_MODE, key);
        byte[] encVal = c.doFinal(data.getBytes());
        String encryptedValue = Base64.encodeToString(encVal, Base64.DEFAULT);
        return  encryptedValue;
    }
    public String decrypt(String data , String password) throws Exception {
        SecretKeySpec key = generateKey(password);
        Cipher c = Cipher.getInstance("AES");
        c.init(Cipher.DECRYPT_MODE, key);
        byte[] decodedValue = c.doFinal(Base64.decode(data, Base64.DEFAULT));
        return new String(decodedValue);
    }
    private SecretKeySpec generateKey(String password) throws  Exception{
        final MessageDigest digest = MessageDigest.getInstance("SHA-256");
        byte[] bytes = password.getBytes("UTF-8");
        digest.update(bytes, 0, bytes.length);
        SecretKeySpec key = new SecretKeySpec( digest.digest(), "AES");
        return key;
    }


}
