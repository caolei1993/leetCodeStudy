package main.java.com.exercise.week_037;

import java.util.ArrayList;
import java.util.List;

/**
 * @Author cl
 * @Date 2021/10/13 9:59
 * @Version 1.0
 */
public class LeetCode_412_1_FizzBuzz {
    public List<String> fizzBuzz(int n) {
        List<String> list = new ArrayList<>();
        for (int i = 1; i <= n ; i++) {
            if (i % 3 == 0 && i % 5 == 0) {
                list.add("FizzBuzz");
            } else if (i % 3 == 0) {
                list.add("Fizz");
            } else if (i % 5 == 0) {
                list.add("Buzz");
            } else {
                list.add(i + "");
            }
        }
        return list;
    }
}
