package main.java.com.exercise.week_029;

import java.util.HashSet;
import java.util.PriorityQueue;
import java.util.Set;

/**
 * @Author cl
 * @Date 2021/8/9 20:36
 * @Version 1.0
 */
public class LeetCode_313_1_超级丑数 {
    public int nthSuperUglyNumber(int n, int[] primes) {
        Set<Long> set = new HashSet<>();
        PriorityQueue<Long> queue = new PriorityQueue<>();
        set.add(1L);
        queue.add(1L);

        while (n >= 0) {
            long v = queue.poll();
            if (n == 0) {
                return (int) v;
            }
            for (int prime : primes) {
                long value = v * prime;
                if (!set.contains(value)) {
                    set.add(value);
                    queue.add(value);
                }
            }
        }
        return -1;
    }
}
