[toc]
# [LeetCode_105_1_从前序与中序遍历序列构造二叉树](https://leetcode-cn.com/problems/construct-binary-tree-from-preorder-and-inorder-traversal/)
## 题目
根据一棵树的前序遍历与中序遍历构造二叉树。

注意:  
你可以假设树中没有重复的元素。

例如，给出

前序遍历 preorder = [3,9,20,15,7]  
中序遍历 inorder = [9,3,15,20,7]  
返回如下的二叉树：  
```
    3
   / \
  9  20
    /  \
   15   7
```

## 理解
* 解法一：使用递归法，构造二叉树，可以构建对应左子树的前序遍历与中序遍历数组，但比较麻烦，优化为直接
标记坐标。将坐标作为递归方法的参数。
* 解法二：使用迭代法，主要依靠前序遍历的两条规律，两个相邻节点（a,b）的关系
  1. b是a的左子节点
  2. a没有左子节点，b是a的右子节点或是a祖父节点的右子节点  
  利用栈来存储遍历过的前序遍历的节点，来找第二种情况b的父节点，主要判断栈顶元素是否与中序遍历的坐标指针所
  指的值相等，如果相等则栈顶元素没有左子树，需要看当前遍历的前序遍历节点是谁的右子树。以此类推
  
 ## 解法一
 ### 代码
 ```java
public class LeetCode_1_105_从前序与中序遍历序列构造二叉树 {
    Map<Integer, Integer> map = new HashMap<>();
    public TreeNode buildTree(int[] preorder, int[] inorder) {

        for (int i=0; i<inorder.length; i++) {
            map.put(inorder[i], i);
        }
        return myBuildTree(preorder, inorder, 0, preorder.length-1, 0, inorder.length-1);
    }

    private TreeNode myBuildTree(int[] preorder, int[] inorder, int preLeft, int preRight, int inLeft, int inRight) {
        if (preLeft > preRight) {
            return null;
        }
        TreeNode root = new TreeNode(preorder[preLeft]);
        int rootIndex = map.get(preorder[preLeft]);
        root.left = myBuildTree(preorder, inorder, preLeft+1, preLeft+rootIndex-inLeft, inLeft, rootIndex-1);
        root.right = myBuildTree(preorder, inorder, preLeft+rootIndex-inLeft+1, preRight, rootIndex+1, inRight);
        return root;
    }
}

```
 ## 解法二
 ### 代码
 ```java
public class LeetCode_2_105_从前序与中序遍历序列构造二叉树 {
    public TreeNode buildTree(int[] preorder, int[] inorder) {
        if (preorder == null || preorder.length == 0) {
            return null;
        }
        TreeNode root = new TreeNode(preorder[0]);
        Stack<TreeNode> stack = new Stack<>();
        stack.push(root);
        int inorderIndex = 0;
        for (int i=1; i<preorder.length; i++) {
            int preorderVal = preorder[i];
            TreeNode node = stack.peek();
            if (node.val != inorder[inorderIndex]) {
                node.left = new TreeNode(preorderVal);
                stack.push(node.left);
            } else {
                while (!stack.isEmpty() && stack.peek().val == inorder[inorderIndex]) {
                    node = stack.pop();
                    inorderIndex++;
                }
                node.right = new TreeNode(preorderVal);
                stack.push(node.right);
            }
        }
        return root;
    }
}

```