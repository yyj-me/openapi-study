package com.test;

public class TestRecur {

	public static void main(String[] args) {
		System.out.println(recur(5));
	}

	public static int recur(int n) {
		if (n <= 1) return 1;
		int sum = n + recur(n-1);
		System.out.println(sum/2);
		return sum/2;
	}
}
