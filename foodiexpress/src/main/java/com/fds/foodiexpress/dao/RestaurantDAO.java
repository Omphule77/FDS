package com.fds.foodiexpress.dao;

import java.util.List;
import java.util.Optional;

import com.fds.foodiexpress.entity.Restaurant;


public interface RestaurantDAO {
	List<Restaurant> findAllRestaurant();
	Optional<Restaurant> findByEmail(String email);

}
