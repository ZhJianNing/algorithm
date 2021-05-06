package com.zjn.algorithm.solution;

import java.util.ArrayList;
import java.util.List;

/**
 * CommonAncestor
 *
 * @author zjn
 * @date 2021/5/6
 **/
public class CommonAncestor {



    //递归方法，暂时还没有理解，这是一个先序遍历
    //然后找
    public TreeNode lowestCommonAncestor(TreeNode root, TreeNode p, TreeNode q) {
        if (root == null) {
            return null;
        }
        if (p.val == root.val || q.val == root.val) {//这个就是只要有一个是根节点那么公共祖先一定是根节点,这个画图就可以了
            return root;
        }
        TreeNode treeNode = lowestCommonAncestor(root.left, p, q);//这个其实就是看一下p或者q是不是在左子树这里
        TreeNode treeNode1 = lowestCommonAncestor(root.right, p, q);//这个其实就是看一下p或者q是不是在右子树这里
        if (treeNode != null && treeNode1 != null) {//这说明就是p和q一个在左子树一个在右子树
            return root;
        } else if (treeNode == null && treeNode1 != null) {
            //treeNode==null说明整颗左子树这里其实没有p或者q
            //因为只有有一个p或者q就会返回root,不会返回null
            //都在右子树这里
            return treeNode1;
        } else if (treeNode != null && treeNode1 == null) {//这其实就说明整颗右子树这里没有p或者q
            return treeNode;
        } else {//两个都是null
            return null;
        }
    }


    //查找路径方法
    public TreeNode lowestCommonAncestor2(TreeNode root, TreeNode p, TreeNode q) {
        //根节点到p节点的路径
        List<TreeNode> path1 = new ArrayList<>();
        //根节点到q节点的路径
        List<TreeNode> path2 = new ArrayList<>();
        getPath(root,p,path1);
        getPath(root,q,path2);

        TreeNode result=null;
        int n = Math.min(path1.size(),path2.size());
        //保留最后一个相等的节点即为公共节点
        for(int i=0;i<n;i++){
            if(path1.get(i)==path2.get(i))
                result = path1.get(i);
        }
        return result;
    }
    //前序遍历搜索节点p或q
    void getPath(TreeNode root,TreeNode node,List<TreeNode> path){
        if(root==null)
            return ;
        path.add(root);
        if(root == node)
            return ;
        if(path.get(path.size()-1)!=node){
            getPath(root.left,node,path);
        }
        if(path.get(path.size()-1)!=node){
            getPath(root.right,node,path);
        }
        if(path.get(path.size()-1)!=node){
            path.remove(path.size()-1);
        }
    }
}
