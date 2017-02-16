package com.bytebeats.code;

public class Fibonacci {

	public static void main(String[] args) {

		int n = 20;
		System.out.println(sum(n));
	}

	public static long sum(int n) {
		long sum = 0;
		for (int i = 1; i <= n; i++) {
			sum += fib(i);
		}
		return sum;
	}

	public static long fib(int i) {
		if (i == 1 || i == 2) {
			return 1;
		}
		return fib(i - 1) + fib(i - 2);
	}
}
