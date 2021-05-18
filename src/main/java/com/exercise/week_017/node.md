[toc]
# [LeetCode_993_1_二叉树的堂兄弟节点](https://leetcode-cn.com/problems/cousins-in-binary-tree/)
## 题目
在二叉树中，根节点位于深度 0 处，每个深度为 k 的节点的子节点位于深度 k+1 处。

如果二叉树的两个节点深度相同，但 父节点不同 ，则它们是一对堂兄弟节点。

我们给出了具有唯一值的二叉树的根节点 root ，以及树中两个不同节点的值 x 和 y 。

只有与值 x 和 y 对应的节点是堂兄弟节点时，才返回 true 。否则，返回 false。

 

示例 1：
```
输入：root = [1,2,3,4], x = 4, y = 3
输出：false
```


示例 2：
```
输入：root = [1,2,3,null,4,null,5], x = 5, y = 4
输出：true
```


示例 3：
```
输入：root = [1,2,3,null,4], x = 2, y = 3
输出：false
```

提示：

* 二叉树的节点数介于 2 到 100 之间。
* 每个节点的值都是唯一的、范围为 1 到 100 的整数。

## 理解
利用层序遍历，遍历过程中判断子节点的值是否与x和y相同，如果相同记录下标记位flag，如果
x和y同时为某个父节点的左右子节点则返回false，否则遍历完某一层后，查看本层的子元素是否
包含目标值x,y，同时包含则为堂兄弟节点。

### 代码
```java
public class LeetCode_993_1_二叉树的堂兄弟节点 {
    public boolean isCousins(TreeNode root, int x, int y) {
        Queue<TreeNode> queue = new LinkedList<>();
        queue.offer(root);
        // 记录每一层的元素个数
        int size = 1;
        boolean flag = false;
        List<Integer> list = new ArrayList<>();
        while (!queue.isEmpty()) {
            TreeNode node = queue.poll();
            list.add(node.val);
            size--;
            TreeNode left = node.left;
            TreeNode right = node.right;
            if (left != null) {
                queue.offer(left);
                if (left.val == x || left.val == y) {
                    flag = true;
                }
            }
            if (right != null) {
                queue.offer(right);
                if (flag) {
                    if (right.val == x || right.val == y) {
                        return false;
                    }
                }
            }
            flag = false;
            if (size == 0) {
                size = queue.size();
                if (list.contains(x) && list.contains(y)) {
                    return true;
                }
                list = new ArrayList<>();
            }
        }
        return false;
    }
}
```