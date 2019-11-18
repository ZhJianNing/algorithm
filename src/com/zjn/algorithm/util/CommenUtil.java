package com.zjn.algorithm.util;

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
}
