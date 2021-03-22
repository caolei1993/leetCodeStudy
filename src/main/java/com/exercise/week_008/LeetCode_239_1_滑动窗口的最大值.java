package main.java.com.exercise.week_008;

import java.util.Deque;
import java.util.LinkedList;

/**
 * @Author cl
 * @Date 2021/3/8 17:12
 * @Version 1.0
 */
public class LeetCode_239_1_滑动窗口的最大值 {
    public int[] maxSlidingWindow(int[] nums, int k) {
        // 创建存放下标的双端队列
        Deque<Integer> deque = new LinkedList<>();
        // 先将第一个窗口的元素按规则入队
        for (int i = 0; i < k; i++) {
            while (!deque.isEmpty() && nums[i] >= nums[deque.peekLast()]) {
                deque.pollLast();
            }
            deque.offerLast(i);
        }
        int length = nums.length;
        int[] ans = new int[length - k + 1];
        // 存入首个窗口的最大元素的值
        ans[0] = nums[deque.peekFirst()];
        for (int i = k; i < length; i++) {
            while (!deque.isEmpty() && nums[i] >= nums[deque.peekLast()]) {
                deque.pollLast();
            }
            deque.offerLast(i);
            // 当前窗口的最左边坐标为 i-k+1 即 depue.peekFirst() < i-k+1的时候，需要去除队首元素，代表它不在窗口中
            // 在这里deuqe.peekFirst <= i-k，与上面同意义
            if (deque.peekFirst() <= i - k) {
                deque.pollFirst();
            }
            // 依次存入每个窗口中的最大值
            ans[i - k + 1] = nums[deque.peekFirst()];
        }
        return ans;
    }
}
