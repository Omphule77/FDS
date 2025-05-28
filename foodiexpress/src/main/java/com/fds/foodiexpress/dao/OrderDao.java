package com.fds.foodiexpress.dao;

import java.util.List;
import com.fds.foodiexpress.entity.Orders;

public interface OrderDao {
    List<Orders> findOrdersByFlag(String flag);
    List<Orders> findOrdersByFlagAndDEmail(String flag, String dEmail); 
    void updateOrderDetails(int orderId, String flag, String dEmail); 
    List<Orders> getAvailableOrdersExcludingAgent(List<String> flags, String excludedEmail);
}
