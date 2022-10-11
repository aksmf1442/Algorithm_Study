package level2;

public class 괄호변환 {

    public static void main(String[] args) {
        괄호변환_Solution solution = new 괄호변환_Solution();
        solution.solution("(()())()");
//        solution.solution(")(");
//        solution.solution("()))((()");
    }
}

/**
 * 알아야 할 것
 * 1. (, )의 숫자가 같다면 균형잡힌 괄호 문자열
 * 2. 1번의 조건을 만족하고 (, )의 짝도 맞다면 올바른 괄호 문자열
 */

/**
 * 제한사항
 * 1. p는 '(' 와 ')' 로만 이루어진 문자열이며 길이는 2 이상 1,000 이하인 짝수입니다.
 */

/**
 * 문제 풀이 순서
 * 1. 입력이 빈 문자열인 경우, 빈 문자열을 반환합니다.
 * 2. 문자열 w를 두 "균형잡힌 괄호 문자열" u, v로 분리합니다.
 *    단, u는 "균형잡힌 괄호 문자열"로 더 이상 분리할 수 없어야 하며, v는 빈 문자열이 될 수 있습니다.
 * 3. 문자열 u가 "올바른 괄호 문자열" 이라면 문자열 v에 대해 1단계부터 다시 수행합니다.
 * 4. 문자열 u가 "올바른 괄호 문자열"이 아니라면 아래 과정을 수행합니다.
 *  (1) 빈 문자열에 첫 번째 문자로 '('를 붙입니다.
 *  (2) 문자열 v에 대해 1단계부터 재귀적으로 수행한 결과 문자열을 이어 붙입니다.
 *  (3) ')'를 다시 붙입니다.
 *  (4) u의 첫 번째와 마지막 문자를 제거하고, 나머지 문자열의 괄호 방향을 뒤집어서 뒤에 붙입니다.
 *  (5) 생성된 문자열을 반환합니다.
 */

/**
 * goal: 주어진 괄호를 올바른 괄호 문자열로 변환하는 것이 목표
 */

/**
 * 시간복잡도: O(n)
 */
class 괄호변환_Solution {
    public String solution(String p) {
        System.out.println("findCorrectBracket = " + findCorrectBracket(p));
        return findCorrectBracket(p);
    }

    private String findCorrectBracket(String p) {
        // 1. 빈 문자열일시 빈 문자열 반환
        if (p.equals("")) {
            return "";
        }

        // 2. 문자열 w를 두 "균형잡힌 괄호 문자열" u, v로 분리합니다.
        String[] uv = extractUAndV(p);
        String u = uv[0];
        String v = uv[1];

        // 3. 올바른 괄호 문자열인지 확인
        if (isCorrectBracket(u)) {
            // 3.1 v를 다시 재귀적으로 올바른 괄호 문자열 구하기
            return u + findCorrectBracket(v);
        }

        // 4. 아니라면 문제 설명에서 하란대로 하기.
        u = u.substring(1, u.length() - 1);
        String newU = "";

        for (int i = 0; i < u.length(); i++) {
            if (u.charAt(i) == '(') {
                newU += ")";
            } else {
                newU += "(";
            }
        }

        return "(" + findCorrectBracket(v) + ")" + newU;


    }

    private String[] extractUAndV(String p) {
        int count = 0;
        int idx = p.length();
        for (int i = 0; i < p.length(); i++) {
            if (p.charAt(i) == '(') {
                count += 1;
            }

            if (p.charAt(i) == ')') {
                count -= 1;
            }

            // count가 0이면 균형잡힌 문자열
            if (count == 0) {
                idx = i + 1;
                break;
            }
        }

        String u = p.substring(0, idx);
        String v = p.substring(idx);

        return new String[]{u, v};
    }

    private boolean isCorrectBracket(String p) {
        int count = 0;

        for (int i = 0; i < p.length(); i++) {
            if (p.charAt(i) == '(') {
                count += 1;
            } else {
                count -= 1;
            }

            if (count < 0) {
                return false;
            }
        }

        if (count != 0) {
            return false;
        }
        return true;
    }
}
