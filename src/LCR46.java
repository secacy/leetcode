import java.util.ArrayList;
import java.util.List;

/**
 * 全排列
 *
 * @since 2025/9/6 10:57
 * @className LCR46
 * @author hc
 */
public class LCR46 {
    List<List<Integer>> res = new ArrayList<>();

    public List<List<Integer>> permute(int[] nums) {
        // 回溯
        // 回溯法 采用试错的思想，它尝试分步的去解决一个问题。
        // 当它发现现有的分步答案不能得到有效的解答时
        // 它将取消上一步甚至是上几步的计算
        // 再通过其它的可能的分步解答再次寻找问题的答案
        // 回溯法通常用最简单的递归方法来实现
        // 可能出现两种情况
        // 找到一个可能存在的正确的答案
        // 在尝试了所有可能的分步方法后宣告该问题没有答案
        // DFS是一种用于遍历或搜索树或图的算法
        // 这个算法会 尽可能深 的搜索树的分支
        // 当结点 v 的所在边都己被探寻过，搜索将 回溯 到发现结点 v 的那条边的起始结点
        // 回溯
        // 用一个 不断变化 的变量，在尝试各种可能的过程中，搜索需要的结果
        // 强调了 回退 操作对于搜索的合理性
        // 深度优先遍历
        // 强调一种遍历的思想
        // 搜索问题的解，可以通过 遍历 实现
        // 因此回溯算法用于 搜索一个问题的所有的解
        // 这些阶段通过变量的「不同的值」体现，这些变量的不同的值，称之为「状态」
        // 在「回头」以后， 状态变量需要设置成为和先前一样，这个操作称之为「状态重置」
        // path 变量是一个栈
        // 我们需要一个变量来表示当前程序递归到第几层，我们把这个变量叫做 depth，或者命名为 index
        // 布尔数组 used
        // 执行深度优先遍历，从较深层的结点返回到较浅层结点的时候
        // 需要做「状态重置」，即「回到过去」、「恢复现场」
        int len = nums.length;
        boolean[] used = new boolean[len];
        List<Integer> path = new ArrayList<>();
        dfs(nums, len, 0, path, used);
        return res;
    }

    private void dfs(int[] nums, int len, int depth, List<Integer> path, boolean[] used) {
        if (depth == len) {
            res.add(new ArrayList<>(path));
            return;
        }

        for (int i = 0; i < len; i++) {
            if (!used[i]) {
                path.add(nums[i]);
                used[i] = true;
                dfs(nums, len, depth+1, path, used);
                used[i] = false;
                path.remove(path.size()-1);
            }
        }
    }
}
