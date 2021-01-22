[toc]
# [LeetCode_144_1_二叉树的前序遍历](https://leetcode-cn.com/problems/binary-tree-preorder-traversal/)
## 题目
给你二叉树的根节点 root ，返回它节点值的 前序 遍历。
**示例 1：**  
输入：root = [1,null,2,3]
输出：[1,2,3]

**示例 2：**  
输入：root = []
输出：[]

**示例 3：**  
输入：root = [1]
输出：[1]

**示例 4：**  
输入：root = [1,2]
输出：[1,2]

**示例 5：**  
输入：root = [1,null,2]
输出：[1,2]

**提示：**  
树中节点数目在范围 [0, 100] 内
-100 <= Node.val <= 100  
 
进阶：递归算法很简单，你可以通过迭代算法完成吗？

## 理解
* 解法一：使用递归写法（递归三部曲，确认递归方法的参数及返回值，确认截止条件，确认每一次递归最小操作）
* 解法二：使用迭代写法（借助栈数据结构，实现中左右遍历）

## 解法一
### 代码
```java
public class LeetCode_144_1_二叉树的前序遍历 {
    List<Integer> list = new ArrayList<>();
    public List<Integer> preorderTraversal(TreeNode root) {
        preorder(root);
        return list;
    }

    public void preorder(TreeNode node) {
        if (node == null) {
            return;
        }
        list.add(node.val);
        preorder(node.left);
        preorder(node.right);
    }
}

```

## 解法二
### 代码
```java
public class LeetCode_144_2_二叉树的前序遍历 {
    public List<Integer> preorderTraversal(TreeNode root) {
        List<Integer> list = new ArrayList<>();
        Stack<TreeNode> stack = new Stack<>();
        if (root == null) {
            return list;
        }
        stack.push(root);
        while (!stack.isEmpty()) {
            TreeNode node = stack.pop();
            list.add(node.val);
            if (node.right != null) {
                stack.push(node.right);
            }
            if (node.left != null) {
                stack.push(node.left);
            }
        }
        return list;
    }
}
```
# [LeetCode_94_1_二叉树的中序遍历](https://leetcode-cn.com/problems/binary-tree-inorder-traversal/)
## 题目
给定一个二叉树的根节点 root ，返回它的 中序 遍历。

**示例 1：**  
输入：root = [1,null,2,3]
输出：[1,3,2]

**示例 2：**  
输入：root = []
输出：[]

**示例 3：**  
输入：root = [1]
输出：[1]

**示例 4：**  
输入：root = [1,2]
输出：[2,1]

**示例 5：**  
输入：root = [1,null,2]
输出：[1,2]
 
**提示：**

树中节点数目在范围 [0, 100] 内
-100 <= Node.val <= 100

进阶: 递归算法很简单，你可以通过迭代算法完成吗？

## 理解
* 解法一：使用递归
* 解法二：使用迭代

## 解法一
### 代码
```java
public class LeetCode_94_1_二叉树的中序遍历 {
    private List<Integer> list = new ArrayList<>();
    public List<Integer> inorderTraversal(TreeNode root) {
        inorder(root);
        return list;
    }

    public void inorder (TreeNode node) {
        if (node == null) {
            return;
        }
        inorder(node.left);
        list.add(node.val);
        inorder(node.right);
    }
}
```

## 解法二
### 代码
```java
public class LeetCode_94_2_二叉树的中序遍历 {
    public List<Integer> inorderTraversal(TreeNode root) {
        List<Integer> list = new ArrayList<>();
        Stack<TreeNode> stack = new Stack<>();
        TreeNode node = root;
        while (true) {
            if (node != null) {
                stack.push(node);
                node = node.left;
            } else {
                if (stack.isEmpty()) {
                    break;
                }
                node = stack.pop();
                list.add(node.val);
                node = node.right;
            }
        }
        return list;
    }
}
```
# [LeetCode_145_1_二叉树的后序遍历](https://leetcode-cn.com/problems/binary-tree-postorder-traversal/)
## 题目
给定一个二叉树，返回它的 后序 遍历。

**示例:**

输入: [1,null,2,3]  
   1
    \
     2
    /
   3 

输出: [3,2,1]  
进阶: 递归算法很简单，你可以通过迭代算法完成吗？

## 理解
* 解法一：使用递归
* 解法二：使用迭代，前序遍历的顺序为中左右，后续遍历为左右中，借助前序遍历更改为中右左，在翻转一次
* 解法三：使用迭代，无论遍历左节点还是右节点，最终都需要回到根节点，借助记录处理的最后一个节点是否为
当前节点的右节点来确认是否该节点下的所有子节点都已经处理完，只需处理根节点自己。

## 解法一
### 代码
```java
public class LeetCode_145_1_二叉树的后序遍历 {
    private List<Integer> list = new ArrayList<>();
    public List<Integer> postorderTraversal(TreeNode root) {
        postorder(root);
        return list;
    }
    public void postorder(TreeNode node) {
        if (node == null) {
            return;
        }
        postorder(node.left);
        postorder(node.right);
        list.add(node.val);
    }
}
```

## 解法二
### 代码
```java
public class LeetCode_145_2_二叉树的后序遍历 {
    public List<Integer> postorderTraversal(TreeNode root) {
        List<Integer> list = new ArrayList<>();
        Stack<TreeNode> stack = new Stack<>();
        Stack<TreeNode> stack2 = new Stack<>();
        if (root == null) {
            return list;
        }
        TreeNode node = root;
        stack.push(node);
        while (!stack.isEmpty()) {
            node = stack.pop();
            stack2.push(node);
            if (node.left != null) {
                stack.push(node.left);
            }
            if (node.right != null)  {
                stack.push(node.right);
            }
        }
        while (!stack2.isEmpty()) {
            list.add(stack2.pop().val);
        }
        return list;
    }
}
```
## 解法三
### 代码
```java
public class LeetCode_145_3_二叉树的后序遍历 {
    public List<Integer> postorderTraversal(TreeNode root) {
        List<Integer> list = new ArrayList<>();
        Stack<TreeNode> stack = new Stack<>();
        TreeNode curr = root;
        TreeNode last = null;
        while (curr != null || !stack.isEmpty()) {
            while (curr != null) {
                stack.push(curr);
                curr = curr.left;
            }
            curr = stack.peek();
            if (curr.right == null || curr.right == last) {
                list.add(curr.val);
                stack.pop();
                last = curr;
                curr = null;
            } else {
                curr = curr.right;
            }
        }
        return list;
    }
}
```
# [LeetCode_102_1_二叉树的层序遍历](https://leetcode-cn.com/problems/binary-tree-level-order-traversal/)
## 题目
给你一个二叉树，请你返回其按 层序遍历 得到的节点值。 （即逐层地，从左到右访问所有节点）。

**示例：**  
二叉树：[3,9,20,null,null,15,7], 
```
    3
   / \
  9  20
    /  \
   15   7
```
返回其层序遍历结果：  
```
[
  [3],
  [9,20],
  [15,7]
]
```
## 理解
 二叉树的层序遍历，借助队列数据结构（先进先出），从上至下遍历二叉树，因为需要对每层操作，所以定义临时
 变量size来记录每层节点个数，用来判断每层遍历是否完成。
 
### 代码
```java
public class LeetCode_102_1_二叉树的层序遍历 {
    public List<List<Integer>> levelOrder(TreeNode root) {
        List<List<Integer>> list = new ArrayList<>();
        Queue<TreeNode> queue = new LinkedList<>();
        int size = 1;

        List<Integer> level = new ArrayList<>();
        if (root == null) {
            return list;
        }
        queue.offer(root);

        while (!queue.isEmpty()) {
            TreeNode node = queue.poll();
            level.add(node.val);
            size--;
            if (node.left != null) {
                queue.offer(node.left);
            }
            if (node.right != null) {
                queue.offer(node.right);
            }
            if (size == 0) {
                size = queue.size();
                list.add(level);
                level = new ArrayList<>();
            }
        }
        return list;
    }
}
```
# [LeetCode_104_1_二叉树的最大深度](https://leetcode-cn.com/problems/maximum-depth-of-binary-tree/)
## 题目
给定一个二叉树，找出其最大深度。

二叉树的深度为根节点到最远叶子节点的最长路径上的节点数。  

说明: 叶子节点是指没有子节点的节点。

**示例：**
给定二叉树 [3,9,20,null,null,15,7]，
```
    3
   / \
  9  20
    /  \
   15   7
```

返回它的最大深度 3 。

## 理解
* 解法一：使用层序遍历，记录遍历的最大层数
* 解法二：使用递归法，求最大深度

## 解法一：
### 代码：
```java
public class LeetCode_104_1_二叉树的最大深度 {
    public int maxDepth(TreeNode root) {
        Queue<TreeNode> queue = new LinkedList<>();
        int ans = 0;
        if (root == null) {
            return ans;
        }
        // 每一层节点个数
        int size = 1;
        queue.offer(root);
        while (!queue.isEmpty()) {
            TreeNode node = queue.poll();
            size--;
            if (node.left != null) {
                queue.offer(node.left);
            }
            if (node.right != null) {
                queue.offer(node.right);
            }
            // 代表本层已经遍历完，该遍历下一层
            if (size == 0) {
                ans++;
                size = queue.size();
            }
        }
        return ans;
    }
}

```

## 解法二：
### 代码：
```java
public class LeetCode_104_2_二叉树的最大深度 {
    public int maxDepth(TreeNode root) {
      if (root == null) {
          return 0;
      }
      return Math.max(maxDepth(root.left), maxDepth(root.right)) + 1;
    }
}
```

