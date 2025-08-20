import java.util.LinkedList;
import java.util.Queue;
import java.util.Stack;

/**
 * 对称二叉树
 *
 * @since 2025/8/20 22:58
 * @className LCR101
 * @author hc
 */
public class LCR101 {
    public boolean isSymmetric(TreeNode root) {
        return help(root, root);
    }

    public boolean help(TreeNode ln, TreeNode rn) {
        if (ln == null && rn == null) return true;
        if (ln == null || rn == null) return false;
        return (ln.val == rn.val) && help(ln.left, rn.right) && help(ln.right, rn.left);
    }

    public boolean iteration(TreeNode root) {
        // 引入一个队列，这是把递归程序改写成迭代程序的常用方法
        // 初始化时我们把根节点入队两次
        // 每次提取两个结点并比较它们的值
        // 将两个结点的左右子结点按相反的顺序插入队列中

        return check(root, root);
    }

    public boolean check(TreeNode u, TreeNode v) {
        Queue<TreeNode> q = new LinkedList<>();
        q.offer(u);
        q.offer(v);
        while (!q.isEmpty()) {
            u = q.poll();
            v = q.poll();
            if (u == null && v == null) {
                continue;
            }
            if ((u == null || v == null) || (u.val != v.val)) {
                return false;
            }
            q.offer(u.left);
            q.offer(v.right);
            q.offer(u.right);
            q.offer(v.left);
        }
        return true;
    }
}
