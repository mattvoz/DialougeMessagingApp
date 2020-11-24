package com.example.demo;
/*
import static org.mockito.Mockito.when;

import org.springframework.boot.test.autoconfigure.web.servlet.WebMvcTest;

import static org.mockito.ArgumentMatchers.any;
import static org.mockito.Mockito.any;
import static org.hamcrest.CoreMatchers.*;
import static org.hamcrest.Matchers.*;
import static org.hamcrest.Matchers.is;
import static org.junit.Assert.assertEquals;

import org.json.JSONObject;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.mockito.Mock;
import org.springframework.test.context.junit4.SpringRunner;
import org.springframework.test.web.servlet.MockMvc;
import org.springframework.boot.test.autoconfigure.web.servlet.WebMvcTest;
import org.springframework.boot.test.mock.mockito.MockBean;
import org.springframework.http.MediaType;

import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.get;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.post;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.status;

import java.util.ArrayList;
import java.util.List;

import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.jsonPath;


import com.example.demo.Controllers.MainController;
import com.example.demo.FriendCode.FriendRepository;
import com.example.demo.UserCode.UserRepository;


import com.example.demo.FriendCode.Friend;
import com.example.demo.UserCode.User;

import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;

@RunWith(SpringRunner.class)
@WebMvcTest(MainController.class)
public class MainControllerTest 
{

	@Autowired
	private MockMvc controller;
	
	@MockBean 
	private UserRepository userRepo;
	
	@MockBean
	private FriendRepository friendRepo;
	
	@Mock
	User u1;
	
	@Mock
	Friend f1;
	
	
	@Test
	public void testAddingUser() throws Exception{
		List<User> users = new ArrayList<User>();
		List<Friend> friends = new ArrayList<Friend>();
		
		when(userRepo.findAll()).thenReturn(users);
		
		when(userRepo.save((User)any(User.class)))
				.thenAnswer(x -> {
					User n = x.getArgument(0);
					users.add(n);
					return null;
				});
		
		when(friendRepo.save((Friend)any(Friend.class)))
		.thenAnswer(x -> {
			Friend n = x.getArgument(0);
			friends.add(n);
			return null;
		});
		
		JSONObject testObj = new JSONObject();
		testObj.put("username", "tommi");
		testObj.put("password", "idk");
		testObj.put("encryptionKey", "hey");
		
		when(userRepo.findByusername("tim")).thenReturn(u1);
		
		byte[] sendData = testObj.toString().getBytes();
		
		controller.perform(post("/addUser").contentType(MediaType.APPLICATION_JSON).content(sendData))
		.andExpect(status().isOk());
		
	}
	
	/*
	@Test
	public void testGetAll() throws Exception{
		List<User> users = new ArrayList<User>();
		when(u1.getUsername()).thenReturn("hello");
		
		users.add(u1);
		
		when(userRepo.findAll()).thenReturn(users);
		
		controller.perform(get("/all"))
		.andExpect(status().isOk());
		
	}
	
	

}
*/
