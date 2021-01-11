# LeetCode_530_1_二叉搜索树的最小绝对差
## 链接
https://leetcode-cn.com/problems/minimum-absolute-difference-in-bst/
## 题目
给你一棵所有节点为非负值的二叉搜索树，请你计算树中任意两节点的差的绝对值的最小值。

**示例：**
```
输入：

   1
    \
     3
    /
   2

输出：
1

解释：
最小绝对差为 1，其中 2 和 1 的差的绝对值为 1（或者 2 和 3）。

```
**提示：**
* 树中至少有 2 个节点。
* 二叉树的大小范围在 2 到 100。
* 二叉树总是有效的，每个节点的值都是整数，且不重复。
* 本题与 783 https://leetcode-cn.com/problems/minimum-distance-between-bst-nodes/ 相同

## 理解
使用中序遍历将二叉搜索树中的val按从小到大的顺序放入list。  
list相邻的两个数求差，最后取所有差的最小值。

## 解法一
### 代码
```java
/**
 * Definition for a binary tree node.
 * public class TreeNode {
 *     int val;
 *     TreeNode left;
 *     TreeNode right;
 *     TreeNode(int x) { val = x; }
 * }
 */
class Solution {
    public int getMinimumDifference(TreeNode root) {
        List<Integer> list = new ArrayList<>();
        inorder(root, node -> {
            list.add(node.val);
        });
        int ans = Integer.MAX_VALUE;
        for (int i=1; i<list.size(); i++) {
            ans = Math.min(ans, list.get(i)-list.get(i-1));
        }
        return ans;
    }

    /**
     * 中序遍历
     * @param root
     * @param consumer
     */
    public void inorder(TreeNode root, Consumer<TreeNode> consumer) {
        if (root == null || consumer == null) {
            return;
        }
        inorder(root.left, consumer);
        consumer.accept(root);
        inorder(root.right, consumer);
    }
}
```
## 解法二
使用了非递归，借助栈做中序遍历，直接在遍历过程中求解最小差值。

### 代码
```java
public class LeetCode_530_2_二叉搜索树的最小绝对差 {
    int pre = -1;
    int ans = Integer.MAX_VALUE;
    public int getMinimumDifference(TreeNode root) {
        inorder(root);
        return ans;
    }

    /**
     * 中序遍历
     * @param root
     */
    public void inorder(TreeNode root) {
        TreeNode node = root;
        Stack<TreeNode> stack = new Stack<>();
        while (true) {
            if (node != null) {
                stack.push(node);
                node = node.left;
            } else {
                if (stack.empty()) {
                    break;
                }
                node = stack.pop();
                if (pre == -1) {
                    pre = node.val;
                } else {
                    ans = Math.min(ans, node.val - pre);
                    pre = node.val;
                }
                node = node.right;
            }
        }
    }
}
```
## 解法三
查看别人提交，递归直接处理，执行时间最短。

### 代码
```java
public class LeetCode_530_3_二叉搜索树的最小绝对差 {
    private int pre = -1;
    private int ans = Integer.MAX_VALUE;
    public int getMinimumDifference(TreeNode root) {
        inorder(root);
        return ans;
    }

    /**
     * 中序遍历
     * @param root
     */
    public void inorder(TreeNode root) {
        if (root == null) {
            return;
        }
        inorder(root.left);
        if (pre != -1) {
            ans = Integer.min(ans, root.val - pre);
        }
        pre = root.val;
        inorder(root.right);
    }
}
```