package toutiao_test;

import org.junit.Test;

public class TEST{
	
	private static void WAITING() {		 
	    final Object lock = new Object();
	    Thread t1 = new Thread(){
	        @Override
	        public void run() {
	           
	            int i = 0;
	           
	            while(true ){
	                 synchronized (lock) {
	                     try {
	                         lock.wait();
	                    } catch (InterruptedException e) {
	                    }
	                    System. out.println(i++);
	                }
	           }
	       }
	   };
	   
	   Thread t2 = new Thread(){
	        @Override
	        public void run() {
	           
	            while(true ){
	                 synchronized (lock) {
	                     for(int i = 0; i< 100000; i++){
	                        System. out.println(i);
	                    }
	                    lock.notifyAll();
	                }
	                
	           }
	       }
	   };
	   
	   t1.setName( "^^t1^^");
	   t2.setName( "^^t2^^");
	   
	   t1.start();
	   t2.start();
	}
	public static void main(String[] args) {
		WAITING();
	}
	@Test
	public void test() {
		String	s1	=	new	String("777"); 
		String	s2	=	"aaa777"; 
		String	s3	=	"aaa"	+	"777"; 
		String	s4	=	"aaa"	+	s1;
		
		System.out.println(s1==s2);
		System.out.println(s2==s4);
		System.out.println(s2==s3);
		System.out.println(s2==s4.intern());
		System.out.println(s2.substring(2, 5));
		
		String	s	=	" 	Hello ";			
		s	+=	" World ";			
		s.trim();//trim()是删除字符串首尾的比20（包括20，即空白字符）小的字符，字符串中间的不删除			
		System.out.println(s.trim());

		
	}
	@Test
	public void test1() {
		int[][] a = new int[3][4];
		System.out.println(a.length+" ");
	}
	
}