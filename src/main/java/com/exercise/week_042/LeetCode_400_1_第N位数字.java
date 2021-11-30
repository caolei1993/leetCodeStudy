package main.java.com.exercise.week_042;

public class LeetCode_400_1_第N位数字 {
    public static int findNthDigit(int n) {
        // i代表数字长度
        int i = 1;
        // sum用来统计小于n的长度的数字总个数
        long sum = 0;
        for (; i <= 9; i++) {
            // 计算每个len的数字的个数
            long count = (long)(i * 9 * Math.pow(10, i - 1));
            if (sum + count >= n) {
                break;
            }
            sum += count;
        }

        // 计算i长度的数字的起始值
        long start = (long)Math.pow(10, i - 1);

        // 计算目标数字在start上的增量
        // 本身计算时应该使用(n - sum - i) / i，如果能整除，则增量就是当前值，不能整除，增量就是结果+1
//        long add = (n - sum - i) % i == 0 ? (n - sum - i) / i : (n - sum - i) / i + 1;
        long add = (n - sum - 1) / i;
        long x = start + add;

        // 因为字符坐标是从0排序的，所以需要多减一个1
        return String.valueOf(x).charAt((int)(n - sum - 1) % i) - '0';
    }

    public static void main(String[] args) {
        System.out.println(findNthDigit(9));
    }
}
