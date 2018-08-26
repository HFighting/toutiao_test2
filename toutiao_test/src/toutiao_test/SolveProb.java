package toutiao_test;

import java.util.Arrays;
 
/**
 * 列举数组中任意元素和为定值的组合
 */
public class SolveProb {
    // Arrays.sort(arr);
    static int[] flag = new int[100];
    static int index = 0;// 记录当前

    public static void numGroup(int[] arr, int start, int length, int sum) {
        if (sum == 0) {
            for (int j = 0; j < index; j++) {
                System.out.print(flag[j]);
            }
            System.out.println();
        } 
        else if(sum>0) {
            for (int i = start; i < length; i++) {
            	int temp_sum=sum - arr[i]; 
                if(temp_sum>=0) 
                {
                  flag[index++] = arr[i];
                  SolveProb.numGroup(arr, i + 1, length-1, sum - arr[i]);
                }
                else	//因为后面的数比这个大，若此时temp_sum小于0，则后面的数更不满足条件，不用再进行循环
                	break;
             } 
        }
        index--;
    }       
    public static void main(String[] args) {
        int[] arr = { 1, 3, 2, 4, 5, 6, 7, 8, 9 };
        int sum = 7;      
        Arrays.sort(arr);  //对数组进行排序后的话，可以用numGroup方法        
        SolveProb.numGroup(arr, 0, arr.length, sum);
    }
}
