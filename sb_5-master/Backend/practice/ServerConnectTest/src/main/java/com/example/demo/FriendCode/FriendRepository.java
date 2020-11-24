package com.example.demo.FriendCode;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.stereotype.Repository;
import org.springframework.data.repository.query.Param;


public interface FriendRepository extends JpaRepository<Friend, Integer>
{
	Friend findByfriendName(String FriendName);
	Friend findBydecipher(String Decipher);
}
