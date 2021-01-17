# [LeetCode_530_1_二叉搜索树的最小绝对差](https://leetcode-cn.com/problems/minimum-absolute-difference-in-bst/)
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

# [LeetCode_108_1_将有序数组转化为二叉搜索树](https://leetcode-cn.com/problems/convert-sorted-array-to-binary-search-tree/)
## 题目
将一个按照升序排列的有序数组，转换为一棵高度平衡二叉搜索树。

本题中，一个高度平衡二叉树是指一个二叉树每个节点 的左右两个子树的高度差的绝对值不超过 1。

**示例：**
```
给定有序数组: [-10,-3,0,5,9],

一个可能的答案是：[0,-3,9,-10,null,5]，它可以表示下面这个高度平衡二叉搜索树：

      0
     / \
   -3   9
   /   /
 -10  5

```
## 理解
有序的数组类似二叉搜索树的中序遍历，反向思考，每次都取最中间的元素作为二叉搜索树当前位置的根节点。
可以使用递归来完成二叉搜索树还原。
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
     public TreeNode sortedArrayToBST(int[] nums) {
        return helper(nums, 0, nums.length - 1 );
    }

    public TreeNode helper(int[] nums, int left, int right) {
        if (left > right) {
            return null;
        }
        int mid = (left + right) / 2;
        TreeNode root = new TreeNode(nums[mid]);
        root.left = helper(nums, left, mid - 1);
        root.right = helper(nums, mid + 1 , right);
        return root;
    }
}
```

# [LeetCode_938_1_二叉搜索树的范围和](https://leetcode-cn.com/problems/range-sum-of-bst/)
## 题目
给定二叉搜索树的根结点 root，返回值位于范围 [low, high] 之间的所有结点的值的和。

**示例 1：**
```
输入：root = [10,5,15,3,7,null,18], low = 7, high = 15
输出：32
```

**示例 2：**
```
输入：root = [10,5,15,3,7,13,18,1,null,6], low = 6, high = 10
输出：23
```

**提示：**
* 树中节点数目在范围 [1, 2 * 104] 内
* 1 <= Node.val <= 105
* 1 <= low <= high <= 105
* 所有 Node.val 互不相同

## 理解
* 使用中序遍历，将在范围的值叠加
* 使用栈，遍历在有可能在范围内的值，判断叠加
* 迭代，判断根节点的值的返回，使用迭代求值

## 解法一
### 代码
```java
public class LeetCode_938_1_二叉搜索树的范围和 {
    private int sum = 0;
    private int low;
    private int high;
    public int rangeSumBST(TreeNode root, int low, int high) {
        this.low = low;
        this.high = high;
        inorder(root);
        return sum;
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
       if (root.val >= low && root.val <= high) {
           sum += root.val;
       }
       inorder(root.right);
    }
}
```
## 解法二
### 代码
```java
public class LeetCode_938_2_二叉搜索树的范围和 {
    public int rangeSumBST(TreeNode root, int low, int high) {
        int ans = 0;
        Stack<TreeNode> stack = new Stack<>();
        stack.push(root);
        while (!stack.empty()) {
            TreeNode node = stack.pop();
            if (node.val >= low && node.val <= high) {
                ans += node.val;
            }
            if (node.val > low && node.left != null) {
                stack.push(node.left);
            }
            if (node.val < high && node.right != null) {
                stack.push(node.right);
            }
        }
        return ans;
    }
}
```
## 解法三
### 代码
```java
public class LeetCode_938_3_二叉搜索树的范围和 {
    public int rangeSumBST(TreeNode root, int low, int high) {
        if (root == null) {
            return 0;
        }
        if (root.val >= low && root.val <= high) {
            return root.val + rangeSumBST(root.left, low, high) + rangeSumBST(root.right, low, high);
        } else if(root.val < low) {
            return rangeSumBST(root.right, low, high);
        } else {
            return rangeSumBST(root.left, low, high);
        }
    }
}
```
# [LeetCode_235_1_二叉搜索树的最近公共祖先](https://leetcode-cn.com/problems/lowest-common-ancestor-of-a-binary-search-tree/)
## 题目
给定一个二叉搜索树, 找到该树中两个指定节点的最近公共祖先。

百度百科中最近公共祖先的定义为：“对于有根树 T 的两个结点 p、q，最近公共祖先表示为一个结点 x，满足 x 是 p、q 的祖先且 x 的深度尽可能大（一个节点也可以是它自己的祖先）。”

例如，给定如下二叉搜索树:  root = [6,2,8,0,4,7,9,null,null,3,5]

**示例1：**
```
输入: root = [6,2,8,0,4,7,9,null,null,3,5], p = 2, q = 8
输出: 6 
解释: 节点 2 和节点 8 的最近公共祖先是 6。
```

**示例2：**
```
输入: root = [6,2,8,0,4,7,9,null,null,3,5], p = 2, q = 4
输出: 2
解释: 节点 2 和节点 4 的最近公共祖先是 2, 因为根据定义最近公共祖先节点可以为节点本身。
```

**说明：**
* 所有节点的值都是唯一的。
* p、q 为不同节点且均存在于给定的二叉搜索树中。

## 理解
* 方法一：从根节点遍历找p节点，记录沿途所有的父节点包含p自己，组成一个父节点集合。同理找到q的父节点集合，最后从后向前遍历p的父节点，看q的父节点集合是否包含，查询到高度最低的公共父节点。
* 方法二：只遍历一次，同时看p和q相对于root的位置，使用递归，查找最近公共父节点。
* 方法三：和方法二类似，只是不采用递归，使用迭代的方法实现。
## 解法一
### 代码
```java
public class LeetCode_235_1_二叉搜索树的最近公共祖先 {
    public TreeNode lowestCommonAncestor(TreeNode root, TreeNode p, TreeNode q) {
        List<TreeNode> parentP = getParentValueList(root, p);
        List<TreeNode> parentQ = getParentValueList(root, q);
        for (int i = parentP.size()-1; i >= 0 ; i--) {
            if (parentQ.contains(parentP.get(i))) {
                return parentP.get(i);
            }
        }
        return null;
    }
    public List<TreeNode> getParentValueList(TreeNode root, TreeNode node) {
        List<TreeNode> list = new ArrayList<>();
        while (root.val != node.val) {
            if (root.val < node.val) {
                list.add(root);
                root = root.right;
            } else {
                list.add(root);
                root = root.left;
            }
        }
        list.add(root);
        return list;
    }
}
```
## 解法二
### 代码
```java
public class LeetCode_235_2_二叉搜索树的最近公共祖先 {
    public TreeNode lowestCommonAncestor(TreeNode root, TreeNode p, TreeNode q) {
        if (root == null) {
            return null;
        }
        if (root.val > p.val && root.val > q.val) {
            return lowestCommonAncestor(root.left, p, q);
        } else if (root.val < p.val && root.val < q.val) {
            return lowestCommonAncestor(root.right, p, q);
        } else{
            // pq分别在左右节点中或pq中一个就是root节点
            return root;
        }
    }
}
```
## 解法三
### 代码
```java
public class LeetCode_235_3_二叉搜索树的最近公共祖先 {
    public TreeNode lowestCommonAncestor(TreeNode root, TreeNode p, TreeNode q) {
        TreeNode node = root;
        while (true) {
            if (node.val > p.val && node.val > q.val) {
                node = node.left;
            } else if (node.val < p.val && node.val < q.val) {
                node = node.right;
            } else{
                // pq分别在左右节点中或pq中一个就是node节点
                break;
            }
        }
        return node;
    }
}
```
# [LeetCode_230_1_二叉搜索树中第K小的元素](https://leetcode-cn.com/problems/kth-smallest-element-in-a-bst/)
## 题目
给定一个二叉搜索树，编写一个函数 kthSmallest 来查找其中第 k 个最小的元素。
**说明：**
你可以假设 k 总是有效的，1 ≤ k ≤ 二叉搜索树元素个数。
**示例1：**
```
输入: root = [3,1,4,null,2], k = 1
   3
  / \
 1   4
  \
   2
输出: 1
```
**示例2：**
```
输入: root = [5,3,6,2,4,null,null,1], k = 3
       5
      / \
     3   6
    / \
   2   4
  /
 1
输出: 3
```
**进阶：**
如果二叉搜索树经常被修改（插入/删除操作）并且你需要频繁地查找第 k 小的值，你将如何优化 kthSmallest 函数？

## 理解
* 解法一：中序遍历二叉搜索树从小到大排列，存入list,返回index等于k-1位置的元素及第k小的元素。
* 解法二：优化中序遍历，找到第k小的元素就停止遍历。

## 解法一
### 代码
```java
public class LeetCode_230_1_二叉搜索树中第K小的元素 {
    private List<Integer> list = new ArrayList<>();
    public int kthSmallest(TreeNode root, int k) {
        inorder(root);
        if (list.isEmpty()) {
            return 0;
        }
        return list.get(k-1);
    }

    public void inorder(TreeNode root) {
        if (root == null) {
            return;
        }
        inorder(root.left);
        list.add(root.val);
        inorder(root.right);
    }
}
```

## 解法二
### 代码
```java
public class LeetCode_230_2_二叉搜索树中第K小的元素 {
    private int index = 1;
    private int ans = 0;
    private boolean flag = false;
    public int kthSmallest(TreeNode root, int k) {
        inorder(root, k);
        return ans;
    }

    public void inorder(TreeNode root, int k) {
        if (root == null || flag) {
            return;
        }
        inorder(root.left, k);
        if (flag) {
            return;
        }
        if (k == index) {
            ans = root.val;
            flag = true;
        } else {
            index += 1;
        }
        inorder(root.right, k);
    }
}
```