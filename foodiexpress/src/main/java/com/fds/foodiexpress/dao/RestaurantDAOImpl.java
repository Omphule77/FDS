package com.fds.foodiexpress.dao;

import java.util.List;
import java.util.Optional;

import org.springframework.stereotype.Repository;

import com.fds.foodiexpress.entity.Customer;
import com.fds.foodiexpress.entity.Delivery;
import com.fds.foodiexpress.entity.FoodItems;
import com.fds.foodiexpress.entity.Restaurant;

import jakarta.persistence.EntityManager;
import jakarta.persistence.NoResultException;
import jakarta.persistence.TypedQuery;
@Repository
public class RestaurantDAOImpl implements RestaurantDAO {
	private EntityManager entityManager;

    public RestaurantDAOImpl(EntityManager entityManager) {
        this.entityManager = entityManager;
    }

	@Override
	public List<Restaurant> findAllRestaurant() {
		TypedQuery theQuery = entityManager.createQuery("select r From Restaurant r",Restaurant.class);

		// Return Query
		return theQuery.getResultList();
	}

	@Override
	public Optional<Restaurant> findByEmail(String email) {
		 try {
	            // Create JPQL query
	            TypedQuery<Restaurant> query = entityManager.createQuery(
	                "SELECT c FROM Restaurant c WHERE c.oEmail = :email", Restaurant.class);
	            
	            query.setParameter("email", email); // Bind the email parameter
	            
	            return Optional.ofNullable(query.getSingleResult()); // Return Optional
	        } catch (NoResultException e) {
	            return Optional.empty(); // Handle case where no customer is found
	        }
	}

	@Override
	public void addFoodItem(FoodItems f) {
		entityManager.persist(f);
		
	}

}
