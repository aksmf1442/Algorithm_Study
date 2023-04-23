package level3;

import java.util.*;

public class 보석쇼핑 {

    public static void main(String[] args) {
        String[] gems = {"DIA", "RUBY", "RUBY", "DIA", "DIA", "EMERALD", "SAPPHIRE", "DIA"};
        보석쇼핑_Solution solution = new 보석쇼핑_Solution();
        int[] result = solution.solution(gems);
        for (int i : result) {
            System.out.print(i + ",");
        }
    }
}

class 보석쇼핑_Solution {
    public int[] solution(String[] gems) {
        Map<String, Integer> gemMap = new HashMap<>();
        int gemSize = new HashSet<>(Arrays.asList(gems)).size();

        int len = Integer.MAX_VALUE;

        int start = 0;
        int end = 0;
        int idx = 0;

        for (int i = 0; i < gems.length; i++) {
            // gemMap에 넣기.
            gemMap.put(gems[i], gemMap.getOrDefault(gems[i], 0) + 1);

            // gemMap의 맨 앞의 값이 두 개 이상일 경우 start값을 +1해준다.
            while (gemMap.get(gems[idx]) > 1) {
                gemMap.put(gems[idx], gemMap.get(gems[idx]) - 1);
                idx++;
            }

            // 최대 길이 정하기
            if (gemMap.size() == gemSize && len > (i - idx + 1)) {
                len = i - idx + 1;
                start = idx + 1;
                end = i + 1;
            }
        }

        return new int[]{start, end};
    }
}
