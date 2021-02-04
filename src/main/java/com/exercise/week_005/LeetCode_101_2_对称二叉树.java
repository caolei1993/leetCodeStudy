package main.java.com.exercise.week_005;

/**
 * @Author cl
 * @Date 2021/2/3 21:32
 * @Version 1.0
 */
public class LeetCode_101_2_对称二叉树 {
    public boolean isSymmetric(TreeNode root) {
        if (root == null) {
            return true;
        } else {
            return check(root.left, root.right);
        }
    }

    private boolean check(TreeNode left, TreeNode right) {
        if (left == null && right == null) {
            return true;
        }
        if (left == null || right == null) {
            return false;
        }
        return left.val == right.val && check(left.right, right.left) && check(left.left, right.right);
    }
}
