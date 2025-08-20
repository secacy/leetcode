/**
 * @since 2025/8/20 22:46
 * @className LCR104
 * @author hc
 */
public class LCR104 {
    public int maxD = 0;
    public int mySolution(TreeNode root) {
        recur(root, 0);
        return maxD;
    }

    public void recur(TreeNode root, int depth) {
        if (root == null) {
            maxD = Math.max(maxD, depth);
            return;
        }
        recur(root.left, depth+1);
        recur(root.right, depth+1);
    }

    public int dfs(TreeNode root) {
        // 深度优先搜索
        // 如果我们知道了左子树和右子树的最大深度 l 和 r，那么该二叉树的最大深度即为
        // max(l,r)+1
        if(root == null) {
            return 0;
        } else {
            int leftHeight = dfs(root.left);
            int rightHeight = dfs(root.right);
            return Math.max(leftHeight, rightHeight) + 1;
        }
    }
}
