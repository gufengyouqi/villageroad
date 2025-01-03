package com.villageroad.web.test;

import java.util.Arrays;

public class MoneyTest {
    public int coinChange(int[] coins, int amount) {
        int max = amount + 1;
        int[] dp = new int[amount + 1];
        Arrays.fill(dp, max);
        dp[0] = 0;
        for (int i = 1; i <= amount; i++) {
            for (int j = 0; j < coins.length; j++) {
                if (coins[j] <= i) {
                    dp[i] = Math.min(dp[i], dp[i - coins[j]] + 1);
                }
            }
        }
        return dp[amount] > amount ? -1 : dp[amount];
    }

    public static void main(String[] args) {
        MoneyTest moneyTest = new MoneyTest();
        int i = moneyTest.coinChange(new int[]{1, 2, 5, 10, 20}, 36);
        System.out.println(i);
    }

}
