package com.example.demo.accessingdatamysql;

import org.springframework.data.repository.CrudRepository;

import com.example.demo.accessingdatamysql.User;

public interface UserRepository  extends CrudRepository<User, Integer>
{
	
}
