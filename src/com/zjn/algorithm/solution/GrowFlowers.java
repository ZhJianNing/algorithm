package com.zjn.algorithm.solution;

/**
 * GrowFlowers
 * <p>
 * ## 假设你有一个很长的花坛，一部分地块种植了花，另一部分却没有。可是，花卉不能种植在相邻
 * ## 的地块上，它们会争夺水源，两者都会死去。给定一个花坛（表示为一个数组包含0和1，其中0表
 * ## 示没种植花，1表示种植了花），和一个数 n 。能否在不打破种植规则的情况下种入 n 朵花？能
 * ## 则返回True，不能则返回False。
 * ## 示例 1:
 * ## 输入: flowerbed = [1,0,0,0,1], n = 1
 * ## 输出: True
 *
 * @author zjn
 * @date 2021/4/14
 **/
public class GrowFlowers {
    public static void main(String[] args) {
        int[] flwerbed = {1, 0, 0, 1, 0, 0, 0, 1};
        int n = 2;
        System.out.println(canFlower(flwerbed, n));
    }

    static boolean canFlower(int[] flowerbed, int n) {
        if (n == 0) {
            return true;
        }
        //为花坛一头一尾创建连个虚拟花位0
        int[] copy = new int[flowerbed.length + 2];
        copy[0] = 0;
        for (int i = 0; i < flowerbed.length; i++) {
            copy[i + 1] = flowerbed[i];
        }
        copy[flowerbed.length + 1] = 0;

        int a = 0;
        for (int i = 0; i < copy.length; i++) {

            if (copy[i] == 0) {
                a++;
            } else {
                a = 0;
            }
            if (a == 3) {
                n--;
                a = 0;
                i--;
            }
            if (n == 0) {
                return true;
            }
        }
        return false;
    }

    /*
    简单地使用贪心的思路，能够种花就种，不能种就往后继续尝试，直到所有格子都试过为止。
    下面直接上代码，每行都有注释，保证能看明白。
    */
    public static boolean canPlaceFlowers(int[] flowerbed, int n) {
        for (int i = 0; i < flowerbed.length; i++) {
            if (n <= 0) {        // 如果已经种够花了，可以提前返回true
                return true;
            }
            if (flowerbed[i] == 1) {     // 如果已经种过花了，则不能再种了
                continue;
            }
            if (i > 0 && flowerbed[i - 1] == 1) {        // 如果上一个格子已经种过花了，则当前这格不能种花
                continue;
            }
            if (i < flowerbed.length - 1 && flowerbed[i + 1] == 1) {   // 如果下一个格子已经种过花了，则当前这格不能种花
                continue;
            }
            // 可以种花了，并且记录次数
            flowerbed[i] = 1;
            n--;
        }
        return n <= 0;
    }


}
