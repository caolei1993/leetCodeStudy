package main.java.com.exercise.week_004;

import java.util.List;

/**
 * @Author cl
 * @Date 2021/1/27 21:13
 * @Version 1.0
 */
public class Node {
    public int val;
    public List<Node> children;

    public Node() {}

    public Node(int _val) {
        val = _val;
    }

    public Node(int _val, List<Node> _children) {
        val = _val;
        children = _children;
    }
}
