package com.example.springboot;

import java.util.Arrays;
import java.util.Random;
import java.util.Scanner;

import org.springframework.boot.CommandLineRunner;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.context.ApplicationContext;
import org.springframework.context.annotation.Bean;

@SpringBootApplication
public class Application {

	public static void main(String[] args) {
		SpringApplication.run(Application.class, args);
	}

	@Bean
	public CommandLineRunner commandLineRunner(ApplicationContext ctx) {
		return args -> {
//			String cont = "Y";
//			while(cont.equals("Y") || cont .equals("y")) {
//				Random rand = new Random();
//				int numbToGuess = rand.nextInt(100);
//				Scanner in = new Scanner(System.in);
//				
//				System.out.println("Guess a number between 0 and 100: ");
//				int number = in.nextInt();
//				String hOl = "";
//				while (number != numbToGuess) {
//					if (number > numbToGuess) {
//						hOl = "Lower ";
//					}
//					else {
//						hOl = "Higher ";
//					}
//					System.out.println(hOl + " Incorrect, try again: ");
//					number = in.nextInt();
//				}
//				
//				System.out.println("CONGRATS");
//				System.out.println("Play again? Y to continue: ");
//				cont = in.next();
//			}
//			
//				System.out.println("Goodbye");
//		
				
		};
	}

}