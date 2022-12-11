package level2;

import java.util.Arrays;
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
        int[] newElements = new int[elements.length * 2];

        for(int i = 0; i < elements.length; i++) {
            newElements[i] = newElements[i + elements.length] = elements[i];
        }

        Set<Integer> set = new HashSet<>();

        for(int i = 1; i <= elements.length; i++) {
            for(int j = 0; j < elements.length; j++) {
                set.add(Arrays.stream(newElements, j, j+i).sum());
            }
        }

        return set.size();
    }
}
