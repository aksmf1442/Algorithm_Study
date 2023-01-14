package level3;

import java.util.Stack;

public class 표편집 {

    public static void main(String[] args) {
        표편집_Solution solution = new 표편집_Solution();
        int n = 8;
        int k = 2;
        String[] cmd = {"D 2", "C", "U 3", "C", "D 4", "C", "U 2", "Z", "Z", "U 1", "C"};
        String result = solution.solution(n, k, cmd);
        for (int i = 0; i < result.length(); i++) {
            System.out.print(result.charAt(i));
        }
    }
}

class 표편집_Solution {

    public String solution(int n, int k, String[] cmd) {
        return "";
    }
}
