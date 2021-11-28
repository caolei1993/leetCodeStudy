package main.java.com.exercise.week_041;

public class LeetCode_458_1_可怜的小猪 {
    public int poorPigs(int buckets, int minutesToDie, int minutesToTest) {
        // 所有可能的种类
        int times = minutesToTest / minutesToDie + 1;
        return (int)Math.ceil((Math.log(buckets) / Math.log(times)));
    }
}
