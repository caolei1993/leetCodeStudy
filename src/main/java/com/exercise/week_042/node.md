[toc]

# [LeetCode_786_1_第K个最小的素数分数](https://leetcode-cn.com/problems/k-th-smallest-prime-fraction/)
## 理解
解法一：暴力求解，利用优先队列，根据从大到小排列数据，在元素等于k个时，当队头元素大于遍历元素时，
队头元素出队，遍历元素入队，保证queue中只存储k个元素，遍历完成后，队头元素即为第K小元素。

时间复杂度：遍历所有元素的复杂度为O(n^2)，将元素入堆出堆的复杂度为O(logk),
所以整体复杂度为O(n^2*logk)，空间复杂度为O(k)

解法二：多路合并  
因为原数组是有序的，所以组成(0,1)的分数为多个有序序列，问题转化为在n-1个有序
序列中找第K小的元素，那我们利用多路合并思想，先将所有的队头存入优先队列，优先
队列按从小到大排列，出队k-1次，每次出队，将对应序列的下一个元素存入，最后第
k次出队即为第K小的元素。

时间复杂度：起始将 n - 1 个序列的头部元素放入堆中，复杂度为 O(nlogn)；
然后重复 k 次操作得到第 k 小的值，复杂度为 O(klogn)。整体复杂度为 O(max(n, k) * logn)  
空间复杂度：O(n)

## 解法一
### 代码
```java
public class LeetCode_786_1_第K个最小的素数分数 {
    public int[] kthSmallestPrimeFraction(int[] arr, int k) {
        // 优先队列从大到小排列，队头为最大元素
        PriorityQueue<int[]> queue = new PriorityQueue<>((a, b) -> Double.compare(b[0] * 1.0 / b[1], a[0] * 1.0 / a[1]));
        int n = arr.length;
        for (int i = 0; i < n; i++) {
            for (int j = i + 1; j < n; j++) {
                // 当前遍历的值
                double t = arr[i] * 1.0 / arr[j];
                if (queue.size() > 0 && queue.peek()[0] * 1.0 / queue.peek()[1] > t) {
                    if (queue.size() == k) {
                        queue.poll();
                    }
                    queue.offer(new int[]{arr[i], arr[j]});
                }
            }
        }
        return queue.poll();
    }
}
```

## 解法二
### 代码
```java
public class LeetCode_786_2_第K个最小的素数分数 {
    public int[] kthSmallestPrimeFraction(int[] arr, int k) {
        // 优先队列从小到大排列，队头为最小元素
        PriorityQueue<int[]> queue = new PriorityQueue<>((a, b) -> {
           double t1 = arr[a[0]] * 1.0 / arr[a[1]];
           double t2 = arr[b[0]] * 1.0 / arr[b[1]];
           return Double.compare(t1, t2);
        });
        int n = arr.length;
        // 将没有有序队列的头存入优先队列中
        for (int i = 1; i < n; i++) {
            queue.offer(new int[]{0, i});
        }
        // 将前k-1个小值都poll掉
        while (k-- > 1) {
            int[] v = queue.poll();
            int i = v[0], j = v[1];
            if (i + 1 < j) {
                queue.offer(new int[]{i + 1, j});
            }
        }
        // 取出第K个小值
        int[] val = queue.poll();
        return new int[]{arr[val[0]], arr[val[1]]};
    }
}
```

# [LeetCode_400_1_第N位数字](https://leetcode-cn.com/problems/nth-digit/)
## 理解
计算思路：  
1. 确认目标值x的长度（利用累加法，判断n的长度落在哪个长度区间）  
2. 确认当前长度的初始值
3. 确定目标值x和当前长度初始值的偏移量
4. 求解n对应目标值x的元素的下标，获取并返回

时间复杂度为log(n)，空间复杂度为O(1)

### 代码
```java
public class LeetCode_400_1_第N位数字 {
    public static int findNthDigit(int n) {
        // i代表数字长度
        int i = 1;
        // sum用来统计小于n的长度的数字总个数
        long sum = 0;
        for (; i <= 9; i++) {
            // 计算每个len的数字的个数
            long count = (long)(i * 9 * Math.pow(10, i - 1));
            if (sum + count >= n) {
                break;
            }
            sum += count;
        }

        // 计算i长度的数字的起始值
        long start = (long)Math.pow(10, i - 1);

        // 计算目标数字在start上的增量
        // 本身计算时应该使用(n - sum - i) / i，如果能整除，则增量就是当前值，不能整除，增量就是结果+1
//        long add = (n - sum - i) % i == 0 ? (n - sum - i) / i : (n - sum - i) / i + 1;
        long add = (n - sum - 1) / i;
        long x = start + add;

        // 因为字符坐标是从0排序的，所以需要多减一个1
        return String.valueOf(x).charAt((int)(n - sum - 1) % i) - '0';
    }

    public static void main(String[] args) {
        System.out.println(findNthDigit(9));
    }
}
```

# [LeetCode_1446_1_连续字符](https://leetcode-cn.com/problems/consecutive-characters/)
## 理解
双指针问题，遍历原数组，在遍历的过程中如果遇到相同的相邻元素，利用新的指针
统计能量，每次取统计完更新能量为最大值，最终返回能量

时间复杂度为O(n)  
空间复杂度为O(1)

### 代码
```java
public class LeetCode_1446_1_连续字符 {
    public int maxPower(String s) {
        int n = s.length();
        int ans = 0;
        for (int i = 0; i < n;) {
            int count = 1;
            char c = s.charAt(i);
            while (++i < n && s.charAt(i) == c) {
                count++;
            }
            ans = Math.max(ans, count);
        }
        return ans;
    }
}
```



# [LeetCode_506_1_相对名次](https://leetcode-cn.com/problems/relative-ranks/)
## 理解
字符串模拟题：  
利用克隆方法获取数组克隆对象，并排序，将排序后的结果和名次存入map，再遍历
原数组，获取相应的名次，名次小于3的做特殊的奖牌处理，大于3的名次加一返回（从
0排的名次）。

时间复杂度：克隆的复杂度是O(n)，排序的复杂度是O(nlogn)，构造哈希表的复杂
度为O(n)，利用哈希表构造答案的复杂度为O(n)，所以整体复杂度为O(nlogn)

空间复杂度为O(n)

### 代码
```java
public class LeetCode_506_1_相对名次 {
    String[] str = new String[]{"Gold Medal", "Silver Medal", "Bronze Medal"};
    public String[] findRelativeRanks(int[] score) {
        int n = score.length;
        int[] clone = score.clone();
        Arrays.sort(clone);
        Map<Integer, Integer> map = new HashMap<>();
        for (int i = n - 1; i >= 0 ; i--) {
            map.put(clone[i], n - 1 - i);
        }

        String[] ans = new String[n];
        for (int i = 0; i < n; i++) {
            Integer val = map.get(score[i]);
            ans[i] = val < 3 ? str[val] : String.valueOf(val + 1);
        }

        return ans;
    }
}
```

# [LeetCode_1005_1_K次取反后最大化的数组和](https://leetcode-cn.com/problems/maximize-sum-of-array-after-k-negations/)
## 理解
利用小根堆求解：  
使用小根堆对数组元素进行排序，每次取出最小元素取反后放回，重复操作k次，
最后再将结果累加即可

时间复杂度：遍历存入小根堆为O(nlogn)，取反操作为O(klogn)，求和为O(nlogn)，整体
时间复杂度为O(nlogn)  
空间复杂度为O(n)
### 代码
```java
public class LeetCode_1005_1_K次取反后最大化的数组和 {
    public int largestSumAfterKNegations(int[] nums, int k) {
        PriorityQueue<Integer> queue = new PriorityQueue<>();
        for (int n : nums) {
            queue.offer(n);
        }

        while (k-- > 0) {
            int v = queue.poll();
            queue.offer(-v);
        }
        int ans = 0;
        while (!queue.isEmpty()) {
            ans += queue.poll();
        }
        return ans;
    }
}
```

# [LeetCode_383_1_赎金信](https://leetcode-cn.com/problems/ransom-note/)
## 理解
字符串模拟题：  
统计词频比对

时间复杂度为O(m+n)，空间复杂度为O(m+n+C)，m为信的长度，n为杂志的长度，C为
常数26

### 代码
```java
public class LeetCode_383_1_赎金信 {
    public boolean canConstruct(String ransomNote, String magazine) {
        int[] cnt = new int[26];
        for (char c : magazine.toCharArray()) {
            cnt[c - 'a']++;
        }
        for (char cc : ransomNote.toCharArray()) {
            if (--cnt[cc - 'a'] < 0) {
                return false;
            }
        }
        return true;
    }
}
```

# [LeetCode_372_1_超级次方](https://leetcode-cn.com/problems/super-pow/)
## 理解
快速幂：   
因为我们对b数组进行逐位求解幂次，所以都是10以内的幂次，也可以直接通过乘法
遍历求解

### 代码
```java
public class LeetCode_372_1_超级次方 {
    int mod = 1337;
    public int superPow(int a, int[] b) {
        return dfs(a, b, b.length - 1);
    }

    private int dfs (int a, int[] b, int n) {
        if (n == -1) {
            return 1;
        }
        return (qpow(dfs(a, b, n - 1), 10) * qpow(a, b[n])) % mod;
    }
    private int qpow(int a, int b) {
        int val = 1;
        a %= mod;
        while (b != 0) {
            if ((b & 1) == 1) {
                val = val * a % mod;
            }
            a = a * a % mod;
            b >>= 1;
        }
        return val;
    }
}
```