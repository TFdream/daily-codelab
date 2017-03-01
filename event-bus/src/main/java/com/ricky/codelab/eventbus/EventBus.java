package com.ricky.codelab.eventbus;

import java.util.List;
import java.util.concurrent.ExecutorService;
import java.util.concurrent.Executors;

public class EventBus {

	private static volatile EventBus defaultInstance;
	private final String identifier;
	private final ExecutorService executorService;
	private SubscriberRegistry subscriberRegistry = new SubscriberRegistry();
	
	public EventBus() {
		this("default");
	}

	public EventBus(String identifier) {
		this(identifier, Executors.newFixedThreadPool(Runtime.getRuntime().availableProcessors()));
	}

	public EventBus(String identifier, ExecutorService executorService) {
		this.identifier = identifier;
		this.executorService = executorService;
	}

	/**
	 * Singleton pattern
	 * @return
	 */
	public static EventBus getDefault() {
		if (defaultInstance == null) {
			synchronized (EventBus.class) {
				if (defaultInstance == null) {
					defaultInstance = new EventBus();
				}
			}
		}
		return defaultInstance;
	}

	public final String getIdentifier() {
		return identifier;
	}
	
	final ExecutorService getExecutor() {
		return executorService;
	}

	public void register(Object listener) {
		
		subscriberRegistry.register(listener);
	}

	public void post(Object event) {
		List<Subscriber> eventSubscribers = subscriberRegistry.getSubscribers(event);
		if(eventSubscribers!=null){
			for (Subscriber subscriber : eventSubscribers) {
				System.out.println(subscriber);
			}
		}
	}

	public void unregister(Object listener) {

		subscriberRegistry.unregister(listener);
	}

}
