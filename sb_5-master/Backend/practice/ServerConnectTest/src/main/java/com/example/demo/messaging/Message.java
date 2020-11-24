package com.example.demo.messaging;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.Lob;
import javax.persistence.Table;

@Entity
@Table(name = "messages")
public class Message
{
	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	private Long id;
	
	@Lob
	private String data;
	
	@Column
	private String starter;
	
	@Column
	private String destination;
	
	public Message()
	{
	}
	
	public Message(String data, String destination, String starter)
	{
		this.data = data;
		this.destination = destination;
		this.starter = starter;
	}
	
	public String getStarter()
	{
		return this.starter;
	}
	
	public String getData()
	{
		return this.data;
	}
	
	public String getDestination()
	{
		return this.destination;
	}
	
	public void setData( String data)
	{
		this.data = data;
	}
	
	public void setDestination(String destination)
	{
		this.destination = destination;
	}
	
	public void setStarter(String startLocation)
	{
		this.starter = startLocation;
	}

}
