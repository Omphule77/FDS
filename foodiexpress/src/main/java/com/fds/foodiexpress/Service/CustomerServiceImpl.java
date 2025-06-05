package com.fds.foodiexpress.Service;

import java.util.List;
import java.util.Optional;

import org.springframework.stereotype.Service;

import com.fds.foodiexpress.dao.CustomerDao;
import com.fds.foodiexpress.entity.Customer;
import com.fds.foodiexpress.entity.Delivery;
import com.fds.foodiexpress.entity.Feedback;
import com.fds.foodiexpress.entity.FoodItems;
import com.fds.foodiexpress.entity.Orders;
import com.fds.foodiexpress.entity.Restaurant;

import jakarta.transaction.Transactional;

@Service
public class CustomerServiceImpl implements CustomerService {
	
	CustomerDao customerDao;
	public CustomerServiceImpl(CustomerDao customerDao) {
		this.customerDao=customerDao;
	}
	

	@Override
	@Transactional
	public void register(Customer customer) {
		customerDao.addCustomer(customer);
	}
	
	@Override
	 @Transactional
	public void updateCustomer(Customer customer) {
	    customerDao.save(customer); // Save updated details
	}
	
	@Override
    @Transactional
    public void updateCustomerDetails(String email, String name, String address, String phone, String altPhone) {
        Customer customer = customerDao.findByEmail(email);

        if (customer != null) {
            if (name != null) customer.setName(name);
            if (address != null) customer.setAddress(address);
            if (phone != null) customer.setPhone(phone);
            if (altPhone != null) customer.setAltPhone(altPhone);

            customerDao.updateCustomer(customer);
//            customerDao.updateAuthoritiesTable(customer);
//            customerDao.updateAuthoritiesTable(customer);
        }
    }


	@Override
	@Transactional
	public void registerDelivery(Delivery delivery) {
		customerDao.addDelivery(delivery);
		
	}


	@Override
	@Transactional
	public void registerRestaurant(Restaurant restaurant) {
		customerDao.addRestaurant(restaurant);
		
	}


	@Override
	@Transactional
	public List<FoodItems> findAllFood() {
		
		return customerDao.findAll();
	}


	@Override
	@Transactional
	public Optional<Customer> customerFind(String email) {
		
		return customerDao.findCtm(email);
	}


	@Override
	public Optional<FoodItems> findAllBuger() {
		
		return customerDao.findBurger();
	}


	@Override
	public Optional<FoodItems> findAllIceCream() {
		// TODO Auto-generated method stub
		return customerDao.findIce();
	}


	@Override
	public Optional<FoodItems> findAllCake() {
		// TODO Auto-generated method stub
		return customerDao.findCake();
	}


	@Override
	public Optional<FoodItems> findAllFries() {
		// TODO Auto-generated method stub
		return customerDao.findFries();
	}


	@Override
	public Optional<FoodItems> findAllBiryani() {
		// TODO Auto-generated method stub
		return customerDao.findBiryani();
	}


	@Override
	public Optional<FoodItems> findAllChicken() {
		// TODO Auto-generated method stub
		return customerDao.findChicken();
	}


	@Override
	public Optional<FoodItems> findAllPizza() {
		// TODO Auto-generated method stub
		return customerDao.findPizza();
	}


	@Override
	public Optional<FoodItems> findAllSandwitch() {
		// TODO Auto-generated method stub
		return customerDao.findSandwitch();
	}


	@Override
	public FoodItems findFoodById(int id) {
		return customerDao.findById(id);
	}


	@Override
	@Transactional
	public void addctmorder(Orders order) {
		customerDao.addOrder(order);
	}


	@Override
	public List<Orders> findOrderCard(String email) {
		return customerDao.findctmCard(email);
	}


	@Override
	@Transactional
	public void addFeedback(Feedback f) {
		customerDao.addCtmFeedback(f);
	}


}
