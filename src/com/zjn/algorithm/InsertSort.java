package com.zjn.algorithm;

/**
 * InsertSort 插入排序
 *
 * @author       zjn
 * @date         2019/11/18
 *
 **/
public class InsertSort {
	public static void main(String[] args) {

	}

	public static void sort(int[] arrays) {
		int current;
		for (int i = 0; i < arrays.length - 1; i++) {
			current = arrays[i + 1];
			// 默认已排好序的子序列最大元素下标
			int preIndex = i;
			// 遍历已排好序的子序列 如果比插入值比当前子序列元素小 则当前子元素下标增加
			while (preIndex >= 0 && current < arrays[preIndex]) {
				arrays[preIndex + 1] = arrays[preIndex];
				preIndex--;
			}
			//经过上面遍历 找到第一个比current小的元素preIndex,然后把当前元素赋值给preIndex+1下标元素
			arrays[preIndex + 1] = current;
		}
	}

}
