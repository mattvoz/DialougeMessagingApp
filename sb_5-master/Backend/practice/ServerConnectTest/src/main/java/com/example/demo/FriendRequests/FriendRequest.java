package com.example.demo.FriendRequests;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;

@Entity
public class FriendRequest 
{
	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	private Integer id;
	
	@Column
	private String sender;
	
	@Column
	private String reciever;
	
	public void FriendRequest()
	{
	}
	
	public void setSender(String sender)
	{
		this.sender = sender;
	}
	
	public void setReceiver(String reciever)
	{
		this.reciever = reciever;
	}
	
	public String getSender()
	{
		return this.sender;
	}
	
	public String getReciever()
	{
		return this.reciever;
	}

}
