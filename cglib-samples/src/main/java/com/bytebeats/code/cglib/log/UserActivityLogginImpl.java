package com.bytebeats.code.cglib.log;

/**
 * 需要被代理的类
 * @author Ricky Fung
 *
 */
public class UserActivityLogginImpl implements UserActivityLogger {

	@Override
	public void login(String username, String password) {
		System.out.println("login is executing, username:"+username+",password:"+password);
	}

	@Override
	public void logout(String username) {
		System.out.println("logout is executing, username:"+username);
	}

	@Override
	public void signin(String email, String username, String password) {
		System.out.println("signin is executing, email:"+email+",username:"+username+",password:"+password);
	}

	@Override
	public void browse(String username, String url) {
		System.out.println("login is executing, username:"+username+",url:"+url);
	}
	
}
