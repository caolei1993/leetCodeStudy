package main.java.com.exercise.week_035;

/**
 * @Author cl
 * @Date 2021/9/30 10:30
 * @Version 1.0
 */
public class LeetCode_223_1_矩形面积 {
    public int computeArea(int ax1, int ay1, int ax2, int ay2, int bx1, int by1, int bx2, int by2) {
        // 求取矩形1和矩形2的面积和
        int sum = (ax2 - ax1) * (ay2 - ay1) + (bx2 - bx1) * (by2 - by1);
        // 求取阴影部分面积
        int x = Math.max(0, Math.min(ax2 , bx2) - Math.max(ax1, bx1));
        int y = Math.max(0, Math.min(ay2, by2) - Math.max(ay1, by1));
        return sum - x * y;
    }
}
