package level2;

import java.util.ArrayList;
import java.util.Collections;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

public class 순위검색 {

    public static void main(String[] args) {
        순위검색_Solution solution = new 순위검색_Solution();
        String[] info = {"java backend junior pizza 150", "python frontend senior chicken 210",
            "python frontend senior chicken 150", "cpp backend senior pizza 260",
            "java backend junior chicken 80", "python backend senior chicken 50"};
        String[] query = {"java and backend and junior and pizza 100",
            "python and frontend and senior and chicken 200", "cpp and - and senior and pizza 250",
            "- and backend and senior and - 150", "- and - and - and chicken 100",
            "- and - and - and - 150"};
        int[] result = solution.solution(info, query);
        for (int i : result) {
            System.out.print(i + ",");
        }
    }
}

class 순위검색_Solution {

    Map<String, List<Integer>> map = new HashMap<>();
    public int[] solution(String[] info, String[] query) {
        int[] answer = new int[query.length];
        for (String userInfo: info) {
            dfs("", userInfo.split(" "), 0);
        }

        for (String key : map.keySet()) {
            Collections.sort(map.get(key));
        }

        for (int i = 0; i < query.length; i++) {
            String[] splitQuery = query[i].replaceAll(" and ", "").split(" ");
            String key = splitQuery[0];
            int score = Integer.parseInt(splitQuery[1]);

            answer[i] = binarySearch(key, score);
        }

        return answer;
    }

    private int binarySearch(String key, int score) {
        if (map.containsKey(key)) {
            List<Integer> lst = map.get(key);
            int left = 0;
            int right = lst.size() - 1;

            if (lst.get(right) < score) {
                return 0;
            }

            while (left < right) {
                int mid = (left + right) / 2;
                if (lst.get(mid) < score) {
                    left = mid + 1;
                } else {
                    right = mid;
                }
            }
            return lst.size() - left;
        }
        return 0;
    }

    private void dfs(String key, String[] userInfo, int depth) {
        if (depth == 4) {
            if (map.containsKey(key)) {
                map.get(key).add(Integer.parseInt(userInfo[4]));
            } else {
                List<Integer> lst = new ArrayList<>();
                lst.add(Integer.parseInt(userInfo[4]));
                map.put(key, lst);
            }
            return;
        }

        dfs(key + "-", userInfo, depth + 1);
        dfs(key + userInfo[depth], userInfo, depth + 1);
    }
}
