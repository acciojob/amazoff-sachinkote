package com.driver;

public class Order {

    private String id;
    private int deliveryTime;

    public Order(String id, String deliveryTime) {

        // The deliveryTime has to converted from string to int and then stored in the attribute
        //deliveryTime  = HH*60 + MM
    	String timehr=""+deliveryTime.charAt(0)+deliveryTime.charAt(1);
    	int timeinhr=Integer.parseInt(timehr);
    	String timemn=""+deliveryTime.charAt(3)+deliveryTime.charAt(4);
    	int timeinmn=Integer.parseInt(timemn);
    	this.deliveryTime=timeinhr*60+timeinmn;
    	this.id=id;
    }

    public String getId() {
        return id;
    }

    public void setId(String id) {
		this.id = id;
	}

	public void setDeliveryTime(int deliveryTime) {
		this.deliveryTime = deliveryTime;
	}

	public int getDeliveryTime() {return deliveryTime;}
}
