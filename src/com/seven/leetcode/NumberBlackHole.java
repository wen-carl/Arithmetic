/*
 * Copyright (c) 2020. Carl
 */

package com.seven.leetcode;

import java.util.ArrayList;
import java.util.Comparator;
import java.util.List;
import java.util.OptionalInt;
import java.util.stream.Collectors;
import java.util.stream.IntStream;

public class NumberBlackHole {

    public static final int STEP = 10;
    public static final int LENGTH = 4;
    public static final int FINAL_VALUE = 6174;

    public static void main(String[] args) {
        // write your code here
        System.out.println("hello world!");
        test();
    }

    static void test() {
        List<Integer> list = IntStream.rangeClosed(1, 9999)
            .filter(x -> x % 1111 != 0)
            .boxed()
            .collect(Collectors.toList());
        log(list);

        List<Temp> res = new ArrayList<>();
        list.parallelStream()
            .forEach(x -> {
                int y = x;
                int z = y;
                int index = 0;
                do {
                    index++;
                    z = y;
                    y = test2(y);
                } while (z != y);
                res.add(new Temp(x, index, y));
            });

        List<Temp> result = res.stream()
            .sorted(Comparator.comparingInt(a -> a.value))
            .collect(Collectors.toList());

        OptionalInt max = res.stream()
            .mapToInt(Temp::getTimes)
            .max();

        log(result);
    }

    static int test3(int x) {
        int y = x;
        int z = y;
        do {
            z = y;
            y = test2(y);
        } while (z != y);

        return y;
    }

    static int test2(int num) {
        List<Integer> nums = new ArrayList<>();
        int x = num;
        for (int i = LENGTH; i > 0; i--) {
            int grade = (int) Math.pow(STEP, i - 1);
            int temp = x / grade;

            nums.add(temp);
            x = x - temp * grade;
        }

        log("num: " + num + " nums: " + nums);

        // 排序，从小到大
        nums = nums.stream()
            .sorted()
            .collect(Collectors.toList());

        int max = 0;
        int min = 0;
        for (int i = 0; i < nums.size(); i++) {
            int grade = (int) Math.pow(STEP, i);
            max += nums.get(i) * grade;

            min += nums.get(nums.size() - i - 1) * grade;
        }

        return max - min;
    }

    static void log(String msg) {
        System.out.println(msg);
    }

    static void log(List list) {
        System.out.println(list);
    }

    static class Temp {
        public int value;
        public int times;
        public int finalValue;

        public Temp(int value, int times, int finalValue) {
            this.value = value;
            this.times = times;
            this.finalValue = finalValue;
        }

        public int getValue() {
            return value;
        }

        public void setValue(int value) {
            this.value = value;
        }

        public int getTimes() {
            return times;
        }

        public void setTimes(int times) {
            this.times = times;
        }

        public int getFinalValue() {
            return finalValue;
        }

        public void setFinalValue(int finalValue) {
            this.finalValue = finalValue;
        }

        @Override
        public String toString() {
            return "Temp{" +
                "value=" + value +
                ", times=" + times +
                ", finalValue=" + finalValue +
                '}';
        }
    }
}
