[toc]

# [LeetCode_447_1_回旋镖的数量](https://leetcode-cn.com/problems/number-of-boomerangs/)
## 题目
给定平面上 n 对 互不相同 的点 points ，其中 points[i] = [xi, yi] 。回旋镖 是由点 (i, j, k) 表示的元组 ，其中 i 和 j 之间的距离和 i 和 k 之间的距离相等（需要考虑元组的顺序）。

返回平面上所有回旋镖的数量。

示例 1：
```
输入：points = [[0,0],[1,0],[2,0]]
输出：2
解释：两个回旋镖为 [[1,0],[0,0],[2,0]] 和 [[1,0],[2,0],[0,0]]
```

示例 2：
```
输入：points = [[1,1],[2,2],[3,3]]
输出：2
```

示例 3：
```
输入：points = [[1,1]]
输出：0
```
 

提示：

* n == points.length
* 1 <= n <= 500
* points[i].length == 2
* -10^4 <= xi, yi <= 10^4
* 所有点都 互不相同

## 理解
我们可以在给定i的情况下，统计与i距离相等的j,k的组合数有多少。  
我们可以借助哈希表预处理，在给定i的情况下，统计所有点与i的距离，存入哈希表中，key为距离，value为点的
个数，然后分别对所有距离进行累加处理。  
为了方便计算，避免使用sqrt，我们直接使用x^2 + y^2来指两个点之间的距离。  
时间复杂度为O(n^2)，空间复杂度为O(n)
### 代码
```java
public class LeetCode_447_1_回旋镖的数量 {
    public int numberOfBoomerangs(int[][] points) {
        int n = points.length;
        int ans = 0;
        for (int i = 0; i < n; i++) {
            Map<Integer, Integer> map = new HashMap<>();
            for (int j = 0; j < n; j++) {
                if (i == j) {
                    continue;
                }
                int x = points[i][0] - points[j][0], y = points[i][1] - points[j][1];
                int value = x * x + y * y;
                map.put(value, map.getOrDefault(value, 0) + 1);
            }
            for (Integer key : map.keySet()) {
                int cnt = map.get(key);
                ans += cnt * (cnt - 1);
            }
        }
        return ans;
    }
}
```