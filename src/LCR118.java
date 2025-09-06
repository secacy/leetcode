import java.util.ArrayList;
import java.util.List;

/**
 * 杨辉三角
 *
 * @since 2025/9/4 8:54
 * @className LCR118
 * @author hc
 */
public class LCR118 {
    public List<List<Integer>> generate(int numRows) {
        List<List<Integer>> ans = new ArrayList<>();
        for (int i = 0; i < numRows; i++) {
            List<Integer> list = new ArrayList<>();
            for (int j = 0; j <= i; j++) {
                if (j == 0 || j == i) list.add(1);
                else list.add(ans.get(i-1).get(j-1) +
                        ans.get(i-1).get(j));
            }
            ans.add(list);
        }
        return ans;
    }
}
