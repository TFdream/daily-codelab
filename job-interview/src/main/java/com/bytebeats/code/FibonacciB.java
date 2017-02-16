package com.bytebeats.code;

public class FibonacciB {

	public static void main(String[] args) {
		
		System.out.println(sum(40));
	}

	public static int sum(int n) {
		
		int a = 1, b = 1, c = 0;
		System.out.println("斐波那契数列前"+n+"项为：");
		System.out.print(a + "\t" + b + "\t");
		
		int sum = a+b;
		for (int i = 3; i <= n; i++) {
			c = a + b;
			a = b;
			b = c;
			System.out.print(c + "\t");
			sum += c;
		}
		
		return sum;
	}

}
