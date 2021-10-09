package main.java.com.exercise.week_036;

import java.util.ArrayList;
import java.util.List;

/**
 * @Author cl
 * @Date 2021/10/9 9:42
 * @Version 1.0
 */
public class LeetCode_352_1_将数据流变为多个不相交区间 {
    /**
     * 用来保存不相交区间的集合
     */
    List<int[]> list = new ArrayList<>();
    /**
     * 定义哨兵头区间和尾区间
     */
    int[] head = new int[]{-10, -10}, tail = new int[]{10010, 10010};

    public LeetCode_352_1_将数据流变为多个不相交区间() {
        list.add(head);
        list.add(tail);
    }

    public void addNum(int val) {
        int n = list.size();
//        if (n == 2) {
//            list.add(1, new int[]{val, val});
//            return;
//        }
        // 通过二分法，查找相邻的区间
        int l = 0, r = n - 1;
        while (l < r) {
            int mid = l + r + 1 >> 1;
            if (list.get(mid)[0] <= val) {
                l = mid;
            } else {
                r = mid - 1;
            }
        }
        int[] cur = new int[]{val, val};
        int[] pre = list.get(r);
        int[] next = list.get(r + 1);

        if ((pre[0] <= val && pre[1] >= val)) {

        } else if ((pre[1] + 1 == val) && (next[0] - 1 == val)) {
            pre[1] = next[1];
            list.remove(next);
        } else if (pre[1] + 1 == val) {
            pre[1] = val;
        } else if (next[0] - 1 == val) {
            next[0] = val;
        } else {
            list.add(r + 1, cur);
        }
    }

    public int[][] getIntervals() {
        int n = list.size();
        int[][] ans = new int[n - 2][2];
        int ids = 0;
        for (int i = 1; i < n - 1; i++) {
            ans[ids++] = list.get(i);
        }
        return ans;
    }
}
