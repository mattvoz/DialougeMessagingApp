package com.example.dialogue.logic;

import android.content.Context;
import android.widget.ListView;
import android.widget.Toast;

import com.example.dialogue.R;
import com.example.dialogue.Utils.friendsListAdapter;
import com.example.dialogue.network.IServerRequest;
import com.example.dialogue.ui.IView;

import org.json.JSONArray;
import org.json.JSONException;

import java.util.ArrayList;

/**
 * @author Christian Williams
 */
public class friendsList_Logic implements IVolleyListener {


    private IView L;
    private IServerRequest serverRequest;
    private ArrayList<String> tempList = new ArrayList<String>();
    private String url;
    private String FriendNames;
    private ListView parent;
    private Context needeedContext;
    private String listType;

    /**
     *  Constructor for friends list logic
     * @param L : IView of page to use
     * @param SR :Server Request
     * @param URL : String of URL to use
     * @param v : ListView to use
     * @param viewParent :context of parent
     * @param typeList : String of what is contained in the list
     */
    public friendsList_Logic(IView L, IServerRequest SR, String URL, ListView v, Context viewParent, String typeList){
        this.L = L;
        serverRequest = SR;
        serverRequest.addVolleyListener(this);
        url = URL;
        parent = v;
        needeedContext = viewParent;
        listType = typeList;
    }

    /**
     * Gets FriendsList from the server
     */
    public void getList(){

        serverRequest.getFriendsList(url);

    }

    /**
     * Returns String ArrayList of Friends
     * @return
     */
    public ArrayList<String> retrieveList(){
        return tempList;
    }

    /**
     * Sets List of friends to s
     * @param s : ArrayList of Strings
     */
    public void setList(ArrayList<String> s){ tempList = s;}

    /**
     * On Success of server response string, s is either printed in a toast if the user is already on the friends list or is added to the User's friendsList
     * @param s
     */
    @Override
    public void onSuccess(String s) {
    if(s.equals("this user is already on your friends list") || s.equals("sorry that user doesn't exist") || s.equals("friend added") || s.equals("Friend added") ){
        Toast.makeText(needeedContext, s , Toast.LENGTH_SHORT).show();
    }
    else {
        tempList = new ArrayList<String>();
        tempList.add(s);
        parent.setAdapter(new friendsListAdapter(needeedContext, R.layout.activity_list_item, tempList, L, parent));
    }
    }
    /**
     * On Success of server response JsonArray, s is transformed into an ArrayList</String> and the list is set to friends List
     * @param s
     */
    @Override
    public void onSuccess(JSONArray s) {
        for(int i = 0; i < s.length(); i++){
            try {
                String user = s.getJSONObject(i).getString(listType);

                    System.out.println(s.getJSONObject(i).getString(listType));
                if(user != null){
                tempList.add(user);
                }

            } catch (JSONException e) {
                e.printStackTrace();
            }

        }
        System.out.println(s.length());
        if (tempList !=null) {
            if (listType.equals("username")) {
                parent.setAdapter(new friendsListAdapter(needeedContext, R.layout.activity_list_item, tempList, L, parent));
            } else {
                parent.setAdapter(new friendsListAdapter(needeedContext, R.layout.activity_list_item, tempList, L, parent));
            }

        }

    }

    /**
     * Finds Friends or potential friends in the user database to add to friends list
     * @param url :URL to use
     * @param friendName : Username of friend to look up
     * @throws JSONException
     */
    public void findFriends(String url,String friendName) throws JSONException {
        FriendNames = friendName;
        serverRequest.getSearchResults(url);
    }

    /**
     * Retruns arraylist of friends on success of server call
     * @return
     */
    public ArrayList<String> findFriendsSuccess() {
        tempList = new ArrayList<String>();
        if (FriendNames != null) {
            tempList.add(FriendNames);
        }

        return tempList;
    }

    /**
     * adds friend in URL to the user's friends list
     * @param url :URL containing basic URL and Friend's Username
     */
    public void addFriend(String url){
        serverRequest.addFriends(url);
    }

    /**
     * On error of server request
     * @param s
     */
    @Override
    public void onError(String s) {

    }
}
