package level2;

public class n2배열자르기 {

    public static void main(String[] args) {
        int n = 3;
        int left = 2;
        int right = 5;
        n2배열자르기_Solution solution = new n2배열자르기_Solution();
        int[] result = solution.solution(n, left, right);
        for (int i = 0; i < result.length; i++) {
            System.out.println(result[i]);
        }
    }
}

class n2배열자르기_Solution {

    public int[] solution(int n, long left, long right) {
        int len = (int) right - (int) left;
        int[] answer = new int[len + 1];

        int idx = 0;
        for (long i = left; i <= right; i++) {
            long row = i / n;
            long col = i % n;
            answer[idx++] = Math.max((int)row, (int)col) + 1;
        }

        return answer;
    }
}
