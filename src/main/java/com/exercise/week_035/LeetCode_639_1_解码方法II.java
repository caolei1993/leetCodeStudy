package main.java.com.exercise.week_035;

/**
 * @Author cl
 * @Date 2021/9/27 11:00
 * @Version 1.0
 */
public class LeetCode_639_1_解码方法II {
    static final int mod = (int)1e9 + 7;
    public int numDecodings(String s) {
        char[] cs = s.toCharArray();
        int n = cs.length;
        // 定义状态f[i]代表以cs[i]为结尾的字符串解码的方案数
        long[] f = new long[n];
        // f[0]值的初始化
        f[0] = cs[0] == '*' ? 9 : (cs[0] != '0' ? 1 : 0);
        for (int i = 1; i < n; i++) {
            char c = cs[i], prev = cs[i - 1];
            long l = i - 2 >= 0 ? f[i - 2] : 1;
            if (c == '*') {
                // 此时c可以单独作为一个item
                f[i] += f[i - 1] * 9;
                // 与前一位组合在一起
                if (prev == '*') {
                    // 与前一位可以组成11 - 26中不算20的所有数，共15种
                    f[i] += l * 15;
                } else {
                    int u = prev - '0';
                    if (u == 1) {
                        f[i] += l * 9;
                    } else if (u == 2) {
                        f[i] += l * 6;
                    }
                }
            } else {
                // c为数字
                int v = c - '0';
                if (prev == '*') {
                    if (v == 0) {
                        // 只能组成10,20两种情况
                        f[i] += l * 2;
                    } else {
                        // c可以单独作为一个item
                        f[i] += f[i - 1];
                        if (1 <= v && v <= 6) {
                            // c可以与前一位组成1x或2x两种情况
                            f[i] += l * 2;
                        } else {
                            // 剩余7 - 9的情况只可能是1x
                            f[i] += l;
                        }
                    }
                } else {
                    // prev是数字
                    int u = prev - '0';
                    if (v == 0) {
                        // c为0时，只有前一位是1或2时才合法
                        if (u == 1 || u == 2) {
                            f[i] += l;
                        }
                    } else {
                        // c是1 - 9，c可以单独作为一个item
                        f[i] += f[i - 1];
                        if (u == 1) {
                            // 前一位是1，与当前位可组成11-19的合法方案
                            f[i] += l;
                        } else if (u == 2) {
                            if (1 <= v && v <= 6) {
                                f[i] += l;
                            }
                        }
                    }
                }
            }
            f[i] %= mod;
        }
        return (int)f[n - 1];
    }
}
