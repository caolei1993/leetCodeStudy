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
# [LeetCode_127_1_单词接龙](https://leetcode-cn.com/problems/word-ladder/)
## 题目
字典 wordList 中从单词 beginWord 和 endWord 的 转换序列 是一个按下述规格形成的序列：

* 序列中第一个单词是 beginWord 。
* 序列中最后一个单词是 endWord 。
* 每次转换只能改变一个字母。
* 转换过程中的中间单词必须是字典 wordList 中的单词。  

给你两个单词 beginWord 和 endWord 和一个字典 wordList ，找到从 beginWord 到 endWord 的 最短转换序列 中的 单词数目 。如果不存在这样的转换序列，返回 0。

 
示例 1：
```

```
输入：beginWord = "hit", endWord = "cog", wordList = ["hot","dot","dog","lot","log","cog"]
输出：5
解释：一个最短转换序列是 "hit" -> "hot" -> "dot" -> "dog" -> "cog", 返回它的长度 5。
示例 2：
```

```
输入：beginWord = "hit", endWord = "cog", wordList = ["hot","dot","dog","lot","log"]
输出：0
解释：endWord "cog" 不在字典中，所以无法进行转换。
 

提示：

* 1 <= beginWord.length <= 10
* endWord.length == beginWord.length
* 1 <= wordList.length <= 5000
* wordList[i].length == beginWord.length
* beginWord、endWord 和 wordList[i] 由小写英文字母组成
* beginWord != endWord
* wordList 中的所有字符串 互不相同

## 理解
利用bfs解决，普通bfs有搜索空间爆炸问题，使用双向bfs来减小数据空间宽度。
* 创建两个队列分别用于两个方向的搜索
* 创建两个哈希表，用来过滤重复方案和记录转化次数（通过多少步转化而来）
* 为了尽可能的让两个搜索方向平均，每次从队列取值扩展时，从队列容量较小的操作
* 如果在搜索过程中搜索到对方的哈希表中存在的节点，说明找到了最短路径。

需要注意返回的是单词总数包含首尾，所以需要给转化结果+1，无法转化按要求返回0。  


### 代码
```java
public class LeetCode_127_1_单词接龙 {
    String b;
    String e;
    Set<String> set = new HashSet<>();
    public int ladderLength(String beginWord, String endWord, List<String> wordList) {
        b = beginWord;
        e = endWord;
        set.addAll(wordList);
        if (!set.contains(endWord)) {
            return 0;
        }
        int t = dfs();
        return t == -1 ? 0 : t + 1;
    }

    private int dfs() {
        Deque<String> d1 = new ArrayDeque<>(), d2 = new ArrayDeque<>();

        Map<String, Integer> m1 = new HashMap<>(), m2 = new HashMap<>();
        m1.put(b, 0);
        m2.put(e, 0);
        d1.add(b);
        d2.add(e);
        while (!d1.isEmpty() && !d2.isEmpty()) {
            int t = -1;
            if (d1.size() <= d2.size()) {
                t = update(d1, m1, m2);
            } else {
                t = update(d2, m2, m1);
            }
            if (t != -1) {
                return t;
            }
        }
        return -1;
    }

    private int update(Deque<String> deque, Map<String, Integer> source, Map<String, Integer> other) {
        String v = deque.poll();
        int n = v.length();
        for (int i = 0; i < n; i++) {
            for (int j = 0; j < 26; j++) {
                String s = v.substring(0, i) + (char)('a' + j) + v.substring(i + 1, n);
                if (set.contains(s)) {
                    if (source.containsKey(s)) {
                        continue;
                    }
                    if (other.containsKey(s)) {
                        return source.get(v) + 1 + other.get(s);
                    }
                    deque.add(s);
                    source.put(s, source.get(v) + 1);
                }
            }
        }
        return -1;
    }
}
```

# [LeetCode_752_1_打开转盘锁](https://leetcode-cn.com/problems/open-the-lock/)
## 题目
你有一个带有四个圆形拨轮的转盘锁。每个拨轮都有10个数字： '0', '1', '2', '3', '4', '5', '6', '7', '8', '9' 。每个拨轮可以自由旋转：例如把 '9' 变为 '0'，'0' 变为 '9' 。每次旋转都只能旋转一个拨轮的一位数字。

锁的初始数字为 '0000' ，一个代表四个拨轮的数字的字符串。

列表 deadends 包含了一组死亡数字，一旦拨轮的数字和列表里的任何一个元素相同，这个锁将会被永久锁定，无法再被旋转。

字符串 target 代表可以解锁的数字，你需要给出解锁需要的最小旋转次数，如果无论如何不能解锁，返回 -1 。

示例 1:
```
输入：deadends = ["0201","0101","0102","1212","2002"], target = "0202"
输出：6
解释：
可能的移动序列为 "0000" -> "1000" -> "1100" -> "1200" -> "1201" -> "1202" -> "0202"。
注意 "0000" -> "0001" -> "0002" -> "0102" -> "0202" 这样的序列是不能解锁的，
因为当拨动到 "0102" 时这个锁就会被锁定。
```

示例 2:
```
输入: deadends = ["8888"], target = "0009"
输出：1
解释：
把最后一位反向旋转一次即可 "0000" -> "0009"。
```

示例 3:
```
输入: deadends = ["8887","8889","8878","8898","8788","8988","7888","9888"], target = "8888"
输出：-1
解释：
无法旋转到目标数字且不被锁定。
```

示例 4:
```
输入: deadends = ["0000"], target = "8888"
输出：-1
```

提示：

* 1 <= deadends.length <= 500
* deadends[i].length == 4
* target.length == 4
* target 不在 deadends 之中
* target 和 deadends[i] 仅由若干位数字组成

## 理解
利用bfs解决，普通bfs有搜索空间爆炸问题，使用双向bfs来减小数据空间宽度。
* 创建两个队列分别用于两个方向的搜索
* 创建两个哈希表，用来过滤重复方案和记录转化次数（通过多少步转化而来）
* 为了尽可能的让两个搜索方向平均，每次从队列取值扩展时，从队列容量较小的操作
* 如果在搜索过程中搜索到对方的哈希表中存在的节点，说明找到了最短路径。

需要注意转盘每个位置转动都可以+1或者-1，所以里层遍历是从-1到1，需要跳过0，并且
转盘从9，+1到0，0-1即-1是9，需要单独处理。 

### 代码
```java
public class LeetCode_752_1_打开转盘锁 {
    String s, t;
    Set<String> set = new HashSet<>();
    public int openLock(String[] deadends, String target) {
        s = "0000";
        t = target;
        set.addAll(Arrays.asList(deadends));
        if (s.equals(t)) {
            return 0;
        }
        if (set.contains(s)) {
            return -1;
        }
        return dfs();
    }

    private int dfs() {
        int a = -1;
        Deque<String> d1 = new ArrayDeque<>(), d2 = new ArrayDeque<>();
        Map<String, Integer> m1 = new HashMap<>(), m2 = new HashMap<>();
        d1.add(s);
        d2.add(t);
        m1.put(s, 0);
        m2.put(t, 0);
        while (!d1.isEmpty() && !d2.isEmpty()) {
            if (d1.size() <= d2.size()) {
                a = update(d1, m1, m2);
            } else {
                a = update(d2, m2, m1);
            }
            if (a != -1) {
                return a;
            }
        }
        return -1;
    }

    private int update(Deque<String> deque, Map<String, Integer> source, Map<String, Integer> other) {
        String v = deque.poll();
        // 转盘4位
        for (int i = 0; i < 4; i++) {
            // 每一位都可以+1或者-1
            for (int j = -1; j <= 1; j++) {
                if (j == 0) {
                    continue;
                }

                int origin = v.charAt(i) - '0';
                int next = (origin + j) % 10;
                if (next == -1) {
                    next = 9;
                }
                // 拼接经过一次转动的转盘数据
                String newS = v.substring(0, i) + next + v.substring(i + 1);
                // 如果存在于卡死集合中，跳过
                if (set.contains(newS)) {
                    continue;
                }
                // 如果存在于原map中，代表已经转到过，跳过
                if (source.containsKey(newS)) {
                    continue;
                }
                // 如果存在于other中，代表找到了最短路径
                if (other.containsKey(newS)) {
                    return source.get(v) + 1 + other.get(newS);
                }
                source.put(newS, source.get(v) + 1);
                deque.add(newS);
            }
        }
        return -1;
    }
}
```