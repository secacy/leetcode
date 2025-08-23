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
    public String decodeString(String s) {
        Deque<Object> stack = new ArrayDeque<>();
        int sum = 0;
        StringBuffer stringBuffer = new StringBuffer();
        for (int i = 0; i < s.length(); i++) {
            char c = s.charAt(i);
            if (Character.isDigit(c)) {
                // 是数字
                // 如果前面有字符串则把字符串压入栈
                if (!stringBuffer.isEmpty()) {
                    stack.push(stringBuffer.toString());
                    stringBuffer = new StringBuffer();
                }
                int n = Character.getNumericValue(c);
                sum = sum * 10 + n;
            } else if (c == '[') {
                // 是[，则压入数字，压入[
                stack.push(sum);
                stack.push(c);
                sum = 0;
            } else if (c == ']') {
                // 是]，则压入不为空的字符串
                // 弹出数字和[和字符串
                // 合并数字和字符串
                // 直到找到[，再找到数字
                if (!stringBuffer.isEmpty()) {
                    stack.push(stringBuffer.toString());
                    stringBuffer = new StringBuffer();
                }
                String str = "";
                while (!stack.isEmpty()) {
                    Object pop = stack.pop();
                    if (pop instanceof Character ch){
                        if(ch == '[') {
                            continue;
                        }
                    } else if (pop instanceof String st) {
                        str = st + str;
                    } else if (pop instanceof Integer in) {
                        str = str.repeat(in);
                        stack.push(str);
                        break;
                    }
                }
            } else {
                // 是字符串，则先存入sb
                stringBuffer.append(c);
            }
        }
        if (!stringBuffer.isEmpty()) {
            stack.push(stringBuffer.toString());
        }
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
        String string = lcr394.decodeString(s);
        System.out.println(string);
    }
}
