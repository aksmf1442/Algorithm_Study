package 복습;

import java.util.Arrays;

public class 단속카메라복습 {
    public static void main(String[] args) {
        단속카메라_Solution solution = new 단속카메라_Solution();
        int[][] routes = {{-20,-15}, {-14,-5}, {-18,-13}, {-5,-3}};
        System.out.println(solution.solution(routes));
    }
}

class 단속카메라_Solution {
    public int solution(int[][] routes) {
        Arrays.sort(routes, (a, b) -> a[1] - b[1]);
        int answer = 1;
        int current = routes[0][1];

        for (int i = 1; i < routes.length; i++) {
            if (routes[i][0] > current) {
                answer++;
                current = routes[i][1];
            }
        }
        return answer;
    }
}
