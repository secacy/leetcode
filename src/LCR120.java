import java.util.HashSet;
import java.util.Set;
import java.util.Vector;

/**
 * @Author:
 * @CreateTime: 2025-07-08
 * @Description:
 */
public class LCR120 {
    /**
     * 寻找文件副本
      */
    public int findRepeatDocument(int[] documents) {
        // 用 set 看是不是包含该id
        Set<Integer> set = new HashSet<>();
        for (int id: documents) {
            if (set.contains(id)) {
                return id;
            } else {
                set.add(id);
            }
        }
        return -1;
        // 时间复杂度 O(N) HashSet 添加与查找元素皆为 O(1)
        // 空间复杂度 O(N)
    }
}
