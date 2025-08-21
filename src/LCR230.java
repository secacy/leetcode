import java.util.Deque;
import java.util.HashMap;
import java.util.LinkedList;
import java.util.Map;

/**
 * @since 2025/8/22 0:55
 * @className LCR230
 * @author hc
 */
public class LCR230 {
    public int mySolution(TreeNode root, int k) {
        // 中序遍历
        Deque<TreeNode> stack = new LinkedList<>();
        int i = 0;
        while (!stack.isEmpty() || root != null) {
            while (root != null) {
                stack.push(root);
                root = root.left;
            }
            root = stack.pop();
            i++;
            if (i == k) return root.val;
            root = root.right;
        }
        return -1;
    }

    public int solution2(TreeNode root, int k) {
        // 如果你需要频繁地查找第 k 小的值，你将如何优化算法？
        // 记录子树的结点数

        // 我们可以记录下以每个结点为根结点的子树的结点数
        // 我们既可以将以每个结点为根结点的子树的结点数存储在结点中，也可以将其记录在哈希表中
        MyBst bst = new MyBst(root);
        return bst.kthSmallest(k);
    }

    class MyBst {
        TreeNode root;
        Map<TreeNode, Integer> nodeNum;

        public MyBst(TreeNode root) {
            this.root = root;
            this.nodeNum = new HashMap<TreeNode, Integer>();
            countNodeNum(root);
        }

        // 返回二叉搜索树中第k小的元素
        public int kthSmallest(int k) {
            TreeNode node = root;
            while (node != null) {
                int left = getNodeNum(node.left);
                if (left < k - 1) {
                    node = node.right;
                    k -= left + 1;
                } else if (left == k - 1) {
                    break;
                } else {
                    node = node.left;
                }
            }
            return node.val;
        }

        // 统计以node为根结点的子树的结点数
        private int countNodeNum(TreeNode node) {
            if (node == null) {
                return 0;
            }
            nodeNum.put(node, 1 + countNodeNum(node.left) + countNodeNum(node.right));
            return nodeNum.get(node);
        }

        // 获取以node为根结点的子树的结点数
        private int getNodeNum(TreeNode node) {
            return nodeNum.getOrDefault(node, 0);
        }
    }

    public int solution3(TreeNode root, int k) {
        //如果二叉搜索树经常被修改（插入/删除操作）并且你需要频繁地查找第 k 小的值，你将如何优化算法？
        // 平衡二叉搜索树

        // 我们在记录子树的结点数的基础上，将二叉搜索树转换为平衡二叉搜索树
        // 并在插入和删除操作中维护它的平衡状态。
        // 该解法以后待补充
        return 0;
    }
}

