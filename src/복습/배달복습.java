package 복습;

import java.util.*;

public class 배달복습 {

    public static void main(String[] args) {
        // 예제 값 초기화
        int N = 5;
        int[][] road = new int[6][3];
        road[0] = new int[]{1, 2, 1};
        road[1] = new int[]{2, 3, 3};
        road[2] = new int[]{5, 2, 2};
        road[3] = new int[]{1, 4, 2};
        road[4] = new int[]{5, 3, 1};
        road[5] = new int[]{5, 4, 2};
        int K = 3;

        배달_Solution solution = new 배달_Solution();
        System.out.println("solution = " + solution.solution(N, road, K));

    }

}

class 배달_Solution {

    List<Node>[] graph;

    public int solution(int N, int[][] road, int K) {
        graph = new ArrayList[N+1];

        // road의 각 마을과 가중치를 통한 양방향 그래프를 초기화
        for (int i = 0; i < road.length; i++) {
            int[] arr = road[i];
            int a = arr[0];
            int b = arr[1];
            int c = arr[2];

            if (graph[a] == null) {
                graph[a] = new ArrayList<>();
            }

            if (graph[b] == null) {
                graph[b] = new ArrayList<>();
            }

            graph[a].add(new Node(b, c));
            graph[b].add(new Node(a, c));
        }

        int result = dijkstra(1, N, K);

        return result;
    }

    private int dijkstra(int start, int N, int K) {
        // 최단 거리를 넣을 배열
        int[] distanceArr = new int[N + 1];
        Arrays.fill(distanceArr, Integer.MAX_VALUE);
        distanceArr[start] = 0;

        // queue 현재 있는 마을과 여기까지의 가중치를 넣을 예정
        Queue<Node> queue = new LinkedList<>();
        queue.add(new Node(start, 0));

        while (!queue.isEmpty()) {
            Node node = queue.poll();

            for (Node current : graph[node.target]) {
                if (current.cost + node.cost >= distanceArr[current.target]) {
                    continue;
                }

                int dis = node.cost + current.cost;

                distanceArr[current.target] = dis;
                queue.add(new Node(current.target, dis));
            }

        }

        int result = 1;
        for (int i = 2; i < distanceArr.length; i++) {
            if (distanceArr[i] <= K) {
                result += 1;
            }
        }
        return result;
    }

    class Node {
        int target;
        int cost;

        public Node(int target, int cost) {
            this.target = target;
            this.cost = cost;
        }

    }

}
