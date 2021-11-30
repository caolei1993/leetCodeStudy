package main.java.com.exercise.week_032;

import java.util.ArrayList;
import java.util.List;

/**
 * @Author cl
 * @Date 2021/9/10 17:16
 * @Version 1.0
 */
public class LeetCode_68_1_文本左右对齐 {
    public List<String> fullJustify(String[] words, int maxWidth) {
        int n = words.length;
        List<String> ans = new ArrayList<>();
        List<String> row = new ArrayList<>();

        for (int i = 0; i < n;) {
            // 获取每行存储的单词，存入row
            row.clear();
            row.add(words[i]);
            int cur = words[i++].length();
            while (i < n && (cur + 1 + words[i].length()) <= maxWidth) {
                row.add(words[i]);
                cur = cur + 1 + words[i++].length();
            }

            // 如果是最后一行
            if (i == n) {
                StringBuilder builder = new StringBuilder(row.get(0));
                for (int j = 1; j < row.size(); j++) {
                    builder.append(" ").append(row.get(j));
                }
                while (builder.length() < maxWidth) {
                    builder.append(" ");
                }
                ans.add(builder.toString());
                break;
            }

            // 如果当前行只有一个单词
            int cnt = row.size();
            if (cnt == 1) {
                StringBuilder builder = new StringBuilder(row.get(0));
                while (builder.length() < maxWidth) {
                    builder.append(" ");
                }
                ans.add(builder.toString());
                continue;
            }

            /**
             * 其余为一般情况
             * wordWidth : 当前行单词总长度;
             * spaceWidth : 当前行空格总长度;
             * spaceItemWidth  : 往下取整后的单位空格长度
             * count : 前count个空格需要多加" "
             */
            int wordWidth = cur - (cnt -1);
            int spaceWidth = maxWidth - wordWidth;
            int spaceItemWidth  = spaceWidth / (cnt - 1);
            int count = spaceWidth - (spaceWidth / (cnt - 1) * (cnt - 1));
            StringBuilder builder = new StringBuilder();
            while (spaceItemWidth > 0) {
                builder.append(" ");
                spaceItemWidth--;
            }
            String spaceItem = builder.toString();
            StringBuilder sb =  new StringBuilder();
            for (int j = 0; j < cnt; j++) {
                sb.append(row.get(j));
                if (j == cnt - 1) {
                    break;
                }
                sb.append(spaceItem);
                if (count > 0) {
                    sb.append(" ");
                    count--;
                }
            }
            ans.add(sb.toString());
        }

        return ans;
    }
}
