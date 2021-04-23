package com.zjn.algorithm.solution;

/**
 * Yushui 柱子接雨水问题
 *
 * 参考：https://mp.weixin.qq.com/s/f9ebzbwymR8jQeUDxjeCDA
 *
 * @author zjn
 * @date 2021/4/23
 **/
public class Yushui {
    public static void main(String[] args) {
        int[] height = {4, 2, 0, 3, 2, 5};
        System.out.println(trap2(height));
    }

    /*
    1、暴力破解   时间O(N^2)  空间O(1)
    很明显每个柱子顶部可以储水的高度为：该柱子的左右两侧最大高度的较小者减去此柱子的高度，
    //因此我们只需要遍历每个柱子，累加每个柱子可以储水的高度即可。
    */
    public static int trap(int[] height) {
        //两端节点肯定不能存储雨水，不需要遍历
        int yushui = 0;
        for (int i = 1; i < height.length - 1; i++) {

            int leftMax = 0, rightMax = 0, max = 0;
            for (int j = 0; j < i; j++) {
                leftMax = Math.max(leftMax, height[j]);
            }
            for (int k = i + 1; k < height.length; k++) {
                rightMax = (rightMax >= height[k] ? rightMax : height[k]);
            }
            max = Math.min(leftMax, rightMax);
            if (max > height[i]) {
                yushui += (max - height[i]);
            }
        }
        return yushui;
    }

    /*
    2、动态规划   时间O(N) 空间O(N)
    在上述的暴力法中，对于每个柱子，我们都需要从两头重新遍历一遍求出左右两侧的最大高度，这里是有很多重复计算的，
    很明显最大高度是可以记忆化的，具体在这里可以用数组边递推边存储，也就是常说的动态规划，DP。

    具体做法：
    定义二维dp数组 int[][] dp = new int[n][2],其中，dp[i][0] 表示下标i的柱子左边的最大值，dp[i][1] 表示下标i的柱
    子右边的最大值。分别从两头遍历height数组，为 dp[i][0]和 dp[i][1] 赋值。同方法1，遍历每个柱子，累加每个柱子可
    以储水的高度。

     */
    public static int trap2(int[] height) {
        int yushui = 0;
        int n = height.length;
        if (n <= 2) {
            return 0;
        }
        //记录height中每个节点左右最大值
        int[][] dp = new int[n][2];
        //两端赋值
        dp[0][0] = 0;
        dp[n - 1][1] = 0;
        //查找每个节点的左边最大值,从左边开始查
        for (int i = 1; i < n; i++) {
            dp[i][0] = Math.max(dp[i - 1][0], height[i - 1]);
        }
        //查找每个节点的右边最大值，从右边开始查
        for (int i = n - 2; i > 0; i--) {
            dp[i][1] = Math.max(dp[i + 1][1], height[i + 1]);
        }
        //计算
        for (int i = 0; i < n; i++) {
            int max = Math.min(dp[i][0], dp[i][1]);
            if (max > height[i]) {
                yushui += (max - height[i]);
            }
        }
        return yushui;
    }
}
