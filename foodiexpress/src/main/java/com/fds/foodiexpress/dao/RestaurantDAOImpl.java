package com.fds.foodiexpress.dao;

import java.util.List;

import org.springframework.stereotype.Repository;

import com.fds.foodiexpress.entity.Delivery;
import com.fds.foodiexpress.entity.Restaurant;

import jakarta.persistence.EntityManager;
import jakarta.persistence.TypedQuery;
@Repository
public class RestaurantDAOImpl implements RestaurantDAO {
	private final EntityManager entityManager;

    public RestaurantDAOImpl(EntityManager entityManager) {
        this.entityManager = entityManager;
    }

	@Override
	public List<Restaurant> findAllRestaurant() {
		TypedQuery theQuery = entityManager.createQuery("select r From Restaurant r",Restaurant.class);

		// Return Query
		return theQuery.getResultList();
	}

}
