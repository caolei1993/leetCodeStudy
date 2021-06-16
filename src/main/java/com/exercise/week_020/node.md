[toc]
# [LeetCode_494_1_目标和](https://leetcode-cn.com/problems/target-sum/)
## 题目
给你一个整数数组 nums 和一个整数 target 。

向数组中的每个整数前添加 '+' 或 '-' ，然后串联起所有整数，可以构造一个 表达式 ：

例如，nums = [2, 1] ，可以在 2 之前添加 '+' ，在 1 之前添加 '-' ，然后串联起来得到表达式 "+2-1" 。  
返回可以通过上述方法构造的、运算结果等于 target 的不同 表达式 的数目。

示例 1：
```
输入：nums = [1,1,1,1,1], target = 3
输出：5
解释：一共有 5 种方法让最终目标和为 3 。
-1 + 1 + 1 + 1 + 1 = 3
+1 - 1 + 1 + 1 + 1 = 3
+1 + 1 - 1 + 1 + 1 = 3
+1 + 1 + 1 - 1 + 1 = 3
+1 + 1 + 1 + 1 - 1 = 3
```

示例 2：
```
输入：nums = [1], target = 1
输出：1
```

提示：

* 1 <= nums.length <= 20
* 0 <= nums[i] <= 1000
* 0 <= sum(nums[i]) <= 1000
* -1000 <= target <= 100

## 理解
解法一：使用爆搜法，利用递归求取针对每个值做加法和减法的情况，直到所有数值处理完，如果结果与target相同
就返回1，所有的方法相加返回。
解法二：使用动态规划，设立f[i][j]代表前i个数字和为j的所有方案数，i的取值范围为nums的长度，j的取值范围
为[-s,s]，s为所有数字总和。  
转移方程为f[i][j] = f[i - 1][j - nums[i - 1]] + f[i - 1][j + nums[i - 1]]  
f[0][0]=1 为初始条件：代表不考虑任何数，凑出计算结果为 0 的方案数为 1 种。  
为了保证j的取值范围为整数，我们整体将第二纬度右移s个单位，即j的取值为[0, 2*s]。  
初始化也调整了f[0][s] = 1

## 解法一
### 代码
```java
public class LeetCode_494_1_目标和 {
    public int findTargetSumWays(int[] nums, int target) {
        return dfs(nums, target, 0, 0);
    }

    /**
     *
     * @param nums  目标数组
     * @param target  目标值
     * @param i 当前遍历的长度
     * @param cur 当前的计算值
     * @return
     */
    private int dfs(int[] nums, int target, int i, int cur) {
        if (i == nums.length) {
            return cur == target ? 1 : 0;
        }
        int left = dfs(nums, target, i + 1, cur + nums[i]);
        int right = dfs(nums, target, i + 1, cur - nums[i]);
        return left + right;
    }


}
```

## 解法二
### 代码
```java
public class LeetCode_494_2_目标和 {
    public int findTargetSumWays(int[] nums, int target) {
        int n = nums.length;
        // 初始化并计算所有数字的总和
        int s = 0;
        for (int num : nums) {
            s += num;
        }
        // 目标值比所有数字总和都大或者比所有数字总和的负数都小，直接返回0
        if (target > s || target < -s) {
            return 0;
        }
        // 定义f[i][j],代表前i个数字，求取和为j的方案数，j的取值范围为[-s,s]，f[0][0]的值为1，为了避免j为负值，所有值右移s
        int[][] f = new int[n + 1][2 * s + 1];
        f[0][0 + s] = 1;
        for (int i = 1; i <= n ; i++) {
            int x = nums[i - 1];
            for (int j = -s; j <= s; j++) {
                if ((j - x) + s >= 0) {
                    f[i][j + s] += f[i - 1][(j - x) + s];
                }
                if ((j + x) + s <= 2 * s) {
                    f[i][j + s] += f[i - 1][(j + x) + s];
                }
            }
        }
        return f[n][target + s];
    }
}
```

# [LeetCode_1049_1_最后一块石头的重量II](https://leetcode-cn.com/problems/last-stone-weight-ii/)
## 题目
有一堆石头，用整数数组 stones 表示。其中 stones[i] 表示第 i 块石头的重量。

每一回合，从中选出任意两块石头，然后将它们一起粉碎。假设石头的重量分别为 x 和 y，且 x <= y。那么粉碎的可能结果如下：

如果 x == y，那么两块石头都会被完全粉碎；  
如果 x != y，那么重量为 x 的石头将会完全粉碎，而重量为 y 的石头新重量为 y-x。  
最后，最多只会剩下一块 石头。返回此石头 最小的可能重量 。如果没有石头剩下，就返回 0。  

示例 1：
```
输入：stones = [2,7,4,1,8,1]
输出：1
解释：
组合 2 和 4，得到 2，所以数组转化为 [2,7,1,8,1]，
组合 7 和 8，得到 1，所以数组转化为 [2,1,1,1]，
组合 2 和 1，得到 1，所以数组转化为 [1,1,1]，
组合 1 和 1，得到 0，所以数组转化为 [1]，这就是最优值。
```

示例 2：
```
输入：stones = [31,26,33,21,40]
输出：5
```

示例 3：
```
输入：stones = [1,2]
输出：1
```

提示：

* 1 <= stones.length <= 30
* 1 <= stones[i] <= 100

## 理解
动态规划dp问题，可以考虑为将所有石头分为两堆，每堆总和看做新的大石头，按照题意求两个石头重量差的最小值，
相当于求取在所有石头中选石头总和小于等于sum/2的最大值。定义dp二维数组f[i][j]表示考虑前i块石头的情况下，
重量小于等于j的最大值。  
i为石头的总数，j为石头总重量除以2。  
遍历所有石头，每个石头都有选和不选两种情况，不选的时候f[i][j] = f[i - 1][j]，  
选的时候f[i][j] = f[i - 1][j - stones[i - 1]] + stores[i - 1]，结果取两者中的较大值。  
最终结果为两堆重量总和相减，sum-f[n][sum/2]-f[n][sum/2]

### 代码
```java
public class LeetCode_1049_1_最后一块石头的重量II {
    public int lastStoneWeightII(int[] stones) {
        int n = stones.length;
        int sum = 0;
        for (int stone : stones) {
            sum += stone;
        }
        int t = sum / 2;
        int[][] f = new int[n + 1][t + 1];
        for (int i = 1; i <= n ; i++) {
            int s = stones[i - 1];
            for (int j = 0; j <= t ; j++) {
                f[i][j] = f[i - 1][j];
                if (j - s >= 0) {
                    f[i][j] =Math.max(f[i][j], f[i - 1][j - s] + s);
                }
            }
        }
        return Math.abs(sum - f[n][t] - f[n][t]);
    }
}
```


# [LeetCode_879_1_盈利计划](https://leetcode-cn.com/problems/profitable-schemes/)
## 题目
集团里有 n 名员工，他们可以完成各种各样的工作创造利润。

第 i 种工作会产生 profit[i] 的利润，它要求 group[i] 名成员共同参与。如果成员参与了其中一项工作，就不能参与另一项工作。

工作的任何至少产生 minProfit 利润的子集称为 盈利计划 。并且工作的成员总数最多为 n 。

有多少种计划可以选择？因为答案很大，所以 返回结果模 10^9 + 7 的值。

示例 1：
```
输入：n = 5, minProfit = 3, group = [2,2], profit = [2,3]
输出：2
解释：至少产生 3 的利润，该集团可以完成工作 0 和工作 1 ，或仅完成工作 1 。
总的来说，有两种计划。
```

示例 2：
```
输入：n = 10, minProfit = 5, group = [2,3,5], profit = [6,7,8]
输出：7
解释：至少产生 5 的利润，只要完成其中一种工作就行，所以该集团可以完成任何工作。
有 7 种可能的计划：(0)，(1)，(2)，(0,1)，(0,2)，(1,2)，以及 (0,1,2) 。
```

提示：

* 1 <= n <= 100
* 0 <= minProfit <= 100
* 1 <= group.length <= 100
* 1 <= group[i] <= 100
* profit.length == group.length
* 0 <= profit[i] <= 100

## 理解
动态规划题目，普通背包问题只有一个限制就是容量限制，但本题有两个限制即人员总数n限制及最小利润minProfit
限制，普通的背包问题我们一般使用二维动态规划求解，本题则可以使用三维动态规划求解：  
f[i][j][k]表示前i个任务，使用人数不超过j，产生利润不小于k的所有方案数量。
i,j,k的区间确认在此题比较简单即任务总数，人数和最小利润，注意为了方便处理边界任务我们从1开始遍历。  
初始化，f[0][x][0]都初始化为1，代表0个任务，最小利润为0，任意人数的方案数都为1。  
转移方程的考虑，我们在遍历任务过程中，可以选择当前任务，也可以不选择当前任务，  
不选择当前任务：f[i][j][k] = f[i - 1][j][k]  
选择当前任务：首先要确认人数要大于等于当前任务要求的人数，  
f[i][j][k] = f[i - 1][j - group[i]][max(0, k - profit[i])]  
此处需要注意题意要求是不少于最小利润，k-profit[i]可能为负数，因为第三纬度不会为负数，所以我们取差值和0
中的较大值，文意理解为如果当前任务的利润大于等于k，那么之前任务的利润最小可以为0。

### 代码
```java
public class LeetCode_879_1_盈利计划 {
    int v = (int) 1e9 + 7;
    public int profitableSchemes(int n, int minProfit, int[] group, int[] profit) {
        int m = group.length;
        // f[i][j][k]代表前i个任务，使用人数不超过j，至少获得k的利润的方案数
        int[][][] f = new int[m + 1][n + 1][minProfit + 1];
        // 初始化动态规则的值，没有任务，没有利润，无论多少人都是一个方案
        for (int i = 0; i <= n ; i++) {
            f[0][i][0] = 1;
        }
        // 遍历任务
        for (int i = 1; i <= m; i++) {
            int g = group[i - 1], p = profit[i - 1];
            // 遍历人数
            for (int j = 0; j <= n; j++) {
                // 遍历利润
                for (int k = 0; k <= minProfit ; k++) {
                    // 不选第i - 1个任务
                    f[i][j][k] = f[i - 1][j][k];
                    // 选第i-1个任务
                    // 计算差的利润值(当前任务获得的利润大于等于k时，之前的任务允许获取0利润)
                    int value = Math.max(k - p, 0);
                    if (j >= g) {
                        f[i][j][k] += f[i - 1][j - g][value];
                    }
                    if (f[i][j][k] > v) {
                        f[i][j][k] -= v;
                    }
                }
            }
        }
        return f[m][n][minProfit];
    }
}
```

# [LeetCode_518_1_零钱兑换II](https://leetcode-cn.com/problems/coin-change-2/)
## 题目
给定不同面额的硬币和一个总金额。写出函数来计算可以凑成总金额的硬币组合数。假设每一种面额的硬币有无限个。 


示例 1:
```
输入: amount = 5, coins = [1, 2, 5]
输出: 4
解释: 有四种方式可以凑成总金额:
5=5
5=2+2+1
5=2+1+1+1
5=1+1+1+1+1
```

示例 2:
```
输入: amount = 3, coins = [2]
输出: 0
解释: 只用面额2的硬币不能凑成总金额3。
```

示例 3:
```
输入: amount = 10, coins = [10] 
输出: 1
```
 
注意:

你可以假设：

* 0 <= amount (总金额) <= 5000
* 1 <= coin (硬币面额) <= 5000
* 硬币种类不超过 500 种
* 结果符合 32 位符号整数

## 理解
解法一：使用动态规划，使用二维动态规划解决，f[i][j]代表使用前i种硬币，凑成金额j的所有方案数。  
考虑i和j的取值范围，i为金币的种类，j为目标金额。  
初始化动态数组f[0][0] = 1，表示使用0种硬币，凑成金额0的方案数为1。  
考虑状态转移方程，遍历到第i-1种硬币时，总有两种情况，选择第i-1种硬币和不选第i种硬币  
不选，f[i][j] = f[i - 1][j]  
选择第i种，要考虑选[1, j/coins[i-1]]个的情况总和，f[i][j] += f[i - 1][j - k * coins[i - 1]]，k代表
选择个数

解法二：优惠为一维动态规划，省略物品纬度即硬币纬度，f[j]代表凑金额为j的总的方案数。  
j的取值范围为目标金额。  
初始化f[0] = 1，表示凑成金额0的方案数为1，就是什么都不选。  
考虑状态转移方程，还是需要遍历所有的种类金币，遍历每种金币时，都对[0,j]金额的方案数做累加，
直到所有种类的硬币遍历完。

## 解法一
### 代码
```java
public class LeetCode_518_1_零钱兑换II {
    public int change(int amount, int[] coins) {
        int n = coins.length;
        // f[i][j]代表利用前i种硬币凑成金额j总共的方案数
        int[][] f = new int[n + 1][amount + 1];
        // 初始化没有硬币，凑成金额0，方案数为1
        f[0][0] = 1;
        for (int i = 1; i <= n; i++) {
            int value = coins[i -1];
            for (int j = 0; j <= amount; j++) {
                // 不选第i-1种硬币
                f[i][j] = f[i - 1][j];
                // 选第i-1种硬币
                for (int k = 1; k * value <= j; k++) {
                    f[i][j] += f[i - 1][j - k * value];
                }
            }
        }
        return f[n][amount];
    }
}
```

## 解法二
### 代码
```java
public class LeetCode_518_2_零钱兑换II {
    public int change(int amount, int[] coins) {
        int n = coins.length;
        // f[j]代表凑成金额j总共的方案数
        int[] f = new int[amount + 1];
        f[0] = 1;
        for (int i = 1; i <= n; i++) {
            int value = coins[i -1];
            // 金额从value上涨的过程中其实已经包含了选择【1,amount/coins[i-1]】个该种硬币的过程
            for (int j = value; j <= amount; j++) {
                f[j] += f[j - value];
            }
        }
        return f[amount];
    }
}
```
# [LeetCode_279_1_完全平方数](https://leetcode-cn.com/problems/perfect-squares/)
## 题目
给定正整数 n，找到若干个完全平方数（比如 1, 4, 9, 16, ...）使得它们的和等于 n。你需要让组成和的完全平方数的个数最少。

给你一个整数 n ，返回和为 n 的完全平方数的 最少数量 。

完全平方数 是一个整数，其值等于另一个整数的平方；换句话说，其值等于一个整数自乘的积。例如，1、4、9 和 16 都是完全平方数，而 3 和 11 不是。

示例 1：
```
输入：n = 12
输出：3 
解释：12 = 4 + 4 + 4
```

示例 2：
```
输入：n = 13
输出：2
解释：13 = 4 + 9
```
 
提示：

* 1 <= n <= 104

## 理解
解法一：朴素dp解法，预处理出所有符合条件的完全平方数存入list，定义状态为f[i][j]代表使用前i-1个完全
平方数凑成和为j的最少平方数数量  
i和j的取值范围为list的长度和n+1  
初始化值，因为我们知道第一个完全平方数是1，所以有j的值是多少，就需要多少个数来凑成j值，所以初始化所有
的f[0][j] = j  
考虑转移方程，当遍历到i时，我们可以不选择坐标为i的完全平方数，此时f[i][j] = f[i - 1][j]  
我们也可以选择坐标为i的完全平方数，此时需要考虑选几次，k数值范围为[1,j/list.get[i]]，从中取最小值，
f[i][j] = Math.min(f[i][j], f[i - 1][j - k * t] + k) 

解法二：优惠为一维动态规划，省略物品纬度即完全平方数纬度，f[j]代表凑值为j的最少平方数数量。  
j的取值范围为目标值。  
初始化f[0] = 0，表示凑成值为0的数，需要0个平方数，其余值先初始化为一个较大值。  
考虑状态转移方程，还是需要遍历所有的种类满足条件的平方数，遍历每种平方数t时，都对[t,n]值的dp数组重新计算，
求取f[j]与f[j - t]中的较小值，直到所有的平方数遍历完。

## 解法一
### 代码
```java
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
```

## 解法二
### 代码
```java
public class LeetCode_279_2_完全平方数 {
    public int numSquares(int n) {
        // 定义dp数组，f[j]代表凑成j，需要的完全平方数数量
        int[] f = new int[n + 1];
        Arrays.fill(f, 0x3f3f3f3f);
        f[0] = 0;
        for (int i = 1; i * i <= n ; i++) {
            int t = i * i;
            for (int j = t; j <= n; j++) {
                f[j] = Math.min(f[j], f[j - t] + 1);
            }
        }
        return f[n];
    }
}
```

# [LeetCode_278_1_第一个错误的版本](https://leetcode-cn.com/problems/first-bad-version/)
## 题目
你是产品经理，目前正在带领一个团队开发新的产品。不幸的是，你的产品的最新版本没有通过质量检测。由于每个版本都是基于之前的版本开发的，所以错误的版本之后的所有版本都是错的。

假设你有 n 个版本 [1, 2, ..., n]，你想找出导致之后所有版本出错的第一个错误的版本。

你可以通过调用 bool isBadVersion(version) 接口来判断版本号 version 是否在单元测试中出错。实现一个函数来查找第一个错误的版本。你应该尽量减少对调用 API 的次数。

示例:
```
给定 n = 5，并且 version = 4 是第一个错误的版本。

调用 isBadVersion(3) -> false
调用 isBadVersion(5) -> true
调用 isBadVersion(4) -> true

所以，4 是第一个错误的版本。 
```

## 理解
使用二分法求解，

### 代码
```java
public class LeetCode_278_1_第一个错误的版本 {
    public int firstBadVersion(int n) {
        int left = 0, right = n;
        while (left < right) {
            long tmp = (long)left + right >> 1;
            int mid = (int)tmp;
            if (isBadVersion(mid)) {
                right = mid;
            } else {
                left = mid + 1;
            }
        }
        return left;
    }

    private boolean isBadVersion(int mid) {
        return false;
    }
}
```