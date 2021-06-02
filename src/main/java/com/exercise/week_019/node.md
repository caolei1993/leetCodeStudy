[toc]
# [LeetCode_342_1_4的幂](https://leetcode-cn.com/problems/power-of-four/)
## 题目
给定一个整数，写一个函数来判断它是否是 4 的幂次方。如果是，返回 true ；否则，返回 false 。

整数 n 是 4 的幂次方需满足：存在整数 x 使得 n == 4x

示例 1：
```
输入：n = 16
输出：true
```

示例 2：
```
输入：n = 5
输出：false
```

示例 3：
```
输入：n = 1
输出：true
```

 
提示：

* -231 <= n <= 231 - 1
 

进阶：

你能不使用循环或者递归来完成本题吗？

## 理解
解法一：因为为4的幂，那一定是2的幂，将题目转化为求2的幂，4的x次方可以转化为2的2x次方，
也就是，我们先对n开平方得到x，再看x是否是2的幂即可。
解法二：因为4的幂，值转化为二进制，有且只有一个1，并且1在偶数位上，位数从0开始数，也就是
n即为2的幂，且n与上0101 0101 0101 0101 0101 0101....32位数的值为0。
解法三：4的幂的值除以3取余为1，证明：  
4 ^ n = (3 + 1) ^ n = a1 * 3 ^ n + a2 * 3 ^ (n - 1) ... + an * 3 + 1  
如果是2的幂，又不是4的幂，数字可以表示为4^n * 2，按上面的推导该值除以3取余为2。

## 解法一
### 代码
```java
public class LeetCode_342_1_4的幂 {
    public boolean isPowerOfFour(int n) {
        if (n <= 0) {
            return false;
        }
        int x = (int)Math.sqrt(n);
        return x * x == n && (x & -x) == x;
    }
}
```

## 解法二
### 代码
```java
public class LeetCode_342_2_4的幂 {
    public boolean isPowerOfFour(int n) {
        return n > 0 && (n & -n) == n && (n & 0xaaaaaaaa) == 0;
    }
}
```

## 解法三
### 代码
```java
public class LeetCode_342_3_4的幂 {
    public boolean isPowerOfFour(int n) {
        return (n & -n) == n && n % 3 == 1;
    }
}
```

# [LeetCode_1744_1_在指定天吃到想吃的糖果](https://leetcode-cn.com/problems/can-you-eat-your-favorite-candy-on-your-favorite-day/)
## 题目
给你一个下标从 0 开始的正整数数组 candiesCount ，其中 candiesCount[i] 表示你拥有的第 i 类糖果的数目。同时给你一个二维数组 queries ，其中 queries[i] = [favoriteTypei, favoriteDayi, dailyCapi] 。

你按照如下规则进行一场游戏：

你从第 0 天开始吃糖果。  
你在吃完 所有 第 i - 1 类糖果之前，不能 吃任何一颗第 i 类糖果。  
在吃完所有糖果之前，你必须每天 至少 吃 一颗 糖果。  
请你构建一个布尔型数组 answer ，满足 answer.length == queries.length 。answer[i] 为 true 的条件是：在每天吃 不超过 dailyCapi 颗糖果的前提下，你可以在第 favoriteDayi 天吃到第 favoriteTypei 类糖果；否则 answer[i] 为 false 。注意，只要满足上面 3 条规则中的第二条规则，你就可以在同一天吃不同类型的糖果。

请你返回得到的数组 answer 。

示例 1：
```
输入：candiesCount = [7,4,5,3,8], queries = [[0,2,2],[4,2,4],[2,13,1000000000]]
输出：[true,false,true]
提示：
1- 在第 0 天吃 2 颗糖果(类型 0），第 1 天吃 2 颗糖果（类型 0），第 2 天你可以吃到类型 0 的糖果。
2- 每天你最多吃 4 颗糖果。即使第 0 天吃 4 颗糖果（类型 0），第 1 天吃 4 颗糖果（类型 0 和类型 1），你也没办法在第 2 天吃到类型 4 的糖果。换言之，你没法在每天吃 4 颗糖果的限制下在第 2 天吃到第 4 类糖果。
3- 如果你每天吃 1 颗糖果，你可以在第 13 天吃到类型 2 的糖果。
```

示例 2：
```
输入：candiesCount = [5,2,6,4,1], queries = [[3,1,2],[4,10,3],[3,10,100],[4,100,30],[1,3,1]]
输出：[false,true,true,false,false]
```

提示：

* 1 <= candiesCount.length <= 105
* 1 <= candiesCount[i] <= 105
* 1 <= queries.length <= 105
* queries[i].length == 3
* 0 <= favoriteTypei < candiesCount.length
* 0 <= favoriteDayi <= 109
* 1 <= dailyCapi <= 109

## 理解
利用前缀和来记录想吃第i类糖前要吃掉多少颗糖，在遍历第i个queries时，我们可以确认
当前要吃第几类糖type，在哪天day，以及每天最多吃多少颗daily，我们可以算出要吃第type
类糖需要的最少天数min和最大天数max（需要注意天数可能会超过int的取值范围），只要day
在[min,max]中ans[i] = true，否则为false。需要注意我们算的天数是从1开始，题意从0开始
所以day = queries[i][1] + 1

### 代码
```java
public class LeetCode_1744_1_在指定天吃到想吃的糖果 {
    public boolean[] canEat(int[] candiesCount, int[][] queries) {
        int n = candiesCount.length, m = queries.length;
        boolean[] ans = new boolean[m];
        // 求取前缀和，来确认吃第i类糖果前共需要吃多少个糖果
        long[] sum = new long[n + 1];
        for (int i = 1; i <= n; i++) {
            sum[i] = sum[i - 1] + candiesCount[i - 1];
        }
        for (int i = 0; i < m; i++) {
            int type = queries[i][0], day = queries[i][1] + 1, daily = queries[i][2];
            long min = sum[type] / daily + 1, max = sum[type + 1];
            ans[i] = day >= min && day <= max;
        }
        return ans;
    }
}
```

# [LeetCode_523_1_连续的子数组和](https://leetcode-cn.com/problems/continuous-subarray-sum/)
## 题目
给你一个整数数组 nums 和一个整数 k ，编写一个函数来判断该数组是否含有同时满足下述条件的连续子数组：

子数组大小 至少为 2 ，且  
子数组元素总和为 k 的倍数。  
如果存在，返回 true ；否则，返回 false 。  

如果存在一个整数 n ，令整数 x 符合 x = n * k ，则称 x 是 k 的一个倍数。

示例 1：
```
输入：nums = [23,2,4,6,7], k = 6
输出：true
解释：[2,4] 是一个大小为 2 的子数组，并且和为 6 。
```

示例 2：
```
输入：nums = [23,2,6,4,7], k = 6
输出：true
解释：[23, 2, 6, 4, 7] 是大小为 5 的子数组，并且和为 42 。 
42 是 6 的倍数，因为 42 = 7 * 6 且 7 是一个整数。
```

示例 3：
```
输入：nums = [23,2,6,4,7], k = 13
输出：false
```

提示：

* 1 <= nums.length <= 105
* 0 <= nums[i] <= 109
* 0 <= sum(nums[i]) <= 231 - 1
* 1 <= k <= 231 - 1

## 理解
利用前缀和加HashSet来求解，前缀和是为了快速求取某个子区间的值（O(1)复杂度下求取）。  
我们假设[i,j]区间的和是k的倍数，所以有 sum[j] - sum[i - 1] = n * k  
等式两边同时除以k得：sum[j]/k - sum[i - 1]/k = n  
n是个整数，所以我们只需要保证sum[j]和sum[i - 1]除以k取余的结果相同即可。
所以我们遍历区间的右边界，在其左边找是否存在除以k取余结果与之相等的i即可（这里需要
注意子数组的最小长度是2，所以我们前缀和数组的差值最小要等于2），注意按本题的题意n=0
即区间的结果为0，多个连续元素为0，这种子区间也是满足的。

证明： a = x1 * k + y1，b = x2 * k + y2  
       a - b = （x1 - x2）k + y1 - y2
       所以要想a-b是k的一个倍数，y1 - y2 = 0，y1和y2分别为数字a和b除以k取余的值  

### 代码
```java
public class LeetCode_523_1_连续的子数组和 {
    public boolean checkSubarraySum(int[] nums, int k) {
        int n = nums.length;
        // 创建并初始化前缀和数组
        int[] sum = new int[n + 1];
        for (int i = 1; i <= n ; i++) {
            sum[i] = sum[i - 1] + nums[i - 1];
        }
        Set<Integer> set = new HashSet<>();
        for (int i = 2; i <= n; i++) {
            set.add(sum[i - 2] % k);
            if (set.contains(sum[i] % k)) {
                return true;
            }
        }
        return false;
    }
}
```