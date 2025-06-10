package com.fds.foodiexpress.dao;

import java.util.LinkedHashMap;
import java.util.List;
import java.util.Map;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.stereotype.Repository;

import com.fds.foodiexpress.dao.AdminDAO;
import com.fds.foodiexpress.dao.CustomerDao;
import com.fds.foodiexpress.entity.Admin;
import com.fds.foodiexpress.entity.Authorities;
import com.fds.foodiexpress.entity.Customer;
import com.fds.foodiexpress.entity.Delivery;
import com.fds.foodiexpress.entity.Restaurant;
import com.fds.foodiexpress.entity.Users;

import jakarta.persistence.EntityManager;
import jakarta.persistence.PersistenceContext;
import jakarta.transaction.Transactional;

@Repository
public class AdminDAOImpl implements AdminDAO {

	private final CustomerDao customerDao;
	private final UserDAO userDao; // New dependency to query the user table
	private final DeliveryDAO deliveryDao;
	private final RestaurantDAO restaurantDao;

	@PersistenceContext
	private EntityManager entityManager;

	@Autowired
	public AdminDAOImpl(CustomerDao customerDao, UserDAO userDao,DeliveryDAO deliveryDao,RestaurantDAO restaurantDao) {
		this.customerDao = customerDao;
		this.userDao = userDao;
		this.deliveryDao=deliveryDao;
		this.restaurantDao=restaurantDao;
	}

	@Override
	public Map<Customer, String> findAllCustomersWithFlag() {
		List<Customer> customers = customerDao.findAllCustomer();
		Map<Customer, String> customerFlagMap = new LinkedHashMap<>();
		for (Customer customer : customers) {
			Users user = userDao.findByEmail(customer.getEmail());
			if (user != null) {

				int flagValue = Integer.parseInt(user.getEnabled());
				if (flagValue == 1) {
					customerFlagMap.put(customer, "active");
				} else if (flagValue == 0) {
					customerFlagMap.put(customer, "inactive");
				}

			} else {
				customerFlagMap.put(customer, "Invalid");
			}
		}
		return customerFlagMap;
	}
	@Override
	public Map<Delivery, String> findAllDeliveryPartnerWithFlag() {
		// Example Implementation:
		// Retrieve all customers and generate a dummy "flag" (for example purposes,
		// here we just set flag = 1 for every customer).
		List<Delivery> deliveryPartner = deliveryDao.findAllDeliveryPartner();
		Map<Delivery, String> deliveryPartnerFlagMap = new LinkedHashMap<>();
		for (Delivery partner : deliveryPartner) {
			Users user = userDao.findByEmail(partner.getEmail());
			if (user != null) {

				int flagValue = Integer.parseInt(user.getEnabled());
				if (flagValue == 1) {
					deliveryPartnerFlagMap.put(partner, "active");
				} else if (flagValue == 0) {
					deliveryPartnerFlagMap.put(partner, "inactive");
				}

			} else {
				// If no user found, you might decide to put a default flag (like 0) or skip.
				deliveryPartnerFlagMap.put(partner, "Invalid");
			}
		}
		return deliveryPartnerFlagMap;
	}
	
	@Override
	public Map<Restaurant, String> findAllRestaurantWithFlag() {
		// TODO Auto-generated method stub
		List<Restaurant> restaurants = restaurantDao.findAllRestaurant();
		Map<Restaurant, String> restaurantFlagMap = new LinkedHashMap<>();
		for (Restaurant restaurant : restaurants) {
			Users user = userDao.findByEmail(restaurant.getoEmail());
			if (user != null) {

				int flagValue = Integer.parseInt(user.getEnabled());
				if (flagValue == 1) {
					restaurantFlagMap.put(restaurant, "active");
				} else if (flagValue == 0) {
					restaurantFlagMap.put(restaurant, "inactive");
				}

			} else {
				// If no user found, you might decide to put a default flag (like 0) or skip.
				restaurantFlagMap.put(restaurant, "Invalid");
			}
		}
		return restaurantFlagMap;
	
	}
	@Override
	@Transactional
	public void toggleCustomerFlag(String email) {
		Users user = userDao.findByEmail(email);
		if (user != null) {
			int currentFlag = Integer.parseInt(user.getEnabled());
			if (currentFlag == 1) {
				user.setEnabled("0");
			} else if (currentFlag == 0) {
				user.setEnabled("1");
			}
			userDao.updateUser(user);
		}
	}

	@Override
	public Admin findByEmail(String email) {
		return entityManager.createQuery("SELECT a FROM Admin a WHERE a.email=:email",Admin.class)
				.setParameter("email", email).getSingleResult();
		}

	@Override
	@Transactional
	public void update(Admin admin) {
		Admin existingAdmin = entityManager.find(Admin.class, admin.getId());
		if (admin.getPassword() == null || admin.getPassword().isEmpty()) {
		    admin.setPassword(existingAdmin.getPassword()); // Keeping the old password
		}
		entityManager.merge(admin);
		
	}

	@Override
	public void add(Users user,Authorities authority,Admin admin) {
		entityManager.persist(user);
		entityManager.persist(authority);
		entityManager.persist(admin);
	}

}
