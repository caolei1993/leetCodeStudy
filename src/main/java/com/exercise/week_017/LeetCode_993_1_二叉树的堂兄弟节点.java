package main.java.com.exercise.week_017;

import java.util.ArrayList;
import java.util.LinkedList;
import java.util.List;
import java.util.Queue;

/**
 * @author cl
 * @version 1.0
 * @date 2021/5/17 20:16
 */
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
