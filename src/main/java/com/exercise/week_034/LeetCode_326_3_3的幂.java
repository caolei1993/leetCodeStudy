package main.java.com.exercise.week_034;

import java.util.HashSet;
import java.util.Set;

/**
 * @Author cl
 * @Date 2021/9/23 11:02
 * @Version 1.0
 */
public class LeetCode_326_3_3的幂 {
    static Set<Integer> set = new HashSet<>();
    static {
        int v = 1;
        set.add(v);
        while (v <= Integer.MAX_VALUE / 3) {
            v *= 3;
            set.add(v);
        }
    }
    public boolean isPowerOfThree(int n) {
        return n > 0 && set.contains(n);
    }
}
