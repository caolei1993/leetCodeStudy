package main.java.com.exercise.week_017;

import java.util.Arrays;

/**
 * @author cl
 * @version 1.0
 * @date 2021/5/18 17:31
 */
public class LeetCode_1442_1_形成两个异或相等数组的三元组数目 {
    public static int countTriplets(int[] arr) {
        int n = arr.length;
        if (n < 2) {
            return 0;
        }
        // 定义前缀和数组
        int[] preArr = new int[n + 1];
        int ans = 0;
        // 初始化前缀和数组的值
        int value = 0;
        for (int i = 1; i <= n; i++) {
            value ^= arr[i - 1];
            preArr[i] = value;
        }
        // 遍历i
        for (int i = 1; i <= n; i++) {
            // 遍历j
            for (int j = i + 1; j <= n; j++) {
                for (int k = j; k <= n; k++) {
                    int a = preArr[i - 1] ^ preArr[j - 1];
                    int b = preArr[j - 1] ^ preArr[k];
                    if (a == b) {
                        ans += 1;
//                        System.out.println("-----i,j,k =" + (i - 1) + "," + (j - 1) + "," + (k - 1));
                    }
                }
            }
        }
        return ans;
    }
}
