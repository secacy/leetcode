import java.util.Arrays;
import java.util.HashMap;
import java.util.List;

/**
 * @Author:
 * @CreateTime: 2025-07-08
 * @Description:
 */
public class LCR124 {

    /**
     * 推理二叉树
     */

    public class TreeNode {
        int val;
        TreeNode left;
        TreeNode right;
        TreeNode() {}
        TreeNode(int val) { this.val = val; }
        TreeNode(int val, TreeNode left, TreeNode right) {
            this.val = val;
            this.left = left;
            this.right = right;
        }
    }

    // 为了提升效率，本文使用哈希表 hmap 存储中序遍历的值与索引的映射，查找操作的时间复杂度为 O(1)
    int[] preorder;
    HashMap<Integer, Integer> hmap = new HashMap<>();

    public TreeNode deduceTree(int[] preorder, int[] inorder) {
        this.preorder = preorder;
        for (int i = 0; i < inorder.length; i++)
            hmap.put(inorder[i], i);
        return recur(0, 0, inorder.length - 1);
    }

    TreeNode recur(int root, int left, int right) {
        if(left > right) return null;                          // 递归终止
        TreeNode node = new TreeNode(preorder[root]);          // 建立根节点
        int i = hmap.get(preorder[root]);                      // 划分根节点、左子树、右子树
        node.left = recur(root + 1, left, i - 1);              // 开启左子树递归
        node.right = recur(root + i - left + 1, i + 1, right); // 开启右子树递归
        return node;                                           // 回溯返回根节点
    }


    public TreeNode mySolution(int[] preorder, int[] inorder) {
        int len = preorder.length;
        if (len <= 0) {
            return null;
        }
        int val = preorder[0];
        if (len == 1) {
            return new TreeNode(val);
        }
        int pos = 0;
        for(; pos < len; pos++) {
            if (inorder[pos] == val) {
                break;
            }
        }
        return new TreeNode(val,
                mySolution(Arrays.copyOfRange(preorder, 1, pos+1), Arrays.copyOfRange(inorder, 0, pos)),
                mySolution(Arrays.copyOfRange(preorder, pos+1, len), Arrays.copyOfRange(inorder, pos+1, len)));
    }
}
