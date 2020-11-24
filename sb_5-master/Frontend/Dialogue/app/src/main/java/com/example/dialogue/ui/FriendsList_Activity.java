package com.example.dialogue.ui;
import android.app.ProgressDialog;
import android.content.Context;
import android.content.Intent;
import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ArrayAdapter;
import android.widget.EditText;
import android.widget.ImageButton;
import android.widget.ImageView;
import android.widget.ListView;
import android.widget.TextView;
import android.widget.Toast;

import com.example.dialogue.R;
import com.example.dialogue.logic.friendsList_Logic;
import com.example.dialogue.network.ServerRequest;
import com.example.dialogue.objects.MessageLog;


import androidx.annotation.NonNull;
import androidx.appcompat.app.AppCompatActivity;

import org.json.JSONException;

import java.util.ArrayList;
import java.util.List;

/**
 * @author Christian Williams
 */
public class FriendsList_Activity extends AppCompatActivity implements IView {


    public static ArrayList<String> list;
    String FriendsArrayURL = "http://coms-309-sb-05.cs.iastate.edu:8080/all";
    String SearchFriendsURL;
    private ImageButton backButtons;
    private ImageButton searchButtons;
    private EditText searchBar;
    private ProgressDialog pDialog;
    // private RequestQueue rQue;
    private static ListView List_View;

//    private MyListAdapter adapter;

    /**
     * Creates the page and instantiates all of the components
     * @param savedInstanceState
     */
    @Override
    protected void onCreate(Bundle savedInstanceState) {

        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_friends_list_);

        pDialog = new ProgressDialog(FriendsList_Activity.this);
        pDialog.setMessage("Loading...");
        pDialog.setCancelable(false);

        List_View =(ListView) findViewById(R.id.listView);


        backButtons = (ImageButton) findViewById(R.id.backButton);
        backButtons.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                moveToFriends();
            }
        });

        final ServerRequest rQue = new ServerRequest();
        String type = "username";
        final friendsList_Logic friendsList = new friendsList_Logic(FriendsList_Activity.this, rQue, FriendsArrayURL,List_View,FriendsList_Activity.this,type);



        friendsList.getList();

    }

    /**
     * Moves to the User Friends Page
     */
    private void moveToFriends() {
        Intent intent2 = new Intent(FriendsList_Activity.this, userFirends_Activity.class);
        startActivity(intent2);

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

    /**]
     * Returns a String Arralist of Friends names
     * @return
     */
    public ArrayList<String> getList() {
        return list;
    }

    /**
     * Sets List of friends to s
     * @param s : Arraylist</String> of friends names to display
     */
    public void setList(ArrayList<String> s){
        list = s;
    }

//    public ArrayList<String> getFriendsList() {
//        ArrayList<String> tempList = new ArrayList<>();
//        String FriendUser = "";
//
//        for(int i = 0; i < friendslist.size(); i++){
//            try {
//                JSONObject user = friendslist.get(i);
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
//
//        return list;
//    }

    /**
     * When the onpressed it true it moves back to friends page
      */
    @Override
    public void onBackPressed() {
        moveToFriends();
        super.onBackPressed();
    }
    /**
     * Creates toast and sets text to s
     * @param s :String to set
     */
    @Override
    public void toastText(String s) {

    }

    /**
     * Changes Screen to c
     * @param c : Screen to change to
     */
    @Override
    public void changeScreen(Class c) {
        startActivity(new Intent(this.getApplicationContext(), c));
    }

    /**
     * Sets User to u
     * @param u
     */
    @Override
    public void setUser(String u) {

    }

    /**
     * Returns context
     * @return
     */
    @Override
    public Context getContext() {
        return this.getApplicationContext();
    }



//        private class MyListAdapter extends ArrayAdapter<String>{
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
