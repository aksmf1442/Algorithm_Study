package 복습;

import java.util.Stack;

public class 뒤에있는큰수찾기복습 {
    public static void main(String[] args) {
        뒤에있는큰수찾기_Solution solution = new 뒤에있는큰수찾기_Solution();
        int[] numbers = {2, 3, 3, 5};
        int[] result = solution.solution(numbers);
        for (int i : result) {
            System.out.print(i + ",");
        }
    }
}

class 뒤에있는큰수찾기_Solution {
    public int[] solution(int[] numbers) {
        Stack<Integer> stack = new Stack<>();
        int[] ret = new int[numbers.length];

        for (int i = 0; i < numbers.length; i++) {
            if (stack.isEmpty() || numbers[i] < numbers[i - 1]) {
                stack.push(i);
            } else {
                while (!stack.isEmpty() && numbers[stack.peek()] < numbers[i]) {
                    ret[stack.pop()] = numbers[i];
                }
                stack.push(i);
            }
        }

        while (!stack.isEmpty()) {
            ret[stack.pop()] = -1;
        }
        return ret;
    }
}
