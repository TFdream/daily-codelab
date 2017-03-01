package com.ricky.codelab.eventbus;

import java.util.List;

public class SubscriberRegistry {

	public void register(Object listener) {
		
		Class<?> clazz = listener.getClass();
		System.out.println("register listener class:"+clazz.getName());
	}
	
	public void unregister(Object listener) {
		Class<?> clazz = listener.getClass();
		System.out.println("unregister listener class:"+clazz.getName());
	}

	public List<Subscriber> getSubscribers(Object event) {
		
		return null;
	}
}
