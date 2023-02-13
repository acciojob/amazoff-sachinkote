package com.driver;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import org.springframework.stereotype.Repository;

@Repository
public class OrderRepository {

	HashMap<String ,Order> orderMap=new HashMap<>();
	HashMap<String ,DeliveryPartner> partnerMap=new HashMap<>();
	HashMap<String ,List<String>> orderpartnerMap=new HashMap<>();
	HashMap<String ,Order>UnassignedOrderMap=new HashMap<>();
	
	public void addOrder(Order order) {
		// TODO Auto-generated method stub
		orderMap.put(order.getId(), order);
		UnassignedOrderMap.put(order.getId(), order);
	}

	public void addPartner(String partnerId) {
		// TODO Auto-generated method stub
		DeliveryPartner p=new DeliveryPartner(partnerId);
		
		partnerMap.put(partnerId,p );
		
	}

	public void addOrderPartnerPair(String orderId, String partnerId) {
		// TODO Auto-generated method stub
		if(orderpartnerMap.containsKey(partnerId))
		{
			List<String> l=orderpartnerMap.get(partnerId);
			l.add(orderId);
			UnassignedOrderMap.remove(orderId);
			orderpartnerMap.put(partnerId, l);
		}
		else
		{
			List<String>l=new ArrayList<>();
			l.add(orderId);
			orderpartnerMap.put(partnerId, l);
		}
	}

	public Order getOrderById(String orderId) {
		// TODO Auto-generated method stub
		return orderMap.get(orderId);
	}

	public DeliveryPartner getPartnerById(String partnerId) {
		// TODO Auto-generated method stub
		return partnerMap.get(partnerId);
	}

	public Integer getOrderCountByPartnerId(String partnerId) {
		// TODO Auto-generated method stub
		return orderpartnerMap.get(partnerId).size();
	}

	public List<String> getOrdersByPartnerId(String partnerId) {
		// TODO Auto-generated method stub
		return orderpartnerMap.get(partnerId);
	}

	public List<String> getAllOrders() {
		// TODO Auto-generated method stub
		List<String> l= new ArrayList<>();
		 for(Map.Entry m : orderMap.entrySet())
		 {
		    l.add((String) m.getKey());
		 }
		return l;
	}

	public Integer getOrdersLeftAfterGivenTimeByPartnerId(int time, String partnerId) {
		// TODO Auto-generated method stub
		int c=0;
		List<String> l=orderpartnerMap.get(partnerId);
		for( String  s:l)
		{
			Order order=orderMap.get(s);
		    if(order.getDeliveryTime()<time)
		    	c++;
		}
		return c;
	}

	public Integer getCountOfUnassignedOrders() {
		// TODO Auto-generated method stub
		
		return orderMap.size()-orderpartnerMap.size();
	}

	public int getLastDeliveryTimeByPartnerId(String partnerId) {
		// TODO Auto-generated method stub
		int c=0;
		List<String> l=orderpartnerMap.get(partnerId);
		for( String  s:l)
		{
			Order order=orderMap.get(s);
		    if(order.getDeliveryTime()>c)
		    	c=order.getDeliveryTime();
		}
		return c;
	}

	public void deletePartnerById(String partnerId) {
		// TODO Auto-generated method stub
		
		partnerMap.remove(partnerId);
	    List<String> l=orderpartnerMap.get(partnerId);
	    orderpartnerMap.remove(partnerId);
	    for(String s:l)
	    {
	    	UnassignedOrderMap.put(s,orderMap.get(s) );
	    }
		
	}

	public void deleteOrderById(String orderId) {
		// TODO Auto-generated method stub
		orderMap.remove(orderId);
		UnassignedOrderMap.remove(orderId);
		for(Map.Entry m : orderpartnerMap.entrySet())
		{
			List<String>l=(List<String>) m.getValue();
			
			if(l.contains(orderId))
			{
				l.remove(orderId);
			}
		}
	}
	
	
}
