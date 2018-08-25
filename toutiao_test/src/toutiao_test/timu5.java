package toutiao_test;
/*
3
2
helloworld
hdlrowolle
2
helloworld
worldhello
2
abcde
acbde
 */
/*
 * 第一行表示有多少组数据
 * 第二行表示该组数据有多少个字符串
 * 要求第一行字符串围成一个圈，从任意一处切开，按照顺时针或逆时针遍历，能找到第二行字符串
 * 
 */
import java.util.Scanner;

/*
 * 以下是我的实现，只考虑了每组数据有2行字符串的情况，也想不出更好的求解方法了，望高手指点
 */
public class timu5 {
	
	public static void main(String[] args) {
		Scanner sc = new Scanner(System.in);
		int size = sc.nextInt();
		while(size>0) {
			int count = sc.nextInt();
			String[] strings = new String[count];
			for (int i = 0; i < count; i++) {
				strings[i] = sc.next();
			}
			char[] chars = strings[0].toCharArray();
			char[] char2 = strings[1].toCharArray();
			boolean flag = true;
			for (int i = 0; i < chars.length; i++) {
				int length = i + chars.length;
				char[] str = new char[chars.length];//顺时针数组
				char[] str2 = new char[chars.length];//逆时针数组
				//顺时针数组
				for (int j = i, k =0; j < length; j++,k++) {
					str[k] = chars[j%chars.length];
				}
				//逆时针数组
				for (int j = i+chars.length,k=0; j-k>=0 && k<chars.length; k++) {
					str2[k] = chars[(j-k)%chars.length];					
				}
				//顺时针找
				flag = true;
				for (int j = 0; j < str.length; j++) {
					if (str[j]==char2[j]) {
						continue;
					}else {
						flag = false;
						break;
					}	
				}
				if (flag) {
					System.out.println("Yeah");
					break;
				}
				//逆时针找
				flag = true;
				for (int j = 0; j < str.length; j++) {
					if (str2[j]==char2[j]) {
						continue;
					}else {
						flag = false;
						break;
					}	
				}
				if (flag) {
					System.out.println("Yeah");
					break;
				}
			}				
			if (!flag) {
				System.out.println("sad");
			}
			size-=1;
		}
		sc.close();				
	}
}
