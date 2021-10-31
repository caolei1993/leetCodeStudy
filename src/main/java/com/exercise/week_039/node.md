[toc]

# [LeetCode_240_1_搜索二维矩阵II](https://leetcode-cn.com/problems/search-a-2d-matrix-ii/)
## 题目
编写一个高效的算法来搜索 m x n 矩阵 matrix 中的一个目标值 target 。该矩阵具有以下特性：

每行的元素从左到右升序排列。  
每列的元素从上到下升序排列。
 

示例 1：
```
输入：matrix = [[1,4,7,11,15],[2,5,8,12,19],[3,6,9,16,22],[10,13,14,17,24],[18,21,23,26,30]], target = 5
输出：true
```


示例 2：
```
输入：matrix = [[1,4,7,11,15],[2,5,8,12,19],[3,6,9,16,22],[10,13,14,17,24],[18,21,23,26,30]], target = 20
输出：false
```
 

提示：

* m == matrix.length
* n == matrix[i].length
* 1 <= n, m <= 300
* -10^9 <= matrix[i][j] <= 10^9
* 每行的所有元素从左到右升序排列
* 每列的所有元素从上到下升序排列
* -10^9 <= target <= 10^9

## 理解
解法一：二分法查找  
使用二分法锁定值出现的列的最大值，同样使用二分法锁定值出现的行的最大值，在最大列和最大行之前遍历查找
目标值  
时间复杂度最差为O(m*n)，即目标值在矩阵的右下角，总体为O(logm*logn)  
空间复杂度为O(1)

## 解法一
### 代码
```java
public class LeetCode_240_1_搜索二维矩阵II {
    public boolean searchMatrix(int[][] matrix, int target) {
        int m = matrix.length, n = matrix[0].length;
        int l1 = 0, r1 = n - 1;
        while (l1 < r1) {
            int mid = (l1 + r1 + 1) >> 1;
            if (matrix[0][mid] <= target) {
                l1 = mid;
            } else {
                r1 = mid - 1;
            }
        }
        int l2 = 0, r2 = m - 1;
        while (l2 < r2) {
            int mid = (l2 + r2 + 1) >> 1;
            if (matrix[mid][0] <= target) {
                l2 = mid;
            } else {
                r2 = mid - 1;
            }
        }
        for (int i = 0; i < l1; i++) {
            for (int j = 0; j < l2; j++) {
                if (matrix[i][j] == target) {
                    return true;
                }
            }
        }
        return false;
    }
}
```

# [LeetCode_496_1_下一个更大元素I](https://leetcode-cn.com/problems/next-greater-element-i/)
## 题目
给你两个 没有重复元素 的数组 nums1 和 nums2 ，其中nums1 是 nums2 的子集。

请你找出 nums1 中每个元素在 nums2 中的下一个比其大的值。

nums1 中数字 x 的下一个更大元素是指 x 在 nums2 中对应位置的右边的第一个比 x 大的元素。如果不存在，对应位置输出 -1 。

 
示例 1:
```
输入: nums1 = [4,1,2], nums2 = [1,3,4,2].
输出: [-1,3,-1]
解释:
    对于 num1 中的数字 4 ，你无法在第二个数组中找到下一个更大的数字，因此输出 -1 。
    对于 num1 中的数字 1 ，第二个数组中数字1右边的下一个较大数字是 3 。
    对于 num1 中的数字 2 ，第二个数组中没有下一个更大的数字，因此输出 -1 。
```

示例 2:
```
输入: nums1 = [2,4], nums2 = [1,2,3,4].
输出: [3,-1]
解释:
    对于 num1 中的数字 2 ，第二个数组中的下一个较大数字是 3 。
    对于 num1 中的数字 4 ，第二个数组中没有下一个更大的数字，因此输出 -1 。
```

提示：

* 1 <= nums1.length <= nums2.length <= 1000
* 0 <= nums1[i], nums2[i] <= 10^4
* nums1和nums2中所有整数 互不相同
* nums1 中的所有整数同样出现在 nums2 中
 
进阶：你可以设计一个时间复杂度为 O(nums1.length + nums2.length) 的解决方案吗？

## 理解
利用单调栈求解  
从后往前依次遍历nums2，将元素从小到大压入栈，维护单调栈的过程中利用map记录右边比当前值大的第一个数，
最后再重组出结果数组返回。  

时间复杂度为O(m + n)，m为num1的长度，n为num2的长度，空间复杂度为O(m)

### 代码
```java
public class LeetCode_496_1_下一个更大元素I {
    public int[] nextGreaterElement(int[] nums1, int[] nums2) {
        // 用来存储nums的值，及它右边的第一个比它大的元素
        Map<Integer, Integer> map = new HashMap<>();
        Deque<Integer> deque = new ArrayDeque<>();

        for (int i = nums2.length - 1; i >= 0 ; i--) {
            // 将栈顶比它小的值全pop掉
            while (!deque.isEmpty() && deque.peek() < nums2[i]) {
                deque.pop();
            }
            map.put(nums2[i], deque.isEmpty() ? -1 : deque.peek());
            deque.push(nums2[i]);
        }
        int[] ans = new int[nums1.length];
        for (int i = 0; i < nums1.length ; i++) {
            ans[i] = map.get(nums1[i]);
        }
        return ans;
    }
}
```

# [LeetCode_301_1_删除无效的括号](https://leetcode-cn.com/problems/remove-invalid-parentheses/)
## 题目
给你一个由若干括号和字母组成的字符串 s ，删除最小数量的无效括号，使得输入的字符串有效。

返回所有可能的结果。答案可以按 任意顺序 返回。

示例 1：
```
输入：s = "()())()"
输出：["(())()","()()()"]
```

示例 2：
```
输入：s = "(a)())()"
输出：["(a())()","(a)()()"]
```

示例 3：
```
输入：s = ")("
输出：[""]
```

提示：

* 1 <= s.length <= 25
* s 由小写英文字母以及括号 '(' 和 ')' 组成
* s 中至多含 20 个括号

## 理解
解法一：搜索 + 剪枝  

通过数学思路处理左右括号，左括号为1，右括号为-1，合法的字符串累加得分为0，如果出现得分为负，直接
剔除剪枝 
 
预处理出所有可能匹配的最大的对数max，即左括号数与右括号数中的较小值

遍历完成后，需要维护最大有效长度len，如果出现新的有效字符串长度大于原有的len，需要舍弃之前的结果（清空set）

判断当前下标字符的情况：  
1. 当前字符为'('，可以选择，也可以不选（即删除），选择需要累积得分+1
2. 当前字符为')'，可以选择，也可以不选，选择需要累积得分-1
3. 其他字符，直接拼接即可

遍历完成后使用set去重，将结果返回

时间复杂度：预处理max的复杂度为O(n)，不考虑剪枝效果，每个位置都有两种选择，最坏的情况下搜索的复杂
度为O(2^n)，每次搜索中都得拼接处长度为n的新字符串，所以整体的时间复杂度为O(n*2^n)

空间复杂度：最大合法方案数与字符串长度呈线性关系，所以为O(n)

解法二：在解法一的基础上，可以预处理出len的值（即确定要删除的左括号数量与右括号数量），多一层剪枝  

时间复杂度在最坏的情况下与上面一致，空间复杂度也为O(n)

## 解法一
### 代码
```java
public class LeetCode_301_1_删除无效的括号 {
    /**
     * 存储答案，set用来去重
     */
    Set<String> set = new HashSet<>();
    /**
     * n代表字符串长度，max代表最大的括号对数，len代表合法的字符的长度
     */
    int n, max, len;
    /**
     * 目标字符串
     */
    String str;
    public List<String> removeInvalidParentheses(String s) {
        str = s;
        n = s.length();
        int l = 0, r = 0;
        for (int i = 0; i < n; i++) {
            char c = s.charAt(i);
            if (c == '(') {
                l++;
            } else if (c == ')') {
                r++;
            }
        }
        max = Math.min(l, r);
        dfs(0, "", 0);
        return new ArrayList<>(set);
    }

    /**
     *
     * @param u 当前遍历的坐标
     * @param cur 当前字符串
     * @param score 分数
     */
    private void dfs(int u, String cur, int score) {
        if (score < 0 || score > max) {
            return;
        }
        if (u == n) {
            if (score == 0 && cur.length() >= len) {
                if (cur.length() > len) {
                    set.clear();
                }
                len = cur.length();
                set.add(cur);
            }
            return;
        }
        char c = str.charAt(u);
        if (c == '(') {
            dfs(u + 1, cur + String.valueOf(c), score + 1);
            dfs(u + 1, cur, score);
        } else if (c == ')') {
            dfs(u + 1, cur + String.valueOf(c), score - 1);
            dfs(u + 1, cur, score);
        } else {
            dfs(u + 1, cur + String.valueOf(c), score);
        }
    }
}

```

## 解法二
### 代码
```java
public class LeetCode_301_2_删除无效的括号 {
    Set<String> set = new HashSet<>();
    int n, len, max;
    String str;
    public List<String> removeInvalidParentheses(String s) {
        n = s.length();
        str = s;
        // 定义需要删除的左括号数量及右括号数量
        int l = 0, r = 0;
        for (int i = 0; i < n; i++) {
            char c = s.charAt(i);
            if (c == '(') {
                l++;
            } else if (c == ')') {
                if (l > 0) {
                    // 代表括号匹配上了
                    l--;
                } else {
                    // 代表有多余的右括号
                    r++;
                }
            }
        }
        len = n - l - r;

        int l1 = 0, r1 = 0;
        for (int i = 0; i < n; i++) {
            char c = s.charAt(i);
            if (c == '(') {
                l1++;
            } else if (c == ')') {
                r1++;
            }
        }
        max = Math.min(l1, r1);
        dfs(0, "", l, r, 0);
        return new ArrayList<>(set);
    }

    /**
     *
     * @param u 遍历的下标
     * @param s 当前的字符串
     * @param l 剩余需要删除的左括号数量
     * @param r 剩余需要删除的右括号数量
     * @param score 分数
     */
    private void dfs(int u, String s, int l, int r, int score) {
        if (l < 0 || r < 0 || score < 0 || score > max) {
            return;
        }
        if (l == 0 && r == 0) {
            if (s.length() == len) {
                set.add(s);
            }
        }
        if (u == n) {
            return;
        }
        char c = str.charAt(u);
        if (c == '(') {
            dfs(u + 1, s + c, l, r, score + 1);
            dfs(u + 1, s, l - 1, r, score);
        } else if (c == ')') {
            dfs(u + 1, s + c, l, r, score - 1);
            dfs(u + 1, s, l, r - 1, score);
        } else {
            dfs(u + 1, s + c, l, r, score);
        }
    }
}
```

# [LeetCode_869_1_重新排序得到2的幂](https://leetcode-cn.com/problems/reordered-power-of-2/)
## 题目
给定正整数 N ，我们按任何顺序（包括原始顺序）将数字重新排序，注意其前导数字不能为零。

如果我们可以通过上述方式得到 2 的幂，返回 true；否则，返回 false。

 
示例 1：
```
输入：1
输出：true
```

示例 2：
```
输入：10
输出：false
```

示例 3：
```
输入：16
输出：true
```

示例 4：
```
输入：24
输出：false
```

示例 5：
```
输入：46
输出：true
```

提示：

* 1 <= N <= 10^9

## 理解
解法一：使用dfs+lowBit  
通过bfs实现数字重排，注意需要特殊处理前导0的情况，再利用lowBit判断重排出来的数字如果为2的幂直接返回。

时间复杂度为O(log以10为底的n + 1)，即O(logn)  
空间复杂度为O(1)

解法二：使用打表+词频统计  
通过预处理出范围内的所有2的幂，统计目标值n的所有数字的频率，遍历预处理的2的幂的值，统计数字频率与
n的数字频率比对，完全一样则返回true，遍历完返回false

时间复杂度为O(C*logn)，C为符合范围内2的幂的个数，为常数  
空间复杂度为O(C)

## 解法一
### 代码
```java
public class LeetCode_869_1_重新排序得到2的幂 {
    int m;
    int[] cnts = new int[10];
    public boolean reorderedPowerOf2(int n) {
        // 统计n中各个数字的个数以及n的长度
        while (n != 0) {
            cnts[n % 10]++;
            n /= 10;
            m++;
        }
        return dfs(0, 0);
    }

    /**
     *
     * @param u 当前数字长度
     * @param cur 当前的值
     * @return
     */
    private boolean dfs(int u, int cur) {
        if (u == m) {
            return (cur & -cur) == cur;
        }
        for (int i = 0; i < 10; i++) {
            if (cnts[i] != 0) {
                cnts[i]--;
                if (!(i == 0 && cur == 0) && dfs(u + 1, cur * 10 + i)) {
                    return true;
                }
                cnts[i]++;
            }
        }
        return false;
    }
}

```

## 解法二
### 代码
```java
public class LeetCode_869_2_重新排序得到2的幂 {
    static Set<Integer> set = new HashSet<>();

    static {
        // 预处理出所有范围内2的幂的值
        for (int i = 1; i <= (int) 1e9; i *= 2) {
            set.add(i);
        }
    }

    public boolean reorderedPowerOf2(int n) {
        int[] cnts = new int[10];
        // 统计n中各个数字的个数以及n的长度
        while (n != 0) {
            cnts[n % 10]++;
            n /= 10;
        }
        int[] cnt = new int[10];
        // 检查词频是否相同
        out:
        for (int i : set) {
            Arrays.fill(cnt, 0);
            while (i != 0) {
                cnt[i % 10]++;
                i /= 10;
            }
            for (int j = 0; j < 10; j++) {
                if (cnt[j] != cnts[j]) {
                    continue out;
                }
            }
            return true;
        }
        return false;
    }
}
```

# [LeetCode_335_1_路径交叉](https://leetcode-cn.com/problems/self-crossing/)
## 理解
分情况讨论分析可能相交的情况：  
1. d[i]与d[i - 3]相交
2. d[i]与d[i - 4]相交
3. d[i]与d[i - 5]相交

时间复杂度为O(n)，空间复杂度为O(1)

### 代码
```java
public class LeetCode_335_1_路径交叉 {
    public boolean isSelfCrossing(int[] distance) {
        int n = distance.length;
        if (n < 4) {
            return false;
        }
        for (int i = 3; i < n; i++) {
            // d[i]与d[i - 3]相交
            if (distance[i] >= distance[i - 2] && distance[i - 1] <= distance[i - 3]) {
                return true;
            }
            // d[i]与d[i - 4]相交
            if (i >= 4 && distance[i - 1] == distance[i - 3] && distance[i] + distance[i - 4] >= distance[i - 2]) {
                return true;
            }
            // d[i]与d[i - 5]相交
            if (i >= 5 && distance[i] + distance[i - 4] >= distance[i - 2] &&
                    distance[i - 1] + distance[i - 5] >= distance[i - 3] &&
                    distance[i - 1] < distance[i - 3] && distance[i - 2] > distance[i - 4]) {
                return true;
            }
        }
        return false;
    }
}
```

# [LeetCode_260_1_只出现一次的数字III](https://leetcode-cn.com/problems/single-number-iii/)
## 理解
利用异或算法求取两个目标值的异或值sum，通过异或值的结果确认一个位置，两个目标值在该位置的值不同，
再通过判断该位置求取异或，分离出两个目标值返回。

时间复杂度为O(n)，空间复杂度为O(1)

### 代码
```java
public class LeetCode_260_1_只出现一次的数字III {
    public int[] singleNumber(int[] nums) {
        int n = nums.length;
        int sum = 0;
        for (int num : nums) {
            sum ^= num;
        }
        int index = Integer.MAX_VALUE;
        for (int i = 31; i >= 0 ; i--) {
            if (((sum >> i) & 1) == 1) {
                index = i;
                break;
            }
        }
        int[] ans = new int[2];
        for (int num : nums) {
            if (((num >> index) & 1) == 1) {
                ans[0] ^= num;
            } else {
                ans[1] ^= num;
            }
        }
        return ans;
    }
}

```

# [LeetCode_500_1_键盘行](https://leetcode-cn.com/problems/keyboard-row/)
## 理解
解法一：使用三个集合分别存储三行字符，将单词转化为小写字母，分别判断单词首字母属于哪一行，再判断
剩余字符是否都是该行的字符，都符合则添加到结果集中。

时间复杂度为所有的单词长度之和，空间复杂度为O(C)，C为常数26

解法二：使用int数组，提前将三行字符对应值的行数落入数组中，接着遍历单词，转化为小写，查看当前单词
的所有字符从int数组中获取的行数是否相等，如果符合添加到结果集。

复杂度同上

## 解法一
### 代码
```java
public class LeetCode_500_1_键盘行 {
    static List<Character> line1 = new ArrayList<>();
    static List<Character> line2 = new ArrayList<>();
    static List<Character> line3 = new ArrayList<>();
    static {
        line1.add('q');
        line1.add('w');
        line1.add('e');
        line1.add('r');
        line1.add('t');
        line1.add('y');
        line1.add('u');
        line1.add('i');
        line1.add('o');
        line1.add('p');
        line2.add('a');
        line2.add('s');
        line2.add('d');
        line2.add('f');
        line2.add('g');
        line2.add('h');
        line2.add('j');
        line2.add('k');
        line2.add('l');
        line3.add('z');
        line3.add('x');
        line3.add('c');
        line3.add('v');
        line3.add('b');
        line3.add('n');
        line3.add('m');
    }

    public String[] findWords(String[] words) {
        List<String> ans = new ArrayList<>();
        for (String word : words) {
            String wordNew = word.toLowerCase();
            boolean flag = false;
            switch (word.charAt(0)) {
                case 'q':
                case 'w':
                case 'e':
                case 'r':
                case 't':
                case 'y':
                case 'u':
                case 'i':
                case 'o':
                case 'p':
                    for ( char c : wordNew.toCharArray()) {
                        if (line1.contains(c)) {
                            flag = true;
                        } else {
                            flag = false;
                            break;
                        }
                    }
                    if (flag) {
                        ans.add(word);
                    }
                    break;
                case 'a':
                case 's':
                case 'd':
                case 'f':
                case 'g':
                case 'h':
                case 'j':
                case 'k':
                case 'l':
                    for ( char c : wordNew.toCharArray()) {
                        if (line2.contains(c)) {
                            flag = true;
                        } else {
                            flag = false;
                            break;
                        }
                    }
                    if (flag) {
                        ans.add(word);
                    }
                    break;
                case 'z':
                case 'x':
                case 'c':
                case 'v':
                case 'b':
                case 'n':
                case 'm':
                    for ( char c : wordNew.toCharArray()) {
                        if (line3.contains(c)) {
                            flag = true;
                        } else {
                            flag = false;
                            break;
                        }
                    }
                    if (flag) {
                        ans.add(word);
                    }
                    break;
                default:
            }
        }
        return ans.toArray(new String[0]);
    }
}
```

## 解法二
### 代码
```java
public class LeetCode_500_2_键盘行 {
    static String[] str = new String[]{"qwertyuiop", "asdfghjkl", "zxcvbnm"};
    static int[] hash = new int[26];
    static {
        for (int i = 0; i < str.length; i++) {
            char[] chars = str[i].toCharArray();
            for (char c : chars) {
                hash[c - 'a'] = i;
            }
        }
    }
    public String[] findWords(String[] words) {
        List<String> ans = new ArrayList<>();
        out:
        for (String word : words) {
            char[] chars = word.toCharArray();
            int t = -1;
            for (char c : chars) {
                c = Character.toLowerCase(c);
                if (t == -1) {
                    t = hash[c - 'a'];
                } else {
                    if (t != hash[c - 'a']) {
                        continue out;
                    }
                }
            }
            ans.add(word);
        }
        return ans.toArray(new String[0]);
    }

}
```