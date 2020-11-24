package com.example.dialogue.Utils;

import android.content.Context;
import android.content.Intent;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ArrayAdapter;
import android.widget.ImageButton;
import android.widget.ImageView;
import android.widget.ListView;
import android.widget.TextView;
import android.widget.Toast;

import androidx.annotation.NonNull;

import com.example.dialogue.R;
import com.example.dialogue.logic.CurrentMessageLogic;
import com.example.dialogue.logic.friendRequestLogic;
import com.example.dialogue.logic.friendsList_Logic;
import com.example.dialogue.network.ServerRequest;
import com.example.dialogue.objects.MessageLog;
import com.example.dialogue.objects.Messages;
import com.example.dialogue.ui.FriendsList_Activity;
import com.example.dialogue.ui.CurrentMessages;
import com.example.dialogue.ui.IView;
import com.example.dialogue.ui.Login;
import com.example.dialogue.ui.UserMessaging;
import com.example.dialogue.ui.userFirends_Activity;

import java.util.ArrayList;
import java.util.List;

/**
 * Creates an adapter to use in a list
 *
 * @author Christian Williams
 */

public class friendsListAdapter extends ArrayAdapter<String> {
    private int layout;
    private List<String> list;
    private IView L;
    private ListView List_View;

    /**
     * Constructor for creating an adapter for friendslist ListViews
     * @param context :Context of the page containing the ListView
     * @param resource :Id of the layout to be used
     * @param objects :List of items to be shown
     * @param L : IView
     * @param List_View : ListView to use
     */

    public friendsListAdapter(@NonNull Context context, int resource, @NonNull List<String> objects, IView L, ListView List_View) {
        super(context, resource, objects);
        this.L = L;
        layout = resource;
        list = objects;
        this.List_View = List_View;

    }

    /**
     * Gets the view for each object in the list
     * @param position :position of object in the list
     * @param view : View that is being used for each object in the list
     * @param parent : parent of the view that wou are getting
     * @return
     */
    @Override
    public View getView(final int position, View view, ViewGroup parent){
        dataHold main = null;
        if (view == null){
            LayoutInflater inflater = LayoutInflater.from(getContext());
            view = inflater.inflate(layout, parent, false);
            dataHold holder= new dataHold();
            holder.thumbnail = (ImageView) view.findViewById(R.id.list_thumnail);
            holder.username = (TextView)  view.findViewById(R.id.username);
            holder.List_addbutton = (ImageButton)  view.findViewById(R.id.list_add);
            holder.List_messagebutton = (ImageButton)  view.findViewById(R.id.list_message);

            view.setTag(holder);
        }

        main = (dataHold) view.getTag();
        final String friendAdded = list.get(position);
        final String FriendAddURL = "http://coms-309-sb-05.cs.iastate.edu:8080/" + AppController.getInstance().getUser() + "/addFriendByName/" + list.get(position);
        final View finalView1 = view;
        main.List_addbutton.setOnClickListener(new View.OnClickListener(){
            @Override
            public void onClick(View v) {
                //Toast.makeText(getContext(),friendAdded ,Toast.LENGTH_LONG).show();

                friendRequestLogic friendRequestLogic = new friendRequestLogic(List_View, friendsListAdapter.super.getContext(), L);
                friendRequestLogic.createWebSocketClient();
                System.out.println(friendAdded +" Adding friend");
                friendRequestLogic.sendRequest(friendAdded);





//                final ServerRequest rQue = new ServerRequest();
//                friendsList_Logic friendsList = new friendsList_Logic(L, rQue, FriendAddURL,List_View, friendsListAdapter.super.getContext(),"friendName");
//                friendsList.addFriend(FriendAddURL);
            }
        });

        final dataHold finalMain = main;
        main.List_messagebutton.setOnClickListener(new View.OnClickListener(){
            @Override
            public void onClick(View v) {
                //Toast.makeText(getContext(),"Create new message chat" ,Toast.LENGTH_LONG).show();
                boolean chatStarted = false;
                ArrayList<MessageLog> temp;
                //System.out.println(CurrentMessageLogic.getUserLog(finalMain.username.getText().toString()));
                if(!AppController.getInstance().pullData().equals(null)) {
                    temp = AppController.getInstance().pullData();
                }
                else{
                    temp = new ArrayList<>();
                }
                for(int i = 0; i < temp.size(); i ++){
                    if(temp.get(i).getRecipient().equals(finalMain.username.getText().toString())){
                        chatStarted = true;
                    }
                }
                if (chatStarted){
                    Toast.makeText(getContext(),"Chat already established with user" ,Toast.LENGTH_LONG).show();
                }
                else{
                    Toast.makeText(getContext(),"Create new message chat" ,Toast.LENGTH_LONG).show();
                    MessageLog newMessage = new MessageLog(finalMain.username.getText().toString());
                    temp.add(newMessage);
                    AppController.getInstance().saveData(temp);
                }


            }
        });
//
        main.username.setText(list.get(position));
        return view;
    }

    /**
     * Class to hold data of each object within the list
     */
    public class dataHold {
        ImageView thumbnail;
        TextView username;
        ImageButton List_addbutton;
        ImageButton List_messagebutton;


    }
}
