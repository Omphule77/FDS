package com.fds.foodiexpress.Service;

import java.util.Map;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.fds.foodiexpress.dao.AdminDAO;
import com.fds.foodiexpress.entity.Admin;
import com.fds.foodiexpress.entity.Customer;
import com.fds.foodiexpress.entity.Delivery;
import com.fds.foodiexpress.entity.Restaurant;

import jakarta.transaction.Transactional;

@Service
public class AdminServiceImpl implements AdminService {

	private final AdminDAO adminDAO;

	@Autowired
	public AdminServiceImpl(AdminDAO adminDao) {
		this.adminDAO = adminDao;
	}

	@Override
	public Map<Customer, String> findAllCustomersWithFlag() {
		return adminDAO.findAllCustomersWithFlag();
	}

	@Override
	public Map<Delivery, String> findAllDeliveryPartnerWithFlag() {
		return adminDAO.findAllDeliveryPartnerWithFlag();
	}
	
	@Override
	public Map<Restaurant, String> findAllRestaurantWithFlag() {
		// TODO Auto-generated method stub
		return adminDAO.findAllRestaurantWithFlag();
	}

	@Override
	@Transactional
	public void toggleCustomerFlag(String email) {
		adminDAO.toggleCustomerFlag(email);
	}

	@Override
	public Admin getAdminDetails(String email) {
		return adminDAO.findByEmail(email);
	}

	@Override
	public void updateAdmin(Admin admin) {
		adminDAO.update(admin);
		
	}

	

}
