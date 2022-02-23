[toc]

# [LeetCode_1405_1_最长快乐字符串](https://leetcode-cn.com/problems/longest-happy-string/)
## 理解
贪心思想问题，每次都将剩余最多的字符添加到最后，保证拼接的字符串最长，这里可以
借助优先队列来实现每次取剩余字符最多的字符

这里需要注意，如果字符串长度超过2，并且前面两个字符与当前取出的字符一样，就不
能再添加了，只能取队列中的下一个多的字符，如果队列为空，则直接返回。

### 代码
```java
public class LeetCode_1405_1_最长快乐字符串 {
    public String longestDiverseString(int a, int b, int c) {
        StringBuilder sb = new StringBuilder();
        PriorityQueue<int[]> queue = new PriorityQueue<>((x, y)-> y[1] - x[1]);
        if (a != 0) {
            queue.offer(new int[]{0, a});
        }
        if (b != 0) {
            queue.offer(new int[]{1, b});
        }
        if (c != 0) {
            queue.offer(new int[]{2, c});
        }
        while (!queue.isEmpty()) {
            int[] cur = queue.poll();
            int len = sb.length();
            if (len >= 2 && sb.charAt(len - 1) - 'a' == cur[0] && sb.charAt(len - 2) - 'a' == cur[0]) {
                if (queue.isEmpty()) {
                    break;
                }
                int[] next = queue.poll();
                sb.append((char)(next[0] + 'a'));
                next[1]--;
                if (next[1] > 0) {
                    queue.offer(next);
                }
                queue.offer(cur);
            } else {
                sb.append((char)(cur[0] + 'a'));
                cur[1]--;
                if (cur[1] > 0) {
                    queue.offer(cur);
                }
            }
        }
        return sb.toString();
    }
}
```