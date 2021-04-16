package com.zjn.algorithm.solution;

import java.util.ArrayDeque;
import java.util.ArrayList;
import java.util.List;
import java.util.Queue;

/**
 * BinaryTree
 * <p>
 * 给你一个二叉树，请你返回其按 层序遍历 得到的节点值。 （即逐层地，从左到右访问所有节点）。
 * 3
 * / \
 * 9  20
 * /  \
 * 15   7
 * 示例：
 * 二叉树：[3,9,20,null,null,15,7],
 * <p>
 * 输出：
 * [
 * [3],
 * [9,20],
 * [15,7]
 * ]
 *
 * @author zjn
 * @date 2021/4/14
 **/
public class BinaryTree {

    public List<List<Integer>> levelOrder(TreeNode root) {
        List<List<Integer>> result = new ArrayList<List<Integer>>();
        Queue<TreeNode> container = new ArrayDeque<TreeNode>();
        if (null == root) {
            return result;
        }
        container.offer(root);
        while (container.size() > 0) {
            int size = container.size();
            List<Integer> tmp = new ArrayList<>();
            while (size > 0) {
                size--;
                TreeNode treeNode = container.poll();
                System.out.print(treeNode.val + " ");
                tmp.add(treeNode.val);
                if (treeNode.left != null) {
                    container.offer(treeNode.left);
                }
                if (treeNode.right != null) {
                    container.offer(treeNode.right);
                }
            }
            System.out.println();
            result.add(tmp);

        }
        return result;
    }

}

//Definition for a binary tree node.
class TreeNode {
    int val;
    TreeNode left;
    TreeNode right;

    TreeNode() {
    }

    TreeNode(int val) {
        this.val = val;
    }

    TreeNode(int val, TreeNode left, TreeNode right) {
        this.val = val;
        this.left = left;
        this.right = right;
    }
}
