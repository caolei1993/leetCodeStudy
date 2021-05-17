package main.java.com.exercise.week_016;

/**
 * @author cl
 * @version 1.0
 * @date 2021/5/12 21:18
 */
public class LeetCode_1310_1_子数组异或查询 {
    public int[] xorQueries(int[] arr, int[][] queries) {
        int n = queries.length;
        int[] ans = new int[n];
        for (int i = 0; i < n ; i++) {
            int[] subArr = queries[i];
            int value = 0;
            for (int j = subArr[0]; j <= subArr[1]; j++) {
                value = value ^ arr[j];
            }
            ans[i] = value;
        }
        return ans;
    }
}
