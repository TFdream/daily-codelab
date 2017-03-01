package com.ricky.codelab.eventbus;

public enum ThreadMode {
	
	/**
     * Subscriber will be called in the same thread, which is posting the event. This is the default. 
     */
	POSTING, 
	
	/**
     * Event handler methods are called in a separate thread. This is always independent from the posting thread.
     */
    ASYNC;
}
