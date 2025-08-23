import java.util.ArrayList;
import java.util.LinkedList;
import java.util.List;
import java.util.Queue;

/**
 * 二叉树的右视图
 *
 * @since 2025/8/22 10:07
 * @className LCR199
 * @author hc
 */
public class LCR199 {
    public List<Integer> mySolution(TreeNode root) {
        // bfs
        Queue<TreeNode> queue = new LinkedList<>();
        List<Integer> ans = new ArrayList<>();
        if (root == null) return ans;
        queue.add(root);
        while (!queue.isEmpty()) {
            int size = queue.size();
            for (int i = 0; i < size; i++) {
                TreeNode node = queue.remove();
                if (i == 0) ans.add(node.val);
                if (node.right != null) queue.add(node.right);
                if (node.left != null) queue.add(node.left);
            }
        }
        return ans;
    }

    public List<Integer> rightSideView(TreeNode root) {
        // dfs
        List<Integer> ans = new ArrayList<>();
        dfs(root, 0, ans);
        return ans;
    }

    public void dfs(TreeNode root, int depth, List<Integer> ans) {
        if (root == null) {
            return;
        }
        if (depth == ans.size()) {// 这个深度首次遇到
            ans.add(root.val);
        }
        dfs(root.right, depth+1, ans);
        dfs(root.left, depth+1, ans);
    }
}
