package com.example.dialogue.logic;

import android.content.Context;
import android.widget.ListView;
import android.widget.Toast;

import com.example.dialogue.R;
import com.example.dialogue.Utils.AppController;
import com.example.dialogue.Utils.friendRequestAdapter;
import com.example.dialogue.network.IServerRequest;
import com.example.dialogue.network.IWeb;
import com.example.dialogue.ui.IView;
import com.example.dialogue.ui.friendRequest_Activity;

import org.json.JSONArray;

import java.net.URI;
import java.net.URISyntaxException;
import java.util.ArrayList;
import java.util.List;

import tech.gusavila92.websocketclient.WebSocketClient;

public class friendRequestLogic implements IWeb {

    private ListView lv;
    private Context adapterContext;
    private IServerRequest serverRequest;
    private ArrayList<String> friendRequestNames;
    private WebSocketClient webSocketClient;
    private IView L;
    private String user;

    public friendRequestLogic(ListView v, Context viewParent, IView L) {

        adapterContext = viewParent;
        lv = v;
        this.L = L;
        if(AppController.friendRequestList == null){
            friendRequestNames = new ArrayList<String>();
        }
        else{
            friendRequestNames = AppController.friendRequestList;
        }


    }

    @Override
    public void createWebSocketClient() {
        URI uri;
        try {
            // Connect to local host
            String ws = "ws://coms-309-sb-05.cs.iastate.edu:8080/friendRequest/";

            if (user == null) {
                user = AppController.getInstance().getUser();
            }
            System.out.println(user);

            ws = ws + user;
            uri = new URI(ws);
        }
        catch (URISyntaxException e) {
            e.printStackTrace();
            return;
        }

        webSocketClient = new WebSocketClient(uri) {

            @Override
            public void onOpen() {

            }

            @Override
            public void onTextReceived(String message) {
                // to do for recieving friend requests
                System.out.println(message + " here");
                System.out.println(!message.equals("now taking requests") && !AppController.friendRequestList.contains(message));
                for (int i = 0; i < friendRequestNames.size() ; i++){
                    System.out.println(friendRequestNames.get(i).toString() + "List");
                }
                if (!message.equals("now taking requests") && !friendRequestNames.contains(message)) {
                    System.out.println(message + " here2");

                    friendRequestNames.add(message);
                    System.out.println(friendRequestNames.size() + "SIze here");

                }
                lv.setAdapter(new friendRequestAdapter(adapterContext, R.layout.adapter_friendrequest, friendRequestNames, L, lv));
                AppController.friendRequestList = friendRequestNames;
                lv.deferNotifyDataSetChanged();
            }


            @Override
            public void onBinaryReceived(byte[] data) {

            }

            @Override
            public void onPingReceived(byte[] data) {

            }

            @Override
            public void onPongReceived(byte[] data) {

            }

            @Override
            public void onException(Exception e) {

            }

            @Override
            public void onCloseReceived() {

            }
        };


        webSocketClient.setConnectTimeout(10000);
        webSocketClient.setReadTimeout(60000);
        webSocketClient.enableAutomaticReconnection(5000);
        webSocketClient.connect();

    }

    @Override
    public WebSocketClient getWebSocketClient() {
        return webSocketClient;
    }
    public String sendRequest(String usertoRequest){
        webSocketClient.send(usertoRequest + " " + AppController.getInstance().getUser());
        L.toastText("Request Sent");
        return "Request Sent";
    }
    public ArrayList<String> getRequestList(){
        return friendRequestNames;
    }
    public void setRequestList(ArrayList<String> s) {
        friendRequestNames = s;
    }

    public void setUser(String s){
        user = s;
    }

}


