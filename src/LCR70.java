/**
 * 爬梯子
 *
 * @since 2025/9/2 8:58
 * @className LCR70
 * @author hc
 */
public class LCR70 {
    public int climbStairs(int n) {
        // 动态规划
        // 动态规划的转移方程：f(x)=f(x−1)+f(x−2)
        // 边界条件：f(0) = 1; f(1)=1
        // 由于这里的 f(x) 只和 f(x−1) 与 f(x−2) 有关
        // 我们可以用「滚动数组思想」把空间复杂度优化成 O(1)
        int p = 0, q = 0, r = 1;
        for (int i = 1; i <= n; i++) {
            p = q;
            q = r;
            r = p + q;
        }
        return r;

        // 时间复杂度：O(n)
    }

    public int solution2(int n) {
        return 0;
    }




    public int mySolution(int n) {
        // ans[n] = ans[n-1] + ans[n-2]
        if (n == 1) return 1;
        if (n == 2) return 2;
        int[] ans = new int[n+1];
        ans[1] = 1;
        ans[2] = 2;
        for (int i = 3; i <= n; i++) {
            ans[i] = ans[i-1] + ans[i-2];
        }
        return ans[n];
    }
}
