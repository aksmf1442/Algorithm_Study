package 복습;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

public class 귤고르기복습 {

    public static void main(String[] args) {
        귤고르기_Solution solution = new 귤고르기_Solution();
        int k = 6;
        int[] tangerine = {1, 3, 2, 5, 4, 5, 2, 3};
        System.out.println(solution.solution(k, tangerine));
    }
}


class 귤고르기_Solution {

    Map<Integer, Integer> map = new HashMap<>();

    public int solution(int k, int[] tangerine) {
        int answer = 0;
        for (int i : tangerine) {
            map.put(i, map.getOrDefault(i, 0) + 1);
        }

        List<List<Integer>> dp = new ArrayList<>();
        for (int i = 0; i <= k; i++) {
            dp.add(new ArrayList<>());
        }

        List<Map.Entry<Integer, Integer>> lst = new ArrayList<>(map.entrySet());
        lst.sort((x, y) -> y.getValue().compareTo(x.getValue()));

        for (Map.Entry<Integer, Integer> l : lst) {
            if (k <= 0) {
                break;
            }

            Integer value = l.getValue();
            k -= value;
            answer++;
        }

        return answer;
    }

}
