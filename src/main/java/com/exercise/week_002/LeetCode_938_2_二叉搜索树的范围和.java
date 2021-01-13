package main.java.com.exercise.week_002;

import java.util.Stack;

/**
 * @Author cl
 * @Date 2021/1/13 17:39
 * @Version 1.0
 */
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
