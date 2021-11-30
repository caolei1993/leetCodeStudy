[toc]

# [LeetCode_319_1_灯泡的开关](https://leetcode-cn.com/problems/bulb-switcher/)
## 理解
数学问题：  
第i个灯泡的反转次数等于它所有因子（包括1和i）的个数，一开始状态为灭，只有反转奇数次才能是亮的
状态，所以只有因子个数为奇数次的才会亮，只有平方数的因子个数为奇数（比如6=1*6，2*3，它们的因子都
是成对出现的，而4=1*4,2*2，只有平方数的平方根因子只出现一次），所以最终答案是求取n以内的平方数的
数量，直接对n开平方即是答案。

时间复杂度空间复杂度均为O(1)

### 代码
```java
public class LeetCode_319_1_灯泡的开关 {
    public int bulbSwitch(int n) {
        return (int)Math.sqrt(n + 0.5);
    }
}
```
