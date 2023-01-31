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

    Map<String, List<Integer>> combination = new HashMap<>();
    public int[] solution(String[] info, String[] query) {
        int[] answer = new int[query.length];

        for (String userInfo: info) {
            createCombination("", userInfo.split(" "), 0);
        }

        for (String key : combination.keySet()) {
            Collections.sort(combination.get(key));
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
        if (combination.containsKey(key)) {
            List<Integer> lst = combination.get(key);
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

    private void createCombination(String key, String[] userInfo, int depth) {
        if (depth == 4) {
            List<Integer> lst = combination.getOrDefault(key, new ArrayList<>());
            lst.add(Integer.parseInt(userInfo[4]));
            combination.put(key, lst);
            return;
        }

        createCombination(key + "-", userInfo, depth + 1);
        createCombination(key + userInfo[depth], userInfo, depth + 1);
    }
}
