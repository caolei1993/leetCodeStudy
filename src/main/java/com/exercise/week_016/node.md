[toc]
# [LeetCode_872_1_叶子相似的树](https://leetcode-cn.com/problems/leaf-similar-trees/)
## 题目
请考虑一棵二叉树上所有的叶子，这些叶子的值按从左到右的顺序排列形成一个 叶值序列 。

举个例子，如上图所示，给定一棵叶值序列为 (6, 7, 4, 9, 8) 的树。

如果有两棵二叉树的叶值序列是相同，那么我们就认为它们是 叶相似 的。

如果给定的两个根结点分别为 root1 和 root2 的树是叶相似的，则返回 true；否则返回 false 。

 
示例 1：
```
输入：root1 = [3,5,1,6,2,9,8,null,null,7,4], root2 = [3,5,1,6,7,4,2,null,null,null,null,null,null,9,8]
输出：true
```

示例 2：
```
输入：root1 = [1], root2 = [1]
输出：true
```

示例 3：
```
输入：root1 = [1], root2 = [2]
输出：false
```

示例 4：
```
输入：root1 = [1,2], root2 = [2,2]
输出：true
```

示例 5：
```
输入：root1 = [1,2,3], root2 = [1,3,2]
输出：false
```

提示：

* 给定的两棵树可能会有 1 到 200 个结点。
* 给定的两棵树上的值介于 0 到 200 之间。

## 理解
利用递归的方法实现二叉树前序遍历，遍历过程中，将符合条件的节点（无子节点）添加至集合，找到
两个二叉树满足题意的两个集合后，比较集合即可。

### 代码
```java
public class LeetCode_872_1_叶子相似的树 {
    public boolean leafSimilar(TreeNode root1, TreeNode root2) {
        List<Integer> list1 = new ArrayList<>();
        List<Integer> list2 = new ArrayList<>();
        preOrder(root1, list1);
        preOrder(root2, list2);
        int n1 = list1.size();
        int n2 = list2.size();
        if (n1 != n2) {
            return false;
        }
        for (int i = 0; i < n1; i++) {
            if (!list1.get(i).equals(list2.get(i))) {
                return false;
            }
        }
        return true;
    }
    public void preOrder(TreeNode node, List<Integer> list) {
        if (node == null) {
            return;
        }
        if (node.left == null && node.right == null) {
            list.add(node.val);
        }
        preOrder(node.left, list);
        preOrder(node.right, list);
    }
}
```
# [LeetCode_1734_1_解码异或后的排列](https://leetcode-cn.com/problems/decode-xored-permutation/)
## 题目
给你一个整数数组 perm ，它是前 n 个正整数的排列，且 n 是个 奇数 。

它被加密成另一个长度为 n - 1 的整数数组 encoded ，满足 encoded[i] = perm[i] XOR perm[i + 1] 。比方说，如果 perm = [1,3,2] ，那么 encoded = [2,1] 。

给你 encoded 数组，请你返回原始数组 perm 。题目保证答案存在且唯一。

 
示例 1：
```
输入：encoded = [3,1]
输出：[1,2,3]
解释：如果 perm = [1,2,3] ，那么 encoded = [1 XOR 2,2 XOR 3] = [3,1]
```

示例 2：
```
输入：encoded = [6,5,4,6]
输出：[2,4,1,5,3]
```

提示：

* 3 <= n < 105
* n 是奇数。
* encoded.length == n - 1

## 理解
解法一：暴力解法，因为我们知道了原数组和前n个正整数的排列，那我们遍历前n个正整数，让他们
依次作为首个元素，再根据 ans[i] = encoded[i - 1] ^ ans[i - 1]，求取整个ans数组，再将
数组转化为集合，与包含了前n个正整数的集合做比较，相等的话就返回结果。（运行超时）
解法二：因为有encoded[i] = ans[i] ^ ans[i + 1]，因此：  
e[0] = a[0] ^ a[1]  
e[1] = a[1] ^ a[2]  
...
e[n - 1] = a[n - 1] ^ a[n]  
我们从第一个开始，间隔一个，直到encoded[n - 1]之前的encoded数组的异或结果a，即：  
a = a[0] ^ a[1] ^ a[2] ^ a[3] ......... ^ a[n - 1]  
因为原数组是n个正整数的排列，且异或运算遵循交换律，所有我们求前n个正整数的异或结果b：  
b = a[0] ^ a[1] ^ a[2] ^ a[3] ......... ^ a[n - 1] ^ a[n]  
我们求取 a ^ b 就得到了 a[n]，再根据 e[i] = a[i] ^ a[i + 1]，两边同时异或a[i + 1]得：  
a[i] = e[i] ^ a[i + 1]，我们依次从后往前求取数组的值，直到求取到完整的数组。 

## 解法一
### 代码
```java
public class LeetCode_1734_1_解码异或后的排列 {
    public static int[] decode(int[] encoded) {
        int n = encoded.length;
        int[] ans = new int[n + 1];
        List<Integer> list = new ArrayList<>(n + 1);
        for (int i = 1; i <= n + 1 ; i++) {
            list.add(i);
        }
        // 遍历首个元素可能的值
        for (int i = 1; i <= n + 1 ; i++) {
            ans[0] = i;
            for (int j = 1; j < n + 1; j++) {
                ans[j] = ans[j - 1] ^ encoded[j - 1];
            }
            System.out.println(Arrays.toString(ans));
            List<Integer> list1 = Arrays.stream(ans).boxed().collect(Collectors.toList());
            if (list1.containsAll(list)){
                return ans;
            }
        }
        return ans;
    }
}
```
## 解法二
### 代码
```java
public class LeetCode_1734_2_解码异或后的排序 {
    public int[] decode(int[] encoded) {
        int n = encoded.length;
        int[] ans = new int[n + 1];
        // a = ans[0] ^ ans[1] ^ ans[2] .... ^ ans[n]
        int a = 0;
        for (int i = 1; i <= n + 1 ; i++) {
            a = a ^ i;
        }
        // b = ans[0] ^ ans[1] ^ ans[2] ..... ^ ans[n - 1]
        int b = 0;
        for (int i = 0; i < n - 1 ; i += 2) {
            b = b ^ encoded[i];
        }
        ans[n] = a ^ b;
        for (int i = n - 1; i >= 0  ; i--) {
            ans[i] = ans[i + 1] ^ encoded[i];
        }
        return ans;
    }
}
```
# [LeetCode_1310_1_子数组异或查询](https://leetcode-cn.com/problems/xor-queries-of-a-subarray/)
## 题目
有一个正整数数组 arr，现给你一个对应的查询数组 queries，其中 queries[i] = [Li, Ri]。

对于每个查询 i，请你计算从 Li 到 Ri 的 XOR 值（即 arr[Li] xor arr[Li+1] xor ... xor arr[Ri]）作为本次查询的结果。

并返回一个包含给定查询 queries 所有结果的数组。

示例 1：
```
输入：arr = [1,3,4,8], queries = [[0,1],[1,2],[0,3],[3,3]]
输出：[2,7,14,8]
解释：
数组中元素的二进制表示形式是：
1 = 0001
3 = 0011
4 = 0100
8 = 1000
查询的 XOR 值为：
[0,1] = 1 xor 3 = 2
[1,2] = 3 xor 4 = 7
[0,3] = 1 xor 3 xor 4 xor 8 = 14
[3,3] = 8
```

示例 2：
```
输入：arr = [4,8,2,10], queries = [[2,3],[1,3],[0,0],[0,3]]
输出：[8,0,4,4]
 
```

提示：

* 1 <= arr.length <= 3 * 10^4
* 1 <= arr[i] <= 10^9
* 1 <= queries.length <= 3 * 10^4
* queries[i].length == 2
* 0 <= queries[i][0] <= queries[i][1] < arr.length

## 理解
解法一：暴力枚举，时间复杂度在O（n * m），依次求取ans数组中的每一个元素，在通过
获取queries[i]，来确认异或的起始和结尾位置，求取值，再赋值到ans的相应位置。
解法二：前缀和，时间复杂度在O（n），n为queries数组的长度，我们预先求取前缀和数组，
这里前缀和的求取，利用异或算法代替，根据异或运算的性质，我们知道两个相等的值异或的
结果为0且异或运算遵循交换律，所以[i,j]区间的异或结果为，preArr[i - 1] ^ preArr[j]
需特殊处理i为0的情况，或者preArr从下标1开始复制到下标n。

## 解法一
### 代码
```java
public class LeetCode_1310_1_子数组异或查询 {
    public int[] xorQueries(int[] arr, int[][] queries) {
        int n = queries.length;
        int[] ans = new int[n];
        for (int i = 0; i < n ; i++) {
            int[] subArr = queries[i];
            int value = 0;
            for (int j = subArr[0]; j <= subArr[1]; j++) {
                value = value ^ arr[j];
            }
            ans[i] = value;
        }
        return ans;
    }
}
```

## 解法二
### 代码
```java
public class LeetCode_1310_2_子数组异或查询 {
    public int[] xorQueries(int[] arr, int[][] queries) {
        int m = arr.length;
        // 定义前缀和数组（这里做异或操作）
        int[] preArr = new int[m];
        int value = 0;
        // 初始化前缀和数组
        for (int i = 0; i < m; i++) {
            value = value ^ arr[i];
            preArr[i] = value;
        }

        int n = queries.length;
        int[] ans = new int[n];

        for (int i = 0; i < n ; i++) {
            int[] subArr = queries[i];
            ans[i] = subArr[0] == 0 ? preArr[subArr[1]] : preArr[subArr[0] - 1] ^ preArr[subArr[1]];
        }
        return ans;
    }
}
```

# [LeetCode_421_1_数组中两个数的最大异或值](https://leetcode-cn.com/problems/maximum-xor-of-two-numbers-in-an-array/)
## 题目
给你一个整数数组 nums ，返回 nums[i] XOR nums[j] 的最大运算结果，其中 0 ≤ i ≤ j < n 。

进阶：你可以在 O(n) 的时间解决这个问题吗？

 

示例 1：
```
输入：nums = [3,10,5,25,2,8]
输出：28
解释：最大运算结果是 5 XOR 25 = 28.
```

示例 2：
```
输入：nums = [0]
输出：0
```

示例 3：
```
输入：nums = [2,4]
输出：6
```

示例 4：
```
输入：nums = [8,10,2]
输出：10
```

示例 5：
```
输入：nums = [14,70,53,83,49,91,36,80,92,51,66,70]
输出：127
```

提示：

* 1 <= nums.length <= 2 * 104
* 0 <= nums[i] <= 231 - 1

## 理解
利用字典树（Tire）来辅助求取异或结果的最大值，我们用字典树，存储每一个数字的二进制值，
只可能有两种情况也就是0和1，再通过字典树的getVal方法来获取，某一位与当前值的该位（
二进制位）值相反的数，尽可能取差异位多的情况，保证异或的结果最大，这样当遍历完整个数组
时，不断取最大的异或结果，就能得到我们的目标值。

### 代码
```java
public class LeetCode_421_1_数组中两个数的最大异或值 {

    static class Node {
        Node[] nds = new Node[2];
    }
    Node root = new Node();

    private void add (int x) {
        Node p = root;
        for (int i = 31; i >= 0 ; i--) {
            int a = (x >> i) & 1;
            if (p.nds[a] == null) {
                p.nds[a] = new Node();
            }
            p = p.nds[a];
        }
    }

    private int getVal(int x) {
        Node p = root;
        int ans = 0;
        for (int i = 31; i >= 0 ; i--) {
            int a = (x >> i) & 1, b = 1 - a;
            if (p.nds[b] != null) {
                ans |= b << i;
                p = p.nds[b];
            } else {
                ans |= a << i;
                p = p.nds[a];
            }
        }
        return ans;
    }
    public int findMaximumXOR(int[] nums) {
        int ans = 0;
        for (int i : nums) {
            add(i);
            int j = getVal(i);
            ans = Math.max(ans, i ^ j);
        }
        return ans;
    }
}
```
