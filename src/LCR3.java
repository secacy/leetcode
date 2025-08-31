import java.util.HashSet;
import java.util.Set;

/**
 * 无重复字符的最长字串
 *
 * @since 2025/8/31 17:33
 * @className LCR3
 * @author hc
 */
public class LCR3 {
    public int lengthOfLongestSubstring(String s) {
        // 滑动窗口
        char[] ss = s.toCharArray();
        Set<Character> set = new HashSet<>();
        int ans = 0;
        for (int left = 0, right = 0; right < s.length(); right++) {
            char ch = ss[right];
            while (set.contains(ch)) {
                set.remove(ss[left]);
                left++;
            }
            set.add(ss[right]);
            ans = Math.max(ans, right - left + 1);
        }
        return ans;
    }

    public int mySolution(String s) {
        Set<Character> set = new HashSet<>();
        int ans = 0;
        int k = 0;
        // 滑动窗口
        for (int i = 0; i < s.length(); i++) {
            char c = s.charAt(i);
            if (set.contains(c)) {
                ans = Math.max(ans, set.size());
                while (s.charAt(k) != c) {
                    set.remove(s.charAt(k));
                    k++;
                }
                set.remove(s.charAt(k));
                k++;
            }
            set.add(c);
        }
        ans = Math.max(set.size(), ans);
        return ans;
    }
}
