[toc]

# [LeetCode_825_1_适龄的朋友](https://leetcode-cn.com/problems/friends-of-appropriate-ages/)
## 理解
排序+双指针  

从三个不发送好友请求的条件来看，以y的角度来说，可总结为：
**年龄比我小的不考虑（同龄的可以），年龄比我大可以考虑，但是不能超过一定范围则不考虑。**

即对于y而言，会发送好友请求的x为一个连续的年龄段，所以我们可以将年龄排序，
遍历每个age作为y，利用双指针求取可发送好友请求的范围[i,j)，统计请求
总数为累加区间长度，需要注意因为区间包含y本身，所以计算时需要j - i - 1,
累加结果并返回。

时间复杂度：排序的复杂度为O(nlogn)，遍历的y的最坏时间复杂度为O(n^2)  
空间复杂度：O(logn)为排序需要的栈空间

### 代码
```java
public class LeetCode_825_1_适龄的朋友 {
    public int numFriendRequests(int[] ages) {
        Arrays.sort(ages);
        int n = ages.length, ans = 0;
        for (int k = 0, i = 0, j = 0; k < n; k++) {
            while(i < k && !check(ages[i], ages[k])) {
                i++;
            }
            if (j < k) {
                j = k;
            }
            while(j < n && check(ages[j], ages[k])) {
                j++;
            }
            if (i < j) {
                ans += j - i - 1;
            }
        }
        return ans;
    }

    private boolean check(int x, int y) {
        if (y <= x / 2 + 7) {
            return false;
        }
        if (y > x) {
            return false;
        }
        if (y > 100 && x < 100) {
            return false;
        }
        return true;
    }
}
```

# [LeetCode_1995_1_统计特殊四元组](https://leetcode-cn.com/problems/count-special-quadruplets/)
## 理解
解法一：因为数组范围只有50，我们使用暴力遍历，时间复杂度为O(n^4)，不会
超时

## 解法一
### 代码
```java
public class LeetCode_1995_1_统计特殊四元组 {
    public int countQuadruplets(int[] nums) {
        int n = nums.length;
        int ans = 0;
        for (int a = 0; a < n; a++) {
            for (int b = a + 1; b < n; b++) {
                for (int c = b + 1; c < n; c++) {
                    for (int d = c + 1; d < n; d++) {
                        if (nums[a] + nums[b] + nums[c] == nums[d]) {
                            ans++;
                        }
                    }
                }
            }
        }
        return ans;
    }
}
```

## 理解
解法二：倒序遍历c，使得每次可以多取一个c + 1作为d，使用数组或哈希表
统计d及d出现的次数，并计算a + b + c的值，在数组中取相应的d，叠加答案
并返回。

整体的时间复杂度为O(n^3)  
空间复杂度为O(1)

## 解法二
### 代码
```java
public class LeetCode_1995_2_统计特殊四元组 {
    public int countQuadruplets(int[] nums) {
        int n = nums.length;
        int ans = 0;
        int[] cnt = new int[310];
        for (int c = n - 2; c >= 2; c--) {
            // 使用数组记录d的值与出现的频率
            cnt[nums[c + 1]]++;
            for (int a = 0; a < n; a++) {
                for (int b = a + 1; b < c; b++) {
                    ans += cnt[nums[a] + nums[b] + nums[c]];
                }
            }
        }
        return ans;
    }
}
```

## 理解
利用等式变化a + b = d - c，我们倒序遍历b，每次可以多取一个b + 1，作为
c，依次计算出所有d和这个c的差值，记录在哈希表或数组中，需要注意这里求取
差值可能为负数，数组的下标不能为负，所以我们可以将值整体偏移200，保证
差值一定为正，求取满足条件的a与b的和，同样整体偏移200，并从数组中取相应
答案的数量，进行累加。

整体时间复杂为O(n^2)  
空间复杂度为O(1)

## 解法三
### 代码
```java
public class LeetCode_1995_3_统计特殊四元组 {
    public int countQuadruplets(int[] nums) {
        int n = nums.length;
        int ans = 0;
        int[] cnt = new int[1000];
        // 变式子: nums[a] + nums[b] = nums[d] - nums[c]
        for (int b = n - 3; b >= 1; b--) {
            for (int d = b + 2; d < n; d++) {
                // 减法的值有可能为负数，整体偏移200
                cnt[nums[d] - nums[b + 1] + 200]++;
            }
            for (int a = 0; a < b; a++) {
                ans += cnt[nums[a] + nums[b] + 200];
            }
        }
        return ans;
    }
}
```

# [LeetCode_846_1_一手顺子](https://leetcode-cn.com/problems/hand-of-straights/)
## 理解
使用哈希表+小根堆模拟分牌。  

使用哈希表统计数子及词频，使用小根堆对牌排序，我们从小根堆每次取堆头的牌，
作为顺子的起始最小的牌，并从哈希表中依次往下取，取不到就返回false，每次
取的堆头牌需要判断是否还有，因为为了组顺子会依次往下取，可能已经取完了后续
的牌。如果都按分组数，组合完毕，则返回true。

时间复杂度：将所有牌存入哈希表的时间复杂度为O(n)，将所有牌入堆与出堆的
时间复杂度为O(nlogn)，所以整体的时间复杂度为O(nlogn)

空间复杂度为O(n)

### 代码
```java
public class LeetCode_846_1_一手顺子 {
    public boolean isNStraightHand(int[] hand, int groupSize) {
        int n = hand.length;
        if (n % groupSize != 0) {
            return false;
        }
        // 用来存入元素，保证每次都能取到最小值作为顺子的头
        PriorityQueue<Integer> queue = new PriorityQueue<>((a, b) -> a - b);
        // 统计每个数子及出现次数
        Map<Integer, Integer> map = new HashMap<>();
        for (int i : hand) {
            queue.offer(i);
            map.put(i, map.getOrDefault(i, 0) + 1);
        }

        while (!queue.isEmpty()) {
            int first = queue.poll();
            if (map.get(first) == 0) {
                continue;
            }
            for (int i = 0; i < groupSize; i++) {
                int count = map.getOrDefault(first + i, 0);
                if (count == 0) {
                    return false;
                }
                map.put(first + i, count - 1);
            }
        }
        return true;
    }
}
```

# [LeetCode_507_1_完美数](https://leetcode-cn.com/problems/perfect-number/)
## 理解
模拟题，在[1, 根号下num]范围内求解正因子，因为因子都是成对存在的，所以
我们每次取余为0时，累加两个因子，遍历结束后与num做比较返回。

时间复杂度为O(logn)  
空间复杂度为O(1)

### 代码
```java
public class LeetCode_507_2_完美数 {
    public boolean checkPerfectNumber(int num) {
        if (num == 1) {
            return false;
        }
        int sum = 1;
        for (int i = 2; i <= num / i; i++) {
            if (num % i == 0) {
                sum += i + num / i;
            }
        }
        return num == sum;
    }
}
```