package level2;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;

public class 이모티콘할인행사 {
    public static void main(String[] args) {
        이모티콘할인행사_Solution solution = new 이모티콘할인행사_Solution();
        int[][] users = {{40, 10000}, {25, 10000}};
        int[] emoticons = {7000, 9000};
        int[] result = solution.solution(users, emoticons);
        for (int i : result) {
            System.out.print(i + ", ");
        }
    }
}

/**
 * 1. 이모티콘에서 모든 할인율의 경우의 수를 구하기 위해서 dfs를 통해 모든 경우의 수를 구한다.
 * 2. 구한 경우의 수로 users의 값을 계산해서 가장 최선의 수를 얻어낸다.
 */

class 이모티콘할인행사_Solution {

    int[][] users;
    int[] emoticons;
    int[] answer;

    public int[] solution(int[][] users, int[] emoticons) {
        answer = new int[2];
        this.users = users;
        this.emoticons = emoticons;

        dfs(new ArrayList<>());

        return answer;
    }

    private void dfs(List<Integer> candidate) {
        if (emoticons.length == candidate.size()) {
            int emoticonPlus = 0;
            int cost = 0;
            for (int[] user : users) {
                int userCost = calculateUserCost(candidate, user);

                if (userCost >= user[1]) {
                    emoticonPlus++;
                } else {
                    cost += userCost;
                }
            }

            if (emoticonPlus > answer[0] || (emoticonPlus == answer[0] && cost > answer[1])) {
                answer[0] = emoticonPlus;
                answer[1] = cost;
            }
            return;
        }

        for (int ratio = 10; ratio <= 40; ratio += 10) {
            candidate.add(ratio);
            dfs(candidate);
            candidate.remove(candidate.size() - 1);
        }
    }

    private int calculateUserCost(List<Integer> candidate, int[] user) {
        int currentCost = 0;
        for (int i = 0; i < emoticons.length; i++) {
            int price = emoticons[i];
            int ratio = candidate.get(i);

            if (ratio < user[0]) {
                continue;
            }

            int discountPrice = price - (ratio * price / 100);
            currentCost += discountPrice;
        }
        return currentCost;
    }
}