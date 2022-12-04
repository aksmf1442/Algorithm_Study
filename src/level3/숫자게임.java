package level3;

import java.util.Arrays;
import java.util.List;
import java.util.stream.Collectors;

public class 숫자게임 {

    public static void main(String[] args) {
        int[] A = {5, 1, 3, 7};
        int[] B = {2, 2, 6, 8};
        숫자게임_Solution solution = new 숫자게임_Solution();
        System.out.println(solution.solution(A, B));
    }
}

class 숫자게임_Solution {

    public int solution(int[] A, int[] B) {
        int answer = 0;
        List<Integer> aList = Arrays.stream(A).sorted().boxed().collect(Collectors.toList());
        List<Integer> bList = Arrays.stream(B).sorted().boxed().collect(Collectors.toList());
        while (!aList.isEmpty() && !bList.isEmpty()) {
            Integer a = aList.remove(0);
            Integer b = bList.remove(0);
            while (!bList.isEmpty() && a >= b) {
                b = bList.remove(0);
            }

            if (a < b) {
                answer++;
            }
        }
        return answer;
    }
}
