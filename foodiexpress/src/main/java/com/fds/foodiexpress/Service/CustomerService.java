package com.fds.foodiexpress.Service;

import com.fds.foodiexpress.entity.Customer;
import com.fds.foodiexpress.entity.Delivery;
import com.fds.foodiexpress.entity.Restaurant;

public interface CustomerService {

	void register(Customer customer);
	void registerDelivery(Delivery delivery);
	void registerRestaurant(Restaurant restaurant);
}
