package com.fds.foodiexpress.Service;

import java.util.Map;
import com.fds.foodiexpress.entity.Customer;
import com.fds.foodiexpress.entity.Delivery;
import com.fds.foodiexpress.entity.Restaurant;

public interface AdminService {

	// Corresponding service layer method that your controller can call.
	Map<Customer, String> findAllCustomersWithFlag();
	Map<Delivery, String> findAllDeliveryPartnerWithFlag();
	Map<Restaurant, String> findAllRestaurantWithFlag();


	void toggleCustomerFlag(String email);
}
