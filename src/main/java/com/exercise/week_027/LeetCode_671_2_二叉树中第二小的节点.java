package main.java.com.exercise.week_027;

/**
 * @Author cl
 * @Date 2021/7/27 15:37
 * @Version 1.0
 */
public class LeetCode_671_2_二叉树中第二小的节点 {
    int ans = -1;
    public int findSecondMinimumValue(TreeNode root) {
        dfs(root, root.val);
        return ans;
    }

    private void dfs(TreeNode node, int cur) {
        if (node == null) {
            return;
        }
        if (node.val != cur) {
            if (ans == -1) {
                ans = node.val;
            } else {
                ans = Math.min(ans, node.val);
            }
        }
        dfs(node.left, cur);
        dfs(node.right, cur);
    }
}
