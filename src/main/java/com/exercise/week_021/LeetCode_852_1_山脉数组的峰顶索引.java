package main.java.com.exercise.week_021;

import javax.xml.bind.annotation.XmlInlineBinaryData;

/**
 * @Author cl
 * @Date 2021/6/16 15:19
 * @Version 1.0
 */
public class LeetCode_852_1_山脉数组的峰顶索引 {
    public  int peakIndexInMountainArray(int[] arr) {
        int l = 0, r = arr.length - 1;
        while (l < r) {
            long tem = (long)l + r + 1 >> 1;
            int mid = (int)tem;
            System.out.println("l=" + l +",r=" + r + ",mid=" + mid);
            if (arr[mid - 1] < arr[mid]) {
                l = mid;
            } else {
                r = mid - 1;
            }
        }
        return r;
    }
}
