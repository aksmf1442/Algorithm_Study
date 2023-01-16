package level3;

import java.util.*;

public class 야근지수 {
    public static void main(String[] args) {
        야근지수_Solution solution = new 야근지수_Solution();
        int[] works = {2, 1, 2};
        int n = 1;
        System.out.println(solution.solution(n, works));
    }
}

class 야근지수_Solution {
    public long solution(int n, int[] works) {
        long answer = 0;

        PriorityQueue<Integer> queue = new PriorityQueue<>(Comparator.reverseOrder());
        for (int work : works) {
            queue.add(work);
        }

        while (n > 0) {
            queue.add(queue.poll() - 1);
            n--;
            if (queue.peek() == 0) {
                return 0;
            }
        }

        while (!queue.isEmpty()) {
            answer += Math.pow(queue.poll(), 2);
        }

        return answer;
    }
}
