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

class 양과늑대_Solution {
    int maxSheep = 0;
    int[] info;
    int[][][] visited;
    List<Integer>[] graph;

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

        for (int next: graph[current]) {
            // next의 노드에 도달했을 때 이전에 같은 상황을 마주한 적이 있는지 확인
            // 같은 상황을 마주했던 곳을 또 가면 무한 루프에 빠지기 때문에 방지하기 위해 visited를 사용
            if (visited[next][sheep][wolf] == 0) {
                info[current] = -1;
                visited[current][sheep][wolf] = 1;
                dfs(sheep, wolf, next);
                info[current] = currentAnimal;
                visited[current][sheep][wolf] = 0;
            }
        }
    }

    private void initGraph(int[] info, int[][] edges) {
        graph = new ArrayList[info.length];
        for (int i = 0; i < info.length; i++) {
            graph[i] = new ArrayList<>();
        }

        for (int i = 0; i < edges.length; i++) {
            int a = edges[i][0];
            int b = edges[i][1];
            graph[a].add(b);
            graph[b].add(a);
        }
    }
}
