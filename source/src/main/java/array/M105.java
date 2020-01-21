package array;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.LinkedList;
import java.util.List;

/**
 * 105. 从前序与中序遍历序列构造二叉树
 *
 根据一棵树的前序遍历与中序遍历构造二叉树。

 注意:
 你可以假设树中没有重复的元素。

 例如，给出

 前序遍历 preorder = [3,9,20,15,7]
 中序遍历 inorder = [9,3,15,20,7]
 返回如下的二叉树：

 3
 / \
 9  20
 /  \
 15   7
 */
public class M105 {

    public static void main(String[] args) {
        new M105().buildTree(new int[] {1,3,4},new int[] {1,3,4});
    }

    public class TreeNode {
        int val;
        TreeNode left;
        TreeNode right;
        TreeNode(int x) { val = x; }
    }

    public TreeNode buildTree(int[] preorder, int[] inorder) {
        if (preorder.length == 0) return null;

        return DFS(preorder, inorder, 0, 0, inorder.length);
    }

    /**
     *
     * @param preorder
     * @param inorder
     * @param preorderIdx 前序第一个数字
     * @param len 子树长度
     * @return
     */
    public TreeNode DFS(int[] preorder, int[] inorder, int preorderIdx, int inorderIdx, int len) {
        if (len == 0) return null;

        TreeNode node = new TreeNode(preorder[preorderIdx]);

        // 找到前序和中序第一个相同的数字，这之间的距离就是左子树的距离
        int count = 0;
        int i = inorderIdx;
        while (preorder[preorderIdx] != inorder[i]) {
            i++;
            count++;
        }

        // 计算左子树，前序第一个字母跳过，中序不变，树的长度就是count计数
        node.left = DFS(preorder, inorder, preorderIdx+1, inorderIdx, count);
        // 计算右子树，前序和中序-跳过第一个字母和左子树长度
        // 长度，子树当前长度-左子树长度-head
        node.right = DFS(preorder, inorder, preorderIdx+count+1, inorderIdx+count+1, len-count-1);
        return node;
    }


    public TreeNode buildTree_(int[] preorder, int[] inorder) {
        return DFS(preorder, 0, preorder.length, inorder, 0);
    }

    /**
     *
     * @param preorder
     * @param root - 子树在前序数组中的的起点位置（Root节点）
     * @param treeSize - 子树的长度
     * @param inorder
     * @param l - 子树在中序数组中的起点位置
     * @return
     */
    public TreeNode DFS(int[] preorder, int root, int treeSize, int[] inorder, int l) {
        if(treeSize==0)
            return null;
        // 根节点
        TreeNode now=new TreeNode(preorder[root]);
        // 左子树节点的个数
        int count=0;
        while(count<treeSize){
            if(inorder[count+l]==preorder[root])
                break;
            count++;
        }
        now.left = DFS(preorder,root+1,count,inorder,l);
        now.right = DFS(preorder,root+count+1,treeSize-count-1, inorder,l+count+1);
        return now;
    }

}
