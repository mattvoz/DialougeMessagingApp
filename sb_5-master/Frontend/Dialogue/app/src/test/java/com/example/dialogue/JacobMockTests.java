package com.example.dialogue;
import android.content.Context;
import android.widget.ListView;

import com.example.dialogue.Utils.LogListAdapter;
import com.example.dialogue.logic.AdminToolsLogic;
import com.example.dialogue.logic.CurrentMessageLogic;
import com.example.dialogue.logic.IVolleyListener;
import com.example.dialogue.logic.LoginLogic;
import com.example.dialogue.logic.RegistrationLogic;
import com.example.dialogue.logic.UserMessageLogic;
import com.example.dialogue.network.IServerRequest;
import com.example.dialogue.ui.IView;
import com.example.dialogue.ui.Login;

import org.json.JSONArray;
import org.json.JSONException;
import org.json.JSONObject;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.junit.runners.JUnit4;
import org.mockito.Mock;

import static org.junit.Assert.assertEquals;
import static org.mockito.Mockito.*;


@RunWith(JUnit4.class)
public class JacobMockTests {
    @Mock
    private IView view;

    @Mock
    private IServerRequest req;

    @Mock
    private IVolleyListener iVolleyListener;

    @Mock
    private ListView listView;

    @Mock
    private Login login;




    @Mock
    CurrentMessageLogic currentMessageLogic;

    @Mock
    private Context context;
    @Test
    public void testCheckLoginRequest() {
        view = mock(IView.class);
        req = mock(IServerRequest.class);
        LoginLogic l = new LoginLogic(view, req);

        l.Login("jpnett", "12345");

        verify(req).LoginToServer("http://coms-309-sb-05.cs.iastate.edu:8080/user/jpnett/12345");


    }
//    @Test
//    public void BuildListLogTest(){
//        currentMessageLogic = mock(CurrentMessageLogic.class);
//        LogListAdapter Adapter = mock(LogListAdapter.class);
//        view = mock(IView.class);
//        listView = mock(ListView.class);
//        UserMessageLogic userMessageLogic = new UserMessageLogic(view, listView);
//        userMessageLogic.buildList();
//
//        verify(listView).setAdapter(Adapter);
//    }
    @Test
    public void TestRegistrationSuccess(){
        view = mock(IView.class);
        req = mock(IServerRequest.class);
        RegistrationLogic registrationLogic = new RegistrationLogic(view, req);
        registrationLogic.onSuccess("Saved");
        verify(view).changeScreen(Login.class);

    }
//    @Test
//    public void TestSignUp(){
//        view = mock(IView.class);
//        req = mock(IServerRequest.class);
//        RegistrationLogic registrationLogic = new RegistrationLogic(view, req);
//        JSONObject jsonObject = new JSONObject();
//        try {
//            jsonObject.put("username", "Jacob");
//            jsonObject.put("password", "12345");
//        } catch (JSONException e) {
//            e.printStackTrace();
//        }
//
//
//        String url = "http://coms-309-sb-05.cs.iastate.edu:8080/addUser";
//        registrationLogic.Register("Jacob", "12345", "12345");
//        verify(req).RegisterToServer(url, jsonObject);
//
//    }

    @Test
    public void MockingTest(){
        view = mock(IView.class);
        req = mock(IServerRequest.class);
        LoginLogic loginLogic = new LoginLogic(view, req);
        int i = loginLogic.mockingTestMethod(1);
        verify(view).toastText("hello");
        assertEquals(i , 7);
    }
    @Test
    public void AdminToolTest(){

        view = mock(IView.class);
        req = mock(IServerRequest.class);
        listView = mock(ListView.class);
        AdminToolsLogic adminToolsLogic = new AdminToolsLogic(view,req, listView);
        adminToolsLogic.setUser("jpnett");
        doNothing().when(req).getReport("http://coms-309-sb-05.cs.iastate.edu:8080/report/removeReport/jpnett");
        Boolean answer = adminToolsLogic.deleteReport("http://coms-309-sb-05.cs.iastate.edu:8080/report/removeReport/jpnett");

        assertEquals(false, answer);

    }

}
