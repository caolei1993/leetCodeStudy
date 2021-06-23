package main.java.com.exercise.week_022;

import java.util.ArrayList;
import java.util.List;

/**
 * @Author cl
 * @Date 2021/6/21 16:01
 * @Version 1.0
 */
public class LeetCode_401_2_二进制手表 {
    public List<String> readBinaryWatch(int turnedOn) {
        List<String> list = new ArrayList<>();
        for (int h = 0; h < 12; h++) {
            for (int m = 0; m < 60; m++) {
                int count = Integer.bitCount(h) + Integer.bitCount(m);
                if (count == turnedOn) {
                    list.add(h + ":" + (m < 10 ? "0" + m : m));
                }
            }
        }
        return list;
    }
}
