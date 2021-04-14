package com.zjn.algorithm.paixu;

import com.zjn.algorithm.util.CommenUtil;

/**
 * ShellSort    希尔排序
 *
 * @author zjn
 * @date 2019/11/19
 **/
public class ShellSort {
    public static void main(String[] args) {
        int[] data = {2, 9, 3, 0, 5, 8};
        sort(data);
        CommenUtil.display(data);
    }

    public static void sort(int[] arrays) {
        int len = arrays.length;
        //subArraysNum表示分成子数组的个数
        int temp, subArraysNum = len / 2;
        //分成的子数组个数越来越少，容量越来越大（初始容量2），直到和arrays容量一样（即不分组）
        while (subArraysNum > 0) {
            //从subArraysNum到len遍历可以得到subArraysNum个数组
            for (int i = subArraysNum; i < len; i++) {
                //子数组后面的元素
                temp = arrays[i];
                //获取子数组前面的下标
                int preIndex = i - subArraysNum;
                //如果前一个下标大于等于0  并且前一个元素大于后面的元素（类似于插入排序）
                while (preIndex >= 0 && arrays[preIndex] > temp) {
                    //把前一个元素的值赋值给子数组后面的元素
                    arrays[preIndex + subArraysNum] = arrays[preIndex];
                    //下标减去子数组个数（即间隔数）
                    preIndex -= subArraysNum;
                }
                // temp即arrays[subArraysNum]的值
                arrays[preIndex + subArraysNum] = temp;
            }
            //subArraysNum=subArraysNum/2
            subArraysNum /= 2;
        }
    }

}
