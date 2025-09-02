import java.awt.event.WindowStateListener;
import java.util.Arrays;
import java.util.HashMap;
import java.util.Map;

/**
 * 和为k的子数组
 *
 * @since 2025/9/1 15:44
 * @className LCR560
 * @author hc
 */
public class LCR560 {
    public int subarraySum(int[] nums, int k) {
        // 前缀和+哈希表优化

        // 前缀和的定义：
        // s[0]=0, s[i]=nums[0]+nums[1]+⋯+nums[i−1]。
        // 注意 s 是一个长为 n+1 的数组，第一项一定是 0

        // 问题转化为：s 中有多少对下标 (i,j) 满足 i<j 且 s[j]−s[i]=k？
        // 如果暴力枚举，O(n2)
        // 前缀和+两数之和
        // 把前缀和放入哈希表中
        // 判断 k - preSum[i] 哈希表中是否存在以及存在的个数
        // 我们可以把 s[j]−s[i]=k 移项，得：s[i]=s[j]−k
        // 为什么这题不适合用滑动窗口做？滑动窗口需要满足单调性

        int ans = 0, pre = 0;
        Map<Integer, Integer> map = new HashMap<>(nums.length); // 设置容量可以快 2ms
        for (int x: nums) {
            map.merge(pre, 1, Integer::sum);
            pre += x;
            ans += map.getOrDefault(pre - k, 0);
        }
        return ans;
    }

    public int solution2(int[] nums, int k) {
        // 枚举
        // 以下解法的时间复杂度为O(n2)
        int ans = 0;
        for (int start = 0; start < nums.length; start++) {
            int sum = 0;
            for (int end = start; end < nums.length; end++) {
                sum += nums[end];
                if (sum == k) {
                    ans++;
                }
            }
        }
        return ans;
    }

    public int mySolution(int[] nums, int k) {
        // k有可能是负的
        // 前缀和吗？
        int ans = 0;
        int len = nums.length;
        int[] preSum = new int[len];
        preSum[0] = nums[0];
        for (int i = 1; i < len; i++) {
            preSum[i] = preSum[i-1] + nums[i];
        }
        // 然后再采用滑动窗口？
        // 这些前缀和只能两两相减来比较
        // 或者单单看一个
        // 只能是后面的减去前面的
        for (int left = 0; left < len; left++) {
            if (preSum[left] == k) ans++;
            for (int right = left + 1; right < len; right++) {
                if (preSum[right] - preSum[left] == k) ans++;
            }
        }
        return ans;

        // 上述时间复杂度为O(N2)
    }

    public static void main(String[] args) {
        int[] nums = {1,2,1,2,1};
        LCR560 lcr560 = new LCR560();
        int ans = lcr560.subarraySum(nums, 3);
        System.out.println(ans);
    }
}
