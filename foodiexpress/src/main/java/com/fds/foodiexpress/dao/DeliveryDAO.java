package com.fds.foodiexpress.dao;

import java.util.List;

import com.fds.foodiexpress.entity.Customer;
import com.fds.foodiexpress.entity.Delivery;

public interface DeliveryDAO {
    Delivery save(Delivery delivery);
    Delivery findById(Integer id);
    Delivery findByEmail(String email);
    void updateUserTable(Delivery delivery);
    void updateAuthoritiesTable(Delivery delivery);
	List<Delivery> findAllDeliveryPartner();
}
