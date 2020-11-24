package com.example.demo.messaging;

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
@ServerEndpoint(value = "/message/{username}")
public class ChatSend 
{   
    private static HashMap<Session, String> sessionToUsers = new HashMap<>();
    private static HashMap<String, Session> usersToSession = new HashMap<>();
  
    private static MessageRepository msgRepo;
  
  @Autowired
  public void setMessageRepository(MessageRepository repo)
  {
	  msgRepo = repo;
  }
	
	@OnOpen
	public void onOpen(Session session, @PathParam("username") String username)
	throws IOException, EncodeException
	{
		sessionToUsers.put(session, username);
		usersToSession.put(username, session);
		
		Message message = new Message(null,null,null);
		message.setData("user has joined chat");
		broadcast("joined");
		
		List<Message> m = msgRepo.findBydestination(username);
		
		for(int i = 0; i < m.size(); i++)
		{
			Message cur = m.get(i);
			
			sendDm((String)cur.getDestination(), "@" + cur.getStarter()+ " " + cur.getData());
			
			msgRepo.delete(cur);
		}
	
		
	}
	
	@OnMessage
	public void onMessage(Session session, String message) throws IOException, EncodeException
	{
		String username = sessionToUsers.get(session);
		
		Message msg = new Message();
		
		if(message.startsWith("@"))
		{
			String destUsername = message.split(" ")[0].substring(1);
			message = message.replace(message.split(" ")[0], "");
			
			msg.setData(message);
			msg.setStarter(username);
			msg.setDestination(destUsername);
			msgRepo.save(msg);
			
			sendDm(destUsername, "@" + username + " " + message);
		}
		else
		{
			broadcast(username + " " + message);
			msg.setData(message);
			msg.setStarter(username);
		}


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

	
	private void sendDm(String dest, String message) throws IOException, EncodeException 
	{
		try {
			usersToSession.get(dest).getBasicRemote().sendText(message);
		} 
	    catch (IOException e) {
				e.printStackTrace();
			}
		catch (NullPointerException e)
		{
			e.printStackTrace();
		}

	}
}
