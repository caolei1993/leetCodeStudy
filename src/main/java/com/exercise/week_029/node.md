[toc]
# [LeetCode_313_1_超级丑数](https://leetcode-cn.com/problems/super-ugly-number/)
## 题目
超级丑数 是一个正整数，并满足其所有质因数都出现在质数数组 primes 中。

给你一个整数 n 和一个整数数组 primes ，返回第 n 个 超级丑数 。

题目数据保证第 n 个 超级丑数 在 32-bit 带符号整数范围内。

 
示例 1：
```
输入：n = 12, primes = [2,7,13,19]
输出：32 
解释：给定长度为 4 的质数数组 primes = [2,7,13,19]，前 12 个超级丑数序列为：[1,2,4,7,8,13,14,16,19,26,28,32] 。
```

示例 2：
```
输入：n = 1, primes = [2,3,5]
输出：1
解释：1 不含质因数，因此它的所有质因数都在质数数组 primes = [2,3,5] 中。
```

 
提示：

* 1 <= n <= 10^6
* 1 <= primes.length <= 100
* 2 <= primes[i] <= 1000
* 题目数据 保证 primes[i] 是一个质数
* primes 中的所有值都 互不相同 ，且按 递增顺序 排列

## 理解
* 解法一：使用优先队列求解（最小堆）  
首先将第一个丑数1入队，接着我们不断从队列中取最小值和primes提供的质数因子相乘得到新的丑数存入队列，
此时需要注意防止相同的丑数重复入队，可以使用set去重，当我们第n次从队列中取出值时即为第n个丑数。  
时间复杂度为O(nmlog(nm))，m是primes的长度，优先队列的每次循环都需要取出一个元素，入队约m个元素，
，时间复杂度为O(lognm + mlognm)，可以看做O(mlognm)，总共遍历n次，所以整体为O(nmlognm)，空间复杂度也为O(n*m)



## 解法一
### 代码
```java
public class LeetCode_313_1_超级丑数 {
    public int nthSuperUglyNumber(int n, int[] primes) {
        Set<Long> set = new HashSet<>();
        PriorityQueue<Long> queue = new PriorityQueue<>();
        set.add(1L);
        queue.add(1L);

        while (n >= 0) {
            long v = queue.poll();
            if (n == 0) {
                return (int) v;
            }
            for (int prime : primes) {
                long value = v * prime;
                if (!set.contains(value)) {
                    set.add(value);
                    queue.add(value);
                }
            }
        }
        return -1;
    }
}
```


# [LeetCode_413_1_等差数列划分](https://leetcode-cn.com/problems/arithmetic-slices/)
## 题目
如果一个数列 至少有三个元素 ，并且任意两个相邻元素之差相同，则称该数列为等差数列。

* 例如，[1,3,5,7,9]、[7,7,7,7] 和 [3,-1,-5,-9] 都是等差数列。

给你一个整数数组 nums ，返回数组 nums 中所有为等差数组的 子数组 个数。

子数组 是数组中的一个连续序列。

 
示例 1：
```
输入：nums = [1,2,3,4]
输出：3
解释：nums 中有三个子等差数组：[1, 2, 3]、[2, 3, 4] 和 [1,2,3,4] 自身。
```

示例 2：
```
输入：nums = [1]
输出：0
```


提示：

* 1 <= nums.length <= 5000
* -1000 <= nums[i] <= 1000

## 理解
我们可以确认子数组的左端点，通过遍历找等差数组的右端点，通过这种双指针的方式，找到一个等差子数组
[i, j]，假设区间长度为len，那么下一步我们就得求取这个子数组共包含了多少个等差数组方案。  
取子数组长度为len的方案数，结果为1，取子数组长度为3的方案数，结果为len - 3 + 1，总结可知，长度为
len的子数组所包含的所有方案数其实是首项是1，末项是len - 3 + 1的等差数列的求和结果，直接使用
等差数列求和公式，（首项 + 末项） * 项数 / 2。  
最终把各个子等差数组的结果累加求得最终结果

### 代码
```java
public class LeetCode_413_1_等差数列划分 {
    public int numberOfArithmeticSlices(int[] nums) {
        int n = nums.length;
        int ans = 0;
        // 遍历子数组左边界
        for (int i = 0; i < n - 2 ;) {
            int j = i, d = nums[i + 1] - nums[i];
            while (j + 1 < n && nums[j + 1] - nums[j] == d) {
                j++;
            }
            int len = j - i + 1;
            int a1 = 1, an = len - 3 + 1;
            ans += (a1 + an) * an / 2;
            i = j;
        }
        return ans;
    }
}
```

# [LeetCode_446_1_等差数列划分II_子序列](https://leetcode-cn.com/problems/arithmetic-slices-ii-subsequence/)
## 题目
给你一个整数数组 nums ，返回 nums 中所有 等差子序列 的数目。

如果一个序列中 至少有三个元素 ，并且任意两个相邻元素之差相同，则称该序列为等差序列。

* 例如，[1, 3, 5, 7, 9]、[7, 7, 7, 7] 和 [3, -1, -5, -9] 都是等差序列。
* 再例如，[1, 1, 2, 5, 7] 不是等差序列。

数组中的子序列是从数组中删除一些元素（也可能不删除）得到的一个序列。

* 例如，[2,5,10] 是 [1,2,1,2,4,1,5,10] 的一个子序列。

题目数据保证答案是一个 32-bit 整数。

 
示例 1：
```
输入：nums = [2,4,6,8,10]
输出：7
解释：所有的等差子序列为：
[2,4,6]
[4,6,8]
[6,8,10]
[2,4,6,8]
[4,6,8,10]
[2,4,6,8,10]
[2,6,10]
```

示例 2：
```
输入：nums = [7,7,7,7,7]
输出：16
解释：数组中的任意子序列都是等差子序列。
```
 

提示：

* 1  <= nums.length <= 1000
* -2^31 <= nums[i] <= 2^31 - 1

## 理解
动态规划 + 哈希表：  
定义状态f[i][d]代表以nums[i]为尾项，差值为d的等差数列的数量（包含弱等差子序列，即只包含两个元素
的子序列）  
我们可以利用二重循环来遍历nums中的所有元素对（nums[i], nums[j]）, 其中j < i，将nums[i]，nums[j]
分别看为子等差序列的尾项和倒数第二项，差值可以通过nums[i] - nums[j]来计算，因为公差相同，我们
可以将nums[i]加到以nums[j]为结尾的差值为d的等差子序列的结尾构成新的子序列，所以有f[i][d] += f[j][d],
同时还有(nums[j], nums[i])这组弱等差子序列，所以转移方程为f[i][d] += f[j][d] + 1  

因为题目要求等差子序列至少包含3个元素，回顾我们的二重循环，我们有将nums[i]接到nums[j]后构成新
的等差子序列的操作，并添加上nums[j],nums[i]这个弱的等差子序列，其实我们不看这后一步，第一步
nums[i]拼接上后，就保证了子序列至少3个元素的条件，因为原始的子序列至少是弱等差子序列。因此我们
在循环中不断累加f[j][d]就是最终的答案  

由于nums[i]的取值范围很大，所以差值的范围也很大，**超过了int的取值范围**，所以我们将第二维用哈希表
来替代。

时间复杂度O(n^2)，空间复杂度O(n^2)

### 代码
```java
public class LeetCode_446_1_等差数列划分II_子序列 {
    public int numberOfArithmeticSlices(int[] nums) {
        int n = nums.length;
        int ans = 0;
        // maps[i] 代表以nums[i]为尾项的所有等差子序列的集合，map的k，v分别代表差值和子序列数量
        Map<Long, Integer>[] maps = new Map[n];
        // 初始化数组中的所有map对象
        for (int i = 0; i < n; i++) {
            maps[i] = new HashMap<>();
        }

        for (int i = 0; i < n; i++) {
            for (int j = 0; j < i; j++) {
                long d = 1L * nums[i] - nums[j];
                int cnts = maps[j].getOrDefault(d, 0);
                ans += cnts;
                maps[i].put(d, maps[i].getOrDefault(d, 0) + cnts + 1);
            }
        }
        return ans;
    }
}
```

# [LeetCode_516_1_最长回文子序列](https://leetcode-cn.com/problems/longest-palindromic-subsequence/)
## 题目
给你一个字符串 s ，找出其中最长的回文子序列，并返回该序列的长度。

子序列定义为：不改变剩余字符顺序的情况下，删除某些字符或者不删除任何字符形成的一个序列。

 
示例 1：
```
输入：s = "bbbab"
输出：4
解释：一个可能的最长回文子序列为 "bbbb" 。
```

示例 2：
```
输入：s = "cbbd"
输出：2
解释：一个可能的最长回文子序列为 "bb" 。
```
 

提示：

* 1 <= s.length <= 1000
* s 仅由小写英文字母组成

## 理解
使用动态规划求解  
* 首先定义状态，f[i][j]代表下标从i到j的子字符串中所包换的最大回文子序列的长度
* 数据初始化，f[i][i]均代表只包含单个字符的回文串，长度均为1、
* 转移方程：我们判断s中第i个和第j个字符是否相等，如果相等则有f[i][j] = f[i+1][j-1] + 2，
如果不相等则有f[i][j] = Math.max(f[i][j-1], f[i+1][j])
* 因为我们观察转移方程i需要由i+1转移而来，所以我们从达到下遍历首端i，j则从i后面从小到大遍历
* 最终结果返回f[0][n-1]

时间复杂度为O(n^2)，空间复杂度也为O(n^2)

### 代码
```java
public class LeetCode_516_1_最长回文子序列 {
    public int longestPalindromeSubseq(String s) {
        int n = s.length();
        // f[i][j]代表下标i到j的子字符串中包含的最大的回文子序列长度
        int[][] f = new int[n][n];

        for (int i = n - 1; i >= 0 ; i--) {
            // 包含单个字符为长度为1的回文
            f[i][i] = 1;
            for (int j = i + 1; j < n; j++) {
                if (s.charAt(i) == s.charAt(j)) {
                    f[i][j] = f[i + 1][j - 1] + 2;
                } else {
                    f[i][j] = Math.max(f[i + 1][j], f[i][j - 1]);
                }
            }
        }
        return f[0][n - 1];
    }
}
```

# [LeetCode_233_1_数字1的个数](https://leetcode-cn.com/problems/number-of-digit-one/)
## 题目
给定一个整数 n，计算所有小于等于 n 的非负整数中数字 1 出现的个数。

示例 1：
```
输入：n = 13
输出：6
```

示例 2：
```
输入：n = 0
输出：0
```

提示：

* 0 <= n <= 2 * 10^9

## 理解
题目要求计算小于等于目标值的所有正整数中包含1的个数，我们可以逐位计算，即计算个位，十位，百位等等1的
个数，再进行累加，求取最终的结果。  
以n = 1234举例，我们求取百位的1的个数，百位完整的1的个数有100个，[100, 199]共100个， 我们用n/1000
可以得到有多少个完整的百位，n/1000 x 100 就可以得到完整的百位的1的个数，再求n % 1000就是余下的值m，分
情况讨论：  
* m < 100则百位的1的个数为0
* 100 <= m < 200，百位1的个数等于m - 100 + 1
* m >= 200，百位上可以完整的出现100次1，即[100, 199]共百位100个1

我们可以发现当 m < 100，我们求取 m - 100 + 1是小于等于0的，我们期望值是0，当m >= 200，m - 100 + 1
是大于100的，我们的期望值是100。归纳下来我们求取m中百位1的个数可以为min(max(m - 100 + 1, 0), 100)  
依次从最低位开始求取每一位的1的个数累加

时间复杂度O(m)，m是n的具体位数，空间复杂度为O(1)

### 代码
```java
public class LeetCode_233_1_数字1的个数 {
    public int countDigitOne(int n) {
        int ans = 0;
        for (long mult = 1; mult <= n ; mult *= 10) {
            ans += (n / (mult * 10)) * mult + Math.min(Math.max(n % (mult * 10) - mult + 1, 0), mult);
        }
        return ans;
    }
}
```