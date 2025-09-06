/**
 * 打家劫舍
 *
 * @since 2025/9/4 9:00
 * @className LCR198
 * @author hc
 */
public class LCR198 {
    public int rob(int[] nums) {
        // 动态规划
        // 空间优化：考虑到每间房屋的最高总金额只和该房屋的前两间房屋的最高总金额相关，因此可以使用滚动数组
        int prev = 0;
        int curr = 0;
        for (int num: nums) {
            int temp = Math.max(curr, prev + num);
            prev = curr;
            curr = temp;
        }
        return curr;
    }


    public int mySolution(int[] nums) {
        // f(n) = Math.max(f(n-1), f(n-2) + nums[n]);
        int len = nums.length;
        int[] ans = new int[len];
        for (int i = 0; i < len; i++) {
            if (i == 0) ans[i] = nums[0];
            else if (i == 1) ans[i] = Math.max(nums[0], nums[1]);
            else {
                ans[i] = Math.max(ans[i-1], ans[i-2] + nums[i]);
            }
        }
        return ans[len-1];
    }
}
