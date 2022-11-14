package level3;

public class 징검다리건너기 {

    public static void main(String[] args) {
        징검다리건너기_Solution solution = new 징검다리건너기_Solution();
        int[] stones = {2, 4, 5, 3, 2, 1, 4, 2, 5, 1};
        int k = 3;
        System.out.println(solution.solution(stones, k));
    }
}

class 징검다리건너기_Solution {

    public int solution(int[] stones, int k) {
        int result = 0;
        int min = 0;
        int max = 200000000;

        while (min <= max) {
            int mid = (min + max) / 2;
            if (checkFriendCount(mid, k, stones)) {
                min = mid + 1;
                result = mid;
            } else {
                max = mid - 1;
            }
        }

        return result;
    }

    private boolean checkFriendCount(int friend, int k, int[] stones) {
        int count = 0;
        for (int i = 0; i < stones.length; i++) {
            if (stones[i] - friend < 0) {
                count++;
            } else {
                count = 0;
            }

            if (count == k) {
                return false;
            }
        }
        return true;
    }
}
