import java.util.ArrayList;
import java.util.List;

/**
 * 子集
 *
 * @since 2025/9/6 13:18
 * @className LCR78
 * @author hc
 */
public class LCR78 {
    List<List<Integer>> ans = new ArrayList<>();
    public List<List<Integer>> subsets(int[] nums) {
        List<Integer> path = new ArrayList<>();
        int index = 0;
        int len = nums.length;
        help(path, 0, len, nums);
        return ans;
    }

    public void help(List<Integer> path, int index, int len, int[] nums) {
        ans.add(new ArrayList<>(path));
        for (int i = index; i < len; i++) {
            path.add(nums[i]);
            help(path, i+1, len, nums);
            path.remove(path.size() - 1);
        }
    }
}
