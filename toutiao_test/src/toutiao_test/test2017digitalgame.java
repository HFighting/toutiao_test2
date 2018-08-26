package toutiao_test;

import java.util.Arrays;
import java.util.Scanner;
/*
 * 题目描述：
 * 给一个整形数组，包含n个数，找出无法从n个数中选取部分求和的数字中的最小数（从1开始）
 */
public class test2017digitalgame {

	static int[] flag = new int[1000];//存储和为定值的子串
	static int index = 0;//记录当前
	static boolean bool = false;//如果找到了，则返回true
	
	public static void main(String[] args) {
		Scanner sc = new Scanner(System.in);
		int size = sc.nextInt();
		int[] a = new int[size];
		for (int i = 0; i < size; i++) {
			a[i] = sc.nextInt();
		}
		sc.close();
		Arrays.sort(a);//使用该类中的该方法可以对数组进行排序
		for (int i = 1; i <= 1000; i++) {
			find(a, 0,a.length,i);
			if (bool) {//如果找到了继续找
				bool = false;
				continue;
			}else {
				System.out.println(i);
				return;
			}
		}
	}
	//回溯法
	public static void find(int[] a,int begin,int end,int num) {
		if (num == 0) {
			bool = true;
			return;
		}else if (num > 0) {
			for (int i = begin; i < end; i++) {
				int temp_num = num - a[i];
				if (temp_num>=0) {
					flag[index++] = a[i];
					find(a, i+1, end-1, temp_num);
				}else 
					break;
			}
		}
		index--;
	}
//	public static int[] sort(int[] a,int size) {
//		boolean flag = false;
//		for (int i = 0; i < size-1&&!flag; i++) {
//			flag = true;
//			for (int j = 1; j < size-i; j++) {
//				if (a[j-1]>a[j]) {
//					swap(j-1,j,a);
//					flag = false;
//				}
//			}
//			if (flag) {
//				return a;
//			}
//		}
//		return a;
//	}
//	public static void swap(int a,int b,int[] c) {
//		int temp = c[a];
//		c[a] =c[b];
//		c[b] = temp;
//	}

	
}
