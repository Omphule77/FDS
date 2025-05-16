package com.fds.foodiexpress.dao;

import org.springframework.data.jpa.repository.JpaRepository;

import com.fds.foodiexpress.entity.Customer;
import com.fds.foodiexpress.entity.Delivery;
import com.fds.foodiexpress.entity.Restaurant;

public interface CustomerDao{

	void addCustomer(Customer customer);
	void addDelivery(Delivery delivery);
	void addRestaurant(Restaurant restaurant);
}
