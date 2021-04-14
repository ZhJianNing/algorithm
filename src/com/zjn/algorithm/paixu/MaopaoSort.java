package com.zjn.algorithm.paixu;

import com.zjn.algorithm.util.CommenUtil;

import java.util.Arrays;

/**
 * MaopaoSort 冒泡排序
 * 1、比较相邻的元素。如果第一个比第二个大，就交换它们两个；
 * 2、对每一对相邻元素作同样的工作，从开始第一对到结尾的最后一对，这样在最后的元素应该会是最大的数；
 * 3、针对所有的元素重复以上的步骤，除了最后一个；
 *
 * 重复步骤1~3，直到排序完成
 *
 * @author zjn
 * @date 2019/10/16
 **/
public class MaopaoSort {
    public static void main(String[] args) {
    	int array[] = {3,9,1,4,2,7,8,6,5};
    	try {
    		//优化前
    		sort(array);
    	}catch(Exception e) {
    		e.printStackTrace();
    	}
    	
    	int array2[] = {3,9,1,4,2,7,8,6,5};
    	try {
    		//优化后
    		sort2(array2);
    	}catch(Exception e) {
    		e.printStackTrace();
    	}
    	

    } 
    
    //优化前
    public static void sort(int[] array) {
    	int n = array.length;
    	for(int i=0;i<n;i++) {
    		for(int j=0;j<n-i-1;j++) {
    			if(array[j]>array[j+1]) {
    				CommenUtil.swap(array,j,j+1);
    			}
    		}
    	//	System.out.println("第"+(i+1)+"轮后："+Arrays.toString(array));
    	}
    }
    
    //优化前
    public static void sort2(int[] array){
    	int n = array.length;
    	for(int i=0;i<n;i++) {
    		//设置一个标识来检测每轮比较是否产生了交换，如果没有交换说明已经排好序了
    		boolean isFinish = true;    		
    		for(int j=0;j<n-i-1;j++) {
    			if(array[j]>array[j+1]) {
    				CommenUtil.swap(array,j,j+1);
                    isFinish = false;
    			}
    		}
    	//	System.out.println("第"+(i+1)+"轮后："+Arrays.toString(array));
    		if(isFinish) {
    			break;
    		}
    	}
    }
}
