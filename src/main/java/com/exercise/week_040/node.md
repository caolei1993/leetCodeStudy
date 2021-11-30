[toc]

# [LeetCode_575_1_分糖果](https://leetcode-cn.com/problems/distribute-candies/)
## 理解
使用set对数组去重，计算出糖果的总的种类数，因为需要将糖果分为两堆，所以如果总的种类数大于等于每堆数量，
那么我们能保证其中一堆中的糖果各不相同，返回每堆的数量，否则必定有重复，返回总的种类数

时间复杂度为O(n)，空间复杂度为O(n)

### 代码
```java
public class LeetCode_575_1_分糖果 {
    public int distributeCandies(int[] candyType) {
        int n = candyType.length;
        Set<Integer> set = new HashSet<>();
        for (int i : candyType) {
            set.add(i);
        }
        int size = set.size();
        return Math.min(size, n >> 1);
    }
}
```

# [LeetCode_268_1_丢失的数字](https://leetcode-cn.com/problems/missing-number/)
## 理解
解法一：使用排序，再比对每个位置与当前坐标是否相等。  
时间复杂度：假定 Arrays.sort 使用的是双轴快排实现，因为排序的时间复杂度为O(nlogn)，所以整体为O(nlogn)  
空间复杂度为O(logn)  

解法二：借助一个布尔数组来求解，存在的值，相应坐标的布尔值置为true，为false的即为缺失的  
时间复杂度为O(n)，空间复杂度为O(n)  

解法三：使用数学知识，使用等差队列求和公式求取0-n的sum值，再累加目标数组的cur值，差值即为缺失值  
时间复杂度为O(n)，空间复杂度为O(1)

解法四：使用异或知识，先求取0-n的异或结果，再与目标数组的每个值进行异或，因为除了缺失值，其余数都
是异或2次，结果为0，缺失值异或一次，所以最终结果为缺失值  
时间复杂度为O(n)，空间复杂度为O(1)

## 解法一
```java
public class LeetCode_268_1_丢失的数字 {
    public int missingNumber(int[] nums) {
        int n = nums.length;
        Arrays.sort(nums);
        for (int i = 0; i < n ; i++) {
            if (nums[i] != i) {
                return i;
            }
        }
        return n;
    }
}
```

## 解法二
```java
public class LeetCode_268_2_丢失的数字 {
    public int missingNumber(int[] nums) {
        int n = nums.length;
        boolean[] flag = new boolean[n + 1];
        for (int num : nums) {
            flag[num] = true;
        }
        for (int i = 0; i <= n; i++) {
            if (!flag[i]) {
                return i;
            }
        }
        return -1;
    }
}
```

## 解法三
```java
public class LeetCode_268_3_丢失的数字 {
    public int missingNumber(int[] nums) {
        int n = nums.length;
        int cur = 0, sum;
        sum = n * (n + 1) / 2;
        for (int num : nums) {
            cur += num;
        }
        return sum - cur;
    }
}
```

## 解法四
```java
public class LeetCode_268_4_丢失的数字 {
    public int missingNumber(int[] nums) {
        int n = nums.length;
        int val = 0;
        for (int i = 0; i <= n; i++) {
            val ^= i;
        }
        for (int num : nums) {
            val ^= num;
        }
        return val;
    }
}
```

# [LeetCode_598_1_范围求和II](https://leetcode-cn.com/problems/range-addition-ii/)
## 理解
每次操作都会覆盖x,y与0,0形成的矩形区域，所以最大数为每次都被覆盖的区域，所以需要求解x轴的最小值，
与y轴的最小值，相乘即为结果

时间复杂度：令k为数组ops的长度，复杂度为O(k)  
空间复杂度为O(1)

### 代码一
```java
public class LeetCode_598_1_范围求和II {
    public int maxCount(int m, int n, int[][] ops) {
        int minx = Integer.MAX_VALUE, miny = Integer.MAX_VALUE;
        if (ops.length == 0) {
            return m * n;
        }
        for (int[] op : ops) {
            minx = Math.min(minx, op[0]);
            miny = Math.min(miny, op[1]);
        }
        return minx * miny;
    }
}
```

### 代码二
```java
public class LeetCode_598_2_范围求和II {
    public int maxCount(int m, int n, int[][] ops) {
        for (int[] p : ops) {
            m = Math.min(m, p[0]);
            n = Math.min(n, p[1]);
        }
        return m * n;
    }
}
```