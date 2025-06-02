package com.fds.foodiexpress.dao;

import java.util.List;
import java.util.Optional;

import org.springframework.data.jpa.repository.JpaRepository;

import com.fds.foodiexpress.entity.Customer;
import com.fds.foodiexpress.entity.Delivery;
import com.fds.foodiexpress.entity.Feedback;
import com.fds.foodiexpress.entity.FoodItems;
import com.fds.foodiexpress.entity.Orders;
import com.fds.foodiexpress.entity.Restaurant;

public interface CustomerDao{

	void addCustomer(Customer customer);
	void addDelivery(Delivery delivery);
	void addOrder(Orders order);
	void addRestaurant(Restaurant restaurant);
	void addCtmFeedback(Feedback f);
	List<FoodItems> findAll();
	Optional<Customer> findCtm(String email);
	Optional<FoodItems> findBurger();
	Optional<FoodItems> findIce();
	Optional<FoodItems> findCake();
	Optional<FoodItems> findFries();
	Optional<FoodItems> findBiryani();
	Optional<FoodItems> findChicken();
	Optional<FoodItems> findPizza();
	Optional<FoodItems> findSandwitch();
	FoodItems findById(int id);
	List<Orders> findctmCard(String email);
}
