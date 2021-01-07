# LeetCode_450_1_删除二叉搜索树中的节点
## 题目
给定一个二叉搜索树的根节点 root 和一个值 key，删除二叉搜索树中的 key 对应的节点，并保证二叉搜索树的性质不变。返回二叉搜索树（有可能被更新）的根节点的引用。

一般来说，删除节点可分为两个步骤：

首先找到需要删除的节点；
如果找到了，删除它。  
**说明:** 要求算法时间复杂度为 O(h)，h 为树的高度。
**示例:**
```
root = [5,3,6,2,4,null,7]
key = 3

    5
   / \
  3   6
 / \   \
2   4   7

给定需要删除的节点值是 3，所以我们首先找到 3 这个节点，然后删除它。

一个正确的答案是 [5,4,6,2,null,null,7], 如下图所示。

    5
   / \
  4   6
 /     \
2       7

另一个正确答案是 [5,2,6,null,4,null,7]。

    5
   / \
  2   6
   \   \
    4   7

```

## 理解
本题考查的是二叉树的前驱节点、后继节点的查找
节点的删除（度为二的节点删除，度为一的节点删除，叶子节点的删除） 
本题的前驱后继查找都是在特定条件下的，删除也不能删除叶子节点

### 正常的二叉树前驱节点查询方法：
1. 如果有左子节点，就取左子节点中最大的。即node.left.right.right....
直到该节点的右子节点为空
2. 如果没有左子节点，有父节点，就取父节点中第一个比自己小的，即直到该节点是
父节点的右子节点截止，如果遍历完也没找到，则没有前驱（二叉搜索树的最小节点）
3. 如果既没有左子节点，也没有父节点，则没有前驱（只有一个根节点）

### 正常的二叉树后继节点查询方法：
1. 如果有右子节点，就取右子节点中最小的。即node.rignt.left.left....
直到该节点的左子节点为空
2. 如果没有右子节点，有父节点，就取父节点中第一个比自己大的，即直到该节点
是父节点的左子节点，如果遍历完也没找到，则没有后驱（二叉搜索树的最大节点）
3. 如果既没有右子节点，也没有父节点，则没有后驱（只有一个根节点）

### 节点的删除
1. 度为2的节点删除，获取node的前驱节点或后继节点，
* 前驱节点或后继节点的值赋值给node的值
* 再删除相应的前驱或后继节点
* 如果一个节点的度为2，那么它的前驱、后继节点的度只可能是1和0
2. 度为1的节点删除，获取该节点的子节点（左或右）
* 子节点的parent指向node的parent
* node的parent的left或right指向子节点
* 如果node是根节点，root=child, child.parent=null
3. 删除叶子节点
* node的parent的left或right置为null
* 如果node是根节点，root=null

### 代码
```java
/**
 * Definition for a binary tree node.
 * public class TreeNode {
 *     int val;
 *     TreeNode left;
 *     TreeNode right;
 *     TreeNode() {}
 *     TreeNode(int val) { this.val = val; }
 *     TreeNode(int val, TreeNode left, TreeNode right) {
 *         this.val = val;
 *         this.left = left;
 *         this.right = right;
 *     }
 * }
 */
class Solution {
    public TreeNode deleteNode(TreeNode root, int key) {
        if (root == null) {
            return root;
        }
        if (root.val > key) {
            root.left = deleteNode(root.left, key);
        } else if (root.val < key) {
            root.right = deleteNode(root.right, key);
        } else {
            // 叶子节点（无规定，返回）
            if (root.left == null && root.right == null) {
                return null;
            } else if (root.left != null) {
               root.val = predecessor(root).val;
               root.left = deleteNode(root.left, root.val);
            } else {
                root.val = successor(root).val;
                root.right = deleteNode(root.right, root.val);
                
            }
        }

        return root;
    }

    /**
     * 查询node的前驱
     * @param node
     * @return
     */
    private TreeNode predecessor(TreeNode node) {
        // 1.有左子节点，查询左子节点中最大的
        node = node.left;
        while (node.right != null) {
            node = node.right;
        }
        return node;

        // 2.无左子节点，有父节点，查询父节点，一直查询到节点是父节点的右节点，否则没有前驱

        // 3.无左节点，无父节点，没有前驱
    }

    /**
     * 查询node的后继
     * @param node
     * @return
     */
    private TreeNode successor(TreeNode node) {
        node = node.right;
        while (node.left != null) {
            node = node.left;
        }
        return node;
    }

}
```

