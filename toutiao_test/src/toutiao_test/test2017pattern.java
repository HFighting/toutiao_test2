package toutiao_test;

import java.util.Scanner;

/*
小易喜欢的单词具有以下特性：
1.单词每个字母都是大写字母
2.单词没有连续相等的字母
3.单词没有形如“xyxy”(这里的x，y指的都是字母，并且可以相同)这样的子序列，子序列可能不连续。

例如：
小易不喜欢"ABBA"，因为这里有两个连续的'B'	w*
小易不喜欢"THETXH"，因为这里包含子序列"THTH"
小易不喜欢"ABACADA"，因为这里包含子序列"AAAA"
小易喜欢"A","ABA"和"ABCBA"这些单词
给你一个单词，你要回答小易是否会喜欢这个单词（只要不是不喜欢，就是喜欢）。
 */
public class test2017pattern {

	public static void main(String[] args) {
		Scanner sc = new Scanner(System.in);
		String string = sc.nextLine();
		sc.close();
		if (test1(string)&&test2(string)&&test3(string)) {
			System.out.println("like");
		}else {
			System.out.println("dislike");
		}

	}
	//条件1,都是大写字母
	public static boolean test1(String word) {
		return word.matches("[A-Z]+");
	}
	//条件2，没有两个连续的相等字符
	public static boolean test2(String word) {
		return !word.matches(".*(.)\\1.*");
	}
	//条件3，没有连续出现的子序列x..y..x..y
	public static boolean test3(String word) {
		return !word.matches(".*(?<one>.).*(?<two>.).*\\one.*\\two.*");
	}

}
