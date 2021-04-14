package com.zjn.algorithm.paixu;

import java.util.ArrayList;

/**
 * RadixSort    基数排序（位数排序）
 * <p>
 * ***********************基数排序 vs 计数排序 vs 桶排序*******************
 * 这三种排序算法都利用了桶的概念，但对桶的使用方法上有明显差异：
 * 基数排序：根据键值的每位数字来分配桶
 * 计数排序：每个桶只存储单一键值
 * 桶排序：每个桶存储一定范围的数值
 * **************************************************************************
 *
 * @author zjn
 * @date 2019/11/24
 **/
public class RadixSort {
    public static void main(String[] args) {

    }

    public static void sort(int[] arrays) {
        //查找最大的数
        int max = arrays[0];
        for (int i = 0; i < arrays.length; i++) {
            max = Math.max(arrays[i], max);
        }
        //定义最大数的位数（10进制）
        int maxDigit = 0;
        while (max != 0) {
            max = max / 10;
            maxDigit++;
        }
        //定义几个个桶
        ArrayList<ArrayList<Integer>> bucketList = new ArrayList<>();
        for (int i = 0; i < 10; i++) {
            bucketList.add(new ArrayList<Integer>());
        }
        //定义模数和除数
        int mod = 10, div = 1;

        //根据元素的个位数、十位数、百位数直到（maxDigit+1）位数排序，每次排序都要装桶、清桶
        //循环maxDigit次时，所有的元素的个位数、十位数。。。最高位都是有序的
        for (int i = 0; i < maxDigit; i++, mod *= 10, div *= 10) {
            //遍历待排序数组，装桶
            for (int j = 0; j < arrays.length; j++) {
                //获取元素的个、十、百。。。位数
                int num = (arrays[j] % mod) / div;
                //根据相同位数大小装桶
                bucketList.get(num).add(arrays[j]);
            }
            //清桶
            int arraysIndex = 0;
            //遍历所有的桶，把桶里的数据再赋值给待排序数组（此时个位、十位。。。。已为有序）
            for(int j = 0;j<bucketList.size();j++){
                for (int k = 0; k < bucketList.get(j).size(); k++) {
                    arrays[arraysIndex++] = bucketList.get(j).get(k);
                }
                //遍历完清除这个桶的数据
                bucketList.get(j).clear();
            }
        }
    }
}
