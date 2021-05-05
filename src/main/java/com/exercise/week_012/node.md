[toc]
# [LeetCode_179_1_最大数](https://leetcode-cn.com/problems/largest-number/)
## 题目
给定一组非负整数 nums，重新排列每个数的顺序（每个数不可拆分）使之组成一个最大的整数。

注意：输出结果可能非常大，所以你需要返回一个字符串而不是整数。

示例 1：
```
输入：nums = [10,2]
输出："210"
```

示例 2：
```
输入：nums = [3,30,34,5,9]
输出："9534330"
```

示例 3：
```
输入：nums = [1]
输出："1"
```

示例 4：
```
输入：nums = [10]
输出："10"
```

提示：

* 1 <= nums.length <= 100
* 0 <= nums[i] <= 109

## 理解
解法一：利用字符串比较，将目标数组转为字符串，在使用数组排序，利用λ表达式传入比较
规则（分别前后拼接做比较），最终把排序好的字符串数组拼接起来返回（特殊处理全为0的数组）。
解法二：也是重写数组排序的比较规则，这次比较的是数字类型，通过计算参数位数，来确认 
拼接后的值，并做比较，并把排序好的数组拼接为字符串返回（特殊处理全为0的数组）。

## 解法一
### 代码
```java
public class LeetCode_179_1_最大数 {
    public String largestNumber(int[] nums) {
        int length = nums.length;
        String[] strArr = new String[length];
        for (int i = 0; i < length; i++) {
            strArr[i] = nums[i] + "";
        }
        Arrays.sort(strArr, (a, b) -> (b + a).compareTo(a + b));
        StringBuilder builder = new StringBuilder();
        for(String str : strArr) {
            builder.append(str);
        }
        String ans = builder.toString();
        return ans.charAt(0) == '0' ? "0" : ans;
    }
}
```

## 解法二
### 代码
```java
public class LeetCode_179_2_最大数 {
    public String largestNumber(int[] nums) {
        int length = nums.length;
        Integer[] arr = new Integer[length];
        for (int i = 0; i < length; i++) {
            arr[i] = nums[i];
        }
        Arrays.sort(arr, new Comparator<Integer>() {
            @Override
            public int compare(Integer o1, Integer o2) {
                int sx = 10;
                int sy = 10;
                while (sx <= o1) {
                    sx *= 10;
                }
                while (sy <= o2) {
                    sy *= 10;
                }
                return (sx * o2 + o1) - (sy * o1 + o2);
            }
        });
        if (arr[0] == 0) {
            return "0";
        }
        StringBuilder builder = new StringBuilder();
        for(Integer str : arr) {
            builder.append(str);
        }
        return builder.toString();
    }
}
```

# [LeetCode_183_1_二叉搜索树节点最小距离](https://leetcode-cn.com/problems/minimum-distance-between-bst-nodes/)
## 题目
给你一个二叉搜索树的根节点 root ，返回 树中任意两不同节点值之间的最小差值 。

注意：本题与 530：https://leetcode-cn.com/problems/minimum-absolute-difference-in-bst/ 相同

示例 1：
```
输入：root = [4,2,6,1,3]
输出：1
```

示例 2：
```
输入：root = [1,0,48,null,null,12,49]
输出：1
```

提示：

* 树中节点数目在范围 [2, 100] 内
* 0 <= Node.val <= 105

## 理解
利用DFS(深度优先搜索)，中序遍历，二叉树的中序遍历的特点是数据从小到大排列，最小
距离肯定出现在相邻两个节点的差值，所以在中序遍历过程中求取前一个值与当前值的差值并
与ans取小值，直到遍历结束。

### 代码
```java
public class LeetCode_183_1_二叉搜索树节点最小距离 {
    int ans = Integer.MAX_VALUE;
    TreeNode pre = null;
    public int minDiffInBST(TreeNode root) {
        inOrder(root);
        return ans;
    }

    private void inOrder(TreeNode node) {
        if (node == null) {
            return;
        }
        inOrder(node.left);
        if(pre!=null){
            ans = Math.min(ans, node.val - pre.val);
        }
        pre = node;
        inOrder(node.right);
    }
}
```
# [LeetCode_208_1_实现Trie前缀树](https://leetcode-cn.com/problems/implement-trie-prefix-tree/)
## 题目
Trie（发音类似 "try"）或者说 前缀树 是一种树形数据结构，用于高效地存储和检索字符串数据集中的键。这一数据结构有相当多的应用情景，例如自动补完和拼写检查。

请你实现 Trie 类：

Trie() 初始化前缀树对象。  
void insert(String word) 向前缀树中插入字符串 word 。  
boolean search(String word) 如果字符串 word 在前缀树中，返回 true（即，在检索之前已经插入）；否则，返回 false 。  
boolean startsWith(String prefix) 如果之前已经插入的字符串 word 的前缀之一为 prefix ，返回 true ；否则，返回 false 。  
 

示例：
```
输入
["Trie", "insert", "search", "search", "startsWith", "insert", "search"]
[[], ["apple"], ["apple"], ["app"], ["app"], ["app"], ["app"]]
输出
[null, null, true, false, true, null, true]

解释
Trie trie = new Trie();
trie.insert("apple");
trie.search("apple");   // 返回 True
trie.search("app");     // 返回 False
trie.startsWith("app"); // 返回 True
trie.insert("app");
trie.search("app");     // 返回 True
```

提示：

* 1 <= word.length, prefix.length <= 2000
* word 和 prefix 仅由小写英文字母组成
* insert、search 和 startsWith 调用次数 总计 不超过 3 * 104 次

## 理解
英文字母共有26个，所以我们可以初始化一个26长度的数组来代表26个字母，还需要一个
布尔类型变量来代表该节点是否是终止节点。从根节点到子节点的路径的index是多少，就
代表是哪个字符。查询与前缀的查询的区别在与查询需要判断最终节点是否为终止节点，插入
则是遍历字符串，判断每一个字符，如果存在，就移动到该字符，如果不存在就创建改该节点
并移动到该节点。

### 代码
```java
public class LeetCode_208_1_实现Trie前缀树 {

    class Trie {
        Trie[] children;
        boolean isEnd;

        /** Initialize your data structure here. */
        public Trie() {
            children = new Trie[26];
            isEnd = false;
        }

        /** Inserts a word into the trie. */
        public void insert(String word) {
            Trie node = this;
            for (int i = 0; i < word.length() ; i++) {
                char ch = word.charAt(i);
                int index = ch - 'a';
                if (node.children[index] == null) {
                    node.children[index] = new Trie();
                }
                node = node.children[index];
            }
            node.isEnd = true;
        }

        /** Returns if the word is in the trie. */
        public boolean search(String word) {
            Trie node = searchStr(word);
            return node != null && node.isEnd;
        }

        /** Returns if there is any word in the trie that starts with the given prefix. */
        public boolean startsWith(String prefix) {
            Trie node = searchStr(prefix);
            return node != null;
        }

        private Trie searchStr(String word) {
            Trie node = this;
            for (int i = 0; i < word.length(); i++) {
                char ch = word.charAt(i);
                int index = ch - 'a';
                if (node.children[index] != null) {
                    node = node.children[index];
                } else {
                    return null;
                }
            }
            return node;
        }
    }

}
```
# [LeetCode_213_1_打家劫舍II](https://leetcode-cn.com/problems/house-robber-ii/)
## 题目
你是一个专业的小偷，计划偷窃沿街的房屋，每间房内都藏有一定的现金。这个地方所有的房屋都 围成一圈 ，这意味着第一个房屋和最后一个房屋是紧挨着的。同时，相邻的房屋装有相互连通的防盗系统，如果两间相邻的房屋在同一晚上被小偷闯入，系统会自动报警 。

给定一个代表每个房屋存放金额的非负整数数组，计算你 在不触动警报装置的情况下 ，能够偷窃到的最高金额。

示例 1：
```
输入：nums = [2,3,2]
输出：3
解释：你不能先偷窃 1 号房屋（金额 = 2），然后偷窃 3 号房屋（金额 = 2）, 因为他们是相邻的。
```

示例 2：
```
输入：nums = [1,2,3,1]
输出：4
解释：你可以先偷窃 1 号房屋（金额 = 1），然后偷窃 3 号房屋（金额 = 3）。
     偷窃到的最高金额 = 1 + 3 = 4 。
```

示例 3：
```
输入：nums = [0]
输出：0
```


提示：

* 1 <= nums.length <= 100
* 0 <= nums[i] <= 1000

## 理解
此题与198.打家劫舍的区别在于，店铺首尾是相连的，即抢劫了第一家就不能抢劫最后一家，
抢劫了最后一家，就不能抢劫第一家。  
分析出这个规律后，整体思路还是动态分布求取抢劫最大值，转化方程也与下题一致。  
    dp[i] = Math.max(dp[i-2] + nums[i], dp[i-1])  
问题可按上面的分析分为两部分，第一种，假设抢劫第一家，计算出[0, length - 2]家
的最大抢劫金额n1；第二种，假设抢劫最后一家，计算出[1, length - 1]家的最大抢劫金额
n2，最后取n1和n2的最大值为i家店抢劫的最大值。  

### 代码
```java
public class LeetCode_213_1_打家劫舍II {
    public int rob(int[] nums) {
        int length = nums.length;
        if (length == 1) {
          return nums[0];
        }
        if (length == 2) {
            return Math.max(nums[0], nums[1]);
        }

        return Math.max(maxRob(nums, 0 , length - 2), maxRob(nums, 1, length - 1));
    }

    private int maxRob(int[] nums, int start, int end) {
        // 保存i-2位置的抢劫最大值
        int first = nums[start];
        // 保存i-1位置的抢劫最大值
        int secend = Math.max(first, nums[start + 1]);
        for (int i = start + 2; i <= end; i++) {
            int tem = secend;
            secend = Math.max(first + nums[i], secend);
            first = tem;
        }
        return secend;
    }
}
```

# [LeetCode_198_1_打家劫舍](https://leetcode-cn.com/problems/house-robber/)
## 题目
你是一个专业的小偷，计划偷窃沿街的房屋。每间房内都藏有一定的现金，影响你偷窃的唯一制约因素就是相邻的房屋装有相互连通的防盗系统，如果两间相邻的房屋在同一晚上被小偷闯入，系统会自动报警。

给定一个代表每个房屋存放金额的非负整数数组，计算你 不触动警报装置的情况下 ，一夜之内能够偷窃到的最高金额。

示例 1：
```
输入：[1,2,3,1]
输出：4
解释：偷窃 1 号房屋 (金额 = 1) ，然后偷窃 3 号房屋 (金额 = 3)。
     偷窃到的最高金额 = 1 + 3 = 4 。
```

示例 2：
```
输入：[2,7,9,3,1]
输出：12
解释：偷窃 1 号房屋 (金额 = 2), 偷窃 3 号房屋 (金额 = 9)，接着偷窃 5 号房屋 (金额 = 1)。
     偷窃到的最高金额 = 2 + 9 + 1 = 12 。
```

提示：

* 0 <= nums.length <= 100
* 0 <= nums[i] <= 400

## 理解
解法一：使用动态规划，假如已经盗劫到第i个房子，那么有两种可能；  
       第一种，盗窃第i个房子，那么总的盗窃金额是前i-2个房子的总的盗窃金额+当前房子的金额
       第二种，不盗窃第i个房子，那么总的盗窃金额是前i-1个房子的总的盗窃金额。
       第i个房子的盗窃金额最大值，应该取这两种中的大的那个。
       则得到转化方程为：dp[i] = Math.max(dp[i-2] + nums[i], dp[i-1])
       最终返回dp[length - 1]即可。
解法二：同样使用动态规划，因为我们发现转化方程只与dp[i-2]和dp[i-1]有关，所以
       我们优化空间复杂度，使用两个变量来表示上述的两个值，其余不变。
       
## 解法一
### 代码
```java
public class LeetCode_198_1_打家劫舍 {
    public int rob(int[] nums) {
        int length = nums.length;
        if (length == 0) {
            return 0;
        }
        if (length == 1) {
            return nums[0];
        }
        if (length == 2) {
            return Math.max(nums[0], nums[1]);
        }
        int[] rob = new int[length];
        rob[0] = nums[0];
        rob[1] = Math.max(nums[0], nums[1]);
        for (int i = 2; i < length; i++) {
            rob[i] = Math.max(rob[i - 2] + nums[i], rob[i - 1]);
        }
        return rob[length - 1];
    }
}
```
## 解法二
### 代码
```java
public class LeetCode_198_2_打家劫舍 {
    public int rob(int[] nums) {
        int length = nums.length;
        if (length == 0) {
            return 0;
        }
        if (length == 1) {
            return nums[0];
        }
        if (length == 2) {
            return Math.max(nums[0], nums[1]);
        }
        // 用变量代表i-2位置的rob的值
        int first = nums[0];
        // 代表i-1位置的rob的值
        int second = Math.max(nums[0], nums[1]);
        for (int i = 2; i < length; i++) {
            int tem = second;
            second = Math.max(first + nums[i], second);
            first = tem;
        }
        return second;
    }
}
```

# [LeetCode_87_1_打扰字符串](https://leetcode-cn.com/problems/scramble-string/)
## 题目
使用下面描述的算法可以扰乱字符串 s 得到字符串 t ：  
如果字符串的长度为 1 ，算法停止  
如果字符串的长度 > 1 ，执行下述步骤：  
在一个随机下标处将字符串分割成两个非空的子字符串。即，如果已知字符串 s ，则可以将其分成两个子字符串 x 和 y ，且满足 s = x + y 。  
随机 决定是要「交换两个子字符串」还是要「保持这两个子字符串的顺序不变」。即，在执行这一步骤之后，s 可能是 s = x + y 或者 s = y + x 。  
在 x 和 y 这两个子字符串上继续从步骤 1 开始递归执行此算法。  
给你两个 长度相等 的字符串 s1 和 s2，判断 s2 是否是 s1 的扰乱字符串。如果是，返回 true ；否则，返回 false 。

 

示例 1：
```
输入：s1 = "great", s2 = "rgeat"
输出：true
解释：s1 上可能发生的一种情形是：
"great" --> "gr/eat" // 在一个随机下标处分割得到两个子字符串
"gr/eat" --> "gr/eat" // 随机决定：「保持这两个子字符串的顺序不变」
"gr/eat" --> "g/r / e/at" // 在子字符串上递归执行此算法。两个子字符串分别在随机下标处进行一轮分割
"g/r / e/at" --> "r/g / e/at" // 随机决定：第一组「交换两个子字符串」，第二组「保持这两个子字符串的顺序不变」
"r/g / e/at" --> "r/g / e/ a/t" // 继续递归执行此算法，将 "at" 分割得到 "a/t"
"r/g / e/ a/t" --> "r/g / e/ a/t" // 随机决定：「保持这两个子字符串的顺序不变」
算法终止，结果字符串和 s2 相同，都是 "rgeat"
这是一种能够扰乱 s1 得到 s2 的情形，可以认为 s2 是 s1 的扰乱字符串，返回 true
```

示例 2：
```
输入：s1 = "abcde", s2 = "caebd"
输出：false
```

示例 3：
```
输入：s1 = "a", s2 = "a"
输出：true
```

提示：

* s1.length == s2.length
* 1 <= s1.length <= 30
* s1 和 s2 由小写英文字母组成
* 通过次数36,148提交次数73,096

## 理解
解法一：使用朴素解法，遍历分割点，从1到length;  
字符串s1，从i分割成为两段[0,i),[i,length)，同样s2也可以分为两段与之匹配，
[0,i),[i,length)，但s2还有一种分割法，即前后调转，[0,length - i),[length - i,length)
这两种分割法，只要有一种能和s1的两段互为打扰字符串，即匹配成功。（解法一会超时）  

解法二：使用动态规划，主要理解布尔类型三元数组代表的含义，f[i][j][len]代表
字符串1从下标i开始，字符串2从下标j开始，两者之后长度为len的子字符串是否为打扰
字符串。转化方程与上面解法的比较情况一致，举个例子，f[0][0][2]的值的确认为：  
a = f[0][0][1] && f[1][1][1];  
b = f[0][1][1] && f[1][0][1];  
f[0][0][2] = a || b;

## 解法一
### 代码
```java
public class LeetCode_87_1_打扰字符串 {
    public boolean isScramble(String s1, String s2) {
        if (s1.equals(s2)) {
            return true;
        }
        if (!check(s1, s2)) {
            return false;
        }
        int length = s1.length();
        for (int i = 1; i < length; i++) {
            String s1Sub1 = s1.substring(0, i), s1Sub2 = s1.substring(i);
            String s2Sub1 = s2.substring(0, i), s2Sub2 = s2.substring(i);
            if (isScramble(s1Sub1, s2Sub1) && isScramble(s1Sub2, s2Sub2)) {
                return true;
            };
            String s2Sub3 = s2.substring(0, length - i), s2Sub4 = s2.substring(length - i);
            if (isScramble(s1Sub1, s2Sub3) && isScramble(s1Sub2, s2Sub4)) {
                return true;
            }
        }
        return false;
    }

    private boolean check(String s1, String s2) {
        if (s1.length() != s2.length()) {
            return false;
        }
        int[] a = new int[26], b = new int[26];
        char[] s1Arr = s1.toCharArray();
        char[] s2Arr = s2.toCharArray();
        for (int i = 0; i < s1Arr.length; i++) {
            a[s1Arr[i] - 'a']++;
            b[s2Arr[i] - 'a']++;
        }
        for (int i = 0; i < 26; i++) {
            if (a[i] != b[i]) {
                return false;
            }
        }
        return true;
    }
}
```

## 解法二
### 代码
```java
public class LeetCode_87_2_打扰字符串 {
    public boolean isScramble(String s1, String s2) {
        if (s1.equals(s2)) {
            return true;
        }

        int n = s1.length();
        boolean[][][] f = new boolean[n][n][n + 1];
        char[] ch1 = s1.toCharArray();
        char[] ch2 = s2.toCharArray();
        // 处理长度为1的所有情况
        for (int i = 0; i < n; i++) {
            for (int j = 0; j < n; j++) {
                f[i][j][1] = ch1[i] == ch2[j];
            }
        }

        // 处理其余长度
        for (int len = 2; len <= n; len++) {
            for (int i = 0; i <= n - len; i++) {
                for (int j = 0; j <= n - len; j++) {
                    for (int k = 1; k < len; k++) {
                        boolean a = f[i][j][k] && f[i + k][j + k][len - k];
                        boolean b = f[i][j + len - k][k] && f[i + k][j][len - k];
                        if (a || b) {
                            f[i][j][len] = true;
                        }
                    }
                }
            }
        }
        return f[0][0][n];
    }
}
```

# [LeetCode_220_1_存在重复元素III](https://leetcode-cn.com/problems/contains-duplicate-iii/)
## 题目
给你一个整数数组 nums 和两个整数 k 和 t 。请你判断是否存在 两个不同下标 i 和 j，使得 abs(nums[i] - nums[j]) <= t ，同时又满足 abs(i - j) <= k 。

如果存在则返回 true，不存在返回 false。

示例 1：
```
输入：nums = [1,2,3,1], k = 3, t = 0
输出：true
```

示例 2：
```
输入：nums = [1,0,1,1], k = 1, t = 2
输出：true
```

示例 3：
```
输入：nums = [1,5,9,1,5,9], k = 2, t = 3
输出：false
```

提示：

* 0 <= nums.length <= 2 * 104
* -231 <= nums[i] <= 231 - 1
* 0 <= k <= 104
* 0 <= t <= 231 - 1

## 理解
解法一：滑动窗口，窗口长度保持在k个长度，这样保证abs[i - j] <= k;  
接着我们在窗口中找大于等于目标值的最小数m，和小于等于目标值的最大数n;  
判断 m - value <= t, value - n <= t 是否成立，如果成立返回true，否则继续轮询
数组。每轮询一个数，都要添加到窗口，并把超出窗口限制的值，从窗口中删除。  
我们借助java中的TreeSet帮助我们实现。  
解法二：桶排序，上述方法需要在窗口k的长度中寻找与目标值最相近的大于等于与小于等于的
值，这个操作并非O(1)级别的，虽然我们借助红黑树，但查询也是O(logk)级别的操作，
我们如果借助桶，把数按规定放入桶中，只需要查询目标桶是否有值，即可确认是否有满足
abs(nums[i] - nums[j] <= t)的数据，桶的最大个数为k，保证abs(i - j) <= k。

## 解法一
### 代码
```java
public class LeetCode_220_1_存在重复元素III {
    public boolean containsNearbyAlmostDuplicate(int[] nums, int k, int t) {
        int length = nums.length;
        TreeSet<Long> set = new TreeSet<>();
        for (int i = 0; i < length; i++) {
            Long value = (long) nums[i];
            Long floor = set.floor(value);
            Long ceil = set.ceiling(value);
            if (floor != null && value - floor <= t) {
                return true;
            }
            if (ceil != null && ceil - value <= t) {
                return true;
            }
            set.add(value);
            if (i >= k) {
                set.remove((long)nums[i - k]);
            }
        }
        return false;
    }
}
```

## 解法二
### 代码
```java
public class LeetCode_220_2_存在重复元素III {
    int size;
    public boolean containsNearbyAlmostDuplicate(int[] nums, int k, int t) {
        int length = nums.length;
        Map<Long, Long> map = new HashMap<>(k);
        size = t + 1;
        for (int i = 0; i < length; i++) {
            long value = (long)nums[i];
            long idx = getIdx(value);
            if (map.containsKey(idx)) {
                return true;
            }
            long left = idx - 1;
            long right = idx + 1;
            // 在[0,2t]区间也可能存在差值小于等于t的两个数。
            if (map.containsKey(left) && value - map.get(left) <= t) {
                return true;
            }
            if (map.containsKey(right) && map.get(right) - value <= t) {
                return true;
            }
            map.put(idx, value);

            // 最多k个桶，保证下标符合i-j <= k
            // 当 i = k时，我们需要删除nums[0]对应的桶
            if (i >= k) {
                map.remove(getIdx(nums[i - k]));
            }
        }
        return false;
    }

    private long getIdx(long value) {
        /*
         比如 [0, 1, 2, 3], t = 3, 这个数组中的值都是符合差值小于等于t的，应该放在同一个桶里，所以我们除以 t+1，保证
         他们除出来的结果都为0，获取到桶的index都是同一个值即放在同一个桶里；
         当value小于0时，比如[-4, -3, -2, -1]，这四个值的差值也符合小于等于t，按我们设计他们应该放在index = -1的桶里，
         但是现在除以 t+1的结果有 -1 和 0 两个值，为了统一我们给value统一加1，这样计算的结果都为0，再将最后计算的结果-1，
         这样就将负数也合理的处理在目标桶里了。
         */

        return value >= 0 ? value / size : (value + 1) / size - 1;
    }
}
```