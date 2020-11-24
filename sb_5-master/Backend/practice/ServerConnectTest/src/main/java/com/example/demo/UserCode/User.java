package com.example.demo.UserCode;

import java.util.HashSet;
import java.util.Set;
import java.util.SortedSet;

import javax.persistence.*;
//import FriendList.FriendsList;
import javax.sql.rowset.serial.SerialBlob;

import com.example.demo.FriendCode.Friend;
import com.example.demo.FriendCode.FriendsList;


@Entity
public class User
{

	
	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	private Integer id;
	
	@Column
	private String username;
	
	@Column
	private String password;
	
	@Column
	private String encryptionKey;
	
	@Column
	private int infractions = 0;
	
	@Column
	private int userType = 0;
	//@Column
	//private SerialBlob profilePicture;
	
	@ManyToMany
	@JoinTable(
			name = "user_friends",
			joinColumns = @JoinColumn(name = "user_id"),
			inverseJoinColumns = @JoinColumn(name = "friend_id")
			)
	private Set<Friend> userFriends;
	
	
	public String getUsername()
	{
		return this.username;
	}
	
	public String getPassword()
	{
		return password;
	}
	
	public String getEncryptionKey()
	{
		return encryptionKey;
	}
	
	public void setUsername(String newUsername)
	{
		this.username = newUsername;
	}
	
	public void setPassword(String newPassword)
	{
		this.password = newPassword;
	}
	
	public void setEncryption(String newEncrypt)
	{
		this.encryptionKey = newEncrypt;
	}
	
	
	public Object[] getFriendsArray()
	{
		return this.userFriends.toArray();
	}
	
	public Set<Friend> getFriendsSet()
	{
		return this.userFriends;
	}
	


	public void setFriendsList(FriendsList f)
	{
		this.userFriends = f;
	}
	
	public void setEmptyFriendsList()
	{
		this.userFriends = new HashSet<Friend>();
	}

	public String addFriend(Friend f)
	{
		if(this.userFriends.add(f))
		{
			return "friend added";
		}
		
		return "failed to add friend";
	}

	public Object[] getFriends()
	{
		return this.userFriends.toArray();
	}
	
	public void updateUsertype(int n)
	{
		this.userType = n;
	}
	
	public int getUserType()
	{
		return this.userType;
	}
	
	public void addInfraction()
	{
		this.infractions += 1;
	}
	
	public int getInfractions()
	{
		return this.infractions;
	}
	
}

