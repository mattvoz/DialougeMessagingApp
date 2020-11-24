package com.example.demo.FriendCode;

import java.util.Set;

import javax.persistence.*;

import com.example.demo.UserCode.User;

/**
 * 
 * @author matt
 *Table for friends
 */
@Entity
public class Friend implements Comparable<Object>
{

	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	private Integer id;

	@Column(name = "friendName")
	private String friendName;

	@Column(name = "decipher")
	private String decipher;

	@Column
	private String Location;
	
	@ManyToMany(mappedBy = "userFriends")
	Set<User> userList;
	

	public Friend()
	{
	}
	
	public Friend(String FriendName, String decipher, String Location)
	{
		this.friendName = FriendName;
		this.decipher = decipher;
		this.Location = Location;
	}

	
	public String getFriendName()
	{
		return this.friendName;
	}
	
	public String getDecipher()
	{
		return this.decipher;
	}
	
	public String getLocation()
	{
		return this.Location;
	}
	
	@Override
	public boolean equals(Object o)
	{
		if(o.getClass() != this.getClass())
		{
			return false;
		}
		
		Friend f = (Friend) o;
		if(this.id == f.id)
		{
			return true;
		}
		
		return false;
	}
	
	public int compareTo(Object o)
	{
		if (o.getClass() != this.getClass())
		{
			return 1;
		}
		
		Friend e = (Friend)o;
		
		if(this.friendName.toLowerCase().compareTo(e.getFriendName().toLowerCase()) < 0)
		{
			return 1;
		} else if(this.friendName.compareTo(e.getFriendName()) == 0)
		{
			return 0;
		}
		
		return -1;
	}
	
	public void setFriendName(String name)
	{
		this.friendName = name;
	}
	
	public void setLocation(String Location)
	{
		this.Location = Location;
	}
	
	public void setDecipher(String cipher)
	{
		this.decipher = cipher;
	}

}
