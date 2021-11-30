package main.java.com.exercise.week_038;

/**
 * @Author cl
 * @Date 2021/10/23 21:31
 * @Version 1.0
 */
public class LeetCode_492_1_构造矩形 {
    public int[] constructRectangle(int area) {
        for (int i = (int)Math.sqrt(area);; i-- ) {
            if (area % i == 0) {
                return new int[]{area / i, i};
            }
        }
    }
}
