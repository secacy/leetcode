import java.util.ArrayDeque;
import java.util.Deque;

/**
 * 字符串解码
 *
 * @since 2025/8/23 0:02
 * @className LCR394
 * @author hc
 */
public class LCR394 {
    // 本题难点在于括号内嵌套括号，需要从内向外生成与拼接字符串
    // 这与栈的先入后出特性对应
    public String decodeString(String s) {
        Deque<Integer> numStack = new ArrayDeque<>(); // 存储需要重复字符串的数字
        Deque<String> strStack = new ArrayDeque<>(); // 存储待重复的字符串
        int num = 0;
        StringBuilder res = new StringBuilder();

        for (Character c: s.toCharArray()) {
            if (c == '[') {
                numStack.push(num);
                strStack.push(res.toString()); // 把res压入栈中
                num = 0;
                res = new StringBuilder();
            } else if (c == ']') {
                int n = numStack.pop();
                String last = strStack.pop();
                String str = res.toString().repeat(n);
                res = new StringBuilder(last + str);
            } else if (c >= '0' && c <= '9') {
                num = num * 10 + (c - '0');
            } else {
                res.append(c);
            }
        }
        return res.toString();
    }

    public String mySolution(String s) {
        Deque<Object> stack = new ArrayDeque<>();
        int sum = 0;
        StringBuilder stringBuilder = new StringBuilder();
        for (Character c: s.toCharArray()) {
            if (Character.isDigit(c)) {
                // 是数字
                // 把当前字符串压入栈
                sum = sum * 10 + (c - '0');
                if (!stringBuilder.isEmpty()) {
                    stack.push(stringBuilder.toString());
                    stringBuilder = new StringBuilder();
                }
            } else if (c == '[') {
                // 是[
                // 压入数字
                stack.push(sum);
                sum = 0;
            } else if (c == ']') {
                // 是]，则压入字符串
                // 弹出数字和字符串
                // 合并数字和字符串
                // 直到找到数字
                String str = stringBuilder.toString();
                stringBuilder = new StringBuilder();
                while (!stack.isEmpty()) {
                    Object pop = stack.pop();
                    if (pop instanceof String st) {
                        str = st + str;
                    } else if (pop instanceof Integer in) {
                        stack.push(str.repeat(in));
                        break;
                    }
                }
            } else {
                // 是字符串，则先存入sb
                stringBuilder.append(c);
            }
        }
        stack.push(stringBuilder.toString());
        String str = "";
        while (!stack.isEmpty()) {
            Object pop = stack.pop();
            if (pop instanceof String st) {
                str = st + str;
            }
        }
        return str;
    }

    public static void main(String[] args) {
        LCR394 lcr394 = new LCR394();
        String s = "2[abc]3[cd]ef";
        String string = lcr394.mySolution(s);
        System.out.println(string);
    }
}
