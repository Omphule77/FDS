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
	void save(Customer customer);
	void updateCustomer(Customer customer);
    Customer findByEmail(String email);
	Optional<Customer> findCtm(String email);
	Optional<List<FoodItems>> findBurger();
	Optional<List<FoodItems>> findIce();
	Optional<List<FoodItems>> findCake();
	Optional<List<FoodItems>> findFries();
	Optional<List<FoodItems>> findBiryani();
	Optional<List<FoodItems>> findChicken();
	Optional<List<FoodItems>> findPizza();
	Optional<List<FoodItems>> findSandwitch();
	FoodItems findById(int id);
	Orders findOById(int id);
	List<Orders> findctmCard(String email);
	
	
}
