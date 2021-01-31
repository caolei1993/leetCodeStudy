package main.java.com.exercise.week_004;

import java.util.LinkedList;
import java.util.Queue;

/**
 * @Author cl
 * @Date 2021/1/26 20:34
 * @Version 1.0
 * https://leetcode-cn.com/problems/maximum-width-of-binary-tree/
 */
public class LeetCode_662_1_二叉树最大宽度 {
    public int widthOfBinaryTree(TreeNode root) {
        int ans = 0;
        if (root == null) {
            return ans;
        }
        Queue<TreeNode> queue = new LinkedList<>();
        queue.offer(root);
        // 每层真正元素个树，初始化第一层一个
        int size = 1;
        // 答案，root非null，刚开始为1，根节点个数
        ans = size;
        // 每层最左到最右中间的的元素个数（包含null）
        int count = 0;
        // 临时记录某层，某个元素后null的个数
        int nullCount = 0;
        while (!queue.isEmpty()) {
            TreeNode node = queue.poll();
            size--;
            if (node.left != null) {
                queue.offer(node.left);
                count++;
            } else {
                if (queue.size() > size) {
                    nullCount++;
                }
            }
            if (node.right != null) {
                queue.offer(node.right);
                count++;
                count += nullCount;
                nullCount = 0;
            } else {
                nullCount++;
            }
            if (size == 0) {
                size = queue.size();
                nullCount = 0;
                ans = Math.max(ans, count);
            }
        }
        return ans;
    }
}
