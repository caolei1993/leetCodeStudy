package main.java.com.exercise.week_056;

import java.util.ArrayList;
import java.util.List;

/**
 * @author cl
 * @version 1.0
 * @description： TODO
 * @date 2024/1/2 19:11
 */
public class LeetCode_68_1_文本左右对齐 {
    public List<String> fullJustify(String[] words, int maxWidth) {
        // 确认每行的单词
        List<List<String>> lineList = new ArrayList<>();
        for (int i = 0; i < words.length;) {
            List<String> list = new ArrayList<>();
            list.add(words[i]);
            int len = words[i++].length();
            while (i < words.length && (len + 1 + words[i].length()) <= maxWidth) {
                list.add(words[i]);
                len = len + 1 + words[i++].length();
            }
            lineList.add(list);
        }

        List<String> ans = new ArrayList<>();
        int count = lineList.size();
        for (int i = 0; i < count; i++) {
            StringBuilder sb = new StringBuilder();
            List<String> line = lineList.get(i);
            // 该行只有一个单词，使用空格补全
            if (line.size() == 1) {
                sb.append(line.get(0));
                while (sb.length() < maxWidth) {
                    sb.append(" ");
                }
                ans.add(sb.toString());
                continue;
            }
            // 最后一行，使用空格填充
            if (i == count - 1) {
                sb.append(line.get(0));
                for (int j = 1; j < line.size(); j++) {
                    sb.append(" ").append(line.get(j));
                }
                while (sb.length() < maxWidth) {
                    sb.append(" ");
                }
                ans.add(sb.toString());
                continue;
            }
            // 包含多个单词的行
            int wordLen = 0;
            for (String str : line) {
                wordLen += str.length();
            }
            int spaceLen = maxWidth - wordLen;
            int avgSpaceLen = spaceLen / (line.size() - 1);
            int preCount = spaceLen - avgSpaceLen * (line.size() - 1);
            for (int j = 0; j < line.size(); j++) {
                sb.append(line.get(j));
                // 不是最后一个单词
                if (j != line.size() - 1) {
                    for (int k = 0; k < avgSpaceLen; k++) {
                        sb.append(" ");
                    }
                    if (j < preCount) {
                        sb.append(" ");
                    }
                }
            }
            while (sb.length() < maxWidth) {
                sb.append(" ");
            }
            ans.add(sb.toString());
        }
        return ans;
    }

    public static void main(String[] args) {
        String[] str = new String[]{"Science","is","what","we","understand","well","enough","to","explain","to","a","computer.","Art","is","everything","else","we","do"};
        System.out.println(new LeetCode_68_1_文本左右对齐().fullJustify(str, 20));
//        System.out.println("everything else we do".length());
    }
}
