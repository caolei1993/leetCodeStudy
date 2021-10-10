package main.java.com.exercise.week_035;

/**
 * @Author cl
 * @Date 2021/9/28 9:19
 * @Version 1.0
 */
public class LeetCode_437_1_路径总和III {

    int ans;
    int target;

    public int pathSum(TreeNode root, int targetSum) {
        target = targetSum;
        dfs1(root);
        return ans;
    }

    private void dfs1(TreeNode node) {
        if (node == null) {
            return;
        }
        dfs2(node, node.val);
        dfs1(node.left);
        dfs1(node.right);
    }

    private void dfs2(TreeNode node, int val) {
        if (val == target) {
            ans++;
        }
        if (node.left != null) {
            dfs2(node.left, val + node.left.val);
        }
        if (node.right != null) {
            dfs2(node.right, val + node.right.val);
        }
    }
}
