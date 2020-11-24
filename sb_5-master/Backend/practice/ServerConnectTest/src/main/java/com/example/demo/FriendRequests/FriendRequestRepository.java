package com.example.demo.FriendRequests;

import java.util.List;

import org.springframework.data.jpa.repository.JpaRepository;


public interface FriendRequestRepository extends JpaRepository<FriendRequest,Long> 
{
	List<FriendRequest> findByReciever(String Reciever);
	void removeByreciever(String reciever);
}
