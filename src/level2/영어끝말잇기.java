package level2;

import java.util.HashMap;
import java.util.Map;

public class 영어끝말잇기 {

    public static void main(String[] args) {
        영어끝말잇기_Solution solution = new 영어끝말잇기_Solution();
        int n = 3;
        String[] words = {"tank", "kick", "know", "wheel", "land", "dream", "mother", "robot",
            "tank"};
        int[] result = solution.solution(n, words);
    }
}


/**
 * goal:사람의 수 n과 사람들이 순서대로 말한 단어 words 가 매개변수로 주어질 때, 가장 먼저 탈락하는 사람의 번호와 그 사람이 자신의 몇 번째 차례에 탈락하는지를
 * 알아내기.
 */

/**
 * 제한사항
 * 1. 끝말잇기에 참여하는 사람의 수 n은 2 이상 10 이하의 자연수입니다.
 * 2. words는 끝말잇기에 사용한 단어들이 순서대로 들어있는 배열이며, 길이는 n 이상 100 이하입니다.
 */

/**
 * 시간복잡도: O(words.length) 최대 : 100
 */

class 영어끝말잇기_Solution {

    public int[] solution(int n, String[] words) {
        Map<String, Integer> wordMap = new HashMap();
        int turn = 0;
        int participant = 0;

        wordMap.put(words[0], 1);
        for (int i = 1; i < words.length; i++) {

            // 한 번도 사용되지 않았고, 끝말잇기도 성공했다면 pass
            if (wordMap.get(words[i]) == null && words[i].charAt(0) == words[i - 1].charAt(
                words[i - 1].length() - 1)) {
                wordMap.put(words[i], 1);
                continue;
            }
            turn = (i / n) + 1;
            participant = (i % n) + 1;
            break;
        }

        return new int[]{turn, participant};
    }
}
