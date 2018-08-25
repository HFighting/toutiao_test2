package toutiao_test;
/*
A = “aba”，B = “b”。这里有4种把B插入A的办法：
在A的第一个字母之前: "baba" 不是回文 
在第一个字母‘a’之后: "abba" 是回文 
在字母‘b’之后: "abba" 是回文 
在第二个字母'a'之后 "abab" 不是回文 
所以满足条件的答案为2
输入描述:
每组输入数据共两行。
第一行为字符串A
第二行为字符串B
字符串长度均小于100且只包含小写字母

aba
b
 */
import java.util.Scanner;

public class test2017duichengString {

	public static void main(String[] args) {
		Scanner sc = new Scanner(System.in);
		String str1 = sc.nextLine();
		String str2 = sc.nextLine();
		sc.close();
		int size1 = str1.length();
		int size2 = str2.length();
		char[] str11 = str1.toCharArray();
		char[] str22 = str2.toCharArray();
		char[] chars = new char[size1+size2];
		int count = 0;
		//以a的长度为基础，包括末尾
		for (int i = 0; i <= size1; i++) {
			int k = i;//k表示插入的点
			//k之前的都插入a字符串,不包括k
			for (int j = 0; j < k; j++) {
				chars[j] = str11[j];
			}
			//k之后包括k插入b字符串
			for (int j = k, m =0; j < k+size2; j++) {
				chars[j] = str22[m++];
			}
			//剩余的全部插入a没插入完的字符串
			for (int j = k+size2; j < chars.length; j++) {
				chars[j] = str11[k++];
			}
			//插入完后判断新形成的字符数组是否是对称数组
			boolean flag = true;
			for (int j = 0,m=chars.length-1; j < m; j++,m--) {
				if (chars[j]==chars[m]) {
					continue;
				}else {
					flag = false;
					break;
				}
			}
			if (flag) {
				count++;
			}
		}
		System.out.println(count);
	}
	
}





