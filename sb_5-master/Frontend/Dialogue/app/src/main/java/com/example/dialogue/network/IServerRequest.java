package com.example.dialogue.network;

import com.example.dialogue.logic.IVolleyListener;

import org.json.JSONObject;
/**
 * @author Jacob Nett
 */
public interface IServerRequest {
    public void LoginToServer(String url);
    public void RegisterToServer(String url, JSONObject object);
    public void addVolleyListener(IVolleyListener Logic);
    public void getFriendsList(String url);
    public void getSearchResults(String url);
    public void addFriends(String url);
    public void getReport(String url);
    public void deleteReport(String url);
    public void issueInfraction(String url);
    public void reportMessages(String url,JSONObject message);


}
