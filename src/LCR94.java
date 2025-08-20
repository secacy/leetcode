import java.util.ArrayList;
import java.util.List;
import java.util.Stack;

/**
 * 二叉树的中序遍历
 *
 * @since 2025/8/20 21:57
 * @className LCR94
 * @author hc
 */
public class LCR94 {
    List<Integer> tree = new ArrayList<>();
    public List<Integer> mySolution(TreeNode root) {
        // 递归法
        recur(root);
        return tree;
    }

    void recur(TreeNode root) {
        if (root == null) return;
        recur(root.left);
        tree.add(root.val);
        recur(root.right);
    }

    //前序遍历：中，左，右
    //中序遍历：左，中，右
    //后序遍历：左，右，中

    public List<Integer> iteration(TreeNode root) {
        // 递归的调用过程是不断往左边走，当左边走不下去了，就打印节点，并转向右边，然后右边继续这个过程
        // 我们在迭代实现时，就可以用**栈**来模拟上面的调用过程

        List<Integer> res = new ArrayList<>();
        Stack<TreeNode> stack = new Stack<>();
        while (stack.size()>0 || root!=null) {
            //不断往左子树方向走，每走一次就将当前节点保存到栈中
            //这是模拟递归的调用
            if (root != null) {
                stack.add(root);
                root = root.left;
            } else {
                //当前节点为空，说明左边走到头了，从栈中弹出节点并保存
                //然后转向右边节点，继续上面整个过程
                TreeNode tmp = stack.pop();
                res.add(tmp.val);
                root = tmp.right;
            }
        }
        return res;
    }

    public List<Integer> morris(TreeNode root) {
        // 莫里斯遍历
        // 优点是没有使用任何辅助空间
        // 缺点是改变了整个树的结构，强行把一棵二叉树改成一段链表结构

        List<Integer> res = new ArrayList<>();
        TreeNode pre = null;
        while (root != null) {
            // 如果左节点不为空，就将当前节点连带右子树全部挂到
            // 左节点的最右子树下面
            if (root.left != null) {
                pre = root.left;
                while (pre.right != null) {
                    pre = pre.right;
                }
                pre.right = root;
                // 将root指向root的left
                TreeNode tmp = root;
                root = root.left;
                tmp.left = null;
            } else {
                res.add(root.val);
                root = root.right;
            }
        }
        return res;
    }

    public List<Integer> mark(TreeNode root) {
        // 栈是一种 先进后出的结构，出栈顺序为左，中，右
        // 那么入栈顺序必须调整为倒序，也就是右，中，左

        // 颜色标记法
        // 使用颜色标记节点的状态，新节点为白色，已访问的节点为灰色
        // 如果遇到的节点为白色，则将其标记为灰色，然后将其右子节点、自身、左子节点依次入栈
        // 如果遇到的节点为灰色，则将节点的值输出
        List<Integer> ans = new ArrayList<>();
        Stack<Object> stack = new Stack<>();
        if (root == null) return ans;
        stack.push(root);
        while (!stack.isEmpty()) {
            Object cur = stack.pop();
            if (cur instanceof TreeNode) {
                TreeNode node = (TreeNode) cur;
                if(node.right != null) stack.push(node.right);
                stack.push(node.val);
                if(node.left != null) stack.push(node.left);
            } else {
                ans.add((Integer) cur);
            }
        }
        return ans;
    }
}
