package main.java.com.exercise.week_017;

import java.util.HashMap;
import java.util.List;
import java.util.Map;

/**
 * @author cl
 * @version 1.0
 * @date 2021/5/18 22:14
 */
public class LeetCode_1442_2_形成两个异或相等数组的三元组数目 {
    public static int countTriplets(int[] arr) {
        int n = arr.length;
        if (n < 2) {
            return 0;
        }

        int ans = 0;
        // 遍历i的位置
        for (int i = 0; i < n-1; i++) {
            int sum = arr[i];
            // 遍历k的位置
            for (int k = i + 1; k < n; k++) {
                sum ^= arr[k];
                if (sum == 0) {
                    ans += k - i;
                }
            }
        }
        return ans;
    }
}
