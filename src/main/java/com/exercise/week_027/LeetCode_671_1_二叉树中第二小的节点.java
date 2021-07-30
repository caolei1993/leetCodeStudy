package main.java.com.exercise.week_027;

/**
 * @Author cl
 * @Date 2021/7/27 14:51
 * @Version 1.0
 */
public class LeetCode_671_1_二叉树中第二小的节点 {
    public int findSecondMinimumValue(TreeNode root) {
        // 根节点为null，或无子节点（题目要求子节点的数量只可能为2或0）
        if (root == null || root.left == null) {
            return -1;
        }
        // 两个子节点
        if (root.left.val == root.right.val) {
            int a = findSecondMinimumValue(root.left);
            int b = findSecondMinimumValue(root.right);
            if (a != -1 && b != -1) {
                return Math.min(a, b);
            } else {
                return Math.max(a, b);
            }

        }
        if (root.val == root.left.val) {
            int v = findSecondMinimumValue(root.left);
            if (v != -1) {
                return Math.min(v, root.right.val);
            }
            return root.right.val;
        } else {
            int v = findSecondMinimumValue(root.right);
            if (v != -1) {
                return Math.min(v, root.left.val);
            }
            return root.left.val;
        }
    }
}
