import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

/**
 * 找到字符串中所有字母异位词
 *
 * @since 2025/8/31 19:51
 * @className LCR438
 * @author hc
 */
public class LCR438 {
    public List<Integer> findAnagrams(String s, String p) {
        // 滑动窗口
        List<Integer> ans = new ArrayList<>();
        int[] cnt = new int[26]; // 统计 p 的每种字母的出现次数
        for (char c: p.toCharArray()) {
            cnt[c - 'a']++;
        }
        for (int left = 0, right = 0; right < s.length(); right++) {
            int c = s.charAt(right) - 'a';
            cnt[c]--; // 右端点字母进入窗口
            while (cnt[c] < 0) { // 字母 c 太多了
                cnt[s.charAt(left) - 'a']++;  // 左端点字母离开窗口
                left++;
            }
            if (right - left + 1 == p.length()) {
                ans.add(left);
            }
        }
        return ans;
    }


    public List<Integer> mySolution(String s, String p) {
        List<Integer> ans = new ArrayList<>();
        int len = p.length();
        Map<Character, Integer> pattern = new HashMap<>();
        char[] cs = p.toCharArray();
        for (int i = 0; i < len; i++) {
            putKey(pattern, cs[i]);
        }
        char[] ss = s.toCharArray();
        Map<Character, Integer> map = new HashMap<>();
        for (int left = 0, right = 0; right < s.length(); right++) {
            char ch = ss[right];
            if (!pattern.containsKey(ch)) {
                map = new HashMap<>();
                left = right + 1;
            } else {
                while (map.containsKey(ch) && pattern.get(ch) <= map.get(ch)) {
                    removeKey(map, ss[left]);
                    left++;
                }
                putKey(map, ch);
            }
            if (right - left + 1 == len) {
                removeKey(map, ss[left]);
                ans.add(left);
                left++;
            }
        }
        return ans;
    }

    void putKey(Map<Character, Integer> map, char ch) {
        if (!map.containsKey(ch)) {
            map.put(ch, 1);
        } else {
            map.put(ch, map.get(ch) + 1);
        }
    }

    void removeKey(Map<Character, Integer> map, char ch) {
        int n = map.get(ch);
        if (n > 1) {
            map.put(ch, n -1);
        } else {
            map.remove(ch);
        }
    }
}
