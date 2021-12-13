[toc]

# [LeetCode_807_1_保持城市天际线](https://leetcode-cn.com/problems/max-increase-to-keep-city-skyline/)
## 理解
首先计算出每行每列的最大值，遍历矩阵每个位置，求取当前位置所在行和列中的最大
值中的较小值，求取最值与当前楼的差值，并累加结果，返回。

时间复杂度为O(n^2)，空间复杂度为O(2n)

### 代码
```java
public class LeetCode_807_1_保持城市天际线 {
    public int maxIncreaseKeepingSkyline(int[][] grid) {
        int n = grid.length;
        int[] row = new int[n];
        int[] col = new int[n];
        // 遍历行
        for (int i = 0; i < n; i++) {
            // 遍历列
            for (int j = 0; j < n; j++) {
                row[i] = Math.max(row[i], grid[i][j]);
                col[j] = Math.max(col[j], grid[i][j]);
            }
        }
        int ans = 0;
        for (int i = 0; i < n; i++) {
            for (int j = 0; j < n; j++) {
                ans += Math.min(row[i], col[j]) - grid[i][j];
            }
        }
        return ans;
    }
}
```