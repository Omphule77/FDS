package com.fds.foodiexpress.Service;

import java.util.List;
import java.util.Optional;

import org.springframework.stereotype.Service;

import com.fds.foodiexpress.dao.RestaurantDAO;
import com.fds.foodiexpress.entity.Orders;
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

	// This method was originally c_order and now specifically fetches pending orders
	@Override
	public List<Orders> findPendingOrders(String restName) {
		return restaurantDAO.findOrdersByRestaurantNameAndTFlag(restName, "0"); // "0" for pending
	}

	// New method to fetch completed orders
	@Override
	public List<Orders> findCompletedOrders(String restName) {
		return restaurantDAO.findOrdersByRestaurantNameAndTFlag(restName, "1"); // "1" for completed
	}

	@Override
	@Transactional
	public void markOrderAsCompleted(int orderId) {
		restaurantDAO.updateOrderTFlag(orderId, "1"); // Set tFlag to "1" for completed
	}
}