package level3;

public class 등굣길 {
    public static void main(String[] args) {
        등굣길_Solution solution = new 등굣길_Solution();
        int m = 4;
        int n = 3;
        int[][] puddles = {{2, 2}};

        System.out.println(solution.solution(m, n, puddles));
    }
}

class 등굣길_Solution {
    public int solution(int m, int n, int[][] puddles) {
        int answer = 0;
        int[][] roads = new int[n][m];

        for (int[] puddle : puddles) {
            roads[puddle[1] - 1][puddle[0] - 1] = -1;
        }

        roads[0][0] = 1;

        for (int i = 0; i < n; i++) {
            for (int j = 0; j < m; j++) {
                if (roads[i][j] == -1) {
                    roads[i][j] = 0;
                    continue;
                }

                if (i != 0) {
                    roads[i][j] += roads[i-1][j] % 1000000007;
                }

                if (j != 0) {
                    roads[i][j] += roads[i][j-1] % 1000000007;
                }
            }
        }
        return roads[n-1][m-1];
    }
}