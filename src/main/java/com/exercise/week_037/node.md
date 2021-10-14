[toc]

# [LeetCode_273_1_整数转化英文表示](https://leetcode-cn.com/problems/integer-to-english-words/)
## 题目
将非负整数 num 转换为其对应的英文表示。

示例 1：
```
输入：num = 123
输出："One Hundred Twenty Three"
```

示例 2：
```
输入：num = 12345
输出："Twelve Thousand Three Hundred Forty Five"
```

示例 3：
```
输入：num = 1234567
输出："One Million Two Hundred Thirty Four Thousand Five Hundred Sixty Seven"
```

示例 4：
```
输入：num = 1234567891
输出："One Billion Two Hundred Thirty Four Million Five Hundred Sixty Seven Thousand Eight Hundred Ninety One"
```

提示：

* 0 <= num <= 2^31 - 1

## 理解
字符串模拟题   
每三位可循环处理，处理为几百几十几，然后通过判断值的位数，来确定在后面添加十亿，百万，千等单位，
注意字符串间的空格处理。  
3位数的处理：  
* x > 100，百位处理，用x/100拼上百
* x > 20，十位处理，x/10，转化为英文几十
* 1-19，直接处理为相应的英文

注意0需要单独讨论

因为数据有限，我们整个处理的时间复杂度和空间复杂度均可认为为O(1)


### 代码
```java
public class LeetCode_273_1_整数转化英文表示 {
    static final String[] SMALL = {"Zero", "One", "Two", "Three", "Four", "Five", "Six", "Seven", "Eight", "Nine", "Ten",
            "Eleven", "Twelve", "Thirteen", "Fourteen", "Fifteen", "Sixteen", "Seventeen", "Eighteen", "Nineteen"};
    static final String[] MEDIUM = {"", "", "Twenty", "Thirty", "Forty", "Fifty", "Sixty", "Seventy", "Eighty", "Ninety"};
    static final String[] LARGE = { "Billion", "Million", "Thousand", ""};

    private String numToStr(int x) {
        StringBuilder sb = new StringBuilder();
        if (x >= 100) {
            sb.append(SMALL[x / 100]).append(" Hundred ");
            x %= 100;
        }
        if (x >= 20) {
            sb.append(MEDIUM[x / 10]).append(" ");
            x %= 10;
        }
        if (x != 0) {
            sb.append(SMALL[x]).append(" ");
        }
        return sb.toString();
    }

    public String numberToWords(int num) {
        if (num == 0) {
            return SMALL[num];
        }
        StringBuilder sb = new StringBuilder();
        for (int i = (int)1e9, j = 0; i > 0 ; i /= 1000, j++) {
            if (num < i) {
                continue;
            }
            sb.append(numToStr(num / i)).append(LARGE[j]).append(" ");
            num %= i;
        }
        while (sb.charAt(sb.length() - 1) == ' ') {
            sb.deleteCharAt(sb.length() - 1);
        }
        return sb.toString();
    }
}
```

# [LeetCode_29_1_两数相除](https://leetcode-cn.com/problems/divide-two-integers/)
## 题目
给定两个整数，被除数 dividend 和除数 divisor。将两数相除，要求不使用乘法、除法和 mod 运算符。

返回被除数 dividend 除以除数 divisor 得到的商。

整数除法的结果应当截去（truncate）其小数部分，例如：truncate(8.345) = 8 以及 truncate(-2.7335) = -2

 
示例 1:
```
输入: dividend = 10, divisor = 3
输出: 3
解释: 10/3 = truncate(3.33333..) = truncate(3) = 3
```

示例 2:
```
输入: dividend = 7, divisor = -3
输出: -2
解释: 7/-3 = truncate(-2.33333..) = -2
```


提示：

* 被除数和除数均为 32 位有符号整数。
* 除数不为 0。
* 假设我们的环境只能存储 32 位有符号整数，其数值范围是 [−2^31,  2^31 − 1]。本题中，如果除法结果溢出，则返回 231 − 1。

## 理解
位运算实现倍增  
* 首先处理值溢出的情况，只有被除数为-2^31，除数为-1的时候，计算的值会溢出int的范围，直接返回int的最大值
* 提前判断返回值的符号，符号相同为正，符号相异为负
* 在a >= b的情况下，我们计算如下： 
```
a - (b << 0)，k = 1 << 0 = 1
a - (b + b) = a - (b << 1)，k = 1 << 1 = 2
a - (b + b + b + b) = a - (b << 2)，k = 1 << 2 = 4
.........
依次类推，最终结果为k的值的叠加 
```

因为遍历为常数次31次，所以时间复杂度为O(1)，空间复杂度也为O(1)

### 代码
```java
public class LeetCode_29_1_两数相除 {
    public int divide(int a, int b) {
        // 判断值溢出int范围的情况
        if (a == Integer.MIN_VALUE && b == -1) {
            return Integer.MAX_VALUE;
        }
        // 计算返回值的符号
        int sign = (a > 0) ^ (b > 0) ? -1 : 1;
        a = Math.abs(a);
        b = Math.abs(b);
        int ans = 0;
        // 从大往小判断a包含了多少个b
        for (int i = 31; i >= 0; i--) {
            // b左移替换为a的右移，防止b左移溢出
            // a使用无符号左移，兼容a = -2^31的情况
            // (a >>> i) >= b，修改为(a >>> i) - b >= 0，兼容b = -2^31的情况，一个正的int值加上 2^31次方，int溢出结果为负值，条件判断为false
            if ((a >>> i) - b >= 0) {
                a -= (b << i);
                ans += (1 << i);
            }
        }
        return sign == 1 ? ans : -ans;
    }
}
```

# [LeetCode_412_1_FizzBuzz](https://leetcode-cn.com/problems/fizz-buzz/)
## 题目
写一个程序，输出从 1 到 n 数字的字符串表示。

1. 如果 n 是3的倍数，输出“Fizz”；

2. 如果 n 是5的倍数，输出“Buzz”；

3.如果 n 同时是3和5的倍数，输出 “FizzBuzz”。

示例：
```
n = 15,

返回:
[
    "1",
    "2",
    "Fizz",
    "4",
    "Buzz",
    "Fizz",
    "7",
    "8",
    "Fizz",
    "Buzz",
    "11",
    "Fizz",
    "13",
    "14",
    "FizzBuzz"
]
```

## 理解
简单的字符串模拟  
时间复杂度为O(n)，空间复杂度为O(n)

### 代码
```java
public class LeetCode_412_1_FizzBuzz {
    public List<String> fizzBuzz(int n) {
        List<String> list = new ArrayList<>();
        for (int i = 1; i <= n ; i++) {
            if (i % 3 == 0 && i % 5 == 0) {
                list.add("FizzBuzz");
            } else if (i % 3 == 0) {
                list.add("Fizz");
            } else if (i % 5 == 0) {
                list.add("Buzz");
            } else {
                list.add(i + "");
            }
        }
        return list;
    }
}
```

# [LeetCode_剑指OfferII069_1_山峰数组的顶部](https://leetcode-cn.com/problems/B1IidL/)
## 题目
符合下列属性的数组 arr 称为 山峰数组（山脉数组） ：

* arr.length >= 3
* 存在 i（0 < i < arr.length - 1）使得：
    1. arr[0] < arr[1] < ... arr[i-1] < arr[i]  
    2. arr[i] > arr[i+1] > ... > arr[arr.length - 1]

给定由整数组成的山峰数组 arr ，返回任何满足 arr[0] < arr[1] < ... arr[i - 1] < arr[i] > arr[i + 1] > ... > arr[arr.length - 1] 的下标 i ，即山峰顶部。

 

示例 1：
```
输入：arr = [0,1,0]
输出：1
```

示例 2：
```
输入：arr = [1,3,5,4,2]
输出：2
```

示例 3：
```
输入：arr = [0,10,5,2]
输出：1
```

示例 4：
```
输入：arr = [3,4,5,1]
输出：2
```

示例 5：
```
输入：arr = [24,69,100,99,79,78,67,36,26,19]
输出：2
```
 

**提示**：

* 3 <= arr.length <= 10^4
* 0 <= arr[i] <= 10^6
* 题目数据保证 arr 是一个山脉数组
 

**进阶**：很容易想到时间复杂度 O(n) 的解决方案，你可以设计一个 O(log(n)) 的解决方案吗？

## 理解
使用二分法求解：  
因为题目给定数组一定是山脉数组，那么山顶两边满足以下特性：  
* 山顶左边arr[x - 1] < arr[x]
* 山顶右边arr[x - 1] > arr[x]

我们根据其二段性，利用二分法求解山顶的坐标，并返回  

时间复杂度为O(logn)，空间复杂度为O(1)

### 代码
```java
public class LeetCode_剑指OfferII069_1_山峰数组的顶部 {
    public int peakIndexInMountainArray(int[] arr) {
        int n = arr.length;
        int l = 0, r = n - 1;
        while (l < r) {
            int mid = l + r + 1 >> 1;
            if (arr[mid - 1] < arr[mid] ) {
                l = mid;
            } else {
                r = mid - 1;
            }
        }
        return r;
    }
}
```