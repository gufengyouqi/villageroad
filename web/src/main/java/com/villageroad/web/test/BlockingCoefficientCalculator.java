package com.villageroad.web.test;

/**
 * 阻塞系数计算公式
 */
public class BlockingCoefficientCalculator {
    public static void main(String[] args) {
        double averageWaitTime = calculateAverageWaitTime(); // 获取平均等待时间
        double averageServiceTime = calculateAverageServiceTime(); // 获取平均服务时间
        
        double blockingCoefficient = (1 - averageWaitTime / averageServiceTime) * 100;
        
        System.out.println("阻塞系数为：" + blockingCoefficient);
    }
    
    private static double calculateAverageWaitTime() {
        // TODO: 根据实际情况编写计算平均等待时间的代码
        return 5.6789; // 返回计算结果（单位：秒）
    }
    
    private static double calculateAverageServiceTime() {
        // TODO: 根据实际情况编写计算平均服务时间的代码
        return 3.4567; // 返回计算结果（单位：秒）
    }
}