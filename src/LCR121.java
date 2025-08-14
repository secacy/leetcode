/**
 * @Author:
 * @CreateTime: 2025-07-08
 * @Description:
 */
public class LCR121 {
    /**
     * 寻找目标值 - 二维度数组
     * 矩阵 “从上到下递增、从左到右递增”
     */
    public boolean findTargetIn2DPlants(int[][] plants, int target) {
        // 以右上节点为顶点
        // 我们将矩阵逆时针旋转 45° ，并将其转化为图形式，发现其类似于 二叉搜索树
        // 即对于每个元素，其左分支元素更小、右分支元素更大。
        // 向左变小，向右变大
        // 因此，考虑从 “根节点” 开始搜索，遇到比 target 大的元素就向左，反之向右，即可找到目标值 target
        int i = 0;
        int j = plants[0].length - 1;
        while (j >= 0 && i <= plants.length - 1) {
            if (plants[i][j] == target) return true;
            else if (plants[i][j] > target) j--;
            else i++;
        }
        return false;
        // 时间复杂度：O(M+N)
        // 空间复杂度：O(1)
    }

    public boolean mySolution(int[][] plants, int target) {
        // 以下是暴力法
        // 最大时间复杂度为 O(MN)
        for (int i = 0; i < plants.length; i++) {
            for (int j = 0; j < plants[i].length;) {
                if (plants[i][j] == target) return true;
                else if (plants[i][j] < target) j++;
                else {
                    break;
                }
            }
        }
        return false;
    }
}
