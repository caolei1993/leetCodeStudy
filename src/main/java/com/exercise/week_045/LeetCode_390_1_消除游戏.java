package main.java.com.exercise.week_045;

/**
 * @author cl
 * @version 1.0
 * @date 2022/1/2 9:14 下午
 */
public class LeetCode_390_1_消除游戏 {
    public int lastRemaining(int n) {
        return 1 == n ? 1 : 2 * (n / 2 + 1 - lastRemaining(n / 2));
    }
}
