package com.zjn.algorithm;

import com.zjn.algorithm.util.CommenUtil;

import java.util.Arrays;

/**
 * MergeSort    归并排序
 * 和选择排序一样，归并排序的性能不受输入数据的影响，但表现比选
 * 择排序好的多，因为始终都是O(n log n）的时间复杂度。代价是需要额外的内存空间。
 * <p>
 * 算法描述:
 * 把长度为n的输入序列分成两个长度为n/2的子序列；
 * 对这两个子序列分别采用归并排序；
 * 将两个排序好的子序列合并成一个最终的排序序列
 *
 * @author zjn
 * @date 2019/11/19
 **/
public class MergeSort {
    public static void main(String[] args) {

        int[] data = {2, 9, 3, 0, 5, 8};
        CommenUtil.display(sort(data));
    }

    public static int[] sort(int[] arrays) {
        //设置递归临界条件
        if (arrays.length < 2) return arrays;
        //分割数据
        int midIndex = arrays.length / 2;
        //Arrays.copyOfRange   [xxx,xxx)
        //额外空间
        int[] left = Arrays.copyOfRange(arrays, 0, midIndex);
        int[] right = Arrays.copyOfRange(arrays, midIndex, arrays.length);
        return merge(sort(left), sort(right));

    }

    /**
     * 合并两个已排好序的子序列
     *
     * @param
     * @return :
     * @author : zjn
     * @date : 2019/11/19
     */
    public static int[] merge(int[] left, int[] right) {
        int[] result = new int[left.length + right.length];
        for (int index = 0, leftIndex = 0, rightIndex = 0; index < left.length + right.length; index++) {
            //left数组元素插入完成后，直接从right数组中取值
            if (leftIndex >= left.length) {
                result[index] = right[rightIndex++];
//                result[index] = right[rightIndex];
//                rightIndex++;
                //right数组元素插入完成后，直接从left数组中取值
            } else if (rightIndex >= right.length) {
                result[index] = left[leftIndex++];
            } else if (left[leftIndex] > right[rightIndex]) {
                result[index] = right[rightIndex++];
            } else {
                result[index] = left[leftIndex++];
            }

        }
        return result;

    }
}
