package main.java.com.exercise.week_002;

import java.util.Stack;

/**
 * @Author cl
 * @Date 2021/1/11 16:53
 * @Version 1.0
 */
public class LeetCode_530_2_二叉搜索树的最小绝对差 {
    int pre = -1;
    int ans = Integer.MAX_VALUE;
    public int getMinimumDifference(TreeNode root) {
        inorder(root);
        return ans;
    }

    /**
     * 中序遍历
     * @param root
     */
    public void inorder(TreeNode root) {
        TreeNode node = root;
        Stack<TreeNode> stack = new Stack<>();
        while (true) {
            if (node != null) {
                stack.push(node);
                node = node.left;
            } else {
                if (stack.empty()) {
                    break;
                }
                node = stack.pop();
                if (pre == -1) {
                    pre = node.val;
                } else {
                    ans = Math.min(ans, node.val - pre);
                    pre = node.val;
                }
                node = node.right;
            }
        }
    }
}
