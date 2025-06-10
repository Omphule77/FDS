package com.fds.foodiexpress.dao;

import java.util.List;
import java.util.Optional;

import com.fds.foodiexpress.entity.Orders;
import com.fds.foodiexpress.entity.Restaurant;


public interface RestaurantDAO {
	List<Restaurant> findAllRestaurant();
	Optional<Restaurant> findByEmail(String email);
	List<Orders> findOrdersByRestaurantNameAndTFlag(String restName, String tFlag); // New method
	void updateOrderTFlag(int orderId, String newTFlag); // New method
}