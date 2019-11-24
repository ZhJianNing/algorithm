package com.zjn.algorithm.compare;

import com.zjn.algorithm.*;
import com.zjn.algorithm.util.CommenUtil;

import java.util.List;

/**
 * Test
 *
 * @author zjn
 * @date 2019/11/18
 **/
public class Test {
    public static void main(String[] args) {
        int data[] = CommenUtil.generateArray(30000);
        //CommenUtil.display(data);
        //冒泡排序
        int[] maopao1 = data.clone();
        long start1 = CommenUtil.getCurTime();
        MaopaoSort.sort(maopao1);
        CommenUtil.inputCostTime("冒泡1", start1);
        //CommenUtil.display(maopao1);

        int[] maopao2 = data.clone();
        long start2 = CommenUtil.getCurTime();
        MaopaoSort.sort2(maopao2);
        CommenUtil.inputCostTime("冒泡2", start2);
        //CommenUtil.display(maopao2);

        int[] quick = data.clone();
        long start3 = CommenUtil.getCurTime();
        QuickSort.sort(quick, 0, quick.length - 1);
        CommenUtil.inputCostTime("快速", start3);
        //CommenUtil.display(quick);

        int[] select = data.clone();
        long start4 = CommenUtil.getCurTime();
        SelectSort.sort(select);
        CommenUtil.inputCostTime("选择", start4);
        //CommenUtil.display(select);

        int[] insert = data.clone();
        long start5 = CommenUtil.getCurTime();
        InsertSort.sort(insert);
        CommenUtil.inputCostTime("插入", start5);
        //CommenUtil.display(insert);

        int[] heap = data.clone();
        long start6 = CommenUtil.getCurTime();
        HeapSort.sort(heap);
        CommenUtil.inputCostTime("堆", start6);
        //CommenUtil.display(heap);

        int[] merge = data.clone();
        long start7 = CommenUtil.getCurTime();
        int[] mergeResult = MergeSort.sort(merge);
        CommenUtil.inputCostTime("归并", start7);
        //CommenUtil.display(mergeResult);


        int[] shell = data.clone();
        long start8 = CommenUtil.getCurTime();
        ShellSort.sort(shell);
        CommenUtil.inputCostTime("希尔", start8);
        //CommenUtil.display(shell);

        int[] counting = data.clone();
        long start9 = CommenUtil.getCurTime();
        CountingSort.sort(counting);
        CommenUtil.inputCostTime("计数", start9);
        //CommenUtil.display(counting);


        List<Integer> bucket2 = CommenUtil.intArrayToList(data);
        long start10 = CommenUtil.getCurTime();
        //需要调整桶的大小
        List<Integer> result = BucketSort.sort(bucket2, 5);
        CommenUtil.inputCostTime("桶", start10);
        //CommenUtil.displayList(result);

        int[] radix = data.clone();
        long start11 = CommenUtil.getCurTime();
        //需要调整桶的大小
        RadixSort.sort(radix);
        CommenUtil.inputCostTime("基数", start11);
        //CommenUtil.display(radix);

        CommenUtil.displayRank();


    }
}
