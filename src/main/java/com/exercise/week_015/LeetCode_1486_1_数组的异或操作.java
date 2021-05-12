package main.java.com.exercise.week_015;

/**
 * @Author cl
 * @Date 2021/5/7 7:33
 * @Version 1.0
 * https://leetcode-cn.com/problems/xor-operation-in-an-array/
 */
public class LeetCode_1486_1_数组的异或操作 {
    public int xorOperation(int n, int start) {
        int[] arr = new int[n];
        int ans = 0;
        for (int i = 0; i < n; i++) {
            arr[i] = start + 2 * i;
            ans = ans ^ arr[i];
        }
        return ans;
    }
}
