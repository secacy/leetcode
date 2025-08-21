/**
 * 平滑二叉搜索树
 *
 * @since 2025/8/21 23:30
 * @className LCR108
 * @author hc
 */
public class LCR108 {
    public TreeNode sortedArrayToBST(int[] nums) {
        return help(nums, 0, nums.length-1);
    }

    public TreeNode help(int[] nums, int l, int r) {
        int len = r - l;
        if (len < 0) return null;
        int midIndex = (l+r) / 2;
        TreeNode root = new TreeNode(nums[midIndex]);
        root.left = help(nums, l, midIndex-1);
        root.right = help(nums, midIndex+1, r);
        return root;
    }

}
