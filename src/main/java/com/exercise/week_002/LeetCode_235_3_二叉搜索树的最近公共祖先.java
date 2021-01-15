package main.java.com.exercise.week_002;

/**
 * @Author cl
 * @Date 2021/1/14 9:36
 * @Version 1.0
 */
public class LeetCode_235_3_二叉搜索树的最近公共祖先 {
    public TreeNode lowestCommonAncestor(TreeNode root, TreeNode p, TreeNode q) {
        TreeNode node = root;
        while (true) {
            if (node.val > p.val && node.val > q.val) {
                node = node.left;
            } else if (node.val < p.val && node.val < q.val) {
                node = node.right;
            } else{
                // pq分别在左右节点中或pq中一个就是node节点
                break;
            }
        }
        return node;
    }
}
