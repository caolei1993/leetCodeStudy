package main.java.com.exercise.week_039;

import java.util.ArrayList;
import java.util.List;

/**
 * @Author cl
 * @Date 2021/10/31 16:46
 * @Version 1.0
 */
public class LeetCode_500_1_键盘行 {
    static List<Character> line1 = new ArrayList<>();
    static List<Character> line2 = new ArrayList<>();
    static List<Character> line3 = new ArrayList<>();
    static {
        line1.add('q');
        line1.add('w');
        line1.add('e');
        line1.add('r');
        line1.add('t');
        line1.add('y');
        line1.add('u');
        line1.add('i');
        line1.add('o');
        line1.add('p');
        line2.add('a');
        line2.add('s');
        line2.add('d');
        line2.add('f');
        line2.add('g');
        line2.add('h');
        line2.add('j');
        line2.add('k');
        line2.add('l');
        line3.add('z');
        line3.add('x');
        line3.add('c');
        line3.add('v');
        line3.add('b');
        line3.add('n');
        line3.add('m');
    }

    public String[] findWords(String[] words) {
        List<String> ans = new ArrayList<>();
        for (String word : words) {
            String wordNew = word.toLowerCase();
            boolean flag = false;
            switch (word.charAt(0)) {
                case 'q':
                case 'w':
                case 'e':
                case 'r':
                case 't':
                case 'y':
                case 'u':
                case 'i':
                case 'o':
                case 'p':
                    for ( char c : wordNew.toCharArray()) {
                        if (line1.contains(c)) {
                            flag = true;
                        } else {
                            flag = false;
                            break;
                        }
                    }
                    if (flag) {
                        ans.add(word);
                    }
                    break;
                case 'a':
                case 's':
                case 'd':
                case 'f':
                case 'g':
                case 'h':
                case 'j':
                case 'k':
                case 'l':
                    for ( char c : wordNew.toCharArray()) {
                        if (line2.contains(c)) {
                            flag = true;
                        } else {
                            flag = false;
                            break;
                        }
                    }
                    if (flag) {
                        ans.add(word);
                    }
                    break;
                case 'z':
                case 'x':
                case 'c':
                case 'v':
                case 'b':
                case 'n':
                case 'm':
                    for ( char c : wordNew.toCharArray()) {
                        if (line3.contains(c)) {
                            flag = true;
                        } else {
                            flag = false;
                            break;
                        }
                    }
                    if (flag) {
                        ans.add(word);
                    }
                    break;
                default:
            }
        }
        return ans.toArray(new String[0]);
    }
}
