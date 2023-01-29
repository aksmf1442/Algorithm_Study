package level3;

/**
 * goal: 내구도가 1이상인 건물의 수 구하기
 */

/**
 * 알아야 할 것
 * 1. skill은 [type, r1, c1, r2, c2, degree] 형태
 * 2. type이 1일 경우 적의 공격, 2일 경우 회복
 * 3. (r1, c1)부터 (r2, c2)까지 공격하거나 회복
 */

/**
 * 풀이 방법 V1
 * 1. 이중 for문으로 직접 값 변경
 * -> 이렇게 하며 시간 복잡도가 250000(skill 길이) * 1000(N) * 1000(M) = 250000000000 2500억이 되어서 실패
 */

import java.util.Arrays;

/**
 * 풀이 방법 V2
 * 1. skill 따로 먼저 계산을 해서 한 번에 넣기.(부분합)
 */

public class 파괴되지않은건물 {

    public static void main(String[] args) {
        파괴되지않은건물_Solution solution = new 파괴되지않은건물_Solution();
//        int[][] board = {{5, 5, 5, 5, 5}, {5, 5, 5, 5, 5}, {5, 5, 5, 5, 5}, {5, 5, 5, 5, 5}};
//        int[][] skill = {{1, 0, 0, 3, 4, 4}, {
//            1, 2, 0, 2, 3, 2}, {2, 1, 0, 3, 1, 2}, {1, 0, 1, 3, 3, 1}};
        int[][] board = {{1,2,3},{4,5,6},{7,8,9}};
        int[][] skill = {{1,1,1,2,2,4},{1,0,0,1,1,2},{2,2,0,2,0,100}};

        System.out.println("solution.solution(board, skill) = " + solution.solution(board, skill));
    }
}

class 파괴되지않은건물_Solution {

    public int solution(int[][] board, int[][] skill) {
        int answer = 0;
        return answer;
    }


}

