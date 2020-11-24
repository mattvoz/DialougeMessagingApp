package com.example.demo.UserCode;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.stereotype.Repository;

import com.example.demo.FriendCode.FriendsList;
import com.example.demo.FriendCode.Friend;

import org.springframework.data.repository.query.Param;


public interface UserRepository extends JpaRepository<User, Integer>
{
	User findByusername(String username);
}

