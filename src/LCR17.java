import java.util.ArrayList;
import java.util.HashSet;
import java.util.List;
import java.util.Set;

/**
 * 电话号码的组合
 *
 * @since 2025/9/6 13:19
 * @className LCR17
 * @author hc
 */
public class LCR17 {
    private static final String[] MAPPING = new String[]{"", "", "abc", "def", "ghi", "jkl", "mno", "pqrs", "tuv", "wxyz"};
    public List<String> letterCombinations(String digits) {
        int len = digits.length();
        if (len == 0) {
            return List.of();
        }

        List<String> ans = new ArrayList<>();
        char[] path = new char[len];
        dfs(0, ans, path, len, digits.toCharArray());
        return ans;
    }

    private void dfs(int i, List<String> ans, char[] path, int len, char[] digits) {
        if (i == len) {
            ans.add(new String(path));
            return;
        }
        String letters = MAPPING[digits[i] - '0'];
        for (char c: letters.toCharArray()) {
            path[i] = c; // 直接覆盖
            dfs(i+1, ans, path, len, digits);
        }
    }


    public List<String> mySolution(String digits) {
        List<String> res = new ArrayList<>();
        if (digits.isEmpty()) {
            return res;
        }
        List<List<Character>> nums = new ArrayList<>();
        for (char c: digits.toCharArray()) {
            List<Character> list = new ArrayList<>();
            int i = c - '0';
            int cur = (i - 2) * 3;
            if (i == 8 || i == 9) cur += 1;
            list.add((char)('a' + cur));
            list.add((char)('a' + cur + 1));
            list.add((char)('a' + cur + 2));
            if (i == 7) list.add((char)('a' + cur + 3));
            if (i == 9) list.add((char)('a' + cur + 3));
            nums.add(list);
        }

        myDfs(nums, nums.size(), 0, "", res);
        return res;
    }

    private void myDfs(List<List<Character>> nums, int len, int depth, String path, List<String> res) {
        if (depth == len) {
            res.add(path);
            return;
        }
        List<Character> list = nums.get(depth);
        for (Character character : list) {
            myDfs(nums, len, depth + 1, path + character, res);
        }
    }
}
