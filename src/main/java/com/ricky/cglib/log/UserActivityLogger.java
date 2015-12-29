package com.ricky.cglib.log;

public interface UserActivityLogger {
	
	public void login(String username, String password);
	public void logout(String username);
	public void signin(String email, String username, String password);
	public void browse(String username, String url);
}
