package main.java.com.exercise.week_035;

import java.util.HashMap;
import java.util.Map;

/**
 * @Author cl
 * @Date 2021/10/6 18:26
 * @Version 1.0
 */
public class LeetCode_166_1_分数到小数 {
    public String fractionToDecimal(int numerator, int denominator) {
        // 将除数被除数转为long型，防止计算的过程中数据溢出int范围
        long a = numerator, b = denominator;
        // 如果能整除，直接返回结果
        if (a % b == 0) {
            return String.valueOf(a / b);
        }
        StringBuilder sb = new StringBuilder();
        // 如果两个数的计算结果为负数，直接在结果前添加负号
        if (a * b < 0) {
            sb.append("-");
        }
        // 取除数与被除数的绝对值，求取正数除法结果
        a = Math.abs(a);
        b = Math.abs(b);
        // 计算小数以前部分
        sb.append(String.valueOf(a / b) + ".");
        a %= b;
        Map<Long, Integer> map = new HashMap<>();
        while (a != 0) {
            // 记录当前余数所在位置
            map.put(a, sb.length());
            a *= 10;
            sb.append(a / b);
            a %= b;

            // 判断当前余数是否之前存在过，如果出现过，则将出现的位置到当前位置的部分抠出来作为循环部分
            if (map.containsKey(a)) {
                int u = map.get(a);
                return String.format("%s(%s)", sb.substring(0, u), sb.substring(u));
            }
        }
        return sb.toString();
    }
}
