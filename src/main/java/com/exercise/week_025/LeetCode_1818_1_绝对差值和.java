package main.java.com.exercise.week_025;

import java.util.TreeSet;

/**
 * @Author cl
 * @Date 2021/7/14 9:19
 * @Version 1.0
 */
public class LeetCode_1818_1_绝对差值和 {
    long mod = (int) 1e9 + 7;
    public int minAbsoluteSumDiff(int[] nums1, int[] nums2) {
        int n = nums1.length;
        // 记录差值总和
        long sum = 0;
        // 记录最大差值
        long max = 0;
        TreeSet<Integer> set = new TreeSet<>();
        for (int num : nums1) {
            set.add(num);
        }
        int minNums1 = set.first();
        int maxNums1 = set.last();

        int d;
        for (int i = 0; i < n; i++) {
            int a = nums1[i], b = nums2[i];
            int v = Math.abs(a - b);
            sum += v;
            if (b >= maxNums1) {
                d = b - maxNums1;
            } else if (b <= minNums1){
                d = minNums1 - b;
            } else {
                // 大于等于b的最小值，并计算差值
                int d1 = Math.abs(set.ceiling(b) - b);
                // 小于等于b的最大值，并计算差值
                int d2 = Math.abs(set.floor(b) - b);
                // 取较小的差值
                d = Math.min(d1, d2);
            }
            max = Math.max(max, Math.abs(v - d));
        }
        return (int)((sum - max) % mod);
    }
}
