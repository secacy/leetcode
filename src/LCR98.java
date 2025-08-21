import java.util.ArrayDeque;
import java.util.Deque;
import java.util.LinkedList;

/**
 * 验证二叉搜索树
 *
 * @since 2025/8/21 23:44
 * @className LCR98
 * @author hc
 */
public class LCR98 {
    public boolean isValidBST(TreeNode root) {
        return isValidBST(root, Long.MIN_VALUE, Long.MAX_VALUE);
    }

    // int的范围：-2^31 ~ 2^31-1

    public boolean isValidBST(TreeNode node, long lower, long upper) {
        // 巧妙的解法
        // 不是区分左右，而是对该节点的值进行限制
        // 比较该节点的值是否符合要求
        // 再去看其他子树

        if (node == null) {
            return true;
        }
        if (node.val <= lower || node.val >= upper) {
            return false;
        }
        return isValidBST(node.left, lower, node.val) && isValidBST(node.right, node.val, upper);
        // node.val本身是符合条件的
    }

    public boolean solution2(TreeNode root) {
        // 中序遍历
        // 二叉搜索树「中序遍历」得到的值构成的序列一定是升序的
        // 我们在中序遍历的时候实时检查当前节点的值是否大于前一个中序遍历到的节点的值即可

        Deque<TreeNode> stack = new LinkedList<>();
        double inorder = -Double.MAX_VALUE;

        while (!stack.isEmpty() || root != null) {
            while (root != null) {
                stack.push(root);
                root = root.left;
            }
            root = stack.pop();
            // 如果中序遍历得到的节点的值小于等于前一个 inorder，说明不是二叉搜索树
            if (root.val <= inorder) {
                return false;
            }
            inorder = root.val;
            root = root.right;
        }
        return true;
    }

    // 中序遍历
    public void inorderTraversal(TreeNode root) {
        Deque<TreeNode> stack = new LinkedList<>();

        while (!stack.isEmpty() || root != null) {
            while (root != null) {
                stack.push(root);
                root = root.left;
            }
            root = stack.pop();
            int val = root.val;
            root = root.left;
        }
    }

    public boolean mySolution(TreeNode root) {
        return help(root, Long.MIN_VALUE, Long.MAX_VALUE); // 原本放弃了使用long的，没想到真使用long
    }

    public boolean help(TreeNode root, long minV, long maxV) {
        if (root == null) return true;
        if (root.left != null) {
            int lva = root.left.val;
            if (lva <= minV || lva >= maxV || lva >= root.val) return false;
        }
        if (root.right != null) {
            int rva = root.right.val;
            if (rva >= maxV || rva <= minV || rva <= root.val) return false;
        }
        return help(root.left, minV, Math.min(maxV, root.val)) && help(root.right, Math.max(root.val, minV), maxV);
    }
}
