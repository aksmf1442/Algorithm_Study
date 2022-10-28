package level3;

import java.util.Arrays;
import java.util.HashMap;
import java.util.HashSet;
import java.util.LinkedList;
import java.util.Map;
import java.util.Queue;
import java.util.Set;

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
        Queue<String> gemQueue = new LinkedList<>();

        int len = Integer.MAX_VALUE;
        int start = 0;
        int idx = 0;

        gemCategory.addAll(Arrays.asList(gems));

        // 보석을 처음부터 끝까지 순회
        for (int i = 0; i < gems.length; i++) {
            // 현재 보석을 큐에 넣고 현재 큐에 이 보석이 몇 개 들어있는지 확인하고 없으면 1 있으면 현재 값 + 1해서
            // map에서 해당 보석의 개수를 업데이트 해준다.
            gemMap.put(gems[i], gemMap.getOrDefault(gems[i], 0) + 1);
            gemQueue.add(gems[i]);

            // 큐의 맨 앞을 확인해서 해당 보석이 2개 이상이라면 맨 앞의 보석은 필요 없는 것이 되니까 제거해주고 idx를 +1해준다.
            while (gemMap.get(gemQueue.peek()) > 1) {
                gemMap.put(gemQueue.peek(), gemMap.get(gemQueue.poll()) - 1);
                idx++;
            }

            // map에 들어있는 보석의 개수가 보석의 종류의 개수와 같아지고, 그 구간이 이전에 찾았던 구간보다 짧다면 최신화해준다.
            if (gemMap.size() == gemCategory.size() && len > (i - idx)) {
                len = i - idx;
                start = idx + 1;
            }
        }

        return new int[]{start, start + len};
    }
}
