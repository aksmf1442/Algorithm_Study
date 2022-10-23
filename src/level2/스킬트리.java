package level2;

public class 스킬트리 {

    public static void main(String[] args) {
        스킬트리_Solution solution = new 스킬트리_Solution();
        String skill = "CBD";
        String[] skill_trees = {"BACDE", "CBADF", "AECB", "BDA"};
        System.out.println(
            "solution.solution(skill, skill_trees) = " + solution.solution(skill, skill_trees));
    }
}

/**
 * goal: 가능한 스킬트리의 개수 구하기
 */

class 스킬트리_Solution {

    public int solution(String skill, String[] skill_trees) {
        int answer = 0;
        int skillLength = skill.length();
        for (String sk : skill_trees) {
            int idx = 0;
            answer++;
            for (int i = 0; i < skillLength; i++) {
                int skIdx = sk.indexOf(skill.charAt(i));
                if (skIdx == -1) {
                    idx = sk.length();
                    continue;
                }
                if (skIdx < idx) {
                    answer--;
                    break;
                }
                idx = skIdx;
            }
        }

        return answer;
    }
}
