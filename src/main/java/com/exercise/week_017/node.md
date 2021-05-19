[toc]
# [LeetCode_993_1_二叉树的堂兄弟节点](https://leetcode-cn.com/problems/cousins-in-binary-tree/)
## 题目
在二叉树中，根节点位于深度 0 处，每个深度为 k 的节点的子节点位于深度 k+1 处。

如果二叉树的两个节点深度相同，但 父节点不同 ，则它们是一对堂兄弟节点。

我们给出了具有唯一值的二叉树的根节点 root ，以及树中两个不同节点的值 x 和 y 。

只有与值 x 和 y 对应的节点是堂兄弟节点时，才返回 true 。否则，返回 false。

 

示例 1：
```
输入：root = [1,2,3,4], x = 4, y = 3
输出：false
```


示例 2：
```
输入：root = [1,2,3,null,4,null,5], x = 5, y = 4
输出：true
```


示例 3：
```
输入：root = [1,2,3,null,4], x = 2, y = 3
输出：false
```

提示：

* 二叉树的节点数介于 2 到 100 之间。
* 每个节点的值都是唯一的、范围为 1 到 100 的整数。

## 理解
利用层序遍历，遍历过程中判断子节点的值是否与x和y相同，如果相同记录下标记位flag，如果
x和y同时为某个父节点的左右子节点则返回false，否则遍历完某一层后，查看本层的子元素是否
包含目标值x,y，同时包含则为堂兄弟节点。

### 代码
```java
public class LeetCode_993_1_二叉树的堂兄弟节点 {
    public boolean isCousins(TreeNode root, int x, int y) {
        Queue<TreeNode> queue = new LinkedList<>();
        queue.offer(root);
        // 记录每一层的元素个数
        int size = 1;
        boolean flag = false;
        List<Integer> list = new ArrayList<>();
        while (!queue.isEmpty()) {
            TreeNode node = queue.poll();
            list.add(node.val);
            size--;
            TreeNode left = node.left;
            TreeNode right = node.right;
            if (left != null) {
                queue.offer(left);
                if (left.val == x || left.val == y) {
                    flag = true;
                }
            }
            if (right != null) {
                queue.offer(right);
                if (flag) {
                    if (right.val == x || right.val == y) {
                        return false;
                    }
                }
            }
            flag = false;
            if (size == 0) {
                size = queue.size();
                if (list.contains(x) && list.contains(y)) {
                    return true;
                }
                list = new ArrayList<>();
            }
        }
        return false;
    }
}
```
# [LeetCode_1442_1_形成两个异或相等数组的三元组数目](https://leetcode-cn.com/problems/count-triplets-that-can-form-two-arrays-of-equal-xor/)
## 题目
给你一个整数数组 arr 。

现需要从数组中取三个下标 i、j 和 k ，其中 (0 <= i < j <= k < arr.length) 。

a 和 b 定义如下：
```
a = arr[i] ^ arr[i + 1] ^ ... ^ arr[j - 1]
b = arr[j] ^ arr[j + 1] ^ ... ^ arr[k]
注意：^ 表示 按位异或 操作。
```

请返回能够令 a == b 成立的三元组 (i, j , k) 的数目。

 

示例 1：
```
输入：arr = [2,3,1,6,7]
输出：4
解释：满足题意的三元组分别是 (0,1,2), (0,2,2), (2,3,4) 以及 (2,4,4)
```

示例 2：
```
输入：arr = [1,1,1,1,1]
输出：10
```

示例 3：
```
输入：arr = [2,3]
输出：0
```

示例 4：
```
输入：arr = [1,3,5,7,9]
输出：3
```

示例 5：
```
输入：arr = [7,11,12,9,5,2,7,17,22]
输出：8
```

提示：

* 1 <= arr.length <= 300
* 1 <= arr[i] <= 10^8

## 理解
解法一：利用前缀和，分别遍历i,j,k来求解，暴力方法，时间复杂度为
O(n³)
解法二：利用前缀和，因为有在[i,k]有a=b，根据异或运算的性质，我们
可以得到arr[i] ^ arr[i+1] ^ arr[i+2] ……… ^ arr[k] = 0，
且在[i,k]这个区间中，只要满足累计异或的结果为0，那么j可以为i+1,
i+2,i+3……,k，共k-i种可能。所以我们确认了i的位置后，找相应的k
的位置，找到后累计到结果中（即ans+=k-i），总体时间复杂度在O(n²)
解法三：有了解法2后，我们知道假设满足preArr[i] = preArr[k+1]的
i有多个，即总共的结果为:  
(k - i1) + (k - i2) + …… + (k - im) = k * m - (i1 + i2 + …… + im)  
所以我们利用map，分别存贮preArr[k]出现的次数以及对应的下标的和，
遍历k的坐标从0开始，判断的下标为preArr[k + 1]，map入库的key为
preArr[k]，保证了找到的i的下标至少与k相差1，查询的preArr的下标与
map保存的最大下标至少需要差2，才能保证有两个元素，保证了题意的满足
i<j<=k。整体时间复杂度为O(n)

## 解法一
### 代码
```java
public class LeetCode_1442_1_形成两个异或相等数组的三元组数目 {
    public static int countTriplets(int[] arr) {
        int n = arr.length;
        if (n < 2) {
            return 0;
        }
        // 定义前缀和数组
        int[] preArr = new int[n + 1];
        int ans = 0;
        // 初始化前缀和数组的值
        int value = 0;
        for (int i = 1; i <= n; i++) {
            value ^= arr[i - 1];
            preArr[i] = value;
        }
        // 遍历i
        for (int i = 1; i <= n; i++) {
            // 遍历j
            for (int j = i + 1; j <= n; j++) {
                for (int k = j; k <= n; k++) {
                    int a = preArr[i - 1] ^ preArr[j - 1];
                    int b = preArr[j - 1] ^ preArr[k];
                    if (a == b) {
                        ans += 1;
//                        System.out.println("-----i,j,k =" + (i - 1) + "," + (j - 1) + "," + (k - 1));
                    }
                }
            }
        }
        return ans;
    }
}
```

## 解法二
### 代码
```java
public class LeetCode_1442_2_形成两个异或相等数组的三元组数目 {
    public static int countTriplets(int[] arr) {
        int n = arr.length;
        if (n < 2) {
            return 0;
        }

        int ans = 0;
        // 遍历i的位置
        for (int i = 0; i < n-1; i++) {
            int sum = arr[i];
            // 遍历k的位置
            for (int k = i + 1; k < n; k++) {
                sum ^= arr[k];
                if (sum == 0) {
                    ans += k - i;
                }
            }
        }
        return ans;
    }
}
```

## 解法三
### 代码
```java
public class LeetCode_1442_3_形成两个异或相等数组的三元组数目 {
    public static int countTriplets(int[] arr) {
        int n = arr.length;
        if (n < 2) {
            return 0;
        }
        // 定义前缀和数组
        int[] preArr = new int[n + 1];
        int ans = 0;
        // 初始化前缀和数组的值
        for (int i = 1; i <= n; i++) {
            preArr[i] = preArr[i - 1] ^ arr[i - 1];
        }
        Map<Integer, Integer> cntMap = new HashMap<>();
        Map<Integer, Integer> totalMap = new HashMap<>();
        // 遍历i
        for (int i = 0; i < n; i++) {
            // k的坐标至少比i大1（map里存放的preArr的坐标与判断的坐标需要至少相差2）
            // 当i = 1时，map存入的下标到0，判断的下标到2，即判断最小的满足题意的三元组坐标，i,j,k = 0,1,1
            // 当i = n - 1时，map存入的下标到n - 2， 判断下标到n，即判断的是最大满足题意的三元组坐标，i,j,k = n-2,n-1,n-1
            if (cntMap.containsKey(preArr[i + 1])) {
                ans += cntMap.get(preArr[i + 1]) * i - totalMap.get(preArr[i + 1]);
            }
            cntMap.put(preArr[i], cntMap.getOrDefault(preArr[i], 0) + 1);
            totalMap.put(preArr[i], totalMap.getOrDefault(preArr[i], 0) + i);
        }
        return ans;
    }
}
```
# [LeetCode_1738_1_找出第k大的异或坐标值](https://leetcode-cn.com/problems/find-kth-largest-xor-coordinate-value/)
## 题目
给你一个二维矩阵 matrix 和一个整数 k ，矩阵大小为 m x n 由非负整数组成。

矩阵中坐标 (a, b) 的 值 可由对所有满足 0 <= i <= a < m 且 0 <= j <= b < n 的元素 matrix[i][j]（下标从 0 开始计数）执行异或运算得到。

请你找出 matrix 的所有坐标中第 k 大的值（k 的值从 1 开始计数）。

 

示例 1：
```
输入：matrix = [[5,2],[1,6]], k = 1
输出：7
解释：坐标 (0,1) 的值是 5 XOR 2 = 7 ，为最大的值。
```

示例 2：
```
输入：matrix = [[5,2],[1,6]], k = 2
输出：5
解释：坐标 (0,0) 的值是 5 = 5 ，为第 2 大的值。
```

示例 3：
```
输入：matrix = [[5,2],[1,6]], k = 3
输出：4
解释：坐标 (1,0) 的值是 5 XOR 1 = 4 ，为第 3 大的值。
```

示例 4：
```
输入：matrix = [[5,2],[1,6]], k = 4
输出：0
解释：坐标 (1,1) 的值是 5 XOR 2 XOR 1 XOR 6 = 0 ，为第 4 大的值。
```

提示：

* m == matrix.length
* n == matrix[i].length
* 1 <= m, n <= 1000
* 0 <= matrix[i][j] <= 106
* 1 <= k <= m * n

## 理解
使用动态规划求取子区间的异或结果，再使用优先队列来求取第k大的值，
我们转义方程为：  
f[i][j] = f[i-1][j] ^ f[i][j-1] ^ f[i-1][j-1] ^ matrix[i-1][j-1]  
我们动态规划的首行和首列还是初始化为0，继续往下求解。初始化优先队列的
容量为k，且排列规则由小到大，当队列中元素少于k个时，直接将子区间的异或
结果添加到队列，当队列中的元素大于等于k个时，判断子区间异或结果是否大于
队首元素（队列中的最小元素），如果大于，则弹出队首元素，将结果入队，否则
放弃结果（不可能是第k大值），全部遍历完后，返回队首值，即为第k大值。

### 代码
```java
public class LeetCode_1738_1_找出第k大的异或坐标值 {
    public int kthLargestValue(int[][] matrix, int k) {
        int m = matrix.length;
        int n = matrix[0].length;
        int[][] f = new int[m + 1][n + 1];
        PriorityQueue<Integer> queue = new PriorityQueue<>(k, (a, b) -> a-b);
        for (int i = 1; i <= m; i++) {
            for (int j = 1; j <= n ; j++) {
                f[i][j] = f[i - 1][j] ^ f[i][j - 1] ^ f[i - 1][j - 1] ^ matrix[i - 1][j - 1];
                if (queue.size() < k) {
                    queue.add(f[i][j]);
                } else {
                    if (f[i][j] > queue.peek()) {
                        queue.poll();
                        queue.add(f[i][j]);
                    }
                }
            }
        }
        return queue.peek();
    }
}
```