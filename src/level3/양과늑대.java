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
 * 나의 생각
 * 1. 양의 수가 늑대 수와 같아지는 순간 양이 이길 수가 없는 상태가 됌 그래서 정지.
 * 2. 스택을 사용한다.
 * 3.
 */

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
        if (info[current] == 0) {
            sheep++;
        }

        if (info[current] == 1) {
            wolf++;
        }

        if (sheep == wolf) {
            return;
        }

        maxSheep = Math.max(maxSheep, sheep);

        for (int i = 0; i < graph[current].size(); i++) {
            Integer next = graph[current].get(i);
            int temp = info[current];
            if (visited[next][sheep][wolf] == 0) {
                info[current] = -1;
                visited[current][sheep][wolf] = 1;
                dfs(sheep, wolf, next);
                info[current] = temp;
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
