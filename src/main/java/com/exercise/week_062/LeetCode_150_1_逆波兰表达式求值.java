package main.java.com.exercise.week_062;

import java.util.Deque;
import java.util.LinkedList;

/**
 * @author cl
 * @version 1.0
 * @description： TODO
 * @date 2024/3/4 17:08
 */
public class LeetCode_150_1_逆波兰表达式求值 {
    public int evalRPN(String[] tokens) {
        Deque<Integer> deque = new LinkedList<>();
        for (String str : tokens) {
            switch (str) {
                case "+":
                    Integer one = deque.pop();
                    Integer two = deque.pop();
                    deque.push(one + two);
                    break;
                case "-":
                    Integer three = deque.pop();
                    Integer four = deque.pop();
                    deque.push(four - three);
                    break;
                case "*":
                    Integer five = deque.pop();
                    Integer six = deque.pop();
                    deque.push(five * six);
                    break;
                case "/":
                    Integer seven = deque.pop();
                    Integer eight = deque.pop();
                    deque.push(eight / seven);
                    break;
                default:
                    deque.push(Integer.parseInt(str));

            }
        }
        return deque.pop();
    }
}
