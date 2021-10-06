package main.java.com.exercise.week_036;

import java.util.Iterator;

/**
 * @Author cl
 * @Date 2021/10/6 18:01
 * @Version 1.0
 */
public class LeetCode_284_1_顶端迭代器 implements Iterator<Integer> {
    Iterator<Integer> iter;
    Integer next;

    public LeetCode_284_1_顶端迭代器(Iterator<Integer> iterator) {
        // initialize any member here.
        iter = iterator;
        if (iter.hasNext()) {
            next = iter.next();
        }
    }

    // Returns the next element in the iteration without advancing the iterator.
    public Integer peek() {
        return next;
    }

    // hasNext() and next() should behave the same as in the Iterator interface.
    // Override them if needed.
    @Override
    public Integer next() {
        int ans = next;
        next = iter.hasNext() ? iter.next() : null;
        return ans;
    }

    @Override
    public boolean hasNext() {
        return next != null;
    }
}
