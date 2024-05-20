package com.villageroad.web.test;

import java.io.BufferedInputStream;
import java.util.Scanner;

/**
 * 一副扑克牌的每张牌表示一个数（J、Q、K 分别表示 11、12、13，两个司令都表示 6）。
 * 任取4 张牌，即得到 4 个 1~13 的数，请添加运算符（规定为加+ 减- 乘* 除/ 四种）使之成为一个运算式。每个数只能参与一次运算，
 * 4 个数顺序可以任意组合，4 个运算符任意取 3 个且可以重复取。运算遵从一定优先级别，可加括号控制，最终使运算结果为 24。
 * 请输出一种解决方案的表达式，用括号表示运算优先。如果没有一种解决方案，则输出 -1 表示无解。
 *
 * 输入格式：
 *
 * 输入在一行中给出 4 个整数，每个整数取值在 [1, 13]。
 *
 * 输出格式：
 *
 * 输出任一种解决方案的表达式，用括号表示运算优先。如果没有解决方案，请输出 -1。
 *
 * 输入样例：
 *
 * 2 3 12 12
 *
 * 输出样例：
 *
 * ((3-2)*12)+12
 *
 *
 * 思路：
 *
 * 四个操作数，三个操作符，两个括号，有以下五种计算模式
 *
 * ((A op B) op C) op D
 *
 * (A op (B op C)) op D
 *
 * A op (B op (C op D))
 *
 * A op ((B op C) op D)
 *
 * (A op B) op (C op D)
 *
 */
public class CardGame {
    static char[] op = {'#', '+', '-', '*', '/',};

    static float cal(float x, float y, int op) {
        return switch (op) {
            case 1 -> x + y;
            case 2 -> x - y;
            case 3 -> x * y;
            case 4 -> x / y;
            default -> 0;
        };
    }

    static float cal_model1(float i, float j, float k, float t, int op1, int op2, int op3) {
        float r1, r2, r3;
        r1 = cal(i, j, op1);
        r2 = cal(r1, k, op2);
        r3 = cal(r2, t, op3);
        return r3;
    }

    static float cal_model2(float i, float j, float k, float t, int op1, int op2, int op3) {
        float r1, r2, r3;
        r1 = cal(j, k, op2);
        r2 = cal(i, r1, op1);
        r3 = cal(r2, t, op3);
        return r3;
    }

    static float cal_model3(float i, float j, float k, float t, int op1, int op2, int op3) {
        float r1, r2, r3;
        r1 = cal(k, t, op3);
        r2 = cal(j, r1, op2);
        r3 = cal(i, r2, op1);
        return r3;
    }

    static float cal_model4(float i, float j, float k, float t, int op1, int op2, int op3) {
        float r1, r2, r3;
        r1 = cal(j, k, op2);
        r2 = cal(r1, t, op3);
        r3 = cal(i, r2, op1);
        return r3;
    }

    static float cal_model5(float i, float j, float k, float t, int op1, int op2, int op3) {
        float r1, r2, r3;
        r1 = cal(i, j, op1);
        r2 = cal(k, t, op3);
        r3 = cal(r1, r2, op2);
        return r3;
    }

    static int get24(int i, int j, int k, int t) {
        int op1, op2, op3;
        int flag = 0;
        for (op1 = 1; op1 <= 4; op1++)
            for (op2 = 1; op2 <= 4; op2++)
                for (op3 = 1; op3 <= 4; op3++) {
                    if (cal_model1(i, j, k, t, op1, op2, op3) == 24) {
                        System.out.printf("((%d%c%d)%c%d)%c%d\n", i, op[op1], j, op[op2], k, op[op3], t);
                        flag = 1;
                        return flag;
                    }
                    if (cal_model2(i, j, k, t, op1, op2, op3) == 24) {
                        System.out.printf("(%d%c(%d%c%d))%c%d\n", i, op[op1], j, op[op2], k, op[op3], t);
                        flag = 1;
                        return flag;
                    }
                    if (cal_model3(i, j, k, t, op1, op2, op3) == 24) {
                        System.out.printf("%d%c(%d%c(%d%c%d))\n", i, op[op1], j, op[op2], k, op[op3], t);
                        flag = 1;
                        return flag;
                    }
                    if (cal_model4(i, j, k, t, op1, op2, op3) == 24) {
                        System.out.printf("%d%c((%d%c%d)%c%d)\n", i, op[op1], j, op[op2], k, op[op3], t);
                        flag = 1;
                        return flag;
                    }
                    if (cal_model5(i, j, k, t, op1, op2, op3) == 24) {
                        System.out.printf("(%d%c%d)%c(%d%c%d)\n", i, op[op1], j, op[op2], k, op[op3], t);
                        flag = 1;
                        return flag;
                    }
                }
        return 0;
    }

    public static void main(String[] args) {
        int x, y, m, n;
        int i, j, k, t;
        int[] in = new int[4];
        int flag = 0;
        Scanner sc = new Scanner(new BufferedInputStream(System.in));
        for (i = 0; i < 4; i++) in[i] = sc.nextInt();
        ok:
        for (i = 0; i < 4; i++) {
            for (j = 0; j < 4; j++) {
                if (j == i) continue;
                for (k = 0; k < 4; k++) {
                    if (i == k || j == k) continue;
                    for (t = 0; t < 4; t++) {
                        if (t == i || t == j || t == k) continue;
                        x = in[i];
                        y = in[j];
                        m = in[k];
                        n = in[t];
                        flag = get24(x, y, m, n);
                        if (flag == 1) break ok;
                    }
                }
            }
        }
        if (flag == 0) System.out.printf("-1\n");
    }
}