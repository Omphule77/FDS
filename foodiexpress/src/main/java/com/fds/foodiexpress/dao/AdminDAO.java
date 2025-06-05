package com.fds.foodiexpress.dao;

import java.util.Map;

import com.fds.foodiexpress.entity.Customer;
import com.fds.foodiexpress.entity.Delivery;
import com.fds.foodiexpress.entity.Restaurant;

public interface AdminDAO {
	// Example method: return a map of customers with a flag count or some
	// admin-specific metric.
	Map<Customer, String> findAllCustomersWithFlag();
	Map<Delivery, String> findAllDeliveryPartnerWithFlag();
	Map<Restaurant, String> findAllRestaurantWithFlag();

	void toggleCustomerFlag(String email);
}
