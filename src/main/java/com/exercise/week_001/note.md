# LeetCode_450_1_删除二叉搜索树中的节点
## 链接
https://leetcode-cn.com/problems/delete-node-in-a-bst/
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

# LeetCode_700_1_二叉搜索树中的搜索  
## 链接
https://leetcode-cn.com/problems/search-in-a-binary-search-tree/
## 题目
给定二叉搜索树（BST）的根节点和一个值。 你需要在BST中找到节点值等于给定值的节点。 返回以该节点为根的子树。 如果节点不存在，则返回 NULL。  
**例如**  
```
给定二叉搜索树:

        4
       / \
      2   7
     / \
    1   3

和值: 2
```  
你应该返回如下子树:
```
      2     
     / \   
    1   3
```  
在上述示例中，如果要找的值是 5，但因为没有节点值为 5，我们应该返回 NULL。

## 理解  
考察二叉搜索树的节点查找及递归  
了解二叉搜索树的特性，node的值大于左子数，小于其右子数。
## 解法一
递归
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
    public TreeNode searchBST(TreeNode root, int val) {
        if (root == null) {
            return root;
        }
        if (root.val > val) {
            return  searchBST(root.left, val);
        }
        if (root.val < val) {
            return  searchBST(root.right, val);
        }
        return root;
    }

}
```

## 解法二
迭代
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
    public TreeNode searchBST(TreeNode root, int val) {
        while (true) {
            if (root == null || root.val == val) {
                return root;
            }
            root = root.val > val ? root.left : root.right;
        }
    }

}
```

# LeetCode_701_1_二叉搜索树中的插入操作  
## 链接
https://leetcode-cn.com/problems/insert-into-a-binary-search-tree/
## 题目  
给定二叉搜索树（BST）的根节点和要插入树中的值，将值插入二叉搜索树。 返回插入后二叉搜索树的根节点。 输入数据 保证 ，新值和原始二叉搜索树中的任意节点值都不同。

注意，可能存在多种有效的插入方式，只要树在插入后仍保持为二叉搜索树即可。 你可以返回 任意有效的结果 。

**示例 1：**
```

输入：root = [4,2,7,1,3], val = 5
输出：[4,2,7,1,3,5]
解释：另一个满足题目要求可以通过的树是：

```

**示例 2：**
```

输入：root = [40,20,60,10,30,50,70], val = 25
输出：[40,20,60,10,30,50,70,null,null,25]

```

**示例 3：**
```

输入：root = [4,2,7,1,3,null,null,null,null,null,null], val = 5
输出：[4,2,7,1,3,5]

```

**提示**
* 给定的树上的节点数介于 0 和 10^4 之间
* 每个节点都有一个唯一整数值，取值范围从 0 到 10^8
* -10^8 <= val <= 10^8
* 新值和原始二叉搜索树中的任意节点值都不同

## 理解
考察二叉搜索树的元素插入

**添加步骤：**
* 找到父节点parent
* 创建新的节点node
* parent.left = node 或者 parent.right = node
* 遇到相等的值，建议覆盖

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
    public TreeNode insertIntoBST(TreeNode root, int val) {
        TreeNode node = new TreeNode(val);
        if (root == null) {
            return node;
        }
        // 创建遍历的节点
        TreeNode node1 = root;
        // 记录添加节点的父节点
        TreeNode indexNode = root;
        // 记录新节点应该加在左边还是右边
        boolean flag = false;
        while (node1 != null) {
            if (val < node1.val) {
                indexNode = node1;
                node1 = node1.left;
                flag = true;
            } else {
                indexNode = node1;
                node1 = node1.right;
                flag = false;
            }
        }
        if (flag) {
            indexNode.left = node;
        } else {
            indexNode.right = node;
        }

        return root;
    }
}
```
# LeetCode_98_1_验证二叉搜索树
## 链接
https://leetcode-cn.com/problems/validate-binary-search-tree/submissions/
## 题目
给定一个二叉树，判断其是否是一个有效的二叉搜索树。

假设一个二叉搜索树具有如下特征：

* 节点的左子树只包含小于当前节点的数。
* 节点的右子树只包含大于当前节点的数。
* 所有左子树和右子树自身必须也是二叉搜索树。

** 示例1：**
```
输入:
    2
   / \
  1   3
输出: true
```

** 示例2：**
```
输入:
    5
   / \
  1   4
     / \
    3   6
输出: false
解释: 输入为: [5,1,4,null,null,3,6]。
     根节点的值为 5 ，但是其右子节点值为 4 。
```
## 理解
考察二叉树的特点，二叉搜索树node的左子树的值都小于node的值，右子树的值都大于node
的值，左子树和右子树分别也是二叉搜索树。
使用递归方法遍历二叉搜索树的所有节点，看值是否在lower和upper的区间之间。
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
     public boolean isValidBST(TreeNode root) {
       return recurse(root, null, null);
    }

    boolean recurse(TreeNode node, Integer lower, Integer upper) {
        if (node == null) {
            return true;
        }
        if (lower != null && node.val <= lower) {
            return false;
        }
        if (upper != null && node.val >= upper) {
            return false;
        }
        if (!recurse(node.left, lower, node.val)) {
            return false;
        }
        if (!recurse(node.right, node.val, upper)) {
            return false;
        }
        // 只有根节点
        return true;
    }
}
```
  