package main.java.com.exercise.week_045;

/**
 * @author cl
 * @version 1.0
 * @date 2021/12/31 9:04 上午
 */
public class LeetCode_507_1_完美数 {
    public boolean checkPerfectNumber(int num) {
        if (num == 1) {
            return false;
        }
        int sum = 1;
        for (int i = 2; i <= Math.sqrt(num); i++) {
            if (num % i == 0) {
                sum += i;
                if (i * i != num) {
                    sum += num / i;
                }
            }
        }
        return num == sum;
    }
}
