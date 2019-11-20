package com.zjn.algorithm.util;

import java.util.Random;

/**
 * CommenUtil
 *
 * @author zjn
 * @date 2019/11/18
 **/
public class CommenUtil {

    /** 打印数组
     * @param null
     * @return :
     * @author : zjn
     * @date : 2019/11/18
     */
    public static void display(int[] data) {
        for (int i = 0; i < data.length; i++) {
            System.out.print(data[i]);
            System.out.print(" ");
        }
        System.out.println(" ");
    }

    /** 数据交换元素
     * @param null
     * @return :
     * @author : zjn
     * @date : 2019/11/18
     */
    public static void swap(int[] array,int i,int j) {
        int temp = array[i];
        array[i]=array[j];
        array[j]=temp;
    }

    public static long getCurTime(){
        return System.currentTimeMillis();
    }

    public static void inputCostTime(String type,long start){
        System.out.println(type+"排序算法耗时："+ (System.currentTimeMillis()-start)+"ms");
    }
    
    /**
     * 生成测试数组
     *
     * @param
     * @return :
     * @author : zjn
     * @date : 2019/11/18
     */
    public static int[] generateArray(int sum) {
        int[] array = new int[sum];
        //生成数组
        for (int i = 0; i < sum - 1; i++) {
            array[i] = i + 1;
        }
        //随机打乱
        for (int i = 0; i < sum - 1; i++) {
            int r = i + new Random().nextInt(sum - 1 - i);     // between i and N-1
            Integer temp = array[i];
            array[i] = array[r];
            array[r] = temp;
        }

        return array;
    }
}
