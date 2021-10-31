package main.java.com.exercise.week_039;

import java.util.Arrays;
import java.util.HashSet;
import java.util.Set;

/**
 * @Author cl
 * @Date 2021/10/28 21:44
 * @Version 1.0
 */
public class LeetCode_869_2_重新排序得到2的幂 {
    static Set<Integer> set = new HashSet<>();

    static {
        // 预处理出所有范围内2的幂的值
        for (int i = 1; i <= (int) 1e9; i *= 2) {
            set.add(i);
        }
    }

    public boolean reorderedPowerOf2(int n) {
        int[] cnts = new int[10];
        // 统计n中各个数字的个数以及n的长度
        while (n != 0) {
            cnts[n % 10]++;
            n /= 10;
        }
        int[] cnt = new int[10];
        // 检查词频是否相同
        out:
        for (int i : set) {
            Arrays.fill(cnt, 0);
            while (i != 0) {
                cnt[i % 10]++;
                i /= 10;
            }
            for (int j = 0; j < 10; j++) {
                if (cnt[j] != cnts[j]) {
                    continue out;
                }
            }
            return true;
        }
        return false;
    }
}
