package com.fds.foodiexpress.dao;

import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Repository;

import com.fds.foodiexpress.entity.Authorities;
import com.fds.foodiexpress.entity.Customer;
import com.fds.foodiexpress.entity.Delivery;
import com.fds.foodiexpress.entity.Restaurant;
import com.fds.foodiexpress.entity.Users;

import jakarta.persistence.EntityManager;
import jakarta.transaction.Transactional;

@Repository
public class CustomerDaoImpl implements CustomerDao {

	private EntityManager entityManager;
	private PasswordEncoder passwordEncoder;
	 
	public CustomerDaoImpl(EntityManager em,PasswordEncoder passwordEncoder ) {
		entityManager=em;
		this.passwordEncoder = passwordEncoder;
	}
	
	@Override
	public void addCustomer(Customer customer) {
		Users user = new Users();
		Authorities authorities = new Authorities();
		
		customer.setPassword(passwordEncoder.encode(customer.getPassword()));
		user.setUsername(customer.getEmail());
		user.setPassword(customer.getPassword());
		user.setEnabled("1");
		
		authorities.setAuthority("ROLE_CUSTOMER");
		authorities.setUsername(customer.getEmail());
		
		entityManager.persist(user);
		entityManager.persist(authorities);
		entityManager.persist(customer);
	}

	@Override
	public void addDelivery(Delivery delivery) {
		Users user = new Users();
		Authorities authorities = new Authorities();
		
		delivery.setPassword(passwordEncoder.encode(delivery.getPassword()));
		user.setUsername(delivery.getEmail());
		user.setPassword(delivery.getPassword());
		user.setEnabled("1");
		
		authorities.setAuthority("ROLE_DELIVERY");
		authorities.setUsername(delivery.getEmail());
		
		entityManager.persist(user);
		entityManager.persist(authorities);
		entityManager.persist(delivery);
			
	}

	@Override
	public void addRestaurant(Restaurant restaurant) {
		Users user = new Users();
		Authorities authorities = new Authorities();
		
		restaurant.setPassword(passwordEncoder.encode(restaurant.getPassword()));
		user.setUsername(restaurant.getoEmail());
		user.setPassword(restaurant.getPassword());
		user.setEnabled("1");
		
		authorities.setAuthority("ROLE_RESTAURANT");
		authorities.setUsername(restaurant.getoEmail());
		
		entityManager.persist(user);
		entityManager.persist(authorities);
		entityManager.persist(restaurant);
		
	}

}
