package 복습;

import java.util.ArrayList;
import java.util.LinkedList;
import java.util.List;
import java.util.Queue;

public class 카드짝맞추기복습 {

    public static void main(String[] args) {
        카드짝맞추기_Solution solution = new 카드짝맞추기_Solution();
        int[][] board = {{3,0,0,2},{0,0,1,0},{0,1,0,0},{2,0,0,3}};
        int r = 0;
        int c = 1;

        System.out.println(solution.solution(board, r, c));
    }
}


class 카드짝맞추기_Solution {

    int[] dx = {0, 0, -1, 1};
    int[] dy = {1, -1, 0, 0};

    int result = Integer.MAX_VALUE;
    int[] visited;
    int r;
    int c;
    int[][] board;

    public int solution(int[][] board, int r, int c) {
        this.r = r;
        this.c = c;
        this.board = board;

        int flipCardSize = findFlipCardSize(board);
        visited = new int[flipCardSize];

        List<List<Location>> cardLocations = findCardLocations(board, flipCardSize);

        dfs(new ArrayList<>(), cardLocations, r, c);

        return result;
    }

    private int findMinDistances(List<List<Location>> candidate) {
        int x = r;
        int y = c;
        int count = 0;

        int[][] board = new int[4][4];
        for (int i = 0; i < 4; i++) {
            for (int j = 0; j < 4; j++) {
                board[i][j] = this.board[i][j];
            }
        }

        System.out.println("1111");
        for (List<Location> locations : candidate) {
            for (int i = 0; i < 2; i++) {
                count += findMinDistance(board, locations.get(i), x, y) + 1;
                System.out.println(count);
                x = locations.get(i).x;
                y = locations.get(i).y;
                board[x][y] = 0;
            }
        }
        if (count == 14) {
            for (List<Location> locations : candidate) {
                System.out.println("----");
                System.out.println(locations.get(0).x);
                System.out.println(locations.get(0).y);
                System.out.println("----");
                System.out.println(locations.get(1).x);
                System.out.println(locations.get(1).y);
                System.out.println("----");
            }
        }

        return count;
    }

    private int findMinDistance(int[][] board, Location target, int x, int y) {
        Queue<Node> queue = new LinkedList<>();
        int[][] visited = new int[4][4];

        visited[x][y] = 1;
        queue.add(new Node(x, y, 0));

        while (!queue.isEmpty()) {
            Node node = queue.poll();
            if (target.x == node.x && target.y == node.y) {
                return node.distance;
            }

            for (int i = 0; i < 4; i++) {
                int nx = x + dx[i];
                int ny = y + dy[i];
                if (nx < 0 || ny < 0 || nx >= 4 || ny >= 4 || visited[nx][ny] == 1) {
                    continue;
                }

                visited[nx][ny] = 1;
                queue.add(new Node(nx, ny, node.distance + 1));
            }

            for (int i = 0; i < 4; i++) {
                Location current = findCardLocation(board, node.x, node.y, i);
                if (visited[current.x][current.y] == 1) {
                    continue;
                }
                visited[current.x][current.y] = 1;
                queue.add(new Node(current.x, current.y, node.distance + 1));
            }
        }

        return 2;
    }

    private Location findCardLocation(int[][] board, int x, int y, int i) {
        x += dx[i];
        y += dy[i];
        while (x >= 0 && x < 4 && y >= 0 && y < 4) {
            if (board[x][y] != 0) {
                return new Location(x, y);
            }
            x += dx[i];
            y += dy[i];
        }
        return new Location(x - dx[i], y - dy[i]);
    }

    private void dfs(List<List<Location>> candidate, List<List<Location>> cardLocations, int r, int c) {
        if (candidate.size() == cardLocations.size()) {
            this.r = r;
            this.c = c;
            result = Math.min(result, findMinDistances(candidate));
            return;
        }

        for (int i = 0; i < cardLocations.size(); i++) {
            if (visited[i] == 1) {
                continue;
            }
            visited[i] = 1;
            List<Location> locations = cardLocations.get(i);

            candidate.add(cardLocations.get(i));
            dfs(candidate, cardLocations, r, c);
            candidate.remove(candidate.size() - 1);

            candidate.add(List.of(locations.get(1), locations.get(0)));
            dfs(candidate, cardLocations, r, c);
            candidate.remove(candidate.size() - 1);
            visited[i] = 0;
        }
    }

    private List<List<Location>> findCardLocations(int[][] board, int flipCardSize) {
        List<List<Location>> cardLocations = new ArrayList<>();
        for (int i = 0; i < flipCardSize; i++) {
            cardLocations.add(new ArrayList<>());
        }

        for (int i = 0; i < board.length; i++) {
            for (int j = 0; j < board[i].length; j++) {
                if (board[i][j] != 0) {
                    int cardIdx = board[i][j] - 1;
                    cardLocations.get(cardIdx).add(new Location(i, j));
                }
            }
        }
        return cardLocations;
    }

    private int findFlipCardSize(int[][] board) {
        int size = 0;
        for (int i = 0; i < board.length; i++) {
            for (int j = 0; j < board[i].length; j++) {
                if (board[i][j] > 0) {
                    size++;
                }
            }
        }
        return size / 2;
    }

    class Location {
        int x;
        int y;

        public Location(int x, int y) {
            this.x = x;
            this.y = y;
        }
    }

    class Node {
        int x;
        int y;
        int distance;

        public Node(int x, int y, int distance) {
            this.x = x;
            this.y = y;
            this.distance = distance;
        }
    }
}
