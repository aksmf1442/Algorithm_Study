package level2;

import java.util.*;

public class 시소짝꿍 {
    public static void main(String[] args) {
        시소짝꿍_Solution solution = new 시소짝꿍_Solution();
        int[] weights = {100, 180, 360, 100, 270};
        long result = solution.solution(weights);
        System.out.println(result);
    }
}

class 시소짝꿍_Solution {
    public long solution(int[] weights) {
        long answer = 0;

        Arrays.sort(weights);

        int tmpCnt = 0;
        for (int i = 0; i < weights.length - 1; i++) {
            if (i != 0) {
                if (weights[i] == weights[i - 1]) {
                    tmpCnt--;
                    answer += tmpCnt;
                    continue;
                }
            }
            tmpCnt = 0;

            for (int j = i + 1; j < weights.length; j++) {
                if (
                        weights[i] == weights[j] ||
                                weights[i] * 2 == weights[j] * 3 ||
                                weights[i] * 2 == weights[j] * 4 ||
                                weights[i] * 3 == weights[j] * 2 ||
                                weights[i] * 3 == weights[j] * 4 ||
                                weights[i] * 4 == weights[j] * 2 ||
                                weights[i] * 4 == weights[j] * 3
                ) {
                    tmpCnt++;
                }
            }
            System.out.println(tmpCnt);
            answer += tmpCnt;
        }

        return answer;
    }

}