package level2;

import java.io.IOException;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.LinkedList;
import java.util.List;
import java.util.Queue;

/**
 * 알아야 할 것
 * 1. 양방향 그래프
 * 2. 1번 마을에서 각 마을로 음식 배달을 하려고 하는데, N개의 마을에서 K시간 이하로 배달이 가능한 마을에서만
 *    주문을 받으려고 하고 가능한 수를 결과값으로 출력
 * 3. road의 a, b, c는 각각 a, b는 마을을 의미하고 c는 가중치
 */

/**
 * 제한사항
 * 1. 1 <= N <= 50
 * 2. 1 <= road.length <= 2,000
 * 3. 1 <= K <= 500,000
 */

/**
 * 문제 풀이 순서
 * 1. 일단 양방향 그래프를 표현하기
 * 2.
 */
public class 배달 {

    static List<int[]>[] graph;

    public static void main(String[] args) throws IOException {
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

        int result = 1;
        graph = new ArrayList[N+1];

        // 각 road 별 가중치 초기화
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

            graph[a].add(new int[]{b, c});
            graph[b].add(new int[]{a, c});
        }

        for (int i = 2; i <= N; i++) {
            int distance = bfs(1, i, N);
            if (distance <= K) {
                System.out.println("i = " + i);
                result += 1;
            }
        }
        System.out.println("result = " + result);

    }

    private static int bfs(int start, int end, int N) {
        Queue<List<Integer>> queue = new LinkedList<>();
        int[] arr = new int[N + 1];
        Arrays.fill(arr, Integer.MAX_VALUE);
        arr[start] = 0;
        List<Integer> a = new ArrayList<>();
        a.add(start);
        a.add(0);
        queue.add(a);

        while (!queue.isEmpty()) {
            List<Integer> lst = queue.poll();
            int node = lst.get(0);
            int distance = lst.get(1);

            for (int[] current : graph[node]) {
                int target = current[0];
                int targetDistance = current[1];
                
                if (targetDistance + distance >= arr[target]) {
                    continue;
                }

                int dis = targetDistance + distance;

                List<Integer> l = new ArrayList<>();
                l.add(target);
                l.add(dis);

                // 최소값 초기화
                arr[target] = dis;
                
                queue.add(l);
            }
        }
        
        return arr[end];
    }
}
