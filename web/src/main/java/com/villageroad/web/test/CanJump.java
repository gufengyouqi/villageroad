package com.villageroad.web.test;

public class CanJump {
//    nums = [2,3,1,1,4]
//    nums = [3,2,1,0,4]

    public static boolean canJump(int[] nums) {
        int len = nums.length;
        int rightMost = 0;
        for (int i = 0; i < len; i++) {
            if (i <= rightMost) {
                rightMost = Math.max(i, i + nums[i]);
                if (rightMost >= len - 1) {
                    return true;
                }
            }
        }

        return false;
    }

    public static void main(String[] args) {
        int[] nums=new int[]{2,3,0,1,4} ;
//        int[] nums = new int[]{3, 2, 1, 0, 4};
        System.out.println(canJump(nums));
    }
}
