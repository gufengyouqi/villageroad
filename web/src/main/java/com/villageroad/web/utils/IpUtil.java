package com.villageroad.web.utils;

import java.util.*;


public class IpUtil {
    /**
     *
     * @param s string字符串
     * @return string字符串ArrayList
     */

    //6.1. 记录分段IP数字字符串nums

    private String  nums = "";
    //3.在递归的主体中使用step记录分割出的数字个数，index记录递归的下标。
    public void  dfs(String s, ArrayList<String> res, int step, int index) {
        //4.当前分割出的字符串cur
        String cur = "";
        //5.分割出四个数字（结束递归是指step为4，且下标到达字符串末尾）
        if (step == 4) {
            //6.下标必须走到末尾，返回并将记录分段IP数字字符串装入结果集
            if (index != s.length())
                return;
            res.add(nums);
        }
        //7.或者在主体递归中（在主体递归中，每次加入一个字符当数字，最多可以加入三个数字，剩余字符串进入递归构造下一个数字）
        else {
            //8.最长for循环遍历3位（）
            for (int i = index; i < index + 3 && i < s.length(); i++) {
                //8.1将当前遍历的字符加入到当前分割的字符串中
                cur += s.charAt(i);
                //9.将当前分割的字符串转数字num比较（将数字字符串转化为基本数据类型整型）
                int num = Integer.parseInt(cur);
                //9.1 将记录分段IP数据字符串赋值给字符串temp
                String  temp = nums;
                //10.不能超过255且不能有前导0或为0也要当前字符串字符只有一位（然后要检查每次的数字是否合法，不超过255且没有前导0）
                if (num <= 255 && (cur.length() == 1 || cur.charAt(0) != '0')) {
                    //11当前数字向后移动3位后不为0，说明足够三位可以添加点.
                    if (step - 3 != 0) 
                        //11.1.添加点.（合法IP需要将其连接，同时递归完这一轮需要回溯）
                        nums += cur + ".";
                     else 
                        nums += cur;
                    

                    //12.使用递归查找下一个数字
                    dfs(s, res, step + 1, i + 1);

                    //13.回溯
                    nums = temp;

                }
            }
        }
    }

    public ArrayList<String> restoreIpAddresses (String s) {
        // write code here
        //1.定义结果集res
        ArrayList<String> res = new ArrayList<>();
        //2.使用递归+回溯的方法实现数字字符串转化成IP地址
        dfs(s, res, 0, 0);
        //14.返回结果集
        return res;
    }

    public static void main(String[] args) {
        IpUtil ipUtil = new IpUtil();
        ArrayList<String> ips = ipUtil.restoreIpAddresses("12332231");
        for (String ip : ips) {
            System.out.println(ip);
        }
    }
}
