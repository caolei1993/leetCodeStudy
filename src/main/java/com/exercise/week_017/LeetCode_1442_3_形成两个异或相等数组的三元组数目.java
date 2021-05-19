package main.java.com.exercise.week_017;

import java.util.HashMap;
import java.util.List;
import java.util.Map;

/**
 * @author cl
 * @version 1.0
 * @date 2021/5/18 21:05
 */
public class LeetCode_1442_3_形成两个异或相等数组的三元组数目 {
    public static int countTriplets(int[] arr) {
        int n = arr.length;
        if (n < 2) {
            return 0;
        }
        // 定义前缀和数组
        int[] preArr = new int[n + 1];
        int ans = 0;
        // 初始化前缀和数组的值
        for (int i = 1; i <= n; i++) {
            preArr[i] = preArr[i - 1] ^ arr[i - 1];
        }
        Map<Integer, Integer> cntMap = new HashMap<>();
        Map<Integer, Integer> totalMap = new HashMap<>();
        // 遍历i
        for (int i = 0; i < n; i++) {
            // k的坐标至少比i大1（map里存放的preArr的坐标与判断的坐标需要至少相差2）
            // 当i = 1时，map存入的下标到0，判断的下标到2，即判断最小的满足题意的三元组坐标，i,j,k = 0,1,1
            // 当i = n - 1时，map存入的下标到n - 2， 判断下标到n，即判断的是最大满足题意的三元组坐标，i,j,k = n-2,n-1,n-1
            if (cntMap.containsKey(preArr[i + 1])) {
                ans += cntMap.get(preArr[i + 1]) * i - totalMap.get(preArr[i + 1]);
            }
            cntMap.put(preArr[i], cntMap.getOrDefault(preArr[i], 0) + 1);
            totalMap.put(preArr[i], totalMap.getOrDefault(preArr[i], 0) + i);
        }
        return ans;
    }
}
