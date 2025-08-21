import java.util.TreeMap;

/**
 * 树的直径
 *
 * @since 2025/8/20 23:49
 * @className LCR543
 * @author hc
 */
public class LCR543 {

    int ans;

    public int diameterOfBinaryTree(TreeNode root) {
        // 一条路径的长度为该路径经过的节点数减一
        // 求直径等效于求路径经过节点数的最大值减一
        // 任意一条路径均可以被看作由某个节点为起点，从其左儿子和右儿子向下遍历的路径拼接得到

        // 该节点的左儿子向下遍历经过最多的节点数 L（即以左儿子为根的子树的深度）
        // 其右儿子向下遍历经过最多的节点数 R
        // 那么以该节点为起点的路径经过节点数的最大值即为 L+R+1
        // 二叉树的直径就是所有节点 d_node 的最大值减一
        ans = 1;
        depth(root);
        return ans - 1;
    }

    public int depth(TreeNode node) {
        // 树的深度
        if (node == null) {
            return 0;  // 访问到空节点了，返回0
        }
        int l = depth(node.left);
        int r = depth(node.right);
        ans = Math.max(ans, l+r+1);  // 节点的个数
        return Math.max(l, r) + 1;
    }

    public int L = 0;

    public int mySolution(TreeNode root) {
        // 寻找每个节点的最长左距离和最长右距离
        // 取最大
        help(root);
        return L;
    }

    public int help(TreeNode root) {
        // 我们界定该help函数返回的是根节点到左/右子节点的最大距离
        if (root == null) return 0;
        int maxL = 0;
        int maxR= 0;
        if (root.left != null) maxL = help(root.left) + 1;
        if (root.right != null) maxR = help(root.right) + 1;
        L = Math.max(maxL+maxR, L);
        return Math.max(maxL, maxR);
    }
}
