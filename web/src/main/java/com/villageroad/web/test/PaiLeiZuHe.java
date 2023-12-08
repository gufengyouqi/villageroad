package com.villageroad.web.test;

import java.util.Stack;

/**
 * 排列组合
 */
public class PaiLeiZuHe {
    public static Stack<String> stack = new Stack<>();
    public static void main(String[] args) {
        String shu[] = {"a","b","c"};
        f(shu,3,0);
    }
    /**
     *
     * @param shu   待选择的数组
     * @param targ  要选择多少个次
     * @param cur   当前选择的是第几次
     */
    private static void f(String[] shu, int targ, int cur) {
        // TODO Auto-generated method stub
        if(cur == targ) {
            System.out.println(stack);
            return;
        }

        for(int i=0;i<shu.length;i++) {
            if(!stack.contains(shu[i])) {
                stack.add(shu[i]);
                f(shu, targ, cur+1);
                stack.pop();
            }

        }
    }
}
