package com.example.dialogue.network;

import com.example.dialogue.Utils.AppController;
import com.example.dialogue.logic.CurrentMessageLogic;
import com.example.dialogue.logic.UserMessageLogic;
import com.example.dialogue.objects.MessageLog;
import com.example.dialogue.objects.Messages;
import com.example.dialogue.ui.Login;

import java.net.URI;
import java.net.URISyntaxException;
import java.util.ArrayList;
import tech.gusavila92.websocketclient.WebSocketClient;
/**
 * @author Christian Williams
 */
public class createWeb implements IWeb {
    private WebSocketClient webSocketClient;
    @Override
    public void createWebSocketClient() {
        URI uri;
        try {
            // Connect to local host
            String ws = "ws://coms-309-sb-05.cs.iastate.edu:8080/message/";
            String user = AppController.getInstance().getUser();
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
            /**
             * method for receiving messages from backend
             */
            public void onTextReceived(String s) {
                if(!s.equals("@joined") && !s.equals("joined")) {
                    ArrayList<MessageLog> messageLogs = AppController.getInstance().pullData();
                    // String @user1000 hello
                    //System.out.println(s);
                    String sender = s.split(" ")[0];
                    System.out.println(sender);
                    if (!sender.contains("@")) {
                        sender = "@" + sender;
                        System.out.println(sender);
                    }
                    sender = sender.substring(1);
                    System.out.println(sender);
                    String messageString = "";
                    try {
                        messageString = AppController.getInstance().decrypt(s.substring(sender.length()+2), sender);
                    } catch (Exception e) {
                        e.printStackTrace();
                    }

                    System.out.println("message" + messageString);
                    boolean notInList = true;
                    int size = messageLogs.size();
                    if (size == 0){
                        MessageLog newLog = new MessageLog((sender));
                        newLog.addMessage(new Messages(messageString, sender));
                        messageLogs.add(newLog);
                        CurrentMessageLogic.setAllLogs(messageLogs);
                        notInList = false;
                    }
                    for (int i = 0; i < size; i++) {
                        System.out.println("Recipient " + (messageLogs.get(i).getRecipient().equals(sender)));
                        if (messageLogs.get(i).getRecipient().equals(sender)) {

                            Messages message = new Messages(messageString, sender);
                            messageLogs.get(i).addMessage(message);
                            notInList = false;

                        }
                    }
                     if (notInList) {
                        MessageLog newLog = new MessageLog((sender));
                        newLog.addMessage(new Messages(messageString, sender));
                        messageLogs.add(newLog);
                        CurrentMessageLogic.setAllLogs(messageLogs);
                     }
                    if(AppController.getInstance().getViewLog() != null){
                        if(AppController.getInstance().getViewLog().getRecipient().equals(sender)){
                            AppController.getInstance().addToViewLog(new Messages(messageString, sender));
                        }
                    }
                        AppController.getInstance().saveData(messageLogs);
//                        if(AppController.getInstance().getViewLog() != null){
//                            MessageLog log = AppController.getInstance().getViewLog();
//                            if(log.getRecipient().equals(sender)){
//                                log.addMessage(new Messages(messageString, sender));
//                                AppController.getInstance().setViewLog(log);
//                            }
//                        }
                }
                // build new message
                //first array is recipient name



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
                e.printStackTrace();
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

}
