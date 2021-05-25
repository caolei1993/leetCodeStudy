[toc]
# [LeetCode_1787_1_使所有区间的异或结果为零](https://leetcode-cn.com/problems/make-the-xor-of-all-segments-equal-to-zero/)
## 题目
给你一个整数数组 nums​​​ 和一个整数 k​​​​​ 。区间 [left, right]（left <= right）的 异或结果 是对下标位于 left 和 right（包括 left 和 right ）之间所有元素进行 XOR 运算的结果：nums[left] XOR nums[left+1] XOR ... XOR nums[right] 。

返回数组中 要更改的最小元素数 ，以使所有长度为 k 的区间异或结果等于零。

 

示例 1：
```
输入：nums = [1,2,0,3,0], k = 1
输出：3
解释：将数组 [1,2,0,3,0] 修改为 [0,0,0,0,0]
```

示例 2：
```
输入：nums = [3,4,5,2,1,7,3,4,7], k = 3
输出：3
解释：将数组 [3,4,5,2,1,7,3,4,7] 修改为 [3,4,7,3,4,7,3,4,7]
```

示例 3：
```
输入：nums = [1,2,4,1,2,5,1,2,6], k = 3
输出：3
解释：将数组[1,2,4,1,2,5,1,2,6] 修改为 [1,2,3,1,2,3,1,2,3]
```

提示：

* 1 <= k <= nums.length <= 2000
* 0 <= nums[i] < 2^10

## 理解
从示例可以看出，其实就是每k个元素
一组进行重复，我们证明一下：  
假设[i,j]区间的长度为k，nums[i] ^ nums[i+1] ^ …… ^ nums[j] = 0，  
区间整体右移一个长度，nums[i+1] ^ nums[i+2] ^ …… ^ nums[j+1] = 0，  
两个区间的等式整体异或，中间元素重复抵消，最终得到nums[i] ^ nums[j+1] = 0，  
即nums[i] = nums[j+1]，上面的猜想得到了证明。  
将整个数组，分解为k列，n行的矩阵，最后一行可能不会满（因为题意也没
说，nums的长度一定是k的整数倍），问题转化为求取，最终使得每列相等，
同时首行的异或值为0的最小修改次数。  
采用动态规划，f[i][xor]代表考虑前i+1列，且首行前i+1列异或值为
xor的最小修改次数，最终答案为f[k-1][0]。  
第一维的取值范围为[0,k)，根据题意0 <= nums[i] < 2^10，异或
为不进位的加法，所以第二维度的范围为[0,1024)  
我们可以使用map统计每列每个数字出现的次数，用cnt统计每列数字的个数（
因为最后一行可能不满k个）  
不是一般性的考虑转移方程：  
* 如果当前处于第0列，那么f[0][xor]只需要考虑需要把多少个不为xor的
数变为xor，即f[0][xor] = cnt - map.get(xor)  
* 如果不是首列，需要考虑与前一列的关系：  
f[i][xor]由两部分组成，一个是前i-1列的修改次数，以及本列的修改次数。
  1. 这个时候需要分开考虑，只修改本列的部分数据，这个时候需要遍历本列
    的所有数字，找到修改的最小个数。  
    f[i][xor] = f[i-1][xor^num] + (cnt - map.get(num))
  2. 修改本列的所有数据，这个我们就得知道前i-1列的最小的修改次数，
    我们利用g数组提前存储起来
    f[i][xor] = g[i-1] + cnt
     
f[i][xor]的最终值，取以上两种情况中的较小值。

### 代码
```java
public class LeetCode_1787_1_使所有区间的异或结果为零 {
    public int minChanges(int[] nums, int k) {
        int n = nums.length;
        int max = 1024;
        // f[i][j]代表前i+1列，首行异或结果为j的最小修改次数
        int[][] f = new int[k][max];
        // 记录每列最小修改次数的值
        int[] g = new int[k];
        Arrays.fill(g, Integer.MAX_VALUE);

        // 遍历每列, i为列数，cnt记录每列总共有多少个数字
        for (int i = 0, cnt = 0; i < k; i++, cnt = 0) {
            // 记录每列中每个值的个数
            Map<Integer, Integer> map = new HashMap<>();
            for (int j = i; j < n; j += k) {
                map.put(nums[j], map.getOrDefault(nums[j], 0) + 1);
                cnt++;
            }
            // 首列，只需要考虑修改几个得到xor值即可
            if (i == 0) {
                for (int xor = 0; xor < max; xor++) {
                    f[0][xor] = cnt - map.getOrDefault(xor, 0);
                    g[0] = Math.min(g[0], f[0][xor]);
                }
            } else {
                // 非首列，需要考虑前一列
                for (int xor = 0; xor < max; xor++) {
                    // 整列修改
                    f[i][xor] = g[i - 1] + cnt;
                    // 非整列修改
                    for (int num: map.keySet()) {
                        f[i][xor] = Math.min(f[i][xor], f[i - 1][xor ^ num] + cnt - map.get(num));
                    }
                    g[i] = Math.min(g[i], f[i][xor]);
                }
            }
        }
        return f[k - 1][0];
    }
}
```