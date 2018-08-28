package toutiao_test;

import java.util.ArrayList;
import java.util.List;
import java.util.Scanner;

/*
 * 输入一个字符串，把该字符串转换成字符数组，然后打印出该字符数组的所有子串
 */
public class PrintAllSubString {

	public static void main(String[] args) {
		Scanner sc = new Scanner(System.in);
		String string = sc.nextLine();
		char[] chars = string.toCharArray();
		sc.close();
		printall(chars);
	}
	public static void printall(char[] chars) {
		int count = (int)Math.pow(2, chars.length);//子串的数目是2^n-1
		List<ArrayList<Character>> allchar = new ArrayList<>();
		for (int i = 1; i < count; i++) {//从1开始遍历
			int temp = i;
			int index = 0;
			ArrayList<Character> substr = new ArrayList<>();
			while (temp!=0) {
				if ((temp&1)==1) {//找出该二进制中所有带1的位
					substr.add(chars[index]);//这些带1的位就是一个子串
				}
				index++;
				temp >>= 1;
			}
			allchar.add(substr);
		}	
		print(allchar);
	}	
	public static void print(List<ArrayList<Character>> allchar) {
		for (ArrayList<Character> arrayList : allchar) {
			for (Character character : arrayList) {
				System.out.print(character+" ");
			}
			System.out.println();
		}		
	}
}
