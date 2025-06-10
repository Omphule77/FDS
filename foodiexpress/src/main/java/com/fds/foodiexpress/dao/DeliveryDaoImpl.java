package com.fds.foodiexpress.dao;

import java.util.List;

import org.springframework.stereotype.Repository;

import com.fds.foodiexpress.entity.Delivery;
import jakarta.persistence.EntityManager;
import jakarta.persistence.NoResultException;
import jakarta.persistence.TypedQuery;
import jakarta.transaction.Transactional;

@Repository
public class DeliveryDaoImpl implements DeliveryDAO {
    private final EntityManager entityManager;

    public DeliveryDaoImpl(EntityManager entityManager) {
        this.entityManager = entityManager;
    }

    @Override
    @Transactional
    public Delivery save(Delivery delivery) {
        return entityManager.merge(delivery);
    }

    @Override
    public Delivery findById(Integer id) {
        return entityManager.find(Delivery.class, id);
    }

    @Override
    public Delivery findByEmail(String email) {
        try {
            return entityManager.createQuery("SELECT d FROM Delivery d WHERE d.email = :email", Delivery.class)
                .setParameter("email", email)
                .getSingleResult();
        } catch (NoResultException e) {
            return null;
        }
    }
    @Override
    @Transactional
    public void updateUserTable(Delivery delivery) {
        entityManager.createQuery("UPDATE Users u SET u.username = :email, u.password = :password WHERE u.username = :oldEmail")
            .setParameter("email", delivery.getEmail())
            .setParameter("password", delivery.getPassword()) // Assuming password update is required
            .setParameter("oldEmail", delivery.getEmail()) // Keep username unchanged
            .executeUpdate();
    }

    @Override
    @Transactional
    public void updateAuthoritiesTable(Delivery delivery) {
        entityManager.createQuery("UPDATE Authorities a SET a.username = :email WHERE a.username = :oldEmail")
            .setParameter("email", delivery.getEmail())
            .setParameter("oldEmail", delivery.getEmail()) // Keep username unchanged
            .executeUpdate();
    }
    
    @Override
	public List<Delivery> findAllDeliveryPartner() {
		// write Query
		TypedQuery theQuery = entityManager.createQuery("select d From Delivery d",Delivery.class);

		// Return Query
		return theQuery.getResultList();
	}

}
