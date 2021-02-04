package main.java.com.exercise.week_005;

import java.util.HashMap;
import java.util.Map;

/**
 * @Author cl
 * @Date 2021/2/3 18:12
 * @Version 1.0
 */
public class LeetCode_889_2_根据前序和后序遍历构造二叉树 {
    Map<Integer, Integer> map = new HashMap<>();
    public TreeNode constructFromPrePost(int[] pre, int[] post) {
        for (int i=0; i<post.length; i++) {
            map.put(post[i], i);
        }

        return buildTree(pre, post, 0, 0, post.length);
    }

    /**
     *
     * @param pre
     * @param post
     * @param preIndex 前序遍历首节点的坐标
     * @param postIndex 后序遍历首节点的坐标
     * @param size 长度
     * @return
     */
    public TreeNode buildTree(int[] pre, int[] post, int preIndex, int postIndex, int size) {
        if (size == 0) {
            return null;
        }
        TreeNode root = new TreeNode(pre[preIndex]);
        if (size == 1) {
            return root;
        }
        // 前序遍历根节点后默认认为是左节点
        int leftLength = map.get(pre[preIndex+1]) - postIndex + 1;
        root.left = buildTree(pre, post, preIndex+1, postIndex,  leftLength);
        root.right = buildTree(pre, post, preIndex+leftLength+1, postIndex+leftLength, size-leftLength-1);
        return root;
    }
}
