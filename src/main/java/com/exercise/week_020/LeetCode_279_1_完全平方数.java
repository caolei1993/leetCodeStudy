package main.java.com.exercise.week_020;

import java.util.ArrayList;
import java.util.List;

/**
 * @Author cl
 * @Date 2021/6/11 15:47
 * @Version 1.0
 */
public class LeetCode_279_1_完全平方数 {
    public int numSquares(int n) {
        // 预处理出所有的完全平方数，存入list
        int ids = 1;
        List<Integer> list = new ArrayList<>();
        while (ids * ids <= n) {
            list.add(ids * ids);
            ids++;
        }
        int length = list.size();
        // 定义dp数组，f[i][j]代表使用前i + 1个完全平方数凑成j的最少平方数数量
        int[][] f = new int[length][n + 1];
        // 预处理使用第一个数的情况，因为第一个完全平方数1，相当于初始化dp数组
        for (int j = 0; j <= n; j++) {
            f[0][j] = j;
        }
        // 处理除第一个数以外的情况
        for (int i = 1; i < length; i++) {
            int t = list.get(i);
            for (int j = 0; j <= n; j++) {
                // 不选择坐标为i的数
                f[i][j] = f[i - 1][j];
                // 选择坐标为i的数
                for (int k = 1; k * t <= j; k++) {
                    f[i][j] = Math.min(f[i][j], f[i - 1][j - k * t] + k);
                }
            }
        }
        return f[length - 1][n];
    }
}
