package main.java.com.exercise.week_001;

/**
 * @Author cl
 * @Date 2021/1/9 17:17
 * @Version 1.0
 * 迭代
 */
public class LeetCode_700_2_二叉搜索树中的搜索 {
    public TreeNode searchBST(TreeNode root, int val) {
        while (true) {
            if (root == null || root.val == val) {
                return root;
            }
            root = root.val > val ? root.left : root.right;
        }
    }
}
