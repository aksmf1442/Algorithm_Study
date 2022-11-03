package level2;

import java.util.Arrays;

public class 거리두기 {

    public static void main(String[] args) {
        거리두기_Solution solution = new 거리두기_Solution();
        String[][] places = {{"POOOP", "OXXOX", "OPXPX", "OOXOX", "POXXP"},
            {"POOPX", "OXPXP", "PXXXO", "OXXXO", "OOOPP"},
            {"PXOPX", "OXOXP", "OXPOX", "OXXOP", "PXPOX"},
            {"OOOXX", "XOOOX", "OOOXX", "OXOOX", "OOOOO"},
            {"PXPXP", "XPXPX", "PXPXP", "XPXPX", "PXPXP"}};
        int[] result = solution.solution(places);
        for (int i : result) {
            System.out.print(i + ",");
        }
    }
}

/**
 * goal: 거리두기를 잘 하고 있는지 확인하기(거리두기를 잘 지켰다면 1, 지키지 않았다면 0)
 * <p>
 * 알아야 할 것 1. 대기실은 5개이며, 각 대기실은 5x5 크기 2. |r1 - r2| + |c1 - c2|가 2이하가 되도록 거리를 유지해야 한다. 3. 단 응시자가
 * 앉아있는 자리 사이가 파티션으로 막혀 있을 경우에는 허용 4. P는 응시자가 앉아있는 자리를 의미합니다. | O는 빈 테이블을 의미합니다. | X는 파티션을 의미합니다.
 */

/**
 * 알아야 할 것
 * 1. 대기실은 5개이며, 각 대기실은 5x5 크기
 * 2. |r1 - r2| + |c1 - c2|가 2이하가 되도록 거리를 유지해야 한다.
 * 3. 단 응시자가 앉아있는 자리 사이가 파티션으로 막혀 있을 경우에는 허용
 * 4. P는 응시자가 앉아있는 자리를 의미합니다. | O는 빈 테이블을 의미합니다. | X는 파티션을 의미합니다.
 */

/**
 * 문제 풀이 순서
 * 1.
 */

class 거리두기_Solution {
    int[] answer;
    int[][] visited;
    int[] dx = {0, 0, -1, 1};
    int[] dy = {1, -1, 0, 0};

    public int[] solution(String[][] places) {
        answer = new int[places.length];
        Arrays.fill(answer, 1);
        for (int i = 0; i < places.length; i++) {
            String[] place = places[i];
            checkPlace(place, i);
        }
        return answer;
    }

    private void checkPlace(String[] place, int currentIdx) {
        for (int x = 0; x < 5; x++) {
            if (answer[currentIdx] == 0) {
                break;
            }
            for (int y = 0; y < 5; y++) {
                visited = new int[5][5];
                if (place[x].charAt(y) != 'P') {
                    continue;
                }
                dfs(place, 0, x, y, currentIdx);
            }
        }
    }

    private void dfs(String[] place, int depth, int x, int y, int currentIdx) {
        visited[x][y] = 1;

        char current = place[x].charAt(y);
        if (current == 'X' || (depth == 2 && current != 'P')) {
            return;
        }

        if (depth != 0 && current == 'P') {
            answer[currentIdx] = 0;
            return;
        }

        for (int i = 0; i < 4; i++) {
            int nx = x + dx[i];
            int ny = y + dy[i];
            if (nx < 0 || ny < 0 || nx >= 5 || ny >= 5 || visited[nx][ny] == 1) {
                continue;
            }
            dfs(place, depth + 1, nx, ny, currentIdx);
        }
    }
}
