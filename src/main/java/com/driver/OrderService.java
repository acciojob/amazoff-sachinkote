package com.driver;

import java.util.List;

import org.springframework.stereotype.Service;

@Service
public class OrderService {

	OrderRepository repo=new OrderRepository();
	
	public void addOrder(Order order) {
		// TODO Auto-generated method stub
		repo.addOrder(order);
	}

	public void addPartner(String partnerId) {
		// TODO Auto-generated method stub
		repo.addPartner(partnerId);
	}

	public void addOrderPartnerPair(String orderId, String partnerId) {
		// TODO Auto-generated method stub
		repo.addOrderPartnerPair(orderId,partnerId);
		
	}

	public Order getOrderById(String orderId) {
		// TODO Auto-generated method stub
		return repo.getOrderById(orderId);
	}

	public DeliveryPartner getPartnerById(String partnerId) {
		// TODO Auto-generated method stub
		return repo.getPartnerById(partnerId); 
		
	}

	public Integer getOrderCountByPartnerId(String partnerId) {
		// TODO Auto-generated method stub
		return repo.getOrderCountByPartnerId(partnerId);
	}

	public List<String> getOrdersByPartnerId(String partnerId) {
		// TODO Auto-generated method stub
		
		return repo.getOrdersByPartnerId(partnerId);
	}

	public List<String> getAllOrders() {
		// TODO Auto-generated method stub
		
		return repo.getAllOrders();
	}

	public Integer getCountOfUnassignedOrders() {
		// TODO Auto-generated method stub
		return repo.getCountOfUnassignedOrders();
	}

	public String getLastDeliveryTimeByPartnerId(String partnerId) {
		// TODO Auto-generated method stub
		String time="";
		int t=repo.getLastDeliveryTimeByPartnerId(partnerId);
		int hr=t/60;
		int m=t%60;
		time=""+hr+":"+m;
		return time;
	}

	public void deletePartnerById(String partnerId) {
		// TODO Auto-generated method stub
		repo.deletePartnerById( partnerId);
		
	}

	public Integer getOrdersLeftAfterGivenTimeByPartnerId(String time, String partnerId) {
		// TODO Auto-generated method stub
		int ttime=0;
		String timehr=""+time.charAt(0)+time.charAt(1);
    	int timeinhr=Integer.parseInt(timehr);
    	String timemn=""+time.charAt(3)+time.charAt(4);
    	int timeinmn=Integer.parseInt(timemn);
    	ttime=timeinhr*60+timeinmn;
		return repo.getOrdersLeftAfterGivenTimeByPartnerId(ttime,partnerId);
	}

	public void deleteOrderById(String orderId) {
		// TODO Auto-generated method stub
		repo.deleteOrderById(orderId);
	}
}
