package com.fds.foodiexpress.dao;

import java.util.List;

import com.fds.foodiexpress.entity.Restaurant;


public interface RestaurantDAO {
	List<Restaurant> findAllRestaurant();

}
