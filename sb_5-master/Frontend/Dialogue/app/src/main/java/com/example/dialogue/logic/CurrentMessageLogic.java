package com.example.dialogue.logic;

import android.content.Context;
import android.widget.ListView;

import com.example.dialogue.Utils.MessageListAdapter;
import com.example.dialogue.network.IServerRequest;
import com.example.dialogue.objects.MessageLog;
import com.example.dialogue.ui.IView;


import java.util.ArrayList;
/**
 * @author Jacob Nett
 */
public class CurrentMessageLogic{

    private IView L;
    private IServerRequest serverRequest;
    private ListView lv;
    private static MessageLog recipientLog;
    private static ArrayList<MessageLog> staticLogs;
    private Context c;
    private ListView v;
    private MessageListAdapter adapter;

    public CurrentMessageLogic(IView L, Context c, ListView v) {
        this.L = L;
        this.c = c;
        this.v = v;
    }

    public void buildList() {

    }

    public static void setAllLogs(ArrayList<MessageLog> s){
        staticLogs = s;
    }

}