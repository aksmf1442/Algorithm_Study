package level3;

import java.util.HashMap;
import java.util.Map;

public class 방문길이 {

    public static void main(String[] args) {
        방문길이_Solution solution = new 방문길이_Solution();
        String dirs = "ULURRDLLU";
        System.out.println(solution.solution(dirs));
    }
}

class 방문길이_Solution {

    Map<String, Integer> visited = new HashMap<>();

    public int solution(String dirs) {
        int answer = 0;
        int x = 0;
        int y = 0;

        for (int i = 0; i < dirs.length(); i++) {
            char cmd = dirs.charAt(i);
            String xy = String.valueOf(x) + String.valueOf(y);
            if (cmd == 'U') {
                if (x + 1 > 5) {
                    continue;
                }
                String moveXY = String.valueOf(x + 1) + String.valueOf(y);
                visited.put(xy + moveXY, 1);
                visited.put(moveXY + xy, 1);
                x++;
            }

            if (cmd == 'D') {
                if (x - 1 < -5) {
                    continue;
                }
                String moveXY = String.valueOf(x - 1) + String.valueOf(y);
                visited.put(xy + moveXY, 1);
                visited.put(moveXY + xy, 1);
                x--;
            }

            if (cmd == 'R') {
                if (y + 1 > 5) {
                    continue;
                }
                String moveXY = String.valueOf(x) + String.valueOf(y + 1);
                visited.put(xy + moveXY, 1);
                visited.put(moveXY + xy, 1);
                y++;
            }

            if (cmd == 'L') {
                if (y - 1 < -5) {
                    continue;
                }
                String moveXY = String.valueOf(x) + String.valueOf(y - 1);
                visited.put(xy + moveXY, 1);
                visited.put(moveXY + xy, 1);
                y--;
            }
        }
        return visited.size() / 2;
    }
}
