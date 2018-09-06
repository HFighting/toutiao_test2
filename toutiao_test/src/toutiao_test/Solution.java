package toutiao_test;


import java.util.ArrayList;
import java.util.Iterator;
import java.util.LinkedList;
import java.util.Queue;
import java.util.Stack;


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
	/*
	 * 请实现两个函数，分别用来序列化和反序列化二叉树
	 */

	String Serialize(TreeNode root) {//序列化二叉树，按照先序遍历
		StringBuilder sb = new StringBuilder();
		serialize(root, sb);
		return sb.toString();
	}
	public void serialize(TreeNode node,StringBuilder sb) {
		if (node!=null) {
			sb.append(node.val+"!");
			Serialize(node.left);
	        Serialize(node.right);
		}else {
			sb.append("#!");
			return;
		}       
	}
	int index = 0;
	TreeNode Deserialize(String str) {//反序列化二叉树
		String[] strings = str.split("!");
		TreeNode node = null;
	    if (index < strings.length) {
	    	if ("#".equals(strings[index])) {
	    		node = new TreeNode(Integer.parseInt(strings[index]));//建立结点，先序遍历
		    	index++;
				node.left = Deserialize(str);//递归左子树
				node.right = Deserialize(str);//递归右子树
				return node;
	    	}else {
				index++;
				return null;
			}
		}else {
			 return node;
		}	   
	}
	/*
	 * 利用队列实现层序遍历二叉树，每层打印一行
	 */
	ArrayList<ArrayList<Integer> > Print(TreeNode pRoot) {
		ArrayList<ArrayList<Integer> > list = new ArrayList<>();
		getNode(pRoot, list);
		return list;
    }
	void getNode(TreeNode root,ArrayList<ArrayList<Integer>> list){
		
		Queue<TreeNode> queue = new LinkedList<>();
		if (root==null) {
			return;
		}
		queue.add(root);
		while (!queue.isEmpty()) {//利用链表队列，在更改链表的时候遍历链表
			ArrayList<Integer> nodes = new ArrayList<>();
			int i = 0;int size = queue.size();
			while (i++<size) {
				TreeNode treeNode = queue.poll();
				nodes.add(treeNode.val);
				if (treeNode.left!=null) {
					queue.add(treeNode.left);
				}
				if (treeNode.right!=null) {
					queue.add(treeNode.right);
				}
			}
			list.add(nodes);
		}
	}
	/*
	 * 按照之字形打印二叉树，即第一行从左到右打印二叉树，第二行从右到左打印二叉树，依次类推
	 */
	public ArrayList<ArrayList<Integer> > Print1(TreeNode pRoot) {
		ArrayList<ArrayList<Integer> > list = new ArrayList<>();
		printzhi(pRoot, list);
		return list;
    }
	public void printzhi(TreeNode root,ArrayList<ArrayList<Integer> > list) {
		if (root==null) {
			return;
		}
		Queue<TreeNode> queue = new LinkedList<>();
		queue.add(root);
		boolean flag = true;
		while (!queue.isEmpty()) {
			ArrayList<Integer> nodes = new ArrayList<>();
			if (flag) {
				int i = 0 ;
				int size = queue.size();
				while(i++<size) {
					TreeNode treeNode = queue.poll();//出队列
					nodes.add(treeNode.val);
					if (treeNode.left!=null) {
						queue.add(treeNode.left);
					}
					if (treeNode.right!=null) {
						queue.add(treeNode.right);
					}
				}
				flag = !flag;
			}else {
				int i = 0 ;
				int size = queue.size();
				Stack<Integer> stack = new Stack<>();
				while(size-->i) {
					TreeNode treeNode = queue.poll();//出队列
					stack.push(treeNode.val);
					if (treeNode.left!=null) {
						queue.add(treeNode.left);
					}
					if (treeNode.right!=null) {
						queue.add(treeNode.right);
					}
				}
				while(!stack.isEmpty()) {
					nodes.add(stack.pop());
				}
				flag = !flag;
			}
			list.add(nodes);
		}
	}
	/*
	 * 判断一棵二叉树是否是对称树
	 */
	boolean isSymmetrical(TreeNode pRoot)
    {
		return false;
    }
	/*
	 * 删除一个排序链表中重复的节点1->2->2->3->4->4->5
	 * 处理后：1->2->3->4->5
	 */
	public ListNode deleteDuplication(ListNode pHead)
    {
		if (pHead==null) {//链表为空的情况
			return null;
		}
		if (pHead.next==null) {//链表只有一个元素的情况
			return pHead;
		}
		ListNode firstlist = pHead;
		while (firstlist!=null) {
            if(firstlist.next!=null){
                if (firstlist.val==firstlist.next.val) {
				    firstlist.next = firstlist.next.next;
			    }else {
				    firstlist = firstlist.next;
			    }
            }else{
                break;
            }
		}
		return pHead;
    }
	/*
	 * 删除一个排序链表中重复的节点1->2->2->3->4->4->5
	 * 处理后：1->3->5
	 */
	public ListNode deleteDuplication1(ListNode pHead) {
		if (pHead==null) {//链表为空的情况
			return null;
		}
		if (pHead.next==null) {//链表只有一个元素的情况
			return pHead;
		}
		ListNode current;
		//如果前面几个相等
		if (pHead.val == pHead.next.val) {
			current = pHead.next.next;
			if (current==null) {
				return null;
			}
			while (current.val==pHead.val) {
				current = current.next;
				if (current==null) {
					return null;
				}
			}
			//到这为止，把最开始重复的全部已经去掉，接下来继续删除后面的
			return deleteDuplication1(current);
		}else {//若前面两个不相等
			current = pHead.next;
			//则从第二个开始删除递归
			pHead.next = deleteDuplication1(current);
			return pHead;
		}
	}
	/*
	 * 给一个链表，若其中包含环，请找出该链表的环的入口结点，否则，输出null。
	 * 经典思路：
	 * p1、p2同时指向头结点，p1每次走一步，p2每次走两步，当p1与p2相等时，p1走了x步，p2走了2x步，
	 * 相当于p2比p1多走了一个环的步数，即x就是环中节点的个数；此时让p2指向头结点，并且每次只走一步，
	 * 当p1=p2时，p1就是环的入口节点
	 */
	public ListNode EntryNodeOfLoop(ListNode pHead)
    {
		if (pHead==null) {
			return null;
		}
		if (pHead.next==null) {
			return null;
		}		
       return findhuan(pHead);
    }	
	
	public ListNode findhuan(ListNode phead) {
		ListNode p1 = phead;
		ListNode p2 = phead;//p1\p2同时指向头结点
		do {
			p1 = p1.next;//p1走一步
			p2 = p2.next.next;//p2走两步
		} while (p1!=p2);
		p2 = phead;
		while (p1!=p2) {
			p1 = p1.next;
			p2 = p2.next;
		}
		return p1;
	}
	
	/*
	 * 请实现一个函数用来判断字符串是否表示数值（包括整数和小数）。
	 * 例如，字符串"+100","5e2","-123","3.1416"和"-1E-16"都表示数值。
	 * 但是"12e","1a3.14","1.2.3","+-5"和"12e+4.3"都不是。
	 */
	@Test
	public void isNumeric() {
		String string = "-1E-16";
        String match1 = "[+|-]?[\\.]?((\\d+)|(\\d+(e|E)[-|+]?\\d+))";
        String match2 = "\\d+(\\.)\\d+";
        String match3 = "\\d+(\\.)(\\d+(e|E)[-|+]?\\d+)";
        if (string.matches(match1)||string.matches(match2)||string.matches(match3)) {
			System.out.println(true);
		}else {
			System.out.println(false);
		}		
    }
	
	/*
	 * 请实现一个函数用来匹配包括'.'和'*'的正则表达式。
	 * 模式中的字符'.'表示任意一个字符，而'*'表示它前面的字符可以出现任意次（包含0次）。 
	 * 在本题中，匹配是指字符串的所有字符匹配整个模式。
	 * 例如，字符串"aaa"与模式"a.a"和"ab*ac*a"匹配，但是与"aa.a"和"ab*a"均不匹配
	 */
	//前面一个是字符串，后面一个是模式
	/*思路：
	 * 若模式的第二个是*
	 * 	字符第一个与模式的第一个字符相匹配，若匹配不成功，则模式后移两个字符，继续匹配
	 * 	若匹配成功：
	 * 		1.模式后移两位，继续匹配（相当于出现0次的情况）
	 * 		2.模式后移一位，字符后移一位，继续匹配（相当于出现1次的情况）
	 * 		3.字符后移一位，模式继续匹配（相当于出现多次的情况）
	 * 若模式第二个不是*
	 * 字符第一个与模式第一个相匹配，若失败，则返回false，若成功，则继续匹配，若匹配到.，则字符、模式都后移一位继续匹配
	 */	
	public boolean match(char[] str, char[] pattern)
    {
		if (str==null&&pattern==null) {
			return false;
		}
        return findmatch(str, 0, pattern, 0);
    }
	public boolean findmatch(char[] str,int length1,char[] pattern,int length2) {
		//如果都到尾，则匹配成功
		if (length1==str.length&&length2==pattern.length) {
			return true;
		}
		//若字符没到尾，模式到尾了，则匹配失败，或者是模式到尾了，字符没到尾，都匹配失败
		if ((length2!=pattern.length&&length1==str.length)||(length1!=str.length&&length2==pattern.length)) {
			return false;
		}
		if (length2+1<pattern.length&&pattern[length2+1]=='*') {
			if (str[length1]==pattern[length2]) {
				return findmatch(str, length1, pattern, length2+2)||
						findmatch(str, length1+1, pattern, length2+1)||
						findmatch(str, length1+1, pattern, length2);
			}else {
				return findmatch(str, length1, pattern, length2+2);
			}
		}
		if (length2+1<pattern.length&&pattern[length2+1]!='*') {
			if (str[length1]==pattern[length2]) {
				return findmatch(str, length1+1, pattern, length2+1);
			}else if(pattern[length2]=='.'){
				return findmatch(str, length1+1, pattern, length2+1);
			}else {
				return false;
			}
		}
		if (str==null&&pattern[length2]=='.'&&pattern[length2]=='*') {
			return true;
		}
		return false;
	}
	
	/*
	 * 给定一个数组A[0,1,...,n-1],请构建一个数组B[0,1,...,n-1],
	 * 其中B中的元素B[i]=A[0]*A[1]*...*A[i-1]*A[i+1]*...*A[n-1]。
	 * 不能使用除法
	 * 通俗理解：就是每一次把A中除下标为i的数字外的所有数字相乘得到B[i]的值
	 */
	public int[] multiply(int[] A) {
		int[] B = new int[A.length];
		for (int i = 0; i < A.length; i++) {
			int temp = 1;
			for (int j = 0; j < B.length; j++) {
				if (j==i) {
					continue;
				}else {
					temp *= A[j];
				}
			}
			B[i] = temp;
		}
		return B;
    }
	
}
