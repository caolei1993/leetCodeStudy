package main.java.com.exercise.week_002;

/**
 * @Author cl
 * @Date 2021/1/13 17:15
 * @Version 1.0
 * https://leetcode-cn.com/problems/range-sum-of-bst/
 */
public class LeetCode_938_1_二叉搜索树的范围和 {
    private int sum = 0;
    private int low;
    private int high;
    public int rangeSumBST(TreeNode root, int low, int high) {
        this.low = low;
        this.high = high;
        inorder(root);
        return sum;
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
       if (root.val >= low && root.val <= high) {
           sum += root.val;
       }
       inorder(root.right);
    }
}
