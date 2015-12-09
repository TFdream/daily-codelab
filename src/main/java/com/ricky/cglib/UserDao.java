package com.ricky.cglib;

/**
 * 需要被代理的类
 * @author Ricky Fung
 *
 */
public class UserDao {
	
	public long insert() {
		System.out.println("insert() is executing !");
		return 1;
	}

	public void query() {
		System.out.println("query() is executing !");
	}

	public int update() {
		System.out.println("update() is executing !");
		return 1;
	}

	public int delete() {
		System.out.println("delete() is executing !");
		return 1;
	}
	
}
