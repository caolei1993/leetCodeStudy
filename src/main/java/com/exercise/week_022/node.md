[toc]
# [LeetCode_401_1_二进制手表](https://leetcode-cn.com/problems/binary-watch/)
## 题目
二进制手表顶部有 4 个 LED 代表 小时（0-11），底部的 6 个 LED 代表 分钟（0-59）。每个 LED 代表一个 0 或 1，最低位在右侧。

例如，下面的二进制手表读取 "3:25" 。

（图源：WikiMedia - Binary clock samui moon.jpg ，许可协议：Attribution-ShareAlike 3.0 Unported (CC BY-SA 3.0) ）

给你一个整数 turnedOn ，表示当前亮着的 LED 的数量，返回二进制手表可以表示的所有可能时间。你可以 按任意顺序 返回答案。

小时不会以零开头：  

* 例如，"01:00" 是无效的时间，正确的写法应该是 "1:00" 。
  
分钟必须由两位数组成，可能会以零开头：

* 例如，"10:2" 是无效的时间，正确的写法应该是 "10:02" 。
 
示例 1：
```
输入：turnedOn = 1
输出：["0:01","0:02","0:04","0:08","0:16","0:32","1:00","2:00","4:00","8:00"]
```

示例 2：
```
输入：turnedOn = 9
输出：[]
```

提示：

* 0 <= turnedOn <= 10

## 理解
解法一：利用static代码块遍历所有时间00:00-11:59，遍历过程中利用lowbit函数轮询求取时间中
二进制1的个数，并初始化到对应的map中，最终从map中返回结果即可。
解法二：利用Integer中提供的函数bitCount来统计时间对应的二进制的1的个数，再时间遍历过程中
将满足条件的时间添加到list中，遍历结束返回list。

## 解法一
### 代码
```java
public class LeetCode_401_1_二进制手表 {
    static Map<Integer, List<String>> map = new HashMap<>();
    static {
        for (int h = 0; h < 12; h++) {
            for (int m = 0; m < 60; m++) {
                int count = countOne(h) + countOne(m);
                List<String> list = map.getOrDefault(count, new ArrayList<>());
                list.add(getTime(h, m));
                map.put(count, list);
            }
        }
    }

    static String getTime (int hour, int minute) {
        return hour + ":" + (minute <= 9 ? "0" + minute : minute);
    }

    private static int countOne(int value) {
        int ans = 0;
        for (int i = value; i > 0 ; i -= lowbit(i)) {
            ans++;
        }
        return ans;
    }

    private static int lowbit(int i) {
        return i & -i;
    }

    public List<String> readBinaryWatch(int turnedOn) {
        return map.getOrDefault(turnedOn, new ArrayList<>());
    }
}
```

## 解法二
### 代码
```java
public class LeetCode_401_2_二进制手表 {
    public List<String> readBinaryWatch(int turnedOn) {
        List<String> list = new ArrayList<>();
        for (int h = 0; h < 12; h++) {
            for (int m = 0; m < 60; m++) {
                int count = Integer.bitCount(h) + Integer.bitCount(m);
                if (count == turnedOn) {
                    list.add(h + ":" + (m < 10 ? "0" + m : m));
                }
            }
        }
        return list;
    }
}
```

# [LeetCode_剑指Offer38_1_字符串的排列](https://leetcode-cn.com/problems/zi-fu-chuan-de-pai-lie-lcof/)
## 题目
输入一个字符串，打印出该字符串中字符的所有排列。

你可以以任意顺序返回这个字符串数组，但里面不能有重复元素。

**示例:**
```
输入：s = "abc"
输出：["abc","acb","bac","bca","cab","cba"]
```
 
**限制：**

* 1 <= s 的长度 <= 8

## 理解
回溯dfs，利用布尔类型数组来标记某个index的字符是否已经使用，利用set做结果的去重，需要注意在递归
调用前需要将标记该位置是否使用的标记改为true，代表已使用，调用完后再改回false，因为标记位是所有递归
共用的单例属性。

### 代码
```java
public class LeetCode_剑指Offer38_1_字符串的排列 {

    Set<String> set = new HashSet<>();
    boolean[] b = new boolean[10];

    public String[] permutation(String s) {
        dfs(s, 0, "");
        String[] arr = new String[set.size()];
        int idx = 0;
        for (String str : set) {
            arr[idx++] = str;
        }
        return arr;
    }

    private void dfs(String s, int index, String cur) {
        int n = s.length();
        if (index == n) {
            set.add(cur);
            return;
        }
        for (int i = 0; i < n; i++) {
            if (!b[i]) {
                b[i] = true;
                dfs(s, index + 1, cur + s.charAt(i));
                b[i] = false;
            }
        }
    }
}
```

# [LeetCode_剑指Offer15_1_二进制中1的个数](https://leetcode-cn.com/problems/er-jin-zhi-zhong-1de-ge-shu-lcof/)
## 题目
请实现一个函数，输入一个整数（以二进制串形式），输出该数二进制表示中 1 的个数。例如，把 9 表示成二进制是 1001，有 2 位是 1。因此，如果输入 9，则该函数输出 2。

示例 1：
```
输入：00000000000000000000000000001011
输出：3
解释：输入的二进制串 00000000000000000000000000001011 中，共有三位为 '1'。
```

示例 2：
```
输入：00000000000000000000000010000000
输出：1
解释：输入的二进制串 00000000000000000000000010000000 中，共有一位为 '1'。
```

示例 3：
```
输入：11111111111111111111111111111101
输出：31
解释：输入的二进制串 11111111111111111111111111111101 中，共有 31 位为 '1'。
```

 
提示：

* 输入必须是长度为 32 的 二进制串 。

## 理解
解法一：使用Integer提供的bitCount统计函数
解法二：利用lowbit函数，统计二进制1的个数

## 解法一
### 代码
```java
public class LeetCode_剑指Offer15_1_二进制中1的个数 {
    public int hammingWeight(int n) {
        return Integer.bitCount(n);
    }
}
```

## 解法二
### 代码
```java
public class LeetCode_剑指Offer15_2_二进制中1的个数 {
    public int hammingWeight(int n) {
        int ans = 0;
        while (n != 0) {
            ans++;
            n -= lowbit(n);
        }
        return ans;
    }

    private int lowbit(int n) {
        return n & -n;
    }

}
```
# [LeetCode_149_1_直线上最多的点数](https://leetcode-cn.com/problems/max-points-on-a-line/)
## 题目
给你一个数组 points ，其中 points[i] = [xi, yi] 表示 X-Y 平面上的一个点。求最多有多少个点在同一条直线上。

示例 1：

```
输入：points = [[1,1],[2,2],[3,3]]
输出：3
```

示例 2：

```
输入：points = [[1,1],[3,2],[5,3],[4,1],[2,3],[1,4]]
输出：4
```

提示：

* 1 <= points.length <= 300
* points[i].length == 2
* -104 <= xi, yi <= 104
* points 中的所有点 互不相同

## 理解
解法一：暴力枚举，每次枚举三个点，计算他们的斜率（注意除法可能会失去精度，转化为乘法运算），
如果斜率相等即在同一条直线上。  
两个点确认一条直线，遍历第三个点，确认当前直线上的点数。  
每遍历完一条直线，取ans和当前直线点数中的较大值。

解法二：枚举所有直线的过程不可避免，但统计点数的过程可以优化  
具体的，我们可以先枚举所有可能出现的 直线斜率（根据两点确定一条直线，即枚举所有的「点对」），
使用「哈希表」统计所有 斜率 对应的点的数量，在所有值中取个 max 即是答案

遍历所有点作为端点，遍历该端点下的所有点与端点的斜率，统计斜率，并取出点数最大值，
继续遍历下一个端点，最终返回点数的最大值。


## 解法一
### 代码
```java
public class LeetCode_149_1_直线上最多的点数 {
    public int maxPoints(int[][] points) {
        int n = points.length;
        int ans = 1;
        for (int i = 0; i < n ; i++) {
            int cnt = 1;
            int[] x = points[i];
            for (int j = i + 1; j < n; j++) {
                int[] y = points[j];
                cnt = 2;
                for (int k = j + 1; k < n; k++) {
                    int[] p = points[k];
                    int s1 = (y[1] - x[1]) * (p[0] - y[0]);
                    int s2 = (y[0] - x[0]) * (p[1] - y[1]);
                    if (s1 == s2) {
                        cnt++;
                    }
                }
                ans = Math.max(ans, cnt);
            }
        }
        return ans;
    }
}
```
## 解法二
### 代码
```java
public class LeetCode_149_2_直线上最多的点数 {
    public int maxPoints(int[][] points) {
        int n = points.length;
        int ans = 1;
        for (int i = 0; i < n ; i++) {
            Map<String, Integer> map = new HashMap<>();
            // 记录从i点出发的所有直线上的最多点数个数
            int max = 0;
            for (int j = i + 1; j < n; j++) {
                int x1 = points[i][0], y1 = points[i][1], x2 = points[j][0], y2 = points[j][1];
                int a = x1 - x2, b = y1 - y2;
                int k = gcd(a, b);
                String key = (a / k) + "_" + (b / k);
                // 默认值为1，即包含i节点
                map.put(key, map.getOrDefault(key, 1) + 1);
                max = Math.max(max, map.get(key));
            }
            ans = Math.max(ans, max);
        }
        return ans;
    }

    /**
     * 求取两个数的最大公约数
     * @param a
     * @param b
     * @return
     */
    private int gcd(int a, int b) {
        return b == 0 ? a : gcd(b, a % b);
    }
}
```