[toc]

# [LeetCode_299_1_猜数字游戏](https://leetcode-cn.com/problems/bulls-and-cows/)
## 理解
遍历秘密数字和猜测数字每个位置，如果值相等，公牛数+1，否则记录各个数的词频，最终每个位置的最小值之
和即为奶牛数量  
时间复杂度为O(n)，空间复杂度为O(C)，C为常数大小为10

### 代码
```java
public class LeetCode_299_1_猜数字游戏 {
    public String getHint(String secret, String guess) {
        int n = secret.length();
        int[] cn1 = new int[10];
        int[] cn2 = new int[10];
        // 公牛数量,奶牛数量
        int a = 0, b = 0;
        for (int i = 0; i < n; i++) {
            int c1 = secret.charAt(i) - '0', c2 = guess.charAt(i) - '0';
            if (c1 == c2) {
                a++;
            } else {
                cn1[c1]++;
                cn2[c2]++;
            }
        }
        for (int i = 0; i < 10; i++) {
            b += Math.min(cn1[i], cn2[i]);
        }
        return a + "A" + b + "B";
    }
}
```