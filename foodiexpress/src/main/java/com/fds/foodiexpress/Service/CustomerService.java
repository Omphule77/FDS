package com.fds.foodiexpress.Service;

import java.util.List;
import java.util.Optional;

import com.fds.foodiexpress.entity.Customer;
import com.fds.foodiexpress.entity.Delivery;
import com.fds.foodiexpress.entity.FoodItems;
import com.fds.foodiexpress.entity.Restaurant;

public interface CustomerService {

	void register(Customer customer);
	void registerDelivery(Delivery delivery);
	void registerRestaurant(Restaurant restaurant);
	List<FoodItems> findAllFood();
	Optional<Customer> customerFind(String email);
	Optional<FoodItems> findAllBuger();
	Optional<FoodItems> findAllIceCream();
	Optional<FoodItems> findAllCake();
	Optional<FoodItems> findAllFries();
	Optional<FoodItems> findAllBiryani();
	Optional<FoodItems> findAllChicken();
	Optional<FoodItems> findAllPizza();
	Optional<FoodItems> findAllSandwitch();
	
}
