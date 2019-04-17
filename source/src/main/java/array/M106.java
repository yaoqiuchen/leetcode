package array;

/**
 106. 根据一棵树的中序遍历与后序遍历构造二叉树。

 注意:
 你可以假设树中没有重复的元素。

 例如，给出

 中序遍历 inorder = [9,3,15,20,7]
 后序遍历 postorder = [9,15,7,20,3]
 */
public class M106 {

    public static void main(String[] args) {
        new M106().buildTree(new int[] {9,3,15,20,7},new int[] {9,15,7,20,3});
    }
    public class TreeNode {
        int val;
        TreeNode left;
        TreeNode right;
        TreeNode(int x) { val = x; }
    }

    public TreeNode buildTree(int[] inorder, int[] postorder) {
        return DFS(inorder, 0, inorder.length, postorder, postorder.length - 1);
    }

    /**
     *
     * @param inorder
     * @param r - 子树在中序数组中的的起点位置
     * @param treeSize - 子树的长度
     * @param postorder
     * @param root - 子树在后序数组中的起点位置（Root节点）
     * @return
     */
    public TreeNode DFS(int[] inorder, int r, int treeSize, int[] postorder, int root) {
        if (treeSize == 0) {
            return null;
        }
        int rootVal = postorder[root];
        TreeNode node = new TreeNode(rootVal);

        // 左子树的长度
        int leftLen = 0, idx = r;
        while (inorder[idx] != rootVal) {
            idx++;
            leftLen++;
        }
        // 右子树长度
        int rightLen = treeSize - leftLen - 1;
        node.left = DFS(inorder, r, leftLen, postorder, root - rightLen - 1);

        node.right = DFS(inorder, r+1+leftLen, rightLen, postorder, root - 1);

        return node;
    }

}
