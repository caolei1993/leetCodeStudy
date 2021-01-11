package main.java.com.exercise.week_001;

/**
 * @Author cl
 * @Date 2021/1/9 17:32
 * @Version 1.0
 * https://leetcode-cn.com/problems/validate-binary-search-tree/comments/
 */
public class LeetCode_98_1_验证二叉搜索树 {
    public boolean isValidBST(TreeNode root) {
       return recurse(root, null, null);
    }

    boolean recurse(TreeNode node, Integer lower, Integer upper) {
        if (node == null) {
            return true;
        }
        if (lower != null && node.val <= lower) {
            return false;
        }
        if (upper != null && node.val >= upper) {
            return false;
        }
        if (!recurse(node.left, lower, node.val)) {
            return false;
        }
        if (!recurse(node.right, node.val, upper)) {
            return false;
        }
        // 只有根节点
        return true;
    }
}
