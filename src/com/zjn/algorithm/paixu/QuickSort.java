package com.zjn.algorithm.paixu;

import com.zjn.algorithm.util.CommenUtil;

/**
 * QuickSort    快速排序
 * <p>
 * 快速排序的基本思想：
 * 通过一趟排序将待排记录分隔成独立的两部分，其中一部分记录的关键字均比另一部分
 * 的关键字小，则可分别对这两部分记录继续进行排序，以达到整个序列有序。
 * <p>
 * 具体实现：
 * 快速排序使用分治法来把一个串（list）分为两个子串（sub-lists）。具体算法描述如下：
 * 从数列中挑出一个元素，称为 “基准”（pivot）；
 * 重新排序数列，所有元素比基准值小的摆放在基准前面，所有元素比基准值大的摆在基准的后面（相同的数可以到任一边）。
 * 在这个分区退出之后，该基准就处于数列的中间位置。这个称为分区（partition）操作；
 * 递归地（recursive）把小于基准值元素的子数列和大于基准值元素的子数列排序。
 *
 * @author zjn
 * @date 2019/11/18
 **/
public class QuickSort {
    public static void main(String[] args) {
        int data[] = {3, 9, 1, 4, 2, 7, 8, 6, 5};
        long start =System.currentTimeMillis();
        sort(data, 0, data.length - 1);
        System.out.println((System.currentTimeMillis() - start) +"ms");
        CommenUtil.display(data);

    }

    public static void sort(int[] arrays, int low, int high) {
        //设置递归临界条件
        if (low < high) {
            //划分数组，活动划分下标
            int pivotkey = partition(arrays, low, high);
            //arrays[arrays]是两个子元素的中间值，不用再参与划分
            //依次对两个子表进行递归，知道low=high
            sort(arrays, low, pivotkey - 1);
            sort(arrays, pivotkey + 1, high);
        }

    }

    /**
     * 根据基准分区数组
     *
     * @param low
     * @param high
     * @return :
     * @author : zjn
     * @date : 2019/11/18 10:14
     */
    public static int partition(int[] arrays, int low, int high) {
        //选择第一个元素为基准
        int pivot = arrays[low];

        //一次外层循环，其实只进行了两次数组元素赋值（可以理解为一次高低位数据交换）
        while (low < high) {
            //如果高位数组元素大于或者等于基准值，不做数据交换，高位往前移一位
            while (low < high && arrays[high] >= pivot) {
                --high;
            }
            //如果高位数组元素小于基准值，把高位元素赋值给低位元素（此刻低位元素值在pivot中保存）
            arrays[low] = arrays[high];

            //如果低位元素数组元素小于等于基准值，不做数据交换，低位往后加一位
            while (low < high && arrays[low] <= pivot) {
                ++low;
            }
            //如果低位数组元素大于了基准值，把低位元素赋值给高位元素（之前值已经赋值给低位元素）
            arrays[high] = arrays[low];
        }
        //高低位汇合时（low=high)，low和high可以理解为中间位，
        // 所有数组元素已经根据基准位完成交换,把基准位的值赋值给这个中间位元素；
        arrays[low] = pivot;
        //返回基准值下标
        return low;
    }

}
