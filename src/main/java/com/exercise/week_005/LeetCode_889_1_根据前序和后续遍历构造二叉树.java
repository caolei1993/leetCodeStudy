package main.java.com.exercise.week_005;

import java.util.HashMap;
import java.util.Map;

/**
 * @Author cl
 * @Date 2021/2/3 7:49
 * @Version 1.0
 * https://leetcode-cn.com/problems/construct-binary-tree-from-preorder-and-postorder-traversal/
 * 根据前序遍历和后续遍历构造二叉树结果不唯一，如果再加上是真二叉树，节点度为0或度为2（结果就唯一）
 */
public class LeetCode_889_1_根据前序和后续遍历构造二叉树 {
    Map<Integer, Integer> map = new HashMap<>();
    public TreeNode constructFromPrePost(int[] pre, int[] post) {
        for (int i=0; i<post.length; i++) {
            map.put(post[i], i);
        }

        return buildTree(pre, post, 0, pre.length-1, 0, post.length-1);
    }

    public TreeNode buildTree(int[] pre, int[] post, int preLeft, int preRight, int postLeft, int postRight) {
        if (preLeft > preRight) {
            return null;
        }
        TreeNode root = new TreeNode(pre[preLeft]);
        if (preLeft == preRight) {
            return root;
        }
        int leftLength = map.get(pre[preLeft+1]) - postLeft + 1;
        root.left = buildTree(pre, post, preLeft+1, preLeft+leftLength,  postLeft, postLeft+leftLength-1);
        root.right = buildTree(pre, post, preLeft+leftLength+1, preRight, postLeft+leftLength, postRight-1);
        return root;
    }
}
