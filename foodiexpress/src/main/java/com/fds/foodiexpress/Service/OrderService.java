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

    public List<Orders> getOrdersByFlagAndDEmail(String flag, String dEmail) {
        return orderDAO.findOrdersByFlagAndDEmail(flag, dEmail);
    }

    @Transactional
    public void updateOrderDetails(int orderId, String flag, String dEmail) {
        orderDAO.updateOrderDetails(orderId, flag, dEmail);
    }
    public List<Orders> getAvailableOrdersExcludingAgent(List<String> flags, String excludedEmail) {
        return orderDAO.getAvailableOrdersExcludingAgent(flags, excludedEmail);
    }

}
