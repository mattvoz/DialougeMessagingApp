package com.example.dialogue.ui;

import android.app.ProgressDialog;
import android.content.Context;
import android.content.Intent;
import android.os.Bundle;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ArrayAdapter;
import android.widget.Button;
import android.widget.EditText;
import android.widget.ImageButton;
import android.widget.ImageView;
import android.widget.ListView;
import android.widget.TextView;
import android.widget.Toast;

import androidx.annotation.NonNull;
import androidx.appcompat.app.AppCompatActivity;

import com.android.volley.Request;
import com.android.volley.RequestQueue;
import com.android.volley.Response;
import com.android.volley.VolleyError;
import com.android.volley.toolbox.JsonArrayRequest;
import com.android.volley.toolbox.Volley;
import com.example.dialogue.R;
import com.example.dialogue.Utils.AppController;
import com.example.dialogue.logic.CurrentMessageLogic;
import com.example.dialogue.logic.friendsList_Logic;
import com.example.dialogue.network.ServerRequest;

import org.json.JSONArray;
import org.json.JSONException;
import org.json.JSONObject;

import java.util.ArrayList;
import java.util.List;
import java.util.Set;

/**
 * @author Christian Williams
 */
public class userFirends_Activity extends AppCompatActivity implements IView{


    String username =  AppController.getInstance().getUser();;
    String FriendsArrayURL= "http://coms-309-sb-05.cs.iastate.edu:8080/" + username+ "/friends";;
    String FindFriendsURL;
    String SearchFriendsURL;
    private ImageButton searchButtons;
    private ImageButton backButtons;
    private Button addFriends;
    private String TAG = userFirends_Activity.class.getSimpleName();
    private ProgressDialog pDialog;
    private ListView List_View;
    private EditText searchBar;
    private Button FriendRequestButton;
    //private ArrayList<String> list = new ArrayList<String>();
    private boolean done = false;

//    private MyListAdapter adapter;

    /**
     * Creates and instantiates each item on the page
     * @param savedInstanceState
     */
    @Override
    protected void onCreate(Bundle savedInstanceState) {

        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_user_friends);

     //   username = AppController.getInstance().getUser();
  //      FindFriendsURL = "http://coms-309-sb-05.cs.iastate.edu:8080/" + username+ "/friends";


        pDialog = new ProgressDialog(this);
        pDialog.setMessage("Loading...");
        pDialog.setCancelable(false);

        List_View =(ListView) findViewById(R.id.listView);


        final ServerRequest rQue = new ServerRequest();
        String type = "friendName";
        final friendsList_Logic friendsList = new friendsList_Logic(userFirends_Activity.this, rQue, FriendsArrayURL,List_View,userFirends_Activity.this,type);


        backButtons = (ImageButton) findViewById(R.id.backButton);
        backButtons.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                moveToMessages();
            }
        });

        addFriends = (Button) findViewById(R.id.addFriends);
        addFriends.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                moveToAddFriends();
            }
        });

        FriendRequestButton = (Button) findViewById(R.id.FriendReqbutton);
        FriendRequestButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                changeScreen(friendRequest_Activity.class);
            }
        });

        searchBar = (EditText) findViewById(R.id.searchUsername) ;
        searchButtons = (ImageButton) findViewById(R.id.searchButton);
        searchButtons.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                //System.out.println("HERE +++++++++++++++++++++:" + searchBar.getText().toString());
                setSearchBar(searchBar.getText().toString());
                    String type = "friendName";
                    friendsList_Logic search = new friendsList_Logic(userFirends_Activity.this, rQue, SearchFriendsURL,List_View,userFirends_Activity.this,type);
                try {
                    search.findFriends(SearchFriendsURL,type);
                } catch (JSONException e) {
                    e.printStackTrace();
                }


            }
        });

          friendsList.getList();




    }

    /**
     * Switches pages to Current Messages page
     */
    private void moveToMessages() {
        Intent intent2 = new Intent(userFirends_Activity.this, CurrentMessages.class);
        startActivity(intent2);

    }

    /**
     * Switches page to FriendsList page
     */
    private void moveToAddFriends() {
        Intent intent2 = new Intent(userFirends_Activity.this, FriendsList_Activity.class);
        startActivity(intent2);

    }

    /**
     * Returns what is in the search bar
     * @return
     */
    public String getSearchbar() {
        return SearchFriendsURL ;
    }

    /**
     * Sets what is inside the search bar
     * @param s :String that the search bar will be set to along with URL
     */
    public void setSearchBar (String s){
        SearchFriendsURL = "http://coms-309-sb-05.cs.iastate.edu:8080/findFriend/" + s;
    }

    /**
     * Shows Progress Dialog
     */
    private void showProgressDialog() {
        if (!pDialog.isShowing())
            pDialog.show();
    }

    /**
     * Hides Progress Dialog
     */
    private void hideProgressDialog() {
        if (pDialog.isShowing())
            pDialog.hide();
    }

//    public ArrayList<String> getFriendsList() {
//        ArrayList<String> tempList = new ArrayList<>();
//        String FriendUser = "";
//        for(int i = 0; i < list.size(); i++){
//            try {
//                JSONObject user = list.get(i);
//                FriendUser = user.getString("username");
//                tempList.add(FriendUser);
//                //    System.out.println(FriendUser);
//
//            } catch (JSONException e) {
//                e.printStackTrace();
//            }
//        }
//
//        //System.out.println("HEREEEEEEEEEEEEEEEEEEEEE");
//        List_View.setAdapter(new userFirends_Activity.listAdapter(userFirends_Activity.this, R.layout.activity_list_item, list));
//        return list;
//    }


    @Override
    public void onBackPressed() {
        changeScreen(CurrentMessages.class);
        super.onBackPressed();
    }
    /**
     * Creates a toast and sets toast text to s
     * @param s : string to display on the toast
     */
    @Override
    public void toastText(String s) {
        //nothing needed
    }

    /**
     * Changes screen to C
     * @param c :Screen to change to
     */
    @Override
    public void changeScreen(Class c) {
        startActivity(new Intent(this.getApplicationContext(), c));
    }

    /**
     * Sets User for the application
     * @param u :String of User to set
     */
    @Override
    public void setUser(String u) {
            // nothing needed
    }
/**
 * Gets context of the app
 */
    @Override
    public Context getContext() {
        return this.getApplicationContext();
    }

    /**
     * Returns the URL of FriendsArray
     * @return
     */
    public String getFriendsArrayURL(){
        return FriendsArrayURL;
    }


//    private class listAdapter extends ArrayAdapter<String>{
//        private int layout;
//        private List<String> list;
//        public listAdapter(@NonNull Context context, int resource, @NonNull List<String> objects) {
//            super(context, resource, objects);
//            layout = resource;
//            list = objects;
//
//        }
//        @Override
//        public View getView(int position, View view, ViewGroup parent){
//            dataHold main = null;
//            if (view == null){
//                LayoutInflater inflater = LayoutInflater.from(getContext());
//                view = inflater.inflate(layout, parent, false);
//                dataHold holder= new dataHold();
//                holder.thumbnail = (ImageView) view.findViewById(R.id.list_thumnail);
//                holder.username = (TextView)  view.findViewById(R.id.username);
//                holder.List_addbutton = (ImageButton)  view.findViewById(R.id.list_add);
//                holder.List_messagebutton = (ImageButton)  view.findViewById(R.id.list_message);
//
//                view.setTag(holder);
//            }
//                main = (dataHold) view.getTag();
//
//                main.List_addbutton.setOnClickListener(new View.OnClickListener(){
//                @Override
//                public void onClick(View v) {
//                    Toast.makeText(getContext(),"Friend added", Toast.LENGTH_SHORT);
//                }
//                });
//
//            main.username.setText(list.get(position));
//            return view;
//        }
//    public class dataHold {
//        ImageView thumbnail;
//        TextView username;
//        ImageButton List_addbutton;
//        ImageButton List_messagebutton;
//
//    }
//}
//
////        private class MyListAdapter extends ArrayAdapter<String>{
//        private int layout;
//        private List<String> list;
//        private final Activity context;
//
//            public MyListAdapter(@NonNull Activity context, int resource, @NonNull List<String> objects) {
//                super(context, resource, objects);
//
//
//                this.list = list;
//                this.context = context;
//            }
//
//
//
//            public View getView(int postition, View view, ViewGroup parent){
//                LayoutInflater inflater = context.getLayoutInflater();
//                View rowView = inflater.inflate(R.layout.activity_list_item, null, true);
//
//                TextView username = (TextView) rowView.findViewById(R.id.username);
//                if(list.get(postition) != null || (list.size() != 0)) {
//                    username.setText(list.get(postition));
//                }
//
//                   return rowView;
//            };


      //  }



}

