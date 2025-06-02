package com.fds.foodiexpress.Service;

import com.fds.foodiexpress.entity.Delivery;

public interface DeliveryServiceDAO {
    Delivery findById(int id);
    Delivery findByEmail(String email);
    Delivery save(Delivery theDelivery);
    
}
