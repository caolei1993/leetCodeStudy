[toc]

# [LeetCode_639_1_解码方法II](https://leetcode-cn.com/problems/decode-ways-ii/)
## 题目
一条包含字母 A-Z 的消息通过以下的方式进行了编码：
```
'A' -> 1
'B' -> 2
...
'Z' -> 26
```

要 解码 一条已编码的消息，所有的数字都必须分组，然后按原来的编码方案反向映射回字母（可能存在多种方式）。例如，"11106" 可以映射为：

* "AAJF" 对应分组 (1 1 10 6)
* "KJF" 对应分组 (11 10 6)  

注意，像 (1 11 06) 这样的分组是无效的，因为 "06" 不可以映射为 'F' ，因为 "6" 与 "06" 不同。

除了 上面描述的数字字母映射方案，编码消息中可能包含 '*' 字符，可以表示从 '1' 到 '9' 的任一数字（不包括 '0'）。例如，编码字符串 "1*" 可以表示 "11"、"12"、"13"、"14"、"15"、"16"、"17"、"18" 或 "19" 中的任意一条消息。对 "1*" 进行解码，相当于解码该字符串可以表示的任何编码消息。

给你一个字符串 s ，由数字和 '*' 字符组成，返回 解码 该字符串的方法 数目 。

由于答案数目可能非常大，返回对 10^9 + 7 取余 的结果。

 
示例 1：
```
输入：s = "*"
输出：9
解释：这一条编码消息可以表示 "1"、"2"、"3"、"4"、"5"、"6"、"7"、"8" 或 "9" 中的任意一条。
可以分别解码成字符串 "A"、"B"、"C"、"D"、"E"、"F"、"G"、"H" 和 "I" 。
因此，"*" 总共有 9 种解码方法。
```

示例 2：
```
输入：s = "1*"
输出：18
解释：这一条编码消息可以表示 "11"、"12"、"13"、"14"、"15"、"16"、"17"、"18" 或 "19" 中的任意一条。
每种消息都可以由 2 种方法解码（例如，"11" 可以解码成 "AA" 或 "K"）。
因此，"1*" 共有 9 * 2 = 18 种解码方法。
```

示例 3：
```
输入：s = "2*"
输出：15
解释：这一条编码消息可以表示 "21"、"22"、"23"、"24"、"25"、"26"、"27"、"28" 或 "29" 中的任意一条。
"21"、"22"、"23"、"24"、"25" 和 "26" 由 2 种解码方法，但 "27"、"28" 和 "29" 仅有 1 种解码方法。
因此，"2*" 共有 (6 * 2) + (3 * 1) = 12 + 3 = 15 种解码方法。
```
 
提示：

* 1 <= s.length <= 10^5
* s[i] 是 0 - 9 中的一位数字或字符 '*'

## 理解
解法一：分情况讨论DP  
定义f[i]代表以cs[i]为结尾的字符串解码的方案数  
初始化f[0]，f[0] = cs[0] == '*' ? 9 : (cs[0] != '0' ? 1 : 0)  
不失一般性的考虑一下f[i]该如何转移，cs[i]要么是*，要么是数字，分情况讨论：  
> 当cs[i] == '*'时：
>> 1. 此时cs[i]可以单独看做是一个item，f[i] = f[i - 1] * 9
>> 2. cs[i]也可以与前一位一起组成一个item
>>>  i 前一位是'*'，那么两个连续的*可组成11-26间除了20以外的所有数，共15种，f[i] = f[i - 2] * 15  
>>> ii 前一位是1，那么1和*可组成11-19，共9种，f[i] = f[i - 2] * 9  
>>> iii 前一位是2，那么2和*可组成21-26，共6种，f[i] = f[i - 2] * 6  
>
> 当cs[i]是数字时：
>> 1. 前一位是*：  
>>> i 当前为0，那么可组成1x，或2x两种可能，f[i] = f[i - 2] * 2  
>>> ii 当前为1-9，那么当前值可单独作为一个item，f[i] = f[i - 1]  
>>> iii 当前值为1-6，可以与前一位*组成1x或2x两种可能，f[i] = f[i - 2] * 2  
>>> iiii 当前值为7-9，与前一位*只能组成1x可能，f[i] = f[i - 2]  
>> 2. 前一位是数字：
>>> i 当前位如果是0，前一位只有是1或者2才合法，f[i] = f[i - 2]  
>>> ii 当前位是1-9，当前位可以单独作为一个item，f[i] = f[i - 1]  
>>>> * 前一位是1，可以与当前位组成11-19，f[i] = f[i - 2]  
>>>> * 前一位是2，如果当前位是1-6，可组成21-26合法方案，f[i] = f[i -2]

总的方案数是f[i]上述所有情况的累加和，再对1e9+7取模  

需要注意的是我们多次使用f[i - 2]，这里需要对i - 2的合法性做判断，为了避免对i-2的合法性判断，
我们可以对s前加一个空格作为哨兵（无须真正插入），以简化代码，同时由于 f[i] 只依赖于 f[i - 1]
 和 f[i - 2]，可使用滚动数组优化空间。  
 
 注意使用滚动数组优化空间时，不能偷懒使用toCharArray方法，只能用charAt，因为java为了遵循字符串
 不变原则在调用toCharArray时会返回新的数组，这样空间复杂度仍然为O(n) 
  
 时间复杂度为O(n)，空间复杂度未优化前为O(n)，使用滚动数组优化后为O(1)  
 
 解法二：枚举DP  
 解法一之所以复杂是因为我们不但要枚举当前字符cs[i]，还需要对上一个字符prev分情况讨论  
 
 换一种思路，我们可以利用解码对象只有A-Z来枚举  
 
 从前往后遍历字符串s的时候，我们判断cs[i]参与构成的解码内容item是A-Z中的哪一个，从而将分情况讨论
 转化为与对应的解析字符做对比。  
 
 时间复杂度为O(n*C)，C是常数26，空间复杂度使用滚动数组优化为O(1)


## 解法一
### 代码
```java
public class LeetCode_639_1_解码方法II {
    static final int mod = (int)1e9 + 7;
    public int numDecodings(String s) {
        char[] cs = s.toCharArray();
        int n = cs.length;
        // 定义状态f[i]代表以cs[i]为结尾的字符串解码的方案数
        long[] f = new long[n];
        // f[0]值的初始化
        f[0] = cs[0] == '*' ? 9 : (cs[0] != '0' ? 1 : 0);
        for (int i = 1; i < n; i++) {
            char c = cs[i], prev = cs[i - 1];
            long l = i - 2 >= 0 ? f[i - 2] : 1;
            if (c == '*') {
                // 此时c可以单独作为一个item
                f[i] += f[i - 1] * 9;
                // 与前一位组合在一起
                if (prev == '*') {
                    // 与前一位可以组成11 - 26中不算20的所有数，共15种
                    f[i] += l * 15;
                } else {
                    int u = prev - '0';
                    if (u == 1) {
                        f[i] += l * 9;
                    } else if (u == 2) {
                        f[i] += l * 6;
                    }
                }
            } else {
                // c为数字
                int v = c - '0';
                if (prev == '*') {
                    if (v == 0) {
                        // 只能组成10,20两种情况
                        f[i] += l * 2;
                    } else {
                        // c可以单独作为一个item
                        f[i] += f[i - 1];
                        if (1 <= v && v <= 6) {
                            // c可以与前一位组成1x或2x两种情况
                            f[i] += l * 2;
                        } else {
                            // 剩余7 - 9的情况只可能是1x
                            f[i] += l;
                        }
                    }
                } else {
                    // prev是数字
                    int u = prev - '0';
                    if (v == 0) {
                        // c为0时，只有前一位是1或2时才合法
                        if (u == 1 || u == 2) {
                            f[i] += l;
                        }
                    } else {
                        // c是1 - 9，c可以单独作为一个item
                        f[i] += f[i - 1];
                        if (u == 1) {
                            // 前一位是1，与当前位可组成11-19的合法方案
                            f[i] += l;
                        } else if (u == 2) {
                            if (1 <= v && v <= 6) {
                                f[i] += l;
                            }
                        }
                    }
                }
            }
            f[i] %= mod;
        }
        return (int)f[n - 1];
    }
}
```

## 解法二
### 代码
```java
public class LeetCode_639_2_解码方法II {
    static final int mod = (int)1e9 + 7;
    public int numDecodings(String s) {
        int n = s.length();
        long[] f = new long[3];
        f[0] = 1;
        for (int i = 1; i <= n; i++) {
            char c = s.charAt(i - 1);
            int v = c - '0';
            long cnt = 0;
            int p1 = (i - 1) % 3, p2 = (i - 2) % 3;
            for (int item = 1; item <= 26; item++) {
                if (item < 10) {
                    // c单独作为一个item
                    if (c == '*' || v == item) {
                        cnt += f[p1];
                    }
                } else {
                    // 与前一位一起组成item
                    if (i - 2 < 0) {
                        break;
                    }
                    char prev = s.charAt(i - 2);
                    int u = prev - '0';
                    int a = item / 10, b = item % 10;
                    if ((prev == '*' || u == a) && ((c == '*' && b != 0) || b == v)) {
                        cnt += f[p2];
                    }
                }
            }
            f[i % 3] = cnt % mod;
        }
        return (int)f[n % 3];
    }
}
```

# [LeetCode_437_1_路径总和III](https://leetcode-cn.com/problems/path-sum-iii/)
## 题目
给定一个二叉树的根节点 root ，和一个整数 targetSum ，求该二叉树里节点值之和等于 targetSum 的 路径 的数目。

路径 不需要从根节点开始，也不需要在叶子节点结束，但是路径方向必须是向下的（只能从父节点到子节点）。

 

示例 1：
```
输入：root = [10,5,-3,3,2,null,11,3,-2,null,1], targetSum = 8
输出：3
解释：和等于 8 的路径有 3 条，如图所示。
```


示例 2：
```
输入：root = [5,4,8,11,null,13,4,7,2,null,null,5,1], targetSum = 22
输出：3
```

提示:

* 二叉树的节点个数的范围是 [0,1000]
* -10^9 <= Node.val <= 10^9 
* -1000 <= targetSum <= 1000 

## 理解
解法一：树的遍历+DFS  
使用前序遍历dfs1来遍历树的所有节点，在dfs1中调用dfs2来遍历每个节点为根节点往下的所有路径，遍历过程中
计算节点的值与targetSum比对，将值相等的方案累加到返回值中。

整体时间复杂度为O(n^2)，空间复杂度为O(n^2)，因为有递归栈的存在。

解法二：树的遍历+前缀和  
解法一中我们统计的是以每个节点为开头往下的所有路径，换一个思路，我们统一以每一个节点为结尾的所有合法
数量，配合我们本身就是从上往下进行树的遍历，相当于我们只需要在完整路径（根节点到当前节点的唯一路径）中
找到有多少个节点到当前节点的路径总和为targetSum  

于是这个问题转化为：求解原始根节点到当前节点b的路径中，有多少个节点a，满足Sum[a……b] = targetSum，
由于原始根节点到当前节点的路径是唯一的，所以问题转化为一维前缀和问题。

具体的，我们可以在遍历过程中，记录下从原始节点root到当前节点cur路径中，从root到任意中间节点x的路径
总和，存入哈希表，当遍历到当前节点，我们可以配合哈希表快速找到，是否存在有节点到当前节点cur的路径总和
为targetSum。如果存在累加在返回值中即可。

需要注意我们遍历完当前节点的子节点后需要回溯的将路径总和从哈希表中删除，防止统计跨越到两个方向的路径。

整体时间复杂度为O(n)，空间复杂度为O(n)

## 解法一
### 代码
```java
public class LeetCode_437_1_路径总和III {

    int ans;
    int target;

    public int pathSum(TreeNode root, int targetSum) {
        target = targetSum;
        dfs1(root);
        return ans;
    }

    private void dfs1(TreeNode node) {
        if (node == null) {
            return;
        }
        dfs2(node, node.val);
        dfs1(node.left);
        dfs1(node.right);
    }

    private void dfs2(TreeNode node, int val) {
        if (val == target) {
            ans++;
        }
        if (node.left != null) {
            dfs2(node.left, val + node.left.val);
        }
        if (node.right != null) {
            dfs2(node.right, val + node.right.val);
        }
    }
}
```

## 解法二
### 代码
```java
public class LeetCode_437_2_路径总和III {
    Map<Integer, Integer> map = new HashMap<>();
    int ans, target;

    public int pathSum(TreeNode root, int targetSum) {
        if (root == null) {
            return 0;
        }
        target = targetSum;
        map.put(0, 1);
        dfs(root, root.val);
        return ans;
    }

    private void dfs(TreeNode root, int val) {
        if (map.containsKey(val - target)) {
            ans += map.get(val - target);
        }
        map.put(val, map.getOrDefault(val, 0) + 1);
        if (root.left != null) {
            dfs(root.left, val + root.left.val);
        }
        if (root.right != null) {
            dfs(root.right, val + root.right.val);
        }
        map.put(val, map.get(val) - 1);
    }
}
```

# [LeetCode_517_1_超级洗衣机](https://leetcode-cn.com/problems/super-washing-machines/)
## 题目
假设有 n 台超级洗衣机放在同一排上。开始的时候，每台洗衣机内可能有一定量的衣服，也可能是空的。

在每一步操作中，你可以选择任意 m (1 <= m <= n) 台洗衣机，与此同时将每台洗衣机的一件衣服送到相邻的一台洗衣机。

给定一个整数数组 machines 代表从左至右每台洗衣机中的衣物数量，请给出能让所有洗衣机中剩下的衣物的数量相等的 最少的操作步数 。如果不能使每台洗衣机中衣物的数量相等，则返回 -1 。

 

示例 1：
```
输入：machines = [1,0,5]
输出：3
解释：
第一步:    1     0 <-- 5    =>    1     1     4
第二步:    1 <-- 1 <-- 4    =>    2     1     3    
第三步:    2     1 <-- 3    =>    2     2     2   
```

示例 2：
```
输入：machines = [0,3,0]
输出：2
解释：
第一步:    0 <-- 3     0    =>    1     2     0    
第二步:    1     2 --> 0    =>    1     1     1   
```
  
示例 3：
```
输入：machines = [0,2,0]
输出：-1
解释：
不可能让所有三个洗衣机同时剩下相同数量的衣物。
```

提示：

* n == machines.length
* 1 <= n <= 10^4
* 0 <= machines[i] <= 10^5

## 理解
贪心算法  

实现上，首先我们可以求得衣服总和 sum 以及洗衣机数量 n，从而判断无解情况（sum % n != 0），或者计算最终每台洗衣机的衣服数量 t = sum / n。

然后使用两个变量 ls 和 rs 分别表示当前位置「左边的衣服总数」和「右边的衣服总数」，并在从左往右的遍历过程中实时维护。

对于某个位置 x 而言，达到最终平衡需要从 x 右边往左边运送的衣服数量为 a = max(i * t - ls, 0)，
即左边的当前的衣服数量与最终状态的衣服数量的差值，与 0 取 max 含义代表为如果当前左边衣服多
于最终衣服数量时，此时不需要消耗从右到左的移动次数（只需要消耗从 x 左边到 x 右边的移动次数）；
右边分析同理，我们可以得到达到最终平衡需要从 x 左边到右运送的衣服数量为 
b = max((n - i - 1) * t - rs, 0)。

在所有位置的 a + b 之间取最大值即是答案。

时间复杂度为O(n)，空间复杂度为O(1)


### 代码
```java
public class LeetCode_517_1_超级洗衣机 {
    public int findMinMoves(int[] machines) {
        int n = machines.length;
        int sum = 0, ans = 0;
        for (int m : machines) {
            sum += m;
        }
        if (sum % n != 0) {
            return -1;
        }
        int t = sum / n;

        int ls = 0, rs = sum;
        for (int i = 0; i < n; i++) {
            rs -= machines[i];
            // 需要从左向右传输的
            int a = Math.max(i * t - ls, 0);
            // 需要从右向左传输的
            int b = Math.max((n - i - 1) * t - rs, 0);
            ans = Math.max(ans, a + b);
            ls += machines[i];
        }
        return ans;
    }
}
```


# [LeetCode_223_1_矩形面积](https://leetcode-cn.com/problems/rectangle-area/)
## 题目
给你 二维 平面上两个 由直线构成的 矩形，请你计算并返回两个矩形覆盖的总面积。

每个矩形由其 左下 顶点和 右上 顶点坐标表示：

* 第一个矩形由其左下顶点 (ax1, ay1) 和右上顶点 (ax2, ay2) 定义。  
* 第二个矩形由其左下顶点 (bx1, by1) 和右上顶点 (bx2, by2) 定义。
 
示例 1：

```
输入：ax1 = -3, ay1 = 0, ax2 = 3, ay2 = 4, bx1 = 0, by1 = -1, bx2 = 9, by2 = 2
输出：45
```

示例 2：
```
输入：ax1 = -2, ay1 = -2, ax2 = 2, ay2 = 2, bx1 = -2, by1 = -2, bx2 = 2, by2 = 2
输出：16
```

提示：

* -10^4 <= ax1, ay1, ax2, ay2, bx1, by1, bx2, by2 <= 10^4

## 理解
先求取两个矩形的面积A和B，再求取阴影部分面积C（注意求取阴影部分边时需要和0取max，无交集时为负值），根据
容斥原理，最终结果为A+B-C   

时间复杂度和空间复杂度均为O(1)

### 代码
```java
public class LeetCode_223_1_矩形面积 {
    public int computeArea(int ax1, int ay1, int ax2, int ay2, int bx1, int by1, int bx2, int by2) {
        // 求取矩形1和矩形2的面积和
        int sum = (ax2 - ax1) * (ay2 - ay1) + (bx2 - bx1) * (by2 - by1);
        // 求取阴影部分面积
        int x = Math.max(0, Math.min(ax2 , bx2) - Math.max(ax1, bx1));
        int y = Math.max(0, Math.min(ay2, by2) - Math.max(ay1, by1));
        return sum - x * y;
    }
}
```

# [LeetCode_1436_1_旅行的终点站](https://leetcode-cn.com/problems/destination-city/)
## 题目
给你一份旅游线路图，该线路图中的旅行线路用数组 paths 表示，其中 paths[i] = [cityAi, cityBi] 表示该线路将会从 cityAi 直接前往 cityBi 。请你找出这次旅行的终点站，即没有任何可以通往其他城市的线路的城市。

题目数据保证线路图会形成一条不存在循环的线路，因此恰有一个旅行终点站。

 
示例 1：
```

```
输入：paths = [["London","New York"],["New York","Lima"],["Lima","Sao Paulo"]]
输出："Sao Paulo" 
解释：从 "London" 出发，最后抵达终点站 "Sao Paulo" 。本次旅行的路线是 "London" -> "New York" -> "Lima" -> "Sao Paulo" 。
示例 2：
```

```
输入：paths = [["B","C"],["D","B"],["C","A"]]
输出："A"
解释：所有可能的线路是：
"D" -> "B" -> "C" -> "A". 
"B" -> "C" -> "A". 
"C" -> "A". 
"A". 
显然，旅行终点站是 "A" 。
示例 3：
```

```
输入：paths = [["A","Z"]]
输出："Z"
 

提示：

* 1 <= paths.length <= 100
* paths[i].length == 2
* 1 <= cityAi.length, cityBi.length <= 10
* cityAi != cityBi
* 所有字符串均由大小写英文字母和空格字符组成。

## 理解
字符串简单模拟题，使用哈希表存储所有路线，寻找终点不能作为起点的终点即为最终终点  

时间复杂度和空间复杂度均为O(n)

### 代码
```java
public class LeetCode_1436_1_旅行的终点站 {
    public String destCity(List<List<String>> paths) {
        Map<String, String> map = new HashMap<>();
        for (List<String> path : paths) {
            map.put(path.get(0), path.get(1));
        }
        for (Map.Entry<String, String> entry : map.entrySet()) {
            String v = entry.getValue();
            if (!map.containsKey(v)) {
                return v;
            }
        }
        return null;
    }
}
```

# [LeetCode_405_1_数字转化为十六进制数](https://leetcode-cn.com/problems/convert-a-number-to-hexadecimal/)
## 题目
给定一个整数，编写一个算法将这个数转换为十六进制数。对于负整数，我们通常使用 补码运算 方法。

注意:

1. 十六进制中所有字母(a-f)都必须是小写。
2. 十六进制字符串中不能包含多余的前导零。如果要转化的数为0，那么以单个字符'0'来表示；对于其他情况，十六进制字符串中的第一个字符将不会是0字符。 
3. 给定的数确保在32位有符号整数范围内。
4. 不能使用任何由库提供的将数字直接转换或格式化为十六进制的方法。


示例 1：
```
输入:
26

输出:
"1a"
```

示例 2：
```
输入:
-1

输出:
"ffffffff"
```

## 理解
解法一：  
常规进制转化题，我们从低位开始转化，最低位余数为num % 进制，num /= 进制，通过上述式子不断循环求解，
最终将拼接的结果翻转即为转化好的结果  

* 需要额外判断0的情况
* 因为存在负数，切负数为补码的方法，我们提前判断如果为负数，则求取对应补码的值
* 十六进制每位0-9，10-15需要分开讨论拼接

时间复杂度和空间复杂度取决于构成十六进制的长度，长度固定为C=8，复杂度均为O(C)

解法二：  
分组换算法求解：我们知道2进制转16进制就是4位代表16进制1位，所以我们可以对int类型的32位2进制分组转化，
而且负数在二进制中本身就是以补码形式表示的，无需单独处理负数。利用无符号右移来处理循环数据

复杂度同上

## 解法一
### 代码
```java
public class LeetCode_405_1_数字转化为十六进制数 {
    public String toHex(int num) {
        if (num == 0) {
            return "0";
        }
        long n = num;
        StringBuilder sb = new StringBuilder();
        if (n < 0) {
            n = (long)(Math.pow(2, 32) + n);
        }
        while (n != 0) {
            int a = num % 16;
            if (a == 0) {
                a = num;
            }
            if (a > 9) {
                sb.append((char)((a - 10) + 'a'));
            } else {
                sb.append(a);
            }
            num /= 16;
        }

        return sb.reverse().toString();
    }
}
```

## 解法二
### 代码
```java
public class LeetCode_405_2_数字转化为十六进制数 {
    public String toHex(int num) {
        if (num == 0) {
            return "0";
        }
        StringBuilder sb = new StringBuilder();
        while (num != 0) {
            int a = num & 15;
            char c = (char)(a + '0');
            if (a > 9) {
               c = (char)((a - 10) + 'a');
            }

            sb.append(c);
            num >>>= 4;
        }
        return sb.reverse().toString();
    }
}
```


# [LeetCode_166_1_分数到小数](https://leetcode-cn.com/problems/fraction-to-recurring-decimal/)
## 题目
给定两个整数，分别表示分数的分子 numerator 和分母 denominator，以 字符串形式返回小数 。

如果小数部分为循环小数，则将循环的部分括在括号内。

如果存在多个答案，只需返回 任意一个 。

对于所有给定的输入，保证 答案字符串的长度小于 10^4 。

示例 1：
```
输入：numerator = 1, denominator = 2
输出："0.5"
```

示例 2：
```
输入：numerator = 2, denominator = 1
输出："2"
```

示例 3：
```
输入：numerator = 2, denominator = 3
输出："0.(6)"
```

示例 4：
```
输入：numerator = 4, denominator = 333
输出："0.(012)"
```

示例 5：
```
输入：numerator = 1, denominator = 5
输出："0.2"
```
 

提示：

* -2^31 <= numerator, denominator <= 2^31 - 1
* denominator != 0

## 理解
模拟竖式除法求解问题：  
首先明确有理数除法不会出现无限不循环小数的结果（无理数），所以要么可以整除，要么结果为无限循环小数。  
我们通过不断模拟竖式计算逐步求取结果， 当前位等于 a/b，a %= b，a *= 10  

* 注意计算可能会超过int的范围，所以我们将数值处理为long进行计算
* 如果结果为负数，我们需要提前判断，将符号添加上去
* 利用哈希表记录每个余数出现的位置，如果余数重复出现，则说明结果为无限循环小数，将余数位置之前的作为
不循环体，余数位置到当前位置的数为循环体，放入括号中

时间复杂度和空间复杂度取决于答案的最大长度不超过10^4，复杂度均为O(M)，M为答案长度

### 代码
```java
public class LeetCode_166_1_分数到小数 {
    public String fractionToDecimal(int numerator, int denominator) {
        // 将除数被除数转为long型，防止计算的过程中数据溢出int范围
        long a = numerator, b = denominator;
        // 如果能整除，直接返回结果
        if (a % b == 0) {
            return String.valueOf(a / b);
        }
        StringBuilder sb = new StringBuilder();
        // 如果两个数的计算结果为负数，直接在结果前添加负号
        if (a * b < 0) {
            sb.append("-");
        }
        // 取除数与被除数的绝对值，求取正数除法结果
        a = Math.abs(a);
        b = Math.abs(b);
        // 计算小数以前部分
        sb.append(String.valueOf(a / b) + ".");
        a %= b;
        Map<Long, Integer> map = new HashMap<>();
        while (a != 0) {
            // 记录当前余数所在位置
            map.put(a, sb.length());
            a *= 10;
            sb.append(a / b);
            a %= b;

            // 判断当前余数是否之前存在过，如果出现过，则将出现的位置到当前位置的部分抠出来作为循环部分
            if (map.containsKey(a)) {
                int u = map.get(a);
                return String.format("%s(%s)", sb.substring(0, u), sb.substring(u));
            }
        }
        return sb.toString();
    }
}

```