package com.fds.foodiexpress.Service;

import java.util.Optional;

import org.springframework.stereotype.Service;

import com.fds.foodiexpress.dao.RestaurantDAO;
import com.fds.foodiexpress.entity.FoodItems;
import com.fds.foodiexpress.entity.Restaurant;

import jakarta.transaction.Transactional;

@Service
public class RestaurantServiceImp implements RestaurantService {
    private RestaurantDAO restaurantDAO;
    
    public RestaurantServiceImp(RestaurantDAO restaurantDAO) {
    	this.restaurantDAO=restaurantDAO;
    }

	@Override
	public Optional<Restaurant> findRByEmail(String email) {
		
		return restaurantDAO.findByEmail(email);
	}

	@Override
	@Transactional
	public void addFood(FoodItems f) {
		restaurantDAO.addFoodItem(f);
		
	}
    
    
}
