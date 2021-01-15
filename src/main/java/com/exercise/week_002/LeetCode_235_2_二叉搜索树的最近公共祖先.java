package main.java.com.exercise.week_002;

import java.util.List;

/**
 * @Author cl
 * @Date 2021/1/14 9:21
 * @Version 1.0
 */
public class LeetCode_235_2_二叉搜索树的最近公共祖先 {
    public TreeNode lowestCommonAncestor(TreeNode root, TreeNode p, TreeNode q) {
        if (root == null) {
            return null;
        }
        if (root.val > p.val && root.val > q.val) {
            return lowestCommonAncestor(root.left, p, q);
        } else if (root.val < p.val && root.val < q.val) {
            return lowestCommonAncestor(root.right, p, q);
        } else{
            // pq分别在左右节点中或pq中一个就是root节点
            return root;
        }
    }
}
