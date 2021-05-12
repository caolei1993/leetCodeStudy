package main.java.com.exercise.week_001;

import com.sun.xml.internal.ws.api.model.wsdl.WSDLOutput;

import java.nio.charset.StandardCharsets;
import java.time.*;
import java.time.format.DateTimeFormatter;
import java.time.temporal.ChronoField;
import java.time.temporal.ChronoUnit;
import java.util.*;
import java.util.stream.Collectors;

/**
 * @Author cl
 * @Date 2021/1/6 9:32
 * @Version 1.0
 */
public class LeetCode_399_1_除法求值 {
//    public double[] calcEquation(List<List<String>> equations, double[] values, List<List<String>> queries) {
//
//    }
    public enum ABC{
        A,
        B,
        C
    }
    public static void main(String[] args) {
        System.out.println((int)1e9);
//        System.out.println(Long.parseLong("20210508110300010177000010077000"));
//        System.out.println(5 ^ 5);
//        System.out.println(0 ^ 5);
//        System.out.println(4 ^ 5);
//        System.out.println(ABC.A == null);
//        Stack<String> stack = new Stack<>();
//        stack.push(null);
//        System.out.println(stack.empty());
//        System.out.println(stack.isEmpty());
//        System.out.println(stack.pop());

//        List<Integer> list = new ArrayList<>();
//        list.add(1);
//        list.add(2);
//        list.add(3);
//        System.out.println(list.subList(0,3));

//        StringBuilder builder = new StringBuilder();
//        String[] strArray = "年级:3;姓名:我".split(";");
//        for (String str : strArray) {
//            builder.append(str.split(":")[1]).append(" ");
//        }
//        System.out.println(builder.toString());
//        int[] inorder = new int[]{1,2,3,4,5,6};
//        List<Integer> inorderList = Arrays.stream(inorder).boxed().collect(Collectors.toList());
//        System.out.println(inorderList.get(0));
//        System.out.println(inorderList.toString());
//        System.out.println(inorderList.indexOf(3));
//        String s = "15";
//        System.out.println(s.matches("\\d+"));
//        System.out.println("121222在1线，123456".contains("在线"));

//        LocalDate date = LocalDate.of(2021,3,1);
//        LocalDate date1 = LocalDate.of(2021,3,11);
//        System.out.println(date);
//        System.out.println(date.getYear());
//        System.out.println(date.getMonth());
//        System.out.println(date.getDayOfMonth());
//        System.out.println(date.getDayOfWeek());
//        System.out.println(date.isLeapYear());
//        LocalDate now = LocalDate.now();
//        System.out.println(now);
//        LocalDate now1 = now.withMonth(5);
//        System.out.println(now1);
//        LocalDate now2 = now1.withDayOfMonth(13);
//        System.out.println(now2 + "~~~~~~");
//        LocalDate now3 = now2.withDayOfYear(1);
//        System.out.println(now3);
//        LocalTime time = LocalTime.of(12,54,48,987);
//        System.out.println(time);
//        LocalTime time1 = LocalTime.parse("14:00:00.1");
//        System.out.println(time1);
//        LocalDateTime dateTime = LocalDateTime.of(2021,3,8,15,21,50);
//        System.out.println(dateTime);
//
//        LocalDateTime dateTime1 = LocalDateTime.of(date, time);
//        System.out.println(dateTime1);
//        Instant instant = Instant.now();
//        System.out.println(instant);
//        Duration duration = Duration.between(time, time1);
//        System.out.println(duration);
//        Period period = Period.between(date, date1);
//        System.out.println(period);
//        String a =
//        int[] nums = new int[]{ 1, 2, 3};
//        // 搜索区间为[l,r)
//        int low = 0;
//        int high = nums.length - 1;
//        while (low < high) {
//            int pivot = low + (high - low) / 2;
//            if (nums[pivot] < nums[high]) {
//                high = pivot;
//            } else {
//                low = pivot + 1;
//            }
//        }
//
//        System.out.println("low = "+ low + ", high = "+ high);

//        String[] str = new String[]{"30","3","334"};
//        Arrays.sort(str, (a, b) -> (b + a).compareTo(a + b));
//        System.out.println(Arrays.toString(str));
//        String a = " 113";
//        char[] ch = a.toCharArray();
//        System.out.println(Arrays.toString(ch));
//        int i = ch[0] - '0';
//        System.out.println(i);
//        System.out.println((int)1e8);
    }
}
