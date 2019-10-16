package com.zjn.algorithm;

import java.util.Arrays;

/**
 * MaopaoSort 冒泡排序
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
    private static void sort(int[] array) throws Exception{
    	if(array == null || array.length == 0) {
    		throw new Exception("数组为null或者为空！！！");
    	}
    	System.out.println("优化之前的冒泡排序");
    	int n = array.length;
    	for(int i=0;i<n;i++) {
    		for(int j=0;j<n-i-1;j++) {
    			if(array[j]>array[j+1]) {
    				swap(array,j,j+1);
    			}
    		}
    		System.out.println("第"+(i+1)+"轮后："+Arrays.toString(array));
    	}
    }
    
    //优化前
    private static void sort2(int[] array) throws Exception{
    	if(array == null || array.length == 0) {
    		throw new Exception("数组为null或者为空！！！");
    	}
    	System.out.println("优化之后的冒泡排序");
    	int n = array.length;
    	for(int i=0;i<n;i++) {
    		//设置一个标识来检测每轮比较是否产生了交换，如果没有交换说明已经排好序了
    		boolean isFinish = true;    		
    		for(int j=0;j<n-i-1;j++) {
    			if(array[j]>array[j+1]) {
    				swap(array,j,j+1);
    				isFinish = false;
    			}
    		}
    		System.out.println("第"+(i+1)+"轮后："+Arrays.toString(array));
    		if(isFinish) {
    			break;
    		}
    	}
    }
    
    
    
    private static void swap(int[] array,int i,int j) {
    	int temp = array[i];
    	array[i]=array[j];
    	array[j]=temp;
    }
}
