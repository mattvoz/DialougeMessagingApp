package com.example.demo;

public class Greeting 
{
	private final long identifier;
	private final String message;
	
	public Greeting(long identifier, String message)
	{
		this.identifier = identifier;
		this.message = message;
	}
	
	public long getIdentifier()
	{
		return identifier;
	}
	
	public String getMessage()
	{
		return message;
	}
}