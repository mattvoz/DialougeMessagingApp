package com.example.demo.messaging;

import java.util.List;

import org.springframework.data.jpa.repository.JpaRepository;

public interface MessageRepository extends JpaRepository<Message, Long>
{
	List<Message> findBydestination(String who);
	void removeBydestination(String who);
}
