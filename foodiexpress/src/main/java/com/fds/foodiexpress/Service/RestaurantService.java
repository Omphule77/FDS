package com.fds.foodiexpress.Service;

import java.lang.foreign.Linker.Option;
import java.util.Optional;

import com.fds.foodiexpress.entity.FoodItems;
import com.fds.foodiexpress.entity.Restaurant;

public interface RestaurantService {
   Optional<Restaurant> findRByEmail(String email);
   void addFood(FoodItems f);
}
