package com.fds.foodiexpress.dao;

import java.util.List;
import java.util.Optional;

import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Repository;

import com.fds.foodiexpress.entity.Authorities;
import com.fds.foodiexpress.entity.Customer;
import com.fds.foodiexpress.entity.Delivery;
import com.fds.foodiexpress.entity.Feedback;
import com.fds.foodiexpress.entity.FoodItems;
import com.fds.foodiexpress.entity.Orders;
import com.fds.foodiexpress.entity.Restaurant;
import com.fds.foodiexpress.entity.Users;

import jakarta.persistence.EntityManager;
import jakarta.persistence.NoResultException;
import jakarta.persistence.Query;
import jakarta.persistence.TypedQuery;
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
	
	@Override
	public void addOrder(Orders order) {
		
	        entityManager.persist(order);
	}

	@Override
	public List<FoodItems> findAll() {
		// write Query
		TypedQuery theQuery = entityManager.createQuery("select f From FoodItems f",FoodItems.class);

		// Return Query
		return theQuery.getResultList();
	}

	 @Override
	    public Optional<Customer> findCtm(String email) {
	        try {
	            // Create JPQL query
	            TypedQuery<Customer> query = entityManager.createQuery(
	                "SELECT c FROM Customer c WHERE c.email = :email", Customer.class);
	            
	            query.setParameter("email", email); // Bind the email parameter
	            
	            return Optional.ofNullable(query.getSingleResult()); // Return Optional
	        } catch (NoResultException e) {
	            return Optional.empty(); // Handle case where no customer is found
	        }
	    }

	@Override
	public Optional<FoodItems> findBurger() {
		 try {
	            // Create JPQL query
	            TypedQuery<FoodItems> query = entityManager.createQuery(
	                "SELECT f FROM FoodItems f WHERE f.type ='Burger'", FoodItems.class);
	            
	            return Optional.ofNullable(query.getSingleResult()); // Return Optional
	        } catch (NoResultException e) {
	            return Optional.empty(); // Handle case where no customer is found
	        }
	}

	@Override
	public Optional<FoodItems> findIce() {
		try {
            // Create JPQL query
            TypedQuery<FoodItems> query = entityManager.createQuery(
                "SELECT f FROM FoodItems f WHERE f.type ='IceCream'", FoodItems.class);
            
            return Optional.ofNullable(query.getSingleResult()); // Return Optional
        } catch (NoResultException e) {
            return Optional.empty(); // Handle case where no customer is found
        }
	}

	@Override
	public Optional<FoodItems> findCake() {
		try {
            // Create JPQL query
            TypedQuery<FoodItems> query = entityManager.createQuery(
                "SELECT f FROM FoodItems f WHERE f.type ='Cake'", FoodItems.class);
            
            return Optional.ofNullable(query.getSingleResult()); // Return Optional
        } catch (NoResultException e) {
            return Optional.empty(); // Handle case where no customer is found
        }
	}

	@Override
	public Optional<FoodItems> findFries() {
		try {
            // Create JPQL query
            TypedQuery<FoodItems> query = entityManager.createQuery(
                "SELECT f FROM FoodItems f WHERE f.type ='Fries'", FoodItems.class);
            
            return Optional.ofNullable(query.getSingleResult()); // Return Optional
        } catch (NoResultException e) {
            return Optional.empty(); // Handle case where no customer is found
        }
	}

	@Override
	public Optional<FoodItems> findBiryani() {
		try {
            // Create JPQL query
            TypedQuery<FoodItems> query = entityManager.createQuery(
                "SELECT f FROM FoodItems f WHERE f.type ='Biryani'", FoodItems.class);
            
            return Optional.ofNullable(query.getSingleResult()); // Return Optional
        } catch (NoResultException e) {
            return Optional.empty(); // Handle case where no customer is found
        }
	}

	@Override
	public Optional<FoodItems> findChicken() {
		try {
            // Create JPQL query
            TypedQuery<FoodItems> query = entityManager.createQuery(
                "SELECT f FROM FoodItems f WHERE f.type ='Chicken'", FoodItems.class);
            
            return Optional.ofNullable(query.getSingleResult()); // Return Optional
        } catch (NoResultException e) {
            return Optional.empty(); // Handle case where no customer is found
        }
	}

	@Override
	public Optional<FoodItems> findPizza() {
		try {
            // Create JPQL query
            TypedQuery<FoodItems> query = entityManager.createQuery(
                "SELECT f FROM FoodItems f WHERE f.type ='Pizza'", FoodItems.class);
            
            return Optional.ofNullable(query.getSingleResult()); // Return Optional
        } catch (NoResultException e) {
            return Optional.empty(); // Handle case where no customer is found
        }
	}

	@Override
	public Optional<FoodItems> findSandwitch() {
		try {
            // Create JPQL query
            TypedQuery<FoodItems> query = entityManager.createQuery(
                "SELECT f FROM FoodItems f WHERE f.type ='Sandwitch'", FoodItems.class);
            
            return Optional.ofNullable(query.getSingleResult()); // Return Optional
        } catch (NoResultException e) {
            return Optional.empty(); // Handle case where no customer is found
        }
	}

	@Override
	public FoodItems findById(int id) {
		
		return entityManager.find(FoodItems.class, id);
	}

	@Override
	public List<Orders> findctmCard(String email) {
		// write query
		TypedQuery<Orders> theQuery = entityManager.createQuery("SELECT o FROM Orders o WHERE o.cEmail = :email",
				Orders.class);
		// set parameter
		theQuery.setParameter("email", email);
		// return query
		return theQuery.getResultList();
	}

	@Override
	public void addCtmFeedback(Feedback f) {
		entityManager.persist(f);
	}
	

	@Override
	public List<Customer> findAllCustomer() {
		// write Query
		TypedQuery theQuery = entityManager.createQuery("select c From Customer c",Customer.class);

		// Return Query
		return theQuery.getResultList();
	}


}
