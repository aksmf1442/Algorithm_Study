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
        Set<String> gemCategory = new HashSet<>();

        int len = Integer.MAX_VALUE;
        int start = 0;
        int end = 0;
        int idx = 0;

        gemCategory.addAll(Arrays.asList(gems));

        // 보석을 처음부터 끝까지 순회
        for (int i = 0; i < gems.length; i++) {

            gemMap.put(gems[i], gemMap.getOrDefault(gems[i], 0) + 1);

            while (gemMap.get(gems[idx]) > 1) {
                gemMap.put(gems[idx], gemMap.get(gems[idx]) - 1);
                idx++;
            }

            if (gemMap.size() == gemCategory.size() && len > (i - idx)) {
                len = i - idx;
                end = i + 1;
                start = idx + 1;
            }
        }
        return new int[]{start, end};
    }
}
