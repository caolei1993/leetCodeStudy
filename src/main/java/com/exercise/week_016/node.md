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