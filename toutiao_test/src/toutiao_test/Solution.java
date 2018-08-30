package toutiao_test;

import java.util.ArrayList;
import java.util.List;
import java.util.Stack;

import javax.print.attribute.ResolutionSyntax;

import org.junit.Test;

public class Solution {
	/*
	 * 题目描述
	在一个二维数组中（每个一维数组的长度相同），
	每一行都按照从左到右递增的顺序排序，
	每一列都按照从上到下递增的顺序排序。
	请完成一个函数，输入这样的一个二维数组和一个整数，判断数组中是否含有该整数。
	 */
	public boolean Find(int target, int [][] array) {
		int a = array.length;
		int b = array[0].length;
		for (int i = 0; i < a; i++) {
			for (int j = 0; j < b; j++) {
				if (array[i][j]==target) {
					return true;
				}else if (array[i][j]>target) {
					break;
				}
			}
		}		
		return false;
    }
	/*
	 * 请实现一个函数，将一个字符串中的每个空格替换成“%20”。
	 * 例如，当字符串为We Are Happy.则经过替换之后的字符串为We%20Are%20Happy。
	 * 思想：把该字符串转换成array[]，再新建一个字符串，遍历该数组，加到新建的字符串里，如碰到空格，则加入“%20”。
	 */
	@Test
	public void replaceSpace() {
		String str = "hello word";
    	char[] chars = str.toCharArray();
    	int length = chars.length;
    	String string = new String();
    	for (int i = 0; i < length; i++) {
			if (chars[i]==' ') {
				string += "%20";
			}else {
				string += chars[i];
			}
		}
    	System.out.println(string);
    }
	/*
	 * 输入一个链表，按链表值从尾到头的顺序返回一个ArrayList。
	 * 思想：使用栈存储所有的链表值，最后出栈，添加到ArrayList中
	 */
	@Test
	public void printListFromTailToHead() {
		ListNode first = new ListNode(20);
		ListNode listNode = new ListNode(0);
		first.next = listNode;
		for (int i = 1; i <= 10; i++) {						
			listNode.next = new ListNode(i);
			listNode = listNode.next;
		}
        ArrayList<Integer> list = new ArrayList<>();
        Stack<ListNode> stack = new Stack<>();
        while (first.next!=null) {
        	stack.push(first);
			first = first.next;
		}
        stack.push(first);
        while (!stack.isEmpty()) {
        	ListNode listNode2 = stack.peek();//要先获取栈顶的值
			list.add(listNode2.val);//处理之后
			stack.pop();//再删除栈顶元素
		}
		for (Integer integers : list) {
			System.out.print(integers+" ");
		}
    }
	/*
	 * 输入某二叉树的前序遍历和中序遍历的结果，请重建出该二叉树。
	 * 假设输入的前序遍历和中序遍历的结果中都不含重复的数字。
	 * 例如输入前序遍历序列{1,2,4,7,3,5,6,8}和中序遍历序列{4,7,2,1,5,3,8,6}，则重建二叉树并返回。
	 * 
	 * Definition for binary tree
	 * public class TreeNode {
	 *     int val;
	 *     TreeNode left;
	 *     TreeNode right;
	 *     TreeNode(int x) { val = x; }
	 * }
	 */
	public TreeNode reConstructBinaryTree(int [] pre,int [] in) {
		  TreeNode root=reConstructBinaryTree(pre,0,pre.length-1,in,0,in.length-1);
		  return root;
	}
	//前序遍历{1,2,4,7,3,5,6,8}和中序遍历序列{4,7,2,1,5,3,8,6}
	private TreeNode reConstructBinaryTree(int [] pre,int startPre,int endPre,int [] in,int startIn,int endIn) {
		         
		  if(startPre>endPre||startIn>endIn)
		        return null;
		  TreeNode root=new TreeNode(pre[startPre]);
		         
		  for(int i=startIn;i<=endIn;i++)
		        if(in[i]==pre[startPre]){
		            root.left=reConstructBinaryTree(pre,startPre+1,startPre+i-startIn,in,startIn,i-1);
		            root.right=reConstructBinaryTree(pre,i-startIn+startPre+1,endPre,in,i+1,endIn);
		            break;
		        }		                 
		  return root;
	}
	/*
	 * 请设计一个函数，用来判断在一个矩阵中是否存在一条包含某字符串所有字符的路径。
	 * 路径可以从矩阵中的任意一个格子开始，每一步可以在矩阵中向左，向右，向上，向下移动一个格子。
	 * 如果一条路径经过了矩阵中的某一个格子，则之后不能再次进入这个格子。 
	 * 例如 
	 * 	  a b c e 
	 * 	  s f c s 
	 * 	  a d e e 
	 * 这样的3 X 4 矩阵中包含一条字符串"bcced"的路径，
	 * 但是矩阵中不包含"abcb"路径，因为字符串的第一个字符b占据了矩阵中的第一行第二个格子之后，路径不能再次进入该格子
	 */
		public boolean hasPath(char[] matrix, int rows, int cols, char[] str) {
		        int flag[] = new int[matrix.length];
		        for (int i = 0; i < rows; i++) {
		            for (int j = 0; j < cols; j++) {
		                if (helper(matrix, rows, cols, i, j, str, 0, flag))
		                    return true;
		            }
		        }
		        return false;
		    }
		 
		    private boolean helper(char[] matrix, int rows, int cols, int i, int j, char[] str, int k, int[] flag) {
		        int index = i * cols + j;
		        if (i < 0 || i >= rows || j < 0 || j >= cols || matrix[index] != str[k] || flag[index] == 1)
		            return false;
		        if(k == str.length - 1) //若找到了全部字符，则返回true
		        	return true;
		        flag[index] = 1; //先把状态设为1
		        if (helper(matrix, rows, cols, i - 1, j, str, k + 1, flag)
		                || helper(matrix, rows, cols, i + 1, j, str, k + 1, flag)
		                || helper(matrix, rows, cols, i, j - 1, str, k + 1, flag)
		                || helper(matrix, rows, cols, i, j + 1, str, k + 1, flag)) {
		            return true;
		        }
		        flag[index] = 0;  //回溯的体现，若没有找到，则重新设置为0
		        return false;
		    }
	/*
	 * 地上有一个m行和n列的方格。
	 * 一个机器人从坐标0,0的格子开始移动，每一次只能向左，右，上，下四个方向移动一格，
	 * 但是不能进入行坐标和列坐标的数位之和大于k的格子。
	 * 例如，当k为18时，机器人能够进入方格（35,37），因为3+5+3+7 = 18。
	 * 但是，它不能进入方格（35,38），因为3+5+3+8 = 19。
	 * 请问该机器人能够达到多少个格子？
	 */
	public int movingCount(int threshold, int rows, int cols)
	{
		int[][] flag = new int[rows][cols];
		for (int i = 0; i < rows; i++) {
			for (int j = 0; j < cols; j++) {
				flag[i][j] = 0;
			}
		}
		return findmaxnum(0, 0, threshold, rows, cols, flag);
	}
	public int add(int x,int y) {
		String x1 = String.valueOf(x);
		String y1 = String.valueOf(y);
		int num = 0;
		for (int i = 0; i < x1.length(); i++) {
			num += x1.charAt(i)-'0';			
		}
		for (int i = 0; i < y1.length(); i++) {
			num += y1.charAt(i)-'0';
		}
		return num;
	}
	public int findmaxnum(int x,int y,int threshold,int rows,int cols,int[][] flag) {		
		if (x<0||x>=rows||y>=cols||y<0||add(x, y)>threshold||flag[x][y]==1) {
			return 0;
		}
		flag[x][y] = 1;
		return findmaxnum(x-1, y, threshold, rows, cols, flag)+
			   findmaxnum(x+1, y, threshold, rows, cols, flag)+
			   findmaxnum(x, y-1, threshold, rows, cols, flag)+
			   findmaxnum(x, y+1, threshold, rows, cols, flag)+1;

	}
	@Test
	public void test() {
		System.out.println(add(20, 31));
	}
	/*
	 * 给定一个数组和滑动窗口的大小，找出所有滑动窗口里数值的最大值。
	 * 例如，如果输入数组{2,3,4,2,6,2,5,1}及滑动窗口的大小3，
	 * 那么一共存在6个滑动窗口，他们的最大值分别为{4,4,6,6,6,5}； 
	 * 针对数组{2,3,4,2,6,2,5,1}的滑动窗口有以下6个： 
	 * {[2,3,4],2,6,2,5,1}， {2,[3,4,2],6,2,5,1}， 
	 * {2,3,[4,2,6],2,5,1}， {2,3,4,[2,6,2],5,1}， 
	 * {2,3,4,2,[6,2,5],1}， {2,3,4,2,6,[2,5,1]}。
	 */
	public ArrayList<Integer> maxInWindows(int [] num, int size)
    {
		
        ArrayList<Integer> list = new ArrayList<>();
        if (size==0) {
			return list;
		}
        for (int i = 0; i <= num.length-size; i++) {
			int a = getmax(num, i, i+size);
			list.add(a);
		}
        return list;
    }
	public int getmax(int[] num ,int begin, int end) {
		int max = 0;
		for (int i = begin; i < end; i++) {
			if (num[i]>max) {
				max = num[i];
			}
		}
		return max;
	}
	/*
	 * 给定一棵二叉搜索树，请找出其中的第k小的结点。
	 * 例如， （5，3，7，2，4，6，8）    中，按结点数值大小顺序第三小结点的值为4。
	 */
	/*
	 * 正确代码：
public class Solution {
   int index = 0; //计数器
    TreeNode KthNode(TreeNode root, int k)
    {
        if(root != null){ //中序遍历寻找第k个
            TreeNode node = KthNode(root.left,k);
            if(node != null)
                return node;
            index ++;
            if(index == k)
                return root;
            node = KthNode(root.right,k);
            if(node != null)
                return node;
        }
        return null;
    }
}
	 */
	//我的代码
	int count = 0;
	TreeNode treeNode;
	TreeNode KthNode(TreeNode pRoot, int k)
    {
		fiNode(pRoot, k);
		if (treeNode==null||k==0) {
			return null;
		}
		return treeNode;
    }
	public void fiNode(TreeNode node,int k) {
		if (node.left!=null) {
			fiNode(node.left, k);
		}
		count += 1;
		if (count==k) {
			treeNode = node;
			return;
		}
		if (count>k) {
			return;
		}
		if (node.right!=null) {
			fiNode(node.right, k);
		}
	}
}
