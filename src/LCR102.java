import com.sun.source.tree.Tree;

import java.util.*;

/**
 * @since 2025/8/21 23:07
 * @className LCR102
 * @author hc
 */
public class LCR102 {
    public List<List<Integer>> levelOrder(TreeNode root) {
        List<List<Integer>> ans = new ArrayList<>();
        if (root == null) return ans;
        Queue<TreeNode> queue = new LinkedList<>();
        queue.offer(root);
        // 大坑：while (queue != null) 的判断是无意义的！因为queue已经被初始化了
        // 应该使用 !queue.isEmpty()
        while (!queue.isEmpty()) {
            int size = queue.size();
            List<Integer> list = new ArrayList<>();
            for (int i = 0; i < size; i++) {
                TreeNode node = queue.poll();
                list.add(node.val);
                if (node.left != null) queue.offer(node.left);
                if (node.right != null) queue.offer(node.right);
            }
            ans.add(list);
        }
        return ans;
    }
}
