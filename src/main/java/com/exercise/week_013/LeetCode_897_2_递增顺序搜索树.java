package main.java.com.exercise.week_013;

/**
 * @Author cl
 * @Date 2021/4/25 9:49
 * @Version 1.0
 */
public class LeetCode_897_2_递增顺序搜索树 {
    TreeNode head = new TreeNode(-1);
    TreeNode pre = head;
    public TreeNode increasingBST(TreeNode root) {
        inorder(root);
        return head.right;
    }

    public void inorder(TreeNode node) {
        if (node == null) {
            return;
        }
        inorder(node.left);
        pre.left = null;
        pre.right = node;
        pre = node;
        inorder(node.right);
    }
}
