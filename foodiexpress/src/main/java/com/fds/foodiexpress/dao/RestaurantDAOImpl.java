package com.fds.foodiexpress.dao;

import java.util.List;
import java.util.Optional;

import org.springframework.stereotype.Repository;

import com.fds.foodiexpress.entity.Orders;
import com.fds.foodiexpress.entity.Customer;
import com.fds.foodiexpress.entity.Delivery;
import com.fds.foodiexpress.entity.FoodItems;
import com.fds.foodiexpress.entity.Restaurant;

import jakarta.persistence.EntityManager;
import jakarta.persistence.NoResultException;
import jakarta.persistence.TypedQuery;
import jakarta.transaction.Transactional; // Import Transactional

@Repository
public class RestaurantDAOImpl implements RestaurantDAO {
	private EntityManager entityManager;

    public RestaurantDAOImpl(EntityManager entityManager) {
        this.entityManager = entityManager;
    }

	@Override
	public List<Restaurant> findAllRestaurant() {
		TypedQuery<Restaurant> theQuery = entityManager.createQuery("select r From Restaurant r",Restaurant.class);

		// Return Query
		return theQuery.getResultList();
	}

	@Override
	public Optional<Restaurant> findByEmail(String email) {
		 try {
	            // Create JPQL query
	            TypedQuery<Restaurant> query = entityManager.createQuery(
	                "SELECT r FROM Restaurant r WHERE r.oEmail = :email", Restaurant.class); // Corrected alias to 'r'
	            
	            query.setParameter("email", email); // Bind the email parameter
	            
	            return Optional.ofNullable(query.getSingleResult()); // Return Optional
	        } catch (NoResultException e) {
	            return Optional.empty(); // Handle case where no restaurant is found
	        }
	}

	@Override
	public List<Orders> findOrdersByRestaurantNameAndTFlag(String restName, String tFlag) {
		TypedQuery<Orders> query = entityManager.createQuery(
				"SELECT o FROM Orders o WHERE o.rName = :restName AND o.tFlag = :tFlag", Orders.class);
		query.setParameter("restName", restName);
		query.setParameter("tFlag", tFlag);
		return query.getResultList();
	}

	@Override
	@Transactional // Mark this method as transactional
	public void updateOrderTFlag(int orderId, String newTFlag) {
		Orders order = entityManager.find(Orders.class, orderId);
		if (order != null) {
			order.settFlag(newTFlag);
			entityManager.merge(order); // Merge the updated order
		}
	}

	public void addFoodItem(FoodItems f) {
		entityManager.persist(f);
		
	}

}
