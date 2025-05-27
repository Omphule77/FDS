package com.fds.foodiexpress;

import org.springframework.boot.CommandLineRunner;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.context.annotation.Bean;

import com.fds.foodiexpress.dao.CustomerDao;
import com.fds.foodiexpress.entity.FoodItems;
import com.fds.foodiexpress.entity.Restaurant;
@SpringBootApplication
public class FoodiexpressApplication {

	public static void main(String[] args) {
		SpringApplication.run(FoodiexpressApplication.class, args);
	}
	

}
