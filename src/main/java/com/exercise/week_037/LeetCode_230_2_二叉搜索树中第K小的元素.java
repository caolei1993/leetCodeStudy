package main.java.com.exercise.week_037;

/**
 * @Author cl
 * @Date 2021/10/18 10:15
 * @Version 1.0
 */
public class LeetCode_230_2_二叉搜索树中第K小的元素 {
    int k_;
    int ans;
    boolean flag = false;
    public int kthSmallest(TreeNode root, int k) {
        int k_ = k;
        inorder(root);
        return ans;
    }

    public void inorder(TreeNode node) {
        if (node == null || flag) {
            return;
        }
        inorder(node.left);
        if (k_ == 0) {
            ans = node.val;
            flag = true;
        } else {
            k_--;
        }
        inorder(node.right);
    }
}
