package com.fds.foodiexpress.Service;

import org.springframework.stereotype.Service;

import com.fds.foodiexpress.dao.CustomerDao;
import com.fds.foodiexpress.entity.Customer;
import com.fds.foodiexpress.entity.Delivery;
import com.fds.foodiexpress.entity.Restaurant;

import jakarta.transaction.Transactional;

@Service
public class CustomerServiceImpl implements CustomerService {
	
	CustomerDao customerDao;
	public CustomerServiceImpl(CustomerDao customerDao) {
		this.customerDao=customerDao;
	}
	

	@Override
	@Transactional
	public void register(Customer customer) {
		customerDao.addCustomer(customer);
	}


	@Override
	@Transactional
	public void registerDelivery(Delivery delivery) {
		customerDao.addDelivery(delivery);
		
	}


	@Override
	@Transactional
	public void registerRestaurant(Restaurant restaurant) {
		customerDao.addRestaurant(restaurant);
		
	}

}
