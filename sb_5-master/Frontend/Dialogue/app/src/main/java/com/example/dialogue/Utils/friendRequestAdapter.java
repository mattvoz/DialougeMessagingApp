package com.example.dialogue.Utils;

import android.content.Context;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ArrayAdapter;
import android.widget.Button;
import android.widget.ImageButton;
import android.widget.ImageView;
import android.widget.ListView;
import android.widget.TextView;
import android.widget.Toast;

import androidx.annotation.NonNull;

import com.example.dialogue.R;
import com.example.dialogue.logic.friendsList_Logic;
import com.example.dialogue.network.ServerRequest;
import com.example.dialogue.objects.MessageLog;
import com.example.dialogue.ui.IView;

import java.lang.reflect.Array;
import java.util.ArrayList;
import java.util.List;

public class friendRequestAdapter extends ArrayAdapter<String> {

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

    public friendRequestAdapter(@NonNull Context context, int resource, @NonNull List<String> objects, IView L, ListView List_View) {
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
        friendRequestAdapter.dataHold main = null;
        if (view == null){
            LayoutInflater inflater = LayoutInflater.from(getContext());
            view = inflater.inflate(layout, parent, false);
            friendRequestAdapter.dataHold holder= new friendRequestAdapter.dataHold();
            holder.thumbnail = (ImageView) view.findViewById(R.id.list_thumnail);
            holder.username = (TextView)  view.findViewById(R.id.username);
            holder.acceptButton = (Button)  view.findViewById(R.id.AcceptButton);
            holder.declineButton = (Button)  view.findViewById(R.id.declineButton);

            view.setTag(holder);
        }

        main = (friendRequestAdapter.dataHold) view.getTag();
        final String friendAdded = list.get(position);
        final String FriendAddURL = "http://coms-309-sb-05.cs.iastate.edu:8080/" + AppController.getInstance().getUser() + "/addFriendByName/" + list.get(position);
        final String RequestAddURL = "http://coms-309-sb-05.cs.iastate.edu:8080/" + list.get(position)+ "/addFriendByName/" + AppController.getInstance().getUser() ;
        final View finalView1 = view;



        main.acceptButton.setOnClickListener(new View.OnClickListener(){
            @Override
            public void onClick(View v) {
                System.out.println("Friend accepted");
                final ServerRequest rQue = new ServerRequest();
                friendsList_Logic friendsList = new friendsList_Logic(L, rQue, RequestAddURL,List_View, friendRequestAdapter.super.getContext(),"friendName");
                friendsList.addFriend(RequestAddURL);

                friendsList = new friendsList_Logic(L, rQue, FriendAddURL,List_View, friendRequestAdapter.super.getContext(),"friendName");
                friendsList.addFriend(FriendAddURL);
                AppController.friendRequestList.remove(position);



            }
        });

        final friendRequestAdapter.dataHold finalMain = main;
        main.declineButton.setOnClickListener(new View.OnClickListener(){
            @Override
            public void onClick(View v) {
                //Toast.makeText(getContext(),"Create new message chat" ,Toast.LENGTH_LONG).show();
               System.out.println("Friend Declined   " + AppController.friendRequestList.contains(friendAdded));
               AppController.friendRequestList.remove(position);


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
        Button acceptButton;
        Button declineButton;


    }
}
