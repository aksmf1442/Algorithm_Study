package level2;

import java.util.Arrays;
import java.util.LinkedList;
import java.util.Queue;
import java.util.stream.Collectors;

public class 두큐합같게만들기 {

    public static void main(String[] args) {
        두큐합같게만들기_Solution solution = new 두큐합같게만들기_Solution();
//        int[] queue1 = {1,1};
        int[] queue1 = {3, 2, 7, 2};
//        int[] queue2 = {1,5};
        int[] queue2 = {4, 6, 5, 1};
        System.out.println(
            "solution.solution(queue1, queue2) = " + solution.solution(queue1, queue2));
    }
}

/**
 * goal: 두 큐의 합을 같게 하기
 */

/**
 * 문제 풀이 방법
 * 1. 두 큐의 합 구해서 나누기 2 하기.
 * 2. 어떤 큐에서 뺄지에 대한 모든 경우의 수 구해서 가장 작은 횟수만에 목표를 달성하는지 구하기(백트래킹)
 */

class 두큐합같게만들기_Solution {

    public int solution(int[] queue1, int[] queue2) {
        // 배열을 리스트로 변환
        Queue<Integer> q1 = Arrays.stream(queue1).boxed()
            .collect(Collectors.toCollection(LinkedList::new));
        Queue<Integer> q2 = Arrays.stream(queue2).boxed()
            .collect(Collectors.toCollection(LinkedList::new));
        long sumQ1 = sumQueue(q1);
        long sumQ2 = sumQueue(q2);
        long targetValue = (sumQ1 + sumQ2) / 2;

        int count = 0;
        int max_count = q1.size() * 3;
        while (sumQ1 != targetValue && count < max_count) {
            if (sumQ1 < targetValue) {
                Integer value = q2.poll();
                sumQ1 += value;
                sumQ2 -= value;
                q1.add(value);
            } else {
                Integer value = q1.poll();
                sumQ2 += value;
                sumQ1 -= value;
                q2.add(value);
            }
            count++;
        }

        return count == max_count ? -1 : count;
    }

    private long sumQueue(Queue<Integer> list) {
        return list.isEmpty() ? 0 : list.stream().reduce(Integer::sum).get();
    }

}
