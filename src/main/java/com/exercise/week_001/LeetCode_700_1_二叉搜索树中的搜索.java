package main.java.com.exercise.week_001;

/**
 * @Author cl
 * @Date 2021/1/8 14:33
 * @Version 1.0
 */
public class LeetCode_700_1_二叉搜索树中的搜索 {
    public TreeNode searchBST(TreeNode root, int val) {
        // 考察二叉树的查找，及递归
        if (root == null) {
            return root;
        }
        if (root.val > val) {
            return  searchBST(root.left, val);
        }
        if (root.val < val) {
            return  searchBST(root.right, val);
        }
        return root;
    }
}
