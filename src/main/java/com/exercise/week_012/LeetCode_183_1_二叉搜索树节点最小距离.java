package main.java.com.exercise.week_012;

/**
 * @Author cl
 * @Date 2021/4/13 9:13
 * @Version 1.0
 * https://leetcode-cn.com/problems/minimum-distance-between-bst-nodes/
 */
public class LeetCode_183_1_二叉搜索树节点最小距离 {
    int ans = Integer.MAX_VALUE;
    TreeNode pre = null;
    public int minDiffInBST(TreeNode root) {
        inOrder(root);
        return ans;
    }

    private void inOrder(TreeNode node) {
        if (node == null) {
            return;
        }
        inOrder(node.left);
        if (pre != null) {
            ans = Math.min(ans, node.val - pre.val);
        }
        pre = node;
        inOrder(node.right);
    }
}

