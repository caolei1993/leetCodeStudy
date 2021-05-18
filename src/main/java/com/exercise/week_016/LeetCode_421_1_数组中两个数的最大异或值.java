package main.java.com.exercise.week_016;

/**
 * @author cl
 * @version 1.0
 * @date 2021/5/17 20:44
 */
public class LeetCode_421_1_数组中两个数的最大异或值 {

    static class Node {
        Node[] nds = new Node[2];
    }
    Node root = new Node();

    private void add (int x) {
        Node p = root;
        for (int i = 31; i >= 0 ; i--) {
            int a = (x >> i) & 1;
            if (p.nds[a] == null) {
                p.nds[a] = new Node();
            }
            p = p.nds[a];
        }
    }

    private int getVal(int x) {
        Node p = root;
        int ans = 0;
        for (int i = 31; i >= 0 ; i--) {
            int a = (x >> i) & 1, b = 1 - a;
            if (p.nds[b] != null) {
                ans |= b << i;
                p = p.nds[b];
            } else {
                ans |= a << i;
                p = p.nds[a];
            }
        }
        return ans;
    }
    public int findMaximumXOR(int[] nums) {
        int ans = 0;
        for (int i : nums) {
            add(i);
            int j = getVal(i);
            ans = Math.max(ans, i ^ j);
        }
        return ans;
    }
}
