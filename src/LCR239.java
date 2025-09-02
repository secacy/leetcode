import java.util.*;

/**
 * 滑动窗口最大值
 *
 * @since 2025/9/1 17:10
 * @className LCR239
 * @author hc
 */
public class LCR239 {

    public int[] maxSlidingWindow(int[] nums, int k) {
        // 单调队列
        // 在滑动窗口中，如果 i < j，并且 nums[i]≤nums[j]
        // 则 nums[i] 可以被永久移除

        // 我们可以使用一个队列存储所有还没有被移除的下标
        // 它们在数组 nums 中对应的值是严格单调递减的

        // 当滑动窗口向右移动时，我们需要把一个新的元素放入队列中
        // 如果新元素大于等于队尾元素，队尾的元素就可以被永久地移除

        // 由于队列中下标对应的元素是严格单调递减的
        // 此时队首下标对应的元素就是滑动窗口中的最大值

        // 我们还需要不断从队首弹出元素，直到队首元素在窗口中为止

        // 为了可以同时弹出队首和队尾的元素，我们需要使用双端队列。
        // 满足这种单调性的双端队列一般称作「单调队列」

        int n = nums.length;
        int[] ans = new int[n - k + 1];
        Deque<Integer> q = new ArrayDeque<>();
        for (int i = 0; i < n; i++) {
            // 右边入
            while (!q.isEmpty() && nums[q.getLast()] <= nums[i]) {
                q.removeLast();
            }
            q.addLast(i);

            // 左边出
            int left = i - k + 1; // 窗口左端点
            if (q.getFirst() < left) {
                q.removeFirst();
            }

            // 在窗口左端点处记录答案
            if (left >= 0) {
                ans[left] = nums[q.getFirst()];
            }
        }
        return ans;
    }

    public int[] solution2(int[] nums, int k) {
        // 分块 + 预处理
        // 我们可以将数组 nums 从左到右按照 k 个一组进行分组
        // 如果 i 是 k 的倍数，那么 nums[i] 到 nums[i+k−1] 恰好是一个分组
        // 如果 i 不是 k 的倍数，那么 nums[i] 到 nums[i+k−1] 会跨越两个分组
        // 此时占有第一个分组的后缀以及第二个分组的前缀
        // 假设 j 是 k 的倍数，并且满足 i<j≤i+k−1
        // 预处理出每个分组中的前缀最大值以及后缀最大值
        // prefixMax[i] 表示下标 i 对应的分组中，以 i 结尾的前缀最大值
        // prefixMax[i] =
        // nums[i], i 是 k 的倍数
        // max{prefixMax[i−1],nums[i]}, i 不是 k 的倍数
        // 则答案为 max{suffixMax[i],prefixMax[i+k−1]}

        int n = nums.length;
        int[] preMax = new int[n];
        int[] sufMax = new int[n];
        for (int i = 0; i < n; i++) {
            if (i % k == 0) {
                preMax[i] = nums[i];
            } else {
                preMax[i] = Math.max(preMax[i-1], nums[i]);
            }
        }
        for (int i = n - 1; i >= 0; i--) {
            if (i == n - 1 || (i + 1) % k == 0) {
                sufMax[i] = nums[i];
            } else {
                sufMax[i] = Math.max(sufMax[i + 1], nums[i]);
            }
        }
        int[] ans = new int[n - k + 1];
        for (int i = 0; i <= n - k; i++) {
            ans[i] = Math.max(sufMax[i], preMax[i + k - 1]);
        }
        return ans;
    }

    public int[] solution3(int[] nums, int k) {
        // 优先队列（堆）
        // 堆顶的元素就是堆中所有元素的最大值
        int n = nums.length;
        PriorityQueue<int[]> pq = new PriorityQueue<>(
                (o1, o2) -> o1[0] != o2[0] ? o2[0] - o1[0] : o2[1] - o1[1]
        );
        for (int i = 0; i < k; ++i) {
            pq.offer(new int[]{nums[i], i});
        }
        int[] ans = new int[n - k + 1];
        ans[0] = pq.peek()[0];
        for (int i = k; i < n; ++i) {
            pq.offer(new int[]{nums[i], i});
            while (pq.peek()[1] <= i - k) { // 把不在窗口中的元素弹出
                pq.poll();
            }
            ans[i - k + 1] = pq.peek()[0];
        }
        return ans;

        // 时间复杂度：O(NlogN)
    }


    public int[] mySolution(int[] nums, int k) {
        // 时间复杂度：O(NlogN)
        int len = nums.length;
        int[] ans = new int[len-k];
        TreeMap<Integer, Integer> map = new TreeMap<>();
        for (int i = 0; i < k && i < len; i++) {
            map.merge(nums[i], 1, Integer::sum);
        }
        ans[0] = map.lastKey();
        for (int left = 1, right = k; right < len; left++, right++) {
            map.merge(nums[right], 1, Integer::sum);
            map.computeIfPresent(nums[left-1], (key, value) -> (value == 1 ? null : value - 1));
            ans[left] = map.lastKey();
        }
        return ans;
    }
}
