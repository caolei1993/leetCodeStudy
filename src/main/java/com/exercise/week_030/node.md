[toc]

# [LeetCode_551_1_学生出勤记录I](https://leetcode-cn.com/problems/student-attendance-record-i/)
## 题目
给你一个字符串 s 表示一个学生的出勤记录，其中的每个字符用来标记当天的出勤情况（缺勤、迟到、到场）。记录中只含下面三种字符：

* 'A'：Absent，缺勤
* 'L'：Late，迟到
* 'P'：Present，到场

如果学生能够 同时 满足下面两个条件，则可以获得出勤奖励：

* 按 总出勤 计，学生缺勤（'A'）严格 少于两天。
* 学生 不会 存在 连续 3 天或 3 天以上的迟到（'L'）记录。

如果学生可以获得出勤奖励，返回 true ；否则，返回 false 。

示例 1：
```
输入：s = "PPALLP"
输出：true
解释：学生缺勤次数少于 2 次，且不存在 3 天或以上的连续迟到记录。
```

示例 2：
```
输入：s = "PPALLL"
输出：false
解释：学生最后三天连续迟到，所以不满足出勤奖励的条件。
```
 
提示：

* 1 <= s.length <= 1000
* s[i] 为 'A'、'L' 或 'P'

## 理解
根绝题意模拟即可，时间复杂度O(n)，空间复杂度O(1)

### 代码
```java
public class LeetCode_551_1_学生出勤记录I {
    public boolean checkRecord(String s) {
        int n = s.length();
        int aCount = 0;
        for (int i = 0; i < n; ) {
            char c = s.charAt(i);
            switch (c) {
                case 'A':
                    aCount++;
                    if (aCount >= 2) {
                        return false;
                    }
                    i++;
                    break;
                case 'L':
                    int lCount = 1;
                    while (++i < n && s.charAt(i) == 'L') {
                        lCount++;
                    }
                    if (lCount >= 3) {
                        return false;
                    }
                    break;
                default:
                    i++;
            }
        }
        return true;
    }
}
```

# [LeetCode_552_学生出勤记录II](https://leetcode-cn.com/problems/student-attendance-record-ii/)
## 题目
可以用字符串表示一个学生的出勤记录，其中的每个字符用来标记当天的出勤情况（缺勤、迟到、到场）。记录中只含下面三种字符：
* 'A'：Absent，缺勤
* 'L'：Late，迟到
* 'P'：Present，到场

如果学生能够 同时 满足下面两个条件，则可以获得出勤奖励：

* 按 总出勤 计，学生缺勤（'A'）严格 少于两天。
* 学生 不会 存在 连续 3 天或 连续 3 天以上的迟到（'L'）记录。

给你一个整数 n ，表示出勤记录的长度（次数）。请你返回记录长度为 n 时，可能获得出勤奖励的记录情况 数量 。答案可能很大，所以返回对 109 + 7 取余 的结果。

 
示例 1：
```
输入：n = 2
输出：8
解释：
有 8 种长度为 2 的记录将被视为可奖励：
"PP" , "AP", "PA", "LP", "PL", "AL", "LA", "LL" 
只有"AA"不会被视为可奖励，因为缺勤次数为 2 次（需要少于 2 次）。
```

示例 2：
```
输入：n = 1
输出：3
```

示例 3：
```
输入：n = 10101
输出：183236316
```

提示：

* 1 <= n <= 10^5

## 理解
解法一：使用dfs记忆化搜索，根据题意我们知道一个合法的方案A出现的次数最多为1次，L连续出现的次数最多为
两次，因为我们在枚举统计时，需要决策某一位应该选什么时，我们关心的是当前方案中有多少个A和末尾有多少个
连续的L，根据此遍历所有位置进行统计合法方案。  
时间复杂度为O(n*2*3)，即O(n)，空间复杂度为O(n)


## 解法一
### 代码
```java
public class LeetCode_552_学生出勤记录II {

    int mod = (int)1e9 + 7;
    int[][][] cache;

    public int checkRecord(int n) {
        cache = new int[n + 1][2][3];
        for (int i = 0; i <= n ; i++) {
            for (int j = 0; j < 2 ; j++) {
                for (int k = 0; k < 3 ; k++) {
                    cache[i][j][k] = -1;
                }
            }
        }
        return dfs(n, 0, 0);
    }

    private int dfs(int u, int aCnt, int lCnt) {
        if (aCnt >= 2) {
            return 0;
        }
        if (lCnt >= 3) {
            return 0;
        }
        if (u == 0) {
            return 1;
        }
        if (cache[u][aCnt][lCnt] != -1) {
            return cache[u][aCnt][lCnt];
        }
        int ans = 0;
        // 添加A
        ans = dfs(u - 1, aCnt + 1, 0) % mod;
        // 添加L
        ans = (ans + dfs(u - 1, aCnt, lCnt + 1)) % mod;
        // 添加P
        ans = (ans + dfs(u - 1, aCnt, 0)) % mod;
        cache[u][aCnt][lCnt] = ans;
        return ans;
    }
}

```