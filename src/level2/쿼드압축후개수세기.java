package level2;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.HashSet;
import java.util.List;
import java.util.Set;
import java.util.stream.Collectors;

public class 쿼드압축후개수세기 {

    public static void main(String[] args) {
        쿼드압축후개수세기_Solution solution = new 쿼드압축후개수세기_Solution();
        int[][] arr = {{1,1,0,0},{1,0,0,0},{1,0,0,1},{1,1,1,1}};
        int[] result = solution.solution(arr);
        for (int i : result) {
            System.out.print(i + ",");
        }
    }
}

/**
 * 필요한 기능
 * - [ ] list의 사이즈가 1이 될떄까지 4분의 1로 계속 쪼개기.
 * - [ ] 쪼갠 리스트를 set을 통해 값이 하나가 되는지 확인하고 하나가 된다면 그 곳은 확인을 끝내고 set안에 든 값을 결과값에 +1 해줌
 */

class 쿼드압축후개수세기_Solution {
    int[] result = {0, 0};

    public int[] solution(int[][] arr) {
        List<List<Integer>> lst = new ArrayList<>();
        for (int[] a : arr) {
            lst.add(Arrays.stream(a).boxed().collect(Collectors.toList()));
        }
        divideArr(lst);
        return result;
    }

    private void divideArr(List<List<Integer>> lst) {
        Set<Integer> set = new HashSet<>();
        for (List<Integer> l : lst) {
            set.addAll(l);
        }
        if (set.size() == 1) {
            List<Integer> list = new ArrayList<>(set);
            result[list.get(0)]++;
            return;
        }

        int xMidSize = lst.size() / 2;
        int yMidSize = lst.get(0).size() / 2;

        List<List<Integer>> candidate = new ArrayList<>();
        for (int i = 0; i < xMidSize; i++) {
            candidate.add(lst.get(i).subList(0, yMidSize));
        }
        divideArr(candidate);

        candidate = new ArrayList<>();
        for (int i = xMidSize; i < lst.size(); i++) {
            candidate.add(lst.get(i).subList(0, yMidSize));
        }
        divideArr(candidate);

        candidate = new ArrayList<>();
        for (int i = 0; i < xMidSize; i++) {
            candidate.add(lst.get(i).subList(yMidSize, lst.get(0).size()));
        }
        divideArr(candidate);

        candidate = new ArrayList<>();
        for (int i = xMidSize; i < lst.size(); i++) {
            candidate.add(lst.get(i).subList(yMidSize, lst.get(0).size()));
        }
        divideArr(candidate);
    }
}
