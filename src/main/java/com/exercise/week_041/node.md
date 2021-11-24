[toc]

# [LeetCode_384_1_打乱数组](LeetCode_384_1_打乱数组)
## 理解


### 代码
```java
public class LeetCode_384_1_打乱数组 {
    int[] num;
    int n;
    Random random = new Random();

    public LeetCode_384_1_打乱数组(int[] nums) {
        num = nums;
        n = nums.length;
    }

    public int[] reset() {
        return num;
    }

    public int[] shuffle() {
        int[] ans = num.clone();
        for (int i = n - 1; i > 0; i--) {
            swap(ans, i, random.nextInt(i - 1));
        }
        return ans;
    }

    private void swap(int[] ans, int i, int j) {
        int tem = ans[i];
        ans[i] = ans[j];
        ans[j] = tem;
    }
}
```

# [LeetCode_895_1_亲密字符串](https://leetcode-cn.com/problems/buddy-strings/)
## 理解
字符串模拟题  
需要明确亲密字符串成立的条件，如果两个字符串长度不一致，直接返回false，
如果两个字符串词频不一致，也直接返回false。

词频相同的情况下：  
1.只有两个位置字符不一样  
2.所有位置都相同，但有单个字符的词频超过1个（可以实现相同字符的互换）

以上两种情况满足其一即为亲密字符串

时间复杂度为log(n)，空间复杂度为log(2C)，C为常数26

### 代码
```java
public class LeetCode_895_1_亲密字符串 {
    public boolean buddyStrings(String s, String goal) {
        int n = s.length();
        int m = goal.length();
        if (n != m) {
            return false;
        }
        // 统计词频
        int[] cnt1 = new int[26], cnt2 = new int[26];
        // 记录不同位置的个数
        int sum = 0;
        for (int i = 0; i < n; i++) {
            int a = s.charAt(i) - 'a', b = goal.charAt(i) - 'a';
            cnt1[a]++;
            cnt2[b]++;
            if (a != b) {
                sum++;
            }
        }
        boolean flag = false;
        for (int i = 0; i < 26; i++) {
            if (cnt1[i] != cnt2[i]) {
                return false;
            }
            if (cnt1[i] > 1) {
                flag = true;
            }
        }
        return sum == 2 || (sum == 0 && flag);
    }
}
```