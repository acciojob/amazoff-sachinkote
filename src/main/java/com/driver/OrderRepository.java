package com.driver;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.HashSet;
import java.util.Iterator;
import java.util.List;
import java.util.Map;

import org.springframework.stereotype.Repository;

@Repository
public class OrderRepository {

	HashMap<String ,Order> orderMap=new HashMap<>();
	HashMap<String ,DeliveryPartner> partnerMap=new HashMap<>();
	HashMap<String ,String> orderpartnerMap=new HashMap<>();
	HashMap<String ,HashSet<String> >partnerOrderMap=new HashMap<>();
	
	public void addOrder(Order order) {
		// TODO Auto-generated method stub
		orderMap.put(order.getId(), order);
		
	}

	public void addPartner(String partnerId) {
		// TODO Auto-generated method stub
		DeliveryPartner p=new DeliveryPartner(partnerId);
		p.setId(partnerId);
		partnerMap.put(partnerId,p );
		
	}

	public void addOrderPartnerPair(String orderId, String partnerId) {
		// TODO Auto-generated method stub
		orderpartnerMap.put(orderId, partnerId);
		if(partnerOrderMap.containsKey(partnerId))
		{
			HashSet<String> l=partnerOrderMap.get(partnerId);
			l.add(orderId);
			DeliveryPartner p=partnerMap.get(partnerId);
			p.setNumberOfOrders(l.size());
			partnerOrderMap.put(partnerId, l);
		}
		else
		{
			HashSet<String>l=new HashSet<>();
			l.add(orderId);
			partnerOrderMap.put(partnerId, l);
			DeliveryPartner p=partnerMap.get(partnerId);
			p.setNumberOfOrders(1);
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
		
		 return partnerOrderMap.get(partnerId).size();
	}

	public List<String> getOrdersByPartnerId(String partnerId) {
		// TODO Auto-generated method stub
		
		List<String> l=(List<String>) partnerOrderMap.get(partnerId);
		
		return l;
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

	public Integer getOrdersLeftAfterGivenTimeByPartnerId(String time, String partnerId) {
		// TODO Auto-generated method stub
	    Integer h=Integer.valueOf(time.substring(0, 2));
		Integer m=Integer.valueOf(time.substring(3));
		Integer t=h*60+m;
		
		Integer c=0;
		if(partnerOrderMap.containsKey(partnerId))
		{
		HashSet<String> orders=partnerOrderMap.get(partnerId);
		for(String o:orders)
		{
			if(orderMap.containsKey(o))
			{
				Order currorder=orderMap.get(o);
				if(t<currorder.getDeliveryTime())
				{
					c++;
				}
			}
		}
			
			
		}
		return c;
	}

	public Integer getCountOfUnassignedOrders() {
		// TODO Auto-generated method stub
		
		return orderMap.size()-orderpartnerMap.size();
	}

	public String getLastDeliveryTimeByPartnerId(String partnerId) {
		// TODO Auto-generated method stub
		int t=0;
		if(partnerOrderMap.containsKey(partnerId))
		{
			
		HashSet<String> orders=partnerOrderMap.get(partnerId);
		for(String o:orders)
		{
			if(orderMap.containsKey(o))
			{
				Order currorder=orderMap.get(o);
				if(t<currorder.getDeliveryTime())
				{
					t=currorder.getDeliveryTime();
				}
			}
		}
			
			
		}
		 Integer h=t/60;
		 Integer m=t%60;
		 String hr=String.valueOf(h);
		 String mn=String.valueOf(m);
		 if(hr.length()==1)
			 hr="0"+hr;
		 if(mn.length()==1)
			 mn="0"+mn;
		 
		 
		
		return hr+":"+mn;
	}

	public void deletePartnerById(String partnerId) {
		// TODO Auto-generated method stub
		
		partnerMap.remove(partnerId);
		Iterator<Map.Entry<String, String>> itr = orderpartnerMap.entrySet().iterator();
        
        while(itr.hasNext())
        {
             Map.Entry<String, String> entry = itr.next();
            if(entry.getValue().equals(partnerId))
            {
            	orderpartnerMap.remove(entry.getKey());
            }
        }
        partnerOrderMap.remove(partnerId);
	}

	public void deleteOrderById(String orderId) {
		// TODO Auto-generated method stub
		
		String partnerId=orderpartnerMap.get(orderId);
		HashSet<String> orders=partnerOrderMap.get(partnerId);
		orders.remove(orderId);
		DeliveryPartner partner=partnerMap.get(partnerId);
		partner.setNumberOfOrders(orders.size());
		orderpartnerMap.remove(orderId);
		if(orderMap.containsKey(orderId))
		{
			orderMap.remove(orderId);
		}
		
		}
	}
	
	

