package toutiao_test;

import java.util.Scanner;

public class test2017Fibonacci {

	public static void main(String[] args) {
		Scanner sc = new Scanner(System.in);
		int a = sc.nextInt();
		sc.close();
		fibonacci(0, 1, a);
	}
	public static void fibonacci(int a,int b,int c) {
		int d = a + b;
		if (d>c) {
			if (c-b<d-c) {
				System.out.println(c-b);
			}else {
				System.out.println(d-c);
			}
			return; 
		}else {
			fibonacci(b, d, c);
		}
	}	
}
