package main.java.com.exercise.week_053;

/**
 * @author cl
 * @version 1.0
 * @description： TODO
 * @date 2023/12/6 17:09
 */
public class LeetCode_135_2_分发糖果 {
    public int candy(int[] ratings) {
        int len = ratings.length;
        int up = 1, down = 0, pre = 1, sum = 1;
        for (int i = 1; i < len; i++) {
            if (ratings[i] >= ratings[i - 1]) {
                down = 0;
                pre = ratings[i] == ratings[i - 1] ? 1 : pre + 1;
                sum += pre;
                up = pre;
            } else {
                down++;
                if (down == up) {
                    down++;
                }
                sum += down;
                pre = 1;
            }
        }
        return sum;
    }

    public static void main(String[] args) {
        int[] ratings = new int[]{1,2,3,4,3,2,4,3,2};
        System.out.println(new LeetCode_135_2_分发糖果().candy(ratings));
    }
    //  1 2 3 4 3 2 4 3 2
    //  1 2 3 4 2 1 3 2 1
}
