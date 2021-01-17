package main.java.com.exercise.week_002;

/**
 * @Author cl
 * @Date 2021/1/15 17:57
 * @Version 1.0
 */
public class LeetCode_230_2_二叉搜索树中第K小的元素 {
    private int index = 1;
    private int ans = 0;
    private boolean flag = false;
    public int kthSmallest(TreeNode root, int k) {
        inorder(root, k);
        return ans;
    }

    public void inorder(TreeNode root, int k) {
        if (root == null || flag) {
            return;
        }
        inorder(root.left, k);
        if (flag) {
            return;
        }
        if (k == index) {
            ans = root.val;
            flag = true;
        } else {
            index += 1;
        }
        inorder(root.right, k);
    }
}
