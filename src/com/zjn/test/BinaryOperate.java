package com.zjn.test;

import com.zjn.algorithm.util.CommenUtil;

/**
 * BinaryOperate
 *
 * @author zjn
 * @date 2019/11/25
 **/
public class BinaryOperate {
    public static void main(String[] args) {
        int[] data = {-5,2043};
        for (int i = 0; i < data.length; i++) {
            System.out.println(CommenUtil.intToBinary(data[i]));
        }
        //int max = 1023;
        int max = 2047;
        //int max = 4095;
        System.out.println("max:"+CommenUtil.intToBinary(max)+"("+max+")进行与运算");


        for (int i = 0; i < data.length; i++) {
            System.out.println(data[i]+"与"+max+"进行位与运算结果："+CommenUtil.intToBinary(data[i] & max)
                    +"("+(data[i]&max)+")");
        }

    }
}
