import java.util.ArrayDeque;
import java.util.Deque;
import java.util.LinkedList;
import java.util.List;

/**
 * 接雨水
 *
 * @since 2025/8/31 15:42
 * @className LCR42
 * @author hc
 */
public class LCR42 {
    public int trap(int[] height) {
        // 动态规划
        // 对于下标 i
        // 水能到达的最大高度等于下标 i 两边的最大高度的最小值
        // 下标 i 处能接的雨水量等于下标 i 处的水能到达的最大高度减去 height[i]

        // 如果已经知道每个位置两边的最大高度，则可以在 O(n) 的时间内得到能接的雨水总量
        // 使用动态规划可以在 O(n) 的时间内预处理得到每个位置两边的最大高度

        // 当 1≤i≤n−1 时，leftMax[i]=max(leftMax[i−1],height[i])
        // 当 0≤i≤n−2 时，rightMax[i]=max(rightMax[i+1],height[i])

        // 对于 0≤i<n，下标 i 处能接的雨水量等于 min(leftMax[i],rightMax[i])−height[i]。

        int n = height.length;
        if (n == 0) {
            return 0;
        }

        int[] leftMax = new int[n];
        leftMax[0] = height[0];
        for (int i = 1; i < n; i++) {
            leftMax[i] = Math.max(leftMax[i-1], height[i]);
        }

        int[] rightMax = new int[n];
        rightMax[n-1] = height[n-1];
        for (int i = n-2; i >= 0; i--) {
            rightMax[i] = Math.max(rightMax[i+1], height[i]);
        }

        int ans = 0;
        for (int i = 0; i < n; i++) {
            ans += Math.min(leftMax[i], rightMax[i]) - height[i];
        }

        return ans;
    }

    public int solution2(int[] height) {
        // 单调栈
        // 维护一个单调栈，单调栈存储的是下标
        // 满足从栈底到栈顶的下标对应的数组 height 中的元素递减。

        // 从左到右遍历数组，遍历到下标 i 时
        // 如果栈内至少有两个元素，记栈顶元素为 top，top 的下面一个元素是 left
        // 则一定有 height[left]≥height[top]
        // 如果 height[i]>height[top]，则得到一个可以接雨水的区域
        // 该区域的宽度是 i−left−1，高度是 min(height[left],height[i])−height[top]

        // 单调栈
        int ans = 0;
        Deque<Integer> stack = new LinkedList<>();
        int n = height.length;
        for (int i = 0; i < n; i++) {
            while (!stack.isEmpty() && height[i] > height[stack.peek()]) {
                int top = stack.pop();
                if (stack.isEmpty()) break;
                int left = stack.peek();
                int currWidth = i - left - 1;
                int currHeight = Math.min(height[left], height[i]) - height[top];
                ans += currWidth * currHeight;
            }
            stack.push(i);
        }
        return ans;
    }

    public int solution3(int[] height) {
        // 是否可以将空间复杂度降到 O(1)
        // 双指针
        // 由于数组 leftMax 是从左往右计算，数组 rightMax 是从右往左计算
        // 因此可以使用双指针和两个变量代替两个数组
        // 指针 left 只会向右移动，指针 right 只会向左移动

        // 在最高点左侧的任意坐标处，水位等于该点以及左侧柱子高度的最大值，在最高点右侧相反

        // 谁小谁移动
        // 相遇的位置一定是最高的柱子，这个柱子是无法接水的

        // 双指针
        int l = 0, r = height.length - 1;
        int cap = 0;
        int leftCeil = 0, rightCeil = 0;

        while (l <= r) {
            // 先更新 leftCeil, rightCeil
            leftCeil = Math.max(leftCeil, height[l]);
            rightCeil = Math.max(rightCeil, height[r]);

            if (leftCeil < rightCeil) {
                cap += leftCeil - height[l++]; // 增加水量并使l右移
            } else {
                cap += rightCeil - height[r--]; // 增加水量并使r左移
            }
        }
        return cap;
    }


    public int mySolution(int[] height) {
        // 以下是使用单调栈的解法
        int i = 0, j = 0;
        int ans = 0, curSum = 0;
        Deque<Integer> stack = new ArrayDeque<>();
        stack.push(i);
        while (j < height.length - 1) {
            j++;
            if (height[j] >= height[i]) {
                i = j;
                ans += curSum;
                curSum = 0;
                while (!stack.isEmpty()) stack.pop();
                stack.push(i);
            } else {
                curSum += height[i] - height[j];
                // 对于右边比它矮的情况，应该按照降序来计算
                while (!stack.isEmpty() && height[j] >= height[stack.peek()]) stack.pop();
                stack.push(j);
            }
        }
        curSum = 0;
        i = stack.pop();
        while (!stack.isEmpty()) {
            j = stack.pop();
            for (int k = j+1; k < i; k++) {
                curSum += height[i] - height[k];
            }
            ans += curSum;
            curSum = 0;
            i = j;
        }
        return ans;
    }
}
