package level3;

import java.util.HashSet;
import java.util.LinkedHashSet;
import java.util.Set;

public class 불량사용자 {

    public static void main(String[] args) {
        불량사용자_Solution solution = new 불량사용자_Solution();
        String[] user_id = {"frodo", "fradi", "crodo", "abc123", "frodoc"};
        String[] banned_id = {"fr*d*", "abc1**"};
        System.out.println("solution = " + solution.solution(user_id, banned_id));
    }
}

/**
 * goal: 당첨에서 제외되어야 할 제재 아이디 목록은 몇가지 경우의 수인지 찾기
 */

/**
 * 풀이 방법
 *
 */

class 불량사용자_Solution {

    private Set<Set<String>> result;
    private String[] user_id;
    private String[] banned_id;

    public int solution(String[] user_id, String[] banned_id) {
        result = new HashSet<>();
        this.user_id = user_id;
        this.banned_id = banned_id;

        dfs(new LinkedHashSet<>());
        return result.size();
    }

    private void dfs(LinkedHashSet<String> bannedIdSet) {
        if (bannedIdSet.size() == banned_id.length) {
            if (validateBannedIdSet(bannedIdSet)) {
                result.add(new HashSet<>(bannedIdSet));
            }
            return;
        }

        // 모든 경우의 수 확인
        for (String userId : user_id) {
            if (!bannedIdSet.contains(userId)) {
                bannedIdSet.add(userId);
                dfs(bannedIdSet);
                bannedIdSet.remove(userId);
            }
        }
    }

    private boolean validateBannedIdSet(LinkedHashSet<String> bannedIdSet) {
        int idx = 0;
        for (String candidateBannedId : bannedIdSet) {
            String bannedId = banned_id[idx++];
            if (candidateBannedId.length() != bannedId.length() || !isPossibleBannedId(
                candidateBannedId, bannedId)) {
                return false;
            }
        }
        return true;
    }

    private boolean isPossibleBannedId(String candidateBannedId, String bannedId) {
        for (int i = 0; i < candidateBannedId.length(); i++) {
            if (bannedId.charAt(i) != '*' && bannedId.charAt(i) != candidateBannedId.charAt(i)) {
                return false;
            }
        }
        return true;
    }

}
