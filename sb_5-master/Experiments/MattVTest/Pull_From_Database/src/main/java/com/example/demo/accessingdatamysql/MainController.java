package com.example.demo.accessingdatamysql;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.ResponseBody;

@Controller
@RequestMapping(path = "/users_database") 
public class MainController 
{
	@Autowired
	
	private UserRepository userRepository;
	
	@PostMapping(path = "/add")
	public @ResponseBody String addNewUser (@RequestParam String username, @RequestParam String password,@RequestParam int userType)
	{
		User n = new User();
		
		n.setUsername(username);
		n.setPassword(password);
		n.setUserType(userType);
		userRepository.save(n);
		return"Saved";
	}
	
	@GetMapping(path = "/all")
	public @ResponseBody Iterable<User> getAllUsers()
	{
		return userRepository.findAll();
	}
}
