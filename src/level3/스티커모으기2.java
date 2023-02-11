package level3;

import java.util.Arrays;

public class 스티커모으기2 {

    public static void main(String[] args) {
        스티커모으기2_Solution solution = new 스티커모으기2_Solution();
        int[] sticker = {14, 6, 5, 11, 3, 9, 2, 10};
        System.out.println("solution.solution(sticker) = " + solution.solution(sticker));
    }
}

class 스티커모으기2_Solution {
    public int solution(int sticker[]) {
        int[] dp1 = new int[sticker.length];
        int[] dp2 = new int[sticker.length];

        if (sticker.length == 1) {
            return sticker[0];
        }

        dp1[0] = sticker[0];
        dp1[1] = dp1[0];

        for (int i = 2; i < sticker.length - 1; i++) {
            dp1[i] = Math.max(dp1[i - 2] + sticker[i], dp1[i - 1]);
        }

        for (int i = 1; i < sticker.length; i++) {
            int idx = i - 2 == -1 ? sticker.length - 1 : i - 2;
            dp2[i] = Math.max(dp2[idx] + sticker[i], dp2[i - 1]);
        }

        return Math.max(dp1[dp1.length - 2], dp2[dp2.length - 1]);
    }
}
