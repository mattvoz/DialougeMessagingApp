package com.example.demo.Controllers;

import java.util.List;
import java.util.Set;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import com.example.demo.FriendCode.Friend;
import com.example.demo.FriendCode.FriendRepository;
import com.example.demo.UserCode.User;
import com.example.demo.UserCode.UserRepository;

import io.swagger.annotations.Api;

@Api (value = "FriendController")
@RestController
public class FriendController 
{
	@Autowired
	private UserRepository userRepository;
	
	@Autowired
	private FriendRepository friendsRepository;
	
	@GetMapping(value = "/friends")
	public List<Friend> getAllFriends()
	{
		return friendsRepository.findAll();
	}
	
	@GetMapping("/{username}/friends")
	public Object[] getFriends(@PathVariable String username)
	{
		User n = userRepository.findByusername(username);
		return n.getFriendsArray();
	}
	
	@PostMapping("/{username}/addFriendByCipher/{cipher}")
	public String addFriendByCipher(@PathVariable String username, @PathVariable String cipher)
	{
		User n = userRepository.findByusername(username);
		
		Friend f = friendsRepository.findBydecipher(cipher);
		
		if(f == null)
		{
			return "sorry that user doesn't exist";
		}
		
		if(n.getFriendsSet().contains(f))
		{
			return "this user is already on your friends list";
		}
		
		Friend f2 = friendsRepository.findByfriendName(n.getUsername());
		User n2 = userRepository.findByusername(f.getFriendName());
		
		n2.addFriend(f2);
		n.addFriend(f);
		
		
		
		userRepository.save(n);
		userRepository.save(n2);
		
		return "Friend added";
		
	}
	
	
	@PostMapping("/{username}/addFriendByName/{friendname}")
	public String addFriendByUserName(@PathVariable String username, @PathVariable String friendname)
	{
		User n = userRepository.findByusername(username);
		
		Friend f = friendsRepository.findByfriendName(friendname);
		
		if(f == null)
		{
			return "sorry that user doesn't exist";
		}
		
		if(n.getFriendsSet().contains(f))
		{
			return "this user is already on your friends list";
		}
		
		Friend f2 = friendsRepository.findByfriendName(n.getUsername());
		User n2 = userRepository.findByusername(f.getFriendName());
		
		String success = n.addFriend(f);
		n2.addFriend(f2);
		
		
		userRepository.save(n);
		userRepository.save(n2);
		
		return(success);
		
	}
	
	@GetMapping("/findFriend/{friendName}")
	public Friend findByFriendName(@PathVariable String friendName)
	{
		Friend f = friendsRepository.findByfriendName(friendName);
		if(f == null)
		{
			return null;
		}
		
		return f;
	}
	
	@GetMapping("/friends/allFriends")
	public List<Friend> findAll()
	{
		return friendsRepository.findAll();
	}
	
	

}
