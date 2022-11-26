package level2;

public class 방문길이 {

    public static void main(String[] args) {
        방문길이_Solution solution = new 방문길이_Solution();
        String dirs = "ULURRDLLU";
        System.out.println(solution.solution(dirs));
    }
}

class 방문길이_Solution {

    public int solution(String dirs) {
        int answer = 0;
        int x = 5;
        int y = 5;
        int[][][][] map = new int[11][11][11][11];

        int[] dx = {1, -1, 0, 0};
        int[] dy = {0, 0, 1, -1};
        for(int i = 0 ; i < dirs.length(); i++){
            char tmp = dirs.charAt(i);
            int dir = -1;
            if(tmp == 'U'){
                dir = 0;
            }
            else if(tmp == 'D'){
                dir = 1;
            }
            else if(tmp == 'R'){
                dir = 2;
            }
            else if(tmp == 'L'){
                dir = 3;
            }

            int nx = x + dx[dir];
            int ny = y + dy[dir];
            if(0 > nx || nx >= 11 || 0 > ny || ny >= 11) continue;
            if(map[x][y][nx][ny] == 0){
                map[x][y][nx][ny] = 1;
                map[nx][ny][x][y] = 1;
                answer++;
            }
            x = nx;
            y = ny;
        }
        return answer;
    }
}
