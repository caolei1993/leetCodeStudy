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
# [LeetCode_206_1_反转链表](https://leetcode-cn.com/problems/reverse-linked-list/)
## 题目
反转一个单链表。

示例:

输入: 1->2->3->4->5->NULL  
输出: 5->4->3->2->1->NULL
  
进阶:  
你可以迭代或递归地反转链表。你能否用两种方法解决这道题？

## 理解
* 解法一：使用递归法，递归方法参数为ListNode,截止条件为ListNode==null || ListNode.next==null(只有一个节点，不需要反转)，
处理逻辑（先想象使用递归已经将head.next都已处理好，最后只需将head.next(反转的最后一个节点)的next置为head, head.next=null。
* 解法二：使用迭代法，利用当前节点与前一个节点两个变量来辅助迭代，当前节点初始化为头节点，前一个为null,
迭代条件为当前节点不为空，临时记录当前节点next为临时节点，当前节点next置为pre,pre置为curr,curr置为临时节点。

## 解法一
### 代码
```java
public class LeetCode_206_1_反转链表 {
    public ListNode reverseList(ListNode head) {
        if (head == null || head.next == null) {
            return head;
        }
        ListNode newHead = reverseList(head.next);
        ListNode lastNode = head.next;
        lastNode.next = head;
        head.next = null;
        return newHead;
    }
}
```
## 解法二
### 代码
```java
public class LeetCode_206_2_反转链表 {
    public ListNode reverseList(ListNode head) {
        ListNode curr = head;
        ListNode pre = null;
        while (curr != null) {
            // 临时存入当前节点的下一个
            ListNode tep = curr.next;
            // 当前节点的next指向前一个
            curr.next = pre;
            // 更新前一个节点
            pre = curr;
            // 更新当前节点
            curr = tep;
        }
        return pre;
    }
}
```
# [LeetCode_141_1_环形链表](https://leetcode-cn.com/problems/linked-list-cycle/)
## 题目
给定一个链表，判断链表中是否有环。

如果链表中有某个节点，可以通过连续跟踪 next 指针再次到达，则链表中存在环。 为了表示给定链表中的环，我们使用整数 pos 来表示链表尾连接到链表中的位置（索引从 0 开始）。 如果 pos 是 -1，则在该链表中没有环。注意：pos 不作为参数进行传递，仅仅是为了标识链表的实际情况。

如果链表中存在环，则返回 true 。 否则，返回 false 。

 
进阶：

你能用 O(1)（即，常量）内存解决此问题吗？

 

示例 1：



输入：head = [3,2,0,-4], pos = 1  
输出：true  
解释：链表中有一个环，其尾部连接到第二个节点。  
示例 2：  



输入：head = [1,2], pos = 0  
输出：true  
解释：链表中有一个环，其尾部连接到第一个节点。  
示例 3：  



输入：head = [1], pos = -1  
输出：false  
解释：链表中没有环。  
 

提示：

链表中节点的数目范围是 [0, 104]  
-105 <= Node.val <= 105  
pos 为 -1 或者链表中的一个 有效索引 。  

## 理解
* 解法一：使用快慢指针，空间复杂度控制在o(1)
* 解法二：借助Set的特性，遍历节点。空间复杂度为o(N)

## 解法一
### 代码
```java
public class LeetCode_141_1_环形链表 {
    public boolean hasCycle(ListNode head) {
        if (head == null || head.next == null) {
            return false;
        }
        ListNode second = head;
        while(true) {
            if (head == null || second == null || second.next == null) {
                return false;
            }
            head = head.next;
            second = second.next.next;
            if (head == second) {
                return true;
            }
        }
    }
}
```
## 解法二
### 代码
```java
public class LeetCode_141_2_环形链表 {
    public boolean hasCycle(ListNode head) {
        if (head == null || head.next == null) {
            return false;
        }
        Set<ListNode> set = new HashSet<>();
        while (head != null) {
            if (!set.add(head)) {
                return true;
            }
            head = head.next;
        }
        return false;
    }
}
```
# [LeetCode_83_1_删除排序链表中的重复元素](https://leetcode-cn.com/problems/remove-duplicates-from-sorted-list/)
## 题目
给定一个排序链表，删除所有重复的元素，使得每个元素只出现一次。

示例 1:

输入: 1->1->2  
输出: 1->2  
示例 2:  

输入: 1->1->2->3->3  
输出: 1->2->3  

## 理解
需要注意是一个排序链表，则重复元素只可能出现在相邻位置。

### 代码
```java
public class LeetCode_83_1_删除排序链表中的重复元素 {
    public ListNode deleteDuplicates(ListNode head) {
        ListNode node = head;
        while (node != null && node.next != null) {
            if (node.next.val == node.val) {
                node.next = node.next.next;
            } else {
                node = node.next;
            }
        }
        return head;
    }
}
```
# [LeetCode_876_1_链表的中间结点](https://leetcode-cn.com/problems/middle-of-the-linked-list/)
## 题目
给定一个头结点为 head 的非空单链表，返回链表的中间结点。

如果有两个中间结点，则返回第二个中间结点。

 

示例 1：
```
输入：[1,2,3,4,5]
输出：此列表中的结点 3 (序列化形式：[3,4,5])
返回的结点值为 3 。 (测评系统对该结点序列化表述是 [3,4,5])。
注意，我们返回了一个 ListNode 类型的对象 ans，这样：
ans.val = 3, ans.next.val = 4, ans.next.next.val = 5, 以及 ans.next.next.next = NULL.
```

示例 2：
```
输入：[1,2,3,4,5,6]
输出：此列表中的结点 4 (序列化形式：[4,5,6])
由于该列表有两个中间结点，值分别为 3 和 4，我们返回第二个结点。
```

提示：

给定链表的结点数介于 1 和 100 之间。

## 理解
通过遍历链表求取链表长度，长度/2并向下取整，求得处理头节点次数，最后处理头节点。

### 代码
```java
public class LeetCode_876_1_链表的中间结点 {

    public ListNode middleNode(ListNode head) {
        int size = 0;
        ListNode node = head;
        while(node != null) {
            node = node.next;
            size++;
        }
        int mid = (int)Math.floor(size / 2.0);
        while (mid != 0) {
            head = head.next;
            mid--;
        }
        return head;
    }
}
```

# [LeetCode_203_1_移除链表元素](https://leetcode-cn.com/problems/remove-linked-list-elements/)
## 题目
删除链表中等于给定值 val 的所有节点。

示例:
```
输入: 1->2->6->3->4->5->6, val = 6  
输出: 1->2->3->4->5
```

## 理解
通过虚拟头结点的方式统一处理逻辑，遍历链表，去除指定值。

### 代码
```java
public class LeetCode_203_1_移除链表元素 {
    public ListNode removeElements(ListNode head, int val) {
        ListNode node = new ListNode(0, head);
        ListNode curr = head;
        ListNode pre = node;
        while(curr != null) {
            if (curr.val == val) {
               pre.next = curr.next;
            } else {
                pre = curr;
            }
            curr = curr.next;
        }
        return node.next;
    }
}
```