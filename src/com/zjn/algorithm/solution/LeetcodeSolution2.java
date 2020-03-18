package com.zjn.algorithm.solution;

/**
 * LeetcodeSolution2
 * 给出两个 非空 的链表用来表示两个非负的整数。其中，它们各自的位数是按照 逆序 的方式存储的，并且它们的每个节点只能存储 一位 数字。
 * 如果，我们将这两个数相加起来，则会返回一个新的链表来表示它们的和。
 * 您可以假设除了数字 0 之外，这两个数都不会以 0 开头。
 * <p>
 * 示例：
 * 输入：(2 -> 4 -> 3) + (5 -> 6 -> 4)
 * 输出：7 -> 0 -> 8
 * 原因：342 + 465 = 807
 * <p>
 * <p>
 * 思路：这个思路怎么样， L1 、L2的和 存到L1 ，
 * 1、没有L2 就返回 L1
 * 2、有L2没L1，把L2接到L1 就可以，
 * 3、都不空，就加他们， 然后是进位 ， 可能会有连续2次进位，
 * 如进1后 加的下一位是9 ，那还得进1一次，由于l2没数了，不会触发进位，所以进位连续判断2次。
 *
 * @author zjn
 * @date 2020/3/18
 **/
public class LeetcodeSolution2 {

    /*
    定义数据结构
     */
    static class ListNode {
        int val;
        ListNode next;

        ListNode(int x) {
            val = x;
        }
    }

    public static void main(String[] args) {
        //(2 -> 4 -> 3) ,代表342；
        ListNode l1 = new ListNode(2);
        ListNode l2 = new ListNode(4);
        ListNode l3 = new ListNode(3);
        l1.next = l2;
        l2.next = l3;

        //(5 -> 6 -> 4),代表数字465；
        ListNode l4 = new ListNode(5);
        ListNode l5 = new ListNode(6);
        ListNode l6 = new ListNode(4);
        l4.next = l5;
        l5.next = l6;


        ListNode result = addTwoNumbers(l1, l4);

        int resultNum = 0;
        int wei = 1;

        while (result != null) {
            resultNum += result.val * wei;
            wei *= 10;
            result = result.next;
        }

        System.out.println(resultNum);

    }

    public static ListNode addTwoNumbers(ListNode l1, ListNode l2) {
        if (l1 == null) {
            return l2;
        } else if (l2 == null) {
            return l1;
        } else {
            l1.val = l1.val + l2.val;
            jinwei(l1);
            l1.next = addTwoNumbers(l1.next, l2.next);
            return l1;
        }
    }

    public static void jinwei(ListNode l1) {
        if (l1.val > 9) {
            if (l1.next == null) {
                l1.next = new ListNode(l1.val / 10);
            } else {
                l1.next.val += l1.val / 10;
                //l1.next.val有可能此时是9，有可能还需进位，需要再次判断
                jinwei(l1.next);
            }
            //进位后l1.val = l1.val % 10;
            l1.val %= 10;
        }

    }
}
