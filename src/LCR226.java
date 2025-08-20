/**
 * 翻转二叉树
 *
 * @since 2025/8/20 22:53
 * @className LCR226
 * @author hc
 */
public class LCR226 {
    public TreeNode invertTree(TreeNode root) {
        if (root == null) {
            return root;
        }
        TreeNode tmp = root.left;
        root.left = root.right;
        root.right = tmp;
        invertTree(root.left);
        invertTree(root.right);
        return root;
    }
}
