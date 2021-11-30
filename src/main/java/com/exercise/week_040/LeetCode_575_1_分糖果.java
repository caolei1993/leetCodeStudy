package main.java.com.exercise.week_040;

import java.util.*;

/**
 * @Author cl
 * @Date 2021/11/1 16:36
 * @Version 1.0
 */
public class LeetCode_575_1_分糖果 {
    public int distributeCandies(int[] candyType) {
        int n = candyType.length;
        Set<Integer> set = new HashSet<>();
        for (int i : candyType) {
            set.add(i);
        }
        int size = set.size();
        return Math.min(size, n >> 1);
    }
}
