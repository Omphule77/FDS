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
    public List<Orders> findOrdersByFlagAndDEmail(String flag, String dEmail) {
        return entityManager.createQuery("SELECT o FROM Orders o WHERE o.flag = :flag AND o.dEmail = :dEmail", Orders.class)
                .setParameter("flag", flag)
                .setParameter("dEmail", dEmail)
                .getResultList();
    }

    @Override
    @Transactional
    public void updateOrderDetails(int orderId, String flag, String dEmail) {
        Orders order = entityManager.find(Orders.class, orderId);
        if (order != null) {
            order.setFlag(flag);
            order.setdEmail(dEmail); 
            entityManager.merge(order);
        } else {
            throw new RuntimeException("Order ID " + orderId + " not found.");
        }
    }
    @Override
    public List<Orders> getAvailableOrdersExcludingAgent(List<String> flags, String excludedEmail) {
        return entityManager.createQuery("SELECT o FROM Orders o WHERE o.flag IN :flags AND (o.dEmail IS NULL OR o.dEmail != :excludedEmail)", Orders.class)
                .setParameter("flags", flags)
                .setParameter("excludedEmail", excludedEmail)
                .getResultList();
    }
    @Override
    @Transactional
    public void updateOrderTFlag(int orderId, String newTFlag) {
    	entityManager.createQuery("UPDATE Orders o SET o.tFlag = :newTFlag WHERE o.orderId = :orderId")
        .setParameter("newTFlag", newTFlag)
        .setParameter("orderId", orderId)
        .executeUpdate();
    }
    @Override
    public Orders findOrderById(int orderId) {
        return entityManager.find(Orders.class, orderId);
    }
    




}
