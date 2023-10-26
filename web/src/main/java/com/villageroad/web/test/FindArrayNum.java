package com.villageroad.web.test;

import java.util.HashSet;
import java.util.Iterator;
import java.util.Set;

public class FindArrayNum {
    public static void main(String[] args) {
        System.out.println(find1From2(new int[]{1, 1, 2, 2, 3, 3, 99}));
        int[] num1 = new int[]{0};
        int[] num2 = new int[]{0};
        findNumsAppearOnce(new int[]{1, 2, 2, 3, 3, 99}, num1, num2);
        System.out.println(num1[0] + ":" + num2[0]);
        System.out.println(find1From3(new int[]{1, 1, 1, 2, 2, 2, 3, 3, 3, 99}));
    }

    /**
     * 一个数组中,所有的数都出现2次,有1个出现1次,找到这个数
     *
     * @param a
     * @return
     */
    public static int find1From2(int[] a) {
        int tmp = 0;
        int len = a.length;
        for (int i = 0; i < len; i++) {
            tmp = tmp ^ a[i];
        }
        return tmp;

    }

    /**
     * 数组中有两个出现一次的数字，其他数字都出现两次，找出这两个数字
     *
     * @param array
     * @param num1
     * @param num2
     */
    public static void findNumsAppearOnce(int[] array, int num1[], int num2[]) {
        int temp = 0;
        int index = 0;
//        找到整个数组亦或一次最后的结果m
        for (int i = 0; i < array.length; i++)
            temp ^= array[i];
//        找到m最右边第一个1的位置
        for (; index < 32; index++)
            if ((temp & (1 << index)) != 0) break;
        /**
         * 如果array[i]的第index位为1，则与num1[0]异或,
         * 否则与num2[0]异或，相同的两个数它们第index位的数字肯定相同,
         * 所以两个相同的数肯定能与同一个num相异或
         * */
        for (int i = 0; i < array.length; i++) {
            if ((array[i] & (1 << index)) != 0) num1[0] ^= array[i];
            else num2[0] ^= array[i];
        }
    }

    /**
     * 用哈希表,解这个问题的时间复杂度为O（n）,空间复杂度为O（1）；
     * @param array
     * @param num1
     * @param num2
     */
    public static void FindNumsAppearOnce(int [] array,int num1[] , int num2[]) {
        Set<Integer> set = new HashSet<>();
        for(int i = 0;i < array.length; i++) {
            if(!set.add(array[i])) {
                set.remove(array[i]);
            }
        }
        Iterator<Integer> i = set.iterator();
        num1[0] = i.next();
        num2[0] = i.next();
    }

    /**
     * 如果一个数组中只有一个数出现一次，其他数字都出现三次，请找出这个数
     *
     * @param a
     * @return
     */
    public static int find1From3(int[] a) {
        int[] bits = new int[32];
        int len = a.length;
        /**
         * 统计32位中，每一个位上面1的个数，如果一个数组{1，1，1}
         * 那么数组第0位上的值是3，就是说有三个数的二进制第0位是1
         * */
        for (int i = 0; i < len; i++) {
            for (int j = 0; j < 32; j++) {
                bits[j] = bits[j] + ((a[i] >>> j) & 1);
            }
        }
        /**
         *找出数组中不能被3整除的那个位，则要找的那个数在这个位上肯定是1
         * */
        int res = 0;
        for (int i = 0; i < 32; i++) {
            if (bits[i] % 3 != 0) {
                res = res | (1 << i);
            }
        }
        return res;
    }
}
