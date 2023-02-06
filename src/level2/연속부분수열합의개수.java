package level2;

import java.util.HashSet;
import java.util.Set;

public class 연속부분수열합의개수 {

    public static void main(String[] args) {
        연속부분수열합의개수_Solution solution = new 연속부분수열합의개수_Solution();
        int[] elements = {7, 9, 1, 1, 4};
        System.out.println(solution.solution(elements));
    }
}

class 연속부분수열합의개수_Solution {
    public int solution(int[] elements) {
        int N = elements.length;
        Set<Integer> candidate = new HashSet<>();
        for (int size = 1; size <= N; size++) {
            for (int i = 0; i < elements.length; i++) {
                int value = 0;
                for (int j = 0; j < size; j++) {
                    int idx = (i + j) % N;
                    value += elements[idx];
                }
                candidate.add(value);
            }
        }
        return candidate.size();
    }
}
