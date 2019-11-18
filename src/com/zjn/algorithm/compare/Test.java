package com.zjn.algorithm.compare;

import java.util.Random;

import com.zjn.algorithm.InsertSort;
import com.zjn.algorithm.MaopaoSort;
import com.zjn.algorithm.QuickSort;
import com.zjn.algorithm.SelectSort;
import com.zjn.algorithm.util.CommenUtil;

/**
 * Test
 *
 * @author zjn
 * @date 2019/11/18
 **/
public class Test {
    public static void main(String[] args) {
        int data[] = generateArray(10000);
       // CommenUtil.display(data);
        //冒泡排序
        int[] maopao1 = data.clone();
        long start1 = CommenUtil.getCurTime();
        MaopaoSort.sort(maopao1);
        CommenUtil.inputCostTime("冒泡1",start1);
        //CommenUtil.display(maopao1);

        int[] maopao2 = data.clone();
        long start2 = CommenUtil.getCurTime();
        MaopaoSort.sort2(maopao2);
        CommenUtil.inputCostTime("冒泡2",start2);
        //CommenUtil.display(maopao2);

        int[] quick = data.clone();
        long start3 = CommenUtil.getCurTime();
        QuickSort.sort(quick,0,quick.length-1);
        CommenUtil.inputCostTime("快速",start3);
       // CommenUtil.display(quick);
        
        int[] select = data.clone();
        long start4 = CommenUtil.getCurTime();
        SelectSort.sort(select);
        CommenUtil.inputCostTime("选择",start4);
       // CommenUtil.display(quick);
        
        int[] insert = data.clone();
        long start5 = CommenUtil.getCurTime();
        InsertSort.sort(insert);
        CommenUtil.inputCostTime("插入",start5);
       // CommenUtil.display(quick);


    }

    /** 生成测试数组
     * @param
     * @return :
     * @author : zjn
     * @date : 2019/11/18
     */
    public static int[] generateArray(int sum){
        int[] array = new int[sum];
        //生成数组
        for(int i=0;i<sum-1;i++){
            array[i]=i+1;
        }
        //随机打乱
        for (int i = 0; i < sum-1; i++) {
            int r = i + new Random().nextInt(sum-1-i);     // between i and N-1
            Integer temp = array[i];
            array[i] = array[r];
            array[r] = temp;
        }

        return array;
    }
}
