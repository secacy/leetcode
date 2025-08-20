/**
 * 最大子数组和
 *
 * @since 2025/8/18 0:18
 * @className LR53
 * @author hc
 */
public class LCR53 {
    // TODO SOLUTION2 分治解法未看

    public int maxSubArray(int[] nums) {
        // 动态规划
        // 我们可以考虑 nums[i] 单独成为一段还是加入 f(i−1) 对应的那一段
        // 动态规划转移方程：f(i) = max{f(i-1)+nums[i], nums[i]}
        // 用一个 f 数组来保存 f(i) 的值
        // 考虑到 f(i) 只和 f(i−1) 相关
        // 我们可以只用一个变量 pre 来维护对于当前 f(i) 的 f(i−1) 的值是多少
        // 有点类似滚动数组的思想

        int pre = 0, maxAns = nums[0];
        for (int x: nums) {
            pre = Math.max(pre+x, x);
            maxAns = Math.max(maxAns, pre);
        }
        return maxAns;
    }

    public int solution2(int[] nums) {
        return 1;
    }


    public int mySolution(int[] nums) {
        int i=0, j=0;
        int maxN = nums[0];
        int curSum = maxN;
        while (i <= j && j < nums.length-1) {
            j++;
            if (nums[j]>0) {
                // 如果curSum<0，舍弃全部
                if (curSum<0) {
                    i = j;
                    curSum = 0;
                } else {
                    // 舍弃左边小于0的部分
                    while(nums[i]<=0) i++;
                }
            } else {
                if (curSum < nums[j]) {
                    i = j;
                    curSum = 0;
                }
            }
            curSum +=nums[j];
            maxN = Math.max(maxN, curSum);
        }
        return maxN;
    }
}
