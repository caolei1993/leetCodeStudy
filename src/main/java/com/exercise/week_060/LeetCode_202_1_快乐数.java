package main.java.com.exercise.week_060;

import java.util.HashSet;
import java.util.Set;

/**
 * @author cl
 * @version 1.0
 * @description： TODO
 * @date 2024/1/31 16:35
 */
public class LeetCode_202_1_快乐数 {
    public int getNext(int v) {
        int total = 0;
        while (v > 0) {
            int m = v % 10;
            total += m * m;
            v = v / 10;
        }
        return total;
    }


    public boolean isHappy(int n) {
        Set<Integer> set = new HashSet<>();
        while (n != 1 && !set.contains(n)) {
            set.add(n);
            n = getNext(n);
        }
        return n == 1;
    }
}
