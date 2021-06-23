package main.java.com.exercise.week_021;

/**
 * @Author cl
 * @Date 2021/6/16 13:50
 * @Version 1.0
 */
public class LeetCode_374_1_猜数字大小 {
    public int guessNumber(int n) {
        int l = 1, r = n;
        while (l < r) {
            long tmp = (long)l + r >> 1;
            int mid = (int)tmp;
            if (guess(mid) <= 0) {
                r = mid;
            } else {
                l = mid + 1;
            }
        }
        return l;
    }
    private int guess(int num) {
        return -1;
    }
}
