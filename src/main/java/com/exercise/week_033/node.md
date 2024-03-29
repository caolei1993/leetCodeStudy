[toc]

# [LeetCode_447_1_回旋镖的数量](https://leetcode-cn.com/problems/number-of-boomerangs/)
## 题目
给定平面上 n 对 互不相同 的点 points ，其中 points[i] = [xi, yi] 。回旋镖 是由点 (i, j, k) 表示的元组 ，其中 i 和 j 之间的距离和 i 和 k 之间的距离相等（需要考虑元组的顺序）。

返回平面上所有回旋镖的数量。

示例 1：
```
输入：points = [[0,0],[1,0],[2,0]]
输出：2
解释：两个回旋镖为 [[1,0],[0,0],[2,0]] 和 [[1,0],[2,0],[0,0]]
```

示例 2：
```
输入：points = [[1,1],[2,2],[3,3]]
输出：2
```

示例 3：
```
输入：points = [[1,1]]
输出：0
```
 

提示：

* n == points.length
* 1 <= n <= 500
* points[i].length == 2
* -10^4 <= xi, yi <= 10^4
* 所有点都 互不相同

## 理解
我们可以在给定i的情况下，统计与i距离相等的j,k的组合数有多少。  
我们可以借助哈希表预处理，在给定i的情况下，统计所有点与i的距离，存入哈希表中，key为距离，value为点的
个数，然后分别对所有距离进行累加处理。  
为了方便计算，避免使用sqrt，我们直接使用x^2 + y^2来指两个点之间的距离。  
时间复杂度为O(n^2)，空间复杂度为O(n)
### 代码
```java
public class LeetCode_447_1_回旋镖的数量 {
    public int numberOfBoomerangs(int[][] points) {
        int n = points.length;
        int ans = 0;
        for (int i = 0; i < n; i++) {
            Map<Integer, Integer> map = new HashMap<>();
            for (int j = 0; j < n; j++) {
                if (i == j) {
                    continue;
                }
                int x = points[i][0] - points[j][0], y = points[i][1] - points[j][1];
                int value = x * x + y * y;
                map.put(value, map.getOrDefault(value, 0) + 1);
            }
            for (Integer key : map.keySet()) {
                int cnt = map.get(key);
                ans += cnt * (cnt - 1);
            }
        }
        return ans;
    }
}
```

# [LeetCode_524_1_通过删除字母匹配到字典里最长单词](https://leetcode-cn.com/problems/longest-word-in-dictionary-through-deleting/)
## 题目
给你一个字符串 s 和一个字符串数组 dictionary 作为字典，找出并返回字典中最长的字符串，该字符串可以通过删除 s 中的某些字符得到。

如果答案不止一个，返回长度最长且字典序最小的字符串。如果答案不存在，则返回空字符串。

 
示例 1：
```
输入：s = "abpcplea", dictionary = ["ale","apple","monkey","plea"]
输出："apple"
```

示例 2：
```
输入：s = "abpcplea", dictionary = ["a","b","c"]
输出："a"
```
 

提示：

* 1 <= s.length <= 1000
* 1 <= dictionary.length <= 1000
* 1 <= dictionary[i].length <= 1000
* s 和 dictionary[i] 仅由小写英文字母组成

## 理解
排序+双指针+贪心  
因为题目要返回长度最长的子串，如果长度相等则返回字典序最小的子串，所以我们先对给定的字典集合进行排序，
排序优先按长度，长度按从大到小，如果长度相等，则按字典排序。  
接着我们遍历排好序的集合，利用双指针看当前字符串是否是s的子串，如果是直接返回，否则遍历下一个，全都
遍历完，没有找到，返回空字符串。

### 代码
```java
public class LeetCode_524_1_通过删除字母匹配到字典里最长单词 {
    public String findLongestWord(String s, List<String> dictionary) {
        dictionary.sort((a, b) -> {
            if (a.length() != b.length()) {
                return b.length() - a.length();
            }
            return a.compareTo(b);
        });

        int n = s.length();
        for (String ss : dictionary) {
            int m = ss.length();
            int i = 0, j = 0;
            while (i < n && j < m) {
                if (s.charAt(i) == ss.charAt(j)) {
                    j++;
                }
                i++;
            }
            if (j == m) {
                return ss;
            }
        }
        return "";
    }
}
```

# [LeetCode_162_1_寻找峰值(https://leetcode-cn.com/problems/find-peak-element/)
## 题目
峰值元素是指其值严格大于左右相邻值的元素。

给你一个整数数组 nums，找到峰值元素并返回其索引。数组可能包含多个峰值，在这种情况下，返回 任何一个峰值 所在位置即可。

你可以假设 nums[-1] = nums[n] = -∞ 。

你必须实现时间复杂度为 O(log n) 的算法来解决此问题。

示例 1：
```
输入：nums = [1,2,3,1]
输出：2
解释：3 是峰值元素，你的函数应该返回其索引 2。
```

示例 2：
```
输入：nums = [1,2,1,3,5,6,4]
输出：1 或 5 
解释：你的函数可以返回索引 1，其峰值元素为 2；
     或者返回索引 5， 其峰值元素为 6。
```

提示：

* 1 <= nums.length <= 1000
* -2^31 <= nums[i] <= 2^31 - 1
* 对于所有有效的 i 都有 nums[i] != nums[i + 1]

## 理解
根据题意让我们在O(logn)的时间复杂度解决，很容易想到二分法。  
假设当前分割点mid满足关系nums[mid]>nums[mid + 1]，那么nums[mid + 1]必然不是峰值，nums[mid]有
可能为峰值，那么我们就将r = mid，从左半部分继续找峰值。  
上述方法满足的条件是：
1. 对任意数组而言必然有峰值
2. 二分法不会错过峰值  

因为数组的最小长度为1，且超越的边界都可看做负无穷，所以数组一定有峰值，即使只有一个值，那么当前值就是
峰值。  
基于1我们可以推理：如果当前位置大于其左边界或者右边界，那么在当前位置的右边或者左边必然存在峰值。  
时间复杂度为O(logn)，空间复杂度为O(1)

## 解法一
### 代码
```java
public class LeetCode_162_1_寻找峰值 {
    public int findPeakElement(int[] nums) {
        int n = nums.length;
        int l = 0, r = n - 1;
        while (l < r) {
            int mid = l + r >> 1;
            if (nums[mid] > nums[mid + 1]) {
                r = mid;
            } else {
                l = mid + 1;
            }
        }
        return l;
    }
}
```

## 解法二
### 代码
```java
public class LeetCode_162_2_寻找峰值 {
    public int findPeakElement(int[] nums) {
        int n = nums.length;
        int l = 0, r = n - 1;
        while (l < r) {
            int mid = l + r + 1 >> 1;
            if (nums[mid] > nums[mid - 1]) {
                l = mid;
            } else {
                r = mid - 1;
            }
        }
        return l;
    }
}
```


# [LeetCode_212_1_单词搜索II](https://leetcode-cn.com/problems/word-search-ii/)
## 题目
给定一个 m x n 二维字符网格 board 和一个单词（字符串）列表 words，找出所有同时在二维网格和字典中出现的单词。

单词必须按照字母顺序，通过 相邻的单元格 内的字母构成，其中“相邻”单元格是那些水平相邻或垂直相邻的单元格。同一个单元格内的字母在一个单词中不允许被重复使用。


示例 1：
```
输入：board = [["o","a","a","n"],["e","t","a","e"],["i","h","k","r"],["i","f","l","v"]], words = ["oath","pea","eat","rain"]
输出：["eat","oath"]
```

示例 2：
```
输入：board = [["a","b"],["c","d"]], words = ["abcb"]
输出：[]
```


提示：

* m == board.length
* n == board[i].length
* 1 <= m, n <= 12
* board[i][j] 是一个小写英文字母
* 1 <= words.length <= 3 * 10^4
* 1 <= words[i].length <= 10
* words[i] 由小写英文字母组成
* words 中的所有字符串互不相同

## 理解
字典树 + dfs 
使用字典树Trie来解决此类问题，将列表words单词插入到字典树中，判断是否为终点的成员变量isEnd在这里
我们可以变通为存储字符串本身。遍历网格中的每一个点作为起点向四周延伸比对，注意因为题目要求一个单词
中同一个单元格不能重复使用，所以我们使用一个布尔类型的二维数组来记录我们某个位置的单元格是否已经使用，
单词遍历完成后，再将锁定状态释放。  
时间复杂度为O(m*n*4^10)

### 代码
```java
public class LeetCode_212_1_单词搜索II {
    static class TireNode {
        String s;
        TireNode[] tns = new TireNode[26];
    }

    TireNode root = new TireNode();
    Set<String> set = new HashSet<>();
    int[][] dirs = new int[][]{{1, 0}, {-1, 0}, {0, 1}, {0, -1}};
    char[][] boards;
    int m,n;
    /**
     * 标记某个点是否已经遍历过
     */
    boolean[][] vis = new boolean[15][15];

    void insert (String s) {
        TireNode p = root;
        char[] cc = s.toCharArray();
        for (char c : cc) {
            int v = c - 'a';
            if (p.tns[v] == null) {
                p.tns[v] = new TireNode();
            }
            p = p.tns[v];
        }
        p.s = s;
    }

    public List<String> findWords(char[][] board, String[] words) {
        boards = board;
        m = board.length;
        n = board[0].length;
        for (String s : words) {
            insert(s);
        }

        for (int i = 0; i < m; i++) {
            for (int j = 0; j < n; j++) {
                int u = board[i][j] - 'a';
                if (root.tns[u] != null) {
                    vis[i][j] = true;
                    dfs(i, j, root.tns[u]);
                    vis[i][j] = false;
                }
            }
        }
        return new ArrayList<>(set);
    }

    private void dfs(int i, int j, TireNode node) {
        if (node.s != null) {
            set.add(node.s);
        }

        for (int[] dir : dirs) {
            int x = i + dir[0], y = j + dir[1];
            if (x < 0 || x >= m || y < 0 || y >= n) {
                continue;
            }
            if (vis[x][y]) {
                continue;
            }
            int v = boards[x][y] - 'a';
            if (node.tns[v] != null) {
                vis[x][y] = true;
                dfs(x, y, node.tns[v]);
                vis[x][y] = false;
            }
        }
    }
}
```


# [LeetCode_36_1_有效的数独](https://leetcode-cn.com/problems/valid-sudoku/)
## 题目
请你判断一个 9x9 的数独是否有效。只需要 根据以下规则 ，验证已经填入的数字是否有效即可。

1. 数字 1-9 在每一行只能出现一次。
2. 数字 1-9 在每一列只能出现一次。
3. 数字 1-9 在每一个以粗实线分隔的 3x3 宫内只能出现一次。（请参考示例图）

数独部分空格内已填入了数字，空白格用 '.' 表示。

注意：

* 一个有效的数独（部分已被填充）不一定是可解的。
* 只需要根据以上规则，验证已经填入的数字是否有效即可。
 

示例 1：

```
输入：board = 
[["5","3",".",".","7",".",".",".","."]
,["6",".",".","1","9","5",".",".","."]
,[".","9","8",".",".",".",".","6","."]
,["8",".",".",".","6",".",".",".","3"]
,["4",".",".","8",".","3",".",".","1"]
,["7",".",".",".","2",".",".",".","6"]
,[".","6",".",".",".",".","2","8","."]
,[".",".",".","4","1","9",".",".","5"]
,[".",".",".",".","8",".",".","7","9"]]
输出：true
```

示例 2：
```
输入：board = 
[["8","3",".",".","7",".",".",".","."]
,["6",".",".","1","9","5",".",".","."]
,[".","9","8",".",".",".",".","6","."]
,["8",".",".",".","6",".",".",".","3"]
,["4",".",".","8",".","3",".",".","1"]
,["7",".",".",".","2",".",".",".","6"]
,[".","6",".",".",".",".","2","8","."]
,[".",".",".","4","1","9",".",".","5"]
,[".",".",".",".","8",".",".","7","9"]]
输出：false
解释：除了第一行的第一个数字从 5 改为 8 以外，空格内其他数字均与 示例1 相同。 但由于位于左上角的 3x3 宫内有两个 8 存在, 因此这个数独是无效的。
```


提示：

* board.length == 9
* board[i].length == 9
* board[i][j] 是一位数字或者 '.'

## 理解
本题的题意是判断当前数独的有效性，我们需要对行，列，和小块区域进行分别判断，难点在于怎么判断某个位置
落在那个小块中，我们对小块区域按每行依次编号第一行从左到右为1,2,3，中间一行为4,5,6，最下面一行为7,8,9。  
我们按行号i和列号j来计算当前小块的编号：idx = i / 3 * 3 + j / 3  
解法一使用了哈希表存储值，解法二使用的二维数组来替代哈希表，解法三使用位运算，利用9位二进制来存储，0和
1分别代表某个值有值无值。

在9*9的固定数据量下，时间复杂度和空间复杂度均为O(1)

## 解法一
### 代码
```java
public class LeetCode_36_1_有效的数独 {
    public boolean isValidSudoku(char[][] board) {
        Map<Integer, Set<Integer>> row = new HashMap<>(), col = new HashMap<>(), area = new HashMap<>();
        for (int i = 0; i < 9; i++) {
            row.put(i, new HashSet<>());
            col.put(i, new HashSet<>());
            area.put(i, new HashSet<>());
        }

        for (int i = 0; i < 9; i++) {
            for (int j = 0; j < 9; j++) {
                char c = board[i][j];
                if (c == '.') {
                    continue;
                }
                int u = c - '0';
                int idx = i / 3 * 3 + j / 3;
                if (row.get(i).contains(u) || col.get(j).contains(u) || area.get(idx).contains(u)) {
                    return false;
                }
                row.get(i).add(u);
                col.get(j).add(u);
                area.get(idx).add(u);
            }
        }
        return true;
    }
}
```

## 解法二
### 代码
```java
public class LeetCode_36_2_有效的数独 {
    public boolean isValidSudoku(char[][] board) {
        boolean[][] row = new boolean[10][10], col = new boolean[10][10], area = new boolean[10][10];
        for (int i = 0; i < 9; i++) {
            for (int j = 0; j < 9; j++) {
                char c = board[i][j];
                if (c == '.') {
                    continue;
                }
                int u = c - '0';
                int idx = i / 3 * 3 + j / 3;
                if (row[i][u] || col[j][u] || area[idx][u]) {
                    return false;
                }
                row[i][u] = col[j][u] = area[idx][u] = true;
            }
        }
        return true;
    }
}
```

## 解法三
### 代码
```java
public class LeetCode_36_3_有效的数独 {
    public boolean isValidSudoku(char[][] board) {
        int[] row = new int[10], col = new int[10], area = new int[10];
        for (int i = 0; i < 9; i++) {
            for (int j = 0; j < 9; j++) {
                char c = board[i][j];
                if (c == '.') {
                    continue;
                }
                int u = c - '0';
                int idx = i / 3 * 3 + j / 3;
                if (((row[i] >> u) & 1) == 1 || ((col[j] >> u) & 1) == 1 || ((area[idx] >> u) & 1) == 1) {
                    return false;
                }
                row[i] |= 1 << u;
                col[j] |= 1 << u;
                area[idx] |= 1 << u;
            }
        }
        return true;
    }
}
```

# [LeetCode_292_1_Nim游戏](https://leetcode-cn.com/problems/nim-game/)
## 题目
你和你的朋友，两个人一起玩 Nim 游戏：

* 桌子上有一堆石头。
* 你们轮流进行自己的回合，你作为先手。
* 每一回合，轮到的人拿掉 1 - 3 块石头。
* 拿掉最后一块石头的人就是获胜者。

假设你们每一步都是最优解。请编写一个函数，来判断你是否可以在给定石头数量为 n 的情况下赢得游戏。如果可以赢，返回 true；否则，返回 false 。

 
示例 1：
```
输入：n = 4
输出：false 
解释：如果堆中有 4 块石头，那么你永远不会赢得比赛；
     因为无论你拿走 1 块、2 块 还是 3 块石头，最后一块石头总是会被你的朋友拿走。
```

示例 2：
```
输入：n = 1
输出：true
```

示例 3：
```
输入：n = 2
输出：true
```

提示：

* 1 <= n <= 2^31 - 1

## 理解
博弈论题目，列举归纳：  
1. 假设石子数量小于4个，那么先手者一次拿完，先手者必胜
2. 假设石子数量等于4个，那么先手者无论怎么拿都会有剩余，后手者会一次拿完，那么先手者必败
3. 假设石子数量在[5,7]个，那么先手者必然可以使自己拿完后剩余的石子保持在4个，那么后手者无法一次拿完，
先手者必胜
4. 同理假设石子数量为8个，那么经过先手者和后手者各一次拿去后，后手者可控制石子剩余4个，那么先手者必败

经过列举我们发现如果石子数量是4的倍数时，先手者必败，其余先手者必胜。  

证明，如果存在n % 4 != 0，先手者总是能通过调整拿取数量，使得到剩余数量为4的倍数，那么先手者必胜，
反之必输。

### 代码
```java
public class LeetCode_292_1_Nim游戏 {
    public boolean canWinNim(int n) {
        return n % 4 != 0;
    }
}
```

# [LeetCode_650_1_只有两个键的键盘](https://leetcode-cn.com/problems/2-keys-keyboard/)
## 题目
最初记事本上只有一个字符 'A' 。你每次可以对这个记事本进行两种操作：

* Copy All（复制全部）：复制这个记事本中的所有字符（不允许仅复制部分字符）。
* Paste（粘贴）：粘贴 上一次 复制的字符。

给你一个数字 n ，你需要使用最少的操作次数，在记事本上输出 恰好 n 个 'A' 。返回能够打印出 n 个 'A' 的最少操作次数。

 
示例 1：
```
输入：3
输出：3
解释：
最初, 只有一个字符 'A'。
第 1 步, 使用 Copy All 操作。
第 2 步, 使用 Paste 操作来获得 'AA'。
第 3 步, 使用 Paste 操作来获得 'AAA'。
```

示例 2：
```
输入：n = 1
输出：0
```

提示：

* 1 <= n <= 1000

## 理解
解法三：使用动态规划，定义状态f[i][j]代表记事本有i个字符，粘贴板有j个字符，所需要的最小操作数  
我们粘贴板的字符都是通过copy all来的，所以一个合法的f[i][j]必然有j <= i  
我们不失一般性的考虑一下f[i][j]的转移方程：  
1. 最后一次是paste操作，那么粘贴板的字符数不变，所以有f[i][j] = f[i - j][j] + 1
2. 最后一次是copy all操作，那么必然有i == j，此时f[i][j] = min(f[i][x] + 1)， 0 <= x < i，即
在j < i的所有值中取最小值，做一次copy all  

我们发现最后一个合法的f[i][j]是通过j<i的所有值中的最小值转移而来，所以我们可以利用临时变量在遍历过程
中记录这个最小值，以便于最终对f[i][j]赋值  
再进一步我们发现最后一次操作必然是paste操作，所以原来粘贴板的字符数不会超过i/2，因此在转移f[i][j]时（j<i），
只需要枚举到[0,i/2]即可。  
时间复杂度为O(n^2)，空间复杂度为O(n^2)

解法四：使用动态规划，优化为一维数组，定义状态为f[i]代表记事本有i个字符，需要的最小操作数  
假设我们想要得到i个字符，我们首先得有一个j个字符，再对j个字符使用copy all操作，再使用若干次paste操作
，最终得到i个字符  
这里我们发现j必须是i的因数，粘贴操作使用的次数是i/j - 1次，我们可以枚举j进行转义，进而得到转移方程：  
f[i] = min(f[j] + i/j)，j < i，且j是i的一个因子  
初始话f[1] = 0，最终结果为f[n]  
优化：在枚举因子时，本来我们需要枚举范围[1,i)，由于i必然同时拥有j和i/j两个因子，两者中必然有小于或等于
根号i的值的，所以我们只需要遍历[1,根号i]的范围即可，遍历过程中取两个因子转移值中的较小值即可。  
时间复杂度为O(n根号n)，空间复杂度为O(n)

解法一：使用数学思想，分解质因数
如果我们把一次copy和x次paste看做一次动作，那么一次动作所产生的效果就是将原来的字符串变为x+1倍  
最终的最小操作次数操作如下： 
1. 起始长度为1的记事本字符进行一次copy，k1 - 1次paste，消耗操作次数k1次，得到k1长度的字符
2. 对长度为k1的记事本字符进行一次copy，k2 - 1次paste，消耗操作总次数为k1+k2次，得到长度为k1*k2的字符
3. ....  

最终进行k次动作后，得到长度为n的记事本长度，有n = k1 * k2 * k3 .... * kx  
得到总的操作次数为 k1 + k2 + k3 .... + kx，问题转化为如何拆分可以使操作次数最小  
对任意一个合数而言有 a * b >= a + b，所以我们进可能的拆分必然不会导致操作次数的增多  
因此我们可以使用试除法对n进行质因子分解操作，累加所有的操作次数得到答案  
时间复杂度为O(根号n)，空间复杂度为O(1)

解法二：只是对解法一的分解质因数方案进行了预操作，使用静态代码块求解值范围内的所有数的质因子和，并存储  
时间复杂度为O(C*根号C)，C是常数，所以为O(1)，空间复杂度为O(C)，也可以看为O(1)

## 解法一
### 代码
```java
public class LeetCode_650_1_只有两个键的键盘 {
    public int minSteps(int n) {
        int ans = 0;
        for (int i = 2; i * i <= n; i++) {
            while (n % i == 0) {
                ans += i;
                n /= i;
            }
        }
        if (n != 1) {
            ans += n;
        }
        return ans;
    }
}
```

## 解法二
### 代码
```java
public class LeetCode_650_2_只有两个键的键盘 {
    static int N = 1010;
    static int[] g = new int[N];
    static {
        for (int i = 2; i < N; i++) {
            int cnt = 0;
            int n = i;
            for (int j = 2; j * j <= n ; j++) {
                while (n % j == 0) {
                    cnt += j;
                    n /= j;
                }
            }
            if (n != 1) {
                cnt += n;
            }
            g[i] = cnt;
        }
    }

    public int minSteps(int n) {
        return g[n];
    }
}
```

## 解法三
### 代码
```java
public class LeetCode_650_3_只有两个键的键盘 {
    static final int INF = 0x3f3f3f3f;
    public int minSteps(int n) {
        // f[i][j]代表当前记事本上有i个字符，粘贴板上有j个字符的最小操作次数
        int[][] f = new int[n + 1][n + 1];
        for (int i = 0; i <= n ; i++) {
            for (int j = 0; j <= n ; j++) {
                f[i][j] = INF;
            }
        }

        f[1][0] = 0;
        f[1][1] = 1;
        for (int i = 2; i <= n; i++) {
            int min = INF;
            for (int j = 0; j <= i / 2 ; j++) {
                f[i][j] = f[i - j][j] + 1;
                min = Math.min(min, f[i][j]);
            }
            f[i][i] = min + 1;
        }
        int ans = INF;
        for (int i = 0; i <= n; i++) {
            ans = Math.min(ans, f[n][i]);
        }
        return ans;
    }
}
```

## 解法四
### 代码
```java
public class LeetCode_650_4_只有两个键的键盘 {
    public int minSteps(int n) {
        // f[i]代表记事本上有i个字符，所需要的最少操作次数
        int[] f = new int[n + 1];
        for (int i = 2; i <= n; i++) {
            f[i] = Integer.MAX_VALUE;
            for (int j = 1; j * j <= i; j++) {
                if (i % j == 0) {
                    f[i] = Math.min(f[i], f[j] + i / j);
                    f[i] = Math.min(f[i], f[i/j] + j);
                }
            }
        }
        return f[n];
    }
}
```