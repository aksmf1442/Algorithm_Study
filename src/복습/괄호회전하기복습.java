package 복습;

import java.util.Arrays;
import java.util.List;
import java.util.Stack;
import java.util.stream.Collectors;

public class 괄호회전하기복습 {

    public static void main(String[] args) {
        괄호회전하기_Solution solution = new 괄호회전하기_Solution();
        String s = "[](){}";
        System.out.println("solution.solution(s) = " + solution.solution(s));
    }
}

class 괄호회전하기_Solution {

    public int solution(String s) {
        int answer = 0;
        List<String> lst = Arrays.stream(s.split("")).collect(Collectors.toList());
        for (int i = 0; i < s.length(); i++) {
            Stack<String> stack = new Stack<>();
            for (String parentheses : lst) {
                if (stack.isEmpty()) {
                    stack.add(parentheses);
                    continue;
                }

                if (stack.peek().equals("(") && parentheses.equals(")")) {
                    stack.pop();
                    continue;
                }

                if (stack.peek().equals("[") && parentheses.equals("]")) {
                    stack.pop();
                    continue;
                }

                if (stack.peek().equals("{") && parentheses.equals("}")) {
                    stack.pop();
                    continue;
                }

                stack.add(parentheses);
            }


            if (stack.isEmpty()) {
                answer++;
            }

            // 왼쪽으로 한 칸 이동
            lst.add(lst.remove(0));
        }
        return answer;
    }
}
