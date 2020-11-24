package com.example.dialogue.logic;

import org.json.JSONArray;
/**
 * @author Jacob Nett
 */
public interface IVolleyListener {

    public void onSuccess(String s);
    public void onSuccess(JSONArray s);

    public void onError(String s);
}
