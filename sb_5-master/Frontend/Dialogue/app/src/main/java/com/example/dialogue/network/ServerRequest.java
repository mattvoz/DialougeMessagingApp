
package com.example.dialogue.network;
import com.android.volley.Request;
import com.android.volley.Response;
import com.android.volley.VolleyError;
import com.android.volley.toolbox.JsonArrayRequest;
import com.android.volley.toolbox.JsonObjectRequest;
import com.android.volley.toolbox.StringRequest;
import com.example.dialogue.logic.IVolleyListener;
import com.example.dialogue.Utils.AppController;
import com.google.gson.JsonObject;

import org.json.JSONArray;
import org.json.JSONException;
import org.json.JSONObject;

import java.util.HashMap;

/**
 * @author Jacob Nett and Christian Williams
 */
public class ServerRequest implements IServerRequest {

    private IVolleyListener I;
    @Override
    public void LoginToServer(String url){

        JsonObjectRequest req = new JsonObjectRequest(Request.Method.GET, url, null,
                new Response.Listener<JSONObject>() {
                    @Override
                    public void onResponse(JSONObject response) {
                            String r = "";
                            try {
                                r = response.getString("userType");
                            } catch (JSONException e) {
                                e.printStackTrace();
                            }
                            I.onSuccess(r);
                    }
                }, new Response.ErrorListener() {
            @Override
            public void onErrorResponse(VolleyError error) {
                String K = null;
                I.onSuccess(K);
                error.printStackTrace();

            }
        });
        AppController.getInstance().addToRequestQueue(req);
    }
    @Override
    /**
     * sends user info to server to register them to the table
     */
    public void RegisterToServer(String url, final JSONObject object){
        final JSONObject j = object;
        StringRequest req = new StringRequest(Request.Method.POST, url,
                new Response.Listener<String>() {
                    public void onResponse(String response) {
                        I.onSuccess(response);
                    }
                },
                new Response.ErrorListener() {
                    public void onErrorResponse(VolleyError error) {
                        error.printStackTrace();
                    }
                }
        ) {
            public byte[] getBody() {
                return j.toString().getBytes();
            }
            public String getBodyContentType() {
                return "application/json";
            }
        };
        AppController.getInstance().addToRequestQueue(req);
    }
    @Override
    /**
     * pulls a user friends table
     */
    public void getFriendsList(String url) {
        JsonArrayRequest req = new JsonArrayRequest(Request.Method.GET, url, null,
                new Response.Listener<JSONArray>() {
                    @Override
                    public void onResponse(JSONArray response) {
                        if(response != null) {
                            System.out.println("Size: " + response.length());

                            I.onSuccess(response);
                        }
                    }

                }, new Response.ErrorListener() {
            @Override
            public void onErrorResponse(VolleyError error) {
                error.printStackTrace();
            }
        });
        AppController.getInstance().addToRequestQueue(req);
    }

    @Override
    /**
     * pulls results from search table
     */
    public void getSearchResults(String url) {
        JsonObjectRequest req = new JsonObjectRequest(Request.Method.GET, url, null,
                new Response.Listener<JSONObject>() {
                    @Override
                    public void onResponse(JSONObject response) {
                        if(response != null){
                            try {
                                I.onSuccess(response.getString("friendName"));
                            } catch (JSONException e) {
                                e.printStackTrace();
                            }
                            try {
                                System.out.println(response.getString("friendName"));
                            } catch (JSONException e) {
                                e.printStackTrace();
                            }
                        }
                    }
                }, new Response.ErrorListener() {
            @Override
            public void onErrorResponse(VolleyError error) {
                error.printStackTrace();

            }
        });
        AppController.getInstance().addToRequestQueue(req);
    }

    @Override
    /**
     * adds a user into the friends table
     */
    public void addFriends(String url){
        StringRequest req = new StringRequest(Request.Method.POST, url,  new Response.Listener<String>() {
            @Override
            public void onResponse(String response) {
                I.onSuccess(response);
            }
        }, new Response.ErrorListener() {
            @Override
            public void onErrorResponse(VolleyError error) {
                error.printStackTrace();
            }
        });
        AppController.getInstance().addToRequestQueue(req);
    }
    @Override
    public void getReport(String url){
        JsonArrayRequest req = new JsonArrayRequest(Request.Method.GET, url, null,
                new Response.Listener<JSONArray>() {
                    @Override
                    public void onResponse(JSONArray response) {
                        if(response != null) {
                            System.out.println("Size: " + response.length());

                            I.onSuccess(response);
                        }
                    }

                }, new Response.ErrorListener() {
            @Override
            public void onErrorResponse(VolleyError error) {
                error.printStackTrace();
            }
        });
        AppController.getInstance().addToRequestQueue(req);
    }

    @Override
    public void deleteReport(String url) {
        JsonArrayRequest req = new JsonArrayRequest(Request.Method.POST, url, null,
                new Response.Listener<JSONArray>() {
                    @Override
                    public void onResponse(JSONArray response) {
                        I.onSuccess("yes");
                    }
                }, new Response.ErrorListener() {
            @Override
            public void onErrorResponse(VolleyError error) {
                error.printStackTrace();
            }
        });
        AppController.getInstance().addToRequestQueue(req);
    }

    @Override
    public void issueInfraction(String url) {
        JsonObjectRequest req = new JsonObjectRequest(Request.Method.GET, url, null,
                new Response.Listener<JSONObject>() {
                    @Override
                    public void onResponse(JSONObject response) {
                    }
                }, new Response.ErrorListener() {
            @Override
            public void onErrorResponse(VolleyError error) {
                error.printStackTrace();

            }
        });
        AppController.getInstance().addToRequestQueue(req);
    }

    @Override
    public void reportMessages(String url, JSONObject message) {
        final JSONObject j = message;
        StringRequest req = new StringRequest(Request.Method.POST, url,
                new Response.Listener<String>() {
                    public void onResponse(String response) {
                        I.onSuccess(response);
                    }
                },
                new Response.ErrorListener() {
                    public void onErrorResponse(VolleyError error) {
                        error.printStackTrace();
                    }
                }
        ) {
            public byte[] getBody() {
                return j.toString().getBytes();
            }
            public String getBodyContentType() {
                return "application/json";
            }
        };
        AppController.getInstance().addToRequestQueue(req);
    }

    @Override
    /**
     * sets what lagic class the server request is using
     */
    public void  addVolleyListener(IVolleyListener logic){
        I = logic;
    }


}
