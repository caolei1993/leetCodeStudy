package main.java.com.exercise.week_002;

/**
 * @Author cl
 * @Date 2021/1/11 17:13
 * @Version 1.0
 */
public class LeetCode_530_3_二叉搜索树的最小绝对差 {
    private int pre = -1;
    private int ans = Integer.MAX_VALUE;
    public int getMinimumDifference(TreeNode root) {
        inorder(root);
        return ans;
    }

    /**
     * 中序遍历
     * @param root
     */
    public void inorder(TreeNode root) {
        if (root == null) {
            return;
        }
        inorder(root.left);
        if (pre != -1) {
            ans = Integer.min(ans, root.val - pre);
        }
        pre = root.val;
        inorder(root.right);
    }
}
