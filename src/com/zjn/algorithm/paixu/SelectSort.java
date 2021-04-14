package com.zjn.algorithm.paixu;

import com.zjn.algorithm.util.CommenUtil;

/**
 * SelectSort	选择排序
 *
 * @author       zjn
 * @date         2019/11/18
 *
 **/
public class SelectSort {
	public static void main(String[] args) {

	}

	public static void sort(int[] arrays) {
		for(int i = 0;i<arrays.length;i++) {
			//记录元素最小的下标(默认判断的第一个元素是最小值)
			int minIndex = i;
			for(int j = i;j<arrays.length;j++) {
				if(arrays[j] < arrays[minIndex]) {
					//如果找到比当前小的值记录其下标
					minIndex = j;
				}
			}
			//比较完之后把最小值放到i位置
			CommenUtil.swap(arrays, i, minIndex);
		}
	}
}
