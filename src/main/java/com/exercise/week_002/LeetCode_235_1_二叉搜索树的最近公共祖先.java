package main.java.com.exercise.week_002;

import java.util.ArrayList;
import java.util.List;

/**
 * @Author cl
 * @Date 2021/1/13 18:08
 * @Version 1.0
 */
public class LeetCode_235_1_二叉搜索树的最近公共祖先 {
    public TreeNode lowestCommonAncestor(TreeNode root, TreeNode p, TreeNode q) {
        List<TreeNode> parentP = getParentValueList(root, p);
        List<TreeNode> parentQ = getParentValueList(root, q);
        for (int i = parentP.size()-1; i >= 0 ; i--) {
            if (parentQ.contains(parentP.get(i))) {
                return parentP.get(i);
            }
        }
        return null;
    }
    public List<TreeNode> getParentValueList(TreeNode root, TreeNode node) {
        List<TreeNode> list = new ArrayList<>();
        while (root.val != node.val) {
            if (root.val < node.val) {
                list.add(root);
                root = root.right;
            } else {
                list.add(root);
                root = root.left;
            }
        }
        list.add(root);
        return list;
    }
}
