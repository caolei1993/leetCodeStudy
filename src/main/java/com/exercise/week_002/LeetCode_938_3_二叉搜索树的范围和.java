package main.java.com.exercise.week_002;

/**
 * @Author cl
 * @Date 2021/1/13 17:53
 * @Version 1.0
 */
public class LeetCode_938_3_二叉搜索树的范围和 {
    public int rangeSumBST(TreeNode root, int low, int high) {
        if (root == null) {
            return 0;
        }
        if (root.val >= low && root.val <= high) {
            return root.val + rangeSumBST(root.left, low, high) + rangeSumBST(root.right, low, high);
        } else if(root.val < low) {
            return rangeSumBST(root.right, low, high);
        } else {
            return rangeSumBST(root.left, low, high);
        }
    }
}
