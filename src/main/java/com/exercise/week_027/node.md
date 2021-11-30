[toc]
# [LeetCode_1713_1_得到子序列的最少操作数](https://leetcode-cn.com/problems/minimum-operations-to-make-a-subsequence/)
## 题目
给你一个数组 target ，包含若干 互不相同 的整数，以及另一个整数数组 arr ，arr 可能 包含重复元素。

每一次操作中，你可以在 arr 的任意位置插入任一整数。比方说，如果 arr = [1,4,1,2] ，那么你可以在中间添加 3 得到 [1,4,3,1,2] 。你可以在数组最开始或最后面添加整数。

请你返回 最少 操作次数，使得 target 成为 arr 的一个子序列。

一个数组的 子序列 指的是删除原数组的某些元素（可能一个元素都不删除），同时不改变其余元素的相对顺序得到的数组。比方说，[2,7,4] 是 [4,2,3,7,2,1,4] 的子序列（加粗元素），但 [2,4,2] 不是子序列。


示例 1：
```
输入：target = [5,1,3], arr = [9,4,2,3,4]
输出：2
解释：你可以添加 5 和 1 ，使得 arr 变为 [5,9,4,1,2,3,4] ，target 为 arr 的子序列。
```

示例 2：
```
输入：target = [6,4,8,1,3,2], arr = [4,7,6,2,3,8,6,1]
输出：3
```

提示：

* 1 <= target.length, arr.length <= 10^5
* 1 <= target[i], arr[i] <= 10^9
* target 不包含任何重复元素。

## 理解
LCS转化为LIS + 二分查找  
题目需要求解得到子序列的最少操作数，因为可以在数组任意位置添加任意整数，所以我们可以将问题转化为
求取两个数组的最长公共子序列问题[LCS]，求取到最长子序列长度max后，用target的长度len减去max就是
最少操作数。  
因为题目有target数组的值互不相等，所以我们可以将LCS问题转化为LIS问题，即最长上升子序列问题。  
* 将target的元素依次用相应的坐标标记，得到的新的target坐标数组是一个单调上升序列
* 我们将arr中的元素依次遍历，如果存在于target中即替换为target中对应的坐标，如果不存在直接舍弃，
因为我们要找的公共子序列，所以不存在于target中的值舍弃不影响我们问题的求解。替换完成得到新的arr
的坐标数组，最终问题转化为我们在新的target坐标数组和新的arr的坐标数组中找最长公共子序列，也是
在arr坐标数组中找最长上升子序列。
* 我们可以借助g数组帮我们来计算最长子序列，g[i]代表长度为i的子序列的最后位置的最小值，因为子序列
都是递增序列，要想子序列长，递增序列就得递增的相对较慢一点，当遍历到某个arr坐标序列的值时，我们就
在g数组中利用二分查找，找比遍历值小的最大值（保证当前值能跟在一个相对长的子序列中），更新二分查找
位置的下一个位置的值，并计算子序列长度。

### 代码
```java
public class LeetCode_1713_1_得到子序列的最少操作数 {
    public int minOperations(int[] target, int[] arr) {
        int n = target.length;
        // 将target数组中的每个值映射成为相应的坐标
        Map<Integer, Integer> map = new HashMap<>();
        for (int i = 0; i < n; i++) {
            map.put(target[i], i);
        }
        // 将arr中在target中存在的元素转化成target的坐标，存入list
        List<Integer> list = new ArrayList<>();
        for (int a : arr) {
            if (map.containsKey(a)) {
                list.add(map.get(a));
            }
        }
        // 定义最长子序列的长度为0
        int len = list.size();
        int[] g = new int[len + 1];
        Arrays.fill(g, Integer.MAX_VALUE);
        int max = 0;
        for (Integer i : list) {
            int l = 0, r = len;
            while (l < r) {
                int mid = l + r + 1 >> 1;
                if (g[mid] < i) {
                    l = mid;
                } else {
                    r = mid - 1;
                }
            }
            int clen = r + 1;
            g[clen] = Math.min(g[clen], i);
            max = Math.max(max, clen);
        }
        return n - max;
    }
}
```

# [LeetCode_671_1_二叉树中第二小的节点](https://leetcode-cn.com/problems/second-minimum-node-in-a-binary-tree/)
## 题目
给定一个非空特殊的二叉树，每个节点都是正数，并且每个节点的子节点数量只能为 2 或 0。如果一个节点有两个子节点的话，那么该节点的值等于两个子节点中较小的一个。

更正式地说，root.val = min(root.left.val, root.right.val) 总成立。

给出这样的一个二叉树，你需要输出所有节点中的第二小的值。如果第二小的值不存在的话，输出 -1 。

示例 1：
```
输入：root = [2,2,5,null,null,5,7]
输出：5
解释：最小的值是 2 ，第二小的值是 5 。
```

示例 2：
```
输入：root = [2,2,2]
输出：-1
解释：最小的值是 2, 但是不存在第二小的值。
```

提示：

* 树中节点数目在范围 [1, 25] 内
* 1 <= Node.val <= 2^31 - 1
* 对于树中每个节点 root.val == min(root.left.val, root.right.val)

## 理解
解法一：使用递归的方法，可分二种情况考虑：  
* 根节点为空或无子节点
* 有子节点，需要递归查询和根节点值相等的子节点中的第二小节点，再与另外一个节点做比较，返回其中较小值

需要注意需要对-1做单独处理，免得取较小值时，产生差错。

解法二：使用前序遍历，遍历所有节点，找不等于根节点的值的最小值即为第二小值，遍历完成后返回。

## 解法一
### 代码
```java
public class LeetCode_671_1_二叉树中第二小的节点 {
    public int findSecondMinimumValue(TreeNode root) {
        // 根节点为null，或无子节点（题目要求子节点的数量只可能为2或0）
        if (root == null || root.left == null) {
            return -1;
        }
        // 两个子节点
        if (root.left.val == root.right.val) {
            int a = findSecondMinimumValue(root.left);
            int b = findSecondMinimumValue(root.right);
            if (a != -1 && b != -1) {
                return Math.min(a, b);
            } else {
                return Math.max(a, b);
            }

        }
        if (root.val == root.left.val) {
            int v = findSecondMinimumValue(root.left);
            if (v != -1) {
                return Math.min(v, root.right.val);
            }
            return root.right.val;
        } else {
            int v = findSecondMinimumValue(root.right);
            if (v != -1) {
                return Math.min(v, root.left.val);
            }
            return root.left.val;
        }
    }
}
```

## 解法二
### 代码
```java
public class LeetCode_671_2_二叉树中第二小的节点 {
    int ans = -1;
    public int findSecondMinimumValue(TreeNode root) {
        dfs(root, root.val);
        return ans;
    }

    private void dfs(TreeNode node, int cur) {
        if (node == null) {
            return;
        }
        if (node.val != cur) {
            if (ans == -1) {
                ans = node.val;
            } else {
                ans = Math.min(ans, node.val);
            }
        }
        dfs(node.left, cur);
        dfs(node.right, cur);
    }
}
```
# [LeetCode_863_1_二叉树中所有距离为K的结点](https://leetcode-cn.com/problems/all-nodes-distance-k-in-binary-tree/)
## 题目
给定一个二叉树（具有根结点 root）， 一个目标结点 target ，和一个整数值 K 。

返回到目标结点 target 距离为 K 的所有结点的值的列表。 答案可以以任何顺序返回。

 
示例 1：
```
输入：root = [3,5,1,6,2,0,8,null,null,7,4], target = 5, K = 2
输出：[7,4,1]
解释：
所求结点为与目标结点（值为 5）距离为 2 的结点，
值分别为 7，4，以及 1
```


注意，输入的 "root" 和 "target" 实际上是树上的结点。
上面的输入仅仅是对这些对象进行了序列化描述。
 

提示：

* 给定的树是非空的。
* 树上的每个结点都具有唯一的值 0 <= node.val <= 500 。
* 目标结点 target 是树上的结点。
* 0 <= K <= 1000.

## 理解
使用建图+BFS，链式前向星存图，遍历的时候借助队列，将目标结点入队，借助图来找目标结点为起点的所有
边，再找这些边的终点入队，循环直至找到第k步的所有节点。

### 代码
```java
public class LeetCode_863_1_二叉树中所有距离为K的结点 {
    /**
     * 题目限制最多有501个节点，且每个节点最多有两个边2*（无向边2）
     */
    final static int N = 501, M = N * 4;
    /**
     * head[a]代表a节点对应边的集合的头结点（类似链表）
     */
    int[] head = new int[N];
    /**
     * end[i]表示边i指向的节点
     */
    int[] end = new int[M];
    /**
     * next[i]表示以链表形式存储的边i对应的下一条边
     */
    int[] next = new int[M];
    /**
     * idx用来编号每一条边
     */
    int idx;

    private void add (int a, int b) {
        end[idx] = b;
        next[idx] = head[a];
        head[a] = idx++;
    }

    boolean[] vis = new boolean[N];
    public List<Integer> distanceK(TreeNode root, TreeNode target, int k) {
        List<Integer> ans = new ArrayList<>();
        Arrays.fill(head, -1);
        // 构建图
        dfs(root);

        Queue<Integer> queue = new LinkedList<>();
        queue.offer(target.val);
        vis[target.val] = true;
        while (!queue.isEmpty() && k >= 0) {
            int size = queue.size();
            while (size-- > 0) {
                int value = queue.poll();
                if (k == 0) {
                    ans.add(value);
                    continue;
                }

                for (int i = head[value]; i != -1 ; i = next[i]) {
                    // 获取i指向的结点
                    int j = end[i];
                    if (!vis[j]) {
                        queue.offer(j);
                        vis[j] = true;
                    }
                }
            }
            k--;
        }
        return ans;
    }

    private void dfs(TreeNode node) {
        if (node == null) {
            return;
        }
        if (node.left != null) {
            add(node.val, node.left.val);
            add(node.left.val, node.val);
            dfs(node.left);
        }
        if (node.right != null) {
            add(node.val, node.right.val);
            add(node.right.val, node.val);
            dfs(node.right);
        }
    }
}
```
# [LeetCode_1104_1_二叉树寻路](https://leetcode-cn.com/problems/path-in-zigzag-labelled-binary-tree/)
## 题目
在一棵无限的二叉树上，每个节点都有两个子节点，树中的节点 逐行 依次按 “之” 字形进行标记。

如下图所示，在奇数行（即，第一行、第三行、第五行……）中，按从左到右的顺序进行标记；

而偶数行（即，第二行、第四行、第六行……）中，按从右到左的顺序进行标记。

给你树上某一个节点的标号 label，请你返回从根节点到该标号为 label 节点的路径，该路径是由途经的节点标号所组成的。

 
示例 1：
```
输入：label = 14
输出：[1,3,4,14]
```

示例 2：
```
输入：label = 26
输出：[1,2,6,10,26]
```

提示：

* 1 <= label <= 10^6

## 理解
首先，我们明确如果二叉树每行都是从左往右排序，那么肯定满足以下几条性质：  
* 第i行有2^(i-1)个节点，最左边的标号为2^(i-1)，最右边的标号为2^i - 1
* 对于标号为val的节点，其左子节点的标号为2 * val，其右子节点的标号为2 * val + 1，当val > 1
时，其父节点的标号为 val/2  

因为题目要求按之子对满二叉树排序，所以奇数行都说从左到右排序，偶数行都是从右到左排序，所以为了
能使用正常从左到右排列的这些性质，在偶数行时我们得通过计算得到其本行对称位置的值。依次查找每一行
对应的值，直到根节点。

### 代码
```java
public class LeetCode_1104_1_二叉树寻路 {
    public List<Integer> pathInZigZagTree(int label) {
        int row = 1, rowStart = 1;
        while (rowStart * 2 <= label) {
            row++;
            rowStart *= 2;
        }
        if (row % 2 == 0) {
            label = getReverse(row, label);
        }
        List<Integer> ans = new ArrayList<>();

        while (row > 0) {
            if (row % 2 == 0) {
                ans.add(getReverse(row, label));
            } else {
                ans.add(label);
            }
            row--;
            label >>= 1;
        }
        Collections.reverse(ans);
        return ans;
    }

    private int getReverse(int row, int label) {
        return (1 << row - 1) + (1 << row) - 1 - label;
    }
}
```
# [LeetCode_171_1_Excel表列序号](https://leetcode-cn.com/problems/excel-sheet-column-number/)
## 题目
给你一个字符串 columnTitle ，表示 Excel 表格中的列名称。返回该列名称对应的列序号。


例如，

    A -> 1
    B -> 2
    C -> 3
    ...
    Z -> 26
    AA -> 27
    AB -> 28 
    ...
 
示例 1:
```
输入: columnTitle = "A"
输出: 1
```

示例 2:
```
输入: columnTitle = "AB"
输出: 28
```

示例 3:
```
输入: columnTitle = "ZY"
输出: 701
```

示例 4:
```
输入: columnTitle = "FXSHRXW"
输出: 2147483647
```

提示：

* 1 <= columnTitle.length <= 7
* columnTitle 仅由大写英文组成
* columnTitle 在范围 ["A", "FXSHRXW"] 内

## 理解
题目考察的就是将26进制转化为10进制，与week_023的168问题对应

### 代码
```java
public class LeetCode_171_1_Excel表列序号 {
    public int titleToNumber(String columnTitle) {
        char[] chars = columnTitle.toCharArray();
        int sum = 0;
        int factor = 26;
        for (char c :  chars) {
            int v = c - 'A' + 1;
            sum = sum * 26 + v;
        }
        return sum;
    }
}
```

# [LeetCode_1337_1_矩阵中战斗力最弱的k行](https://leetcode-cn.com/problems/the-k-weakest-rows-in-a-matrix/)
## 题目
给你一个大小为 m * n 的矩阵 mat，矩阵由若干军人和平民组成，分别用 1 和 0 表示。

请你返回矩阵中战斗力最弱的 k 行的索引，按从最弱到最强排序。

如果第 i 行的军人数量少于第 j 行，或者两行军人数量相同但 i 小于 j，那么我们认为第 i 行的战斗力比第 j 行弱。

军人 总是 排在一行中的靠前位置，也就是说 1 总是出现在 0 之前。

 
示例 1：
```
输入：mat = 
[[1,1,0,0,0],
 [1,1,1,1,0],
 [1,0,0,0,0],
 [1,1,0,0,0],
 [1,1,1,1,1]], 
k = 3
输出：[2,0,3]
解释：
每行中的军人数目：
行 0 -> 2 
行 1 -> 4 
行 2 -> 1 
行 3 -> 2 
行 4 -> 5 
从最弱到最强对这些行排序后得到 [2,0,3,1,4]
```

示例 2：
```
输入：mat = 
[[1,0,0,0],
 [1,1,1,1],
 [1,0,0,0],
 [1,0,0,0]], 
k = 2
输出：[0,2]
解释： 
每行中的军人数目：
行 0 -> 1 
行 1 -> 4 
行 2 -> 1 
行 3 -> 1 
从最弱到最强对这些行排序后得到 [0,2,3,1]
```

提示：

* m == mat.length
* n == mat[i].length
* 2 <= n, m <= 100
* 1 <= k <= m
* matrix[i][j] 不是 0 就是 1

## 理解
解法一：对矩阵中每行数据进行统计，统计的结果存入一个二维数组中，再根据题意对数组进行相应的排序，
最后取出前k个结果的坐标值返回。  
时间复杂度：遍历统计为O(m*n)，排序为O(mlogm)，构造答案为O(k)，最终为O(max(m*n, mlogm))  
空间复杂度：O(m)的空间用来存储统计的战斗力，排序需要O(logm)空间，整体为O(m + logm)  

解法二：二分+优先队列  
因为每行队伍军人都站在前面，我们可以利用二分法求解每行军人的个数，再利用优先队列对结果进行排序，
最后获取优先队列中前k个值返回。  

## 解法一
### 代码
```java
public class LeetCode_1337_1_矩阵中战斗力最弱的k行 {
    public int[] kWeakestRows(int[][] mat, int k) {
        int m = mat.length;
        int n = mat[0].length;
        int[][] counts = new int[m][2];
        for (int i = 0; i < m; i++) {
            int[] ma = mat[i];
            int sum = 0;
            for (int v : ma) {
                if (v == 1) {
                    sum++;
                } else {
                    break;
                }
            }
            counts[i] = new int[]{sum, i};
        }
        Arrays.sort(counts, (a, b) -> {
            if (a[0] == b[0]) {
                return a[1] - b[1];
            } else {
                return a[0] - b[0];
            }
        });
        int[] ans = new int[k];
        for (int i = 0; i < k; i++) {
            ans[i] = counts[i][1];
        }
        return ans;
    }
}
```

## 解法二
### 代码
```java
public class LeetCode_1337_2_矩阵中战斗力最弱的k行 {
    public int[] kWeakestRows(int[][] mat, int k) {
        int m = mat.length;
        int n = mat[0].length;
        PriorityQueue<int[]> queue = new PriorityQueue<>((a, b) -> {
            if (a[0] == b[0]) {
                return a[1] - b[1];
            } else {
                return a[0] - b[0];
            }
        });
        for (int i = 0; i < m; i++) {
            int l = 0, r = n - 1;
            while (l < r) {
                int mid = l + r + 1 >> 1;
                if (mat[i][mid] < 1) {
                    r = mid - 1;
                } else {
                    l = mid;
                }
            }
            int cur = mat[i][l] >= 1 ? r + 1 : r;
            queue.add(new int[]{cur, i});
        }
        int[] ans = new int[k];
        int index = 0;
        while (k-- > 0) {
            ans[index] = queue.poll()[1];
            index++;
        }
        return ans;
    }
}
```