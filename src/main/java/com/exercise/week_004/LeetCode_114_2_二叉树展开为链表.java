package main.java.com.exercise.week_004;

import java.util.Stack;

/**
 * @Author cl
 * @Date 2021/1/31 15:03
 * @Version 1.0
 */
public class LeetCode_114_2_二叉树展开为链表 {
    public void flatten(TreeNode root) {
        if (root == null) {
            return;
        }
        Stack<TreeNode> stack = new Stack<>();
        stack.push(root);
        TreeNode pre = null;
        while (!stack.isEmpty()) {
            TreeNode node = stack.pop();
            if (pre != null) {
                pre.left = null;
                pre.right = node;
            }
            if (node.right != null) {
                stack.push(node.right);
            }
            if (node.left != null) {
                stack.push(node.left);
            }
            pre = node;
        }
    }
}
