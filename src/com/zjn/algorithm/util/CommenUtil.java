package com.zjn.algorithm.util;

import java.util.*;
import java.util.stream.Collectors;

/**
 * CommenUtil
 *
 * @author zjn
 * @date 2019/11/18
 **/
public class CommenUtil {

    private static Map<String, Long> costTimeMap = new HashMap<String, Long>();

    /**
     * 打印数组
     *
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

    /**
     * 打印list集合
     *
     * @param null
     * @return :
     * @author : zjn
     * @date : 2019/11/22
     */
    public static void displayList(List<Integer> data) {
        for (int i = 0; i < data.size(); i++) {
            System.out.print(data.get(i));
            System.out.print(" ");
        }
        System.out.println(" ");
    }

    /**
     * 数据交换元素
     *
     * @param null
     * @return :
     * @author : zjn
     * @date : 2019/11/18
     */
    public static void swap(int[] array, int i, int j) {
        int temp = array[i];
        array[i] = array[j];
        array[j] = temp;
    }

    public static long getCurTime() {
        return System.currentTimeMillis();
    }

    public static void inputCostTime(String type, long start) {
        long cost = System.currentTimeMillis() - start;
        System.out.println(type + "排序算法耗时：" + cost + "ms");
        costTimeMap.put(type + "排序", cost);
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

    /**
     * int数组转list
     *
     * @param :data
     * @return :
     * @author : zjn
     * @date : 2019/11/22
     */
    public static List<Integer> intArrayToList(int[] data) {
        return Arrays.stream(data).boxed().collect(Collectors.toList());
    }


    /**
     * 展示算法排名
     *
     * @param
     * @return :
     * @author : zjn
     * @date : 2019/11/24
     */
    public static void displayRank() {
        //把entry放到list中
        List<Map.Entry<String, Long>> tempList = new ArrayList<>(costTimeMap.entrySet());
        //定义map排序规则
        Collections.sort(tempList, new Comparator<Map.Entry<String, Long>>() {
            @Override
            public int compare(Map.Entry<String, Long> o1, Map.Entry<String, Long> o2) {
                return (int) (o1.getValue() - o2.getValue());
            }
        });
        int i = 0;
        System.out.println("性能排名：");
        for (Map.Entry<String, Long> entry : tempList) {
            System.out.println("第" + (++i) + "名：" + entry.getKey() + "消耗了" + entry.getValue() + "ms");
        }
    }
}
