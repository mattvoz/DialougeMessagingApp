package com.example.demo;

import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.Assertions;

import org.mockito.Mock;
import static org.mockito.Mockito.*;

import com.example.demo.UserCode.User;
import com.example.demo.FriendCode.FriendsList;
import com.example.demo.FriendCode.Friend;

public class UserTest {
	@Mock
	FriendsList mocked;
	
	@Mock
	Friend Friend1;
	
	@Mock
	Friend Friend2;
	
	@Mock
	Friend[] f = {Friend1,Friend2};
	
	@Test
	public void testUser()
	{
		mocked = mock(FriendsList.class);
		when(mocked.toArray()).thenReturn(f);
		User testUser = new User();
	
		testUser.setUsername("Hello");
		testUser.setEncryption("NewEncrypt");
		testUser.setPassword("Hello1");
		testUser.setFriendsList(mocked);
		
		Friend[] expected = {Friend1,Friend2};
		
		
		Assertions.assertEquals("Hello", testUser.getUsername());
		Assertions.assertEquals("NewEncrypt", testUser.getEncryptionKey());
		Assertions.assertEquals("Hello1", testUser.getPassword());
		Assertions.assertEquals(expected[0], testUser.getFriends()[0]);
		
	}

}
