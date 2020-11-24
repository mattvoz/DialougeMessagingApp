package com.example.demo;

import static org.mockito.Mockito.mock;
import static org.mockito.Mockito.when;

import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.Assertions;
import org.mockito.Mock;

import com.example.demo.FriendCode.Friend;
import com.example.demo.FriendCode.FriendsList;

public class FriendListTest 
{
	@Mock
	Friend friend1;
	
	@Mock
	Friend friend2;
	
	
	@Test
	public void EmptyTest()
	{
		FriendsList f = new FriendsList();
		Assertions.assertEquals(true,f.isEmpty());
	}
	
	@Test
	public void sizeTest()
	{
		FriendsList f = new FriendsList();
		
		friend1 = mock(Friend.class);
		
		Assertions.assertEquals(0,f.size());
		
		
		
		friend1.setFriendName("hello");
		
		f.add(friend1);
		
		Assertions.assertEquals(1,f.size());
		
		f.remove(friend1);
		
		Assertions.assertEquals(0,f.size());
	}
	
	@Test
	public void addTest()
	{
		friend1 = mock(Friend.class);
		friend2 = mock(Friend.class);
		FriendsList f = new FriendsList();
		
		friend1.setFriendName("tim");
		friend2.setFriendName("ted");
		
		f.add(friend1);
		Assertions.assertEquals(false,f.add(friend1));
	}
	
	@Test
	public void multiAdd()
	{
		friend1 = mock(Friend.class);
		friend2 = mock(Friend.class);
		FriendsList f = new FriendsList();
		
		when(friend1.getFriendName()).thenReturn("tim");
		when(friend2.getFriendName()).thenReturn("tom");
		when(friend1.compareTo(friend2)).thenReturn(1);
		
		f.add(friend1);
		f.add(friend2);
		Assertions.assertEquals(2,f.size());
	}
	
	/*
	@Test
	public void toArrayTest()
	{
		friend1 = mock(Friend.class);
		friend2 = mock(Friend.class);
		FriendsList f = new FriendsList();
		
		friend1.setFriendName("tim");
		friend2.setFriendName("ted");
		
		f.add(friend1);
		f.add(friend2);
		
		Friend[] Mock = new Friend[2];
		
		Mock[0] = friend2;
		Mock[1] = friend1;
		
		//assertEquals(Mock.toArray()[0]);
		
	}
	*/
	
	

}
