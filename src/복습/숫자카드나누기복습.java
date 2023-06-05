package 복습;

public class 숫자카드나누기복습 {

    public static void main(String[] args) {
        int[] arrayA = {10, 17};
        int[] arrayB = {5, 20};
        숫자카드나누기_Solution solution = new 숫자카드나누기_Solution();
        System.out.println(solution.solution(arrayA, arrayB));
    }
}

class 숫자카드나누기_Solution {

    public int solution(int[] arrayA, int[] arrayB) {
        int answer = 0;
        int minValue = Math.max(arrayA[0], arrayB[0]);

        for (int value = 2; value <= minValue; value++) {
            boolean isTrue = true;
            for (int i = 0; i < arrayA.length; i++) {
                if (arrayA[i] % value != 0 || arrayB[i] % value == 0) {
                    isTrue = false;
                    break;
                }
            }
            if (isTrue) {
                answer = Math.max(answer, value);
                continue;
            }

            isTrue = true;
            for (int i = 0; i < arrayB.length; i++) {
                if (arrayB[i] % value != 0 || arrayA[i] % value == 0) {
                    isTrue = false;
                    break;
                }
            }
            if (isTrue) {
                answer = Math.max(answer, value);
            }
        }
        return answer;
    }
}
