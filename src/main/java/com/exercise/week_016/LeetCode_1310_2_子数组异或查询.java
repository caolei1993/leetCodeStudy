package main.java.com.exercise.week_016;

/**
 * @author cl
 * @version 1.0
 * @date 2021/5/16 16:57
 */
public class LeetCode_1310_2_子数组异或查询 {
    public int[] xorQueries(int[] arr, int[][] queries) {
        int m = arr.length;
        // 定义前缀和数组（这里做异或操作）
        int[] preArr = new int[m];
        int value = 0;
        // 初始化前缀和数组
        for (int i = 0; i < m; i++) {
            value = value ^ arr[i];
            preArr[i] = value;
        }

        int n = queries.length;
        int[] ans = new int[n];

        for (int i = 0; i < n ; i++) {
            int[] subArr = queries[i];
            ans[i] = subArr[0] == 0 ? preArr[subArr[1]] : preArr[subArr[0] - 1] ^ preArr[subArr[1]];
        }
        return ans;
    }
}
