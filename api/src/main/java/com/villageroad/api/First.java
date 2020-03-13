package com.villageroad.api;
 
import java.util.Stack;
 
/**
 * @author houshengbin
 */
public class First {

	public static int count;
	/**
	 * 一个人爬楼梯，一步可以迈一级，二级，三级台阶， 如果楼梯有N级，编写程序，输出所有走法。java实现。
	 * 
	 * @param args
	 */
	public static void main(String[] args) {
		// TODO Auto-generated method stub
		Stack<Integer> stt=new Stack<>();
		buileT(stt,6);
		System.out.println(count);
	}
 
	public static void buileT(Stack<Integer> stt,int N) {
		if (N >= 1) {
			// System.out.println("某人走了1个楼梯");
			stt.push(1);
			buileT(stt,N - 1);
			stt.pop();
		}
		if (N >= 2) {
			// System.out.println("某人走了2个楼梯");
			stt.push(2);
			buileT(stt,N - 2);
			stt.pop();
		}
		if (N >= 3) {
			// System.out.println("某人走了3个楼梯");
			stt.push(3);
			buileT(stt,N - 3);
			stt.pop();
		}
		if (N == 0) {
			for(int i:stt)
			{
				System.out.print("由"+i+"--");
			}
			count++;
			System.out.println("完成");
			System.out.println("");
		}
	}
}
 