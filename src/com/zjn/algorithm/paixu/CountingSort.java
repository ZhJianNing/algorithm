package com.zjn.algorithm.paixu;

import java.util.Arrays;

import com.zjn.algorithm.util.CommenUtil;

/**
 * CountingSort 计数排序
 * @author Administrator
 *
 */
public class CountingSort {
	public static void main(String[] args) {
		int[] data = {2, 9, 3, 0, 5, 8};
        sort(data);
        CommenUtil.display(data);
		
	}
	
	public static void sort(int[] arrays) {
		//找到数组中的最大值和最小值(取第一个元素默认为最小值和最大值)
		int min=arrays[0],max=arrays[0];
		//遍历查找最小值和最大值
		for(int i = 0;i<arrays.length;i++) {
			if(arrays[i]<min) {
				min = arrays[i];
			}
			if(arrays[i]>max) {
				max = arrays[i];
			}
		}
		//定义一个偏移量
		int bias = 0 - min;
		//定义一个额外是数组计数
		int[] bucket = new int[max-min+1];
		//给计数数组所有预算赋值为0
		Arrays.fill(bucket, 0);
		for(int i = 0;i<arrays.length;i++) {
			//以arrays中元素的值，为bucket的下标，值相同，则bucket  +1操作
			bucket[arrays[i]]++;
		}
		
		//遍历bucket,把值重新赋值给arrays
		//定义bucket的下标
/*		int bucketIndex = 0;
		for(int i =0;i<arrays.length;i++) {
			//bucket[bucketIndex] == 0 标识arrays没有值为bucketIndex的元素
			if(bucket[bucketIndex] == 0) {
				//找下一个元素
				bucketIndex++;
				//bucket[bucketIndex] == 0时，arrays[i]没有拿到值，不应该对i++操作
				i--;//和for循环中的i++对消掉
			}else {
				arrays[i]=bucketIndex-bias;
				//bucket对应计数元素-1操作
				bucket[bucketIndex]--;
			}
		}*/
		int bucketIndex = 0,arraysIndex = 0;
		while(arraysIndex<arrays.length) {
			if(bucket[bucketIndex]==0) {
				bucketIndex++;
			}else {
				arrays[arraysIndex] = bucketIndex - bias;
				bucket[bucketIndex]--;
				arraysIndex++;
			}			
		}
	}

}
