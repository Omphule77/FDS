package com.fds.foodiexpress.dao;

import org.springframework.stereotype.Repository;

import com.fds.foodiexpress.entity.Delivery;

import jakarta.persistence.EntityManager;
import jakarta.transaction.Transactional;

@Repository
public class DeliveryDaoImpl implements DeliveryDAO {
	private EntityManager entityManager;
	
	public DeliveryDaoImpl(EntityManager entityManager) {
		this.entityManager=entityManager;
	}

	@Override
	@Transactional
	public void update(Delivery delivery) {
		// TODO Auto-generated method stub
		entityManager.merge(delivery);
		
	}

}
