package main.java.com.exercise.week_037;

/**
 * @Author cl
 * @Date 2021/10/12 8:58
 * @Version 1.0
 */
public class LeetCode_29_1_两数相除 {
    public int divide(int a, int b) {
        // 判断值溢出int范围的情况
        if (a == Integer.MIN_VALUE && b == -1) {
            return Integer.MAX_VALUE;
        }
        // 计算返回值的符号
        int sign = (a > 0) ^ (b > 0) ? -1 : 1;
        a = Math.abs(a);
        b = Math.abs(b);
        int ans = 0;
        // 从大往小判断a包含了多少个b
        for (int i = 31; i >= 0; i--) {
            // b左移替换为a的右移，防止b左移溢出
            // a使用无符号左移，兼容a = -2^31的情况
            // (a >>> i) >= b，修改为(a >>> i) - b >= 0，兼容b = -2^31的情况，一个正的int值加上 2^31次方，int溢出结果为负值，条件判断为false
            if ((a >>> i) - b >= 0) {
                a -= (b << i);
                ans += (1 << i);
            }
        }
        return sign == 1 ? ans : -ans;
    }
}
