[toc]

# [LeetCode_704_1_二分查找](https://leetcode-cn.com/problems/binary-search/)
## 题目
给定一个 n 个元素有序的（升序）整型数组 nums 和一个目标值 target  ，写一个函数搜索 nums 中的 target，如果目标值存在返回下标，否则返回 -1。

示例 1:
```
输入: nums = [-1,0,3,5,9,12], target = 9
输出: 4
解释: 9 出现在 nums 中并且下标为 4
```

示例 2:
```
输入: nums = [-1,0,3,5,9,12], target = 2
输出: -1
解释: 2 不存在 nums 中因此返回 -1
```

提示：

* 你可以假设 nums 中的所有元素是不重复的。
* n 将在 [1, 10000]之间。
* nums 的每个元素都将在 [-9999, 9999]之间。

## 理解
使用标准的二分法求解，时间复杂度为O(logn)，空间复杂度为O(1)

### 代码
```java
public class LeetCode_704_1_二分查找 {
    public int search(int[] nums, int target) {
        int len = nums.length;
        int l = 0, r = len - 1;
        while (l < r) {
            int mid = l + r >> 1;
            if (nums[mid] < target) {
                l = mid + 1;
            } else {
                r = mid;
            }
        }
        return nums[r] == target ? r : -1;
    }
}
```

# [LeetCode_1221_1_分割平衡字符串](https://leetcode-cn.com/problems/split-a-string-in-balanced-strings/)
## 题目
在一个 平衡字符串 中，'L' 和 'R' 字符的数量是相同的。

给你一个平衡字符串 s，请你将它分割成尽可能多的平衡字符串。

注意：分割得到的每个字符串都必须是平衡字符串。

返回可以通过分割得到的平衡字符串的 最大数量 。

 
示例 1：
```
输入：s = "RLRRLLRLRL"
输出：4
解释：s 可以分割为 "RL"、"RRLL"、"RL"、"RL" ，每个子字符串中都包含相同数量的 'L' 和 'R' 。
```

示例 2：
```
输入：s = "RLLLLRRRLR"
输出：3
解释：s 可以分割为 "RL"、"LLLRRR"、"LR" ，每个子字符串中都包含相同数量的 'L' 和 'R' 。
```

示例 3：
```
输入：s = "LLLLRRRR"
输出：1
解释：s 只能保持原样 "LLLLRRRR".
```

示例 4：
```
输入：s = "RLRRRLLRLL"
输出：2
解释：s 可以分割为 "RL"、"RRRLLRLL" ，每个子字符串中都包含相同数量的 'L' 和 'R' 。
```

提示：

* 1 <= s.length <= 1000
* s[i] = 'L' 或 'R'
* s 是一个 平衡 字符串

## 理解
贪心算法，可以将字符串中的L和R分别映射为1和-1，在遍历字符串字符的同时求取映射值的累加和，当累加和为
0时就代表发现了一个平衡的子字符串。累加平衡字符串个数返回即可。

### 代码
```java
public class LeetCode_1221_1_分割平衡字符串 {
    public int balancedStringSplit(String s) {
        int len = s.length();
        int ans = 0, value = 0;
        for (int i = 0; i < len; i++) {
            int a = 'L' == s.charAt(i) ? 1 : -1;
            value += a;
            if (value == 0) {
                ans++;
            }
        }
        return ans;
    }
}
```

# [LeetCode_502_1_IPO](https://leetcode-cn.com/problems/ipo/)
## 题目
假设 力扣（LeetCode）即将开始 IPO 。为了以更高的价格将股票卖给风险投资公司，力扣 希望在 IPO 之前开展一些项目以增加其资本。 由于资源有限，它只能在 IPO 之前完成最多 k 个不同的项目。帮助 力扣 设计完成最多 k 个不同项目后得到最大总资本的方式。

给你 n 个项目。对于每个项目 i ，它都有一个纯利润 profits[i] ，和启动该项目需要的最小资本 capital[i] 。

最初，你的资本为 w 。当你完成一个项目时，你将获得纯利润，且利润将被添加到你的总资本中。

总而言之，从给定项目中选择 最多 k 个不同项目的列表，以 最大化最终资本 ，并输出最终可获得的最多资本。

答案保证在 32 位有符号整数范围内。

 
示例 1：
```
输入：k = 2, w = 0, profits = [1,2,3], capital = [0,1,1]
输出：4
解释：
由于你的初始资本为 0，你仅可以从 0 号项目开始。
在完成后，你将获得 1 的利润，你的总资本将变为 1。
此时你可以选择开始 1 号或 2 号项目。
由于你最多可以选择两个项目，所以你需要完成 2 号项目以获得最大的资本。
因此，输出最后最大化的资本，为 0 + 1 + 3 = 4。
```

示例 2：
```
输入：k = 3, w = 0, profits = [1,2,3], capital = [0,1,2]
输出：6
```


提示：

* 1 <= k <= 10^5
* 0 <= w <= 10^9
* n == profits.length
* n == capital.length
* 1 <= n <= 10^5
* 0 <= profits[i] <= 10^4
* 0 <= capital[i] <= 10^9

## 理解
贪心+优先队列：  
由题意可知要得到最大资本，我们需要在每次选择任务时，选择当前资本可选择的利润最大的任务。  
我们可以将每个任务的启动的最小资本和利润存入list，并对list按照最小资本进行排序。  
我们每次遍历时，都将最小资本小于当前资本的任务存入优先队列，优先队列中的按利润由大到小排序，
每次获取任务，都获取当前队列头部的任务，即利润最大的任务，利润值叠加在已有的资本上，直到获取
完k个任务，或者队列中没有可以获取的任务。最终返回叠加的资本。  

时间复杂度：因为我们利用了二元组数组并排序，时间复杂度为O(nlogn)，优先队列最多有n个元素，使用大根堆
计算答案的复杂度为O(klogn)，整体复杂度为O(max(nlogn, klogn))  
空间复杂度为O(n)

### 代码
```java
public class LeetCode_502_1_IPO {
    public int findMaximizedCapital(int k, int w, int[] profits, int[] capital) {
        int n = profits.length;
        // 预处理，将所有任务的启动资本和利润存入List并排序
        List<int[]> list = new ArrayList<>();
        for (int i = 0; i < n; i++) {
            list.add(new int[]{capital[i], profits[i]});
        }
        list.sort((a, b) -> a[0] - b[0]);

        PriorityQueue<Integer> queue = new PriorityQueue<>((a, b) -> b - a);
        // 定义添加任务的下标
        int i = 0;
        while (k-- > 0) {
            while (i < n && list.get(i)[0] <= w) {
                queue.add(list.get(i++)[1]);
            }
            if (queue.isEmpty()) {
                break;
            }
            w += queue.poll();
        }
        return w;
    }
}
```

# [LeetCode_68_1_文本左右对齐](https://leetcode-cn.com/problems/text-justification/)
## 题目
给定一个单词数组和一个长度 maxWidth，重新排版单词，使其成为每行恰好有 maxWidth 个字符，且左右两端对齐的文本。

你应该使用“贪心算法”来放置给定的单词；也就是说，尽可能多地往每行中放置单词。必要时可用空格 ' ' 填充，使得每行恰好有 maxWidth 个字符。

要求尽可能均匀分配单词间的空格数量。如果某一行单词间的空格不能均匀分配，则左侧放置的空格数要多于右侧的空格数。

文本的最后一行应为左对齐，且单词之间不插入额外的空格。

说明:

* 单词是指由非空格字符组成的字符序列。
* 每个单词的长度大于 0，小于等于 maxWidth。
* 输入单词数组 words 至少包含一个单词。

示例:
```
输入:
words = ["This", "is", "an", "example", "of", "text", "justification."]
maxWidth = 16
输出:
[
   "This    is    an",
   "example  of text",
   "justification.  "
]
```

示例 2:
```
输入:
words = ["What","must","be","acknowledgment","shall","be"]
maxWidth = 16
输出:
[
  "What   must   be",
  "acknowledgment  ",
  "shall be        "
]
解释: 注意最后一行的格式应为 "shall be    " 而不是 "shall     be",
     因为最后一行应为左对齐，而不是左右两端对齐。       
     第二行同样为左对齐，这是因为这行只包含一个单词。
```

示例 3:
```
输入:
words = ["Science","is","what","we","understand","well","enough","to","explain",
         "to","a","computer.","Art","is","everything","else","we","do"]
maxWidth = 20
输出:
[
  "Science  is  what we",
  "understand      well",
  "enough to explain to",
  "a  computer.  Art is",
  "everything  else  we",
  "do                  "
]
```

## 理解
字符串模拟，我们需要分情况讨论：  
1. 如果当前行只有一个单词，特殊处理为左对齐
2. 如果当前行是最后一行，特殊处理为左对齐
3. 其余为一般情况，分别计算【当前行单词总长度】，【当前行空格总长度】，继而计算每个单词间的空格
长度（此计算向下取整），可能会有向下取整后的单位空格长度 * 单词间位置数 ≠ 空格总长度，此时需要
从前往后每个单位多添加一个空间，直到补偿上差值。

### 代码
```java
public class LeetCode_68_1_文本左右对齐 {
    public List<String> fullJustify(String[] words, int maxWidth) {
        int n = words.length;
        List<String> ans = new ArrayList<>();
        List<String> row = new ArrayList<>();

        for (int i = 0; i < n;) {
            // 获取每行存储的单词，存入row
            row.clear();
            row.add(words[i]);
            int cur = words[i++].length();
            while (i < n && (cur + 1 + words[i].length()) <= maxWidth) {
                row.add(words[i]);
                cur = cur + 1 + words[i++].length();
            }

            // 如果是最后一行
            if (i == n) {
                StringBuilder builder = new StringBuilder(row.get(0));
                for (int j = 1; j < row.size(); j++) {
                    builder.append(" ").append(row.get(j));
                }
                while (builder.length() < maxWidth) {
                    builder.append(" ");
                }
                ans.add(builder.toString());
                break;
            }

            // 如果当前行只有一个单词
            int cnt = row.size();
            if (cnt == 1) {
                StringBuilder builder = new StringBuilder(row.get(0));
                while (builder.length() < maxWidth) {
                    builder.append(" ");
                }
                ans.add(builder.toString());
                continue;
            }

            /**
             * 其余为一般情况
             * wordWidth : 当前行单词总长度;
             * spaceWidth : 当前行空格总长度;
             * spaceItemWidth  : 往下取整后的单位空格长度
             * count : 前count个空格需要多加" "
             */
            int wordWidth = cur - (cnt -1);
            int spaceWidth = maxWidth - wordWidth;
            int spaceItemWidth  = spaceWidth / (cnt - 1);
            int count = spaceWidth - (spaceWidth / (cnt - 1) * (cnt - 1));
            StringBuilder builder = new StringBuilder();
            while (spaceItemWidth > 0) {
                builder.append(" ");
                spaceItemWidth--;
            }
            String spaceItem = builder.toString();
            StringBuilder sb =  new StringBuilder();
            for (int j = 0; j < cnt; j++) {
                sb.append(row.get(j));
                if (j == cnt - 1) {
                    break;
                }
                sb.append(spaceItem);
                if (count > 0) {
                    sb.append(" ");
                    count--;
                }
            }
            ans.add(sb.toString());
        }

        return ans;
    }
}
```

# [LeetCode_1894_1_找到需要补充粉笔的学生编号](https://leetcode-cn.com/problems/find-the-student-that-will-replace-the-chalk/)
## 题目
一个班级里有 n 个学生，编号为 0 到 n - 1 。每个学生会依次回答问题，编号为 0 的学生先回答，然后是编号为 1 的学生，以此类推，直到编号为 n - 1 的学生，然后老师会重复这个过程，重新从编号为 0 的学生开始回答问题。

给你一个长度为 n 且下标从 0 开始的整数数组 chalk 和一个整数 k 。一开始粉笔盒里总共有 k 支粉笔。当编号为 i 的学生回答问题时，他会消耗 chalk[i] 支粉笔。如果剩余粉笔数量 严格小于 chalk[i] ，那么学生 i 需要 补充 粉笔。

请你返回需要 补充 粉笔的学生 编号 。

 
示例 1：
```
输入：chalk = [5,1,5], k = 22
输出：0
解释：学生消耗粉笔情况如下：
- 编号为 0 的学生使用 5 支粉笔，然后 k = 17 。
- 编号为 1 的学生使用 1 支粉笔，然后 k = 16 。
- 编号为 2 的学生使用 5 支粉笔，然后 k = 11 。
- 编号为 0 的学生使用 5 支粉笔，然后 k = 6 。
- 编号为 1 的学生使用 1 支粉笔，然后 k = 5 。
- 编号为 2 的学生使用 5 支粉笔，然后 k = 0 。
编号为 0 的学生没有足够的粉笔，所以他需要补充粉笔。
```

示例 2：
```
输入：chalk = [3,4,1,2], k = 25
输出：1
解释：学生消耗粉笔情况如下：
- 编号为 0 的学生使用 3 支粉笔，然后 k = 22 。
- 编号为 1 的学生使用 4 支粉笔，然后 k = 18 。
- 编号为 2 的学生使用 1 支粉笔，然后 k = 17 。
- 编号为 3 的学生使用 2 支粉笔，然后 k = 15 。
- 编号为 0 的学生使用 3 支粉笔，然后 k = 12 。
- 编号为 1 的学生使用 4 支粉笔，然后 k = 8 。
- 编号为 2 的学生使用 1 支粉笔，然后 k = 7 。
- 编号为 3 的学生使用 2 支粉笔，然后 k = 5 。
- 编号为 0 的学生使用 3 支粉笔，然后 k = 2 。
编号为 1 的学生没有足够的粉笔，所以他需要补充粉笔。
```

 
提示：

* chalk.length == n
* 1 <= n <= 10^5
* 1 <= chalk[i] <= 10^5
* 1 <= k <= 10^9

## 理解
解法一：利用前缀和+二分求解，前缀和主要是为了获取所有人的消耗粉笔的总和以及后续的二分，
使用k取余前缀和的最大值，就获取了小于最大值的一个消耗量，再使用二分在前缀和中找到这个消耗量的
对应位置，返回坐标即可。

解法二：同样求取所有人总的粉笔消耗量，用k取模这个max值，得到一次遍历就可消耗完的消耗量，最后
遍历，求取换粉笔的人的坐标。

## 解法一
### 代码
```java
public class LeetCode_1894_1_找到需要补充粉笔的学生编号 {
    public int chalkReplacer(int[] chalk, int k) {
        int len = chalk.length;
        long[] sum = new long[len];
        sum[0] = chalk[0];
        for (int i = 1; i < len ; i++) {
            sum[i] = sum[i - 1] + chalk[i];
        }

        k %= sum[len - 1];

        int l = 0, r = len - 1;
        while (l < r) {
            int mid = l + r >> 1;
            if (sum[mid] <= k) {
                l = mid + 1;
            } else {
                r = mid;
            }
        }
        return sum[l] > k ? l : l + 1;
    }
}
```

## 解法二
### 代码
```java
public class LeetCode_1894_2_找到需要补充粉笔的学生编号 {
    public int chalkReplacer(int[] chalk, int k) {
        int len = chalk.length;
        long max = 0;
        for (int i : chalk) {
            max += i;
        }
        k %= max;
        for (int i = 0; i < len; i++) {
            k -= chalk[i];
            if (k < 0) {
                return i;
            }
        }
        return -1;
    }
}
```

# [LeetCode_600_1_不含连续1的非负整数](https://leetcode-cn.com/problems/non-negative-integers-without-consecutive-ones/)
## 题目
给定一个正整数 n，找出小于或等于 n 的非负整数中，其二进制表示不包含 连续的1 的个数。

示例 1:
```
输入: 5
输出: 5
解释: 
下面是带有相应二进制表示的非负整数<= 5：
0 : 0
1 : 1
2 : 10
3 : 11
4 : 100
5 : 101
其中，只有整数3违反规则（有两个连续的1），其他5个满足规则。
```

说明: 1 <= n <= 10^9

## 理解

### 代码
```java

```


# [LeetCode_678_1_有效的括号字符串](https://leetcode-cn.com/problems/valid-parenthesis-string/)
## 题目
给定一个只包含三种字符的字符串：（ ，） 和 *，写一个函数来检验这个字符串是否为有效字符串。有效字符串具有如下规则：

1. 任何左括号 ( 必须有相应的右括号 )。
2. 任何右括号 ) 必须有相应的左括号 ( 。
3. 左括号 ( 必须在对应的右括号之前 )。
4. * 可以被视为单个右括号 ) ，或单个左括号 ( ，或一个空字符串。
5. 一个空字符串也被视为有效字符串。  

示例 1:
```
输入: "()"
输出: True
```

示例 2:
```
输入: "(*)"
输出: True
```

示例 3:
```
输入: "(*))"
输出: True
```

注意:

* 字符串大小将在 [1，100] 范围内。

## 理解
解法一：使用动态规划求解  
状态定义f[i][j]代表前i个字符（此处字符串下标从1开始），能否与j个右括号组成合法的括号字符串。  
初始化f[0][0]为true  
分情况讨论：  
1. 当前遍历的字符为'('时，假如f[i][j] = true，那么f[i - 1][j - 1]必然也为true，所以有
f[i][j] = f[i - 1][j - 1]
2. 当前遍历的字符为')'时，假如f[i][j] = true，那么f[i - 1][j + 1]必然为true，所以有
f[i][j] = f[i - 1][j + 1]
3. 当前遍历的字符为'*'时，*既可以代表左括号，也可以代表右括号或者空字符串，所以这个时候
f[i][j] = f[i - 1][j] | f[i - 1][j - 1] | f[i - 1][j + 1]，三种情况有一个为true，结果就
为true。  
最后判断的时候，注意j-1，j+1的越界判断，最终结果为f[n][0]  
时间复杂度为O(n^2)，空间复杂度为O(n^2)

解法二：贪心算法  
我们利用记录未匹配的左括号数量来求解此问题，因为存在*这个不确定因素，所以未匹配的左括号数据也是一个
浮动的区间l,r。  
1. 遇到'('，l和r都+1
2. 遇到')'，l和r都-1，注意l的最小值为0，不能为负数
3. 遇到'*'，l-1，r+1  

当l > r时，即r成为负数，即右括号太多，没有了与之匹配的左括号，直接返回false  
其余情况，遍历完，只要最少的未匹配的左括号l的数量为0，则字符串为有效的括号字符串  
时间复杂度为O(n)，空间复杂度为O(1)

## 解法一
### 代码
```java
public class LeetCode_678_1_有效的括号字符串 {
    public boolean checkValidString(String s) {
        int n = s.length();
        // 状态定义，f[i][j]表示前i个字符（字符下标从1开始），能否与j个右括号形成合法的括号序列
        boolean[][] f = new boolean[n + 1][n + 1];
        f[0][0] = true;
        for (int i = 1; i <= n; i++) {
            char c = s.charAt(i - 1);
            for (int j = 0; j <= i; j++) {
                if (c == '(') {
                    if (j - 1 >= 0) {
                        f[i][j] = f[i - 1][j - 1];
                    }
                } else if (c == ')') {
                    if (j + 1 <= i) {
                        f[i][j] = f[i - 1][j + 1];
                    }
                } else {
                    f[i][j] = f[i - 1][j];
                    if (j - 1 >= 0) {
                        f[i][j] |= f[i - 1][j - 1];
                    }
                    if (j + 1 <= i) {
                        f[i][j] |= f[i - 1][j + 1];
                    }
                }
            }
        }
        return f[n][0];
    }
}

```

## 解法二
### 代码
```java
public class LeetCode_678_2_有效的括号字符串 {
    public boolean checkValidString(String s) {
        int l = 0, r = 0;
        for (char c : s.toCharArray()) {
            if (c == '(') {
                l++;
                r++;
            } else if (c == ')') {
                l--;
                r--;
            } else {
                l--;
                r++;
            }
            l = Math.max(l, 0);
            if (l > r) {
                return false;
            }
        }
        return l == 0;
    }
}
```