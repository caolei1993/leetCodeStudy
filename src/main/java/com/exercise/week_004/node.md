[toc]
# [LeetCode_107_1_二叉树的层序遍历II](https://leetcode-cn.com/problems/binary-tree-level-order-traversal-ii/)
## 题目
给定一个二叉树，返回其节点值自底向上的层序遍历。 （即按从叶子节点所在层到根节点所在的层，逐层从左向右遍历）

**例如：**  
给定二叉树 [3,9,20,null,null,15,7],
```
    3
   / \
  9  20
    /  \
   15   7
```

返回其自底向上的层序遍历为：
```
[
  [15,7],
  [9,20],
  [3]
]
```

## 理解
* 解法一：使用queue对二叉树进行层序遍历，遍历完成后将结果翻转

## 解法一
### 代码
```java
public class LeetCode_107_1_二叉树的层序遍历II {
    public List<List<Integer>> levelOrderBottom(TreeNode root) {
        List<List<Integer>> list = new ArrayList<>();
        List<Integer> levelList = new ArrayList<>();
        Queue<TreeNode> queue = new LinkedList<>();
        if (root == null) {
            return list;
        }
        int size = 1;
        queue.offer(root);
        while (!queue.isEmpty()) {
            TreeNode node = queue.poll();
            levelList.add(node.val);
            size--;
            if (node.left != null) {
                queue.offer(node.left);
            }
            if (node.right != null) {
                queue.offer(node.right);
            }
            if (size == 0) {
                list.add(levelList);
                levelList = new ArrayList<>();
                size = queue.size();
            }
        }
        Collections.reverse(list);
        return list;
    }
}

```

# [LeetCode_662_1_二叉树最大宽度](https://leetcode-cn.com/problems/maximum-width-of-binary-tree/)
## 题目
给定一个二叉树，编写一个函数来获取这个树的最大宽度。树的宽度是所有层中的最大宽度。这个二叉树与满二叉树（full binary tree）结构相同，但一些节点为空。

每一层的宽度被定义为两个端点（该层最左和最右的非空节点，两端点间的null节点也计入长度）之间的长度。

**示例 1:**

输入: 
```
           1
         /   \
        3     2
       / \     \  
      5   3     9 

```

输出: 4  
解释: 最大值出现在树的第 3 层，宽度为 4 (5,3,null,9)。  
**示例 2:**

输入: 
```
          1
         /  
        3    
       / \       
      5   3     

```


输出: 2  
解释: 最大值出现在树的第 3 层，宽度为 2 (5,3)。  
**示例 3:**

输入: 
```
          1
         / \
        3   2 
       /        
      5   
```
   

输出: 2  
解释: 最大值出现在树的第 2 层，宽度为 2 (3,2)。  
**示例 4:**

输入: 
```
          1
         / \
        3   2
       /     \  
      5       9 
     /         \
    6           7
```

输出: 8  
解释: 最大值出现在树的第 4 层，宽度为 8 (6,null,null,null,null,null,null,7)。  
注意: 答案在32位有符号整数的表示范围内。

## 理解
* 解法一，错误勿看。
* 解法二，创建新的节点对象有位置及深度属性，层序遍历每一个节点，重点是每个节点左子节点的位置属性为该节点
的位置*2，右子节点位置属性为该节点的位置属性*2+1，记录每层最左边节点的位置，依次求宽度（宽度为node.position - left.position + 1)
然后每次取ans与计算结果的最大值保存，直至所有节点遍历完成。
* 解法三，利用深度优先搜索，前序遍历（递归），重点还是遍历函数需要记录深度及位置，左子节点的位置为该节点的位置*2，
右子节点的位置为该节点的位置*2+1，深度+1，利用map记录每层最左边的节点的位置，key为深度，value为值，
遍历节点的同时计算宽度，并求ans与计算值的最大值赋值给ans，直至遍历完成。

## 解法一
### 代码
```java
public class LeetCode_662_1_二叉树最大宽度 {
    public int widthOfBinaryTree(TreeNode root) {
        int ans = 0;
        if (root == null) {
            return ans;
        }
        Queue<TreeNode> queue = new LinkedList<>();
        queue.offer(root);
        // 每层真正元素个树，初始化第一层一个
        int size = 1;
        // 答案，root非null，刚开始为1，根节点个数
        ans = size;
        // 每层最左到最右中间的的元素个数（包含null）
        int count = 0;
        // 临时记录某层，某个元素后null的个数
        int nullCount = 0;
        while (!queue.isEmpty()) {
            TreeNode node = queue.poll();
            size--;
            if (node.left != null) {
                queue.offer(node.left);
                count++;
            } else {
                if (queue.size() > size) {
                    nullCount++;
                }
            }
            if (node.right != null) {
                queue.offer(node.right);
                count++;
                count += nullCount;
                nullCount = 0;
            } else {
                nullCount++;
            }
            if (size == 0) {
                size = queue.size();
                nullCount = 0;
                ans = Math.max(ans, count);
            }
        }
        return ans;
    }
}
```
## 解法二
### 代码
```java
public class LeetCode_662_2_二叉树最大宽度 {
    public int widthOfBinaryTree(TreeNode root) {
        int ans = 0;
        int currentDepth = 0;
        int left = 0;
        Queue<NewTreeNode> queue = new LinkedList<>();
        queue.offer(new NewTreeNode(root, 0 ,0));
        while (!queue.isEmpty()) {
            NewTreeNode node = queue.poll();
            if (node.node != null) {
                queue.offer(new NewTreeNode(node.node.left, node.depth + 1, node.position * 2 ));
                queue.offer(new NewTreeNode(node.node.right, node.depth + 1, node.position * 2 + 1 ));
                if (currentDepth != node.depth) {
                    currentDepth = node.depth;
                    left = node.position;
                }
                ans = Math.max(ans, node.position - left + 1);
            }
        }
        return ans;
    }

    public static class NewTreeNode {
        int depth, position;
        TreeNode node;
        public NewTreeNode (TreeNode node, int depth, int position) {
            this.node = node;
            this.depth = depth;
            this.position = position;
        }
    }
}
```
## 解法三
### 代码
```java
public class LeetCode_662_3_二叉树最大宽度 {
    int ans = 0;
    Map<Integer, Integer> map = new HashMap<>();
    public int widthOfBinaryTree(TreeNode root) {
        dfs(root, 0 ,0);
        return ans;
    }
    public void dfs (TreeNode node, int depth, int position) {
        if (node == null) {
            return;
        }
        map.computeIfAbsent(depth, x -> position);
        ans = Math.max(ans, position - map.get(depth) + 1);
        dfs(node.left, depth + 1, position * 2);
        dfs(node.right, depth +1, position * 2 + 1);
    }
}
```
# [LeetCode_589_1_N叉树的前序遍历](https://leetcode-cn.com/problems/n-ary-tree-preorder-traversal/)
## 题目
给定一个 N 叉树，返回其节点值的前序遍历。

例如，给定一个 3叉树 :

 



 

返回其前序遍历: [1,3,5,6,2,4]。

## 理解
* 解法一：使用递归法，前序遍历N叉树，遵守先处理根节点，再依次处理子节点。
* 解法二：使用迭代法，前序遍历N叉树，使用栈辅助遍历，遵守处理先处理根节点，再将子节点从右向左依次压入
栈，因为栈有后进先出的特性，所以从右向左压栈是为了遵循从左向右处理的顺序。

## 解法一
### 代码
```java
public class LeetCode_589_1_N叉树的前序遍历 {
    List<Integer> list = new ArrayList<>();
    public List<Integer> preorder(Node root) {
        perorder(root);
        return list;
    }
    public void perorder (Node node) {
        if (node == null) {
            return;
        }
        list.add(node.val);
        for (Node node1: node.children) {
            preorder(node1);
        }
    }
}
```
## 解法二
### 代码
```java
public class LeetCode_589_2_N叉树的前序遍历 {
    List<Integer> list = new ArrayList<>();
    public List<Integer> preorder(Node root) {
        if (root == null) {
            return list;
        }
        Stack<Node> stack = new Stack<>();
        stack.push(root);
        while (!stack.isEmpty()) {
            Node node = stack.pop();
            list.add(node.val);
            Collections.reverse(node.children);
            for (Node node1: node.children) {
                stack.push(node);
            }
        }
        return list;
    }
}
```

# [LeetCode_590_1_N叉树的后序遍历](https://leetcode-cn.com/problems/n-ary-tree-postorder-traversal/)
## 题目
给定一个 N 叉树，返回其节点值的后序遍历。

例如，给定一个 3叉树 :

 



 

返回其后序遍历: [5,6,3,2,4,1].

说明: 递归法很简单，你可以使用迭代法完成此题吗?

## 理解
* 解法一：使用递归法，完成N叉树的后序遍历
* 解法二：使用迭代法，完成N叉树的后序遍历，借助两个栈结构，使用将中右左结构翻转为左右中的思想。

## 解法一
### 代码
```java
public class LeetCode_590_1_N叉树的后序遍历 {
    List<Integer> list = new ArrayList<>();
    public List<Integer> postorder(Node root) {
        nodePostorder(root);
        return list;
    }

    public void nodePostorder(Node node) {
        if (node == null) {
            return;
        }
        for (Node child : node.children) {
            nodePostorder(child);
        }
        list.add(node.val);
    }

}

```
## 解法二
### 代码
```java
public class LeetCode_590_2_N叉树的后序遍历 {
    List<Integer> list = new ArrayList<>();
    public List<Integer> postorder(Node root) {
        Stack<Node> stack = new Stack<>();
        Stack<Node> stack1 = new Stack<>();
        if (root == null) {
            return list;
        }
        stack.push(root);
        while (!stack.isEmpty()) {
            Node node = stack.pop();
            stack1.push(node);
            for (Node child : node.children) {
                stack.push(child);
            }
        }
        while (!stack1.isEmpty()) {
            Node node = stack1.pop();
            list.add(node.val);
        }
        return list;
    }
}

```
