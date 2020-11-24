package com.example.dialogue;

import android.app.Activity;
import android.app.Dialog;
import android.content.Context;
import android.content.Intent;
import android.widget.Button;
import android.widget.EditText;
import android.widget.ImageButton;
import android.widget.ListView;

import androidx.annotation.ContentView;

import com.example.dialogue.Utils.AppController;
import com.example.dialogue.Utils.LogListAdapter;
import com.example.dialogue.Utils.friendsListAdapter;
import com.example.dialogue.logic.CurrentMessageLogic;
import com.example.dialogue.logic.IVolleyListener;
import com.example.dialogue.logic.UserMessageLogic;
import com.example.dialogue.logic.friendRequestLogic;
import com.example.dialogue.logic.friendsList_Logic;
import com.example.dialogue.network.IServerRequest;
import com.example.dialogue.network.IWeb;
import com.example.dialogue.objects.MessageLog;
import com.example.dialogue.objects.Messages;
import com.example.dialogue.ui.CurrentMessages;
import com.example.dialogue.ui.FriendsList_Activity;
import com.example.dialogue.ui.IView;
import com.example.dialogue.ui.UserMessaging;
import com.example.dialogue.ui.friendRequest_Activity;
import com.example.dialogue.ui.userFirends_Activity;

import org.junit.Before;
import org.junit.Test;
import org.junit.runner.Request;
import org.junit.runner.RunWith;
import org.junit.runners.JUnit4;
import org.mockito.Mock;
import org.mockito.MockitoAnnotations;
import org.mockito.invocation.InvocationOnMock;
import org.mockito.junit.MockitoJUnit;
import org.mockito.runners.MockitoJUnitRunner;
import org.mockito.stubbing.Answer;

import java.util.ArrayList;
import java.util.List;

import static org.junit.Assert.assertEquals;
import static org.mockito.Mockito.doAnswer;
import static org.mockito.Mockito.doNothing;
import static org.mockito.Mockito.doReturn;
import static org.mockito.Mockito.mock;
import static org.mockito.Mockito.never;
import static org.mockito.Mockito.verify;
import static org.mockito.Mockito.when;
import androidx.test.InstrumentationRegistry;

import tech.gusavila92.websocketclient.WebSocketClient;

@RunWith(JUnit4.class)
public class ChristianMockTests {

    @Mock
    private Context context;
    @Mock
    private IView view;
    @Mock
    private IServerRequest server;
    @Mock
    private FriendsList_Activity friendsListActivity;
    @Mock
    private IVolleyListener volley;
    @Mock
    private AppController appcontroller;
    @Mock
    private friendRequestLogic friendRequestLogic;

    @Before
    public void setUpMockito() {
        MockitoAnnotations.initMocks(this);
    }

    ImageButton searchbutton;
@Test
public void testFriendsList(){
    appcontroller = mock(AppController.class);
    final ArrayList<String> testFriends = new ArrayList<String>();
    testFriends.add("Chuck");
    testFriends.add("Bailey");
    testFriends.add("Colton");
    final ArrayList<String> userFriends = new ArrayList<String>();
    appcontroller.setUser("User1000");
    volley = mock(IVolleyListener.class);
    server = mock(IServerRequest.class);

    view = mock(IView.class);
    context = view.getContext();
    ListView testList = new ListView(context);
    friendsList_Logic  FriendsList = new friendsList_Logic(view, server,"http://coms-309-sb-05.cs.iastate.edu:8080/all",testList,context,"username");
    FriendsList.getList();
    verify(server).getFriendsList("http://coms-309-sb-05.cs.iastate.edu:8080/all");
}
//@Test
//    public void searchFriendUrl(){
//    appcontroller = mock(AppController.class);
//    final ArrayList<String> userFriends = new ArrayList<String>();
//    String User = "User1000";
//    volley = mock(IVolleyListener.class);
//    server = mock(IServerRequest.class);
//    userFirends_Activity userFirendsActivity = new userFirends_Activity();
//    friendsList_Logic search = mock(friendsList_Logic.class);
//    view = mock(IView.class);
////    ListView testList = new ListView(context);
////    friendsListAdapter  FriendsList = new friendsListAdapter(context, R.layout.activity_list_item,testFriends,view,testList);
////    friendsListAdapter.
//
//userFirendsActivity.setSearchBar("User1000");
//    assertEquals(userFirendsActivity.getSearchbar().equals("http://coms-309-sb-05.cs.iastate.edu:8080/findFriend/" + "User1000"), true);
//
//}


    @Test
        public void reportButtonTest(){

        appcontroller = mock(AppController.class);
        Messages B = mock(Messages.class);
        final ArrayList<Messages> A = new ArrayList<Messages>();
        A.add(B);

        final LogListAdapter adapter = mock(LogListAdapter.class);
        view = mock(IView.class);
        appcontroller.setUser("User1000");
        UserMessaging activity = mock(UserMessaging.class);
        context = activity.getContext();

        final ListView k = new ListView(context);
        k.setAdapter(adapter);
        UserMessageLogic userLogic =mock(UserMessageLogic.class);


        verify(activity,never()).toastText("Message reported");

    }

    @Test
        public void testSettingFriendsList(){

        appcontroller = mock(AppController.class);
        final ArrayList<String> userFriends = new ArrayList<String>();
        String User = "User1000";
        view = mock(IView.class);
        volley = mock(IVolleyListener.class);
        server = mock(IServerRequest.class);
        FriendsList_Activity friendsListMain = (new FriendsList_Activity());
        userFirends_Activity friends = mock(userFirends_Activity.class);
        friendsList_Logic logic = mock(friendsList_Logic.class);
        ArrayList<String> friendsList = new ArrayList<String>();
        friendsList.add("bob");
        friendsList.add("Karen");

        friendsListMain.setList(friendsList);

        assertEquals(friendsList.equals(friendsListMain.getList()), true);


    }

    @Test
    public void NewTestforDemo4(){

        appcontroller = mock(AppController.class);
        view = mock(IView.class);
        volley = mock(IVolleyListener.class);
        server = mock(IServerRequest.class);
        IWeb iWeb = mock(IWeb.class);
        String user = "user1000";
        appcontroller.setUser(user);
        ListView lv = mock(ListView.class);

        friendRequest_Activity friendRequest = mock(friendRequest_Activity.class);
        final ArrayList<String> dummyList = new ArrayList<String>();
        dummyList.add("Dave");
        dummyList.add("Chuck");
        dummyList.add("Lexi");
        dummyList.add("Adam");
        dummyList.add("user1000");
        dummyList.add("Garth");

        friendRequestLogic = new friendRequestLogic(lv, friendRequest.getContext(), view );
        friendRequestLogic.setRequestList(dummyList);
        System.out.println(friendRequestLogic.getRequestList().size());
        assertEquals(dummyList.size() == friendRequestLogic.getRequestList().size(), true);
        friendRequestLogic.setUser(user);
        friendRequestLogic.createWebSocketClient();
        WebSocketClient webSocketClient = friendRequestLogic.getWebSocketClient();
        webSocketClient.onTextReceived("Thomas");
//        friendRequestLogic.sendRequest("Thomas");
        //verify((webSocketClient).send("Thomas "+ user));//.then(new Answer<Object>() {
//            @Override
//            public Object answer(InvocationOnMock invocation) throws Throwable {
//                return dummyList.add("Thomas");
//            }
//        });
        //verify(friendRequestLogic).getWebSocketClient().send("Thomas "+ user);
        assertEquals(friendRequestLogic.getRequestList().size()== 7, true);




    }

}
