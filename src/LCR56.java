import java.util.*;

/**
 * 合并区间
 *
 * @since 2025/8/20 0:30
 * @className LR56
 * @author hc
 */
public class LCR56 {
    public static int[][] merge(int[][] intervals) {
        // 嗯我的思路也是排序
        // 但是写得磕磕绊绊于是看了题解

        // 首先，我们将列表中的区间按照左端点升序排序。
        // 然后我们将第一个区间加入 merged 数组中，并按顺序依次考虑之后的每个区间：
        // 如果当前区间的左端点在数组 merged 中**最后一个区间**的右端点之后，那么它们不会重合，我们可以直接将这个区间加入数组 merged 的末尾；
        // 否则，它们重合，我们需要用当前区间的右端点更新数组 merged 中最后一个区间的右端点，将其置为二者的较大值。

        if (intervals.length == 0) {
            return new int[0][2];
        }

        Arrays.sort(intervals, new Comparator<int[]>() {
            @Override
            public int compare(int[] o1, int[] o2) {
                return o1[0] - o2[0];
            }
        });
        List<int[]> merged = new ArrayList<>();
        for (int i = 0; i < intervals.length; i++) {
            int L = intervals[i][0], R = intervals[i][1];
            if (merged.size() == 0 || merged.get(merged.size() - 1)[1] < L) {
                merged.add(new int[]{L, R});
            } else {
                merged.get(merged.size() - 1)[1] = Math.max(merged.get(merged.size()-1)[1], R);
            }
        }
        return merged.toArray(new int[merged.size()][]);
    }

}
