package level3;


import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;
import java.util.PriorityQueue;
import java.util.Queue;

public class 합승택시요금 {

    public static void main(String[] args) {
        합승택시요금_Solution solution = new 합승택시요금_Solution();
        int n = 6;
        int s = 4;
        int a = 6;
        int b = 2;
        int[][] fares = {{4, 1, 10}, {3, 5, 24}, {5, 6, 2}, {3, 1, 41}, {5, 1, 24}, {4, 6, 50},
            {2, 4, 66}, {2, 3, 22}, {1, 6, 25}};
        System.out.println(
            "solution.solution(n, s, a, b, fares) = " + solution.solution(n, s, a, b, fares));
    }
}


/**
 * goal: 무지와 어피치가 각자 집으로 갈 때 둘이 합쳐서 택시 요금이 가장 적게 나오는 경우의 수를 결과로 나타내기.
 */

/**
 * 풀이 방법
 * 1. 일단 노드끼리의 최단 거리를 구한다.
 * 2. 그리고 (출발 노드인 s에서 1~n 중 한 곳의 노드 거리) +
 *    (도착한 노드의 위치에서 각자 집까지의 최단 거리)를 모든 경우의 수를 구한다.
 * 3. 모든 경우의 수를 구하면 그 중 가장 최단 거리를 출력하면 된다.
 */

class 합승택시요금_Solution{

    int[][] distances;
    List<Point>[] graph;

    public int solution(int n, int s, int a, int b, int[][] fares) {
        distances = new int[n + 1][n + 1];
        graph = new List[n + 1];

        for (int i = 0; i <= n; i++) {
            Arrays.fill(distances[i], Integer.MAX_VALUE);
        }

        for (int i = 0; i <= n; i++) {
            graph[i] = new ArrayList<>();
        }

        for (int[] fare : fares) {
            int point1 = fare[0];
            int point2 = fare[1];
            int distance = fare[2];
            graph[point1].add(new Point(point2, distance));
            graph[point2].add(new Point(point1, distance));
        }

        for (int i = 1; i <= n; i++) {
            findShortDistances(i);
        }

        int result = Integer.MAX_VALUE;
        for (int i = 1; i <= n; i++) {
            result = Math.min(result, distances[s][i] + distances[i][a] + distances[i][b]);
        }

        return result;
    }

    private void findShortDistances(int start) {
        Queue<Point> queue = new PriorityQueue<>();
        queue.add(new Point(start, 0));
        distances[start][start] = 0;
        while (!queue.isEmpty()) {
            Point current = queue.poll();

            for (Point target : graph[current.point]) {
                int distance = current.distance + target.distance;

                if (distances[start][target.point] < distance) {
                    continue;
                }

                distances[start][target.point] = distance;
                queue.add(new Point(target.point, distance));
            }
        }
    }
}

class Point implements Comparable<Point> {
    int point;
    int distance;

    public Point(int point, int distance) {
        this.point = point;
        this.distance = distance;
    }

    @Override
    public int compareTo(Point o) {
        return this.distance - o.distance;
    }
}
