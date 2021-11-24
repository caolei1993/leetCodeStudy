package main.java.com.exercise.week_041;

import java.util.Random;

public class LeetCode_384_1_打乱数组 {
    int[] num;
    int n;
    Random random = new Random();

    public LeetCode_384_1_打乱数组(int[] nums) {
        num = nums;
        n = nums.length;
    }

    public int[] reset() {
        return num;
    }

    public int[] shuffle() {
        int[] ans = num.clone();
        for (int i = n - 1; i > 0; i--) {
            swap(ans, i, random.nextInt(i - 1));
        }
        return ans;
    }

    private void swap(int[] ans, int i, int j) {
        int tem = ans[i];
        ans[i] = ans[j];
        ans[j] = tem;
    }
}
