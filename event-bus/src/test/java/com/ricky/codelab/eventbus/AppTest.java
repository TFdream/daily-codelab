package com.ricky.codelab.eventbus;

import org.junit.Test;

import com.ricky.codelab.eventbus.annotation.Subscribe;

/**
 * Unit test for simple App.
 */
public class AppTest {
    
	@Test
	public void testEventBus(){
		
		EventBus eventBus = EventBus.getDefault();
		
		HelloEventListener listener = new HelloEventListener();
		
		eventBus.register(new HelloEventListener());
		
		eventBus.post(new HelloEvent(3, "ricky"));
		
		eventBus.unregister(listener);
	}
}

class HelloEventListener{

    @Subscribe
    public void listen(HelloEvent event) {
        System.out.println("receive msg:"+event.getMsg());
    }
}

class HelloEvent{
	private int id;
	private String msg;
	
	public HelloEvent(){
	}
	public HelloEvent(int id, String msg) {
		super();
		this.id = id;
		this.msg = msg;
	}

	public int getId() {
		return id;
	}
	public void setId(int id) {
		this.id = id;
	}
	public String getMsg() {
		return msg;
	}
	public void setMsg(String msg) {
		this.msg = msg;
	}
	
}


