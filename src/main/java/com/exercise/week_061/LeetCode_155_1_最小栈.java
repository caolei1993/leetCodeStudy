package main.java.com.exercise.week_061;

import java.util.ArrayList;
import java.util.Collections;
import java.util.List;

/**
 * @author cl
 * @version 1.0
 * @description： TODO
 * @date 2024/2/29 17:54
 */
public class LeetCode_155_1_最小栈 {
    List<Integer> list;
    public LeetCode_155_1_最小栈() {
        list = new ArrayList<>();
    }

    public void push(int val) {
        list.add(val);
    }

    public void pop() {
        list.remove(list.size() - 1);
    }

    public int top() {
        return list.get(list.size() - 1);
    }

    public int getMin() {
        List<Integer> l = new ArrayList<>(list);
        Collections.sort(l);
        return l.get(0);
    }
}
