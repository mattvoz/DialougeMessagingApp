package com.example.springboot;

import org.springframework.web.bind.annotation.RestController;

import java.time.LocalDateTime;
import java.util.Random;

import org.springframework.web.bind.annotation.RequestMapping;

@RestController
public class HelloController {

	@RequestMapping("/")
	public String Description() {
		String Description = "This is the local date and time: " + LocalDateTime.now();
		Description += ("\n Enjoy a 'random' video    ");
		Random vid = new Random();
		int vidNum = Math.abs(vid.nextInt() %10);
		
		String[] vids = {
				"https://www.youtube.com/watch?v=dQw4w9WgXcQ", // rick
				"https://www.youtube.com/watch?v=L_jWHffIx5E", // smash mouth
				"https://www.youtube.com/watch?v=kJQP7kiw5Fk", // despacito
				"https://www.youtube.com/watch?v=9bZkp7q19f0", // gangam
				"https://www.youtube.com/watch?v=FTQbiNvZqaY", // Toto
				"https://www.youtube.com/watch?v=XfR9iY5y94s", //down under
				"https://www.youtube.com/watch?v=ghb6eDopW8I", // Mons and men
				"https://www.youtube.com/watch?v=K0tXhd7u56k", // dance till dead
				"https://www.youtube.com/watch?v=Gs069dndIYk", // september
				"https://www.youtube.com/watch?v=WhPvJOnHotE" // rasputin
	};
		
		
		
		
		
		return Description + vids[vidNum];
	}
	

}
