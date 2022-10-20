package level2;

import java.util.Arrays;
import java.util.List;
import java.util.Stack;
import java.util.stream.Collectors;

public class 괄호회전하기 {

    public static void main(String[] args) {
        괄호회전하기_Solution solution = new 괄호회전하기_Solution();
        String s = "[](){}";
        System.out.println("solution.solution(s) = " + solution.solution(s));
    }
}

/**
 * goal: s의 길이만큼 한 칸씩 이동하고 이동했을 때 몇 가지가 올바른 괄호 문자열인지 확인.
 */

/**
 * 풀이 방법
 * 1. 한 칸 왼쪽으로 이동
 * 2. 올바른 괄호 문자열인지 스택을 활용하여 확인
 * 3. s의 길이만큼 반복
 */

class 괄호회전하기_Solution {

    public int solution(String s) {
        int answer = 0;
        for (int i = 0; i < s.length(); i++) {
            List<String> lst = Arrays.stream(s.split("")).collect(Collectors.toList());
            // 왼쪽으로 이동
            for (int j = 0; j < i; j++) {
                lst.add(lst.remove(0));
            }

            Stack<String> stack = new Stack<>();
            while (!lst.isEmpty()) {
                if (stack.isEmpty()) {
                    stack.add(lst.remove(0));
                    continue;
                }

                if (stack.peek().equals("(") && lst.get(0).equals(")")) {
                    lst.remove(0);
                    stack.pop();
                    continue;
                }

                if (stack.peek().equals("[") && lst.get(0).equals("]")) {
                    lst.remove(0);
                    stack.pop();
                    continue;
                }

                if (stack.peek().equals("{") && lst.get(0).equals("}")) {
                    lst.remove(0);
                    stack.pop();
                    continue;
                }

                stack.add(lst.remove(0));
            }


            if (stack.isEmpty()) {
                answer++;
            }
        }
        return answer;
    }
}
