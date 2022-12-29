package level2;


public class 양궁대회 {

    public static void main(String[] args) {
        양궁대회_Solution solution = new 양궁대회_Solution();
        int n = 5;
        int[] info = {2, 1, 1, 1, 0, 0, 0, 0, 0, 0, 0};
        int[] result = solution.solution(n, info);

        for (int i = 0; i < result.length; i++) {
            System.out.println("result = " + result[i]);
        }
    }
}

class 양궁대회_Solution {

    int[] result = {-1};
    int[] lion = new int[11];
    int[] apeach;
    int maxValue = 0;

    public int[] solution(int n, int[] info) {
        apeach = info;
        dfs(n);
        return result;
    }

    private void dfs(int n) {
        if (n == 0) {
            calculateScore();
            return;
        }

        // lion[i]의 값이 info[i]보다 크다면 해당 인덱스는 더이상 더할 필요가 없기 때문에 넘어간다.
        for (int i = 0; i <= 10; i++) {
            if (lion[i] > apeach[i]) {
                break;
            }

            lion[i]++;
            dfs(n - 1);
            lion[i]--;
        }
    }

    private void calculateScore() {
        int apeachScore = 0;
        int lionScore = 0;

        for (int i = 0; i <= 10; i++) {
            if (apeach[i] == 0 && lion[i] == 0) {
                continue;
            }

            if (apeach[i] < lion[i]) {
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


