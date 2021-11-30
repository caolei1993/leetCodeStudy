[toc]

# [LeetCode_299_1_猜数字游戏](https://leetcode-cn.com/problems/bulls-and-cows/)
## 理解
遍历秘密数字和猜测数字每个位置，如果值相等，公牛数+1，否则记录各个数的词频，最终每个位置的最小值之
和即为奶牛数量  
时间复杂度为O(n)，空间复杂度为O(C)，C为常数大小为10

### 代码
```java
public class LeetCode_299_1_猜数字游戏 {
    public String getHint(String secret, String guess) {
        int n = secret.length();
        int[] cn1 = new int[10];
        int[] cn2 = new int[10];
        // 公牛数量,奶牛数量
        int a = 0, b = 0;
        for (int i = 0; i < n; i++) {
            int c1 = secret.charAt(i) - '0', c2 = guess.charAt(i) - '0';
            if (c1 == c2) {
                a++;
            } else {
                cn1[c1]++;
                cn2[c2]++;
            }
        }
        for (int i = 0; i < 10; i++) {
            b += Math.min(cn1[i], cn2[i]);
        }
        return a + "A" + b + "B";
    }
}
```

# [LeetCode_495_1_提莫攻击](https://leetcode-cn.com/problems/teemo-attacking/)
## 理解
解法一：从第二次攻击开始遍历，对比每次攻击的间隔和预定中毒时间的大小，谁小选择谁，需要特殊处理
最后一次攻击。  
时间复杂度为O(n)，空间复杂度为O(1)

解法二：对比当前攻击时间与前一次攻击中毒的清醒时间，如果攻击时间大于等于清醒时间则直接累加一次
中毒周期，否则需要计算本次攻击时间与前一次攻击时间的间隔，进行累加。  
复杂度同上

## 解法一
### 代码
```java
public class LeetCode_495_1_提莫攻击 {
    public int findPoisonedDuration(int[] timeSeries, int duration) {
        int ans = 0;
        for (int i = 1; i < timeSeries.length; i++) {
            // 两次攻击之间如果间隔超过最大中毒时间，取最大中毒事件，否则取两次攻击的间隔时间
            ans += Math.min(duration, timeSeries[i] - timeSeries[i - 1]);
        }
        // 最后一次攻击
        ans += duration;
        return ans;
    }
}

```

## 解法二
### 代码
```java
public class LeetCode_495_2_提莫攻击 {
    public int findPoisonedDuration(int[] timeSeries, int duration) {
        int ans = 0;
        int e = 0;
        for (int time : timeSeries) {
            if (time >= e) {
                ans += duration;
            } else {
                ans += time - e + duration;
            }
            e = time + duration;
        }
        return ans;
    }
}
```

# [LeetCode_375_1_猜数字大小II](https://leetcode-cn.com/problems/guess-number-higher-or-lower-ii/)
## 理解
使用区间DP来解决问题  
状态定义：f[i][j]表示在范围 [i, j][i,j] 内确保胜利的最少金额  

假设我们第一个猜的数字是x，猜错了，需要支付x金额，真实值比x大的情况下，我们
还需要再交f[1][x - 1]，真实值比x小的情况下我们还需要叫f[x + 1][n]，最终我们需要确保一定胜利，
所以f[1][n] = x + max(f[1][x - 1], f[x + 1][n])  
由于f[1][x - 1]和f[x + 1][n]都是比原始问题[1, n]更小的区间，所以我们可以使用动态规划来求解

数据边界：当i=j时，只包含一个数字，不会猜错，所以f[i][j] = 0，当i > j时，[i,j]区间不存在，
所以我们dp的范围为i < j

转移方程的确立：在i,j区间里，第一次猜测的数字k可能是任意数字，得到的金额花费为
max(f[i][k - 1], f[k + 1][j]) + k，k可能为i到j中的任意值，我们需要取所有方案中的最小值，
赋值给f[i][j]

由于状态转移方程为根据规模小的子问题计算规模大的子问题，因此计算子问题的顺序为先计算规模小的子问题，
后计算规模大的子问题，需要注意循环遍历的方向。

### 代码
```java
public class LeetCode_375_1_猜数字大小II {
    public int getMoneyAmount(int n) {
        // f[i][j]代表在区间[i,j]中猜数的最小花费
        int[][] f = new int[n + 1][n + 1];
        for (int i = n - 1; i >= 1 ; i--) {
            for (int j = i + 1; j <= n; j++) {
                int val = Integer.MAX_VALUE;
                for (int k = i; k < j; k++) {
                    int cur = Math.max(f[i][k - 1], f[k+1][j]) + k;
                    val = Math.min(val, cur);
                }
                f[i][j] = val;
            }
        }
        return f[1][n];
    }
}
```