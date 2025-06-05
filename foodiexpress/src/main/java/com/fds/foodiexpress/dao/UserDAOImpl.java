package com.fds.foodiexpress.dao;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Repository;

import com.fds.foodiexpress.entity.Users;

import jakarta.persistence.EntityManager;
import jakarta.persistence.NoResultException;
import jakarta.persistence.PersistenceContext;

@Repository // Mark this class as a repository so Spring can manage it.
public class UserDAOImpl implements UserDAO {

	@PersistenceContext
	private EntityManager entityManager;

	@Autowired
	public UserDAOImpl(EntityManager entityManager) {
		this.entityManager = entityManager;
	}

	@Override
	public Users findByEmail(String email) {
		try {
			// Ensure that the entity name "Users" here exactly matches your entity class
			// name.
			return entityManager.createQuery("SELECT u FROM Users u WHERE u.username = :username", Users.class)
					.setParameter("username", email).getSingleResult();
		} catch (NoResultException e) {
			return null;
		}
	}

	@Override
	public void updateUser(Users user) {
		entityManager.merge(user);
	}
}
