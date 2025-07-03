package com.fds.foodiexpress.Service;

import java.util.List;
import java.util.Optional;

import com.fds.foodiexpress.entity.Orders;
import com.fds.foodiexpress.entity.FoodItems;
import com.fds.foodiexpress.entity.Restaurant;

public interface RestaurantService {
   Optional<Restaurant> findRByEmail(String email);
List<Orders> findPendingOrders(String restName);

List<Orders> findCompletedOrders(String restName);

void markOrderAsCompleted(int orderId);
   void addFood(FoodItems f);
}
