package com.example.demo.FriendRequests;

import java.io.IOException;
import java.util.HashMap;
import java.util.List;

import javax.websocket.EncodeException;
import javax.websocket.OnClose;
import javax.websocket.OnMessage;
import javax.websocket.OnOpen;
import javax.websocket.Session;
import javax.websocket.server.PathParam;
import javax.websocket.server.ServerEndpoint;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;


@Controller
@ServerEndpoint(value = "/friendRequest/{username}")
public class FriendRequestSend 
{   
    private static HashMap<Session, String> sessionToUsers = new HashMap<>();
    private static HashMap<String, Session> usersToSession = new HashMap<>();
  
    private static FriendRequestRepository friendRepo;
  
  @Autowired
  public void setMessageRepository(FriendRequestRepository repo)
  {
	  friendRepo = repo;
  }
	
	@OnOpen
	public void onOpen(Session session, @PathParam("username") String username)
	throws IOException, EncodeException
	{
		sessionToUsers.put(session, username);
		usersToSession.put(username, session);

		
		usersToSession.get(username).getBasicRemote().sendText("now taking requests");
		List<FriendRequest> m = friendRepo.findByReciever(username);
		
		for(int i = 0; i < m.size(); i++)
		{
			FriendRequest cur = (FriendRequest) m.get(i);
			
			sendRequest((String)cur.getReciever(),cur.getSender());
			
			friendRepo.delete(cur);
		}
		
	}
	
	@OnMessage
	public void onRequest(Session session, String Request) throws IOException, EncodeException
	{
		
		String reciever = Request.split(" ")[0];
		String sender = Request.split(" ")[1];
		
		
		sendRequest(reciever,sender);


	}
	
	@OnClose
	public void onClose(Session session) throws IOException, EncodeException {
		String username = sessionToUsers.get(session);
		sessionToUsers.remove(session);
		usersToSession.remove(username);
		
		
	}
	
	private void broadcast(String message) {
		sessionToUsers.forEach((session, username) -> {
			try {
				session.getBasicRemote().sendText(message);
			} 
      catch (IOException e) {
				e.printStackTrace();
			}

		});

	}

	
	private void sendRequest(String Reciever, String Sender) throws IOException, EncodeException 
	{
		try {
			usersToSession.get(Reciever).getBasicRemote().sendText(Sender);
		} 
	    catch (IOException e) {
				e.printStackTrace();
				FriendRequest notOnline = new FriendRequest();
				notOnline.setReceiver(Reciever);
				notOnline.setSender(Sender);
				friendRepo.save(notOnline);
			}
		catch (NullPointerException e)
		{
			e.printStackTrace();
			FriendRequest notOnline = new FriendRequest();
			notOnline.setReceiver(Reciever);
			notOnline.setSender(Sender);
			friendRepo.save(notOnline);
		}

	}
}

