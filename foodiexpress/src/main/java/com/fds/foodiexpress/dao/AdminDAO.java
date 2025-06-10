package com.fds.foodiexpress.dao;

import java.util.Map;

import com.fds.foodiexpress.entity.Admin;
import com.fds.foodiexpress.entity.Authorities;
import com.fds.foodiexpress.entity.Customer;
import com.fds.foodiexpress.entity.Delivery;
import com.fds.foodiexpress.entity.Restaurant;
import com.fds.foodiexpress.entity.Users;

public interface AdminDAO {
	
	Map<Customer, String> findAllCustomersWithFlag();
	Map<Delivery, String> findAllDeliveryPartnerWithFlag();
	Map<Restaurant, String> findAllRestaurantWithFlag();

	void toggleCustomerFlag(String email);
	
	Admin findByEmail(String email);
	void update(Admin admin);
	void add(Users user,Authorities authority,Admin add);
}
