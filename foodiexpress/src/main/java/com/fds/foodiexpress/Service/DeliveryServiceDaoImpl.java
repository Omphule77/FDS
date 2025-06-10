package com.fds.foodiexpress.Service;

import org.springframework.stereotype.Service;
import com.fds.foodiexpress.dao.DeliveryDAO;
import com.fds.foodiexpress.entity.Delivery;

import jakarta.transaction.Transactional;

@Service
public class DeliveryServiceDaoImpl implements DeliveryServiceDAO {
    private final DeliveryDAO deliveryDao;

    public DeliveryServiceDaoImpl(DeliveryDAO deliveryDao) {
        this.deliveryDao = deliveryDao;
    }

    @Override
    public Delivery findById(int id) {
        Delivery result = deliveryDao.findById(id);
        if (result == null) {
            throw new RuntimeException("Did not find user with ID: " + id);
        }
        return result;
    }

    @Override
    public Delivery findByEmail(String email) {
        Delivery result = deliveryDao.findByEmail(email);
        if (result == null) {
            throw new RuntimeException("User not found for email: " + email);
        }
        return result;
    }
    
    @Override
    @Transactional
    public Delivery save(Delivery delivery) {
        return deliveryDao.save(delivery);
    }
}
