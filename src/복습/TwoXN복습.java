package 복습;

public class TwoXN복습 {
    public static void main(String[] args) {
        TwoXN_Solution solution = new TwoXN_Solution();
        int n = 6;
        System.out.println(solution.solution(n));
    }
}

class TwoXN_Solution {
    public int solution(int n) {
        int[] result = new int[n];
        result[0] = 1;
        result[1] = 2;
        for (int i = 2; i < n; i++) {
            result[i] = (result[i - 1] + result[i - 2]) % 1000000007;
        }
        return result[n - 1];
    }
}
