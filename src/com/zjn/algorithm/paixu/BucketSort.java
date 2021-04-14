package com.zjn.algorithm.paixu;

import com.zjn.algorithm.util.CommenUtil;

import java.util.ArrayList;
import java.util.List;

/**
 * BucketSort   桶排序
 * 桶排序是计数排序的升级版。它利用了函数的映射关系，高效与否的关键就在于这个映射函数的确定。
 * 桶排序 (Bucket sort)的工作的原理：假设输入数据服从均匀分布，将数据分到有限数量的桶里，每个
 * 桶再分别排序（有可能再使用别的排序算法或是以递归方式继续使用桶排序进行排
 * 算法描述
 * 人为设置一个BucketSize，作为每个桶所能放置多少个不同数值（例如当BucketSize==5时，该桶可以存放
 * ｛1,2,3,4,5｝这几种数字，但是容量不限，即可以存放100个3）；遍历输入数据，并且把数据一个一个放到对应的桶里去；
 * 对每个不是空的桶进行排序，可以使用其它排序方法，也可以递归使用桶排序；从不是空的桶里把排好序的数据拼接起来。
 * <p>
 * 注意，如果递归使用桶排序为各个桶排序，则当桶数量为1时要手动减小BucketSize增加下一循环桶的数量，
 * 否则会陷入死循环，导致内存溢出。
 *
 * @author zjn
 * @date 2019/11/22
 **/
public class BucketSort {
    public static void main(String[] args) {
        int[] data = {2, 9, 3, 0, 5, 8};
        List<Integer> dataList = CommenUtil.intArrayToList(data);
        List<Integer> sortedList = sort(dataList,4);
        CommenUtil.displayList(sortedList);

    }

    /**
     * 桶排序，不同的桶，整体数据是有序的，但是单个桶内的数据是无序的，
     * 所以该方法就是递归装桶，直到桶内数据成为一个再排序
     * <p>
     * 由于每个桶的容量没有限制，所以过程用来arraylist，不用自己来维护数组长度
     *
     * @param
     * @return :
     * @author : zjn
     * @date : 2019/11/22
     */
    public static List<Integer> sort(List<Integer> arrays, int bucketSize) {
        //每个桶中的数据小于2的时候不需要再分桶了
        if (arrays == null || arrays.size() < 2)
            return arrays;
        //查找数组中的最小值和最大值
        int min = arrays.get(0), max = arrays.get(0);
        for (int i = 0; i < arrays.size(); i++) {
            if (arrays.get(i) > max)
                max = arrays.get(i);
            if (arrays.get(i) < min)
                min = arrays.get(i);
        }
        //根据每个桶可以放的数值范围，计算桶的数量
        int bucketCount = (max - min) / bucketSize + 1;
        ArrayList<ArrayList<Integer>> bucketArray = new ArrayList<>(bucketCount);
        //用来存放结果
        ArrayList<Integer> resultArray = new ArrayList<>();
        //在桶的数组里添加桶
        for (int i = 0; i < bucketCount; i++) {
            bucketArray.add(new ArrayList<Integer>());
        }
        //把待排序数组的元素放到不同的桶里
        for (int i = 0; i < arrays.size(); i++) {
            bucketArray.get((arrays.get(i) - min) / bucketSize).add(arrays.get(i));
        }
        //从桶里拿出数据
        for (int i = 0; i < bucketCount; i++) {
            //如果待排序数组的元素个数此时还大于1，而桶的数量就一个，后续分桶
            //只不过是把待排序数组元素全copy到这个桶里，后续再递归分桶也是一直重复这个动作造成死循环
            //所以这里要调整桶的容积，在递归分桶
            if (bucketCount == 1) {
                bucketSize--;
            }
            //递归分桶
            List<Integer> temp = sort(bucketArray.get(i), bucketSize);
            for (int j = 0; j < temp.size(); j++) {
                resultArray.add(temp.get(j));
            }
        }
        return resultArray;
    }
}
