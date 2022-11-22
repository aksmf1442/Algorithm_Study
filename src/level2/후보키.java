package level2;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

public class 후보키 {

    public static void main(String[] args) {
        후보키_Solution solution = new 후보키_Solution();
        String[][] relation = {{"100", "ryan", "music", "2"}, {"200", "apeach", "math", "2"},
            {"300", "tube", "computer", "3"}, {"400", "con", "computer", "4"},
            {"500", "muzi", "music", "3"}, {"600", "apeach", "music", "2"}};
        System.out.println(solution.solution(relation));
    }
}

class 후보키_Solution {

    List<String> candidateKeys = new ArrayList<>();
    int row = 0;
    int column = 0;

    public int solution(String[][] relation) {
        row = relation.length;
        column = relation[0].length;

        // i개의 컬럼을 만드는 경우의 수
        for (int i = 1; i <= column; i++) {
            int[] visited = new int[column];
            dfs(visited, 0, i, 0, relation);
        }
        return candidateKeys.size();
    }

    private void dfs(int[] visited, int start, int target, int depth, String[][] relation) {
        if (depth == target) {
            List<Integer> keyIndex = new ArrayList<>();
            String key = "";

            // 컬럼을 target의 수만큼 합치기
            for (int i = 0; i < column; i++) {
                if (visited[i] == 1) {
                    key += String.valueOf(i);
                    keyIndex.add(i);
                }
            }

            Map<String, Integer> map = new HashMap<>();

            for (int i = 0; i < row; i++) {
                String attributes = "";
                for (int j : keyIndex) {
                    attributes += relation[i][j];
                }

                // 유일성 만족 X
                if (map.containsKey(attributes)) {
                    return;
                }
                map.put(attributes, 0);
            }

            for (String candidateKey : candidateKeys) {
                int count = 0;
                for (int i = 0; i < key.length(); i++) {
                    String subS = String.valueOf(key.charAt(i));
                    if (candidateKey.contains(subS)) {
                        count++;
                    }
                }
                // 최소성 만족 X(이미 다른 후보키에 포함되어 있는 조합)
                if (count == candidateKey.length()) {
                    return;
                }
            }
            candidateKeys.add(key);
            return;
        }

        for (int i = start; i < column; i++) {
            if (visited[i] == 1) {
                continue;
            };
            visited[i] = 1;
            dfs(visited, i + 1, target, depth + 1, relation);
            visited[i] = 0;
        }
    }
}
