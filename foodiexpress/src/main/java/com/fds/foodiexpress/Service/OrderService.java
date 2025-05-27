package com.fds.foodiexpress.Service;

import java.util.List;
import org.springframework.stereotype.Service;
import com.fds.foodiexpress.dao.OrderDao;
import com.fds.foodiexpress.entity.Orders;
import jakarta.transaction.Transactional;

@Service
public class OrderService {
    private final OrderDao orderDAO;

    public OrderService(OrderDao orderDAO) {
        this.orderDAO = orderDAO;
    }

    public List<Orders> getOrdersByFlag(String flag) {
        return orderDAO.findOrdersByFlag(flag);
    }

    @Transactional
    public void updateOrderStatus(int orderId, String flag) {
        orderDAO.updateOrderFlag(orderId, flag);
    }
}
