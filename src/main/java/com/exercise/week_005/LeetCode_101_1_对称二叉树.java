package main.java.com.exercise.week_005;

import java.util.LinkedList;
import java.util.Queue;

/**
 * @Author cl
 * @Date 2021/2/3 21:21
 * @Version 1.0
 * https://leetcode-cn.com/problems/symmetric-tree/
 */
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
