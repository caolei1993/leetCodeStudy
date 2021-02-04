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
# [LeetCode_889_1_根据前序和后续遍历构造二叉树](https://leetcode-cn.com/problems/construct-binary-tree-from-preorder-and-postorder-traversal/)
## 题目
返回与给定的前序和后序遍历匹配的任何二叉树。  

 pre 和 post 遍历中的值是不同的正整数。

 

示例：

输入：pre = [1,2,4,5,3,6,7], post = [4,5,2,6,7,3,1]  
输出：[1,2,3,4,5,6,7]
 

提示：

1 <= pre.length == post.length <= 30  
pre[] 和 post[] 都是 1, 2, ..., pre.length 的排列  
每个输入保证至少有一个答案。如果有多个答案，可以返回其中一个。

## 理解
* 解法一：利用递归法，递归函数参数设定为完整前序遍历序列，完整后序遍历序列，前序遍历起始坐标，前序遍历终止坐标，
后序遍历起始坐标，后序遍历终止坐标。其实就是变相的把要构成的节点的前序遍历序列和后续遍历序列做为递归
函数的参数，需要注意（子树长度 = 终止坐标 - 起始坐标 +1），同理（终止节点的坐标 = 起始坐标 + 子树长度 -1），
并且约定前序遍历根节点后的第一个元素必须为左子树。
* 解法二：也是利用递归法，只是递归函数的参数简化，简化为，前序遍历起始坐标，后续遍历的起始坐标，子树长度。
有以上三个参数很容易计算出终止坐标（终止坐标 = 起始坐标 + 子树长度 -1），也约定前序遍历根节点后的第一个元素必须为左子树。

## 解法一
### 代码
```java
public class LeetCode_889_1_根据前序和后续遍历构造二叉树 {
    Map<Integer, Integer> map = new HashMap<>();
    public TreeNode constructFromPrePost(int[] pre, int[] post) {
        for (int i=0; i<post.length; i++) {
            map.put(post[i], i);
        }

        return buildTree(pre, post, 0, pre.length-1, 0, post.length-1);
    }

    public TreeNode buildTree(int[] pre, int[] post, int preLeft, int preRight, int postLeft, int postRight) {
        if (preLeft > preRight) {
            return null;
        }
        TreeNode root = new TreeNode(pre[preLeft]);
        if (preLeft == preRight) {
            return root;
        }
        int leftLength = map.get(pre[preLeft+1]) - postLeft + 1;
        root.left = buildTree(pre, post, preLeft+1, preLeft+leftLength,  postLeft, postLeft+leftLength-1);
        root.right = buildTree(pre, post, preLeft+leftLength+1, preRight, postLeft+leftLength, postRight-1);
        return root;
    }
}
```
## 解法二
### 代码
```java
public class LeetCode_889_2_根据前序和后序遍历构造二叉树 {
    Map<Integer, Integer> map = new HashMap<>();
    public TreeNode constructFromPrePost(int[] pre, int[] post) {
        for (int i=0; i<post.length; i++) {
            map.put(post[i], i);
        }

        return buildTree(pre, post, 0, 0, post.length);
    }

    /**
     *
     * @param pre
     * @param post
     * @param preIndex 前序遍历首节点的坐标
     * @param postIndex 后序遍历首节点的坐标
     * @param size 长度
     * @return
     */
    public TreeNode buildTree(int[] pre, int[] post, int preIndex, int postIndex, int size) {
        if (size == 0) {
            return null;
        }
        TreeNode root = new TreeNode(pre[preIndex]);
        if (size == 1) {
            return root;
        }
        // 前序遍历根节点后默认认为是左节点
        int leftLength = map.get(pre[preIndex+1]) - postIndex + 1;
        root.left = buildTree(pre, post, preIndex+1, postIndex,  leftLength);
        root.right = buildTree(pre, post, preIndex+leftLength+1, postIndex+leftLength, size-leftLength-1);
        return root;
    }
}

```
# [LeetCode_101_1_对称二叉树](https://leetcode-cn.com/problems/symmetric-tree/)
## 题目
给定一个二叉树，检查它是否是镜像对称的。
 

例如，二叉树 [1,2,2,3,4,4,3] 是对称的。
```
    1
   / \
  2   2
 / \ / \
3  4 4  3
```


但是下面这个 [1,2,2,null,3,null,3] 则不是镜像对称的:

```
    1
   / \
  2   2
   \   \
   3    3
```

进阶：

你可以运用递归和迭代两种方法解决这个问题吗？

## 理解
* 解法一：使用迭代法，利用队列，先重复将根节点入队两次，如果队列不为空，进入循环，连续出队两个节点，需要
确认这两个节点如果值一样或全为null，即符合对称，否则不对称，子节点需要对称入队判断，第一个节点的左子节点和第二个
节点的右子节点一起入队，第一个节点的右子节点和第二个节点的左子节点一起入队。

* 解法二：使用递归法，递归函数两个参数（节点1，节点2），具体判断与迭代判断类似，递归调用参数也需要对称传输。

## 解法一
### 代码
```java
public class LeetCode_101_1_对称二叉树 {
    public boolean isSymmetric(TreeNode root) {
        return check(root, root);
    }

    private boolean check(TreeNode root, TreeNode root1) {
        Queue<TreeNode> queue = new LinkedList<>();
        queue.offer(root);
        queue.offer(root1);
        while (!queue.isEmpty()) {
            TreeNode a = queue.poll();
            TreeNode b = queue.poll();
            if (a == null && b == null ) {
                continue;
            }
            if (a == null || b == null || a.val != b.val) {
                return false;
            }

            queue.offer(a.left);
            queue.offer(b.right);

            queue.offer(a.right);
            queue.offer(b.left);
        }
        return true;
    }
}
```

## 解法二
### 代码
```java
public class LeetCode_101_2_对称二叉树 {
    public boolean isSymmetric(TreeNode root) {
        if (root == null) {
            return true;
        } else {
            return check(root.left, root.right);
        }
    }

    private boolean check(TreeNode left, TreeNode right) {
        if (left == null && right == null) {
            return true;
        }
        if (left == null || right == null) {
            return false;
        }
        return left.val == right.val && check(left.right, right.left) && check(left.left, right.right);
    }
}

```
# [LeetCode_237_1_删除链表中的节点](https://leetcode-cn.com/problems/delete-node-in-a-linked-list/)
## 题目
请编写一个函数，使其可以删除某个链表中给定的（非末尾）节点。传入函数的唯一参数为 要被删除的节点 。

 

现有一个链表 -- head = [4,5,1,9]，它可以表示为:

 

示例 1：

输入：head = [4,5,1,9], node = 5  
输出：[4,1,9]  
解释：给定你链表中值为 5 的第二个节点，那么在调用了你的函数之后，该链表应变为 4 -> 1 -> 9.  
示例 2：  

输入：head = [4,5,1,9], node = 1  
输出：[4,5,9]  
解释：给定你链表中值为 1 的第三个节点，那么在调用了你的函数之后，该链表应变为 4 -> 5 -> 9.  
 

提示：

链表至少包含两个节点。  
链表中所有节点的值都是唯一的。  
给定的节点为非末尾节点并且一定是链表中的一个有效节点。  
不要从你的函数中返回任何结果。

### 代码
```java
class Solution {
    public void deleteNode(ListNode node) {
        node.val = node.next.val;
        node.next = node.next.next;
    }
}
```