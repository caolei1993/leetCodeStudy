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