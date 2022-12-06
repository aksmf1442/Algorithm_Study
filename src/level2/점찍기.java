package level2;

public class 점찍기 {

    public static void main(String[] args) {
        int k = 2;
        int d = 4;
        점찍기_Solution solution = new 점찍기_Solution();
        System.out.println(solution.solution(k, d));
    }
}

class 점찍기_Solution {

    // x^2 + y^2 <= d^2
    // y^2 <= d^2 - x^2
    public long solution(int k, int d) {
        long answer = 0;
        for (int i = 0; i <= d/k; i++) {
            long x = (long) i * k;
            long y = (long) Math.sqrt(Math.pow(d, 2) - Math.pow(x, 2)) / k;

            // 0도 가능하기 때문에 +1
            answer += y + 1;
        }
        return answer;
    }
}
