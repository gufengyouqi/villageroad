package com.villageroad.web.test;

import java.util.LinkedHashMap;

public class DpTest {
    /**
     * x个苹果，一天只能吃一个、两个、或者三个，问多少天可以吃完？
     * @param n
     * @return
     */
    public static long getStepNumber(int n) {
        if (n == 1) {
            return 1;
        }

        if (n == 2) {
            return 2;
        }

        if (n == 3) {
            return 4;
        }

        if (n > 3) {
            return getStepNumber(n - 1) + getStepNumber(n - 2) + getStepNumber(n - 3);
        }
        return 0;
    }

    public static void main(String[] args) {
//        System.out.println(getStepNumber(4)); //1111,112,121,211,13,31
        Object o = new Object();
        System.out.println(o);
        if (null==o){
            System.out.println(1);
        }else{
            System.out.println(2);
        }
    }
}
