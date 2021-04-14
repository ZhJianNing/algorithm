package com.zjn.algorithm.paixu;

import com.zjn.algorithm.util.CommenUtil;

/**
 * HeapSort   堆排序
 * 用到了完全二叉树的部分性质
 * 完全二叉树放按层次遍历顺序存放入一个一维数组中：
 * 如果从下标从1开始存储，则编号为i的结点的主要关系为：
 * 双亲：下取整 （i/2）       左孩子：2i           右孩子：2i+1
 * <p>
 * 如果从下标从0开始存储，则编号为i的结点的主要关系为：
 * 双亲：下取整 （(i-1)/2）     左孩子：2i+1        右孩子：2i+2
 *
 * @author zjn
 * @date 2019/11/19
 **/
public class HeapSort {

    //声明全局变量，用于记录数组长度
    private static int len;

    public static void main(String[] args) {
        int[] data = {2, 9, 3, 0, 5, 8};
        sort(data);
        CommenUtil.display(data);
    }

    public static void sort(int arrays[]) {
        //设置全局变量
        len = arrays.length;
        //构建一个最大堆
        buildMaxHeap(arrays);

        //循环将堆首位（最大值）与末位交换，然后再重新调整最大值
        while (len > 0) {
            CommenUtil.swap(arrays, 0, len - 1);
            len--;
            //排除末位插入的最大值，再次调整为最大堆
            adjustHeap(arrays, 0);
        }
    }

    /**
     * 建立最大堆
     *
     * @param
     * @return :
     * @author : zjn
     * @date : 2019/11/19
     */
    public static void buildMaxHeap(int[] arrays) {
        //从最后一个非叶子节点开始向上构造最大堆
        //完全二叉树如果是满二叉树，每一层都是其前面所有层个数总和+1；
        //完全二叉树按层级把元素放入数组，最后一个非叶子节点下标为 (len-1)/2
        for (int i = (len - 1) / 2; i >= 0; i--) {
            adjustHeap(arrays, i);
        }
    }

    /**
     * 调整使之成为最大堆
     *
     * @param
     * @return :
     * @author : zjn
     * @date : 2019/11/19
     */
    public static void adjustHeap(int[] arrays, int i) {
        int maxIndex = i;
        //如果有左子树，且左子树大于父节点，则将最大指针指向左子树
        if (i * 2 + 1 < len && arrays[i * 2 + 1] > arrays[maxIndex]) {
            maxIndex = i * 2 + 1;
        }
        //如果有右子树，且右子树大于父节点，则将最大指针指向右子树
        if (i * 2 + 2 < len && arrays[i * 2 + 2] > arrays[maxIndex]) {
            maxIndex = i * 2 + 2;
        }
        //如果父节点不是最大值，则将父节点与最大值交换，并且递归调整与父节点交换的位置
        if (maxIndex != i) {
            CommenUtil.swap(arrays, maxIndex, i);
            //递归判断与父节点交换的元素，是否满足大于其子节点，再次调整
            adjustHeap(arrays, maxIndex);
        }
    }
}
