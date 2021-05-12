[toc]
# [LeetCode_7_1_整数反转](https://leetcode-cn.com/problems/reverse-integer/)
## 题目
给你一个 32 位的有符号整数 x ，返回将 x 中的数字部分反转后的结果。

如果反转后整数超过 32 位的有符号整数的范围 [−231,  231 − 1] ，就返回 0。

假设环境不允许存储 64 位整数（有符号或无符号）。
 

示例 1：
```
输入：x = 123
输出：321
```

示例 2：
```
输入：x = -123
输出：-321
```

示例 3：
```
输入：x = 120
输出：21
```

示例 4：
```
输入：x = 0
输出：0
```

提示：

* -231 <= x <= 231 - 1

## 理解
解法一：因为题意要求假设环境不允许存储64位整数，超出int范围就返回0，我们还是使用了long，只是
对超出int范围的值，特殊处理。
解法二：严格遵守，不使用long，分别对正数负数做了int的边界判断处理。

## 解法一
### 代码
```java
public class LeetCode_7_1_整数反转 {
    public int reverse(int x) {
        long ans = 0;
        while (x != 0) {
            ans = ans * 10 + x % 10;
            x = x / 10;
        }
        return (int)ans == ans ? (int)ans : 0;
    }
}
```

## 解法二
### 代码
```java
public class LeetCode_7_2_整数反转 {
    public int reverse(int x) {
        int ans = 0;
        while (x != 0) {
            if (x > 0 && ans > (Integer.MAX_VALUE - x % 10) / 10) {
                return 0;
            }
            if (x < 0 && ans < (Integer.MIN_VALUE - x % 10) / 10) {
                return 0;
            }
            ans = ans * 10 + x % 10;
            x = x / 10;
        }
        return ans;
    }
}
```


# [LeetCode_740_1_删除并获得点数](https://leetcode-cn.com/problems/delete-and-earn/)
## 题目
给你一个整数数组 nums ，你可以对它进行一些操作。

每次操作中，选择任意一个 nums[i] ，删除它并获得 nums[i] 的点数。之后，你必须删除每个等于 nums[i] - 1 或 nums[i] + 1 的元素。

开始你拥有 0 个点数。返回你能通过这些操作获得的最大点数。


示例 1：
```
输入：nums = [3,4,2]
输出：6
解释：
删除 4 获得 4 个点数，因此 3 也被删除。
之后，删除 2 获得 2 个点数。总共获得 6 个点数。
```

示例 2：
```
输入：nums = [2,2,3,3,3,4]
输出：9
解释：
删除 3 获得 3 个点数，接着要删除两个 2 和 4 。
之后，再次删除 3 获得 3 个点数，再次删除 3 获得 3 个点数。
总共获得 9 个点数。
```

提示：

* 1 <= nums.length <= 2 * 104
* 1 <= nums[i] <= 104

## 理解
该题为打家劫舍的变形题种，同样是动态规划问题，首先我们看题意，就是选了当前值，那相邻的值不能选，
所以由此我们确认我们的转移方程，f[i][0]代表不选择i，获得的所有点数，f[i][1]代表选择i，获得的所有
点数：  
f[i][0] = Math.max(f[i - 1][0], f[i - 1][1])  
f[i][1] = f[i - 1][0] + i * count[i]  
我们预先统计给定数组中每个值出现的次数，因为nums[i] <= 10四次方，我们就初始化一个10001长度的
数组来存储每个值出现的次数，顺便求出数组中的最大值，方便下面DP问题的转移。

### 代码
```java
public class LeetCode_740_1_删除并获得点数 {
    int[] cnts = new int[10001];
    public int deleteAndEarn(int[] nums) {
        int max = Integer.MIN_VALUE;
        for (int num : nums) {
            cnts[num]++;
            max = Math.max(max, num);
        }
        // f[i][0]代表i未被选择的获得的所有点数，f[i][1]代表i被选择获得的所有点数
        int[][] f = new int[max + 1][2];
        for (int i = 1; i <= max; i++) {
            f[i][0] = Math.max(f[i - 1][0], f[i - 1][1]);
            f[i][1] = f[i - 1][0] + i * cnts[i];
        }
        return Math.max(f[max][0], f[max][1]);
    }
}
```
# [LeetCode_1720_1_解码异或后的数组](https://leetcode-cn.com/problems/decode-xored-array/)
## 题目
未知 整数数组 arr 由 n 个非负整数组成。

经编码后变为长度为 n - 1 的另一个整数数组 encoded ，其中 encoded[i] = arr[i] XOR arr[i + 1] 。例如，arr = [1,0,2,1] 经编码后得到 encoded = [1,2,3] 。

给你编码后的数组 encoded 和原数组 arr 的第一个元素 first（arr[0]）。

请解码返回原数组 arr 。可以证明答案存在并且是唯一的。

 

示例 1：
```
输入：encoded = [1,2,3], first = 1
输出：[1,0,2,1]
解释：若 arr = [1,0,2,1] ，那么 first = 1 且 encoded = [1 XOR 0, 0 XOR 2, 2 XOR 1] = [1,2,3]
```

示例 2：
```
输入：encoded = [6,2,7,3], first = 4
输出：[4,2,0,7,4]
```

提示：

* 2 <= n <= 104
* encoded.length == n - 1
* 0 <= encoded[i] <= 105
* 0 <= first <= 105

## 理解
根据二进制的异或运算三条特性：  
1. 两值相等为0，两值不同为1
2. 任何数与0异或都等于其本身  
3. 异或运算遵循结合律和交换律  
我们从题意知，encoded[i] = arr[i] ^ arr[i + 1]，两边同时异或上arr[i]，即  
   encoded[i] ^ arr[i] = arr[i] ^ arr[i] ^ arr[i + 1]  
   arr[i] ^ arr[i] = 0， 0 ^ arr[i + 1] = arr[i + 1]  
   即 arr[i + 1] = encoded[i] ^ arr[i]  
根据此公式，我们容易求出原数组arr.

### 代码
```java
public class LeetCode_1720_1_解码异或后的数组 {
    public int[] decode(int[] encoded, int first) {
        int n = encoded.length;
        int[] arr = new int[n + 1];
        arr[0] = first;
        for (int i = 1; i < n + 1; i++) {
            arr[i] = encoded[i - 1] ^ arr[i - 1];
        }
        return arr;
    }
}
```
# [LeetCode_1486_1_数组的异或操作](https://leetcode-cn.com/problems/xor-operation-in-an-array/)
## 题目
给你两个整数，n 和 start 。

数组 nums 定义为：nums[i] = start + 2*i（下标从 0 开始）且 n == nums.length 。

请返回 nums 中所有元素按位异或（XOR）后得到的结果。

 

示例 1：
```
输入：n = 5, start = 0
输出：8
解释：数组 nums 为 [0, 2, 4, 6, 8]，其中 (0 ^ 2 ^ 4 ^ 6 ^ 8) = 8 。
     "^" 为按位异或 XOR 运算符。
```

示例 2：
```
输入：n = 4, start = 3
输出：8
解释：数组 nums 为 [3, 5, 7, 9]，其中 (3 ^ 5 ^ 7 ^ 9) = 8.
```

示例 3：
```
输入：n = 1, start = 7
输出：7
```

示例 4：
```
输入：n = 10, start = 5
输出：2
```

提示：

* 1 <= n <= 1000
* 0 <= start <= 1000
* n == nums.length

## 理解
按题意求出原数组，求取过程中不断异或操作，注意ans的初始值为0，因为0异或任何数都得任何数，
最终返回ans即可。

### 代码
```java
public class LeetCode_1486_1_数组的异或操作 {
    public int xorOperation(int n, int start) {
        int[] arr = new int[n];
        int ans = 0;
        for (int i = 0; i < n; i++) {
            arr[i] = start + 2 * i;
            ans = ans ^ arr[i];
        }
        return ans;
    }
}
```

# [LeetCode_1482_1_制作m束花所需的最少天数](https://leetcode-cn.com/problems/minimum-number-of-days-to-make-m-bouquets/)
## 题目
给你一个整数数组 bloomDay，以及两个整数 m 和 k 。

现需要制作 m 束花。制作花束时，需要使用花园中 相邻的 k 朵花 。

花园中有 n 朵花，第 i 朵花会在 bloomDay[i] 时盛开，恰好 可以用于 一束 花中。

请你返回从花园中摘 m 束花需要等待的最少的天数。如果不能摘到 m 束花则返回 -1 。

 

示例 1：
```
输入：bloomDay = [1,10,3,10,2], m = 3, k = 1
输出：3
解释：让我们一起观察这三天的花开过程，x 表示花开，而 _ 表示花还未开。
现在需要制作 3 束花，每束只需要 1 朵。
1 天后：[x, _, _, _, _]   // 只能制作 1 束花
2 天后：[x, _, _, _, x]   // 只能制作 2 束花
3 天后：[x, _, x, _, x]   // 可以制作 3 束花，答案为 3
```

示例 2：
```
输入：bloomDay = [1,10,3,10,2], m = 3, k = 2
输出：-1
解释：要制作 3 束花，每束需要 2 朵花，也就是一共需要 6 朵花。而花园中只有 5 朵花，无法满足制作要求，返回 -1 。
```

示例 3：
```
输入：bloomDay = [7,7,7,7,12,7,7], m = 2, k = 3
输出：12
解释：要制作 2 束花，每束需要 3 朵。
花园在 7 天后和 12 天后的情况如下：
7 天后：[x, x, x, x, _, x, x]
可以用前 3 朵盛开的花制作第一束花。但不能使用后 3 朵盛开的花，因为它们不相邻。
12 天后：[x, x, x, x, x, x, x]
显然，我们可以用不同的方式制作两束花。
```

示例 4：
```
输入：bloomDay = [1000000000,1000000000], m = 1, k = 1
输出：1000000000
解释：需要等 1000000000 天才能采到花来制作花束
```

示例 5：
```
输入：bloomDay = [1,10,2,9,3,8,4,7,5,6], m = 4, k = 2
输出：9
```

提示：

* bloomDay.length == n
* 1 <= n <= 10^5
* 1 <= bloomDay[i] <= 10^9
* 1 <= m <= 10^6
* 1 <= k <= n

## 理解
利用二分法解答，因为最小的天数可以将整个天数区间分为两段，[0,ans)段不能收集满m束花，[ans，max]
段都可以收集满m束花，所以根据这个两段性，我们利用二分法求解，因为二分的效率为logn级别，所以
我们可以粗略的估计出上下界[0,1e9]，1e9为题目给出的最大值。判断某个天数中能否收集m束花时，
遍历目标数组，只要数组的值小于天数，花朵+1，花朵满k朵，花束+1，最终花束>=m，返回true，否则
返回false。再利用值不断缩小区间，最终找到最小天数。

###代码
```java
public class LeetCode_1482_1_制作m束花所需的最少天数 {
    public int minDays(int[] bloomDay, int m, int k) {
        int n = bloomDay.length;
        // 不够凑成m束花
        if (n < m * k) {
            return -1;
        }

        int l = 0, r = (int)1e9;
        while (l < r) {
            int mid = (l + r) >> 1;
            if (check(bloomDay, mid, m, k)) {
                r = mid;
            } else {
                l = mid + 1;
            }
        }
        return l;
    }

    private boolean check(int[] bloomDay, int mid, int m, int k) {
        int bouquets = 0;
        int flowers = 0;
        for (int i = 0; i < bloomDay.length; i++) {
            if (bloomDay[i] <= mid) {
                flowers++;
                if (flowers == k){
                    bouquets++;
                    flowers = 0;
                }
            } else {
                flowers = 0;
            }
        }
        return bouquets >= m;
    }
}
```