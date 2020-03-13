package com.villageroad.utils;

import org.apache.commons.lang3.StringUtils;
import org.apache.commons.lang3.math.NumberUtils;

/**
 * @author flybird
 */
public class MoneyConvertUtils {
    private static final char[] cnNumbers = {'零', '壹', '贰', '叁', '肆', '伍', '陆', '柒', '捌', '玖'};
    private static final char[] aUnit = {'角', '分'};
    private static final char[] bUnit = {'拾', '百', '仟'};
    private static final char[] unit = {'万', '亿'};

    /**
     * 数字转大写汉子
     *
     * @param moneyNum
     * @return
     */
    public static String convertToMoney(String moneyNum) {
        if (StringUtils.isBlank(moneyNum)) {
            return moneyNum;
        }
        if (!NumberUtils.isCreatable(moneyNum)) {
            throw new NumberFormatException("this is not number. val: " + moneyNum);
        }

        int len = 0;
        String a, b = "";
        StringBuilder result = new StringBuilder();
        if (moneyNum.indexOf('.') == -1) {
            len = moneyNum.length();
            a = moneyNum;
        } else {
            String[] nums = moneyNum.split("\\.");
            a = nums[0];
            b = nums[1];
            len = a.length();
        }
        if (len > 7) {
            throw new NumberFormatException("金额过大无法计算！");
        }

        int offset = len % 4;
        for (int i = 0; i < a.length(); ) {
            result.append(toCn(a.substring(i, offset), bUnit, (len - i - 1) / 4 - 1));
            if (i == 0) {
                i = offset;
            } else {
                i = i + 4;
            }
            offset = i + 4;
        }
        result.append("元");
        int bLen = b.length();
        if (StringUtils.isNotBlank(b)) {
            String s = toCn(b.substring(0, Math.min(bLen, 3)), aUnit, -1);
            if (s.length() == 3) {
                s += "分";
            }
            result.append(s);
        }
        return result.toString();
    }

    private static String toCn(String num, char[] unionArry, int unitIndex) {
        if ("0000".equals(num)) {
            return "";
        }
        boolean hasZear = false;
        StringBuilder sb = new StringBuilder();
        int length = num.length();
        for (int i = 0; i < length; i++) {
            char c = num.charAt(i);
            if (c == '0') {
                hasZear = true;
                continue;
            }
            if (hasZear) {
                sb.append(cnNumbers[0]);
                hasZear = false;
            }
            sb.append(cnNumbers[c - '0']);
            if (length - i - 2 >= 0) {
                sb.append(unionArry[length - i - 2]);
            }
        }
        if (unitIndex >= 0) {
            sb.append(unit[unitIndex]);
        }

        return sb.toString();
    }

    public static void main(String[] args) {
        System.out.println(MoneyConvertUtils.convertToMoney("1234567.01"));
        System.out.println(MoneyConvertUtils.convertToMoney("12.12"));
    }

}
