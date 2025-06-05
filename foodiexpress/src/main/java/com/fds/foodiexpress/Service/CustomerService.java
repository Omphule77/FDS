package com.fds.foodiexpress.Service;

import java.util.List;
import java.util.Optional;

import com.fds.foodiexpress.entity.Customer;
import com.fds.foodiexpress.entity.Delivery;
import com.fds.foodiexpress.entity.Feedback;
import com.fds.foodiexpress.entity.FoodItems;
import com.fds.foodiexpress.entity.Orders;
import com.fds.foodiexpress.entity.Restaurant;

public interface CustomerService {

	void register(Customer customer);
	void registerDelivery(Delivery delivery);
	void registerRestaurant(Restaurant restaurant);
	void addctmorder(Orders order);
	void addFeedback(Feedback f);
	void updateCustomer(Customer customer);
    void updateCustomerDetails( String name, String address, String phone, String altPhone, String email);
	List<FoodItems> findAllFood();
	Optional<Customer> customerFind(String email);
	Optional<List<FoodItems>> findAllBuger();
	Optional<List<FoodItems>> findAllIceCream();
	Optional<List<FoodItems>> findAllCake();
	Optional<List<FoodItems>> findAllFries();
	Optional<List<FoodItems>> findAllBiryani();
	Optional<List<FoodItems>> findAllChicken();
	Optional<List<FoodItems>> findAllPizza();
	Optional<List<FoodItems>> findAllSandwitch();
	FoodItems findFoodById(int id);
	Orders findOrderById(int id);
	List<Orders> findOrderCard(String email);
	List<Customer> findAllCustomer();
	
	
}
