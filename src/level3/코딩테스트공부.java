package level3;

import java.util.LinkedList;
import java.util.Queue;

public class 코딩테스트공부 {
    public static void main(String[] args) {
        int alp = 10;
        int cop = 10;
        int[][] problems = {{10, 15, 2, 1, 2}, {20, 20, 3, 3, 4}};
        코딩테스트공부_Solution solution = new 코딩테스트공부_Solution();

        System.out.println(solution.solution(alp,cop,problems));
    }
}

class 코딩테스트공부_Solution {
    public int solution(int alp, int cop, int[][] problems) {
        int answer = 0;
        int maxAlp = 0, maxCop = 0;
        for (int[] p : problems) {
            maxAlp = Math.max(p[0], maxAlp);
            maxCop = Math.max(p[1], maxCop);
        }

        alp = Math.min(alp, maxAlp);
        cop = Math.min(cop, maxCop);

        int[][] dp = new int[maxAlp + 1][maxCop + 1];
        for (int al = alp; al <= maxAlp; al++) {
            for (int co = cop; co <= maxCop; co++) {
                if (co == cop) {
                    dp[al][co] = al;
                } else if (al == alp) {
                    dp[al][co] = co;
                } else {
                    dp[al][co] = Math.min(dp[al - 1][co], dp[al][co - 1]) + 1;
                }
            }
        }
        // System.out.println(dp[maxAlp][maxCop]);

        Queue<Point> que = new LinkedList<>();
        que.offer(new Point(alp, cop, 0));

        while (!que.isEmpty()) {
            Point point = que.poll();

            if (dp[point.al][point.co] < point.cost) {
                continue;
            }

            // System.out.println(point.al + " " + point.co);

            // alpReq, copReq, alpRwd, copRwd, cost
            int alpReq, copReq, alpRwd, copRwd, cost;
            int nextAlp, nextCop;
            for (int[] p : problems) {
                alpReq = p[0];
                copReq = p[1];
                alpRwd = p[2];
                copRwd = p[3];
                cost = p[4];
                nextAlp = Math.min(maxAlp, point.al + alpRwd);
                nextCop = Math.min(maxCop, point.co + copRwd);

                if (!(alpReq <= point.al && copReq <= point.co) ||
                        dp[nextAlp][nextCop] <= point.cost + cost) {
                    continue;
                }

                dp[nextAlp][nextCop] = point.cost + cost;
                que.offer(new Point(nextAlp, nextCop, point.cost + cost));
            }

            nextAlp = Math.min(maxAlp, point.al + 1);
            nextCop = Math.min(maxCop, point.co + 1);
            if (dp[nextAlp][point.co] > point.cost + 1) {
                dp[nextAlp][point.co] = point.cost + 1;
                que.offer(new Point(nextAlp, point.co, point.cost + 1));
            }

            if (dp[point.al][nextCop] > point.cost + 1) {
                dp[point.al][nextCop] = point.cost + 1;
                que.offer(new Point(point.al, nextCop, point.cost + 1));
            }
        }

        return dp[maxAlp][maxCop];
    }
}

class Point {
    int al;
    int co;
    int cost;

    public Point(int al, int co, int cost) {
        this.al = al;
        this.co = co;
        this.cost = cost;
    }
}