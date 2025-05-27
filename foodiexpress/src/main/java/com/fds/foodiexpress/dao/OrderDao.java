package com.fds.foodiexpress.dao;

import java.util.List;
import com.fds.foodiexpress.entity.Orders;

public interface OrderDao {
    List<Orders> findOrdersByFlag(String flag);
    void updateOrderFlag(int orderId, String flag);
}
