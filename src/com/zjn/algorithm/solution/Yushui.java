package com.zjn.algorithm.solution;

import java.util.Stack;

/**
 * Yushui 柱子接雨水问题
 * <p>
 * 参考：https://mp.weixin.qq.com/s/f9ebzbwymR8jQeUDxjeCDA
 *
 * @author zjn
 * @date 2021/4/23
 **/
public class Yushui {
    public static void main(String[] args) {
        int[] height = {4, 2, 0, 3, 2};
        System.out.println(trap4(height));
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


    /*
    3、双指针  时间O(N) 空间O(1)
    在上述的动态规划方法中，我们用二维数组来存储每个柱子左右两侧的最大高度，但我们递推累加每个柱子的储水高度时其实只用
    到了 dp[i][0]和 dp[i][1] 两个值，因此我们递推的时候只需要用 int leftMax 和 int rightMax 两个变量就行了。

    即将上述代码中的递推公式：
    //todo 这一步公式的转变没有想明白【从两侧遍历，如果目前左侧最大值，大于右侧最大值，则继续从右侧遍历，一定要从最大值小的一侧遍历】
    res += Math.min(dp[i][0], dp[i][1]) - height[i]; 优化成：res += Math.min(leftMax, rightMax) - height[i];

    注意这里的 leftMax 是从左端开始递推得到的，而 rightMax 是从右端开始递推得到的。
    因此遍历每个柱子，累加每个柱子的储水高度时，也需要用 left 和 right 两个指针从两端开始遍历。
    */
    public static int trap3(int[] height) {
        int res = 0, leftMax = 0, rightMax = 0, left = 0, right = height.length - 1;
        while (left <= right) {
            if (leftMax <= rightMax) {
                leftMax = Math.max(leftMax, height[left]);
                res += leftMax - height[left++];
            } else {
                rightMax = Math.max(rightMax, height[right]);
                res += rightMax - height[right--];
            }
        }
        return res;
    }

    /*
    4、单调栈  时间O(N) 空间O(N)
    单调栈就是栈里面存放的数据都是有序的，所以可以分为单调递增栈和单调递减栈两种。
    单调递增栈【单调栈】就是从栈底到栈顶是从大到小
    单调递减栈【单调递减栈】就是从栈底到栈顶是从小到大

    单调栈是本文想要重点说明的一个方法～
    因为本题是一道典型的单调栈的应用题。
    简单来说就是当前柱子如果小于等于栈顶元素，说明形不成凹槽，则将当前柱子入栈；反之若当前柱子大于栈顶元素，说明形成了凹槽，
    于是将栈中小于当前柱子的元素pop出来，将凹槽的雨水大小累加到结果中。
  */
    public static int trap4(int[] height) {
        //次栈存入height的下标，根据height中元素的值，需要满足单调栈的特性，即存入的下标对应的值应满足从栈底到栈顶是从大到小的顺序
        Stack<Integer> stack = new Stack<>();
        int res = 0;
        //遍历每个柱体
        for (int i = 0; i < height.length; i++) {
            while (!stack.isEmpty() && height[stack.peek()] < height[i]) {
                int leftIndex = stack.pop();
                //如果栈顶元素一直相等，那么全部pop出去
                while (!stack.isEmpty() && height[stack.peek()] == height[i]) {
                    stack.pop();
                }
                if (!stack.isEmpty()) {
                    // stack.peek()是此次接住的雨水的左边界的位置，右边界是当前的柱体，即i。
                    // Math.min(height[stack.peek()], height[i]) 是左右柱子高度的min，减去height[leftIndex]就是雨水的高度。
                    // i - stack.peek() - 1 是雨水的宽度。
                    res += ((Math.min(height[stack.peek()], height[i])) - height[leftIndex]) * (i - stack.peek() - 1);
                }
            }
            stack.push(i);
        }
        return res;
    }
}
