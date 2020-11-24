package com.example.demo.Controllers;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import com.example.demo.FriendCode.Friend;
import com.example.demo.FriendCode.FriendRepository;
import com.example.demo.UserCode.User;
import com.example.demo.UserCode.UserRepository;


@RestController
public class MainController
{
	@Autowired
	private UserRepository userRepository;
	
	@Autowired
	private FriendRepository friendsRepository;
	
	@PostMapping("/add")
	public @ResponseBody String addNewUser (@RequestParam String username, @RequestParam String password, @RequestParam String encryption)
	{
		User n = new User();
		
		n.setUsername(username);
		n.setPassword(password);
		n.setEncryption(encryption);
		
		userRepository.save(n);
		return "Saved";
	}
	
	@PostMapping("/addUser")
	public @ResponseBody String addNewUserMade (@RequestBody User n)
	{
		if(userRepository.findByusername(n.getUsername()) != null)
			{
				return "Username already taken";
			}
		userRepository.save(n);
		
		Friend F = new Friend();
		F.setFriendName(n.getUsername());
		F.setDecipher(n.getEncryptionKey());
		friendsRepository.save(F);
		
		return "Saved";
	}
	
	
	
	@GetMapping("/all")
	public List<User> getAllUsers()
	{
		return userRepository.findAll();
	}
	
	
	@GetMapping(path = "/user/{username}/{password}")
	public @ResponseBody User getUser(@PathVariable String username, @PathVariable String password)
	{
		try {
		User n = userRepository.findByusername(username);
		if( n.getUsername().equals(username) && n.getPassword().equals(password) )
		{
			return n;
		}
		
		return null;
		}
		catch(NullPointerException n)
		{
			return null;
		}
		
	}
	
}

