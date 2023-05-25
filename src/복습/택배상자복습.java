package 복습;

import java.util.Stack;

public class 택배상자복습 {
    public static void main(String[] args) {
        택배상자_Solution solution = new 택배상자_Solution();
        System.out.println(solution.solution(new int[]{4, 3, 1, 2, 5}));
        System.out.println(solution.solution(new int[]{5, 4, 3, 2, 1}));
    }
}

class 택배상자_Solution {
    public int solution(int[] order) {
        int n = order.length;
        Stack<Integer> subContainerBelt = new Stack<>();

        int idx = 0;
        for (int box = 1; box <= n; box++) {
            System.out.println(subContainerBelt);
            if (box == order[idx]) {
                idx++;
                continue;
            }

            while (!subContainerBelt.isEmpty() && subContainerBelt.peek() == order[idx]) {
                subContainerBelt.pop();
                idx++;
            }

            subContainerBelt.add(box);
        }

        while (!subContainerBelt.isEmpty()) {
            Integer box = subContainerBelt.pop();
            if (box == order[idx]) {
                idx++;
            } else {
                break;
            }
        }

        return idx;
    }
}