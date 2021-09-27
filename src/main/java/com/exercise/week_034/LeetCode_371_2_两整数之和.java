package main.java.com.exercise.week_034;

/**
 * @Author cl
 * @Date 2021/9/26 9:57
 * @Version 1.0
 */
public class LeetCode_371_2_两整数之和 {
    public int getSum(int a, int b) {
        return b == 0 ? a : getSum(a ^ b, (a & b) << 1);
    }
}
