package main.java.com.exercise.week_036;

import java.util.*;

/**
 * @Author cl
 * @Date 2021/10/9 10:58
 * @Version 1.0
 */
public class LeetCode_352_2_将数据流变为多个不相交区间 {
    /**
     * 用来保存不相交区间的集合
     */
    TreeSet<int[]> set = new TreeSet<>(Comparator.comparingInt(a -> a[0]));
    /**
     * 定义哨兵头区间和尾区间
     */
    int[] head = new int[]{-10, -10}, tail = new int[]{10010, 10010};

    public LeetCode_352_2_将数据流变为多个不相交区间() {
        set.add(head);
        set.add(tail);
    }

    public void addNum(int val) {
        int[] cur = new int[]{val, val};
        int[] pre = set.floor(cur);
        int[] next = set.higher(cur);
        if ((pre[0] <= val) && (pre[1] >= val)) {

        } else if ((pre[1] + 1 == val) && (next[0] - 1 == val)) {
            pre[1] = next[1];
            set.remove(next);
        } else if (pre[1] + 1 == val) {
            pre[1] = val;
        } else if (next[0] - 1 == val) {
            next[0] = val;
        } else {
            set.add(cur);
        }
    }

    public int[][] getIntervals() {
        int n = set.size();
        int[][] ans = new int[n - 2][2];
        Iterator<int[]> it = set.iterator();
        it.next();
        for (int i = 0; i < n - 2 ; i++) {
            ans[i] = it.next();
        }
        return ans;
    }
}
