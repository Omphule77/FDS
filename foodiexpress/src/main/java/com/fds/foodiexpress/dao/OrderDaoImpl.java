package com.fds.foodiexpress.dao;

import java.util.List;
import org.springframework.stereotype.Repository;
import com.fds.foodiexpress.entity.Orders;
import jakarta.persistence.EntityManager;
import jakarta.persistence.PersistenceContext;
import jakarta.transaction.Transactional;

@Repository
public class OrderDaoImpl implements OrderDao {
    
    @PersistenceContext
    private EntityManager entityManager;

    @Override
    public List<Orders> findOrdersByFlag(String flag) {
        return entityManager.createQuery("SELECT o FROM Orders o WHERE o.flag = :flag", Orders.class)
                .setParameter("flag", flag)
                .getResultList();
    }

    @Override
    @Transactional
    public void updateOrderFlag(int orderId, String flag) {
        Orders order = entityManager.find(Orders.class, orderId);
        if (order != null) {
            order.setFlag(flag);
            entityManager.merge(order);
        } else {
            throw new RuntimeException("Order ID " + orderId + " not found.");
        }
    }
}
