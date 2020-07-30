/*
 * Copyright (c) 2020. Carl
 */

package com.seven.leetcode;

import java.util.Arrays;
import java.util.HashMap;

/**
 * SumOfTwo
 *
 * 给定一个整数数组 nums 和一个目标值 target，请你在该数组中找出和为目标值的那 两个 整数，并返回他们的数组下标。
 *
 * 你可以假设每种输入只会对应一个答案。但是，数组中同一个元素不能使用两遍。
 *
 * 示例:
 *
 * 给定 nums = [2, 7, 11, 15], target = 9
 *
 * 因为 nums[0] + nums[1] = 2 + 7 = 9
 * 所以返回 [0, 1]
 *
 * @author Carl
 * @date 2020.07.30 22:34
 */
public class SumOfTwo {

    public static void main(String[] args) {
        int[] nums = new int[] {1, 2, 3, 5, 9, 10};
        int target = 6;
        int[] result = getResult(nums, target);
        System.out.println(Arrays.toString(result));
    }

    private static int[] getResult(int[] nums, int target) {
        int[] result = new int[] {0, 0};
        if (nums.length <= 1) {
            return result;
        }

        HashMap<Integer, Integer> map = new HashMap<>();
        for (int i = 0; i < nums.length; i ++) {
            if (map.containsKey(target - nums[i])) {
                result[0] = i;
                result[1] = map.get(target - nums[i]);

                return result;
            }

            map.put(nums[i], i);
        }

        return result;
    }
}
