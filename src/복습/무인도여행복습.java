package 복습;

import java.util.ArrayList;
import java.util.Comparator;
import java.util.List;

public class 무인도여행복습 {
    public static void main(String[] args) {
        String[] maps = {"X591X", "X1X5X", "X231X", "1XXX1"};
        무인도여행_Solution solution = new 무인도여행_Solution();
        int[] result = solution.solution(maps);
        for (int i : result) {
            System.out.print(i + ", ");
        }
    }
}

class 무인도여행_Solution {
    int[] dx = {0, 0, -1, 1};
    int[] dy = {1, -1, 0, 0};
    int days;
    public int[] solution(String[] maps) {
        List<Integer> candidate = new ArrayList<>();
        String[][] mapArr = new String[maps.length][maps[0].length()];

        for (int i = 0; i < maps.length; i++) {
            for (int j = 0; j < maps[0].length(); j++) {
                mapArr[i][j] = String.valueOf(maps[i].charAt(j));
            }
        }

        for (int i = 0; i < maps.length; i++) {
            for (int j = 0; j < maps[0].length(); j++) {
                if (mapArr[i][j].equals("X")) {
                    continue;
                }
                days = 0;
                dfs(mapArr, i, j);
                candidate.add(days);
            }
        }

        candidate.sort(Comparator.comparingInt(x -> x));
        int[] result = candidate.stream().mapToInt(x -> x).sorted().toArray();
        return result.length == 0 ? new int[]{-1} : result;
    }

    private void dfs(String[][] mapArr, int x, int y) {
        if (mapArr[x][y].equals("X")) {
            return;
        }

        days += Integer.parseInt(mapArr[x][y]);
        mapArr[x][y] = "X";

        for (int i = 0; i < 4; i++) {
            int nx = dx[i] + x;
            int ny = dy[i] + y;

            if (nx < 0 || ny < 0 || nx >= mapArr.length || ny >= mapArr[0].length) {
                continue;
            }

            dfs(mapArr, nx, ny);
        }
    }
}
