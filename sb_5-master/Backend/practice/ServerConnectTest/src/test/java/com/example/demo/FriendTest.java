package com.example.demo;

import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.Assertions;
import static org.mockito.Mockito.*;

import com.example.demo.UserCode.User;
import com.example.demo.FriendCode.FriendsList;
import com.example.demo.FriendCode.Friend;

public class FriendTest 
{
	@Test
	public void compareToTest()
	{
		Friend f1 = new Friend();
		Friend f2 = new Friend();
		
		f1.setFriendName("Ab");
		f2.setFriendName("AC");
		
		Assertions.assertEquals(1,f1.compareTo(f2));
	}

}
