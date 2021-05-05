[toc]
# [LeetCode_27_1_移除元素](https://leetcode-cn.com/problems/remove-element/)
## 题目
给你一个数组 nums 和一个值 val，你需要 原地 移除所有数值等于 val 的元素，并返回移除后数组的新长度。

不要使用额外的数组空间，你必须仅使用 O(1) 额外空间并 原地 修改输入数组。

元素的顺序可以改变。你不需要考虑数组中超出新长度后面的元素。

说明:

为什么返回数值是整数，但输出的答案是数组呢?

请注意，输入数组是以「引用」方式传递的，这意味着在函数里修改输入数组对于调用者是可见的。

你可以想象内部操作如下:
```
// nums 是以“引用”方式传递的。也就是说，不对实参作任何拷贝
int len = removeElement(nums, val);

// 在函数里修改输入数组对于调用者是可见的。
// 根据你的函数返回的长度, 它会打印出数组中 该长度范围内 的所有元素。
for (int i = 0; i < len; i++) {
    print(nums[i]);
}
```

示例 1：
```
输入：nums = [3,2,2,3], val = 3
输出：2, nums = [2,2]
解释：函数应该返回新的长度 2, 并且 nums 中的前两个元素均为 2。你不需要考虑数组中超出新长度后面的元素。例如，函数返回的新长度为 2 ，而 nums = [2,2,3,3] 或 nums = [2,2,0,0]，也会被视作正确答案。
```

示例 2：
```
输入：nums = [0,1,2,2,3,0,4,2], val = 2
输出：5, nums = [0,1,4,0,3]
解释：函数应该返回新的长度 5, 并且 nums 中的前五个元素为 0, 1, 3, 0, 4。注意这五个元素可为任意顺序。你不需要考虑数组中超出新长度后面的元素。
```

提示：

* 0 <= nums.length <= 100
* 0 <= nums[i] <= 50
* 0 <= val <= 100

## 理解
解法一：利用快慢指针，慢指针代表不需要移除的元素赋值的index位置，快指针即轮询的nums的位置，
判断快指针指向的值如果不是val，就赋值到慢指针位置，慢指针加1。
解法二：因为题目说明了元素的顺序可以改变，举个例子，比如[0,1,2,3,4,5]中移除0，在
方法一中，最终获得[1,2,3,4,5,5]返回长度5，这样我们就得把相同元素后面的元素依次
往前copy，其实最好的做法是[5,1,2,3,4,5]返回5，同样使用双指针，两个指针分别指向
首尾元素，当left指针指向的元素等于val时，我们就将right指针指向的值赋值给left，并
对right做--操作，直到left>right截止，保证所有元素都遍历完毕。

## 解法一
### 代码
```java
public class LeetCode_27_1_移除元素 {
    public int removeElement(int[] nums, int val) {
        int length = nums.length;
        int slow = 0;
        for (int i = 0; i < length; i++) {
            if (nums[i] != val) {
                nums[slow] = nums[i];
                slow++;
            }
        }
        return slow;
    }
}
```
## 解法二
### 代码
```java
public class LeetCode_27_2_移除元素 {
    public int removeElement(int[] nums, int val) {
        int length = nums.length;
        int left = 0, right = length - 1;
        while (left <= right) {
            if (nums[left] == val) {
                nums[left] = nums[right];
                right--;
            } else {
                left++;
            }
        }
        return left;
    }
}
```

# [LeetCode_304_1_二维区域和检索_矩阵不可变](https://leetcode-cn.com/problems/range-sum-query-2d-immutable/)
## 题目
给定一个二维矩阵，计算其子矩形范围内元素的总和，该子矩阵的左上角为 (row1, col1) ，右下角为 (row2, col2) 。

上图子矩阵左上角 (row1, col1) = (2, 1) ，右下角(row2, col2) = (4, 3)，该子矩形内元素的总和为 8。

示例：
```
给定 matrix = [
  [3, 0, 1, 4, 2],
  [5, 6, 3, 2, 1],
  [1, 2, 0, 1, 5],
  [4, 1, 0, 1, 7],
  [1, 0, 3, 0, 5]
]

sumRegion(2, 1, 4, 3) -> 8
sumRegion(1, 1, 2, 2) -> 11
sumRegion(1, 2, 2, 4) -> 12
```

提示：

* 你可以假设矩阵不可变。
* 会多次调用 sumRegion 方法。
* 你可以假设 row1 ≤ row2 且 col1 ≤ col2 。

## 理解
利用二维前缀和，前缀和数组符合以下规则：  
f[i][j] = f[i-1][j] + f[i][j-1] - f[i-1][j-1] + matrix[i][j]  
求取左上节点为r1,c1，右下节点为r2,c2的矩形区域节点和为：  
所求区域的节点和 = f[r2][c2] - f[r1-1][c2] - f[r2][c1-1] + f[r1-1][c1-1] 

### 代码
```java
public class LeetCode_304_1_二维区域和检索_矩阵不可变 {
    int[][] sum;
    public LeetCode_304_1_二维区域和检索_矩阵不可变(int[][] matrix) {
        // 查询行和列的长度
        int m = matrix.length, n = m == 0 ? 0 : matrix[0].length;
        sum = new int[m + 1][n + 1];
        for (int i = 1; i <= m; i++) {
            for (int j = 1; j <= n; j++) {
                sum[i][j] = sum[i - 1][j] + sum[i][j - 1] - sum[i - 1][j - 1] +  matrix[i - 1][j - 1];
            }
        }
    }

    public int sumRegion(int row1, int col1, int row2, int col2) {
        row1++;
        col1++;
        row2++;
        col2++;
        return sum[row2][col2] - sum[row1 - 1][col2] - sum[row2][col1 - 1] + sum[row1 - 1][col1 - 1];
    }
}

```
# [LeetCode_363_1_矩形区域不超过K的最大数值和](https://leetcode-cn.com/problems/max-sum-of-rectangle-no-larger-than-k/)
## 题目
给你一个 m x n 的矩阵 matrix 和一个整数 k ，找出并返回矩阵内部矩形区域的不超过 k 的最大数值和。

题目数据保证总会存在一个数值和不超过 k 的矩形区域。

示例 1：
```
输入：matrix = [[1,0,1],[0,-2,3]], k = 2
输出：2
解释：蓝色边框圈出来的矩形区域 [[0, 1], [-2, 3]] 的数值和是 2，且 2 是不超过 k 的最大数字（k = 2）。
```

示例 2：
```
输入：matrix = [[2,2,-1]], k = 3
输出：3
```

提示：

* m == matrix.length
* n == matrix[i].length
* 1 <= m, n <= 100
* -100 <= matrix[i][j] <= 100
* -105 <= k <= 105

进阶：如果行数远大于列数，该如何设计解决方案？

## 理解
解法一：首先初始化二维前缀和，紧接着固定矩形区间的上界限，固定矩形区间的下界限，
依次遍历矩形右界限，求取每一个右界限与原矩形的左边界形成的矩形面积，再根据右边界
面积 - 左边界减1面积 = 右边界与左边界形成的矩形的面积，我们将每个遍历的右边界与
原矩形的左边界形成的面积存入TreeSet，每遍历到一个右边界都需要满足：  
f[r] - f[l] <= k，转化一下得：f[r] - k <= f[l]，  
所以我们在set中去找满足条件的最大f[l]，f[r] - f[l] = cur, cur就是本次遍历的
小于等于k的最大面积值，如果cur=k，直接返回，否则取ans和cur中的较大的存入ans，继续
遍历。（使用TreeSet的 ceiling方法取大于等于目标值的最小值，实际是基于红黑树的二
分查找）

解法二：最大化二分查找的效率，也是本题的进阶练习所考察的。数据量越多，二分查找的
提高的效率越明显。解法一是默认的控制矩形的上下边界，轮询右边界。解法二则是需要判断
原数组的行数和列数的大小关系，固定的为值较小的，值较大的借助set去处理，其余都一样。

解法三：空间优化，我们预处理前缀和是从上往下，从左往右，与我们遍历子矩阵的顺序是一致的，
之前预处理前缀和的复杂度为O(m*n)，现在在遍历子矩阵同时初始化，优化到O(max(m,n))。

时间复杂度：之前预处理是O(m*n)，现在遍历上下行或左右行为O(min(m,n)²)，遍历右边界
或下边界为O(max(m,n))，求取最大左边界或上边界为O(log(max(m,n)))，整体时间复杂度
为：O(min(m,n)² * max(m,n)log(max(m,n)))

## 解法一
### 代码
```java
public class LeetCode_363_1_矩形区域不超过K的最大数值和 {
    public static int maxSumSubmatrix(int[][] matrix, int k) {
        int ans = Integer.MIN_VALUE;
        int m = matrix.length, n = matrix[0].length;
        int[][] sum = new int[m + 1][n + 1];
        // 初始化二维前缀和
        for (int i = 1; i <= m ; i++) {
            for (int j = 1; j <= n ; j++) {
                sum[i][j] = sum[i - 1][j] + sum[i][j - 1] - sum[i - 1][j - 1] + matrix[i - 1][j - 1];
            }
        }
        // 固定矩形上边界
        for (int i = 1; i <= m ; i++) {
            // 固定矩形下边界
            for (int j = i; j <= m ; j++) {
                TreeSet<Integer> set = new TreeSet<>();
                // 放入一个0,代表左边界为原矩阵的左边界
                set.add(0);
                // 遍历矩形右边界
                for (int l = 1; l <= n ; l++) {
                    // 求取右边界到最左与上下边界组成矩形的面积
                    int right = sum[j][l] - sum[i - 1][l];
                    Integer left = set.ceiling(right - k);
                    if (left != null) {
                        int cur = right - left;
                        if (cur == k) {
                            return k;
                        }
                        ans = Math.max(ans, cur);
                    }
                    set.add(right);
                }
            }
        }
        return ans;
    }
}
```

## 解法二
### 代码
```java
public class LeetCode_363_2_矩形区域不超过K的最大数值和 {
    public static int maxSumSubmatrix(int[][] matrix, int k) {
        int ans = Integer.MIN_VALUE;
        int m = matrix.length, n = matrix[0].length;
        int[][] sum = new int[m + 1][n + 1];
        // 初始化二维前缀和
        for (int i = 1; i <= m ; i++) {
            for (int j = 1; j <= n ; j++) {
                sum[i][j] = sum[i - 1][j] + sum[i][j - 1] - sum[i - 1][j - 1] + matrix[i - 1][j - 1];
            }
        }
        // 二分查找因该使用在数值较大的行或列中
        boolean isRight = m >= n;

        for (int i = 1; i <= (isRight ? n : m) ; i++) {
            for (int j = i; j <= (isRight ? n : m) ; j++) {
                TreeSet<Integer> set = new TreeSet<>();
                // 放入一个0,代表左边界为原矩阵的左边界
                set.add(0);
                // 遍历矩形右边界
                for (int l = 1; l <= (isRight ? m : n) ; l++) {
                    // 求取右边界到最左与上下边界组成矩形的面积
                    int right = sum[j][l] - sum[i - 1][l];
                    Integer left = set.ceiling(right - k);
                    if (left != null) {
                        int cur = right - left;
                        if (cur == k) {
                            return k;
                        }
                        ans = Math.max(ans, cur);
                    }
                    set.add(right);
                }
            }
        }
        return ans;
    }
}
```

## 解法三
### 代码
```java
public class LeetCode_363_3_矩形区域不超过K的最大数值和 {
    public static int maxSumSubmatrix(int[][] matrix, int k) {
        int ans = Integer.MIN_VALUE;
        int m = matrix.length, n = matrix[0].length;
        // 二分查找因该使用在数值较大的行或列中
        boolean isRight = m >= n;
        // 初始化前缀和一维数组（将二维数组简化为一维数组）
        int[] sum = isRight ? new int[m + 1] : new int[n + 1];

        for (int i = 1; i <= (isRight ? n : m) ; i++) {
            // 确认一个边界后，初始化一维数组
            Arrays.fill(sum, 0);
            for (int j = i; j <= (isRight ? n : m) ; j++) {
                TreeSet<Integer> set = new TreeSet<>();
                // 放入一个0,包含边界值
                set.add(0);
                int a = 0;
                // 遍历原矩阵较大维度的数据
                for (int l = 1; l <= (isRight ? m : n) ; l++) {
                    // 由于遍历m,n中较小维度的下界变化，累计求取一维数组当前位置的值
                    sum[l] += isRight ? matrix[l - 1][j - 1] : matrix[j - 1][l - 1];
                    // 求取right的值
                    a += sum[l];
                    Integer left = set.ceiling(a - k);
                    if (left != null) {
                        int cur = a - left;
                        if (cur == k) {
                            return k;
                        }
                        ans = Math.max(ans, cur);
                    }
                    set.add(a);
                }
            }
        }
        return ans;
    }
}
```
# [LeetCode_28_1_实现strStr](https://leetcode-cn.com/problems/implement-strstr/)
## 题目
实现 strStr() 函数。

给你两个字符串 haystack 和 needle ，请你在 haystack 字符串中找出 needle 字符串出现的第一个位置（下标从 0 开始）。如果不存在，则返回  -1 。

 
说明：

当 needle 是空字符串时，我们应当返回什么值呢？这是一个在面试中很好的问题。

对于本题而言，当 needle 是空字符串时我们应当返回 0 。这与 C 语言的 strstr() 以及 Java 的 indexOf() 定义相符。


示例 1：
```
输入：haystack = "hello", needle = "ll"
输出：2
```

示例 2：
```
输入：haystack = "aaaaa", needle = "bba"
输出：-1
```

示例 3：
```
输入：haystack = "", needle = ""
输出：0
```

提示：

* 0 <= haystack.length, needle.length <= 5 * 104
* haystack 和 needle 仅由小写英文字符组成

## 理解
解法一：朴素解法，遍历第一个字符串中的每一个元素，作为头元素与第二个字符串匹配，匹配成功则返回
头元素的节点位置，匹配失败，遍历下一个元素，又从头开始与第二个字符串匹配。需要注意题意中，如果第二
个字符串为空返回0，需要特殊处理。
解法二：利用KMP算法求解，KMP算法的核心，最大相等前后缀数组的预处理。我们预处理出模式字符串的
最大相等前后缀和数组，时间复杂度为O(m)，求解分为4步：  
1. 初始化i,j(i代表后缀的最后一个元素，j代表前缀的最后一个元素，也代表当前字符串的最大相等前后缀的长度)  
2. 不相等时的处理（需要注意不相等时找他前一个的位置，需要考虑边界情况即）
3. 相等时的处理
4. next数组赋值  
处理完next数组，我们同样是遍历原数组中的每一个元素与模式数组做匹配，发现不匹配时，并不是模式数组
回到最初位置，而是找next（index - 1）来确认回到的位置，继续匹配。  
整体时间复杂度为O(m + n)

## 解法一
### 代码
```java
public class LeetCode_28_1_实现strStr {
    public int strStr(String haystack, String needle) {
        if ("".equals(needle)) {
            return 0;
        }
        int n = haystack.length(), m = needle.length();
        char[] ch1 = haystack.toCharArray();
        char[] ch2 = needle.toCharArray();

        for (int i = 0; i < n - m; i++) {
            // 记录haystack遍历的下标a,以及needle的遍历下标b
            int a = i, b = 0;
            while (b < m && ch1[a] == ch2[b]) {
                a++;
                b++;
            }
            if (b == m) {
                return i;
            }
        }
        return -1;
    }
}
```
## 解法二
### 代码
```java
public class LeetCode_28_2_实现strStr {
    public int strStr(String haystack, String needle) {
        if ("".equals(needle)) {
            return 0;
        }
        int n = haystack.length(), m = needle.length();
        char[] ch1 = haystack.toCharArray();
        char[] ch2 = needle.toCharArray();
        // 初始化next数组
        int[] next = new int[m];
        // j是前缀的最后一个元素，i是后缀的最后一个元素，最少要有两个元素，才能求最大相等前后缀的元素个数
        // 即后缀起始从1开始遍历，需要注意只有当j>0时，才需要找它之前的next的位置
        for (int i = 1, j = 0; i < m; i++) {
            while (j > 0 && ch2[j] != ch2[i]) {
                j = next[j - 1];
            }
            if (ch2[j] == ch2[i]) {
                j++;
            }
            next[i] = j;
        }
        // 从第一个元素开始匹配原数组和模板数组
        for (int i = 0, j = 0; i < n; i++) {
            while (j > 0 && ch1[i] != ch2[j]) {
                j = next[j - 1];
            }
            if (ch1[i] == ch2[j]) {
                j++;
            }
            // 需要注意首字母坐标为i - m + 1
            if (j == m) {
                return i - m + 1;
            }
        }
        return -1;
    }
}
```

# [LeetCode_91_1_解码方法](https://leetcode-cn.com/problems/decode-ways/)
## 题目
一条包含字母 A-Z 的消息通过以下映射进行了 编码 ：

'A' -> 1
'B' -> 2
...
'Z' -> 26
要 解码 已编码的消息，所有数字必须基于上述映射的方法，反向映射回字母（可能有多种方法）。例如，"11106" 可以映射为：

"AAJF" ，将消息分组为 (1 1 10 6)  
"KJF" ，将消息分组为 (11 10 6)  
注意，消息不能分组为  (1 11 06) ，因为 "06" 不能映射为 "F" ，这是由于 "6" 和 "06" 在映射中并不等价。  

给你一个只含数字的 非空 字符串 s ，请计算并返回 解码 方法的 总数 。

题目数据保证答案肯定是一个 32 位 的整数。

 
示例 1：
```
输入：s = "12"
输出：2
解释：它可以解码为 "AB"（1 2）或者 "L"（12）。
```

示例 2：
```
输入：s = "226"
输出：3
解释：它可以解码为 "BZ" (2 26), "VF" (22 6), 或者 "BBF" (2 2 6) 。
```

示例 3：
```
输入：s = "0"
输出：0
解释：没有字符映射到以 0 开头的数字。
含有 0 的有效映射是 'J' -> "10" 和 'T'-> "20" 。
由于没有字符，因此没有有效的方法对此进行解码，因为所有数字都需要映射。
```

示例 4：
```
输入：s = "06"
输出：0
解释："06" 不能映射到 "F" ，因为字符串含有前导 0（"6" 和 "06" 在映射中并不等价）。
```

提示：

* 1 <= s.length <= 100
* s 只包含数字，并且可能包含前导零。

## 理解
解法一：使用动态规划，f[i]代表包含i位置之前的所有元素，解码的方案数。当遍历到某个位置时，
我们只需要考虑三种情况，当前遍历值记为a，与前一位数字组成的数字记为b，也就是我们转移方程：  
1.  1 <= a <= 9    f[i] = f[i - 1]   如：前一位是0，当前位是[1-9]  
2. 10 <= b <= 26   f[i] = f[i - 2]   如：10,17,18,19,20  
3. 1 <= a <= 9 且 10 <= b <=26  f[i] = f[i - 1] + f[i - 2]   如：11,12,21,22等  
为了避免处理，前导为0的情况，我们在字符串前加个空，处理的时候从第一个开始处理。(' ' - '0' = -16)
解法二：空间优化，我们发现我们的转移方程只用到了f[i - 1]和f[i - 2]，那么我们可以优化空间，
只使用长度为3的数组来存储。

## 解法一
### 代码
```java
public class LeetCode_91_1_解码方法 {
    public int numDecodings(String s) {
        s = " " + s;
        int length = s.length();
        int[] dp = new int[length];
        dp[0] = 1;
        char[] ch = s.toCharArray();
        for (int i = 1; i < length; i++) {
            int a = ch[i] - '0', b = (ch[i - 1] - '0') * 10 + (ch[i] - '0');
            if (a >= 1 && a <= 9) {
                dp[i] = dp[i - 1];
            }
            if (b >= 10 && b <= 26) {
                dp[i] += dp[i - 2];
            }
        }
        return dp[length - 1];
    }
}
```
## 解法二
### 代码
```java
public class LeetCode_91_2_解码方法 {
    public int numDecodings(String s) {
        s = " " + s;
        int length = s.length();
        int[] dp = new int[3];
        dp[0] = 1;
        char[] ch = s.toCharArray();
        for (int i = 1; i < length; i++) {
            dp[i % 3] = 0;
            int a = ch[i] - '0', b = (ch[i - 1] - '0') * 10 + (ch[i] - '0');
            if (a >= 1 && a <= 9) {
                dp[i % 3] = dp[(i - 1) % 3];
            }
            if (b >= 10 && b <= 26) {
                dp[i % 3] += dp[(i - 2) % 3];
            }
        }
        return dp[(length - 1) % 3];
    }
}
```


# [LeetCode_368_1_最大整数子集](https://leetcode-cn.com/problems/largest-divisible-subset/)
## 题目
给你一个由 无重复 正整数组成的集合 nums ，请你找出并返回其中最大的整除子集 answer ，子集中每一元素对 (answer[i], answer[j]) 都应当满足：  
answer[i] % answer[j] == 0 ，或  
answer[j] % answer[i] == 0  
如果存在多个有效解子集，返回其中任何一个均可。

示例 1：
```
输入：nums = [1,2,3]
输出：[1,2]
解释：[1,3] 也会被视为正确答案。
```

示例 2：
```
输入：nums = [1,2,4,8]
输出：[1,2,4,8]
```

提示：

* 1 <= nums.length <= 1000
* 1 <= nums[i] <= 2 * 109
* nums 中的所有整数 互不相同

## 理解
（动态规划）首先对数组进行排序，因为规则需要判断数A是数B的倍数或约数，我们将数组
排完序，将问题转化为：在一个升序数组里，查询最大整数子集。  
我们遍历原数组，每个数据都当做最大整数子集的最后一个元素，匹配遍历位置之前的所有
数据，确认是否存在nums[i] % nums[j] == 0；找出i位置对应的最大整数子集的长度，并
记录是由哪个j迁移过来。直到遍历完数组。  
遍历f数组，寻找最大整数的长度及最后一个元素的index，再根据回溯数组g，还原回list
返回。

### 代码
```java
public class LeetCode_368_1_最大整数子集 {
    public static List<Integer> largestDivisibleSubset(int[] nums) {
        Arrays.sort(nums);
        int length = nums.length;
        // f[i]代表i之前所有数据的最大整数子集个数
        int[] f = new int[length];
        // g[i]代表f[i]代表的整数子集，前一个元素的下标
        int[] g = new int[length];
        // 遍历所有数据
        for (int i = 0; i < length; i++) {
            // 初始化子集长度为1，回溯坐标为i
            int max = 1, pre = i;
            // 查询i以前的所有数据，看是否存在符合整数子集规则的
            for (int j = 0; j < i ; j++) {
                boolean result = nums[i] % nums[j] == 0;
                if (result) {
                    int count = f[j] + 1;
                    if (count > max) {
                        max = count;
                        pre = j;
                    }
                }
            }
            f[i] = max;
            g[i] = pre;
        }
        int size = -1, idx = -1;
        for (int i = 0; i < length; i++) {
            if (f[i] > size) {
                size = f[i];
                idx = i;
            }
        }
        List<Integer> list = new ArrayList<>(size);
        while (list.size() != size) {
            list.add(nums[idx]);
            idx = g[idx];
        }
        return list;
    }

}
```
# [LeetCode_377_1_组合总和IV](https://leetcode-cn.com/problems/combination-sum-iv/)
## 题目
给你一个由 不同 整数组成的数组 nums ，和一个目标整数 target 。请你从 nums 中找出并返回总和为 target 的元素组合的个数。

题目数据保证答案符合 32 位整数范围。


示例 1：
```
输入：nums = [1,2,3], target = 4
输出：7
解释：
所有可能的组合为：
(1, 1, 1, 1)
(1, 1, 2)
(1, 2, 1)
(1, 3)
(2, 1, 1)
(2, 2)
(3, 1)
请注意，顺序不同的序列被视作不同的组合。
```

示例 2：
```
输入：nums = [9], target = 3
输出：0
```

提示：

* 1 <= nums.length <= 200
* 1 <= nums[i] <= 1000
* nums 中的所有元素 互不相同
* 1 <= target <= 1000
 

进阶：如果给定的数组中含有负数会发生什么？问题会产生何种变化？如果允许负数出现，需要向题目中添加哪些限制条件？

## 理解
解法一：动态规划问题，因为题目给出了nums中最小元素为1，而且所有元素都可以重复选择，那么
组成target的最大元素数为target长度的1，我们使用二维数组来存储，定义 f[i][j] 为组合长度为 i，
凑成总和为 j 的方案数是多少。初始化有效值f[0][0] = 1，先遍历元素长度，再遍历target值，
需要注意nums中每个值都有可能是最后一个元素，转化方程为：  
f[i][j] = f[i - 1][j - value]，j>=value，value为nums中遍历的每一个值  
最后返回j为target的所有值的和即为答案。

解法二：降维优化，f[j]代表，凑成总和为j的方案数是多少，由于nums的数都是正整数，所以
f[0] = 1(代表什么都不选，凑成总和为0的方案数是1)，最终答案为f[target]。  
由于每个数子都可以被选无限次，因此我们在计算任意总和时，我们需要保证每个nums中的值都
会被考虑到，总和target在外层循环，nums在内层循环，得到转移方程为：
f[j] = f[j - value]，j>=value  
遍历完成后返回f[target]。

## 解法一
### 代码
```java
public class LeetCode_377_1_组合总和IV {
    public int combinationSum4(int[] nums, int target) {
        // 因为nums[i]的最小值是1，所以组成target的最大长度为target个1
        int length = target;
        int ans = 0;
        // f[i][j]代表组合中元素个数为i个，值为j的方案数
        int[][] f = new int[length + 1][target + 1];
        f[0][0] = 1;
        for (int i = 1; i <= length; i++) {
            for (int j = 0; j <= target ; j++) {
                for (int value : nums) {
                    if (j >= value) {
                        f[i][j] += f[i - 1][j - value];
                    }
                }
            }
            ans += f[i][target];
        }
        return ans;
    }
}
```

## 解法二
### 代码
```java
public class LeetCode_377_2_组合总和IV {
    public int combinationSum4(int[] nums, int target) {

        // f[i]值为i的方案数
        int[] f = new int[target + 1];
        f[0] = 1;
        for (int j = 1; j <= target ; j++) {
            for (int value : nums) {
                if (j >= value) {
                    f[j] += f[j - value];
                }
            }
        }
        return f[target];
    }
}
```

# [LeetCode_897_1_递增顺序搜索树](https://leetcode-cn.com/problems/increasing-order-search-tree/)
## 题目
给你一棵二叉搜索树，请你 按中序遍历 将其重新排列为一棵递增顺序搜索树，使树中最左边的节点成为树的根节点，并且每个节点没有左子节点，只有一个右子节点。

 

示例 1：
```
输入：root = [5,3,6,2,4,null,8,1,null,null,null,7,9]
输出：[1,null,2,null,3,null,4,null,5,null,6,null,7,null,8,null,9]
```


示例 2：
```
输入：root = [5,1,7]
输出：[1,null,5,null,7]
```

提示：

* 树中节点数的取值范围是 [1, 100]
* 0 <= Node.val <= 1000

## 理解
解法一：利用递归中序遍历，将节点存入List，再处理List中的元素，使用虚拟头节点，统一处理
逻辑。（注意在遍历中处理当前节点的左子节点为空，放在pre中去处理左子节点会漏掉最后一个元素）
解法二：与解法一类似，只是在中序遍历中，处理了节点。

## 解法一
### 代码
```java
public class LeetCode_897_1_递增顺序搜索树 {
    List<TreeNode> list = new ArrayList<>();
    public TreeNode increasingBST(TreeNode root) {
        inorder(root);
        TreeNode head = new TreeNode(-1);
        TreeNode pre = head;
        for (TreeNode node : list) {
            pre.right = node;
            node.left = null;
            pre = node;
        }
        return head.right;
    }

    public void inorder(TreeNode node) {
        if (node == null) {
            return;
        }
        inorder(node.left);
        list.add(node);
        inorder(node.right);
    }
}
```

## 解法二
### 代码
```java
public class LeetCode_897_2_递增顺序搜索树 {
    TreeNode head = new TreeNode(-1);
    TreeNode pre = head;
    public TreeNode increasingBST(TreeNode root) {
        inorder(root);
        return head.right;
    }

    public void inorder(TreeNode node) {
        if (node == null) {
            return;
        }
        inorder(node.left);
        pre.left = null;
        pre.right = node;
        pre = node;
        inorder(node.right);
    }
}
```