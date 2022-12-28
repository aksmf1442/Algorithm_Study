package level3;

import java.util.ArrayList;
import java.util.List;

public class 양과늑대 {

    public static void main(String[] args) {
        양과늑대_Solution solution = new 양과늑대_Solution();
        int[] info = {0, 0, 1, 1, 1, 0, 1, 0, 1, 0, 1, 1};
        int[][] edges = {{0,1},{1,2},{1,4},{0,8},{8,7},{9,10},{9,11},{4,3},{6,5},{4,6},{8,9}};
        System.out.println("solution.solution(info, edges) = " + solution.solution(info, edges));

    }
}

/**
 * 핵심
 * - 모든 경우의 수를 따져봐야함.
 * - 양이 늑대의 수와 같아지는 순간 그 경우의 수는 종료
 * - 노드마다 몇 개의 양을 지니고 있을 때 방문을 했엇는지 체크하고 방문했었다면 종료
 */

/**
 * 풀이 순서
 * 1. 노드끼리 양방향으로 연결하기
 * 2. 루트노드부터 연결된 노드에 방문하기
 * 3. 현재 지니고 있는 양의 개수가 늑대와 같아지면 해당 경우의 수는 종료
 */
class 양과늑대_Solution {

    int maxSheep = 0;
    int[] info;
    int[][][] visited;
    List<List<Integer>> graph;

    public int solution(int[] info, int[][] edges) {
        this.info = info;
        visited = new int[info.length][info.length + 1][info.length + 1];
        initGraph(info, edges);
        dfs(0, 0, 0);
        return maxSheep;
    }

    private void dfs(int sheep, int wolf, int current) {
        int currentAnimal = info[current];
        if (currentAnimal == 0) {
            sheep++;
        }

        if (currentAnimal == 1) {
            wolf++;
        }

        if (sheep == wolf) {
            return;
        }

        maxSheep = Math.max(maxSheep, sheep);

        for (int next: graph.get(current)) {
            if (visited[next][sheep][wolf] == 1) {
                continue;
            }
            info[current] = -1;
            visited[current][sheep][wolf] = 1;
            dfs(sheep, wolf, next);
            info[current] = currentAnimal;
            visited[current][sheep][wolf] = 0;
        }
    }

    private void initGraph(int[] info, int[][] edges) {
        graph = new ArrayList<>();
        for (int i = 0; i < info.length; i++) {
            graph.add(new ArrayList<>());
        }

        for (int[] edge : edges) {
            int parent = edge[0];
            int child = edge[1];
            graph.get(parent).add(child);
            graph.get(child).add(parent);
        }
    }

}
