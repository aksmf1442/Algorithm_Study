package level2;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;

public class 빛의경로사이클 {

    public static void main(String[] args) {
        빛의경로사이클_Solution solution = new 빛의경로사이클_Solution();
        String[] grid = {"S", "S"};
        int[] result = solution.solution(grid);
        for (int i : result) {
            System.out.print("," + i);
        }
    }
}


class 빛의경로사이클_Solution {

    public int[] solution(String[] grid) {
        return new int[0];
    }
}
