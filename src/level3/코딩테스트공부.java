package level3;

public class 코딩테스트공부 {
    public static void main(String[] args) {
        int alp = 10;
        int cop = 10;
        int[][] problems = {{10, 15, 2, 1, 2}, {20, 20, 3, 3, 4}};
        코딩테스트공부_Solution solution = new 코딩테스트공부_Solution();

        System.out.println(solution.solution(alp, cop, problems));
    }
}

class 코딩테스트공부_Solution {
    public int solution(int alp, int cop, int[][] problems) {

        int maxAlp = 0;
        int maxCop = 0;

        for (int i = 0; i < problems.length; i++) {
            maxAlp = Math.max(problems[i][0], maxAlp);
            maxCop = Math.max(problems[i][1], maxCop);
        }

        alp = Math.min(alp, maxAlp);
        cop = Math.min(cop, maxCop);

        int[][] dp = new int[maxAlp + 2][maxCop + 2];

        for (int i = alp; i <= maxAlp; i++) {
            for (int j = cop; j <= maxCop; j++) {
                dp[i][j] = Integer.MAX_VALUE;
            }
        }

        dp[alp][cop] = 0;

        for (int i = alp; i <= maxAlp; i++) {
            for (int j = cop; j <= maxCop; j++) {

                dp[i + 1][j] = Math.min(dp[i + 1][j], dp[i][j] + 1);
                dp[i][j + 1] = Math.min(dp[i][j + 1], dp[i][j] + 1);

                for (int[] problem : problems) {

                    int alpReq = problem[0];
                    int copReq = problem[1];
                    if (i >= alpReq && j >= copReq) {
                        int currentAlp = problem[2] + i;
                        int currentCop = problem[3] + j;
                        int cost = problem[4];

                        if (currentAlp > maxAlp && currentCop > maxCop) {
                            dp[maxAlp][maxCop] = Math.min(dp[maxAlp][maxCop], dp[i][j] + cost);
                        } else if (currentAlp > maxAlp) {
                            dp[maxAlp][currentCop] = Math.min(dp[maxAlp][currentCop], dp[i][j] + cost);
                        } else if (currentCop > maxCop) {
                            dp[currentAlp][maxCop] = Math.min(dp[currentAlp][maxCop], dp[i][j] + cost);
                        } else {
                            dp[currentAlp][currentCop] = Math.min(dp[currentAlp][currentCop], dp[i][j] + cost);
                        }
                    }

                }
            }
        }

        return dp[maxAlp][maxCop];
    }
}