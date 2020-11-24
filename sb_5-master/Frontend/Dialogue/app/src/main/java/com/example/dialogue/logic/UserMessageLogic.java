package com.example.dialogue.logic;

import android.content.Context;
import android.view.View;
import android.widget.AdapterView;
import android.widget.EditText;
import android.widget.ListAdapter;
import android.widget.ListView;

import com.example.dialogue.R;
import com.example.dialogue.Utils.AppController;
import com.example.dialogue.Utils.LogListAdapter;
import com.example.dialogue.network.IServerRequest;
import com.example.dialogue.network.IWeb;
import com.example.dialogue.network.ServerRequest;
import com.example.dialogue.objects.MessageLog;
import com.example.dialogue.objects.Messages;
import com.example.dialogue.ui.CurrentMessages;
import com.example.dialogue.ui.IView;
import com.example.dialogue.ui.Login;

import org.json.JSONArray;
import org.json.JSONObject;

import java.util.ArrayList;
import java.util.List;
import java.util.Timer;
import java.util.TimerTask;

import tech.gusavila92.websocketclient.WebSocketClient;
/**
 * @author Jacob Nett
 */
public class UserMessageLogic implements IVolleyListener{
    private IView L;
    private IWeb iWeb;
    private String Recipient;
    private Context c;
    private ListView v;
    private IServerRequest serverRequest;


    public UserMessageLogic(IView L, ListView v, String recipient,ServerRequest SR){
        this.L = L;
        this.iWeb = AppController.getInstance().getIWeb();
        this.c = L.getContext();
        this.v = v;
        Recipient = recipient;
        serverRequest = SR;
        serverRequest.addVolleyListener(this);
    }

    /**
     * sends message to websocket and logs the message into the app prefs
     * @param toSend
     */
    public void sendMessage(String toSend){
        String encrypSend = "";
        try {
            encrypSend = AppController.getInstance().encrypt(toSend, AppController.getInstance().getUser());
        }
        catch (Exception e){
            e.printStackTrace();
        }
        Messages messages = new Messages(toSend, AppController.getInstance().getUser());
        String message = "@" + Recipient + " " + encrypSend;
        WebSocketClient webSocketClient = iWeb.getWebSocketClient();
        webSocketClient.send(message);
        AppController.getInstance().addToViewLog(messages);
        saveLogs(messages);

    }

    /**
     * sorts how the the message get sent to message save logs
     * @param messages
     */
    private void saveLogs(Messages messages) {
        ArrayList<MessageLog> messageLog = AppController.getInstance().pullData();
        boolean notInList = true;
        int size = messageLog.size();
        if (size == 0){
            MessageLog ml = new MessageLog(Recipient);
            messageLog.add(ml);
            notInList = false;
        }
        for(int i = 0; i < size; i++){
            if(messageLog.get(i).getRecipient().equals(Recipient)){
                messageLog.get(i).addMessage(messages);
                notInList = false;
            }
        }
        if(notInList){
            MessageLog ml = new MessageLog(Recipient);
            messageLog.add(ml);
        }
        AppController.getInstance().saveData(messageLog);
    }

    /**
     * takes the logs and builds it into to the list
     */
    public void buildList(){

    }

    public void reportMessages(String url, JSONObject report){
            serverRequest.reportMessages(url, report);
    }

    @Override
    public void onSuccess(String s) {
        L.toastText(s);
        System.out.println(s);
    }

    @Override
    public void onSuccess(JSONArray s) {

    }

    @Override
    public void onError(String s) {

    }
//    static void setLog(MessageLog set){
//        log = set;
//    }
}
