package com.fds.foodiexpress.Service;

import org.springframework.stereotype.Service;

import com.fds.foodiexpress.dao.DeliveryDAO;
import com.fds.foodiexpress.entity.Delivery;

@Service
public class DeliveryServiceDaoImpl implements DeliveryServiceDAO{
	
	private DeliveryDAO deliveryDao;
	
	public DeliveryServiceDaoImpl(DeliveryDAO deliveryDao) {
		this.deliveryDao=deliveryDao;
	}

	@Override
	public void change(Delivery delivery) {
		// TODO Auto-generated method stub
		deliveryDao.update(delivery);
		
	}

}
