package com.zjn.algorithm.solution;

import com.zjn.algorithm.util.CommenUtil;

import java.util.HashMap;
import java.util.Map;

/**
 * AddSolution  两个数求和问题
 * 题目：给定一个整数数组 nums 和一个目标值 target，请你在该数组中找出和为目标值的那两个整数，并返回他们的数组下标。
 * 你可以假设每种输入只会对应一个答案。但是，你不能重复利用这个数组中同样的元素。
 *
 * @author zjn
 * @date 2019/11/24
 **/
public class AddSolution {
    public static void main(String[] args) {
        //测试求和
       // int[] data = {-3, -6, 3, 2, 4};
        int[] data = {-5,2043,2};
        int[] result1 = twoSum1(data, -3);
        int[] result2 = twoSum2(data, -3);
        int[] result3 = twoSum3_1(data, -3);
        System.out.println(result1[0] + "," + result1[1]);
        System.out.println(result2[0] + "," + result2[1]);
        System.out.println(result3[0] + "," + result3[1]);


    }

    /**
     * 最容易想到的，两层循环暴力破解（耗时较长）
     *
     * @param null
     * @return :
     * @author : zjn
     * @date : 2019/11/25
     */
    public static int[] twoSum1(int[] nums, int target) {
        int[] result = new int[2];
        if (nums == null || nums.length < 2)
            return result;
        for (int i = 0; i < nums.length - 1; i++) {
            for (int j = i + 1; j < nums.length; j++) {
                if (nums[i] + nums[j] == target) {
                    result[0] = i;
                    result[1] = j;
                    return result;
                }
            }
        }
        return result;
    }


    /**
     * 利用数据字典求和
     *
     * @param null
     * @return :
     * @author : zjn
     * @date : 2019/11/25
     */
    public static int[] twoSum2(int[] nums, int target) {
        int[] result = new int[2];
        if (nums == null || nums.length < 2)
            return result;
        Map<Integer, Integer> tempMap = new HashMap<Integer, Integer>();
        for (int i = 0; i < nums.length; i++) {
            int anotherNum = target - nums[i];
            if (tempMap.containsKey(anotherNum)) {
                return new int[]{tempMap.get(anotherNum), i};
            }
            tempMap.put(nums[i], i);
        }
        return result;
    }

    /**
     * 用数据字典、与运算求和（待研究）
     * 该方法有局限性：int[] data = {-5,2043,2};求和-3  得出结果为[1,2]
     * 方法中的max取值应该斟酌（应该根据数组最大值和最小值的绝对值）
     *
     * @param null
     * @return :
     * @author : zjn
     * @date : 2019/11/25
     */
    public static int[] twoSum3(int[] nums, int target) {
        int max = 2047;
        int[] map = new int[2048];
        for (int i = 0; i < nums.length; i++) {
            int dif = map[(target - nums[i]) & max];
            if (dif != 0) {
                return new int[]{dif - 1, i};
            }
            map[(nums[i]) & max] = i + 1;
        }
        return null;
    }


    public static int[] twoSum3_1(int[] nums, int target) {
        //找合适的max值
        int max = 2047;
        int maxnunm = nums[0],minnum=nums[0];
        for(int i = 0;i<nums.length;i++){
            if(nums[i]>maxnunm)
                maxnunm = nums[i];
            if(nums[i]<minnum)
                minnum = nums[i];
        }
        max = Math.max(max,(maxnunm-minnum));
        max = CommenUtil.getNum2(max);

        int[] map = new int[max+1];
        for (int i = 0; i < nums.length; i++) {
            int dif = map[(target - nums[i]) & max];
            if (dif != 0) {
                return new int[]{dif - 1, i};
            }
            map[(nums[i]) & max] = i + 1;
        }
        return null;
    }


}
