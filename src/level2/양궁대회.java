package level2;


import java.util.Arrays;

public class 양궁대회 {

    public static void main(String[] args) {
        양궁대회_Solution solution = new 양궁대회_Solution();
//        int n = 5;
//        int[] info = {2, 1, 1, 1, 0, 0, 0, 0, 0, 0, 0};
        int n = 4;
        int[] info = {2, 0, 0, 1, 1, 0, 0, 0, 0, 0, 0};
        int[] result = solution.solution(n, info);

        for (int i = 0; i < result.length; i++) {
            System.out.println("result = " + result[i]);
        }
    }
}

/**
 * goal: 1. 라이언이 어피치를 가장 큰 점수차로 이기기 위해서 n발의 화살을 어떤 과녁 점수에 맞혀야 하는지 구하기 2. 어피치가 이미 쏜 상태이고 라이언이 어피치를 가장
 * 큰 점수차로 이기기 위한 방법을 찾아야 한다.
 * <p>
 * 알아야 할 것 1. 1~10사이의 과녁에 어피치와 라이언이 같은 곳에 같은 수의 화살이 박혔을 경우는 어피치가 우선순위를 갖는다. 2. 한 곳에 여러 개의 화살을 꽂아도
 * 점수는 하나로 측정된다.
 * <p>
 * 제한 사항 1. 1 ≤ n ≤ 10 2. info의 길이 = 11
 */

/**
 * 알아야 할 것
 * 1. 1~10사이의 과녁에 어피치와 라이언이 같은 곳에 같은 수의 화살이 박혔을 경우는 어피치가 우선순위를 갖는다.
 * 2. 한 곳에 여러 개의 화살을 꽂아도 점수는 하나로 측정된다.
 */

/**
 * 제한 사항
 * 1. 1 ≤ n ≤ 10
 * 2. info의 길이 = 11
 */

/**
 * 풀이 방법 1.
 */

class 양궁대회_Solution {

    int N;
    int[] result = {-1};
    int[] lion = new int[11];
    int maxValue = Integer.MIN_VALUE;
    int idx = 0;
    public int[] solution(int n, int[] info) {
        N = n;
        Arrays.fill(lion, 0);
        dfs(info, 0);
        return result;
    }

    private void dfs(int[] info, int count) {
        idx++;
        if (count == N) {
            calculateScore(info);
            return;
        }

        // lion[i]의 값이 info[i]보다 크다면 해당 인덱스는 더이상 더할 필요가 없기 때문에 넘어간다.
        for (int i = 0; i <= 10 && lion[i] <= info[i]; i++) {
            lion[i]++;
            dfs(info, count + 1);
            lion[i]--;
        }
    }

    private void calculateScore(int[] info) {
        int apeachScore = 0;
        int lionScore = 0;

        for (int i = 0; i <= 10; i++) {
            if (info[i] == 0 && lion[i] == 0) {
                continue;
            }

            if (info[i] < lion[i]) {
                lionScore += 10 - i;
            } else {
                apeachScore += 10 - i;
            }
        }
        compareWithScore(lionScore, apeachScore);
    }

    private void compareWithScore(int lionScore, int apeachScore) {
        int scoreDiff = lionScore - apeachScore;
        if (scoreDiff > 0 && scoreDiff >= maxValue) {
            result = lion.clone();
            maxValue = scoreDiff;
        }
    }


}


